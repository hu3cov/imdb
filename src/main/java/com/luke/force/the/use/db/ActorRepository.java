package com.luke.force.the.use.db;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.luke.force.the.use.api.Actor;

import io.dropwizard.hibernate.AbstractDAO;

public class ActorRepository extends AbstractDAO<Actor>
{
    public ActorRepository(SessionFactory sessionFactory)
    {
        super(sessionFactory);
    }
    
    @SuppressWarnings("unchecked")
    public List<Actor> findAll()
    {
        return list((Query<Actor>) namedQuery("com.luke.force.the.use.api.Actor.findAll"));
    }
}
