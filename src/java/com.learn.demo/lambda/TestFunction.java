package com.learn.demo.lambda;

import java.util.function.Function;

public class TestFunction {


    interface MyInterface{
        void sayHello(String name, String content);
    }
    interface MyInterface1{
        String sayHello(String name, String content);
    }

    public static void test1(){
        Function<String, String> function = t -> t;
        System.out.println(function.apply("hello world"));
    }

    public static void test2(){
        MyInterface myInterface = (a, b) -> System.out.println(a + b);
        myInterface.sayHello("hello", " world!");
    }
    public static void test3(){
        MyInterface1 myInterface1 = (a, b) -> {
            System.out.println(a);
            System.out.println(b);
            return a+b;
        };
        myInterface1.sayHello("hello", " world!");
    }
}
