package com.mengxugu2.demo.config;

import com.mengxugu2.demo.component.MyLocalResolver;

import com.mengxugu2.demo.interceptor.MyLoginHandlerInterceptor ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: mengxuegu-bill
 * @description: 视图控制器添加
 * @author: tgw
 * @create: 2020-05-16 20:39
 */
@Configuration
public class MySpringMvcConfig {
//    @Bean
//   public WebMvcConfigurer webMvcConfigurer(){
//  return  new WebMvcConfigurer(){
//
//
//
//      @Override
//      public void addViewControllers(ViewControllerRegistry registry) {
//          registry.addViewController("/").setViewName("main/login");
//          registry.addViewController("/index.html").setViewName("main/login");
//          registry.addViewController("/main.html").setViewName("main/index");
//
//      }
//      @Override
//      public void addInterceptors(InterceptorRegistry registry) {
//          registry.addInterceptor(new MyLoginHandlerInterceptor()).addPathPatterns("/**")
//                  //排除不需要拦截的请求路径   没有/会访问运行出错
//                  .excludePathPatterns("/", "/index.html", "/login","/css/*", "/img/*", "/js/*");
//          //springboot2+ 之后需要将静态资源文件的访问路径也排除
//
//      }
//  };
//
//    }



/**
*@Description: 自定义的区域解析器，实现接口组件LocaleResolver
*@Param: 
*@return: 
*@Author: tgw
*@date: 2020-05-16
*/
    @Bean
    public LocaleResolver localeResolver(){
        return  new MyLocalResolver();
    }
}
