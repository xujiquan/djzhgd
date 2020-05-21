package com.djzhgd.module.material.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djzhgd.common.utils.DateUtils;
import com.djzhgd.module.constants.MaterialConstant;
import com.djzhgd.module.enums.DictionaryEnum;
import com.djzhgd.module.material.domain.ZhgdMaterialDetection;
import com.djzhgd.module.material.domain.ZhgdMaterialInstock;
import com.djzhgd.module.material.domain.ZhgdMaterialStockRecord;
import com.djzhgd.module.material.domain.ZhgdMaterielDesignUsage;
import com.djzhgd.module.material.mapper.ZhgdMaterialInstockMapper;
import com.djzhgd.module.material.mapper.ZhgdMaterialStockRecordMapper;
import com.djzhgd.module.material.mapper.ZhgdMaterielDesignUsageMapper;
import com.djzhgd.module.material.service.ZhgdMaterialDetectionService;
import com.djzhgd.module.material.service.ZhgdMaterialInstockService;
import com.djzhgd.module.material.service.ZhgdMaterialStockRecordService;
import com.djzhgd.module.result.PageResult;
import com.djzhgd.module.result.ResultEnum;
import com.djzhgd.module.utils.DoubleUtil;
import com.djzhgd.module.utils.HttpClientUtil;
import com.djzhgd.project.system.domain.ZhgdDict;
import com.djzhgd.project.system.mapper.ZhgdDictMapper;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: lgc
 * @Date: 2020/5/18 15:20
 */
@Service
public class ZhgdMaterialStockRecordServiceImpl  extends ServiceImpl<ZhgdMaterialStockRecordMapper, ZhgdMaterialStockRecord> implements ZhgdMaterialStockRecordService {

    public static final String QUERY_URL = "http://new.djzhgd.com:10724/gkxt-api/clylcxbypro/clylcx";

    @Autowired
    private ZhgdMaterialStockRecordMapper zhgdMaterialStockRecordMapper;
    @Autowired
    private ZhgdMaterialInstockMapper zhgdMaterialInstockMapper;
    @Autowired
    private ZhgdMaterialInstockService zhgdMaterialInstockService;
    @Autowired
    private ZhgdDictMapper zhgdDictMapper;
    @Autowired
    private ZhgdMaterielDesignUsageMapper zhgdMaterielDesignUsageMapper;
    @Autowired
    private ZhgdMaterialDetectionService zhgdMaterialDetectionService;
    @Override
    public List<ZhgdMaterialStockRecord> queryByTypeAndNameProId(String materialType, String materialName, String bidsName, Integer tenantId,Long deptId) {
        Map<String, Object> params = new HashMap<>(16);
        params.put("materialType", materialType);
        params.put("materialName", materialName);
        params.put("bidsName", bidsName);
        params.put("tenantId", tenantId);
        params.put("deptId", deptId);

        return zhgdMaterialStockRecordMapper.queryByTypeAndNameProId(params);
    }

    @Override
    public boolean save(ZhgdMaterialStockRecord zhgdMaterialStockRecord) {
        return zhgdMaterialStockRecordMapper.save(zhgdMaterialStockRecord);
    }

    @Override
    public int update(ZhgdMaterialStockRecord zhgdMaterialStockRecord) {
        return zhgdMaterialStockRecordMapper.update(zhgdMaterialStockRecord);
    }

    @Override
    public PageResult<ZhgdMaterialStockRecord> getlist(PageResult<ZhgdMaterialStockRecord> pageResult) {
        IPage<ZhgdMaterialStockRecord> materialStockRecordIPage = null;
        try {
            ZhgdMaterialStockRecord materialStockRecord = pageResult.getExample();
            // 查询条件封装
            LambdaQueryWrapper<ZhgdMaterialStockRecord> queryWrapper = Wrappers.lambdaQuery();

            if(StringUtils.isNotBlank(materialStockRecord.getMaterialName())){
                queryWrapper.eq(ZhgdMaterialStockRecord::getMaterialName, materialStockRecord.getMaterialName());
            }
            queryWrapper.eq(ZhgdMaterialStockRecord::getDeptId, materialStockRecord.getDeptId());
            // 根据id倒序
            queryWrapper.orderByDesc(ZhgdMaterialStockRecord::getId);

            // 设置当前页和页容量
            Page<ZhgdMaterialStockRecord> page = new Page<>(pageResult.getPage(), pageResult.getLimit());
            materialStockRecordIPage = zhgdMaterialStockRecordMapper.selectPage(page, queryWrapper);

            // 设置物料名称
            for (ZhgdMaterialStockRecord zhgdMaterialStockRecord : materialStockRecordIPage.getRecords()) {
                zhgdMaterialStockRecord.setMaterialNameDesc(DictionaryEnum.getNameByValue(zhgdMaterialStockRecord.getMaterialName()));
                zhgdMaterialStockRecord.setMaterialTypeDesc(DictionaryEnum.getNameByValue(zhgdMaterialStockRecord.getMaterialType()));
            }

        } catch (Exception e) {
            log.error("ZhgdMaterialStockRecordService-->getlist  exception:"+e.getMessage());
            pageResult.setCode(ResultEnum.RESULT_ERROR.getCode());
            pageResult.setMsg(ResultEnum.RESULT_ERROR.getMsg()+e.toString());
            return pageResult;
        }
        pageResult.setData(materialStockRecordIPage.getRecords());
        pageResult.setCode(ResultEnum.RESULT_OK.getCode());
        pageResult.setMsg(ResultEnum.RESULT_OK.getMsg());
        pageResult.setCount(materialStockRecordIPage.getTotal());
        return pageResult;

    }

