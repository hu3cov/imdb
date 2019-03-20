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
    
    public List<Genre> findGenresByIds(Set<Long> ids)
    {
        Session session = currentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Genre> cr = cb.createQuery(Genre.class);
        Root<Genre> root = cr.from(Genre.class);
        
        Predicate givenGenres = root.get("id").in(ids);
        cr.select(root).where(givenGenres);
        
        Query<Genre> query = session.createQuery(cr);
        List<Genre> results = query.getResultList();
        return results;
    }
}
