package com.home.selfLearning.projectOne.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.home.selfLearning.projectOne.dao.MovieRepository;
import com.home.selfLearning.projectOne.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class GenerateJSON {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MovieRepository repository;

    public List<Movie> listMovies() throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        List<String> pathToExploreList = Arrays.asList("F:\\MOVIES","G:\\MOVIES", "H:\\#MOVIES#", "I:\\!MOVIES!");

//        GenerateJSON generateJSON = new GenerateJSON();
        List<Movie> movieList = new ArrayList<>();
        pathToExploreList
                .stream()
                .forEach(e -> {
                    Path startPath = Paths.get(e);
                    System.out.println(startPath);
                    try {
//                        generateJSON.walkFileStream(startPath);
                        movieList.addAll(walkFileStream(startPath));

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
        objectMapper.writeValue(new File("moive.json"), movieList);
        repository.saveAll(movieList);
        return movieList;
    }

    private List<Movie> walkFileStream(Path startPath) throws IOException {
        List<String> extensionList = Arrays.asList("mkv", "avi", "rmvb", "mp4", "m4v", "divx", "VOB");
        return Files.walk(startPath, Integer.MAX_VALUE)
                .filter(fn -> fn.getFileName().toString().contains("."))
                .filter(f -> extensionList.contains(f.getFileName().toString().substring(f.getFileName().toString().lastIndexOf(".") + 1)))
                .filter(e -> e.toFile().length() / (1024 * 1024) > 300)
                .map(e -> Movie
                        .builder()
                        .title(titleAndYearLogic(e)[0])
                        .fileName(e.toFile().getName())
                        .location("file:///".concat(extractLocation(e.toString(), "\\")))
                        .year(titleAndYearLogic(e)[1])
                        .sizeInMB(e.toFile().length() / (1024 * 1024))
                        .build()
                )
                .collect(Collectors.toList());
    }

    private String extractLocation(String s, String s2) {
        return s.substring(0, s.lastIndexOf(s2));
    }

    private String[] titleAndYearLogic(Path e) {
        return getTitleAndYear(extractLocation(e.toFile().getName(), "."));
    }

    private String[] getTitleAndYear(String s) {
        Pattern pattern = Pattern.compile("\\d{4}$");
        Matcher matcher = pattern.matcher(s);
        boolean foundMatch = matcher.find();
        s=s.replaceAll("[0-9]{4}$","").trim();
        return new String[]{s, foundMatch?matcher.group(0):"Not Available"};
    }

}
