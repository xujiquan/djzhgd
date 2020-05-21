package com.djzhgd.module.device.vo;

import com.djzhgd.module.device.domain.ZhgdDevice;
import lombok.Data;

@Data
public class ZhgdDeviceVo extends ZhgdDevice {

    private String beginDate;
    private String endDate;
    private String deviceName;


}