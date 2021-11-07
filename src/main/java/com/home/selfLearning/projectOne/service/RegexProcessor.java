package com.home.selfLearning.projectOne.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexProcessor {
    /*
    * [YTS.AM]
  [YTS.MX]
  [(.*?)]		==>> () and .
  \[([\w\s]+?)\]		==> anything between []

				 [\(|\)]|\[([\w(\s|\.)]+?)\]				==>>		THIS DOES THIS ::# =>  Boogeyman (2005) [1080p] [BluRay] [5.1] [YTS.MX] 		==>	Boogeyman 2005
				 *
				 * https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
				 * https://regex101.com/
				 * https://regexone.com/lesson/capturing_groups
				 * https://www.vogella.com/tutorials/JavaRegularExpressions/article.html
     */

    public static void main(String[] args) throws IOException {
        Path filePath = Path.of("C:\\Users\\sanja\\Documents\\my-workspace\\src\\main\\resources\\regex-experiment.txt");//new File("C:\\Users\\sanja\\Documents\\my-workspace\\src\\main\\resources\\regex-experiment.txt");
        Files.readAllLines(filePath)
                .stream()
                .map(line -> line.replaceAll("[\\(|\\)]|\\[([\\w(\\s|\\.)]+?)\\]", ""))
                .map(line -> line.replaceAll("[\\.]"," "))
                //.map(line -> line.substring()//l("[0-9]{4}", ""))
                .map(RegexProcessor::trimAfterYear)
                .forEach(System.out::println);

    }

    private static String trimAfterYear(String s) {
        Pattern pattern = Pattern.compile("\\d{4}");
        Matcher matcher = pattern.matcher(s);
        matcher.matches();
        int end = matcher.end();
        return s.substring(end);
    }
}
