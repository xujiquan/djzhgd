package com.djzhgd.project.system.service.impl;

import cn.hutool.core.io.resource.ClassPathResource;
import com.djzhgd.common.utils.DateUtils;
import com.djzhgd.framework.config.DjzhgdConfig;
import com.djzhgd.project.system.domain.*;

import com.djzhgd.project.system.mapper.*;
import com.djzhgd.project.system.service.IZxPatrolBaseService;
import com.djzhgd.project.utils.Utils;
import com.djzhgd.project.utils.ZhzxImgs;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 巡查管理Service业务层处理
 *
 * @author suenle
 * @date 2020-03-12
 */
@Service
public class ZxPatrolBaseServiceImpl implements IZxPatrolBaseService
{

    @Autowired
    private ZxPatrolBaseMapper zxPatrolBaseMapper;
    @Autowired
    private ZxPatrolBackMapper zxPatrolBackMapper;
    @Autowired
    private ZxDeptInfoMapper zxDeptInfoMapper;
    @Autowired
    private ZxMechanicalInfoMapper zxMechanicalInfoMapper;
    @Autowired
    private ZxFileInfoMapper zxFileInfoMapper;



    /**
     * 查询巡查管理
     *
     * @param patrolId 巡查管理ID
     * @return 巡查管理
     */
    @Override
    public ZxPatrolBase selectZxPatrolBaseById(Long patrolId)
    {
        return zxPatrolBaseMapper.selectZxPatrolBaseById(patrolId);
    }

    /**
     * 查询巡查管理列表
     *
     * @param zxPatrolBase 巡查管理
     * @return 巡查管理
     */
    @Override
    public List<ZxPatrolBase> selectZxPatrolBaseList(ZxPatrolBase zxPatrolBase)
    {
        return zxPatrolBaseMapper.selectZxPatrolBaseList(zxPatrolBase);
    }

    /**
     * 新增巡查管理
     *
     * @param zxPatrolBase 巡查管理
     * @return 结果
     */
    @Override
    public int insertZxPatrolBase(ZxPatrolBase zxPatrolBase)
    {
        zxPatrolBase.setCreateTime(DateUtils.getNowDate());
        return zxPatrolBaseMapper.insertZxPatrolBase(zxPatrolBase);
    }

    /**
     * 修改巡查管理
     *
     * @param zxPatrolBase 巡查管理
     * @return 结果
     */
    @Override
    public int updateZxPatrolBase(ZxPatrolBase zxPatrolBase)
    {
        zxPatrolBase.setUpdateTime(DateUtils.getNowDate());
        return zxPatrolBaseMapper.updateZxPatrolBase(zxPatrolBase);
    }

    /**
     * 批量删除巡查管理
     *
     * @param patrolIds 需要删除的巡查管理ID
     * @return 结果
     */
    @Override
    public int deleteZxPatrolBaseByIds(Long[] patrolIds)
    {
        return zxPatrolBaseMapper.deleteZxPatrolBaseByIds(patrolIds);
    }

    /**
     * 删除巡查管理信息
     *
     * @param patrolId 巡查管理ID
     * @return 结果
     */
    @Override
    public int deleteZxPatrolBaseById(Long patrolId)
    {
        return zxPatrolBaseMapper.deleteZxPatrolBaseById(patrolId);
    }

