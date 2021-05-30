package com.home.selfLearning.projectOne.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
//@AllArgsConstructor
//@NoArgsConstructor
@Builder
@Document("raw-movie-collection")
public class Movie {
    @Id
    private String id;
    private String title;
    private String fileName;
    private String location;
    private long sizeInMB;
    private String year;
}
