package com.djzhgd.module.projectmanagement.result;

import com.djzhgd.module.projectmanagement.domain.ZhgdParticipatingUnits;
import com.djzhgd.module.projectmanagement.vo.ZhgdParticipatingUnitsVo;
import com.djzhgd.project.utils.DateUtil;
import com.djzhgd.project.utils.ObjectToString;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @PROJECT_NAME: dj_zhgd 东交智慧品控项目
 * @ClassName: EntityToVo 实体类和VO层转换
 * @Author: zhangheng
 * @DATE: 2020/5/13 11:51
 * @Version 1.0
 **/
public class ZhgdParticipatingUnitsToVo {

    // 数据库查询的数据转换成Vo层（数据处理），返回到页面
    public static List<ZhgdParticipatingUnitsVo> DemoToVo(List<ZhgdParticipatingUnits> zhgdParticipatingUnitsList){
        List<ZhgdParticipatingUnitsVo> ZhgdParticipatingUnitsVoList = new ArrayList<>();
        ZhgdParticipatingUnitsVo zhgdParticipatingUnitsVo = null;
        if(zhgdParticipatingUnitsList != null && zhgdParticipatingUnitsList.size() > 0){
            for(ZhgdParticipatingUnits zhgdParticipatingUnits : zhgdParticipatingUnitsList){
                zhgdParticipatingUnitsVo =  new  ZhgdParticipatingUnitsVo();
                zhgdParticipatingUnitsVo.setIdVo(ObjectToString.othTrunString(zhgdParticipatingUnits.getId()));
                zhgdParticipatingUnitsVo.setDeptIdVo(ObjectToString.othTrunString(zhgdParticipatingUnits.getDeptId()));
                zhgdParticipatingUnitsVo.setTenantIdVo(ObjectToString.othTrunString(zhgdParticipatingUnits.getTenantId()));
                zhgdParticipatingUnitsVo.setUnitNameVo(ObjectToString.othTrunString(zhgdParticipatingUnits.getUnitName()));
                zhgdParticipatingUnitsVo.setUnitTypeVo(ObjectToString.othTrunString(zhgdParticipatingUnits.getUnitType()));
                zhgdParticipatingUnitsVo.setUnitDutyVo(ObjectToString.othTrunString(zhgdParticipatingUnits.getUnitDuty()));
                zhgdParticipatingUnitsVo.setDisabledVo(ObjectToString.othTrunString(zhgdParticipatingUnits.getDisabled()));
                zhgdParticipatingUnitsVo.setCreateUseridVo(ObjectToString.othTrunString(zhgdParticipatingUnits.getCreateUserid()));
                zhgdParticipatingUnitsVo.setCreateUsernameVo(ObjectToString.othTrunString(zhgdParticipatingUnits.getCreateUsername()));
                zhgdParticipatingUnitsVo.setCreateIpVo(ObjectToString.othTrunString(zhgdParticipatingUnits.getCreateIp()));
                zhgdParticipatingUnitsVo.setUpdateUseridVo(ObjectToString.othTrunString(zhgdParticipatingUnits.getUpdateUserid()));
                zhgdParticipatingUnitsVo.setUpdateUsernameVo(ObjectToString.othTrunString(zhgdParticipatingUnits.getUpdateUsername()));
                zhgdParticipatingUnitsVo.setUpdateIpVo(ObjectToString.othTrunString(zhgdParticipatingUnits.getUpdateIp()));
                zhgdParticipatingUnitsVo.setVersionVo(ObjectToString.othTrunString(zhgdParticipatingUnits.getVersion()));
                if(!StringUtils.isEmpty(zhgdParticipatingUnits.getCreateDatetime())){
                    zhgdParticipatingUnitsVo.setCreateDatetimeVo(DateUtil.getYYYYMMDDHHMMSS(zhgdParticipatingUnits.getCreateDatetime()));
                }
                if(!StringUtils.isEmpty(zhgdParticipatingUnits.getUpdateDatetime())){
                    zhgdParticipatingUnitsVo.setUpdateDatetimeVo(DateUtil.getYYYYMMDDHHMMSS(zhgdParticipatingUnits.getUpdateDatetime()));
                }
                ZhgdParticipatingUnitsVoList.add(zhgdParticipatingUnitsVo);
            }
        }
        return ZhgdParticipatingUnitsVoList;
    }
    // 页面新增或者修改后的数据，转成实体类，保存到数据库
    public static ZhgdParticipatingUnits DemoVoToEntity(ZhgdParticipatingUnitsVo zhgdParticipatingUnitsVo) throws ParseException {
        ZhgdParticipatingUnits zhgdParticipatingUnits = null;
        if(zhgdParticipatingUnitsVo != null){
            zhgdParticipatingUnits = new ZhgdParticipatingUnits();
            if(ObjectToString.strIsNotNull(zhgdParticipatingUnitsVo.getIdVo())){
                zhgdParticipatingUnits.setId(Integer.valueOf(zhgdParticipatingUnitsVo.getIdVo()));
            }
            if(ObjectToString.strIsNotNull(zhgdParticipatingUnitsVo.getDeptIdVo())){
                zhgdParticipatingUnits.setDeptId(Integer.valueOf(zhgdParticipatingUnitsVo.getDeptIdVo()));
            }
            if(ObjectToString.strIsNotNull(zhgdParticipatingUnitsVo.getTenantIdVo())){
                zhgdParticipatingUnits.setTenantId(Integer.valueOf(zhgdParticipatingUnitsVo.getTenantIdVo()));
            }
            if(ObjectToString.strIsNotNull(zhgdParticipatingUnitsVo.getUnitNameVo())){
                zhgdParticipatingUnits.setUnitName(zhgdParticipatingUnitsVo.getUnitNameVo());
            }
            if(ObjectToString.strIsNotNull(zhgdParticipatingUnitsVo.getUnitTypeVo())){
                zhgdParticipatingUnits.setUnitType(Integer.valueOf(zhgdParticipatingUnitsVo.getUnitTypeVo()));
            }
            if(ObjectToString.strIsNotNull(zhgdParticipatingUnitsVo.getUnitDutyVo())){
                zhgdParticipatingUnits.setUnitDuty(zhgdParticipatingUnitsVo.getUnitDutyVo());
            }
            if(ObjectToString.strIsNotNull(zhgdParticipatingUnitsVo.getDisabledVo())){
                zhgdParticipatingUnits.setDisabled(Integer.valueOf(zhgdParticipatingUnitsVo.getDisabledVo()));
            }
            if(ObjectToString.strIsNotNull(zhgdParticipatingUnitsVo.getCreateUseridVo())){
                zhgdParticipatingUnits.setCreateUserid(Integer.valueOf(zhgdParticipatingUnitsVo.getCreateUseridVo()));
            }
            if(ObjectToString.strIsNotNull(zhgdParticipatingUnitsVo.getCreateUsernameVo())){
                zhgdParticipatingUnits.setCreateUsername(zhgdParticipatingUnitsVo.getCreateUsernameVo());
            }
            if(ObjectToString.strIsNotNull(zhgdParticipatingUnitsVo.getCreateIpVo())){
                zhgdParticipatingUnits.setCreateIp(zhgdParticipatingUnitsVo.getCreateIpVo());
            }

            if(ObjectToString.strIsNotNull(zhgdParticipatingUnitsVo.getUpdateUseridVo())){
                zhgdParticipatingUnits.setUpdateUserid(Integer.valueOf(zhgdParticipatingUnitsVo.getUpdateUseridVo()));
            }
            if(ObjectToString.strIsNotNull(zhgdParticipatingUnitsVo.getUpdateUsernameVo())){
                zhgdParticipatingUnits.setUpdateUsername(zhgdParticipatingUnitsVo.getUpdateUsernameVo());
            }
            if(ObjectToString.strIsNotNull(zhgdParticipatingUnitsVo.getUpdateIpVo())){
                zhgdParticipatingUnits.setUpdateIp(zhgdParticipatingUnitsVo.getUpdateIpVo());
            }
            if(ObjectToString.strIsNotNull(zhgdParticipatingUnitsVo.getVersionVo())){
                zhgdParticipatingUnits.setVersion(Integer.valueOf(zhgdParticipatingUnitsVo.getVersionVo()));
            }
            if(ObjectToString.strIsNotNull(zhgdParticipatingUnitsVo.getUpdateDatetimeVo())){
                zhgdParticipatingUnits.setUpdateDatetime(DateUtil.getYYYYMMDDHHMMSS(zhgdParticipatingUnitsVo.getUpdateDatetimeVo()));
            }
            if(ObjectToString.strIsNotNull(zhgdParticipatingUnitsVo.getCreateDatetimeVo())){
                zhgdParticipatingUnits.setCreateDatetime(DateUtil.getYYYYMMDDHHMMSS(zhgdParticipatingUnitsVo.getCreateDatetimeVo()));
            }
        }
        return zhgdParticipatingUnits;
    }


}
