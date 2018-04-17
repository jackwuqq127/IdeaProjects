package com.zit.bean;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

public class Emp implements Cloneable,Serializable{ //浅克隆


    @Max(value = 7100,message = "员工编号止于{value}")
    @Min(value = 7001,message = "员工编号始于{value}")
    private int empno;

    @NotEmpty(message = "员工姓名不能为空")
    private String ename;

    @Past(message = "入职日期不能是一个将来的日期！")
    @NotNull(message = "入职日期不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hiredate;

    @NotEmpty(message="工种不能为空")
    private String job;

    @DecimalMax(value = "5000",message = "工资不能高于{value}")
    @DecimalMin(value = "2000",message = "工资不能低于{value}")
    @NotNull(message = "工资不能为空")
    private Double sal;

    @NumberFormat(pattern ="#,###,###.###")
    private Double comm;

    private Emp mgr;
    private Dept dept;

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Double getComm() {
        return comm;
    }

    public void setComm(Double comm) {
        this.comm = comm;
    }

    public Emp getMgr() {
        return mgr;
    }

    public void setMgr(Emp mgr) {
        this.mgr = mgr;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Emp(){}

    public Emp(int empno, String ename, Double comm) {
        this.empno = empno;
        this.ename = ename;
        this.comm = comm;
    }

    @Override
    public Emp clone(){
        try {
            return (Emp)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", hiredate=" + hiredate +
                ", job='" + job + '\'' +
                ", sal=" + sal +
                ", comm=" + comm +
                '}';
    }
}
