package com.luke.force.the.use.db;

import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
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
    
    public List<Actor> findActorsByIds(Set<Long> ids)
    {
        Session session = currentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Actor> cr = cb.createQuery(Actor.class);
        Root<Actor> root = cr.from(Actor.class);
        
        Predicate givenGenres = root.get("id").in(ids);
        cr.select(root).where(givenGenres);
        
        Query<Actor> query = session.createQuery(cr);
        List<Actor> results = query.getResultList();
        return results;
    }
}
