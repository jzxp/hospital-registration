package com.juzipi.serviceutil.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.juzipi.commonutil.constant.ConstantsMp;
import com.juzipi.serviceutil.util.MD5;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author juzipi
 * @Date 2021/4/28 17:04
 * @Info mp自动填充配置了
 */
@Component
public class MybatisHandler implements MetaObjectHandler {


    //添加时执行
    @Override
    public void insertFill(MetaObject metaObject) {

        /**
         * 基础实体类的通用字段
         */
        //创建时间
        this.setFieldValByName(ConstantsMp.CREATE_TIME,new Date(),metaObject);
        //更新时间
        this.setFieldValByName(ConstantsMp.UPDATE_TIME,new Date(),metaObject);
        //逻辑删除
        this.setFieldValByName(ConstantsMp.DELETED,ConstantsMp.DELETED_VALUE,metaObject);


        /**
         * hospitalSet表的
         */
        //默认默认状态
        this.setFieldValByName(ConstantsMp.HOSPITAL_SET_STATUS,ConstantsMp.HOSPITAL_SET_STATUS_VALUE,metaObject);
        //设置签名密钥
        this.setFieldValByName(ConstantsMp.HOSPITAL_SET_SIGN_KEY, MD5.getSign(),metaObject);


        /**
         * user表
         */
//        this.setFieldValByName(ConstantsMp.USER_INFO_STATUS, ConstantsMp.USER_INFO_STATUS_VALUE,metaObject);


    }

    //更新时执行
    @Override
    public void updateFill(MetaObject metaObject) {
        /**
         * 基础实体类的通用字段
         */
        this.setFieldValByName(ConstantsMp.UPDATE_TIME,new Date(),metaObject);

    }

}
