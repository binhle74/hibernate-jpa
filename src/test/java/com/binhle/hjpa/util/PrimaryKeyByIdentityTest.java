package com.binhle.hjpa.util;

import static org.junit.Assert.assertNotEquals;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.binhle.hjpa.domain.Book;

public class PrimaryKeyByIdentityTest {
    private final static Logger logger = LogManager.getLogger(PrimaryKeyByIdentityTest.class);
    
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
    public void testCreateEntity() {
        entityManager.getTransaction().begin();
        
        Book book = new Book();
        book.setTitle("ABC");
        
        // Execute insert sql immediately
        entityManager.persist(book);
        
        logger.info("--- Commit");
        entityManager.getTransaction().commit();
        
        assertNotEquals(0, book.getId());
    }
}
