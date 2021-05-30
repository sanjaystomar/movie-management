package com.home.selfLearning.projectOne.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("Without 2011", "Witnesses 2019", "Woman Walks Ahead 2017", "Womb 2010", "Wonder Woman 1984 2020", "Words and Pictures 2013", "Words On Bathroom Walls 2020", "Wreckers 2011", "Wrong Turn 2021", "X Men Days of Future Past 2014");
        String regex = "[0-9]{4}$";
//        Pattern pattern = Pattern.compile("\\bWoman\\b", Pattern.CASE_INSENSITIVE);//"[0-9]{4}$");
        Pattern pattern = Pattern.compile("\\d{0,4}$");
        for (String s : stringList) {
            Matcher matcher = pattern.matcher(s);
            matcher.find();
            System.out.println(matcher.group(0));
            System.out.println(s.substring(0,matcher.start()-1));
        }
      /*  String patt = "Q[^u]\\d+\\.";
        Pattern r = Pattern.compile(patt);        String line = "Order QT300. Now!";
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println(patt + " matches \"" +
                    m.group(0) +
                    "\" in \"" + line + "\"");
        } else {
            System.out.println("NO MATCH");
        }*/


//        stringList.stream()
//                .map(e -> e.replaceAll(regex, ""))
//                .forEach(System.out::println);
    }
}
