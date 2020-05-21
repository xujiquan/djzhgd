/**
 * Description: ZhgdMeetingSign页面表单对象
 * Copyright:   Copyright (c)2020
 * Company:     envbase
 * @author:     caoyx
 * @version:    1.0
 * Create at:   2020-05-19 下午 14:52:14
 *  
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2020-05-19   caoyx   1.0         Initial
 */
package com.djzhgd.module.projectmanagement.vo;


import com.djzhgd.module.projectmanagement.domain.ZhgdMeetingSign;
import lombok.Data;

/**
 * ZhgdMeetingSign页面表单对象<br>
 * 
 * @author caoyx
 * @version 1.0, 2020-05-19
 * @see
 * @since 1.0
 */
@Data
public class ZhgdMeetingSignVo extends ZhgdMeetingSign {
	/*private String id;

	*//**
	 * 公司ID 或 项目ID 或 标段ID
	 *//*
	//@NotNull
	private String deptId;

	*//**
	 * 住户id
	 *//*
	//@NotNull
	private Long tenantId;

	*//**
	 * 会议id
	 *//*
	//@Length(max=100)
	private String meetingId;

	*//**
	 * 签到人
	 *//*
	//@Length(max=100)
	private String signName;

	*//**
	 * 签到公司
	 *//*
	//@Length(max=100)
	private String signCompany;

	*//**
	 * 是否有效，0 – 有效，1 – 无效
	 *//*
	//
	private String disabled;

	*//**
	 * 创建人用户ID
	 *//*
	//@Length(max=11)
	private String createUserid;

	*//**
	 * 创建人用户名
	 *//*
	//@Length(max=100)
	private String createUsername;

	*//**
	 * 创建人ip
	 *//*
	//@Length(max=100)
	private String createIp;

	*//**
	 * 创建时间
	 *//*
	//
	private String createDatetime;

	*//**
	 * 最后一次修改人用户ID
	 *//*
	//@Length(max=11)
	private String updateUserid;

	*//**
	 * 最后一次修改人用户名
	 *//*
	//@Length(max=100)
	private String updateUsername;

	*//**
	 * 最后一次修改人ip
	 *//*
	//@Length(max=100)
	private String updateIp;

	*//**
	 * 最后一次修改时间
	 *//*
	//
	private String updateDatetime;*/
}