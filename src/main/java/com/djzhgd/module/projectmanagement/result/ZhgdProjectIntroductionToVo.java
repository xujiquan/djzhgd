package com.djzhgd.module.projectmanagement.result;

import com.djzhgd.module.projectmanagement.domain.ZhgdParticipatingUnits;
import com.djzhgd.module.projectmanagement.domain.ZhgdProjectIntroduction;
import com.djzhgd.module.projectmanagement.vo.ZhgdParticipatingUnitsVo;
import com.djzhgd.module.projectmanagement.vo.ZhgdProjectIntroductionVo;
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
public class ZhgdProjectIntroductionToVo {

    // 数据库查询的数据转换成Vo层（数据处理），返回到页面
    public static List<ZhgdProjectIntroductionVo> ZhgdProjectIntroductionToVo(List<ZhgdProjectIntroduction> zhgdProjectIntroductionList){
        List<ZhgdProjectIntroductionVo> zhgdProjectIntroductionVoList = new ArrayList<>();
        ZhgdProjectIntroductionVo zhgdProjectIntroductionVo = null;
        if(zhgdProjectIntroductionList != null && zhgdProjectIntroductionList.size() > 0){
            for(ZhgdProjectIntroduction zhgdProjectIntroduction : zhgdProjectIntroductionList){
                zhgdProjectIntroductionVo =  new  ZhgdProjectIntroductionVo();
                zhgdProjectIntroductionVo.setIdVo(ObjectToString.othTrunString(zhgdProjectIntroduction.getId()));
                zhgdProjectIntroductionVo.setDeptIdVo(ObjectToString.othTrunString(zhgdProjectIntroduction.getDeptId()));
                zhgdProjectIntroductionVo.setTenantIdVo(ObjectToString.othTrunString(zhgdProjectIntroduction.getTenantId()));
                zhgdProjectIntroductionVo.setUserIdVo(ObjectToString.othTrunString(zhgdProjectIntroduction.getUserId()));
                zhgdProjectIntroductionVo.setProjectIntroductionVo(ObjectToString.othTrunString(zhgdProjectIntroduction.getProjectIntroduction()));
                zhgdProjectIntroductionVo.setAddUserIdVo(ObjectToString.othTrunString(zhgdProjectIntroduction.getAddUserId()));
                zhgdProjectIntroductionVo.setDisabledVo(DateUtil.getYYYYMMDDHHMMSS(zhgdProjectIntroduction.getTime()));
                zhgdProjectIntroductionVo.setDisabledVo(ObjectToString.othTrunString(zhgdProjectIntroduction.getDisabled()));
                zhgdProjectIntroductionVo.setAddUsernameVo(ObjectToString.othTrunString(zhgdProjectIntroduction.getAddUsername()));
                zhgdProjectIntroductionVo.setCreateUseridVo(ObjectToString.othTrunString(zhgdProjectIntroduction.getCreateUserid()));
                zhgdProjectIntroductionVo.setCreateUsernameVo(ObjectToString.othTrunString(zhgdProjectIntroduction.getCreateUsername()));
                zhgdProjectIntroductionVo.setCreateIpVo(ObjectToString.othTrunString(zhgdProjectIntroduction.getCreateIp()));
                zhgdProjectIntroductionVo.setUpdateUseridVo(ObjectToString.othTrunString(zhgdProjectIntroduction.getUpdateUserid()));
                zhgdProjectIntroductionVo.setUpdateUsernameVo(ObjectToString.othTrunString(zhgdProjectIntroduction.getUpdateUsername()));
                zhgdProjectIntroductionVo.setUpdateIpVo(ObjectToString.othTrunString(zhgdProjectIntroduction.getUpdateIp()));
//                zhgdProjectIntroductionVo.setVersionVo(ObjectToString.othTrunString(zhgdProjectIntroduction.getVersion()));
                if(!StringUtils.isEmpty(zhgdProjectIntroduction.getCreateDatetime())){
                    zhgdProjectIntroductionVo.setCreateDatetimeVo(DateUtil.getYYYYMMDDHHMMSS(zhgdProjectIntroduction.getCreateDatetime()));
                }
                if(!StringUtils.isEmpty(zhgdProjectIntroduction.getUpdateDatetime())){
                    zhgdProjectIntroductionVo.setUpdateDatetimeVo(DateUtil.getYYYYMMDDHHMMSS(zhgdProjectIntroduction.getUpdateDatetime()));
                }
                zhgdProjectIntroductionVoList.add(zhgdProjectIntroductionVo);
            }
        }
        return zhgdProjectIntroductionVoList;
    }
    // 页面新增或者修改后的数据，转成实体类，保存到数据库
    public static ZhgdProjectIntroduction ZhgdProjectIntroductionToVoEntity(ZhgdProjectIntroductionVo zhgdProjectIntroductionVo) throws ParseException {
        ZhgdProjectIntroduction zhgdProjectIntroduction = null;
        if(zhgdProjectIntroductionVo != null){
            zhgdProjectIntroduction = new ZhgdProjectIntroduction();
            if(ObjectToString.strIsNotNull(zhgdProjectIntroductionVo.getIdVo())){
                zhgdProjectIntroduction.setId(Integer.valueOf(zhgdProjectIntroductionVo.getIdVo()));
            }
            if(ObjectToString.strIsNotNull(zhgdProjectIntroductionVo.getDeptIdVo())){
                zhgdProjectIntroduction.setDeptId(Integer.valueOf(zhgdProjectIntroductionVo.getDeptIdVo()));
            }
            if(ObjectToString.strIsNotNull(zhgdProjectIntroductionVo.getTenantIdVo())){
                zhgdProjectIntroduction.setTenantId(Integer.valueOf(zhgdProjectIntroductionVo.getTenantIdVo()));
            }
            if(ObjectToString.strIsNotNull(zhgdProjectIntroductionVo.getProjectIntroductionVo())){
                zhgdProjectIntroduction.setProjectIntroduction(zhgdProjectIntroductionVo.getProjectIntroductionVo());
            }
            if(ObjectToString.strIsNotNull(zhgdProjectIntroductionVo.getAddUserIdVo())){
                zhgdProjectIntroduction.setAddUserId(zhgdProjectIntroductionVo.getAddUserIdVo());
            }
            if(ObjectToString.strIsNotNull(zhgdProjectIntroductionVo.getAddUsernameVo())){
                zhgdProjectIntroduction.setAddUsername(zhgdProjectIntroductionVo.getAddUsernameVo());
            }
            if(ObjectToString.strIsNotNull(zhgdProjectIntroductionVo.getTimeVo())){
                zhgdProjectIntroduction.setTime(DateUtil.getYYYYMMDDHHMMSS(zhgdProjectIntroductionVo.getTimeVo()));
            }
            if(ObjectToString.strIsNotNull(zhgdProjectIntroductionVo.getDisabledVo())){
                zhgdProjectIntroduction.setDisabled(Integer.valueOf(zhgdProjectIntroductionVo.getDisabledVo()));
            }
            if(ObjectToString.strIsNotNull(zhgdProjectIntroductionVo.getCreateUseridVo())){
                zhgdProjectIntroduction.setCreateUserid(Integer.valueOf(zhgdProjectIntroductionVo.getCreateUseridVo()));
            }
            if(ObjectToString.strIsNotNull(zhgdProjectIntroductionVo.getCreateUsernameVo())){
                zhgdProjectIntroduction.setCreateUsername(zhgdProjectIntroductionVo.getCreateUsernameVo());
            }
            if(ObjectToString.strIsNotNull(zhgdProjectIntroductionVo.getCreateIpVo())){
                zhgdProjectIntroduction.setCreateIp(zhgdProjectIntroductionVo.getCreateIpVo());
            }

            if(ObjectToString.strIsNotNull(zhgdProjectIntroductionVo.getUpdateUseridVo())){
                zhgdProjectIntroduction.setUpdateUserid(Integer.valueOf(zhgdProjectIntroductionVo.getUpdateUseridVo()));
            }
            if(ObjectToString.strIsNotNull(zhgdProjectIntroductionVo.getUpdateUsernameVo())){
                zhgdProjectIntroduction.setUpdateUsername(zhgdProjectIntroductionVo.getUpdateUsernameVo());
            }
            if(ObjectToString.strIsNotNull(zhgdProjectIntroductionVo.getUpdateIpVo())){
                zhgdProjectIntroduction.setUpdateIp(zhgdProjectIntroductionVo.getUpdateIpVo());
            }

            if(ObjectToString.strIsNotNull(zhgdProjectIntroductionVo.getUpdateDatetimeVo())){
                zhgdProjectIntroduction.setUpdateDatetime(DateUtil.getYYYYMMDDHHMMSS(zhgdProjectIntroductionVo.getUpdateDatetimeVo()));
            }
            if(ObjectToString.strIsNotNull(zhgdProjectIntroductionVo.getCreateDatetimeVo())){
                zhgdProjectIntroduction.setCreateDatetime(DateUtil.getYYYYMMDDHHMMSS(zhgdProjectIntroductionVo.getCreateDatetimeVo()));
            }
        }
        return zhgdProjectIntroduction;
    }


}
