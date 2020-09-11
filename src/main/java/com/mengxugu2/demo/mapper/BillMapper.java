package com.mengxugu2.demo.mapper;



import com.mengxugu2.demo.entities.Bill;
import com.mengxugu2.demo.entities.BillProvider;

import java.util.List;

/**
 * @Auther: 梦学谷
 */
//@Mapper 或 @MapperScan("com.mengxuegu.springboot.mapper")
public interface BillMapper {

    List<BillProvider> getBills(Bill bill);

    BillProvider getBillByBid(Integer bid);

    int addBill(Bill bill);

    int updateBill(Bill bill);

    int deteleBillByBid(Integer bid);

}
