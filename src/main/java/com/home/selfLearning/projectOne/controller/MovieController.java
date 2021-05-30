package com.home.selfLearning.projectOne.controller;

import com.home.selfLearning.projectOne.dao.MovieRepository;
import com.home.selfLearning.projectOne.model.Movie;
import com.home.selfLearning.projectOne.service.GenerateJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("movie")
public class MovieController {
    @Autowired
    private GenerateJSON generateJSON;

    @Autowired
    private MovieRepository repository;

    @GetMapping(value = "load")
    public List<Movie> loadMoiveDetails() throws IOException {
        return generateJSON.listMovies();
    }

    @GetMapping(value = "list-movies")
    public List<Movie> movieList() throws IOException {
        return repository.findAll();
    }

    @GetMapping(value = "search/{title}")
    public List<Movie> searchMOvie(@PathVariable String title) {
        return repository.findByTitle(title);
    }
}