    @Override
    public Map<String, Object> getStockBar(Long deptId) {
        Map<String, Object> resultMap = new HashMap<>(16);
        List<String> stockTypeList = new ArrayList<String>();
        stockTypeList.add(DictionaryEnum.REBAR.getValue());
        stockTypeList.add(DictionaryEnum.CEMENT.getValue());
        stockTypeList.add(DictionaryEnum.GRAVEL.getValue());
        stockTypeList.add(DictionaryEnum.YELLOW_SAND.getValue());
        stockTypeList.add(DictionaryEnum.ADMIXTURE.getValue());
        stockTypeList.add(DictionaryEnum.MJJP.getValue());
        stockTypeList.add(DictionaryEnum.GJX.getValue());
        stockTypeList.add(DictionaryEnum.SH.getValue());
        stockTypeList.add(DictionaryEnum.XJZZ.getValue());
        stockTypeList.add(DictionaryEnum.SSZZ.getValue());
        stockTypeList.add(DictionaryEnum.BWG.getValue());
        stockTypeList.add(DictionaryEnum.GJWP.getValue());

       /* JSONObject object = JSONObject.parseObject(inParam);
        Long tenantId = object.getLong("tenantId");
        Long deptId = object.getLong("deptId");*/

        List<ZhgdMaterialInstock> instockdataList = zhgdMaterialInstockMapper.queryGroupByMaterialNameAndProId(deptId);

        // 总量
        List<Double> allNum = new ArrayList<Double>();

        // 剩余量
        List<Double> remain = new ArrayList<Double>();

        // 使用量
        List<Double> useNum = new ArrayList<Double>();

        // 7天平均平均量
        List<Double> avgNum = new ArrayList<Double>();

        // 计算剩余量与平均量
        Map<String,Object> paraMap = new HashMap<>(16);
       // paraMap.put("tenantId",tenantId);
        paraMap.put("deptId",deptId);

        List<ZhgdMaterialStockRecord> stockdataList = zhgdMaterialStockRecordMapper.queryByTypeAndNameProId(paraMap);
        DecimalFormat df = new DecimalFormat("0.00");

        // 计算总量
        for (Integer i = 0; i < stockTypeList.size(); i++) {
            allNum.add(0.0);
            String stockType = stockTypeList.get(i);
            for (ZhgdMaterialInstock inStockData : instockdataList) {
                Double stockNum = allNum.get(i);
                if (stockType.equals(inStockData.getMaterialName())) {
                    inStockData.setNumber(inStockData.getSumNumber());
                    Map<String, Object> unitInfo = zhgdMaterialInstockService.changeDataToDUN(inStockData);
                    stockNum = DoubleUtil.sum(stockNum, MapUtils.getDoubleValue(unitInfo, "stockNum", 0));
                    allNum.set(i, Double.valueOf(df.format(stockNum)));
                }
            }
        }
        // 计算剩余量与平均量 使用量
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String endTime = simpleDateFormat.format(new Date());
        Date startDate = DateUtils.addWeeks(new Date(),-1);
        String startTime = simpleDateFormat1.format(startDate);


        ZhgdDict zhgdDictSg = zhgdDictMapper.getUrlByDeptid(deptId, MaterialConstant.SG_DEPART_CODE_GROUPID);
        ZhgdDict zhgdDictJl = zhgdDictMapper.getUrlByDeptid(deptId, MaterialConstant.JL_DEPART_CODE_GROUPID);
        ZhgdDict zhgdDictJc = zhgdDictMapper.getUrlByDeptid(deptId, MaterialConstant.JC_DEPART_CODE_GROUPID);

        List<ZhgdDict> bdList = new ArrayList<>();
        bdList.add(zhgdDictSg);
        bdList.add(zhgdDictJl);
        bdList.add(zhgdDictJc);

        JSONObject objectMaterial = new JSONObject();
        objectMaterial.put("shuini1", 0);
        objectMaterial.put("shuini2", 0);
        objectMaterial.put("shuini3", 0);
        objectMaterial.put("sjgl1", 0);
        objectMaterial.put("sjgl2", 0);
        objectMaterial.put("sjgl3", 0);
        objectMaterial.put("sha1", 0);
        objectMaterial.put("waijiaji1", 0);
        objectMaterial.put("waijiaji2", 0);
        objectMaterial.put("meihui1", 0);
        objectMaterial.put("meihui2", 0);

        for (ZhgdDict zhgdDict : bdList) {
            String bdValue = zhgdDict.getDataValue();
            net.sf.json.JSONObject object1 = HttpClientUtil.httpGet(QUERY_URL + "?proId="
                    + MaterialConstant.GK_PROJECTID + "&sysId=3&mp_num=" + bdValue + "&startTime=" + startTime + "&endTime=" + endTime);
            if (null == object1) {
                continue;
            }
            double oshuini1 = Double.parseDouble(String.valueOf(objectMaterial.get("shuini1")));
            double o1shuini1 = Double.parseDouble(String.valueOf(object1.get("shuini1")));
            objectMaterial.put("shuini1",  oshuini1 + o1shuini1);

            double oshuini2 = Double.parseDouble(String.valueOf(objectMaterial.get("shuini2")));
            double o1shuini2 = Double.parseDouble(String.valueOf(object1.get("shuini2")));
            objectMaterial.put("shuini2",  oshuini2 + o1shuini2);

            double oshuini3 = Double.parseDouble(String.valueOf(objectMaterial.get("shuini3")));
            double o1shuini3 = Double.parseDouble(String.valueOf(object1.get("shuini3")));
            objectMaterial.put("shuini3",  oshuini3 + o1shuini3);

            double osjgl1 = Double.parseDouble(String.valueOf(objectMaterial.get("sjgl1")));
            double o1sjgl1 = Double.parseDouble(String.valueOf(object1.get("sjgl1")));
            objectMaterial.put("sjgl1",  osjgl1 + o1sjgl1);

            double osjgl2 = Double.parseDouble(String.valueOf(objectMaterial.get("sjgl2")));
            double o1sjgl2 = Double.parseDouble(String.valueOf(object1.get("sjgl2")));
            objectMaterial.put("sjgl2",  osjgl2 + o1sjgl2);

            double osjgl3 = Double.parseDouble(String.valueOf(objectMaterial.get("sjgl3")));
            double o1sjgl3 = Double.parseDouble(String.valueOf(object1.get("sjgl3")));
            objectMaterial.put("sjgl3",  osjgl3 + o1sjgl3);

            double osha1 = Double.parseDouble(String.valueOf(objectMaterial.get("sha1")));
            double o1sha1 = Double.parseDouble(String.valueOf(object1.get("sha1")));
            objectMaterial.put("sha1",  osha1 + o1sha1);

            double owaijiaji1 = Double.parseDouble(String.valueOf(objectMaterial.get("waijiaji1")));
            double o1waijiaji1 = Double.parseDouble(String.valueOf(object1.get("waijiaji1")));
            objectMaterial.put("waijiaji1",  owaijiaji1 + o1waijiaji1);

            double owaijiaji2 = Double.parseDouble(String.valueOf(objectMaterial.get("waijiaji2")));
            double o1waijiaji2 = Double.parseDouble(String.valueOf(object1.get("waijiaji2")));
            objectMaterial.put("waijiaji2",  owaijiaji2 + o1waijiaji2);

            double omeihui1 = Double.parseDouble(String.valueOf(objectMaterial.get("meihui1")));
            double o1meihui1 = Double.parseDouble(String.valueOf(object1.get("meihui1")));
            objectMaterial.put("meihui1",  omeihui1 + o1meihui1);

            double omeihui2 = Double.parseDouble(String.valueOf(objectMaterial.get("meihui2")));
            double o1meihui2 = Double.parseDouble(String.valueOf(object1.get("meihui2")));
            objectMaterial.put("meihui2",  omeihui2 + o1meihui2);
        }


        for (int i = 0; i < stockTypeList.size(); i++) {
            boolean flag1 = true;
            for (ZhgdMaterialStockRecord stockData : stockdataList) {
                if (stockTypeList.get(i).equals(stockData.getMaterialName())) {
                    remain.add(Double.valueOf(df.format(stockData.getSumNumber())));
                    useNum.add(Double.valueOf(df.format(DoubleUtil.sub(allNum.get(i), stockData.getSumNumber()))));
                    flag1 = false;
                }
            }
            if (flag1) {
                remain.add(0.0);
                useNum.add(0.0);
            }
            String materialName = stockTypeList.get(i);
            Double lowerNumber;
            if (DictionaryEnum.REBAR.getValue().equals(materialName)) {
                avgNum.add(0.0);
            }else if (DictionaryEnum.CEMENT.getValue().equals(materialName)) {
                // 水泥
                double shuini1 = Double.parseDouble(String.valueOf(objectMaterial.get("shuini1")));
                double shuini2 = Double.parseDouble(String.valueOf(objectMaterial.get("shuini2")));
                double shuini3 = Double.parseDouble(String.valueOf(objectMaterial.get("shuini3")));
                double sumshuini = shuini1 + shuini2 + shuini3;
                lowerNumber = sumshuini / 1000;
                avgNum.add(Double.valueOf(df.format(lowerNumber)));
            }else if (DictionaryEnum.GRAVEL.getValue().equals(materialName)) {
                // 碎石
                double sjgl1 = Double.parseDouble(String.valueOf(objectMaterial.get("sjgl1")));
                double sjgl2 = Double.parseDouble(String.valueOf(objectMaterial.get("sjgl2")));
                double sjgl3 = Double.parseDouble(String.valueOf(objectMaterial.get("sjgl3")));
                double sumsjgl = sjgl1 + sjgl2 + sjgl3;
                lowerNumber = sumsjgl / 1000;
                avgNum.add(Double.valueOf(df.format(lowerNumber)));
            }else if (DictionaryEnum.YELLOW_SAND.getValue().equals(materialName)) {
                // 黄沙
                double sha1 = Double.parseDouble(String.valueOf(objectMaterial.get("sha1")));
                lowerNumber = sha1 / 1000;
                avgNum.add(Double.valueOf(df.format(lowerNumber)));
            }else if (DictionaryEnum.ADMIXTURE.getValue().equals(materialName)) {
                // 外加剂
                double waijiaji1 = Double.parseDouble(String.valueOf(objectMaterial.get("waijiaji1")));
                double waijiaji2 = Double.parseDouble(String.valueOf(objectMaterial.get("waijiaji2")));
                double sumwaijiaji = waijiaji1 + waijiaji2;
                lowerNumber = sumwaijiaji / 1000;
                avgNum.add(Double.valueOf(df.format(lowerNumber)));
            }else if (DictionaryEnum.FLY_ASH.getValue().equals(materialName)) {
                // 煤灰
                double meihui1 = Double.parseDouble(String.valueOf(objectMaterial.get("meihui1")));
                double meihui2 = Double.parseDouble(String.valueOf(objectMaterial.get("meihui2")));
                double summeihui = meihui1 + meihui2;
                lowerNumber = summeihui / 1000;
                avgNum.add(Double.valueOf(df.format(lowerNumber)));
            } else {
                avgNum.add(0D);
            }
        }
        // 设计用量
        HashMap<String,Object> map = new HashMap<String,Object>();
        //已使用百分比
        HashMap<String, Object> usagePrecent = new HashMap<String, Object>();
        // 获取所有原材料设计用量
        Map<String,Object> paraMapDesign = new HashMap<>(16);
        paraMapDesign.put("deptId",deptId);
        List<ZhgdMaterielDesignUsage> list = zhgdMaterielDesignUsageMapper.selectAll(paraMapDesign);
        // 入库量/设计用量 * 100 计算百分比 配合前端修改数据返回格式
        Double number = 0.00;
        for (ZhgdMaterielDesignUsage usage : list) {
            if (DictionaryEnum.REBAR.getValue().equals(usage.getMaterialCode())) {
                map.put("gj", usage.getNumber());
                number = getNumber(allNum.get(0), usage.getNumber());
                usagePrecent.put("gj", number);
            }

            if (DictionaryEnum.CEMENT.getValue().equals(usage.getMaterialCode())) {
                map.put("sn", usage.getNumber());
                number = getNumber(allNum.get(1), usage.getNumber());
                usagePrecent.put("sn", number);
            }

            if (DictionaryEnum.GRAVEL.getValue().equals(usage.getMaterialCode())) {
                map.put("ss", usage.getNumber());
                number = getNumber(allNum.get(2), usage.getNumber());
                usagePrecent.put("ss", number);
            }

            if (DictionaryEnum.YELLOW_SAND.getValue().equals(usage.getMaterialCode())) {
                map.put("hs", usage.getNumber());
                number = getNumber(allNum.get(3), usage.getNumber());
                usagePrecent.put("hs", number);
            }

            if (DictionaryEnum.ADMIXTURE.getValue().equals(usage.getMaterialCode())) {
                map.put("wjj", usage.getNumber());
                number = getNumber(allNum.get(4), usage.getNumber());
                usagePrecent.put("wjj", number);
            }

            if (DictionaryEnum.MJJP.getValue().equals(usage.getMaterialCode())) {
                map.put("mjjp", usage.getNumber());
                number = getNumber(allNum.get(5), usage.getNumber());
                usagePrecent.put("mjjp", number);
            }

            if (DictionaryEnum.GJX.getValue().equals(usage.getMaterialCode())) {
                map.put("gjx", usage.getNumber());
                number = getNumber(allNum.get(6), usage.getNumber());
                usagePrecent.put("gjx", number);
            }
            if (DictionaryEnum.SH.getValue().equals(usage.getMaterialCode())) {
                map.put("sh", usage.getNumber());
                number = getNumber(allNum.get(7), usage.getNumber());
                usagePrecent.put("sh", number);
            }

            if (DictionaryEnum.XJZZ.getValue().equals(usage.getMaterialCode())) {
                map.put("xjzz", usage.getNumber());
                number = getNumber(allNum.get(8), usage.getNumber());
                usagePrecent.put("xjzz", number);
            }

            if (DictionaryEnum.SSZZ.getValue().equals(usage.getMaterialCode())) {
                map.put("sszz", usage.getNumber());
                number = getNumber(allNum.get(9), usage.getNumber());
                usagePrecent.put("sszz", number);
            }

            if (DictionaryEnum.BWG.getValue().equals(usage.getMaterialCode())) {
                map.put("bwg", usage.getNumber());
                number = getNumber(allNum.get(10), usage.getNumber());
                usagePrecent.put("bwg", number);
            }

            if (DictionaryEnum.GJWP.getValue().equals(usage.getMaterialCode())) {
                map.put("gjwp", usage.getNumber());
                number = getNumber(allNum.get(11), usage.getNumber());
                usagePrecent.put("gjwp", number);
            }

        }
        resultMap.put("allNum", allNum);
        resultMap.put("remain", remain);
        resultMap.put("useNum", useNum);
        resultMap.put("avgNum", avgNum);
        resultMap.put("estimateNum", map);
        resultMap.put("usagePrecent", usagePrecent);
        return resultMap;
    }

