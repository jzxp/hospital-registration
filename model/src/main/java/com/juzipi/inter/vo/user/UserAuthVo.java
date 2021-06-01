package com.juzipi.inter.vo.user;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author juzipi
 * @Date 2021/6/1 16:03
 * @Info
 */
@Data
public class UserAuthVo {


    @ApiModelProperty(value = "用户姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "证件类型")
    @TableField("certificates_type")
    private String certificatesType;

    @ApiModelProperty(value = "证件编号")
    @TableField("certificates_no")
    private String certificatesNo;

    @ApiModelProperty(value = "证件路径")
    @TableField("certificates_url")
    private String certificatesUrl;

}
