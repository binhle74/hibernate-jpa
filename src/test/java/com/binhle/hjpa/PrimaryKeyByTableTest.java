package com.binhle.hjpa;

import static org.junit.Assert.assertNotEquals;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.binhle.hjpa.domain.Author;
import com.binhle.hjpa.domain.Book;
import com.binhle.hjpa.domain.Book2;
import com.binhle.hjpa.util.EntityManagerUtil;

public class PrimaryKeyByTableTest {
    private final static Logger logger = LogManager.getLogger(PrimaryKeyByTableTest.class);

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

        Author author = new Author();
        author.setFirstName("Binh");
        author.setLastName("Le");

        entityManager.persist(author);

        logger.info("-- Commit");
        entityManager.getTransaction().commit();

        assertNotEquals(0, author.getId());
    }

    @Test
    public void testCustomTableGenerator() {
        entityManager.getTransaction().begin();

        Book book = new Book();
        book.setTitle("Book 1");
        
        Book2 book2 = new Book2();
        book2.setTitle("Book 2");
        
        Book book3 = new Book();
        book3.setTitle("Book 3");
        
        Book book4 = new Book();
        book4.setTitle("Book 4");

        entityManager.persist(book);
        entityManager.persist(book2);
        entityManager.persist(book3);
        entityManager.persist(book4);

        logger.info("-- Commit");
        entityManager.getTransaction().commit();

        assertNotEquals(0, book.getId());
    }
}
