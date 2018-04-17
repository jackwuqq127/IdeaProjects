package com.zit.converter;

import com.zit.bean.Emp;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@Component
public class EmpConverter implements Converter<String,Emp> {
    @Override
    public Emp convert(String source) {
        Emp emp = new Emp();
        List<String> list = Arrays.asList(source.split(","));
        list.forEach(str->{
            String name=str.split(":")[0];
            Object value=str.split(":")[1];
            try {
                Field field=emp.getClass().getDeclaredField(name);
                field.setAccessible(true);
                if(field.getType().getTypeName().equals("int")){
                    value=Integer.parseInt(value.toString());
                }else if(field.getType().getSimpleName().equals("Double")){
                    value= Double.parseDouble(value.toString());
                }else if(field.getType().getSimpleName().equals("Date")){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    value = sdf.parse(value.toString());
                }
                field.set(emp,value); //vale can ba  basic or referennces  python
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return emp;
    }
}
