package com.javarush.dao;

import com.javarush.domain.City;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CityDAO {
    private SessionFactory sessionFactory;

    public CityDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<City> getItem(int offset, int limit) {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<City> criteria = builder.createQuery(City.class);
        Root<City> root = criteria.from(City.class);
        criteria.select(root);
        Query<City> query = sessionFactory.getCurrentSession().createQuery(criteria);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public int getTotalCount() {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<City> root = criteria.from(City.class);
        criteria.select(builder.count(root));
        Query<Long> query = sessionFactory.getCurrentSession().createQuery(criteria);
        return query.uniqueResult().intValue();
    }

    public City getById(Integer id) {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<City> criteria = builder.createQuery(City.class);
        Root<City> root = criteria.from(City.class);
        root.fetch("country", JoinType.LEFT);
        criteria.select(root).where(builder.equal(root.get("id"), id));
        Query<City> query = sessionFactory.getCurrentSession().createQuery(criteria);
        return query.uniqueResult();
    }

    public List<Integer> getIds(int offset, int limit) {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Integer> criteria = builder.createQuery(Integer.class);
        Root<City> root = criteria.from(City.class);
        criteria.select(root.get("id"));
        Query<Integer> query = sessionFactory.getCurrentSession().createQuery(criteria);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }
}
