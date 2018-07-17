package com.stackroute.movieApp.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.movieApp.domain.Movie;
import com.stackroute.movieApp.exception.MovieAlreadyExistException;
import com.stackroute.movieApp.exception.MovieNotFoundException;
import com.stackroute.movieApp.services.MovieService;

@RequestMapping("/api/v1")
@RestController
public class MovieController {
	@Autowired
    private Environment env;
    private MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService) {
        super();
        this.movieService = movieService;
    }
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    
    @PostMapping("/movie")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        try {
            return new ResponseEntity<Movie>(movieService.addMovie(movie),HttpStatus.OK);
        } catch (MovieAlreadyExistException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        }
    
    @GetMapping("/movies")
    public ResponseEntity<?> getMovies() {
		System.out.println(env.getProperty("com.stackroute.user"));
		    logger.debug("This is a debug message");
	        logger.info("This is an info message");
	        logger.warn("This is a warn message");
	        logger.error("This is an error message");
	   return new ResponseEntity<Iterable>(movieService.getAllMovies(), HttpStatus.OK);
    }
    
    @DeleteMapping("/movie/{id}")
    public ResponseEntity<?> deleteMovies(@PathVariable("id") int id, Movie movie) {
        try {
            movieService.deleteMovie(id);
            return new ResponseEntity<Movie>(HttpStatus.OK);

        } catch ( MovieNotFoundException e ) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);

        }
    }
    
    @PutMapping("/movie/{id}")
    public ResponseEntity<?> updateMovies(@PathVariable("id") int id, @RequestBody Movie movie) {
        try {
        return new ResponseEntity<Movie>(movieService.updateMovie(id, movie),HttpStatus.OK);
        }
        catch (MovieNotFoundException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
            }
        }
    
    @GetMapping("/movieId/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable("id") int id) {
        try {
        return new ResponseEntity<Optional<Movie>>(movieService.getMovieById(id),HttpStatus.OK);
        }
        catch (MovieNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);

        }
        }
    
    @GetMapping("/movieTitle/{title}")
    public ResponseEntity<?> getMovieByTitle(@PathVariable("title")String title) {
        try {
        return new ResponseEntity<List<Movie>>(movieService.getMovieByTitle(title),HttpStatus.OK);
        }
        catch (MovieNotFoundException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);

        }
        }
}