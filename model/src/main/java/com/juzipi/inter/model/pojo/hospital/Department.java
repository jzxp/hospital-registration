package com.juzipi.inter.model.pojo.hospital;

import com.juzipi.inter.model.base.BaseMongoEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author juzipi
 * @Date 2021/5/5 19:19
 * @Info 科室表，存入mongo
 */
@Data
@ApiModel(value = "科室表")
@Document(value = "department")
public class Department extends BaseMongoEntity {


    @ApiModelProperty(value = "医院编号")
    @Indexed // 普通索引
    private String hpCode;

    @ApiModelProperty(value = "科室编号")
    private String depCode;

    @ApiModelProperty(value = "科室名称")
    private String depName;

    @ApiModelProperty(value = "大科室编号")
    private String bigCode;

    @ApiModelProperty(value = "大科室名称")
    private String bigName;

    @ApiModelProperty(value = "科室描述")
    private String introduction;

}
