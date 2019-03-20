package com.luke.force.the.use.db;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.luke.force.the.use.api.Genre;

import io.dropwizard.hibernate.AbstractDAO;

public class GenreRepository extends AbstractDAO<Genre>
{
    public GenreRepository(SessionFactory sessionFactory)
    {
        super(sessionFactory);
    }
    
    @SuppressWarnings("unchecked")
    public List<Genre> findAll()
    {
        return list((Query<Genre>) namedQuery("com.luke.force.the.use.api.Genre.findAll"));
    }
}
