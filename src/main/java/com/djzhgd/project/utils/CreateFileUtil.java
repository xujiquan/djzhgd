
package com.djzhgd.project.utils;

import cn.hutool.core.io.resource.ClassPathResource;
import com.djzhgd.framework.config.DjzhgdConfig;
import com.djzhgd.project.system.domain.*;
import com.djzhgd.project.system.mapper.ZxFileInfoMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * @PROJECT_NAME: djzhgd 智慧咨询项目
 * @ClassName: CreateFileUtil java生成文件(xml、word、pdf......)类
 * @Author: zhangheng
 * @DATE: 2020/3/20 14:03
 * @Version 1.0
 **/
public class CreateFileUtil {

    @Autowired
    private ZxFileInfoMapper zxFileInfoMapper;


}
