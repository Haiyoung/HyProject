package com.haiyoung.tinycode;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-06-01 17:14
 * @Version 1.0
 */
public class Constants {

    /** 实体类路径 */
    public static final String DO_MAIN_MYSQL_PATH ="com.haiyoung.tinycode.bean.po";

    /** local mapper路径 */
    public static final String MAPPER_MYSQL_PATH_LOCAL = "com.haiyoung.tinycode.mapper.local";

    /** docker mapper路径 */
    public static final String MAPPER_MYSQL_PATH_DOCKER = "com.haiyoung.tinycode.mapper.docker";

    /** mapper xml 路径 */
    public static final String MAPPER_MYSQL_XML_PATH = "classpath*:**/*Mapper.xml";

    /** 默认分页数量**/
    public static final Integer DEFAULT_PAGE_NUM = 1;
    public static final Integer DEFAULT_PAGE_SIZE = 20;


}
