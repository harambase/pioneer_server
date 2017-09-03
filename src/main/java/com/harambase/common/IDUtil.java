package com.harambase.common;

public class IDUtil {
    public static String genUserID(String info){
        Integer last = (int)(Math.random() * (999 - 100 + 1) + 100);
        return  "9" + info.split("-")[0] + info.split("-")[1] + last;
    }
    
    public static String genCRN(String info) {
        Integer last = (int) (Math.random() * (99 - 10 + 1) + 10);
        return "1" + info.split("-")[0] + info.split("-")[1] + last;
    }
}
