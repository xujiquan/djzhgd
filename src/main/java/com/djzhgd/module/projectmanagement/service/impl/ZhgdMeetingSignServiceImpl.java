package com.djzhgd.module.projectmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djzhgd.framework.config.UploadFileProperties;
import com.djzhgd.module.projectmanagement.domain.ZhgdMeetingContent;
import com.djzhgd.module.projectmanagement.domain.ZhgdMeetingSign;
import com.djzhgd.module.projectmanagement.mapper.ZhgdMeetingContentMapper;
import com.djzhgd.module.projectmanagement.mapper.ZhgdMeetingSignMapper;
import com.djzhgd.module.projectmanagement.service.IZhgdMeetingContentService;
import com.djzhgd.module.projectmanagement.service.IZhgdMeetingSignService;
import com.djzhgd.module.projectmanagement.vo.ZhgdMeetingSignVo;
import com.djzhgd.project.system.mapper.ZhgdFileMapper;
import com.djzhgd.project.system.service.ISysDeptService;
import com.djzhgd.project.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.io.File;

@Slf4j
@Service
public class ZhgdMeetingSignServiceImpl extends ServiceImpl<ZhgdMeetingSignMapper, ZhgdMeetingSign> implements IZhgdMeetingSignService {
    @Autowired
    private ZhgdMeetingContentMapper zhgdmeetingcontentmapper;
    @Autowired
    private ZhgdMeetingSignMapper zhgdmeetingsignmapper;
    @Autowired
    private IZhgdMeetingContentService izhgdmeetingcontentservice;

    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysDeptService deptservice;
    @Autowired
    private UploadFileProperties uploadFileProperties;
    @Autowired
    private ZhgdFileMapper zhgdFileMapper;
    /**
     * 签到
     */
    @Override
    public boolean meetingSignBook(ZhgdMeetingSignVo zhgdmeetingsignvo) {
        try {
            zhgdmeetingsignmapper.insert(zhgdmeetingsignvo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void exportExcel(Integer meetingId, HttpServletResponse response) {

        LambdaQueryWrapper<ZhgdMeetingSign> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ZhgdMeetingSign::getMeetingId, meetingId);
        List<ZhgdMeetingSign>  zhgdMeetingSignList = zhgdmeetingsignmapper.selectList(queryWrapper);
        ZhgdMeetingContent zhgdmeetingcontent = zhgdmeetingcontentmapper.selectById(meetingId);
        BufferedOutputStream fileOut = null;
        boolean flag = false;
        try {
            File file = new File("D:/djzhgd/会议签到簿.xls");
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
            // 创建一个sheet
            HSSFSheet sheet = wb.getSheet("Sheet1");
            // 利用HSSFPatriarch将图片写入EXCEL
            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
            sheet.getRow(1).getCell(1).setCellValue(zhgdmeetingcontent.getMeetingTitle());
            sheet.getRow(2).getCell(1).setCellValue(zhgdmeetingcontent.getMeetingTime());
            sheet.getRow(3).getCell(1).setCellValue("项目部");
            /**
             * 该构造函数有8个参数 前四个参数是控制图片在单元格的位置，分别是图片距离单元格left，top，right，bottom的像素距离
             * 后四个参数，前连个表示图片左上角所在的cellNum和 rowNum，后天个参数对应的表示图片右下角所在的cellNum和
             * rowNum， excel中的cellNum和rowNum的index都是从0开始的
             *
             */
            for (int i = 0; i < zhgdMeetingSignList.size(); i++) {
                ZhgdMeetingSign xySignBook = zhgdMeetingSignList.get(i);
                String signCompanyImg = xySignBook.getSignCompany();
                String signNameImg = xySignBook.getSignName();
                BASE64Decoder decoder = new BASE64Decoder();
                byte[] signCompanyImgBytes = decoder
                        .decodeBuffer(signCompanyImg);
                byte[] signNameImgBytes = decoder.decodeBuffer(signNameImg);

                HSSFClientAnchor anchor1 = new HSSFClientAnchor(0, 0, 0, 0,
                        (short) 1, 6 + i, (short) 2, 7 + i);
                // 插入图片
                patriarch.createPicture(anchor1, wb.addPicture(
                        signNameImgBytes, HSSFWorkbook.PICTURE_TYPE_JPEG));
                // 图片一导出到单元格中
                HSSFClientAnchor anchor2 = new HSSFClientAnchor(0, 0, 0, 0,
                        (short) 2, 6 + i, (short) 3, 7 + i);
                // 插入图片
                patriarch.createPicture(anchor2, wb.addPicture(
                        signCompanyImgBytes, HSSFWorkbook.PICTURE_TYPE_JPEG));

            }
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String("会议签到簿.xls".getBytes("gb2312"), "ISO8859-1"));
            // 生成的excel文件地址
            fileOut = new BufferedOutputStream(response.getOutputStream());
            // 写入excel文件
            wb.write(fileOut);
        } catch (IOException io) {
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                }
            }
        }
    }


}
