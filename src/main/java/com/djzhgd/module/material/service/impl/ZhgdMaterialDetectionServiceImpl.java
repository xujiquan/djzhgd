package com.djzhgd.module.material.service.impl;

import com.djzhgd.common.utils.SecurityUtils;
import com.djzhgd.framework.security.LoginUser;
import com.djzhgd.module.enums.DictionaryEnum;
import com.djzhgd.module.material.domain.ZhgdMaterialDetection;
import com.djzhgd.module.material.domain.ZhgdMaterialInstock;
import com.djzhgd.module.material.domain.vo.ZhgdMaterialDetectionVo;
import com.djzhgd.module.material.mapper.ZhgdMaterialDetectionMapper;
import com.djzhgd.module.material.mapper.ZhgdMaterialInstockMapper;
import com.djzhgd.module.material.service.ZhgdMaterialDetectionService;
import com.djzhgd.module.result.Result;
import com.djzhgd.module.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lgc
 * @Date: 2020/5/18 17:18
 */
@Slf4j
@Service
public class ZhgdMaterialDetectionServiceImpl  implements ZhgdMaterialDetectionService {
    @Autowired
    private ZhgdMaterialDetectionMapper zhgdMaterialDetectionMapper;
    @Autowired
    private ZhgdMaterialInstockMapper zhgdMaterialInstockMapper;


    @Transactional
    @Override
    public Result saveDetection(ZhgdMaterialDetectionVo materialDetectionVo) {
        Result result =new Result();
        try {
            ZhgdMaterialDetection detectionResult = materialDetectionVo.getEntity();
            LoginUser user = SecurityUtils.getLoginUser();
            detectionResult.setCreateUserid(user.getUser().getUserId().intValue());
            detectionResult.setCreateUsername(user.getUsername());
            boolean saveFlag = zhgdMaterialDetectionMapper.save(detectionResult);
            if(!saveFlag){
                result.setCode(ResultEnum.RESULT_INSERT_ERROR.getCode());
                result.setMsg(ResultEnum.RESULT_INSERT_ERROR.getMsg());
                return result;
            }

            String instockBatch = detectionResult.getInstockBatch();
            Integer tenantId = detectionResult.getTenantId();
            Long deptId = detectionResult.getDeptId();
            Map<String, Object> paramMap = new HashMap<>(16);
            paramMap.put("batch", instockBatch);
            paramMap.put("tenantId", tenantId);
            paramMap.put("deptId", deptId);
            boolean flag = true;
            List<ZhgdMaterialInstock> inStockDataList = zhgdMaterialInstockMapper.queryInStockByBatchAndProId(paramMap);
/*        if (!CollectionUtils.isEmpty(inStockDataList)) {
            ZhgdMaterialInstock inStockData = inStockDataList.get(0);
            Integer checkNumber = inStockData.getCheckNum();

            Map<String, Object> inParam = new HashMap<>(16);
            inParam.put("instockBatch", instockBatch);
            inParam.put("detectionType", materialDetectionVo.getEntity().getDetectionType());
            inParam.put("tenantId", tenantId);
            inParam.put("tenantId", tenantId);

            List<ZhgdMaterialDetection> detectionResultList = zhgdMaterialDetectionMapper.getByInStockBatchAndTenId(inParam);
            for (ZhgdMaterialDetection xyDetectionResult2 : detectionResultList) {
                if ("0".equals(xyDetectionResult2.getCheckResult())) {
                    flag = false;
                }
            }
            if (flag && checkNumber == detectionResultList.size()) {
                if (inStockData.getTaskNode() == 1) {
                    inStockData.setTaskNode(2);
                    zhgdMaterialInstockMapper.update(inStockData);

                    String wlName = DictionaryEnum.getNameByValue(inStockData.getMaterialName());
                    // 推送给监理
                    conment = inStockData.getInstockTime() + "进场的" + inStockData.getNumber() + "(吨/件/米)" + inStockData.getBatch() + wlName
                            +"需要抽检";
                    String code = "APP_WULIAOTUISONG_JL";
                    String title = "物料检测";
                    MessageConfigure m = mc.getByPermissionCode(code, 0);
                    List<Map<String, Object>> list = mc.getUsers(m.getZhgdUserId());
                    if (list != null && list.size() > 0) {
                        // 个推 + 待办
                        String roleType = "2";
                        Integer type = 1;
                        for(Map<String,Object> u : list){
                            this.saveLog(u, inStockData, conment, title, code, roleType, type);
                        }
                    }

                    //判断物料类型
                    String materialName = inStockData.getMaterialName();
                    if(materialName.equals(DictionaryConst.REBAR.getValue()) || materialName.equals(DictionaryConst.CEMENT.getValue())){
                        // 施工试验室检测合格，检测次数符合要求之后流转到监理实验室
                        // 计算监理应检测次数(checkNumber)
                        BigDecimal totalNum = new BigDecimal(checkNumber);
                        double percent = 0.3;
                        BigDecimal P = new BigDecimal(percent);
                        BigDecimal multiply = totalNum.multiply(P);
                        BigDecimal result = multiply.setScale(0, BigDecimal.ROUND_UP);
                        int size = result.intValue();
                        zhgdMaterialInstockService.sendOrderToJLSYX(size, inStockData.getMaterialName(), inStockDataList.get(0));
                    }


                }else if(inStockData.getTaskNode().equals(2)){ // 监理实验室
                    String materialName = inStockData.getMaterialName();
                    if(materialName.equals(DictionaryConst.REBAR.getValue()) || materialName.equals(DictionaryConst.CEMENT.getValue())){
                        inStockData.setTaskNode(3);
                        zhgdMaterialInstockService.update(inStockData);
                    }
                }
            }
        }*/
        } catch (Exception e) {
            log.error("saveDetection exception:"+e.getMessage());
            result.setCode(ResultEnum.RESULT_INSERT_ERROR.getCode());
            result.setMsg(ResultEnum.RESULT_INSERT_ERROR.getMsg());
        }
        result.setCode(ResultEnum.RESULT_INSERT_OK.getCode());
        result.setMsg(ResultEnum.RESULT_INSERT_OK.getMsg());
        return result;

    }

