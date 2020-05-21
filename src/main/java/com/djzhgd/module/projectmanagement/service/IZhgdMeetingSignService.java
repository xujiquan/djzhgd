/**
 * Description: ZhgdMeetingSign业务接口
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
package com.djzhgd.module.projectmanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.djzhgd.module.projectmanagement.domain.ZhgdMeetingSign;
import com.djzhgd.module.projectmanagement.vo.ZhgdMeetingSignVo;

import javax.servlet.http.HttpServletResponse;

/**
 * ZhgdMeetingSign业务接口<br>
 * 
 * @author caoyx
 * @version 1.0, 2020-05-19
 * @see
 * @since 1.0
 */
public interface IZhgdMeetingSignService extends IService<ZhgdMeetingSign> {
    boolean meetingSignBook(ZhgdMeetingSignVo zhgdmeetingsignvo);

    void exportExcel(Integer meetingId, HttpServletResponse response);
}