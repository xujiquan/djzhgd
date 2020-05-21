/**
 * Description: ZhgdMeetingSign数据库实体/领域对象
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
package com.djzhgd.module.projectmanagement.domain;

//import javax.validation.constraints.*;
//import org.hibernate.validator.constraints.*;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * ZhgdMeetingSign数据库实体/领域对象<br>
 * 
 * @author caoyx
 * @version 1.0, 2020-05-19
 * @see
 * @since 1.0
 */
@Data
@TableName("ZHGD_MEETING_SIGN")
public class ZhgdMeetingSign implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/**
	 * id
	 */
	//
	private Long id;
	
	/**
	 * 公司ID 或 项目ID 或 标段ID 
	 */
	//@NotNull 
	private Long deptId;
	
	/**
	 * 住户id
	 */
	//@NotNull 
	private Long tenantId;
	
	/**
	 * 会议id
	 */
	//@Length(max=100)
	private String meetingId;
	
	/**
	 * 签到人
	 */
	//@Length(max=100)
	private String signName;
	
	/**
	 * 签到公司
	 */
	//@Length(max=100)
	private String signCompany;
	
	/**
	 * 是否有效，0 – 有效，1 – 无效
	 */
	//
	private Integer disabled;
	
	/**
	 * 创建人用户ID
	 */
	//@Length(max=11)
	private String createUserid;
	
	/**
	 * 创建人用户名
	 */
	//@Length(max=100)
	private String createUsername;
	
	/**
	 * 创建人ip
	 */
	//@Length(max=100)
	private String createIp;
	
	/**
	 * 创建时间
	 */
	//
	private java.util.Date createDatetime;
	
	/**
	 * 最后一次修改人用户ID
	 */
	//@Length(max=11)
	private String updateUserid;
	
	/**
	 * 最后一次修改人用户名
	 */
	//@Length(max=100)
	private String updateUsername;
	
	/**
	 * 最后一次修改人ip
	 */
	//@Length(max=100)
	private String updateIp;
	
	/**
	 * 最后一次修改时间
	 */
	//
	private java.util.Date updateDatetime;
	

}

