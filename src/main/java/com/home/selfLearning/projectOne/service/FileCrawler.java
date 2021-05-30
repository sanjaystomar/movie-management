package com.home.selfLearning.projectOne.service;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Component
public class FileCrawler {
    public static void main(String[] args) throws IOException {
        File directories[] = File.listRoots();
        Arrays.stream(directories).forEach(System.out::println);
        List<String> pathToExploreList = Arrays.asList("F:\\MOVIES","G:\\MOVIES","H:\\#MOVIES#","I:\\!MOVIES!");
//        List<String> pathToExploreList = Arrays.asList("F:\\MOVIES\\moviesFEB2019");

        try {
//            DirectoryStream<Path> stream;
//            stream = Files.newDirectoryStream(startPath);
//            stream.forEach(e -> System.out.println("FileSystem: " + e.getFileSystem() +" FileName: "+ e.getFileName() + " Path: " + e.getRoot()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Files.walk(startPath,Integer.MAX_VALUE).forEach(e -> System.out.println("FileName: "+ e.getFileName() + " Path: " + e.getFileName().toAbsolutePath() + " Extension: " + e.toFile().isFile()));
            FileWriter fileWriter = new FileWriter("OutputFile.csv");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("FileName" + ", FilePath" + ", Size in MB");
            FileCrawler fileCrawler = new FileCrawler();
            pathToExploreList
                    .stream()
                    .forEach(e -> {
                        Path startPath = Paths.get(e);
                        System.out.println(startPath);
//                        printWriter.println(startPath + "################>>>>>>>>");
                        try {
                            fileCrawler.walkFileStream(startPath, printWriter);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        Files.walk(startPath, Integer.MAX_VALUE)
//                .filter( f -> f.getFileName().toFile().isFile())
//                .map(fp -> fp.getFileName().toString())
//                .filter(fn -> fn.getFileName().toString().contains("."))
//.peek(System.out::println)
//                .map(e -> e.getFileName().toString().substring(e.getFileName().toString().lastIndexOf(".")+1))
//                .forEach(System.out::println);
    }

    private void walkFileStream(Path startPath, PrintWriter printWriter) throws IOException {
        List<String> extensionList = Arrays.asList("mkv", "avi", "rmvb", "mp4", "m4v", "divx", "VOB");
//        List<String> extensionList = Arrays.asList("txt", "nfo", "jpg");
        Files.walk(startPath, Integer.MAX_VALUE)
                .filter(fn -> fn.getFileName().toString().contains("."))
                .filter(f -> extensionList.contains(f.getFileName().toString().substring(f.getFileName().toString().lastIndexOf(".") + 1)))
                .filter(e -> e.toFile().length()/(1024*1024)>300)
                .forEach(e -> {
                        printWriter.println(e.toFile().getName() + "," + e + "," + e.toFile().length() / (1024 * 1024));
//                        printWriter.println(e.toFile().getName() + "," + e);
                });
    }

}
