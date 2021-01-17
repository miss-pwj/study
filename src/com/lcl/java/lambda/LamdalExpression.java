package com.lcl.java.lambda;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author lcl
 * @date 2021/1/15 18:29
 * @Description
 */
public class LamdalExpression {

    /*消费型*/
    @Test
    public void test01(){
        //Consumer
        Consumer<Integer> consumer = (x) -> System.out.println("消费型接口" + x);
        //test
        consumer.accept(100);
    }

    /*提供型*/
    @Test
    public void test02(){
        List<Integer> list = new ArrayList<>();
        List<Integer> integers = Arrays.asList(1,2,3);
        list.addAll(integers);
        //Supplier<T>
        Supplier<Integer> supplier = () -> (int)(Math.random() * 10);
        list.add(supplier.get());
        System.out.println(supplier);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    /*函数型*/
    @Test
    public void test03(){
        //Function<T, R>
        String oldStr = "abc123456xyz";
        Function<String, String> function = (s) -> s.substring(1, s.length()-1);
        //test
        System.out.println(function.apply(oldStr));
    }


    /*断言型*/
    @Test
    public void test04(){
        //Predicate<T>
        Integer age = 35;
        Predicate<Integer> predicate = (i) -> i >= 35;
        if (predicate.test(age)){
            System.out.println("你该退休了");
        } else {
            System.out.println("我觉得还OK啦");
        }
    }

    @Test
    public void test05(){

        List list = new ArrayList();
        list.forEach(System.out::println);
        Comparator<Integer> com1 = (x, y) -> Integer.compare(x, y);
        System.out.println(com1.compare(1, 2));

        Comparator<Integer> com2 = Integer::compare;
        System.out.println(com2.compare(2, 1));
    }

    @Test
    public void test06(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Integer integer = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(integer);
    }

}
