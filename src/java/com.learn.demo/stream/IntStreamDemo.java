package com.learn.demo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IntStreamDemo {
    public static void main(String[] args) {
        int[] a = {1,2,3,5,4,3,2,1};
        //分组
        Map<Integer, List<Integer>> collect = Arrays.stream(a).boxed().collect(Collectors.groupingBy(b -> b.intValue()));
        //{1=[1, 1], 2=[2, 2], 3=[3, 3], 4=[4], 5=[5]}
        System.out.println(collect);
        //分组出现次数
        Map<Integer, Long> collect1 = Arrays.stream(a).boxed().collect(Collectors.groupingBy(b -> b.intValue(), Collectors.counting()));
        //{1=2, 2=2, 3=2, 4=1, 5=1}
        System.out.println(collect1);
        //分组求和
        Map<Integer, Integer> collect2 = Arrays.stream(a).boxed().collect(Collectors.groupingBy(b -> b.intValue(), Collectors.summingInt(value -> value.intValue())));
        //{1=2, 2=4, 3=6, 4=4, 5=5}
        System.out.println(collect2);
    }
}
