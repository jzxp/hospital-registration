package com.juzipi.hospital.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.juzipi.commonutil.constant.ConstantsMp;
import com.juzipi.serviceutil.util.MD5;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

/**
 * @Author juzipi
 * @Date 2021/5/18 20:15
 * @Info
 */
@Configuration
public class MybatisHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {

        /**
         * hospitalSet表的
         */
        //默认状态
        this.setFieldValByName(ConstantsMp.HOSPITAL_SET_STATUS,ConstantsMp.HOSPITAL_SET_STATUS_VALUE,metaObject);
        //设置签名密钥
        this.setFieldValByName(ConstantsMp.HOSPITAL_SET_SIGN_KEY, MD5.getSign(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {



    }
}
