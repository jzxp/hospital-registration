package com.juzipi.inter.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.juzipi.inter.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author juzipi
 * @Date 2021/4/27 11:10
 * @Info
 */
@ApiModel(value = "医院设置实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("hospital_set")
public class HospitalSet extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //医院名字
    private String hpName;
    //医院码
    private String hpCode;

    private String apiUrl;

    //签名密钥,插入数据时随机生成
    @TableField(fill = FieldFill.INSERT)
    private String signKey;
    //联系人
    private String contactName;
    //联系电话
    private String contactPhone;

    //状态码,1可以使用,0不能使用
    @TableField(fill = FieldFill.INSERT)
    private Integer status;


}
