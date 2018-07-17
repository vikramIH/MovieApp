package com.stackroute.movieApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.movieApp.domain.Movie;
import com.stackroute.movieApp.exception.MovieAlreadyExistException;
import com.stackroute.movieApp.exception.MovieNotFoundException;
import com.stackroute.movieApp.repositories.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {
    
    
    private MovieRepository movierepository;
    @Autowired
    public MovieServiceImpl(MovieRepository movierepository) {
        super();
        this.movierepository = movierepository;
    }

    @Override
    public Movie addMovie(Movie movie) throws MovieAlreadyExistException {
        if(!movierepository.findByTitle(movie.getTitle()).isEmpty()) {
            throw new MovieAlreadyExistException("Movie already exists");

        }
        else
        {
            return movierepository.save(movie);

        }
    }

    @Override
    public Iterable<Movie> getAllMovies() {
        return movierepository.findAll();
    }

    @Override
    public void deleteMovie(int id) throws MovieNotFoundException {
        if(movierepository.existsById(id)){
            movierepository.deleteById(id);
        }
        else
        {
            throw new MovieNotFoundException("Movie not found");
        }
    }

    @Override
    public Movie updateMovie(int id, Movie movie) throws MovieNotFoundException{
        if(movierepository.existsById(id)){
            movie.setId(id);
            return movierepository.save(movie);
        }
        else
        {
            throw new MovieNotFoundException("Movie not found");
        }
        
        
    }

    @Override
    public Optional<Movie> getMovieById(int id) throws MovieNotFoundException{
        if(movierepository.existsById(id)){
            return movierepository.findById(id);
        }
        else
        {
            throw new MovieNotFoundException("Movie not found");
        }
    }

    @Override
    public List<Movie> getMovieByTitle(String title) throws MovieNotFoundException{
        if(!movierepository.findByTitle(title).isEmpty()){
            return (List<Movie>) movierepository.findByTitle(title);
        }
        else
        {
            throw new MovieNotFoundException("Movie not found");
        }
    }

}