/**
 * Description: ZhgdMeetingContent业务接口
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
package com.djzhgd.module.projectmanagement.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.djzhgd.module.projectmanagement.domain.ZhgdMeetingContent;
import com.djzhgd.module.projectmanagement.domain.Zhgdmeetingagenda;
import com.djzhgd.module.projectmanagement.vo.ZhgdMeetingContentVo;

import java.util.List;

/**
 * ZhgdMeetingContent业务接口<br>
 *
 * @author caoyx
 * @version 1.0, 2020-05-19
 * @see
 * @since 1.0
 */
public interface IZhgdMeetingContentService extends IService<ZhgdMeetingContent> {

    IPage<ZhgdMeetingContent> list(ZhgdMeetingContent zhgdmeetingcontent);

    boolean savecontentandagenda(ZhgdMeetingContentVo zhgdmeetingcontent, List<Zhgdmeetingagenda> zhgdMeetingAgendas);

    ZhgdMeetingContent contentandagenda(Long id);

    boolean removeandothers(Integer id);

    Integer synchronousUpdate(Integer meetingId, Integer currentNodeId, Integer zhgdUserId);
}