    @Override
    public Map<String, Object> getStockPie(Long deptId) {
        Map<String, Object> resultMap = new HashMap<>();
        List<String> stockTypeList = new ArrayList<String>();
        stockTypeList.add(DictionaryEnum.REBAR.getValue());
        stockTypeList.add(DictionaryEnum.CEMENT.getValue());
        stockTypeList.add(DictionaryEnum.GRAVEL.getValue());
        stockTypeList.add(DictionaryEnum.YELLOW_SAND.getValue());
        stockTypeList.add(DictionaryEnum.ADMIXTURE.getValue());
        stockTypeList.add(DictionaryEnum.MJJP.getValue());
        stockTypeList.add(DictionaryEnum.GJX.getValue());
        stockTypeList.add(DictionaryEnum.SH.getValue());
        stockTypeList.add(DictionaryEnum.XJZZ.getValue());
        stockTypeList.add(DictionaryEnum.SSZZ.getValue());
        stockTypeList.add(DictionaryEnum.BWG.getValue());
        stockTypeList.add(DictionaryEnum.GJWP.getValue());

        // 分组获得审核数据，当某个批次有一次审核不通过的则为不通过
        List<ZhgdMaterialDetection> xyDRNoPass = zhgdMaterialDetectionService.queryGroupByNameAndResultAndBatchNoPass(deptId);

        // 获取物料名称与批次,总数
        List<ZhgdMaterialDetection> xyDRByNameAndBatch = zhgdMaterialDetectionService.queryGroupByNameAndBatch(deptId);

        // 十二个百分比
        List<Double> percentageList = new ArrayList<Double>();

        for (String stockType : stockTypeList) {
            // 不合格数量
            Integer num = 0;
            for (ZhgdMaterialDetection nopass : xyDRNoPass) {
                if (stockType.equals(nopass.getMaterialName())) {
                    num++;
                }
            }

            if (num == 0) {
                percentageList.add(1.0);
            } else {
                for (ZhgdMaterialDetection count : xyDRByNameAndBatch) {
                    if (stockType.equals(count.getMaterialName())) {
                        percentageList.add(DoubleUtil.div(count.getCount() - num, count.getCount(), 3));
                    }
                }
            }

        }

        resultMap.put("percentageList", percentageList);
        return resultMap;

    }


