package com.luke.force.the.use.api;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "GENRES")
@Data
public class Genre
{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "NAME", length = 50, unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private List<Movie> movies;
}