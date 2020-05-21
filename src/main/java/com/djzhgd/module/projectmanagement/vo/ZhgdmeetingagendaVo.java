package com.djzhgd.module.projectmanagement.vo;

import com.djzhgd.module.projectmanagement.domain.Zhgdmeetingagenda;
import lombok.Data;

import java.util.List;

@Data
public class ZhgdmeetingagendaVo extends Zhgdmeetingagenda {
    //用来存文件id
    private List<Integer> meetingfileid;
}
