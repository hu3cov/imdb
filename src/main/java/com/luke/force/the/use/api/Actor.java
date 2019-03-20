package com.luke.force.the.use.api;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ACTORS")
@NamedQueries(
{
    @NamedQuery(
        name = "com.luke.force.the.use.api.Actor.findAll",
        query = "SELECT a FROM Actor a"
    )
})
@Data
public class Actor
{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "NAME", length = 50, nullable = false)
    private String name;
    
    @Column(name = "SURNAME", length = 50, nullable = false)
    private String surname;
    
    @Column(name = "BIRTH_DATE", nullable = true)
    private LocalDate birthDate;

    @ManyToMany(mappedBy = "actors", fetch = FetchType.LAZY)
    private List<Movie> movies;
}
