package com.mengxugu2.demo.controller;


import com.mengxugu2.demo.entities.Provider;
import com.mengxugu2.demo.mapper.ProviderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: bii_management_system
 * @description: 供应商控制层
 * @author: tgw
 * @create: 2020-08-28 15:28
 */
@Controller
public class ProviderController {
    @Autowired
    ProviderMapper providerMapper;

    @GetMapping("/providers")
    public String list(Map<String,Object> map,Provider provider){
        List<Provider> providers = providerMapper.getProviders(provider);
        map.put("providers",providers);
        //回显搜索框内输入的数据
        map.put("providerName",provider.getProviderName());

        return "provider/list";
    }

    //查看供应商详情或者修改【仅仅跳转到修改页面】都访问这一个
   // 通过 @PathVariable 可以将URL中占位符参数{xxx}绑定到类的方法形参中@PathVariable("xxx")

    //2、 @RequestParam：将请求参数绑定到你控制器的方法参数上（是springmvc中接收普通参数的注解）
    //语法：@RequestParam(value=”参数名”,required=”true/false”,defaultValue=””)
    //value：参数名【请求网址中的参数  <a th:href="@{/provider/} + ${p.pid} +'?type=update'" href="update.html">】
    //required：是否包含该参数，默认为true，表示该请求路径中必须包含该参数，如果不包含就报错。
    //defaultValue：默认参数值，如果设置了该值，required=true将失效，自动为false,如果没有传该参数，就使用默认值


    //type这个key， 如果没有传值，默认值就是view
    @GetMapping("/provider/{pid}")
    public String view(@PathVariable("pid") Integer pid,
                       @RequestParam(value = "type",defaultValue = "view") String type, Map<String,Object> map){

        Provider provider = providerMapper.getProviderByPid(pid);
        map.put("provider",provider);
        return "provider/"+ type;
    }

    //提交修改供应商信息
    @PutMapping("/provider")
    public String update(Provider provider){

        providerMapper.updateProvider(provider);
        //return "redirect:providers";
        return "redirect:/providers";

    }

    //删除供应商信息
    @DeleteMapping("/provider/{pid}")
    public String delete(@PathVariable ("pid") Integer pid){
        providerMapper.deleteProviderByPid(pid);
        System.out.println("pid = "+pid);
        return "redirect:/providers";

    }

    //跳转到 add供应商信息
    @GetMapping("/provider")
    public String toAddPage(){

        return "provider/add";

    }
    //真正增加供应商
    @PostMapping("/provider")
    public String Add(Provider provider){
        providerMapper.addProvider(provider);
        return "redirect:providers";

    }
}
