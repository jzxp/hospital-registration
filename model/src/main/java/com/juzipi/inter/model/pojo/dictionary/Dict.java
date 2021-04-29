package com.juzipi.inter.model.pojo.dictionary;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.juzipi.inter.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author juzipi
 * @Date 2021/4/29 18:02
 * @Info 数据字典实体类
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "数据字典")
@Data
@NoArgsConstructor
@TableName(value = "data_dict")
public class Dict extends BaseEntity {

    public static final long serialVersionUID = 1L;

    //父节点id
    @ApiModelProperty(value = "上级id")
    private Long parentId;

    @ApiModelProperty(value = "名称")
    private String dictName;

    @ApiModelProperty(value = "值")
    private String dictValue;

    @ApiModelProperty(value = "编码")
    private String dictCode;

    //扩展字段，其他参数
    @ApiModelProperty(value = "其他参数")
    @TableField(exist = false)
    private Map<String, Object> param = new HashMap<>();

    //是否包含子节点
    @ApiModelProperty(value = "是否包含子节点")
    @TableField(exist = false)
    private Boolean hasChildren;
}