    private Double getNumber(Double rk, BigDecimal bigDecimal) {
        Double desPre = 0.00;

        desPre = rk / bigDecimal.doubleValue() * 100;
        desPre = desPre > 100 ? 100 : desPre;
        desPre = new BigDecimal(desPre).setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();

        return desPre;
    }

    @Override
    public Map<String, Object> getStockBarNew(Long deptId) {
        Map<String, Object> resultMap = new HashMap<>();
        List<String> stockTypeList = new ArrayList<String>();

        stockTypeList.add(DictionaryEnum.CEMENT.getValue());
        stockTypeList.add(DictionaryEnum.GRAVEL.getValue());
        stockTypeList.add(DictionaryEnum.YELLOW_SAND.getValue());
        stockTypeList.add(DictionaryEnum.ADMIXTURE.getValue());
        stockTypeList.add(DictionaryEnum.REBAR.getValue());
        stockTypeList.add(DictionaryEnum.MJJP.getValue());
        stockTypeList.add(DictionaryEnum.GJX.getValue());
        stockTypeList.add(DictionaryEnum.SH.getValue());
        stockTypeList.add(DictionaryEnum.XJZZ.getValue());
        stockTypeList.add(DictionaryEnum.SSZZ.getValue());
        stockTypeList.add(DictionaryEnum.BWG.getValue());
        stockTypeList.add(DictionaryEnum.GJWP.getValue());


        List<String> stockTypeDesc = new ArrayList<String>();
        stockTypeDesc.add(DictionaryEnum.CEMENT.getName());
        stockTypeDesc.add(DictionaryEnum.GRAVEL.getName());
        stockTypeDesc.add(DictionaryEnum.YELLOW_SAND.getName());
        stockTypeDesc.add(DictionaryEnum.ADMIXTURE.getName());
        stockTypeDesc.add(DictionaryEnum.REBAR.getName());
        stockTypeDesc.add(DictionaryEnum.MJJP.getName());
        stockTypeDesc.add(DictionaryEnum.GJX.getName());
        stockTypeDesc.add(DictionaryEnum.SH.getName());
        stockTypeDesc.add(DictionaryEnum.XJZZ.getName());
        stockTypeDesc.add(DictionaryEnum.SSZZ.getName());
        stockTypeDesc.add(DictionaryEnum.BWG.getName());
        stockTypeDesc.add(DictionaryEnum.GJWP.getName());

        // 总量
        List<ZhgdMaterialInstock> instockdataList = zhgdMaterialInstockService.queryGroupByMaterialName(deptId);
        // 总量
        List<Double> allNum = new ArrayList<Double>();

        // 剩余量
        List<Double> remain = new ArrayList<Double>();

        // 使用量
        List<Double> useNum = new ArrayList<Double>();

        // 平均量
        List<Double> avgNum = new ArrayList<Double>();

        // 计算剩余量与平均量
        List<ZhgdMaterialStockRecord> stockdataList = zhgdMaterialStockRecordMapper.queryGroupByMaterialName(deptId);

        // 计算总量
        for (Integer i = 0; i < stockTypeList.size(); i++) {
            allNum.add(0.0);
            String stockType = stockTypeList.get(i);
            for (ZhgdMaterialInstock inStockData : instockdataList) {
                Double stockNum = allNum.get(i);
                if (stockType.equals(inStockData.getMaterialName())) {
                    inStockData.setNumber(inStockData.getSumNumber());
                    Map<String, Object> unitInfo = zhgdMaterialInstockService.changeDataToDUN(inStockData);
                    stockNum = DoubleUtil.sum(stockNum, MapUtils.getDoubleValue(unitInfo, "stockNum", 0));
                    allNum.set(i, stockNum);
                }
            }
        }

        // 计算剩余量与平均量 使用量
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String endTime = simpleDateFormat.format(new Date());
        Date startDate = DateUtils.addWeeks(new Date(),-1);
        String startTime = simpleDateFormat1.format(startDate);

        ZhgdDict zhgdDictSg = zhgdDictMapper.getUrlByDeptid(deptId, MaterialConstant.SG_DEPART_CODE_GROUPID);
        ZhgdDict zhgdDictJl = zhgdDictMapper.getUrlByDeptid(deptId, MaterialConstant.JL_DEPART_CODE_GROUPID);
        ZhgdDict zhgdDictJc = zhgdDictMapper.getUrlByDeptid(deptId, MaterialConstant.JC_DEPART_CODE_GROUPID);

        List<ZhgdDict> bdList = new ArrayList<>();
        bdList.add(zhgdDictSg);
        bdList.add(zhgdDictJl);
        bdList.add(zhgdDictJc);

        JSONObject objectMaterial = new JSONObject();
        objectMaterial.put("shuini1", 0);
        objectMaterial.put("shuini2", 0);
        objectMaterial.put("shuini3", 0);
        objectMaterial.put("sjgl1", 0);
        objectMaterial.put("sjgl2", 0);
        objectMaterial.put("sjgl3", 0);
        objectMaterial.put("sha1", 0);
        objectMaterial.put("waijiaji1", 0);
        objectMaterial.put("waijiaji2", 0);
        objectMaterial.put("meihui1", 0);
        objectMaterial.put("meihui2", 0);
        for (ZhgdDict zhgdDict : bdList) {
            String bdValue = zhgdDict.getDataValue();
            net.sf.json.JSONObject object1 = HttpClientUtil.httpGet(QUERY_URL + "?proId="
                    + MaterialConstant.GK_PROJECTID + "&sysId=3&mp_num=" + bdValue + "&startTime=" + startTime + "&endTime=" + endTime);
            if (null == object1) {
                continue;
            }
            double oshuini1 = Double.parseDouble(String.valueOf(objectMaterial.get("shuini1")));
            double o1shuini1 = Double.parseDouble(String.valueOf(object1.get("shuini1")));
            objectMaterial.put("shuini1",  oshuini1 + o1shuini1);

            double oshuini2 = Double.parseDouble(String.valueOf(objectMaterial.get("shuini2")));
            double o1shuini2 = Double.parseDouble(String.valueOf(object1.get("shuini2")));
            objectMaterial.put("shuini2",  oshuini2 + o1shuini2);

            double oshuini3 = Double.parseDouble(String.valueOf(objectMaterial.get("shuini3")));
            double o1shuini3 = Double.parseDouble(String.valueOf(object1.get("shuini3")));
            objectMaterial.put("shuini3",  oshuini3 + o1shuini3);

            double osjgl1 = Double.parseDouble(String.valueOf(objectMaterial.get("sjgl1")));
            double o1sjgl1 = Double.parseDouble(String.valueOf(object1.get("sjgl1")));
            objectMaterial.put("sjgl1",  osjgl1 + o1sjgl1);

            double osjgl2 = Double.parseDouble(String.valueOf(objectMaterial.get("sjgl2")));
            double o1sjgl2 = Double.parseDouble(String.valueOf(object1.get("sjgl2")));
            objectMaterial.put("sjgl2",  osjgl2 + o1sjgl2);

            double osjgl3 = Double.parseDouble(String.valueOf(objectMaterial.get("sjgl3")));
            double o1sjgl3 = Double.parseDouble(String.valueOf(object1.get("sjgl3")));
            objectMaterial.put("sjgl3",  osjgl3 + o1sjgl3);

            double osha1 = Double.parseDouble(String.valueOf(objectMaterial.get("sha1")));
            double o1sha1 = Double.parseDouble(String.valueOf(object1.get("sha1")));
            objectMaterial.put("sha1",  osha1 + o1sha1);

            double owaijiaji1 = Double.parseDouble(String.valueOf(objectMaterial.get("waijiaji1")));
            double o1waijiaji1 = Double.parseDouble(String.valueOf(object1.get("waijiaji1")));
            objectMaterial.put("waijiaji1",  owaijiaji1 + o1waijiaji1);

            double owaijiaji2 = Double.parseDouble(String.valueOf(objectMaterial.get("waijiaji2")));
            double o1waijiaji2 = Double.parseDouble(String.valueOf(object1.get("waijiaji2")));
            objectMaterial.put("waijiaji2",  owaijiaji2 + o1waijiaji2);

            double omeihui1 = Double.parseDouble(String.valueOf(objectMaterial.get("meihui1")));
            double o1meihui1 = Double.parseDouble(String.valueOf(object1.get("meihui1")));
            objectMaterial.put("meihui1",  omeihui1 + o1meihui1);

            double omeihui2 = Double.parseDouble(String.valueOf(objectMaterial.get("meihui2")));
            double o1meihui2 = Double.parseDouble(String.valueOf(object1.get("meihui2")));
            objectMaterial.put("meihui2",  omeihui2 + o1meihui2);
        }


        DecimalFormat df = new DecimalFormat("#.00");
        for (int i = 0; i < stockTypeList.size(); i++) {
            boolean flag1 = true;
            for (ZhgdMaterialStockRecord stockData : stockdataList) {
                if (stockTypeList.get(i).equals(stockData.getMaterialName())) {
                    remain.add(stockData.getSumNumber());
                    useNum.add(DoubleUtil.sub(allNum.get(i), stockData.getSumNumber()));
                    flag1 = false;
                }
            }
            if (flag1) {
                remain.add(0.0);
                useNum.add(0.0);
            }
            String materialName = stockTypeList.get(i);
            Double lowerNumber;
            if (DictionaryEnum.REBAR.getValue().equals(materialName)) {
                avgNum.add(0.0);
            }
            if (DictionaryEnum.CEMENT.getValue().equals(materialName)) {
                // 水泥
                double shuini1 = Double.parseDouble(String.valueOf(objectMaterial.get("shuini1")));
                double shuini2 = Double.parseDouble(String.valueOf(objectMaterial.get("shuini2")));
                double shuini3 = Double.parseDouble(String.valueOf(objectMaterial.get("shuini3")));
                double sumshuini = shuini1 + shuini2 + shuini3;
                lowerNumber = sumshuini / 1000;
                avgNum.add(Double.valueOf(df.format(lowerNumber)));
            }
            if (DictionaryEnum.GRAVEL.getValue().equals(materialName)) {
                // 碎石
                double sjgl1 = Double.parseDouble(String.valueOf(objectMaterial.get("sjgl1")));
                double sjgl2 = Double.parseDouble(String.valueOf(objectMaterial.get("sjgl2")));
                double sjgl3 = Double.parseDouble(String.valueOf(objectMaterial.get("sjgl3")));
                double sumsjgl = sjgl1 + sjgl2 + sjgl3;
                lowerNumber = sumsjgl / 1000;
                avgNum.add(Double.valueOf(df.format(lowerNumber)));
            }
            if (DictionaryEnum.YELLOW_SAND.getValue().equals(materialName)) {
                // 黄沙
                double sha1 = Double.parseDouble(String.valueOf(objectMaterial.get("sha1")));
                lowerNumber = sha1 / 1000;
                avgNum.add(Double.valueOf(df.format(lowerNumber)));
            }
            if (DictionaryEnum.ADMIXTURE.getValue().equals(materialName)) {
                // 外加剂
                double waijiaji1 = Double.parseDouble(String.valueOf(objectMaterial.get("waijiaji1")));
                double waijiaji2 = Double.parseDouble(String.valueOf(objectMaterial.get("waijiaji2")));
                double sumwaijiaji = waijiaji1 + waijiaji2;
                lowerNumber = sumwaijiaji / 1000;
                avgNum.add(Double.valueOf(df.format(lowerNumber)));
            }
            if (DictionaryEnum.FLY_ASH.getValue().equals(materialName)) {
                // 煤灰
                double meihui1 = Double.parseDouble(String.valueOf(objectMaterial.get("meihui1")));
                double meihui2 = Double.parseDouble(String.valueOf(objectMaterial.get("meihui2")));
                double summeihui = meihui1 + meihui2;
                lowerNumber = summeihui / 1000;
                avgNum.add(Double.valueOf(df.format(lowerNumber)));
            }

        }

        resultMap.put("allNum", allNum);
        resultMap.put("remain", remain);
        resultMap.put("useNum", useNum);
        resultMap.put("avgNum", avgNum);
        resultMap.put("stockType", stockTypeDesc);
        return resultMap;
    }