    @Override
    public List<ZhgdMaterialDetection> queryGroupByNameAndResultAndBatchAndDTNoPass(Long deptId) {
        return zhgdMaterialDetectionMapper.queryGroupByNameAndResultAndBatchAndDTNoPass(deptId);
    }

    @Override
    public ZhgdMaterialDetection detailDetection(Integer id) {
        ZhgdMaterialDetection materialDetection = zhgdMaterialDetectionMapper.getById(id);
        materialDetection.setMaterialNameDesc(DictionaryEnum.getNameByValue(materialDetection.getMaterialName()));
        Map<String, Object> paramMap = new HashMap<>(16);
        paramMap.put("batch", materialDetection.getInstockBatch());
        paramMap.put("tenantId", materialDetection.getTenantId());
        paramMap.put("deptId", materialDetection.getDeptId());

        List<ZhgdMaterialInstock> inStockByBatchList = zhgdMaterialInstockMapper.queryInStockByBatchAndProId(paramMap);
        materialDetection.setUnit(inStockByBatchList.get(0).getUnit());
        return materialDetection;
    }

    @Override
    public boolean save(ZhgdMaterialDetection materialDetection) {
        return zhgdMaterialDetectionMapper.save(materialDetection);
    }

    @Override
    public List<ZhgdMaterialDetection> queryAll(Map<String, Object> paraMap) {
        return zhgdMaterialDetectionMapper.queryAllByMap(paraMap);
    }

    @Override
    public List<ZhgdMaterialDetection> queryGroupByNameAndResultAndBatchNoPass(Long deptId) {
        return zhgdMaterialDetectionMapper.queryGroupByNameAndResultAndBatchNoPass(deptId);
    }

    @Override
    public List<ZhgdMaterialDetection> queryGroupByNameAndBatch(Long deptId) {
        return zhgdMaterialDetectionMapper.queryGroupByNameAndBatch(deptId);

    }

    @Override
    public List<ZhgdMaterialDetection> queryGroupByNameAndResultAndBatch(Long deptId) {
        return zhgdMaterialDetectionMapper.queryGroupByNameAndResultAndBatch(deptId);
    }

