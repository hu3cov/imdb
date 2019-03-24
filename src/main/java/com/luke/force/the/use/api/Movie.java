package com.luke.force.the.use.api;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MOVIES")
public class Movie
{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "NAME", length = 50, unique = true, nullable = false)
    private String name;
    
    @Column(name = "STORYLINE", length = 2500, nullable = false)
    private String storyline;
    
    @Column(name = "RELEASE_DATE", nullable = false)
    private LocalDate releaseDate;
    
    @Column(name = "DURATION", nullable = false)
    private Integer duration; // in minutes
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "MOVIE_GENRE", 
               joinColumns = { @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID") }, 
               inverseJoinColumns = { @JoinColumn(name = "GENRE_ID", referencedColumnName = "ID") })
    private List<Genre> genres;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "MOVIE_ACTORS", 
               joinColumns = { @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID") }, 
               inverseJoinColumns = { @JoinColumn(name = "ACTOR_ID", referencedColumnName = "ID") })
    private List<Actor> actors;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStoryline() {
		return storyline;
	}

	public void setStoryline(String storyline) {
		this.storyline = storyline;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
}
