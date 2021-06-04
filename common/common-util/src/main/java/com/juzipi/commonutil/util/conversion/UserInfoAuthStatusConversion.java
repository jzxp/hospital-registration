package com.juzipi.commonutil.util.conversion;

/**
 * @Author juzipi
 * @Date 2021/6/3 11:41
 * @Info
 */
public class UserInfoAuthStatusConversion {

    private static final String NO_AUTH = "未认证";

    private static final String AUTH_NOW = "认证中";

    private static final String AUTH_SUCCESS = "认证成功";

    private static final String AUTH_FAIL = "认证失败";


    public static String stateTransition(Integer status){
        String statusString = "";
        switch (status){
            case 0:
                statusString = NO_AUTH;
                break;
            case 1:
                statusString = AUTH_NOW;
                break;
            case 2:
                statusString = AUTH_SUCCESS;
                break;
            case -1:
                statusString = AUTH_FAIL;
            default:
                break;
        }
        return statusString;
    }


}
