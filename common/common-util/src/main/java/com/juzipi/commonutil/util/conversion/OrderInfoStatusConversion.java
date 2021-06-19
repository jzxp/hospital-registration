package com.juzipi.commonutil.util.conversion;

/**
 * @Author juzipi
 * @Date 2021/6/19 16:27
 * @Info
 */
public class OrderInfoStatusConversion {


    public static final String UN_PAID = "预约成功,待支付";
    public static final String PAID = "已支付";
    public static final String GET_NUMBER = "已取号";
    public static final String CANCEL = "取消预约";


    public static String stateTransition(Integer status){
        String statusString = "";
        switch (status){
            case 0:
                statusString = UN_PAID;
                break;
            case 1:
                statusString = PAID;
                break;
            case 2:
                statusString = GET_NUMBER;
                break;
            case -1:
                statusString = CANCEL;
            default:
                break;
        }
        return statusString;
    }

}
