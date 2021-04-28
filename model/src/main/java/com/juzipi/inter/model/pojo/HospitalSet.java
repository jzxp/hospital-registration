package com.juzipi.inter.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.juzipi.inter.model.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author juzipi
 * @Date 2021/4/27 11:10
 * @Info
 */
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

    private String signKey;
    //联系人
    private String contactName;
    //联系电话
    private String contactPhone;

    //状态码
    private Integer status;


}
