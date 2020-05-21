package com.djzhgd.module.people.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.djzhgd.module.people.domain.ZhgdSafeActivity;
import com.djzhgd.module.people.domain.vo.ZhgdSafeActivityVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZhgdSafeActivityMapper extends BaseMapper<ZhgdSafeActivity> {

    List<ZhgdSafeActivityVo>  getTrainCount(ZhgdSafeActivityVo zhgdSafeActivityVo);

    List<ZhgdSafeActivityVo>  trainZBCount(ZhgdSafeActivityVo zhgdSafeActivityVo);
}