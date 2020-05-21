package com.djzhgd.module.material.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djzhgd.common.utils.DateUtils;
import com.djzhgd.common.utils.SecurityUtils;
import com.djzhgd.module.constants.MaterialConstant;
import com.djzhgd.module.enums.DictionaryEnum;
import com.djzhgd.module.material.domain.ZhgdMaterialDetection;
import com.djzhgd.module.material.domain.ZhgdMaterialInstock;
import com.djzhgd.module.material.domain.ZhgdMaterialStockRecord;
import com.djzhgd.module.material.domain.vo.ZhgdMaterialInstockVo;
import com.djzhgd.module.material.mapper.ZhgdMaterialDetectionMapper;
import com.djzhgd.module.material.mapper.ZhgdMaterialInstockMapper;
import com.djzhgd.module.material.service.ZhgdMaterialDetectionService;
import com.djzhgd.module.material.service.ZhgdMaterialInstockService;
import com.djzhgd.module.material.service.ZhgdMaterialStockRecordService;
import com.djzhgd.module.result.PageResult;
import com.djzhgd.module.result.PageResultSupple;
import com.djzhgd.module.result.Result;
import com.djzhgd.module.result.ResultEnum;
import com.djzhgd.module.utils.HttpClientUtil;
import com.djzhgd.project.system.domain.SysDept;
import com.djzhgd.project.system.domain.SysUser;
import com.djzhgd.project.system.domain.ZhgdDict;
import com.djzhgd.project.system.mapper.SysDeptMapper;
import com.djzhgd.project.system.mapper.SysDictDataMapper;
import com.djzhgd.project.system.mapper.ZhgdDictMapper;
import com.djzhgd.project.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.DecimalFormat;
import java.util.*;


/**
 * @Author: lgc
 * @Date: 2020/5/18 14:05
 */
@Slf4j
@Service
public class ZhgdMaterialInstockServiceImpl extends ServiceImpl<ZhgdMaterialInstockMapper, ZhgdMaterialInstock> implements ZhgdMaterialInstockService {

    @Autowired
    private ZhgdMaterialInstockMapper zhgdMaterialInstockMapper;
    @Autowired
    private ZhgdMaterialStockRecordService zhgdMaterialStockRecordService;
    @Autowired
    private ZhgdMaterialDetectionService zhgdMaterialDetectionService;
    @Autowired
    private ZhgdDictMapper zhgdDictMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private ZhgdMaterialDetectionMapper zhgdMaterialDetectionMapper;