    /**
     * 生成word并下载
     * @param patrolId
     * @return
     */
    public Map<String, Object> downFileWord(Long patrolId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> objectMap = new HashMap<>();
        // 根据巡查管理ID 查询一条 巡查管理 信息
        ZxPatrolBase zxPatrolBase = zxPatrolBaseMapper.selectZxPatrolBaseById(patrolId);
        // 项目信息
        ZxDeptInfo zxParentDeptInfo = null;
        // 摊铺设备数据
        List<ZxMechanicalInfo> zxMaterialInfoTP = null;
        // 碾压设备数据
        List<ZxMechanicalInfo> zxMaterialInfoNY = null;
        // 后场巡查的上一条数据
        ZxPatrolBack zxPatrolBackFirst = null;
        // 根据 巡查标段id(dept_id)查询项目和标段名称
        String sectionName = "";    // 标段名称
        if(zxPatrolBase != null && zxPatrolBase.getDeptId() != null){
            // 巡查标段id
            Long deptId = zxPatrolBase.getDeptId();
            String createDate = "";
            if(zxPatrolBase.getCreateTime() != null){
                createDate = Utils.othToString(zxPatrolBase.getCreateTime());
            }
            // 巡查管理ID
            // 巡查管理同一个deptId下的上一条数据
            ZxPatrolBase zxPatrolBaseFirst = null;
            List<ZxPatrolBase> zxPatrolBaseList = zxPatrolBaseMapper.findBydeptId(deptId, createDate);
            if(zxPatrolBaseList != null && zxPatrolBaseList.size() > 0){
                zxPatrolBaseFirst = zxPatrolBaseList.get(0);    // 取除去本条数据的上一条数据
            }
            if(zxPatrolBaseFirst != null && zxPatrolBaseFirst.getPatrolId() != null){
                List<ZxPatrolBack> zxPatrolBackFirstList = zxPatrolBackMapper.findByPatrolId(zxPatrolBaseFirst.getPatrolId());
                if(zxPatrolBackFirstList != null && zxPatrolBackFirstList.size() > 0){
                    zxPatrolBackFirst = zxPatrolBackFirstList.get(0);
                }
            }
            // 项目标段信息表
            ZxDeptInfo zxDeptInfo = zxDeptInfoMapper.selectZxDeptInfoById(deptId);
            if(zxDeptInfo != null){
                if(zxDeptInfo.getInfoType().intValue() == 2){   //此条是项目
                    objectMap.put("201", "此条数据不能生成word文档");
                    return objectMap;
                }else if(zxDeptInfo.getInfoType().intValue() == 3){ // 此条是标的
                    sectionName = zxDeptInfo.getDeptName();
                    // 父类ID
                    Long parentId = zxDeptInfo.getParentId();
                    // 根据父类ID查询项目信息
                    zxParentDeptInfo = zxDeptInfoMapper.selectZxDeptInfoById(parentId);
                    ZxMechanicalInfo zxMechanicalInfo = new ZxMechanicalInfo();
                    zxMechanicalInfo.setDeptId(deptId);
                    zxMechanicalInfo.setStatus("0");
                    zxMechanicalInfo.setDelFlag("0");
                    // 机械类型是 摊铺设备
                    zxMechanicalInfo.setMechanicalType("1");
                    zxMaterialInfoTP = zxMechanicalInfoMapper.selectZxMechanicalInfoList(zxMechanicalInfo);
                    // 机械类型是 碾压设备
                    zxMechanicalInfo.setMechanicalType("2");
                    zxMaterialInfoNY = zxMechanicalInfoMapper.selectZxMechanicalInfoList(zxMechanicalInfo);
                }
            }
        }
        // 根据巡查管理ID 查询一组 前场巡查 信息
        List<ZxPatrolBack> zxPatrolBackList = zxPatrolBackMapper.findByPatrolId(patrolId);

        ZxPatrolBack zxPatrolBack = null;
        // 获取最新的一条 前场巡查 信息
        if(zxPatrolBackList != null && zxPatrolBackList.size() > 0){
            zxPatrolBack = zxPatrolBackList.get(0);
        }
        objectMap = createWord(request, response, zxPatrolBase, zxPatrolBack, zxParentDeptInfo, sectionName, zxMaterialInfoTP, zxMaterialInfoNY, zxPatrolBackFirst);
        return objectMap;
    }
    /**
     * 封装Map,生成Word
     * @param zxPatrolBase 巡查管理对象
     * @param zxPatrolBack 后场巡查对象
     * @param zxParentDeptInfo  项目标段信息
     *  @param sectionName  标段名称
     * @param zxMaterialInfoTP 摊铺机械数据
     * @param zxMaterialInfoNY 碾压机械数据
     * @param zxPatrolBackFirst 上一条有问题的巡查数据
     * @return
     */
    public Map<String, Object> createWord(HttpServletRequest request, HttpServletResponse response, ZxPatrolBase zxPatrolBase, ZxPatrolBack zxPatrolBack, ZxDeptInfo zxParentDeptInfo,
                                                 String sectionName, List<ZxMechanicalInfo> zxMaterialInfoTP, List<ZxMechanicalInfo> zxMaterialInfoNY, ZxPatrolBack zxPatrolBackFirst)  {
        Map<String, Object> stringObjectMap = new HashMap<>();
        // 封装Map
        Map<String, Object> wordMap = assembleMap(zxPatrolBase, zxPatrolBack, zxParentDeptInfo, zxMaterialInfoTP, zxMaterialInfoNY, zxPatrolBackFirst);
        wordMap.put("sectionName", sectionName);    // 标段名称

        // word模板ftl的名称
        String templateName = "information.ftl";

        // 生成新word文件的路径
        String filePath = DjzhgdConfig.getReportCreatePath();
        ClassPathResource resource = new ClassPathResource("ftl/information.ftl");

        // word模板ftl的路径
        File file =null;
        try {
            file = ResourceUtils.getFile("classpath:ftl/information.ftl");
        }catch (Exception e){
            stringObjectMap.put("code", 201);
            stringObjectMap.put("msg", "生成word失败！");
            return stringObjectMap;
        }
        String templatePath = file.getParent();
        // 生成新word文件的唯一名称
        long timeMillis = System.currentTimeMillis(); // 13位数的时间戳
        String patrolName = zxPatrolBase.getPatrolName();   // 巡查名称
        String fileName = timeMillis + "_" + patrolName + ".doc";
        String zipFileName = timeMillis + "_" + patrolName + ".zip";
        // 输出文件(路径 + 名称)
        String newWord = filePath + "/" + fileName;
        // 生成word
        try{
            createWord(wordMap, templatePath, templateName, newWord);
        }catch(Exception e){
            stringObjectMap.put("code", 201);
            stringObjectMap.put("msg", "生成word失败！");
            return stringObjectMap;
        }
        // 下载word
        try{
            dowloadWord(request, response, zipFileName, fileName, newWord);
        }catch(Exception e){
            stringObjectMap.put("code", 201);
            stringObjectMap.put("msg", "下载wor压缩包失败！");
            return stringObjectMap;
        }
        stringObjectMap.put("code", 200);
        stringObjectMap.put("msg", "下载成功！");
        return stringObjectMap;
    }

