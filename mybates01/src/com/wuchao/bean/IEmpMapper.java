package com.wuchao.bean;

import com.sun.xml.internal.txw2.annotation.XmlNamespace;
import com.wuchao.bean.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IEmpMapper {
    @Insert("insert into emp(empno,ename,job,sal,deptno) VALUES (emp_empno.nextval,#{ename},#{job},#{sal},#{deptno})")
    public int addEmp(Emp emp);

    @Delete("delete from emp where empno=#{empno}")
    public int delEmp(int empno);

    @Select("select * from emp where sal<1000")
    public List<Emp> getEmpAll();
}
