package com.learn.demo.lambda;

import java.util.function.Function;

public class LambdaDemo {
    public static void main(String[] args) {
        //Lambda表达式使用方式之一，匿名函数简化
        anonymityDemo();
        //Lambda表达式使用方式之二，把代码逻辑当做参数传递
        int age = getAge(a -> Integer.parseInt(a), "123");
        System.out.println(age);
    }

    //Lambda表达式使用方式之一，匿名函数简化
    public static void anonymityDemo() {
        //使用lambda表达式之前
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable before lambda");
            }
        }).start();
        //使用lambda表达式之后，匿名函数做简化
        new Thread(()->{
            System.out.println("runnable after lambda");
        }).start();
    }

    //Lambda表达式使用方式之二，把代码逻辑当做参数传递
    public static int getAge(Function<String, Integer> function, String args){
        return function.apply(args);
    }

    //expression: 单条语句表达式 (参数) -> 表达式
    //statement: 语句块 (参数) -> {}
    //reference: 方法引用 分4类 ①基于实例方法引用；②构造方法引用；③基于参数实例方法引用；④静态方法引用

    //动态代理
}
