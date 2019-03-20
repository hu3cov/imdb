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

import lombok.Data;

@Entity
@Table(name = "MOVIES")
@Data
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
}
