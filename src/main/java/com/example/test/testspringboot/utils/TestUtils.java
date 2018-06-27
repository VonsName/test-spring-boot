package com.example.test.testspringboot.utils;

import org.springframework.util.StringUtils;

public class TestUtils {
    static ThreadLocal<String> threadLocal=new InheritableThreadLocal<>();
    public static void set(String name){
        threadLocal.set(name);
    }
    public static String get(){
        return threadLocal.get();
    }
    public static void remove(){
        if (!StringUtils.isEmpty(threadLocal.get())){
            threadLocal.remove();
        }
    }
}