    @Override
    public Map<String, Object> getStockPieNew(Long deptId) {
        Map<String, Object> resultMap = new HashMap<>();
        List<String> stockTypeList = new ArrayList<String>();

        stockTypeList.add(DictionaryEnum.REBAR.getValue());
        stockTypeList.add(DictionaryEnum.CEMENT.getValue());
        stockTypeList.add(DictionaryEnum.GRAVEL.getValue());
        stockTypeList.add(DictionaryEnum.YELLOW_SAND.getValue());
        stockTypeList.add(DictionaryEnum.ADMIXTURE.getValue());
        stockTypeList.add(DictionaryEnum.MJJP.getValue());
        stockTypeList.add(DictionaryEnum.GJX.getValue());
        stockTypeList.add(DictionaryEnum.SH.getValue());
        stockTypeList.add(DictionaryEnum.XJZZ.getValue());
        stockTypeList.add(DictionaryEnum.SSZZ.getValue());
        stockTypeList.add(DictionaryEnum.BWG.getValue());
        stockTypeList.add(DictionaryEnum.GJWP.getValue());


        List<String> stockTypeDesc = new ArrayList<String>();
        stockTypeDesc.add(DictionaryEnum.REBAR.getName());
        stockTypeDesc.add(DictionaryEnum.CEMENT.getName());
        stockTypeDesc.add(DictionaryEnum.GRAVEL.getName());
        stockTypeDesc.add(DictionaryEnum.YELLOW_SAND.getName());
        stockTypeDesc.add(DictionaryEnum.ADMIXTURE.getName());
        stockTypeDesc.add(DictionaryEnum.MJJP.getName());
        stockTypeDesc.add(DictionaryEnum.GJX.getName());
        stockTypeDesc.add(DictionaryEnum.SH.getName());
        stockTypeDesc.add(DictionaryEnum.XJZZ.getName());
        stockTypeDesc.add(DictionaryEnum.SSZZ.getName());
        stockTypeDesc.add(DictionaryEnum.BWG.getName());
        stockTypeDesc.add(DictionaryEnum.GJWP.getName());

        // 分组获得审核数据，当某个批次有一次审核不通过的则为不通过
        List<ZhgdMaterialDetection> xyDRByNameAndResultAndBatch = zhgdMaterialDetectionService.queryGroupByNameAndResultAndBatch(deptId);

        // 分组获得审核数据，当某个批次有一次审核不通过的则为不通过
        List<ZhgdMaterialDetection> xyDRNoPass = zhgdMaterialDetectionService.queryGroupByNameAndResultAndBatchNoPass(deptId);

        // 获取物料名称与批次,总数
        List<ZhgdMaterialDetection> xyDRByNameAndBatch = zhgdMaterialDetectionService.queryGroupByNameAndBatch(deptId);

        // 十二个百分比
        List<Double> percentageList = new ArrayList<Double>();


        for (String stockType : stockTypeList) {
            // 不合格数量
            Integer num = 0;
            for (ZhgdMaterialDetection xyDetectionResult : xyDRByNameAndResultAndBatch) {
                if (stockType.equals(xyDetectionResult.getMaterialName())) {
                    for (ZhgdMaterialDetection nopass : xyDRNoPass) {
                        if (xyDetectionResult.getMaterialName().equals(nopass.getMaterialName())
                                && xyDetectionResult.getInstockBatch().equals(nopass.getInstockBatch())) {
                            num++;
                        }
                    }
                }
            }

            if (num == 0) {
                percentageList.add(1.0);
            } else {
                for (ZhgdMaterialDetection count : xyDRByNameAndBatch) {
                    if (stockType.equals(count.getMaterialName())) {
                        percentageList.add(DoubleUtil.div(count.getCount() - num, count.getCount(), 3));
                    }
                }
            }

        }

        resultMap.put("percentageList", percentageList);
        resultMap.put("stockType", stockTypeDesc);
        return resultMap;

    }
}
