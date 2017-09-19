package com.harambase.common;

import javax.servlet.http.HttpServletRequest;

public class BasePath {
    public static void basePath(HttpServletRequest request){
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
        request.setAttribute("basePath", basePath);
    }
}
