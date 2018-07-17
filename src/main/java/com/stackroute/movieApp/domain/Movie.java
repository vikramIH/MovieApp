package com.stackroute.movieApp.domain;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Movie {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	private int year;
	private String director;
	private String genre;
	
	public Movie() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Movie(int id, String title, int year, String director, String genre) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.director = director;
		this.genre = genre;
	}
	
	
	
	
	
}
