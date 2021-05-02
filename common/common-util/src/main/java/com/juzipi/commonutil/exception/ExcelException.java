package com.juzipi.commonutil.exception;

/**
 * @Author juzipi
 * @Date 2021/5/2 11:30
 * @Info
 */
public class ExcelException extends RuntimeException {

    //所作操作
    private String option;
    //错误所在位置
    private String module;
    //简单错误信息
    private String message;

    public ExcelException(String option, String module) {
        this.option = option;
        this.module = module;
    }

    public ExcelException(String option, String module, String message) {
        this.option = option;
        this.module = module;
        this.message = message;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
