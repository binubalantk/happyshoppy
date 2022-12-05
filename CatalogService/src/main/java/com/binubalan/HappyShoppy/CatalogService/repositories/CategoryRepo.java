package com.binubalan.HappyShoppy.CatalogService.repositories;

import com.binubalan.HappyShoppy.CatalogService.abstractions.ICategoryRepo;
import com.binubalan.HappyShoppy.CatalogService.entities.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

@Repository
public class CategoryRepo implements ICategoryRepo {

    @Autowired
    private final SessionFactory sessionFactory;

    public CategoryRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Category> getCategories() {
        Session session = this.sessionFactory.getCurrentSession();
        TypedQuery<Category> query = session.createQuery("SELECT c FROM Category c", Category.class);
        return query.getResultList();
    }

    @Override
    public Category getById(UUID id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Category.class, id);
    }

    @Override
    public Category create(Category category) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.persist(category);
        session.getTransaction().commit();
        return category;
    }

    @Override
    public void remove(Category category) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.remove(category);
        session.getTransaction().commit();
    }
}
