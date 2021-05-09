package com.juzipi.commonutil.tool;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author juzipi
 * @Date 2021/4/27 19:27
 * @Info Result 结果集
 */
@ApiModel(value = "JSON结果实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {


    @ApiModelProperty(value = "标志，成功或失败")
    private Boolean flag;

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "消息")
    private String message;

    @ApiModelProperty(value = "数据")
    private Object data;

    //无返回值
    public Result(Boolean flag){
        //根据状态的true和false来判断成功或是失败的状态码和信息
        this.flag = flag;
        this.code = flag ? ResultCode.SUCCESS.getCode() : ResultCode.FAIL.getCode();
        this.message = flag ? ResultCode.SUCCESS.getMessage() : ResultCode.FAIL.getMessage();
    }


    //有返回值的
    public Result(Boolean flag,Object data){
        this.flag = flag;
        this.code = flag ? ResultCode.SUCCESS.getCode() : ResultCode.FAIL.getCode();
        this.message = flag ? ResultCode.SUCCESS.getMessage() : ResultCode.FAIL.getMessage();
        this.data = data;
    }



    //有返回值的，成功自定义，失败自定义
    public Result(Boolean flag,ResultCode resultCode,Object data){
        this.flag = flag;
        this.code = flag ? (resultCode == null ? ResultCode.SUCCESS.getCode() : resultCode.getCode()) : (resultCode == null ? ResultCode.FAIL.getCode() : resultCode.getCode());
        this.message = flag ? (resultCode == null ? ResultCode.SUCCESS.getMessage() : resultCode.getMessage()) : (resultCode == null ? ResultCode.FAIL.getMessage() : resultCode.getMessage());
        this.data = data;
    }


    //无返回值的，成功自定义，失败自定义
    public Result(Boolean flag,ResultCode resultCode){
        this.flag = flag;
        this.code = flag ? (resultCode == null ? ResultCode.SUCCESS.getCode() : resultCode.getCode()) : (resultCode == null ? ResultCode.FAIL.getCode() : resultCode.getCode());
        this.message = flag ? (resultCode == null ? ResultCode.SUCCESS.getMessage() : resultCode.getMessage()) : (resultCode == null ? ResultCode.FAIL.getMessage() : resultCode.getMessage());
    }

}
