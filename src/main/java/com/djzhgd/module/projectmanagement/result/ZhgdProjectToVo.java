package com.djzhgd.module.projectmanagement.result;

import com.djzhgd.module.projectmanagement.domain.ZhgdMeetingContent;
import com.djzhgd.module.projectmanagement.domain.Zhgdmeetingagenda;
import com.djzhgd.module.projectmanagement.vo.ZhgdMeetingContentVo;
import com.djzhgd.module.projectmanagement.vo.ZhgdmeetingagendaVo;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
/**
 * @PROJECT_NAME: dj_zhgd 东交智慧品控项目
 * @ClassName: ZhgdProjectToVo 实体类和VO层转换 --项目管理模块用
 * @Author: shenjianann
 * @DATE: 2020/5/13 11:51
 * @Version 1.0
 **/
public class ZhgdProjectToVo {


    // 项目会议表--数据库查询的数据转换成Vo层（数据处理），返回到页面
    public static List<ZhgdmeetingagendaVo> ZhgdmeetingagendaToVo(List<Zhgdmeetingagenda> ZhgdmeetingagendList) {
        List<ZhgdmeetingagendaVo> VoList = new ArrayList<>();
        ZhgdmeetingagendaVo zhgdmeetingagendavo = null;
        if (ZhgdmeetingagendList != null && ZhgdmeetingagendList.size() > 0) {
            for (Zhgdmeetingagenda zhgdmeetingagenda : ZhgdmeetingagendList) {
                zhgdmeetingagendavo = new ZhgdmeetingagendaVo();
                zhgdmeetingagendavo.setId(zhgdmeetingagenda.getId());
                if (!StringUtils.isEmpty(zhgdmeetingagenda.getCreateDatetime())) {
                    zhgdmeetingagendavo.setCreateDatetime(zhgdmeetingagenda.getCreateDatetime());
                }
                if (!StringUtils.isEmpty(zhgdmeetingagenda.getUpdateDatetime())) {
                    zhgdmeetingagendavo.setUpdateDatetime(zhgdmeetingagenda.getUpdateDatetime());
                }
                VoList.add(zhgdmeetingagendavo);
            }
        }
        return VoList;
    }

    // 项目会议表--页面新增或者修改后的数据，转成实体类，保存到数据库
//    public static Zhgdmeetingagenda ZhgdmeetingagendaVoToEntity(ZhgdmeetingagendaVo zhgdmeetingagendavo) throws ParseException {
//        Zhgdmeetingagenda zhgdmeetingagenda = null;
//        if (zhgdmeetingagendavo != null) {
//            zhgdmeetingagenda = new Zhgdmeetingagenda();
//            if (ObjectToString.strIsNotNull(zhgdmeetingagendavo.getIdVo())) {
//                BigDecimal bd = new BigDecimal(zhgdmeetingagendavo.getIdVo());
//                zhgdmeetingagenda.setId(bd);
//            }
//
//        }
//        return zhgdmeetingagenda;
//    }

    // 项目会议表--页面新增或者修改后的数据，转成实体类，保存到数据库
    public static ZhgdMeetingContent ZhgdMeetingContentVoToEntity(ZhgdMeetingContentVo zhgdmeetingcontentvo) throws ParseException {
        ZhgdMeetingContent zhgdmeetingcontent = null;
        if (zhgdmeetingcontentvo != null) {
            zhgdmeetingcontent = new ZhgdMeetingContent();
           /* if (ObjectToString.strIsNotNull(zhgdmeetingcontentvo.getIdVo())) {
                zhgdmeetingcontent.setId(Integer.valueOf(zhgdmeetingcontentvo.getIdVo()));
            }
            if (ObjectToString.strIsNotNull(zhgdmeetingcontentvo.getDeptIdVo())) {
                zhgdmeetingcontent.setDeptId(Integer.valueOf(zhgdmeetingcontentvo.getDeptIdVo()));
            }
            if (ObjectToString.strIsNotNull(zhgdmeetingcontentvo.getTenantIdVo())) {
                zhgdmeetingcontent.setTenantId(Integer.valueOf(zhgdmeetingcontentvo.getTenantIdVo()));
            }
            if (ObjectToString.strIsNotNull(zhgdmeetingcontentvo.getInitiatorVo())) {
                zhgdmeetingcontent.setInitiatorId(zhgdmeetingcontentvo.getInitiatorIdVo());
            }

            if (ObjectToString.strIsNotNull(zhgdmeetingcontentvo.getMeetingTitleVo())) {
                zhgdmeetingcontent.setMeetingTitle(zhgdmeetingcontentvo.getMeetingTitleVo());
            }
            if (ObjectToString.strIsNotNull(zhgdmeetingcontentvo.getMeetingTimeVo())) {
                zhgdmeetingcontent.setMeetingTime(zhgdmeetingcontentvo.getMeetingTimeVo());
            }
            if (ObjectToString.strIsNotNull(zhgdmeetingcontentvo.getDisabledVo())) {
                zhgdmeetingcontent.setDisabled(Integer.valueOf(zhgdmeetingcontentvo.getDisabledVo()));
            }
            if (ObjectToString.strIsNotNull(zhgdmeetingcontentvo.getCreateDatetimeVo())) {
                zhgdmeetingcontent.setCreateDatetime(DateUtil.getYYYYMMDDHHMMSS(zhgdmeetingcontentvo.getCreateDatetimeVo()));
            }
            if (ObjectToString.strIsNotNull(zhgdmeetingcontentvo.getUpdateDatetimeVo())) {
                zhgdmeetingcontent.setUpdateDatetime(new Date());
            }*/
        }
        return zhgdmeetingcontent;

    }
}
