package com.juzipi.inter.model.pojo.hospital;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.juzipi.inter.model.base.BaseEntity;
import com.juzipi.inter.model.base.BaseMongoEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @Author juzipi
 * @Date 2021/5/4 15:15
 * @Info  医院上传接口实体类，存入mongodb的，不能用mybatisplus了
 */
@Data
@ApiModel(value = "医院表")
@Document(value = "hospital")
public class Hospital extends BaseMongoEntity {

    //mongodb的id只能是String类型了，它自动给你生成...


    @ApiModelProperty(value = "医院编码")
    @Indexed(unique = true)//唯一索引
    private String hpCode;

    @ApiModelProperty(value = "医院名字")
    @Indexed //普通索引
    private String hpName;

    @ApiModelProperty(value = "医院类型")
    private String hpType;

    @ApiModelProperty(value = "省编码")
    private String provinceCode;

    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "区编码")
    private String districtCode;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "logo")
    private String logoData;

    @ApiModelProperty(value = "医院简介")
    private String introduction;

    @ApiModelProperty(value = "路程")
    private String route;

    @ApiModelProperty(value = "状态码")
    private Integer status;



}
