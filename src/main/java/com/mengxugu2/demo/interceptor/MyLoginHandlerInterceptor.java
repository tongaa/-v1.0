package com.mengxugu2.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: mengxuegu-bill
 * @description: 登录拦截器，所有登录都要拦截判断
 * @author: tgw
 * @create: 2020-05-17 16:43
 */
public class MyLoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser != null){
            return  true; //已经登陆过 放行
        }
        request.setAttribute("msg","没有权限,请登录！");
        request.getRequestDispatcher("/index.html").forward(request,response); //必须转发到登录 页面  因为重定向不能携带msg信息
        return false;
    }
}
