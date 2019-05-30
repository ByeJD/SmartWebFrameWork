package com.quan.bis.service.basic;

import org.junit.Test;

import java.lang.annotation.Target;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: liuquanquan
 * @date : 2019/5/28 10:11
 */
public class Test1 {



    @Test
    public void mapRemoveTest(){
        // 从map.keySet中删除数据会怎么样，map会变化吗
        HashMap<String,String> map = new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        map.put("4","4");

        Set<String> mapKey =  map.keySet();


        Set<String> stringSet = new HashSet<>();
        stringSet.add("1");

        // removeAll差集,retainAll交集
        mapKey.removeAll(stringSet);

        System.out.println(map.size());
    }


    @Test
    /**
     *  有一个字符串的list，要统计其中长度大于7的字符串的数量
     */
    public void test(){
//        List<String> wordList = Arrays.asList("regular", "expression", "specified", "as", "a",
//                "string", "must");
//
//        int countByIterator = 0;
//        for (String word: wordList) {
//            if (word.length() > 7) {
//                countByIterator++;
//            }
//        }
//
//
//        long countByStream = wordList.stream().filter(w -> w.length() > 7).count();
//
//        long countByParallelStream = wordList.parallelStream().
//                filter(w -> w.length() > 7).count();
//
//
//
//
//        // 从Array或者List建Stream
//        Stream<Integer> integerStream = Stream.of(10,20,30,40);
//        String[] cityArr = {"Beijing","Shanghai","Chengdu"};
//        Stream<String> cityStream = Stream.of(cityArr);
//        Stream<String> nameStream = Arrays.asList("Daniel","Peter","Kevin").stream();
//
//        Stream<String> cityStream2 = Arrays.stream(cityArr, 0, 1);
//
//        Stream<String> emptyStream = Stream.empty();
//
//        // 通过generate和iterate创建无穷stream
//        Stream<String> echos = Stream.generate(() -> "echo");
//        Stream<Integer> integers = Stream.iterate(0, num -> num + 1);

        //
        Stream<String> introStream = Stream.of("Get started with UICollectionView and the photo library the"
                .split(" "));
        Map<Integer, String> introMap2 = introStream.collect(Collectors.toMap(s -> s.length(),
                s -> s, (existingValue, newValue) -> existingValue));
        introMap2.forEach((k,v)->{
            System.out.println(k + " ," + v);
        });

    }


    @Test
    public void regularExpression(){
        String str= "1+2";

        // 已数字分割
        String[] temp = str.split("\\d");


        Stream.of(temp).forEach(System.out::println);
    }



    @Test
    public void mapCount(){
        String line = "I am from China . My name is liuquanquan. I like play badminton . This is a ";

        // 打印字符长度大于7字符串
        Stream.of(line.split(" ")).filter(data->data.length() > 7).forEach(System.out::println);
        // 打印字符长度大于7的字符串的个数
        System.out.println(Stream.of(line.split(" ")).filter(data -> data.length() > 7).count());

        // 统计每个字符出现的次数
        List<String> list = Arrays.asList(line.split(" "));
        HashMap<String,Integer> map = new HashMap<>();
        list.forEach(item->{
             map.computeIfPresent(item,(key,value)->value + 1);
             map.putIfAbsent(item,1);
         });
        System.out.println("*****************************");
        map.forEach((k,v)-> System.out.println(k + " , " + v));

        HashMap<String,Integer> map2 = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            String key = list.get(i);
            if (map2.containsKey(key)){
                int count = map2.get(key);
                map2.put(key,count + 1);
                // 错误
                map2.put(key,count++);

                // map2.put(key,++count);
            }else {
                map2.put(key,1);
            }
        }
    }

































}