    /**
     * 封装生成word的MAP对象
     * @param zxPatrolBase
     * @param zxPatrolBack
     * @return
     */
    public  Map<String, Object> assembleMap(ZxPatrolBase zxPatrolBase, ZxPatrolBack zxPatrolBack, ZxDeptInfo zxParentDeptInfo,
                                                  List<ZxMechanicalInfo> zxMaterialInfoTP, List<ZxMechanicalInfo> zxMaterialInfoNY, ZxPatrolBack zxPatrolBackFirst){
        // 用于组装word页面需要的数据
        Map<String, Object> wordMap = new HashMap<>();
        wordMap.put("bianzhi", "");    // 编制
        wordMap.put("fuhe", "");    // 复核
        wordMap.put("bianhao", "");    // 编号
        String createDate = Utils.getToday();
        wordMap.put("createDate", createDate);    // 日期自动生成
        // 项目信息赋值
        String projectName = "";    // 项目名称
        String customerName = "";  // 甲方名称
        if(zxParentDeptInfo != null){
            projectName = Utils.othToString(zxParentDeptInfo.getDeptName());
            customerName = Utils.othToString(zxParentDeptInfo.getCustomerName());
        }
        wordMap.put("projectName", projectName);
        wordMap.put("customerName", customerName);
        // 摊铺机械信息
        StringBuffer mechanicalModelTP = new StringBuffer();
        if(zxMaterialInfoTP != null){
            wordMap.put("tpList","" + zxMaterialInfoTP.size());
            for(int i=0; i<zxMaterialInfoTP.size(); i++){
                ZxMechanicalInfo zxMechanicalInfo = zxMaterialInfoTP.get(i);
                if(i == (zxMaterialInfoTP.size() - 1)){
                    mechanicalModelTP.append(zxMechanicalInfo.getMechanicalModel());
                }else{
                    mechanicalModelTP.append(zxMechanicalInfo.getMechanicalModel() + "、");
                }
            }
            wordMap.put("tpMechanicalModel", mechanicalModelTP.toString());
        }else{
            wordMap.put("tpList","");
            wordMap.put("tpMechanicalModel","");
        }
        // 碾压机械信息
        if(zxMaterialInfoNY != null){
            wordMap.put("nyList","" + zxMaterialInfoNY.size());
        }else{
            wordMap.put("nyList","");
        }
        // 巡查管理zxPatrolBase赋值
        if(zxPatrolBase != null){
            wordMap.put("patrolId", Utils.othToString(zxPatrolBase.getPatrolId()));//主键
            wordMap.put("deptId", Utils.othToString(zxPatrolBase.getDeptId()));//巡查标段id
            wordMap.put("parentId", Utils.othToString(zxPatrolBase.getParentId()));//备用字段
            wordMap.put("patrolName", Utils.othToString(zxPatrolBase.getPatrolName()));//备用字段
            wordMap.put("constructionStatus", Utils.othToString(zxPatrolBase.getConstructionStructure()));//施工情况
            wordMap.put("weather", Utils.othToString(zxPatrolBase.getWeather()));//天气
            wordMap.put("temperature", Utils.othToString(zxPatrolBase.getTemperature())); //气温
            wordMap.put("windSpeed", Utils.othToString(zxPatrolBase.getWindSpeed()));  // 风速
            wordMap.put("constructionStructure", Utils.othToString(zxPatrolBase.getConstructionStructure())); //结构层
            wordMap.put("constructionSingle", Utils.othToString(zxPatrolBase.getConstructionSingle()));//单幅总长度
            wordMap.put("isFrontPatrol", Utils.othToString(zxPatrolBase.getIsFrontPatrol()));//是否前场巡查
            wordMap.put("isBackPatrol", Utils.othToString(zxPatrolBase.getIsBackPatrol()));//是否后场巡查
            wordMap.put("isBackGk", Utils.othToString(zxPatrolBase.getIsBackGk()));//是否后场是否安装管控
            wordMap.put("isTestCase", Utils.othToString(zxPatrolBase.getIsTestCase()));//室内试验运行情况
            wordMap.put("isEngineeringAppearance", Utils.othToString(zxPatrolBase.getIsEngineeringAppearance()));//实体工程外观情况
            wordMap.put("isAsphaltOnSite", Utils.othToString(zxPatrolBase.getIsAsphaltOnSite()));//沥青驻场情况
            wordMap.put("isSwFlowType", Utils.othToString(zxPatrolBase.getIsSwFlowType()));//水稳拌合楼流量标定
            wordMap.put("isLqFlowType", Utils.othToString(zxPatrolBase.getIsLqFlowType()));//沥青拌合楼流量标定
            wordMap.put("isIndoorProjectInformation", Utils.othToString(zxPatrolBase.getIsIndoorProjectInformation()));//室内试验资料汇总
            wordMap.put("isSceneProjectInformation", Utils.othToString(zxPatrolBase.getIsSceneProjectInformation()));//现场检测资料汇总
            wordMap.put("status", Utils.othToString(zxPatrolBase.getStatus()));//状态（0正常 1停用）
            wordMap.put("delFlag", Utils.othToString(zxPatrolBase.getDelFlag()));//0代表存在 2代表删除
        }else{
            wordMap.put("patrolId", "");                //主键
            wordMap.put("deptId", "");                  //巡查标段id
            wordMap.put("parentId", "");                //备用字段
            wordMap.put("patrolName", "");              //备用字段
            wordMap.put("constructionStatus", "");      //施工情况
            wordMap.put("weather", "");                 //天气
            wordMap.put("temperature", "");             //气温
            wordMap.put("windSpeed", "");               // 风速
            wordMap.put("constructionStructure", "");   //结构层
            wordMap.put("constructionSingle", "");      //单幅总长度
            wordMap.put("isFrontPatrol", "");           //是否前场巡查
            wordMap.put("isBackPatrol", "");            //是否后场巡查
            wordMap.put("isBackGk", "");                //是否后场是否安装管控
            wordMap.put("isTestCase", "");              //室内试验运行情况
            wordMap.put("isEngineeringAppearance", ""); //实体工程外观情况
            wordMap.put("isAsphaltOnSite", "");         //沥青驻场情况
            wordMap.put("isSwFlowType", "");            //水稳拌合楼流量标定
            wordMap.put("isLqFlowType", "");            //沥青拌合楼流量标定
            wordMap.put("isIndoorProjectInformation", "");//室内试验资料汇总
            wordMap.put("isSceneProjectInformation", "");//现场检测资料汇总
            wordMap.put("status", "");                  //状态（0正常 1停用）
            wordMap.put("delFlag", "");                 //0代表存在 2代表删除
        }
        // 前场巡查zxPatrolBack赋值
        if(zxPatrolBack != null){
            wordMap.put("patrolBackId", Utils.othToString(zxPatrolBack.getPatrolBackId()));//后场巡查id
            wordMap.put("deptId", Utils.othToString(zxPatrolBack.getDeptId()));//巡查标段id
            wordMap.put("parentId", Utils.othToString(zxPatrolBack.getParentId()));//备用字段
            wordMap.put("patrolId", Utils.othToString(zxPatrolBack.getPatrolId()));//巡查id
            wordMap.put("pileNo", Utils.othToString(zxPatrolBack.getPileNo()));//施工桩号
            wordMap.put("patrolFrontName", Utils.othToString(zxPatrolBack.getPatrolFrontName()));//巡查名称
            wordMap.put("distance", Utils.othToString(zxPatrolBack.getDistance()));//施工距离
            wordMap.put("constructionStructure", Utils.othToString(zxPatrolBack.getConstructionStructure()));//施工结构层
            wordMap.put("setWithType", Utils.othToString(zxPatrolBack.getSetWithType()));//级配类型
            wordMap.put("isCover", Utils.othToString(zxPatrolBack.getIsCover()));//自卸汽车是否覆盖
            wordMap.put("coverDetails", Utils.othToString(zxPatrolBack.getCoverDetails()));//覆盖详情
            wordMap.put("isTransportTemperature", Utils.othToString(zxPatrolBack.getIsTransportTemperature()));//运输车辆到场温度
            wordMap.put("transportTemperatureDetails", Utils.othToString(zxPatrolBack.getTransportTemperatureDetails()));//温度详情
            wordMap.put("pavingWay", Utils.othToString(zxPatrolBack.getPavingWay()));//摊铺机摊铺方式
            wordMap.put("balancedWay", Utils.othToString(zxPatrolBack.getBalancedWay()));//摊铺机找平方式
            wordMap.put("isSpacing", Utils.othToString(zxPatrolBack.getIsSpacing()));//摊铺机间距控制
            wordMap.put("spacingDetails", Utils.othToString(zxPatrolBack.getSpacingDetails()));//间距详情
            wordMap.put("pavingTemperature", Utils.othToString(zxPatrolBack.getPavingTemperature()));//摊铺机摊铺温度
            wordMap.put("isPavingSituation", Utils.othToString(zxPatrolBack.getIsPavingSituation()));//是否经常停机
            wordMap.put("pavingDetails", Utils.othToString(zxPatrolBack.getPavingDetails()));//停机问题详情
            wordMap.put("isPavingResults", Utils.othToString(zxPatrolBack.getIsPavingResults()));//摊铺后铺面巡查
            wordMap.put("oneRccTemperature", Utils.othToString(zxPatrolBack.getOneRccTemperature()));//初压温度
            wordMap.put("twoRccTemperature", Utils.othToString(zxPatrolBack.getTwoRccTemperature()));//复压温度
            wordMap.put("threeRccTemperature", Utils.othToString(zxPatrolBack.getThreeRccTemperature()));//终压温度
            wordMap.put("oneRccSpeed", Utils.othToString(zxPatrolBack.getOneRccSpeed()));//初压速度
            wordMap.put("twoRccSpeed", Utils.othToString(zxPatrolBack.getTwoRccSpeed()));//复压速度
            wordMap.put("threeRccSpeed", Utils.othToString(zxPatrolBack.getThreeRccSpeed()));//终压速度
            wordMap.put("oneRccPass", Utils.othToString(zxPatrolBack.getOneRccPass()));//初压遍数
            wordMap.put("twoRccPass", Utils.othToString(zxPatrolBack.getTwoRccPass()));//复压遍数
            wordMap.put("threeRccPass", Utils.othToString(zxPatrolBack.getThreeRccPass()));//终压遍数
            wordMap.put("dataAnalysis", Utils.othToString(zxPatrolBack.getDataAnalysis()));//物联网数据分析
            wordMap.put("patrolBackIssue", Utils.othToString(zxPatrolBack.getPatrolBackIssue()));//存在问题及处理意见
            wordMap.put("status", Utils.othToString(zxPatrolBack.getStatus()));//状态（0正常 1停用）
            wordMap.put("delFlag", Utils.othToString(zxPatrolBack.getDelFlag()));//删除标志（0代表存在 2代表删除）
        }else{
            wordMap.put("patrolBackId", "");            //后场巡查id
            wordMap.put("deptId", "");                  //巡查标段id
            wordMap.put("parentId", "");                //备用字段
            wordMap.put("patrolId", "");                //巡查id
            wordMap.put("pileNo", "");                  //施工桩号
            wordMap.put("patrolFrontName", "");         //巡查名称
            wordMap.put("distance", "");                //施工距离
            wordMap.put("constructionStructure", "");   //施工结构层
            wordMap.put("setWithType", "");             //级配类型
            wordMap.put("isCover", "");                 //自卸汽车是否覆盖
            wordMap.put("coverDetails", "");            //覆盖详情
            wordMap.put("isTransportTemperature", "");  //运输车辆到场温度
            wordMap.put("transportTemperatureDetails", "");//温度详情
            wordMap.put("pavingWay", "");               //摊铺机摊铺方式
            wordMap.put("balancedWay", "");             //摊铺机找平方式
            wordMap.put("isSpacing", "");               //摊铺机间距控制
            wordMap.put("spacingDetails", "");          //间距详情
            wordMap.put("pavingTemperature", "");       //摊铺机摊铺温度
            wordMap.put("isPavingSituation", "");       //是否经常停机
            wordMap.put("pavingDetails", "");           //停机问题详情
            wordMap.put("isPavingResults", "");         //摊铺后铺面巡查
            wordMap.put("oneRccTemperature", "");       //初压温度
            wordMap.put("twoRccTemperature", "");       //复压温度
            wordMap.put("threeRccTemperature", "");     //终压温度
            wordMap.put("oneRccSpeed", "");             //初压速度
            wordMap.put("twoRccSpeed", "");             //复压速度
            wordMap.put("threeRccSpeed", "");           //终压速度
            wordMap.put("oneRccPass", "");              //初压遍数
            wordMap.put("twoRccPass", "");              //复压遍数
            wordMap.put("threeRccPass", "");            //终压遍数
            wordMap.put("dataAnalysis", "");            //物联网数据分析
            wordMap.put("patrolBackIssue", "");         //存在问题及处理意见
            wordMap.put("status", "");                  //状态（0正常 1停用）
            wordMap.put("delFlag", "");                 //删除标志（0代表存在 2代表删除）
        }
        // 上次巡查问题
        if(zxPatrolBackFirst != null){
            if("2".equals(zxPatrolBackFirst.getCoverDetails())){
                wordMap.put("coverDetailsFirst","自卸汽车是否未覆盖");
            }else{
                wordMap.put("coverDetailsFirst","");
            }
            if("2".equals(zxPatrolBackFirst.getIsTransportTemperature())){
                wordMap.put("isTransportTemperatureFirst","运输车辆到场温度异常");
            }else{
                wordMap.put("isTransportTemperatureFirst","");
            }
            if("3".equals(zxPatrolBackFirst.getIsSpacing())){
                wordMap.put("isSpacingFirst","摊铺机间距控制大于15米");
            }else{
                wordMap.put("isSpacingFirst","");
            }
            if("2".equals(zxPatrolBackFirst.getIsPavingSituation())){
                wordMap.put("isPavingSituationFirst","摊铺机是出现经常停机");
            }else{
                wordMap.put("isPavingSituationFirst","");
            }
            if(zxPatrolBackFirst.getPavingDetails() != null && !"".equals(zxPatrolBackFirst.getPavingDetails().trim())){
                wordMap.put("pavingDetailsFirst","停机原因：" + zxPatrolBackFirst.getPavingDetails().trim());
            }else{
                wordMap.put("pavingDetailsFirst","");
            }
            if("2".equals(zxPatrolBackFirst.getIsPavingResults())){
                wordMap.put("isPavingResultsFirst","摊铺后铺面巡查局部离析");
            }else if("3".equals(zxPatrolBackFirst.getIsPavingResults())){
                wordMap.put("isPavingResultsFirst","摊铺后铺面巡查严重离析");
            }else{
                wordMap.put("isPavingResultsFirst","");
            }
            if(zxPatrolBackFirst.getPatrolBackIssue() != null && !"".equals(zxPatrolBackFirst.getPatrolBackIssue().trim())){
                wordMap.put("patrolBackIssueFirst","存在问题及处理意见：" + zxPatrolBackFirst.getPatrolBackIssue().trim());
            }else{
                wordMap.put("patrolBackIssueFirst","");
            }
        }else{
            wordMap.put("coverDetailsFirst","");
            wordMap.put("isTransportTemperatureFirst","");
            wordMap.put("isSpacingFirst","");
            wordMap.put("isPavingSituationFirst","");
            wordMap.put("pavingDetailsFirst","");
            wordMap.put("isPavingResultsFirst","");
            wordMap.put("patrolBackIssueFirst","");
        }
        // 图片
        Double imgWidth = 0D;
        Double imgHeight = 0D;
        List<ZhzxImgs> picture = new ArrayList<>();
        ZhzxImgs djzhgdImgs = null;
        String apath = "D:/ideaWorkspace/erweima.jpg";
        String tubiao = getImageStr(apath);
        wordMap.put("tubiao", "");


        List<String> imgList=new ArrayList<String>();
        //获取出现问题的照片
        if(zxPatrolBack != null){
            if("2".equals(zxPatrolBack.getCoverDetails())){
                //wordMap.put("coverDetailsFirst","自卸汽车是否未覆盖");
                String  coverDetailsListArr= zxPatrolBack.getCoverDetails();
                String[] coverDetailsList =  coverDetailsListArr.split(",");
                for(String imgId:coverDetailsList){
                    ZxFileInfo zxFileInfo = zxFileInfoMapper.selectZxFileInfoById(Long.valueOf(imgId));
                    String zxFileInfoPath=DjzhgdConfig.getProfile()+zxFileInfo.getFilePath();
                    imgList.add(zxFileInfoPath);
                }
            }
            if("2".equals(zxPatrolBack.getIsTransportTemperature())){
                //wordMap.put("isTransportTemperatureFirst","运输车辆到场温度异常");
                String  transportTemperatureDetailsListArr= zxPatrolBack.getTransportTemperatureDetails();
                String[] transportTemperatureDetailsList =  transportTemperatureDetailsListArr.split(",");
                for(String imgId:transportTemperatureDetailsList){
                    ZxFileInfo zxFileInfo = zxFileInfoMapper.selectZxFileInfoById(Long.valueOf(imgId));
                    String zxFileInfoPath=DjzhgdConfig.getProfile()+zxFileInfo.getFilePath();
                    imgList.add(zxFileInfoPath);
                }
            }
            if("3".equals(zxPatrolBack.getIsSpacing())){
                //wordMap.put("isSpacingFirst","摊铺机间距控制大于15米");
                String  spacingDetailsListArr= zxPatrolBack.getSpacingDetails();
                String[] spacingDetailsList =  spacingDetailsListArr.split(",");
                for(String imgId:spacingDetailsList){
                    ZxFileInfo zxFileInfo = zxFileInfoMapper.selectZxFileInfoById(Long.valueOf(imgId));
                    String zxFileInfoPath=DjzhgdConfig.getProfile()+zxFileInfo.getFilePath();
                    imgList.add(zxFileInfoPath);
                }
            }
            if("2".equals(zxPatrolBack.getIsPavingSituation())){
                //wordMap.put("isPavingSituationFirst","摊铺机是出现经常停机");
                String  pavingDetailsListArr= zxPatrolBack.getPavingDetails();
                String[] pavingDetailsList =  pavingDetailsListArr.split(",");
                for(String imgId:pavingDetailsList){
                    ZxFileInfo zxFileInfo = zxFileInfoMapper.selectZxFileInfoById(Long.valueOf(imgId));
                    String zxFileInfoPath=DjzhgdConfig.getProfile()+zxFileInfo.getFilePath();
                    imgList.add(zxFileInfoPath);
                }
            }
            if("2".equals(zxPatrolBack.getIsPavingResults())||"3".equals(zxPatrolBack.getIsPavingResults())){
               // wordMap.put("isPavingResultsFirst","摊铺后铺面巡查局部离析");
                String  pavingResultsDetailsListArr= zxPatrolBack.getPavingResultsDetails();
                String[] pavingResultsDetailsList =  pavingResultsDetailsListArr.split(",");
                for(String imgId:pavingResultsDetailsList){
                    ZxFileInfo zxFileInfo = zxFileInfoMapper.selectZxFileInfoById(Long.valueOf(imgId));
                    String zxFileInfoPath=DjzhgdConfig.getProfile()+zxFileInfo.getFilePath();
                    imgList.add(zxFileInfoPath);
                }
            }
        }
        for(int i=0; i<imgList.size(); i++){
            djzhgdImgs = new ZhzxImgs();
            String path =imgList.get(i).replace("profile","");
            djzhgdImgs.setImageBase(getImageStr(path));
            int imgHeight2 = Utils.getImgHeight(new File(path));
            if(imgHeight2<200){
                imgWidth = Utils.getImgWidth(new File(path))*1d;
                imgHeight = Utils.getImgHeight(new File(path))*1d;
            }else if(imgHeight2>200 && imgHeight2<1000){
                imgWidth = Utils.getImgWidth(new File(path))*0.3;
                imgHeight = Utils.getImgHeight(new File(path))*0.3;
            }else{
                imgWidth = Utils.getImgWidth(new File(path))*0.2;
                imgHeight = Utils.getImgHeight(new File(path))*0.2;
            }
            djzhgdImgs.setImgWidth(imgWidth);
            djzhgdImgs.setImgHeight(imgHeight);
            picture.add(djzhgdImgs);
        }



        wordMap.put("picture", picture);
        return wordMap;
    }

