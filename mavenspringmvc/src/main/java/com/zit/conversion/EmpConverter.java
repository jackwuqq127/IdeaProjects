package com.zit.conversion;

import com.zit.bean.Emp;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class EmpConverter implements Converter<String,Emp> {
    @Override
    public Emp convert(String source) {
        Emp emp = new Emp();
        String[] sourceArray = source.split(",");
        int empno = Integer.parseInt(sourceArray[0].split(":")[1]);
        emp.setEmpno(empno);
        String ename=sourceArray[1].split(":")[1];
        emp.setEname(ename);
        String job=sourceArray[2].split(":")[1];
        emp.setJob(job);
        double sal = Double.parseDouble(sourceArray[3].split(":")[1]);
        emp.setSal(sal);
        double comm = Double.parseDouble(sourceArray[4].split(":")[1]);
        emp.setComm(comm);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String hiredateStr=sourceArray[5].split(":")[1];
        try {
            Date hiredate=sdf.parse(hiredateStr);
            emp.setHiredate(hiredate);
        } catch (ParseException e) {}
        return emp;
    }
}
