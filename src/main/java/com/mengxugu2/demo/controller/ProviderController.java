package com.mengxugu2.demo.controller;


import com.mengxugu2.demo.entities.Provider;
import com.mengxugu2.demo.mapper.ProviderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
