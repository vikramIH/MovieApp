package com.stackroute.movieApp.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.movieApp.domain.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, Integer>  {
	
	//@Query("select m from Movie m where m.getTitle = :title")
	List<Movie> findByTitle(String title);

}
