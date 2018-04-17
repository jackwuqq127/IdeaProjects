package com.wuchao.service;

import com.wuchao.bean.Emp;
import com.wuchao.dao.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpService {
    @Autowired
    private EmpMapper empMapper;

    //加工资
    public int addSal(Emp emp){
        return empMapper.addSal(emp);
    }

    //扣工资
    public int subSal(Emp emp){
        double sal=empMapper.getSalByEmpno(emp.getEmpno());
        if(sal<emp.getSal()){
            throw new RuntimeException("余额不足以扣除："+(sal-emp.getSal()));
        }
        return empMapper.subSal(emp);
    }

    public double getSal(int empno){
        return empMapper.getSalByEmpno(empno);
    }

    /**
     *
     * @param e0
     * @param e1
     * @return
     */
    @Transactional(rollbackFor =RuntimeException.class)
    public boolean transfor(Emp e0,Emp e1){
        int a=addSal(e1);
        int b=subSal(e0);
        return a+b==2;
    }
}
