package com.djzhgd.module.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.djzhgd.module.contract.domain.ZhgdTenderingBidding;
import com.djzhgd.module.contract.domain.vo.ZhgdTenderingBiddingVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @PROJECT_NAME: dj_zhgd
 * @ClassName: IZhgdAnnouncementService
 * @Author: xjq
 * @DATE: 2020/5/18 10:42
 * @Version 1.0
 **/
public interface IZhgdTenderingBiddingService extends IService<ZhgdTenderingBidding> {

    IPage<ZhgdTenderingBidding> list(ZhgdTenderingBiddingVo zhgdTenderingBiddingVo);

    ZhgdTenderingBiddingVo detail(Integer id);

    int delete(Integer id);


    boolean saveData(ZhgdTenderingBiddingVo zhgdTenderingBiddingVo);

    boolean updateData(ZhgdTenderingBiddingVo zhgdTenderingBiddingVo);

    Map<String, Object> uploadFile(MultipartFile uploadFile) throws IOException;
}
