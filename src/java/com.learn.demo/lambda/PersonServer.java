package com.learn.demo.lambda;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PersonServer {
    private static List<Person> personList = new ArrayList<>();

    public static void main(String[] args) {
        //1和2相同
        Person person1 = getPersonByPredicate(a -> a.getName().equals("testName"));
        Person person2 = getName("testName");
        //3和4相同
        Person person3 = getAge(18);
        Person person4 = getPersonByPredicate(a -> a.getAge() == 18 && a.getName().equals("name1"));

    }

    /**
     * 根据姓名找人
     *
     * @param name
     * @return
     */
    public static Person getName(String name) {
        for (Person person : personList) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    /**
     * 根据年龄找人
     *
     * @param age
     * @return
     */
    public static Person getAge(int age) {
        for (Person person : personList) {
            if (person.getAge() == age) {
                return person;
            }
        }
        return null;
    }

    /**
     * 根据传入条件找人
     *
     * @param personPredicate
     * @return
     */
    public static Person getPersonByPredicate(Predicate<Person> personPredicate) {
        for (Person person : personList) {
            if (personPredicate.test(person)) {
                return person;
            }
        }
        //基于stream进行过滤
        personList.stream().filter(personPredicate).collect(Collectors.toList());
        return null;
    }

    /**
     * 遍历输出
     * @param personList
     */
    public static void printName(List<Person> personList){
        for (Person person : personList) {
            System.out.println(person.getName());
        }
        personList.stream().forEach(person -> System.out.println(person.getName()));
    }

    /**
     * 排序
     * @param personList
     */
    public static void sortPerson(List<Person> personList) {
        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        //传统排序
        Collections.sort(personList, (o1, o2) -> o1.getAge() - o2.getAge());
        //基于stream排序
        personList.stream().filter(person -> person.getAge() >= 18).sorted((o1, o2) -> o1.getAge() - o2.getAge()).collect(Collectors.toList());
        //按年龄分组
        Map<Integer, List<Person>> map = personList.stream().collect(Collectors.groupingBy(person -> person.getAge()));
    }

}
