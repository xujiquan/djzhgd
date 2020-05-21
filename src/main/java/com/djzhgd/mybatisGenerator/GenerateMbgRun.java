package com.djzhgd.mybatisGenerator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GenerateMbgRun {

    /*
     * Mybatis自带Generator工具生成Mybatis配置
     */
    public static void generate(File file) {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = null;
        try {
            config = cp.parseConfiguration(file);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        try {
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        System.out.println("生成 Mybatis-plus 配置成功！");
    }

    public static void main(String[] args) {

        GenerateMbgRun.generate(new File("./djzhgd-code/djzhgd/src/main/resources/mybatis-generator/mybatis-generator-config.xml"));

    }
}
