package com.learn.demo.lambda;

/**
 * ①加了FunctionalInterface注解
 * ②接口中只有唯一一个抽象方法 自动识别为函数式接口
 * ③如果多余的抽象方法是Object中的方法，不影响第一条规则
 */
@FunctionalInterface
public interface MyFunction {
    void sayHello();
    String toString();
    default void sayHello1(){}
}
