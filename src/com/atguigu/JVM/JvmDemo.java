package com.atguigu.JVM;

/**
 * @author jzp
 * @create 2019-12-18 13:48
 */
public class JvmDemo {
    public static void main(String[] args) {
        Long maxMemory=Runtime.getRuntime().maxMemory();
        Long freeMemory=Runtime.getRuntime().freeMemory();
        Long totalMemory=Runtime.getRuntime().totalMemory();
        System.out.println("内存总量totalMemory"+totalMemory/1024/1024+"M");
        System.out.println("试图使用运行内存maxMemory"+maxMemory/1024/1024+"M");
        System.out.println("freeMemory"+freeMemory/1024/1024+"M");

    }
}