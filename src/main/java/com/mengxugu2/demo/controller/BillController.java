package com.mengxugu2.demo.controller;


import com.mengxugu2.demo.entities.Bill;
import com.mengxugu2.demo.entities.BillProvider;
import com.mengxugu2.demo.entities.Provider;
import com.mengxugu2.demo.mapper.BillMapper;
import com.mengxugu2.demo.mapper.ProviderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: bii_management_system
 * @description: 账单控制层
 * @author: tgw
 * @create: 2020-08-28 15:28
 */
@Controller
public class BillController {
    @Autowired
    ProviderMapper providerMapper;
    @Autowired
    BillMapper billMapper;
    @GetMapping("/bills")
    public String list(Map<String,Object> map, Bill bill){
        List<BillProvider> billProviders = billMapper.getBills(bill);
        List<Provider> providers = providerMapper.getProviders(null);
        map.put("billProviders",billProviders);
        map.put("providers",providers);

        //回显搜索框内输入的数据
        map.put("billName",bill.getBillName());
        map.put("pid",bill.getPid());
        map.put("pay",bill.getPay());

        return "bill/list";
    }





    @GetMapping("/bill/{bid}")
    public String view(@PathVariable("bid") Integer bid,
                       @RequestParam(value = "type",defaultValue = "view") String type, Map<String,Object> map){

        BillProvider billProvider = billMapper.getBillByBid(bid);

        if ("update".equals(type))
        {
            List<Provider> providers = providerMapper.getProviders(null);
            map.put("providers",providers);
        }
        map.put("billProvider",billProvider);
        return "bill/" + type;
    }
    //跳转到 添加 账单信息页面
    @GetMapping("/bill")
    public String toAddPage(Map<String,Object> map){
        List<Provider> providers = providerMapper.getProviders(null);
        map.put("providers",providers);
        return "bill/add";

    }
    //真正增加账单
    @PostMapping("/bill")
    public String Add(Bill bill){

        billMapper.addBill(bill);
        return "redirect:/bills";

    }



    @PutMapping("/bill")
    public String update(BillProvider billProvider){

        billMapper.updateBill(billProvider);
        return "redirect:/bills";

    }


    @DeleteMapping("/bill/{bid}")
    public String delete(@PathVariable ("bid") Integer bid){
        billMapper.deteleBillByBid(bid);

        return "redirect:/bills";

    }

}
