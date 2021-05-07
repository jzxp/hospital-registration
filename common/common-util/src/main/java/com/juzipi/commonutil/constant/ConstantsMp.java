package com.juzipi.commonutil.constant;

/**
 * @Author juzipi
 * @Date 2021/4/28 16:52
 * @Info 插入更新等操作的常量类
 */
public class ConstantsMp {


    public static final String CREATE_TIME = "createTime";

    public static final String UPDATE_TIME = "updateTime";

    public static final String DELETED = "deleted";

    public static final Integer DELETED_VALUE = 0;//逻辑删除,0未删除,1已删除


    /**
     * hospitalSet表
     */
    public static final String STATUS = "status";

    public static final Integer STATUS_VALUE = 1;//状态码,1启用,0禁用

    public static final String SIGN_KEY = "signKey";//签名密钥


    /**
     * hospital 表(mongodb)
     */
    public static final Integer HOSPITAL_STATUS_VALUE_MONGO = 0;//状态码,0未上线,1已上线


    /**
     * schedule表（mongo）
     */
    public static final Integer SCHEDULE_STATUS_VALUE_MONGO = 1;//状态：-1停诊，0停约，1空闲

}
