package com.djzhgd.module.people.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
@Data
public class ZhgdUser {
    private Long id;
    @TableField(fill = FieldFill.INSERT)
    private Long disabled;

    private Long createUserid;

    private String createUsername;
    @TableField(fill = FieldFill.INSERT)
    private Date createDatetime;

    private String createIp;

    private Long updateUserid;

    private String updateUsername;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDatetime;

    private String updateIp;

    private String userName;

    private String companyName;

    private String userType;

    private String postName;

    private Date enterDate;

    private String zhgdDeptName;

    private String zhgdDeptCode;

    private Long zhgdDeptId;

    private String photoPath;

    private String idNumber;

    private String phoneNum;

    private Long ifPresent;

    private String sex;

    private String birthDate;

    private Long age;

    private String nation;

    private String eduBackground;

    private String bloodType;

    private String nativePlace;

    private String homeAddress;

    private String medicalHistory;

    private String remark;

    private String checkId;

    private String card;

    private String centificateCategory;

    private String centificateNo;

    private String centificateCompany;

    private String centificateReleaseTime;

    private String qrCodePath;

    private String idCardPath;

    private String idCardReversePath;

    private String otherSysUserUnique;

    private String certificateEffectiveTime;

    private String keepUserId;

    private String qrCodePicture;

    private Long groups;

    private String contractNumber;

    private Long phoneType;

    private String workingTeam;

    private String sjjyGsj;

    private String sjjyXmj;

    private String sjjyBzj;

    private String testScores;

    private String zrsqdsj;

    private String sqjdsj;

    private String centificatePath;


}