    @Override
    public Result stockCheckDP(Long deptId) {
        Result result =new Result();

        // instock 取checkNum sg
        List<ZhgdMaterialInstock> all = zhgdMaterialInstockMapper.queryAllByDeptId(deptId);
        int sgCount = 0;
        int jlCount = 0;
        int zxCount = 0;
        if (all != null && !all.isEmpty()) {
            for (ZhgdMaterialInstock zhgdMaterialInstock : all) {
                Integer checkNum = zhgdMaterialInstock.getCheckNum();
                sgCount = sgCount + checkNum;
                // 单条数据监理number
                int jlCountEach = this.getSupervisionNum(checkNum, 1);
                // 总和
                jlCount = jlCount + jlCountEach;
            }
        }

        List<Map<String, Object>> resultsMaps = new ArrayList<>();
        // 已检测
        List<ZhgdMaterialDetection> list =  zhgdMaterialDetectionMapper
                .stockCheckByDeptId(deptId);

        Integer checkNum = 0;
        String resultNum = "0";
        if (list != null && !list.isEmpty()) {
            for (ZhgdMaterialDetection materialDetection : list) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                checkNum = materialDetection.getCheckNum();
                // Integer checkNum = materialDetection.getNum();
                String detectionType = materialDetection.getDetectionType();
                NumberFormat numberFormat = NumberFormat.getInstance();
                // 设置精确到小数点后2位
                numberFormat.setMaximumFractionDigits(2);
                switch (detectionType) {
                    case "1":
                        if (checkNum != 0) {
                            resultNum = numberFormat.format((float) checkNum
                                    / (float) sgCount * 100);
                            if (Double.valueOf(resultNum) > 100) {
                                resultNum = "100";
                            }
                        }
                        map.put("detectionType", "施工单位");
                        map.put("allCount", sgCount);
                        map.put("jcCount", checkNum);
                        map.put("percentum", resultNum);
                        break;
                    case "2":
                        if (checkNum != 0) {
                            resultNum = numberFormat.format((float) checkNum
                                    / (float) jlCount * 100);
                            if (Double.valueOf(resultNum) > 100) {
                                resultNum = "100";
                            }
                        }

                        map.put("detectionType", "监理单位");
                        map.put("allCount", jlCount);
                        map.put("jcCount", checkNum);
                        map.put("percentum", resultNum);
                        break;
                    case "3":
                        if (checkNum != 0) {
                            resultNum = numberFormat.format((float) checkNum
                                    / (float) zxCount * 100);
                            if (Double.valueOf(resultNum) > 100) {
                                resultNum = "100";
                            }
                        }

                        map.put("detectionType", "中心试验室");
                        map.put("allCount", zxCount);
                        map.put("jcCount", checkNum);
                        map.put("percentum", resultNum);
                        break;
                    default:
                        break;
                }
                resultsMaps.add(map);
            }
        } else {
            HashMap<String, Object> mapSg = new HashMap<String, Object>();
            mapSg.put("detectionType", "施工单位");
            mapSg.put("allCount", sgCount);
            mapSg.put("jcCount", checkNum);
            mapSg.put("percentum", resultNum);
            HashMap<String, Object> mapJl = new HashMap<String, Object>();
            mapJl.put("detectionType", "监理单位");
            mapJl.put("allCount", jlCount);
            mapJl.put("jcCount", checkNum);
            mapJl.put("percentum", resultNum);
            HashMap<String, Object> mapZx = new HashMap<String, Object>();
            mapZx.put("detectionType", "中心试验室");
            mapZx.put("allCount", zxCount);
            mapZx.put("jcCount", checkNum);
            mapZx.put("percentum", resultNum);
            resultsMaps.add(mapSg);
            resultsMaps.add(mapJl);
            resultsMaps.add(mapZx);
        }

        result.setCode(ResultEnum.RESULT_OK.getCode());
        result.setMsg(ResultEnum.RESULT_OK.getMsg());
        result.setData(resultsMaps);
        return result;
    }

    private int getSupervisionNum(int num, int type) {
        int size = 0;
        if (type == 1) {
            // 总条数
            BigDecimal totalNum = new BigDecimal(num);

            //  30%
            // 2020-04-07 改100%
            // double percent = 0.2;
            double percent = 1.0;
            BigDecimal P = new BigDecimal(percent);

            // 取30%的条数
            BigDecimal multiply = totalNum.multiply(P);

            // 进1 算法
            BigDecimal result = multiply.setScale(0, BigDecimal.ROUND_UP);

            size = result.intValue();
        } else if (type == 2) {
            // 总条数
            BigDecimal totalNum = new BigDecimal(num);

            // 5%
            double percent = 0.05;
            BigDecimal P = new BigDecimal(percent);

            // 取30%的条数
            BigDecimal multiply = totalNum.multiply(P);

            // 进1 算法
            BigDecimal result = multiply.setScale(0, BigDecimal.ROUND_UP);

            size = result.intValue();
        }

        return size;
    }

}
