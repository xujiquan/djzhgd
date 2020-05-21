package com.djzhgd.module.contract.domain.vo;

import com.djzhgd.module.contract.domain.ZhgdTenderingBidding;
import lombok.Data;

import java.util.List;

@Data
public class ZhgdTenderingBiddingVo extends ZhgdTenderingBidding {

    private String beginDate;

    private String endDate;

    private List<String> fileNames;

    private String filePath;
}