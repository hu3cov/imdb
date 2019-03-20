package com.luke.force.the.use.db;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.NotFoundException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.luke.force.the.use.api.Actor;
import com.luke.force.the.use.api.Movie;

import io.dropwizard.hibernate.AbstractDAO;

public class MovieRepository extends AbstractDAO<Movie>
{
    public MovieRepository(SessionFactory sessionFactory)
    {
        super(sessionFactory);
    }
    
    public List<Movie> filterMovies(Integer releaseYear, Integer duration, Long actorId) 
    {
        Session session = currentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Movie> cr = cb.createQuery(Movie.class);
        Root<Movie> root = cr.from(Movie.class);
        
        if(actorId != null)
        {
            Join<Movie, Actor> actorsJoin = root.join("actors", JoinType.INNER);
            actorsJoin.on(cb.equal(actorsJoin.get("id"), actorId));
        }
        
        Predicate greaterEqThanGivenYear = cb.greaterThan(root.get("releaseDate"), LocalDate.of(releaseYear, 1, 1));
        Predicate lessEqThanGivenDuration = cb.le(root.get("duration"), duration);
        cr.select(root).where(cb.and(greaterEqThanGivenYear, lessEqThanGivenDuration)).orderBy(cb.asc(root.get("id")));;
        
        Query<Movie> query = session.createQuery(cr);
        List<Movie> results = query.getResultList();
        return results;
    }
    
    public Optional<Movie> findById(Long id)
    {
        return Optional.ofNullable(get(id));
    }
    
    public void delete(Long id)
    {
        Movie movie = findById(id).orElseThrow(() -> new NotFoundException("Could not find movie with id = " + id));
        currentSession().delete(movie);
    }
}
