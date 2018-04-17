package com.zit.util;

import java.math.BigDecimal;

public class Util {
    public static int factorial(int n){
        if(n==1){
            return 1;
        }
        return n*factorial(n-1);
    }

    public static BigDecimal subFactorial(int m, int n){
        BigDecimal v = BigDecimal.valueOf(m);
        for(int i=1;i<n;i++){
            v = v.multiply(BigDecimal.valueOf(--m));
        }
        return v;
    }

    public static BigDecimal combination(int m,int n){
       return subFactorial(m, n).divide(BigDecimal.valueOf(factorial(n)));
    };

    public static void main(String[] args) {
        BigDecimal v = combination(52, 7);
        System.out.println(v);
    }
}
