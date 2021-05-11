package com.juzipi.inter.vo.hospital;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @Author juzipi
 * @Date 2021/5/11 15:07
 * @Info
 */
@Data
public class DepartmentVo {

    //科室编号
    private String depCode;

    //科室名称
    private String depName;

    //科室下级节点
    private List<DepartmentVo> children;


}