    /**
     * java生产word方法
     * @param wordMap      生成word的内容
     * @param templatePath word模板ftl的路径
     * @param templateName word模板ftl的名称
     * @param newWord      生成新word文件的路径+名称
     */
    public  void createWord(Map wordMap, String templatePath, String templateName, String newWord) {
        try {
            // Configuration 用于读取ftl文件
            Configuration configuration = new Configuration(new Version("2.3.23"));
            // 设置编码
            configuration.setDefaultEncoding("UTF-8");
            // ftl模板文件
            configuration.setDirectoryForTemplateLoading(new File(templatePath));

            // 获取模板
            Template template = configuration.getTemplate(templateName, "UTF-8");
            // 输出文件(路径 + 名称)
            File outFile = new File(newWord);
            // 如果输出目标文件夹不存在，则创建
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            //将模板和数据模型合并生成文件
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
            //生成文件
            template.process(wordMap, out);
            //关闭流
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载生成的word文档
     * @param request
     * @param response
     * @param zipFileName   压缩包名称
     * @param fileName      文件名称
     * @param newWord       文件路径
     */
    public static void dowloadWord(HttpServletRequest request, HttpServletResponse response, String zipFileName, String fileName, String newWord) {
        // 1 响应头的设置
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        // 返回客户端浏览器的版本号、类型
        String agent = request.getHeader("USER-AGENT");
        try {
            //针对IE或者以IE为内核的浏览器：
            if (agent.contains("MSIE")||agent.contains("Trident")) {
                zipFileName = java.net.URLEncoder.encode(zipFileName, "UTF-8");
            } else {
                // 非IE浏览器的处理：
                zipFileName = new String(zipFileName.getBytes("UTF-8"),"ISO-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment;fileName=\"" + zipFileName + "\"");
        // 设置压缩流：直接写入response，实现边压缩边下载
        ZipOutputStream zipos = null;
        try {
            zipos = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()));
            zipos.setMethod(ZipOutputStream.DEFLATED); //设置压缩方法
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 循环将文件写入压缩流
        DataOutputStream os = null;
        // 遍历文件信息（主要获取文件名/文件路径等）
        //文件路径
        File file = new File(newWord);
        if (file.exists()) {
            try {
                //添加ZipEntry，并ZipEntry中写入文件流
                zipos.putNextEntry(new ZipEntry(fileName));
                os = new DataOutputStream(zipos);
                InputStream is = new FileInputStream(file);
                byte[] b = new byte[100];
                int length = 0;
                while((length = is.read(b))!= -1){
                    os.write(b, 0, length);
                }
                is.close();
                zipos.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //关闭流
        try {
            if(os != null){
                os.flush();
                os.close();
            }
            zipos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片进行base64编码
     * @param imgFile
     * @return
     */
    private static String getImageStr(String imgFile) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Base64 encoder = new Base64();
        return encoder.encodeAsString(data);
    }
}
