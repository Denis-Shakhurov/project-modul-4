package com.javarush.dao;

import com.javarush.domain.Country;
import com.javarush.domain.CountryLanguage;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CountryDAO {
    private SessionFactory sessionFactory;

    public CountryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Country> getAll() {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Country> criteria = builder.createQuery(Country.class);
        Root<Country> root = criteria.from(Country.class);
        root.fetch("countryLanguages", JoinType.LEFT);
        criteria.select(root);
        return sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
    }
}
