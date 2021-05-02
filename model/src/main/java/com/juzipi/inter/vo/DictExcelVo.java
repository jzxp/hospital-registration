package com.juzipi.inter.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author juzipi
 * @Date 2021/5/2 15:09
 * @Info
 */
@Data
public class DictExcelVo {


    @ExcelProperty(value = "id",index = 0)
    private Long id;

    @ExcelProperty(value = "父级id",index = 1)
    private Long parentId;

    @ExcelProperty(value = "字典名字",index = 2)
    private String dictName;

    @ExcelProperty(value = "字典值",index = 3)
    private String dictValue;

    @ExcelProperty(value = "字典编码",index = 4)
    private String dictCode;


}