    @Transactional
    @Override
    public Result saveInStockData(ZhgdMaterialInstockVo materialInstockVo, Long deptId) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        return inStockSave(materialInstockVo, "1", user, deptId);
    }

    @Override
    public Result queryInStockById(String inParam) {
        Result result = new Result();
        ZhgdMaterialInstock materialInstock = null;
        try {
            JSONObject object = JSONObject.parseObject(inParam);
            String id = (String) object.get("id");
            materialInstock = zhgdMaterialInstockMapper.getById(Integer.valueOf(id));
            materialInstock.setMaterialNameDesc(DictionaryEnum.getNameByValue(materialInstock.getMaterialName()));
            if (StringUtils.isNotEmpty(materialInstock.getStandard())) {
                materialInstock.setModelDesc(materialInstock.getStandard() + DictionaryEnum.getNameByValue(materialInstock.getModel()));
            } else {
                materialInstock.setModelDesc(DictionaryEnum.getNameByValue(materialInstock.getModel()));
            }
        } catch (Exception e) {
            log.error("queryInStockById exception:" + e.getMessage());
            result.setCode(ResultEnum.RESULT_INSERT_ERROR.getCode());
            result.setMsg(ResultEnum.RESULT_INSERT_ERROR.getMsg() + e.toString());
            return result;
        }
        result.setCode(ResultEnum.RESULT_OK.getCode());
        result.setMsg(ResultEnum.RESULT_OK.getMsg());
        result.setData(materialInstock);
        return result;
    }

    @Override
    public Map<String, Object> detail(Integer id) {
        Map<String, Object> resultMap = new HashMap<>();

        ZhgdMaterialInstock inStockData = zhgdMaterialInstockMapper.getById(id);
        inStockData.setMaterialNameDesc(DictionaryEnum.getNameByValue(inStockData.getMaterialName()));
        if (StringUtils.isNotEmpty(inStockData.getStandard())) {
            inStockData.setModelDesc(inStockData.getStandard() + DictionaryEnum.getNameByValue(inStockData.getModel()));
        } else {
            inStockData.setModelDesc(DictionaryEnum.getNameByValue(inStockData.getModel()));
        }
        inStockData.setMaterialTypeDesc(DictionaryEnum.getNameByValue(inStockData.getMaterialType()));
        // 入库时间格式化
        if (inStockData.getInstockTime().indexOf(".") > 0) {
            inStockData.setInstockTime(inStockData.getInstockTime().substring(0, inStockData.getInstockTime().indexOf(".")));
        }
        Integer checkNumber = inStockData.getCheckNum();
        resultMap.put("inStockData", inStockData);
        if (!StringUtils.isEmpty(inStockData.getInstockWord())) {
            resultMap.put("inStockWord", inStockData.getInstockWord());
        }
        String detectionComment = "";
        if (DictionaryEnum.REBAR.getValue().equals(inStockData.getMaterialName())) {
            detectionComment = "1.重量偏差；2.屈服强度；3.抗拉强度；4.伸长率；5.弯曲";
        }
        if (DictionaryEnum.CEMENT.getValue().equals(inStockData.getMaterialName())) {
            detectionComment = "1.密度；2.细度/比表面积；3.标准稠度用水量；4.凝结时间；5.安定性；6.胶砂强度；7.胶砂流动度；";
        }
        if (DictionaryEnum.GRAVEL.getValue().equals(inStockData.getMaterialName())) {
            detectionComment = "1.筛分；2.密度；3.吸水率；4.含泥量；5.泥块含量；6.针片状颗粒含量；7.压碎值；8.含水率；9.坚固性；10.软弱颗粒含量；";
        }
        if (DictionaryEnum.YELLOW_SAND.getValue().equals(inStockData.getMaterialName())) {
            detectionComment = "1.筛分；2.密度；3.含泥量；4.泥块含量；5.亚甲蓝值MBV；6.含水率；7.压碎值；8.坚固性；";
        }
        if (DictionaryEnum.ADMIXTURE.getValue().equals(inStockData.getMaterialName())) {
            detectionComment = "1.PH值；2.氯离子含量；3.总碱量；4.含固量；5.含水率；6.密度；7.细度；8.硫酸钠含量；9.减水率；10.泌水率比；11.抗压强度比；12.含气量；13.凝结时间差；14.收缩率比；";
        }
        if (DictionaryEnum.FLY_ASH.getValue().equals(inStockData.getMaterialName())) {
            detectionComment = "";
        }
        if (DictionaryEnum.MJJP.getValue().equals(inStockData.getMaterialName())) {
            detectionComment = "1.锚固效率系数；2.总应变；3.洛氏硬度；";
        }
        if (DictionaryEnum.GJX.getValue().equals(inStockData.getMaterialName())) {
            detectionComment = "1.外观尺寸；2.每米质量；3.整根钢绞线最大力；4.规定非比例延伸力；5.最大力总伸长率；6.弹性模量；7.应力松弛性能；";
        }
        if (DictionaryEnum.SH.getValue().equals(inStockData.getMaterialName())) {
            detectionComment = "";
        }
        if (DictionaryEnum.XJZZ.getValue().equals(inStockData.getMaterialName())) {
            detectionComment = "1.抗压弹性质量；2.抗剪弹性模量；3.抗剪粘结性能；4.抗剪老化；5.四氟板与不锈钢板表面摩擦系数；6.容许转角；7.极限抗压强度；";
        }
        if (DictionaryEnum.SSZZ.getValue().equals(inStockData.getMaterialName())) {
            detectionComment = "1.外形尺寸；2.外观质量；3.组装质量；4.防水性能；";
        }
        if (DictionaryEnum.BWG.getValue().equals(inStockData.getMaterialName())) {
            detectionComment = "1.外观质量；2.外观尺寸；3.环刚度；4.局部横向载荷；5.柔韧性；6.抗冲击性；7.径向刚度；8.抗渗漏；";
        }
        if (DictionaryEnum.GJWP.getValue().equals(inStockData.getMaterialName())) {
            detectionComment = "";
        }
        resultMap.put("detectionComment", detectionComment);
        resultMap.put("checkNumber", checkNumber);

        // 获取deptName
        SysDept sysDept = sysDeptMapper.selectDeptById(inStockData.getDeptId());
        resultMap.put("bidsDesc", sysDept.getDeptName());
        return resultMap;
    }

    private Result inStockSave(ZhgdMaterialInstockVo materialInstockVo, String type, SysUser user, Long deptId) {
        Result result = new Result();
        try {
            String materialType = materialInstockVo.getEntity().getMaterialType();
            String materialName = materialInstockVo.getEntity().getMaterialName();
            String bidsName = materialInstockVo.getEntity().getBidsName();
            String batch = materialInstockVo.getEntity().getBatch();
            Integer tenantId = materialInstockVo.getEntity().getTenantId();
            //Long deptId = materialInstockVo.getEntity().getDeptId();

            if ("".equals(batch)) {
                batch = String.valueOf(System.currentTimeMillis());
            }

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("batch", materialInstockVo.getEntity().getBatch());
            paramMap.put("tenantId", tenantId);
            paramMap.put("deptId", deptId);

            List<ZhgdMaterialInstock> queryInStockByBatch = zhgdMaterialInstockMapper.queryInStockByBatchAndProId(paramMap);
            if (queryInStockByBatch.size() > 0) {
                result.setCode(ResultEnum.RESULT_INSERT_ERROR.getCode());
                result.setMsg(ResultEnum.RESULT_INSERT_ERROR.getMsg() + "，该批次号已存在！");
                return result;
            }

            // 保存物料入库清单
            materialInstockVo.getEntity().setTaskNode(1);
            //materialInstockVo.getEntity().setCreateDatetime(DateUtils.parseDate(DateUtils.dateTimeForMate(),DateUtils.YYYY_MM_DD_HH_MM_SS));
            materialInstockVo.getEntity().setDeptId(deptId);
            if (null != user) {
                materialInstockVo.getEntity().setCreateUserid(user.getUserId().intValue());
                materialInstockVo.getEntity().setCreateUsername(user.getUserName());
            }

            Map<String, Object> unitInfo = changeDataToDUN(materialInstockVo.getEntity());
            double stockNum = MapUtils.getDoubleValue(unitInfo, "stockNum", 0);
            // 计算检验次数（若是千克和立方米，需转成以吨位单位）
            int checkNumber = getCheckNum(materialInstockVo.getEntity(), stockNum);
            materialInstockVo.getEntity().setCheckNum(checkNumber);
            materialInstockVo.getEntity().setDisabled(0);
            zhgdMaterialInstockMapper.save(materialInstockVo.getEntity());

            // 重新计算库存
            List<ZhgdMaterialStockRecord> stockDatas = zhgdMaterialStockRecordService.queryByTypeAndNameProId(materialType, materialName, bidsName, tenantId, deptId);
            if (CollectionUtils.isEmpty(stockDatas)) {
                // 第一次入库
                ZhgdMaterialStockRecord zhgdMaterialStockRecord = new ZhgdMaterialStockRecord();
                //zhgdMaterialStockRecord.setCreateDatetime(DateUtils.parseDate(DateUtils.dateTimeForMate(),DateUtils.YYYY_MM_DD_HH_MM_SS));
                if (null != user) {
                    zhgdMaterialStockRecord.setCreateUserid(user.getUserId().intValue());
                    zhgdMaterialStockRecord.setCreateUsername(user.getUserName());
                }
                zhgdMaterialStockRecord.setModel(materialInstockVo.getEntity().getModel());
                zhgdMaterialStockRecord.setMaterialName(materialInstockVo.getEntity().getMaterialName());
                zhgdMaterialStockRecord.setMaterialType(materialInstockVo.getEntity().getMaterialType());
                zhgdMaterialStockRecord.setNumber(stockNum);
                zhgdMaterialStockRecord.setUnit(MapUtils.getString(unitInfo, "unit", ""));
                zhgdMaterialStockRecord.setBidsName(materialInstockVo.getEntity().getBidsName());
                zhgdMaterialStockRecord.setDisabled(0);
                zhgdMaterialStockRecord.setDeptId(deptId);
                if (StringUtils.isNotEmpty(materialInstockVo.getEntity().getStandard())) {
                    zhgdMaterialStockRecord.setModel(materialInstockVo.getEntity().getStandard() + DictionaryEnum.getNameByValue(materialInstockVo.getEntity().getModel()));
                } else {
                    zhgdMaterialStockRecord.setModel(DictionaryEnum.getNameByValue(materialInstockVo.getEntity().getModel()));
                }
                zhgdMaterialStockRecordService.save(zhgdMaterialStockRecord);
            } else {
                // 在原来基础上增加
                ZhgdMaterialStockRecord zhgdMaterialStockRecord = stockDatas.get(0);
                zhgdMaterialStockRecord.setNumber(zhgdMaterialStockRecord.getNumber() + stockNum);
                //zhgdMaterialStockRecord.setUpdateDatetime(DateUtils.parseDate(DateUtils.dateTimeForMate(),DateUtils.YYYY_MM_DD_HH_MM_SS) );
                if (null != user) {
                    zhgdMaterialStockRecord.setUpdateUserid(user.getUserId().intValue());
                    zhgdMaterialStockRecord.setUpdateUsername(user.getUserName());
                }
                zhgdMaterialStockRecordService.update(zhgdMaterialStockRecord);
            }


            // 发送委托单
            if (DictionaryEnum.REBAR.getValue().equals(materialName) || DictionaryEnum.CEMENT.getValue().equals(materialName)) {
                sendOrderForm(checkNumber, materialName, materialInstockVo.getEntity());
            }

            // 保存数据到代办表
            //sendPushMessage(stockNum, materialName, materialInstockVo.getEntity());
        } catch (Exception e) {
            log.error("inStockSave exception:" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setCode(ResultEnum.RESULT_INSERT_ERROR.getCode());
            result.setMsg(ResultEnum.RESULT_INSERT_ERROR.getMsg() + e.toString());
            return result;
        }
        result.setCode(ResultEnum.RESULT_INSERT_OK.getCode());
        result.setMsg(ResultEnum.RESULT_INSERT_OK.getMsg());
        return result;
    }


    /**
     * 单位转换
     *
     * @param zhgdMaterialInstock
     * @return
     */

    @Override
    public Map<String, Object> changeDataToDUN(ZhgdMaterialInstock zhgdMaterialInstock) {
        // TODO 若有其他单位需要转化，直接继续增加即可
        Map<String, Object> ret = new HashMap<>();
        // 如果是千克需要转化成吨，黄沙和碎石有立方米单位，需转化成吨
        DecimalFormat df = new DecimalFormat("0.00");
        double stockNumTemp = zhgdMaterialInstock.getNumber();
        double stockNum = 0;
        String unit = zhgdMaterialInstock.getUnit();
        if ("吨".equals(zhgdMaterialInstock.getUnit())) {
            stockNum = stockNumTemp;
            unit = "吨";
        } else if ("千克".equals(zhgdMaterialInstock.getUnit())) {
            stockNum = Double.parseDouble(df.format((double) stockNumTemp / 1000));
            unit = "吨";
        } else if ("立方米".equals(zhgdMaterialInstock.getUnit())) {
            // 碎石
            if (DictionaryEnum.GRAVEL.getValue().equals(zhgdMaterialInstock.getMaterialName())) {
                stockNum = Double.parseDouble(df.format((double) stockNumTemp * 1.35));
            } else if (DictionaryEnum.YELLOW_SAND.getValue().equals(zhgdMaterialInstock.getMaterialName())) {
                stockNum = Double.parseDouble(df.format((double) stockNumTemp * 1.35));
            } else {
                stockNum = stockNumTemp;
            }
            unit = "吨";
        } else {
            stockNum = stockNumTemp;
        }
        ret.put("stockNum", stockNum);
        ret.put("unit", unit);
        return ret;
    }

    @Override
    public PageResult<ZhgdMaterialInstock> getlist(PageResult<ZhgdMaterialInstock> pageResult, Long deptId) {

        IPage<ZhgdMaterialInstock> materialInstockIPage = null;
        try {
            ZhgdMaterialInstock zhgdMaterialInstock = pageResult.getExample();
            zhgdMaterialInstock.setDeptId(deptId);
            // 查询条件封装
            LambdaQueryWrapper<ZhgdMaterialInstock> queryWrapper = Wrappers.lambdaQuery();
            // 物料名称
            if (StringUtils.isNotBlank(zhgdMaterialInstock.getMaterialName())) {
                queryWrapper.eq(ZhgdMaterialInstock::getMaterialName, zhgdMaterialInstock.getMaterialName());
            }
            queryWrapper.eq(ZhgdMaterialInstock::getDeptId, zhgdMaterialInstock.getDeptId());

            // 根据id倒序
            queryWrapper.orderByDesc(ZhgdMaterialInstock::getId);
            // 设置当前页和页容量
            Page<ZhgdMaterialInstock> page = new Page<>(pageResult.getPage(), pageResult.getLimit());
            materialInstockIPage = zhgdMaterialInstockMapper.selectPage(page, queryWrapper);

            List<ZhgdMaterialInstock> xyInStockDataListPush = new ArrayList<ZhgdMaterialInstock>();

            //获取没有通过的批次类型和所在流程ID
            List<ZhgdMaterialDetection> xyDRNoPass = zhgdMaterialDetectionService.queryGroupByNameAndResultAndBatchAndDTNoPass(deptId);

            for (ZhgdMaterialInstock inStockData : materialInstockIPage.getRecords()) {
                if (inStockData.getInstockTime().indexOf(".") > 0) {
                    inStockData.setInstockTime(inStockData.getInstockTime().substring(0, inStockData.getInstockTime().indexOf(".")));
                }
                // 设置规格型号和物料名称
                if (StringUtils.isNotEmpty(inStockData.getStandard())) {
                    inStockData.setModelDesc(inStockData.getStandard() + DictionaryEnum.getNameByValue(inStockData.getModel()));
                } else {
                    inStockData.setModelDesc(DictionaryEnum.getNameByValue(inStockData.getModel()));
                }
                inStockData.setMaterialNameDesc(DictionaryEnum.getNameByValue(inStockData.getMaterialName()));
                inStockData.setMaterialTypeDesc(DictionaryEnum.getNameByValue(inStockData.getMaterialType()));
                inStockData.setTaskNodeStatus(2);
                for (ZhgdMaterialDetection detectionResult : xyDRNoPass) {
                    if (inStockData.getMaterialName().equals(detectionResult.getMaterialName())
                            && inStockData.getBatch().equals(detectionResult.getInstockBatch())
                            && inStockData.getTaskNode().toString().equals(detectionResult.getDetectionType())) {
                        inStockData.setTaskNodeStatus(Integer.parseInt(detectionResult.getCheckResult()));
                    }

                }
                xyInStockDataListPush.add(inStockData);
            }
        } catch (Exception e) {

            log.error("getlist exception:" + e.getMessage());
            pageResult.setCode(ResultEnum.RESULT_ERROR.getCode());
            pageResult.setMsg(ResultEnum.RESULT_ERROR.getMsg() + e.toString());
            return pageResult;
        }
        pageResult.setData(materialInstockIPage.getRecords());
        pageResult.setCode(ResultEnum.RESULT_OK.getCode());
        pageResult.setMsg(ResultEnum.RESULT_OK.getMsg());
        pageResult.setCount(materialInstockIPage.getTotal());
        return pageResult;
    }


    private int getCheckNum(ZhgdMaterialInstock zhgdMaterialInstock, double stockNum) {
        // TODO MaterialConstant 具体以多少数量为单位检验一次根据实际情况配置，初始设置为100
        int checkNumber = 1;
        String materialName = zhgdMaterialInstock.getMaterialName();
        String materialType = zhgdMaterialInstock.getMaterialType();
        String model = zhgdMaterialInstock.getModel();
        // 钢筋
        if (DictionaryEnum.REBAR.getValue().equals(materialName)) {
            checkNumber = (int) Math.ceil(stockNum / MaterialConstant.REBAR_CHECKNUM);
        }
        // 水泥
        if (DictionaryEnum.CEMENT.getValue().equals(materialName)) {
            // 根据类型不同检验次数不同
            // 散装水泥
            if (DictionaryEnum.SZ_CEMENT.getValue().equals(materialType)) {
                checkNumber = (int) Math.ceil(stockNum / MaterialConstant.CEMENT_SZ_CHECKNUM);
            }
            // 袋装水泥
            if (DictionaryEnum.DZ_CEMENT.getValue().equals(materialType)) {
                checkNumber = (int) Math.ceil(stockNum / MaterialConstant.CEMENT_DZ_CHECKNUM);
            }
        }
        // 碎石
        if (DictionaryEnum.GRAVEL.getValue().equals(materialName)) {
            checkNumber = (int) Math.ceil(stockNum / MaterialConstant.SMALL_GRAVEL_CHECKNUM);
//			// 大石子
//			if (DictionaryEnum.BIG_GRAVEL.getValue().equals(materialType)) {
//				checkNumber = (int) Math.ceil(stockNum/MaterialConstant.BIG_GRAVEL_CHECKNUM);
//			}
//			// 小石子
//			if (DictionaryEnum.SMALL_GRAVEL.getValue().equals(materialType)) {
//				checkNumber = (int) Math.ceil(stockNum/MaterialConstant.SMALL_GRAVEL_CHECKNUM);
//			}
        }
        // 黄沙
        if (DictionaryEnum.YELLOW_SAND.getValue().equals(materialName)) {
            // 细沙
            if (DictionaryEnum.I_SAND.getValue().equals(model)) {
                checkNumber = (int) Math.ceil(stockNum / MaterialConstant.I_SAND_CHECKNUM);
            }
            // 中沙
            if (DictionaryEnum.II_SAND.getValue().equals(model)) {
                checkNumber = (int) Math.ceil(stockNum / MaterialConstant.II_SAND_CHECKNUM);
            }
            // 粗沙
            if (DictionaryEnum.III_SAND.getValue().equals(model)) {
                checkNumber = (int) Math.ceil(stockNum / MaterialConstant.III_SAND_CHECKNUM);
            }
        }
        // 外加剂
        if (DictionaryEnum.ADMIXTURE.getValue().equals(materialName)) {
            checkNumber = (int) Math.ceil(stockNum / MaterialConstant.ADMIXTURE_CHECKNUM);
        }
        // 粉煤灰
        if (DictionaryEnum.FLY_ASH.getValue().equals(materialName)) {
            // I
            if (DictionaryEnum.I_FLY_ASH.getValue().equals(model)) {
                checkNumber = (int) Math.ceil(stockNum / MaterialConstant.I_FLY_ASH_CHECKNUM);
            }
            // II
            if (DictionaryEnum.II_FLY_ASH.getValue().equals(model)) {
                checkNumber = (int) Math.ceil(stockNum / MaterialConstant.II_FLY_ASH_CHECKNUM);
            }
            // III
            if (DictionaryEnum.III_FLY_ASH.getValue().equals(model)) {
                checkNumber = (int) Math.ceil(stockNum / MaterialConstant.III_FLY_ASH_CHECKNUM);
            }
        }
        // 锚具夹片
        if (DictionaryEnum.MJJP.getValue().equals(materialName)) {
            checkNumber = (int) Math.ceil(stockNum / MaterialConstant.MJJP_CHECKNUM);
        }
        // 钢绞线
        if (DictionaryEnum.GJX.getValue().equals(materialName)) {
            checkNumber = (int) Math.ceil(stockNum / MaterialConstant.GJX_CHECKNUM);
        }
        // 波纹管
        if (DictionaryEnum.BWG.getValue().equals(materialName)) {
            if (DictionaryEnum.LX_SLBWG.getValue().equals(model)) {
                checkNumber = (int) Math.ceil(stockNum / MaterialConstant.SLBWG_CHECKNUM);
            }
            if (DictionaryEnum.LX_JSBWG.getValue().equals(model)) {
                checkNumber = (int) Math.ceil(stockNum / MaterialConstant.JSBWG_CHECKNUM);
            }
        }

        return checkNumber;
    }


    /**
     * 委托单
     *
     * @param checkNumber
     * @param materialName
     * @param zhgdMaterialInstock
     */
    private void sendOrderForm(int checkNumber, String materialName, ZhgdMaterialInstock zhgdMaterialInstock) {
        // TODO 目前钢筋水泥需发送委托单，有增加可自行配置
        HashMap<String, Object> map = new HashMap<>(16);

        ZhgdDict zhgdDict = zhgdDictMapper.getUrlByDeptid(zhgdMaterialInstock.getDeptId(), MaterialConstant.TRIAL_PROJECTID_GROUPID);
        map.put("proId", zhgdDict.getDataValue());
        map.put("num", checkNumber);

        ZhgdDict zhgdDict2 = zhgdDictMapper.getUrlByDeptid(zhgdMaterialInstock.getDeptId(), MaterialConstant.TRIAL_ROOMID_GROUPID);

        // 钢筋
        if (DictionaryEnum.REBAR.getValue().equals(materialName)) {
            // TODO 具体参数按实际情况进行配置
            map.put("trialId", 8);
            map.put("trialroomId", zhgdDict2.getDataValue());
        }

        // 水泥
        if (DictionaryEnum.CEMENT.getValue().equals(materialName)) {
            map.put("trialId", 6);
            map.put("trialroomId", zhgdDict2.getDataValue());
        }

        String str = JSONObject.toJSONString(map).toString();
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("data", str);
        try {
            String response = HttpClientUtil.postMethod(MaterialConstant.WTD_URL + "/provide/batchOrder", map2);

            JSONObject object = JSONObject.parseObject(response);

            // 委托单列表
            JSONArray wtdList = (JSONArray) object.get("data");

            for (Object wtd : wtdList) {
                ZhgdMaterialDetection xdr = new ZhgdMaterialDetection();
                xdr.setDetectionNo(String.valueOf(wtd));
                xdr.setInstockBatch(zhgdMaterialInstock.getBatch());
                xdr.setMaterialName(materialName);
                xdr.setCreateDatetime(new Date());
                xdr.setCheckTime(MaterialConstant.FORMAT_YMDHMS.format(new Date()));
                xdr.setDetectionType("1");
                xdr.setCheckResult("2");
                xdr.setDisabled(0);
                xdr.setCreateUserid(zhgdMaterialInstock.getCreateUserid());
                xdr.setCreateUsername(zhgdMaterialInstock.getCreateUsername());
                xdr.setDeptId(zhgdMaterialInstock.getDeptId());
                if (StringUtils.isNotEmpty(zhgdMaterialInstock.getStandard())) {
                    xdr.setModel(zhgdMaterialInstock.getStandard() + DictionaryEnum.getNameByValue(zhgdMaterialInstock.getModel()));
                } else {
                    xdr.setModel(DictionaryEnum.getNameByValue(zhgdMaterialInstock.getModel()));
                }
                zhgdMaterialDetectionService.save(xdr);
            }

        } catch (Exception e) {
            e.printStackTrace();
            zhgdMaterialInstock.setRemark("1");
            zhgdMaterialInstockMapper.update(zhgdMaterialInstock);
        }
    }


    @Override
    public PageResultSupple<Map<String, Object>> queryDetection(PageResultSupple<Map<String, Object>> pageResult, Long deptId) {

        Map<String,Object> responseMap = new HashMap<>(16);


        IPage<ZhgdMaterialDetection> zhgdMaterialDetectionIPage = null;

        Map<String, Object> pageResultExample = pageResult.getExample();
        String detectionType = MapUtils.getString(pageResultExample, "detectionType", "");
        String instockBatch = MapUtils.getString(pageResultExample, "instockBatch", "");
        Integer id = MapUtils.getInteger(pageResultExample, "id");

        // 查询条件封装
        LambdaQueryWrapper<ZhgdMaterialDetection> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.eq(ZhgdMaterialDetection::getDetectionType, detectionType);
        queryWrapper.eq(ZhgdMaterialDetection::getInstockBatch, instockBatch);
        queryWrapper.eq(ZhgdMaterialDetection::getDeptId, deptId);

        // 根据id倒序
        queryWrapper.orderByDesc(ZhgdMaterialDetection::getId);
        // 设置当前页和页容量
        Page<ZhgdMaterialDetection> page = new Page<>(pageResult.getPage(), pageResult.getLimit());
        zhgdMaterialDetectionIPage = zhgdMaterialDetectionMapper.selectPage(page, queryWrapper);
        // 物料描述
        for (ZhgdMaterialDetection resultMap : zhgdMaterialDetectionIPage.getRecords()) {
            resultMap.setMaterialNameDesc(DictionaryEnum.getNameByValue(resultMap.getMaterialName()));
        }
        Integer checkNumer = 0;
        ZhgdMaterialInstock inStockData = zhgdMaterialInstockMapper
                .getById(id);
        int needCheck = inStockData.getCheckNum();


        /**
         * 监理需要检测的次数 次数是施工检测的次数 的30% 就是说 如果施工是 1次 那么监理就是一次 如果施工是3次 那么监理也是1次
         * 如果施工时4次 那么监理是2次 以此类推 获取监理检测的次数
         */
        Map<String, Object> paraMap = new HashMap<>(16);
        paraMap.put("instockBatch", instockBatch);
        paraMap.put("detectionType", detectionType);
        List<ZhgdMaterialDetection> zhgdMaterialDetectionList = zhgdMaterialDetectionService.queryAll(paraMap);

        /**
         * 这个resultList1是监理的检查结果 遍历这个检查结果 如果检查结果中有 0或者2的话就不自动流转到中心试验室
         */
        boolean Supervision = true;

        for (ZhgdMaterialDetection xs : zhgdMaterialDetectionList) {
            if ("2".equals(xs.getCheckResult())) {
                Supervision = false;
            }
            if ("0".equals(xs.getCheckResult())) {
                Supervision = false;
            }
        }
        /**
         * 如果监理的次数达到了施工的30% 那么自动流转到中心试验室 如果物料是水泥或者钢筋的话就需要发送委托单到中心试验室
         */
        int getSupervisionNum = getSupervisionNum(needCheck);
        if (zhgdMaterialDetectionList.size() == getSupervisionNum && Supervision) {
            reverse(inStockData);
        }

        if (zhgdMaterialDetectionIPage.getRecords().size() > 0) {
            Map<String, Object> paramMap = new HashMap<>(16);
            paramMap.put("batch", inStockData.getBatch());
            paramMap.put("deptId", inStockData.getDeptId());
            List<ZhgdMaterialInstock> inStockByBatchList = zhgdMaterialInstockMapper
                    .queryInStockByBatchAndProId(paramMap);

            if (inStockByBatchList.size() > 0) {
                ZhgdMaterialInstock inStockData1 = inStockByBatchList.get(0);
                checkNumer = inStockData1.getCheckNum();
            }
        }

        /**
         * 用于判断物料是否是钢筋和水泥或者需要发委托单和是否有检测不合格的flag
         */
        boolean flag = true;
        // 获取改批次物料的检查全部次数，如果有一条不合格，checkr_result=0的话 那么就直接停止 所有的按钮都是隐藏
        for (ZhgdMaterialDetection detectionResult : zhgdMaterialDetectionIPage.getRecords()) {
            if (("0").equals(detectionResult.getCheckResult())) {
                flag = false;
            }
        }
        /**
         * //只有在物料在已检测的合格并且不是钢筋和水泥的的情况下 才会显示哪些添加检测按钮逻辑 并且发送委托单的按钮是不会显示的
         * 只要在这个if语句中的buttonfive都是不显示的
         */
        if (flag) {

            ZhgdDict zhgdDictSg = zhgdDictMapper.getUrlByDeptid(deptId, MaterialConstant.SG_DEPART_CODE_GROUPID);
            ZhgdDict zhgdDictJl = zhgdDictMapper.getUrlByDeptid(deptId, MaterialConstant.JL_DEPART_CODE_GROUPID);
            ZhgdDict zhgdDictJc = zhgdDictMapper.getUrlByDeptid(deptId, MaterialConstant.JC_DEPART_CODE_GROUPID);
            SysDept sysDept = sysDeptMapper.selectDeptById(deptId);

            if (sysDept.getDeptCode().equals(zhgdDictSg.getDataValue())) {
                // 如果是钢筋水泥，不展示按钮
                if (DictionaryEnum.REBAR.getValue().equals(inStockData.getMaterialName()) || DictionaryEnum.CEMENT.getValue().equals(inStockData.getMaterialName())) {
                    responseMap.put("buttonone", "0");
                    responseMap.put("buttontwo", "0");
                    responseMap.put("buttonfour", "0");
                    //
                } else {
                    int node = inStockData.getTaskNode();
                    if (node == 1) {
                        responseMap.put("buttonone", "1");
                    } else {
                        responseMap.put("buttonone", "0");
                    }
                    responseMap.put("buttontwo", "0");
                    responseMap.put("buttonfour", "0");
//				response.put("buttonfive", "0");
                    responseMap.put("role", "1");
                }
            } else if (sysDept.getDeptCode().equals(zhgdDictJl.getDataValue())) {
                // 到监理的流程时候 。需要先判断改物料的节点 如果改物料的节点不是2的话 就不展示新增和流转
                if (DictionaryEnum.REBAR.getValue().equals(inStockData.getMaterialName()) || DictionaryEnum.CEMENT.getValue().equals(inStockData.getMaterialName())) {
                    responseMap.put("buttonone", "0");
                    responseMap.put("buttontwo", "0");
                    responseMap.put("buttonfour", "0");
                }else{
                    int node = inStockData.getTaskNode();
                    if (node == 2) {
                        responseMap.put("buttonone", "0");
                        responseMap.put("buttontwo", "1");
                        responseMap.put("buttonfour", "0");
                    } else {
                        responseMap.put("buttonone", "0");
                        responseMap.put("buttontwo", "0");
                        responseMap.put("buttonfour", "0");
                    }
//					response.put("buttonfive", "0");
                    responseMap.put("role", "2");
                }


            } else if (sysDept.getDeptCode().equals(zhgdDictJc.getDataValue())) {
                int node = inStockData.getTaskNode();
                if (node == 3) {
                    responseMap.put("buttonone", "0");
                    responseMap.put("buttontwo", "0");
                    responseMap.put("buttonfour", "1");

                } else {
                    responseMap.put("buttonone", "0");
                    responseMap.put("buttontwo", "0");
                    responseMap.put("buttonfour", "0");
                }
//				response.put("buttonfive", "0");
                responseMap.put("role", "3");
            } else {
                // 普通用户 任何按钮都不显示
                responseMap.put("buttonone", "0");
                responseMap.put("buttontwo", "0");
                responseMap.put("buttonfour", "0");
//				response.put("buttonfive", "0");
            }

        } else {
            responseMap.put("buttonone", "0");
            responseMap.put("buttontwo", "0");
            responseMap.put("buttonfour", "0");
        }
        responseMap.put("data",zhgdMaterialDetectionIPage.getRecords());
        pageResult.setData(responseMap);
        pageResult.setCode(ResultEnum.RESULT_OK.getCode());
        pageResult.setMsg(ResultEnum.RESULT_OK.getMsg());
        pageResult.setCount(zhgdMaterialDetectionIPage.getTotal());
        return pageResult;

    }

    /**
     * 按需求 抽检概率改为20%
     * 2020-04-07 监理抽检率 改为100% ；
     * @param num
     * @return
     */
    public  static int getSupervisionNum(int num){
        //这种写法便于替换，以及排除用除法不精确的问题
        // double a = num * 0.2 ;
        double a = num * 1.0;
        int b = new Double(a).intValue();

        int i = String.valueOf(a).indexOf(".");

        if(a % Integer.parseInt(String.valueOf(a).substring(0, i)) == 0){
            return b;
        } else {
            return b+1;
        }

    }

    /**
     * TODO 推送需要补充
     * @param inStockData
     */

    public void reverse(ZhgdMaterialInstock inStockData){

        if (inStockData.getTaskNode() == 3) {
            return;
        }
       /* ZhgdPushmessage pm = new ZhgdPushmessage();
        inStockData.setTaskNode(3);
        zhgdMaterialInstockDao.update(inStockData);*/
    }

    @Override
    public List<ZhgdMaterialInstock> queryGroupByMaterialName(Long deptId) {
        return zhgdMaterialInstockMapper.queryGroupByMaterialName(deptId);
    }
}
