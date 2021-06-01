package com.juzipi.commonutil.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author juzipi
 * @Date 2021/6/1 15:57
 * @Info
 */
public class AuthContextHolder {


    public static Long getUserId(HttpServletRequest request){
        String token = request.getHeader("token");
        return JwtUtils.getUserId(token);
    }



    public static String getUserName(HttpServletRequest request){
        String token = request.getHeader("token");
        return JwtUtils.getUserName(token);
    }

}
