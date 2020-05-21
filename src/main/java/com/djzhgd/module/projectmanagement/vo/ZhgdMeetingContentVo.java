/**
 * Description: ZhgdMeetingContent页面表单对象
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
package com.djzhgd.module.projectmanagement.vo;


import com.djzhgd.module.projectmanagement.domain.ZhgdMeetingContent;
import com.djzhgd.module.projectmanagement.domain.ZhgdMeetingSign;
import com.djzhgd.module.projectmanagement.domain.Zhgdmeetingagenda;
import lombok.Data;

import java.util.List;

/**
 * ZhgdMeetingContent页面表单对象<br>
 * 
 * @author caoyx
 * @version 1.0, 2020-05-19
 * @see
 * @since 1.0
 */
@Data
public class ZhgdMeetingContentVo extends ZhgdMeetingContent {

	//项目会议节点表
	private List<Zhgdmeetingagenda> zhgdMeetingAgendas;
	//项目会议签到表
	private List<ZhgdMeetingSign> zhgdMeetingSigns;

	private ZhgdMeetingContent zhgdmeetingcontent;

}