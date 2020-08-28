package com.mengxugu2.demo.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @program: mengxuegu-bill
 * @description: 自定义区域解析器：中英文切换
 * @author: tgw
 * @create: 2020-05-16 21:44
 */
public class MyLocalResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        Locale locale =Locale.getDefault() ;
        String l = httpServletRequest.getParameter("l");
        if (!StringUtils.isEmpty(l)) {
        String[] split = l.split("_");
             //参数一 语言代码  参数二 是国家代码
            locale = new Locale(split[0],split[1]);

        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
