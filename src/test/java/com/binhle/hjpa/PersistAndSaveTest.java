package com.binhle.hjpa;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.FlushModeType;
import org.hibernate.internal.SessionImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.binhle.hjpa.domain.Book;
import com.binhle.hjpa.domain.Person;
import com.binhle.hjpa.util.EntityManagerUtil;

public class PersistAndSaveTest {
    private final static Logger logger = LogManager.getLogger(PersistAndSaveTest.class);
    private EntityManager entityManager;

    @Before
    public void before() {
        entityManager = EntityManagerUtil.createEntityManager();
    }

    @After
    public void after() {
        entityManager.close();
    }

    @Test
    public void testPersistTransientEntity() {
        entityManager.getTransaction().begin();
        Person person = new Person();
        person.setFirstName("Binh");
        person.setLastName("Le");
        SessionImpl session = entityManager.unwrap(SessionImpl.class);
        Book book = new Book();
        book.setTitle("ABC");
//        entityManager.persist(book);
//        session.save(book);
//        entityManager.flush();
//        logger.info("Book id={}", book.getId());
//        entityManager.persist(person);
        session.save(person);
//        logger.info("Person id={}", person.getId());
//        session.flush();
        
//        Book book2 = new Book();
//        book2.setTitle("BAC");
//        session.save(book2);
        
//        entityManager.persist(person);
        logger.info("--- After saving");
        entityManager.getTransaction().commit();
    }

}
