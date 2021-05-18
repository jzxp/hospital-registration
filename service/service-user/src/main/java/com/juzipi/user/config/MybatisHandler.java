package com.juzipi.user.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.juzipi.commonutil.constant.ConstantsMp;
import com.juzipi.commonutil.util.UniversalUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @Author juzipi
 * @Date 2021/5/18 20:12
 * @Info
 */
//不能共用，每个模块写一个吧
@Configuration
public class MybatisHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        //创建时间
        this.setFieldValByName(ConstantsMp.CREATE_TIME,new Date(),metaObject);
        //更新时间
        this.setFieldValByName(ConstantsMp.USER_INFO_NICKNAME, UniversalUtils.getEnglishRandomNickname(6),metaObject);


    }

    @Override
    public void updateFill(MetaObject metaObject) {

        this.setFieldValByName(ConstantsMp.UPDATE_TIME,new Date(),metaObject);

    }
}
