package com.home.selfLearning.projectOne.dao;

import com.home.selfLearning.projectOne.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    List<Movie> findByTitle(String title);
}
