/**
 * Description: ZhgdMeetingContent数据库实体/领域对象
 * Copyright:   Copyright (c)2020
 * Company:     envbase
 * @author:     caoyx
 * @version:    1.0
 * Create at:   2020-05-19 下午 14:52:13
 *  
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2020-05-19   caoyx   1.0         Initial
 */
package com.djzhgd.module.projectmanagement.domain;

//import javax.validation.constraints.*;
//import org.hibernate.validator.constraints.*;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * ZhgdMeetingContent数据库实体/领域对象<br>
 * 
 * @author caoyx
 * @version 1.0, 2020-05-19
 * @see
 * @since 1.0
 */
@Data
@TableName("ZHGD_MEETING_CONTENT")
public class ZhgdMeetingContent {
	/**
	 * id
	 */
	//
	private Integer id;
	
	/**
	 * 公司ID 或 项目ID 或 标段ID 
	 */
	//@NotNull 
	private Integer deptId;
	
	/**
	 * 住户id
	 */
	//@NotNull 
	private Integer tenantId;
	
	/**
	 * 发起人
	 */
	//@Length(max=100)
	private String initiator;
	
	/**
	 * 会议标题
	 */
	//@Length(max=100)
	private String meetingTitle;
	
	/**
	 * 会议时间
	 */
	//@Length(max=100)
	private String meetingTime;
	
	/**
	 * 发起人id
	 */
	//@Length(max=100)
	private String initiatorId;
	
	/**
	 * 主控方
	 */
	//@Length(max=100)
	private String assignController;
	
	/**
	 * 当前节点
	 */
	//@Length(max=100)
	private String nodeNum;
	
	/**
	 * 是否有效，0 – 有效，1 – 无效
	 */
	//
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
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
	@TableField(fill = FieldFill.INSERT)
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
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private java.util.Date updateDatetime;



}

