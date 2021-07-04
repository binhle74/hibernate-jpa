package com.binhle.hjpa;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.binhle.hjpa.util.EntityManagerUtil;

public abstract class BaseTest {
    protected final Logger logger = LogManager.getLogger(this.getClass());

    private EntityManager entityManager;

    public abstract void before();
    public abstract void after();

    public void closeEntityManager() {
        if (Objects.nonNull(entityManager) && entityManager.isOpen()) {
            entityManager.close();
        }
    }

    public EntityManager getEntityManager() {
        if (Objects.isNull(entityManager) || !entityManager.isOpen()) {
            entityManager = EntityManagerUtil.createEntityManager();
        }
        return entityManager;
    }

    public BaseTest beginTransaction() {
        EntityManager entityManager = this.getEntityManager();
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        return this;
    }

    public BaseTest commitTransaction() {
        EntityManager entityManager = this.getEntityManager();
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().commit();
        }
        return this;
    }

    public <T> BaseTest persist(T entity) {
        EntityManager entityManager = this.getEntityManager();
        entityManager.persist(entity);
        return this;
    }

    public <T> BaseTest remove(T entity) {
        EntityManager entityManager = this.getEntityManager();
        this.beginTransaction();
        entityManager.remove(entity);
        this.commitTransaction();
        return this;
    }

    public <T> void cleanData(Class<T> clazz) {
        EntityManager entityManager = this.getEntityManager();
        beginTransaction();
        List<T> entities = findAll(clazz);
        entities.forEach(entity -> entityManager.remove(entity));
        commitTransaction();
    }

    public <T> List<T> findAll(Class<T> clazz) {
        EntityManager entityManager = this.getEntityManager();
        String sql = String.format("select o from %s o", clazz.getName());
        logger.info("--- Fetch all entity by SQL={}", sql);
        TypedQuery<T> query = entityManager.createQuery(sql, clazz);
        List<T> entities = query.getResultList();
        return entities;
    }

    public <T> T find(Class<T> clazz, Object primaryKey) {
        EntityManager entityManager = this.getEntityManager();
        return entityManager.find(clazz, primaryKey);
    }

    protected String concat(String str1, String str2) {
        return str1 + (str1 == "" ? "" : ",") + str2;
    }
}
