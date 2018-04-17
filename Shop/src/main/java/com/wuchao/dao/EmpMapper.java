package com.wuchao.dao;

import com.wuchao.bean.Emp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* dao 映射类：
* 方法名必须同mapper xml 文件中的id 名必须保持一致
* */
public interface EmpMapper {

    public List<Emp> getEmpAll();
    public Emp getEmpByEmpno(int empno);
    public double getSalByEmpno(int empno);
    public int addSal(Emp emp);
    public int subSal(Emp emp);
}
