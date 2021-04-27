package com.juzipi.commonutil.tools;



//枚举类，用于定义状态码
public enum ResultCode {

    //成功
    SUCCESS(200,"操作成功"),

    //操作成功提示
    CREATE_SUCCESSFULLY(201,"添加成功"),
    REMOVE_SUCCESSFULLY(202,"删除成功"),
    QUERY_SUCCESSFULLY(203,"查询成功"),
    MODIFY_SUCCESSFULLY(204,"修改成功"),
    LOGIN_SUCCESSFULLY(205,"登录成功"),
    REGISTER_SUCCESSFULLY(206,"注册成功"),
    LOGOUT_SUCCESSFULLY(207,"注销成功"),
    //操作失败
    CREATE_FAIL(251,"添加失败"),
    REMOVE_FAIL(252,"删除失败"),
    QUERY_FAIL(253,"查询失败"),
    MODIFY_FAIL(254,"修改失败"),
    REGISTER_FAIL(255,"注册失败"),

    //失败
    FAIL(999,"操作失败"),

    //参数错误
    PARAM_NOT_VALID(301,"参数无效"),
    PARAM_IS_BLANK(302,"参数为空"),
    PARAM_TYPE_ERROR(303,"参数类型错误"),
    PARAM_NOT_COMPLETE(304,"参数缺失"),

    //用户错误
    USER_NOT_LOGIN(401,"未登录"),
    USER_LOGIN_FAILED(402,"登陆失败"),
    USER_NO_PERMISSION(403,"权限不足"),
    USER_ACCOUNT_NOT_EXIST(404,"账号不存在"),
    USER_PASSWORD_ERROR(405,"密码错误"),
    USER_ACCOUNT_DISABLE(406,"账号不可用"),
    USER_ACCOUNT_LOCKED(407,"账号锁定"),
    USER_ACCOUNT_EXPIRED(408,"账号过期"),
    USER_SESSION_INVALID(409,"登陆超时"),
    USER_ACCOUNT_USE_BY_OTHERS(410,"检测到账号已在另一台机器登录"),
    USER_PASSWORD_EXPIRED(411,"密码过期"),
    USER_PASSWORD_IS_BLANK(412,"密码为空"),
    USER_ACCOUNT_IS_REPEATED(413,"账号重复"),
    USER_PASSWORD_CHANGE_CODE(414,"密码已重置为验证码，请尽快修改密码")
    ;

    private final Integer code;
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
