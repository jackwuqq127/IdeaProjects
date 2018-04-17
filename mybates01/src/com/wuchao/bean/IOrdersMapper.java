package com.wuchao.bean;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IOrdersMapper {
    @ResultMap("")
    @Select("select * from orders")
    public List<Orders> getOrdersAll();
}
