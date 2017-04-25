/*
package com.hongchen.generator;

import com.baomidou.mybatisplus.generator.ConfigGenerator;

public class ConfigGeneratorTest {

    protected static ConfigGenerator getConfigGenerator() {
        ConfigGenerator cg = new ConfigGenerator();
        cg.setEntityPackage("com.hongchen.entity");//entity 实体包路径
        cg.setMapperPackage("com.hongchen.mapper");//mapper 映射文件路径
        cg.setServicePackage("com.hongchen.service");//service 层路径
        cg.setXmlPackage("mapper.mapper.xml");//xml层路径（可以不写）
        cg.setServiceImplPackage("com.hongchen.wecha.service.impl");//serviceimpl层路径（可以不写）

		*/
/* 此处可以配置 SuperServiceImpl 子类路径，默认如下 *//*

        //cg.setSuperServiceImpl("com.baomidou.framework.service.impl.SuperServiceImpl");

		*/
/* 此处设置 String 类型数据库ID，默认Long类型 *//*

        //cg.setConfigIdType(ConfigIdType.STRING);

        cg.setSaveDir("mybatis-plus");// 生成文件保存位置

        */
/*
         * 表是否包括前缀
		 * <p>
		 * 例如 mp_user 生成实体类 false 为 MpUser , true 为 User
		 * </p>
		 *//*

        cg.setDbPrefix(true);
        */
/*
         * 默认值为true , 是否覆盖当前路径下已有文件
         *//*

        cg.setFileOverride(true);
        return cg;
    }

}
*/
