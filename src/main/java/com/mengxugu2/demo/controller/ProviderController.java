package com.mengxugu2.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: bii_management_system
 * @description: 供应商控制层
 * @author: tgw
 * @create: 2020-08-28 15:28
 */
@Controller
public class ProviderController {
    @GetMapping("/providers")
    public String list(){

        return "provider/list";
    }
}
