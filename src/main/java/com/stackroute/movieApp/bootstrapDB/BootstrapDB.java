package com.stackroute.movieApp.bootstrapDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.stackroute.movieApp.domain.Movie;
import com.stackroute.movieApp.repositories.MovieRepository;

@Component
public class BootstrapDB implements ApplicationListener<ContextRefreshedEvent>{
//public class BootstrapDB implements CommandLineRunner{	
private MovieRepository movieRepository;

@Autowired
public BootstrapDB(MovieRepository movieRepository) {
	super();
	this.movieRepository = movieRepository;
}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Movie movie1 = new Movie(1, "Batman begins", 2001, "Director10", "Action" );
		Movie movie2 = new Movie(2, "Batman ends", 2001, "Director10", "Action" );
		movieRepository.save(movie1);
	//	movieRepository.save(movie2);
		
	}

//	@Override
//	public void run(String... args) throws Exception {
//		Movie movie1 = new Movie(3, "Batman jhjkhjk", 2001, "Director10", "Action" );
//        movieRepository.save(movie1);
//
//	}
	
	 

}





