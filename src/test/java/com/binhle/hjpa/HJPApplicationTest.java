package com.binhle.hjpa;

import static org.junit.Assert.assertEquals;

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

public class HJPApplicationTest {
    private final static Logger logger = LogManager.getLogger(HJPApplicationTest.class);
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
    public void test_2Records_Use_Same_Default_Sequence_Generator() {
        entityManager.getTransaction().begin();
        Book book = new Book();
        book.setTitle("ABC1");

        Book2 book2 = new Book2();
        book2.setTitle("ABC2");

        Book book3 = new Book();
        book3.setTitle("ABC3");

        entityManager.persist(book);
        entityManager.persist(book2);
        entityManager.persist(book3);

        entityManager.getTransaction().commit();

        assertEquals(book3.getId() - book.getId(), 2);
        assertEquals(book3.getId() - book2.getId(), 1);
        assertEquals(book2.getId() - book.getId(), 1);
    }

    @Test
    public void testHibernateDefaultSequenceGenerator() {
        entityManager.getTransaction().begin();
        Book book = new Book();
        book.setTitle("ABC");
        entityManager.persist(book);
        logger.info("---> Persist book {}", book.getId());

        Book book2 = new Book();
        book2.setTitle("ABC 2");
        entityManager.persist(book2);

        logger.info("---> Persist book {}", book2.getId());

        entityManager.flush();
        logger.info("---> Start commit books");
        entityManager.getTransaction().commit();
        logger.info("---> After commit books");
    }

    @Test
    public void testHibernateCustomSequenceGenerator() {
        entityManager.getTransaction().begin();
        long initSeqVal = 3;
        // Author Sequence is being configured with initial value 3

        // First author id should be 3
        Author author1 = new Author();
        author1.setFirstName("First Name 1");
        author1.setLastName("Last Name 1");

        // Second author id should be 4
        Author author2 = new Author();
        author2.setFirstName("First Name 2");
        author2.setLastName("Last Name 2");

        // Third author id should be 5
        Author author3 = new Author();
        author3.setFirstName("First Name 3");
        author3.setLastName("Last Name 3");

        entityManager.persist(author1);
        entityManager.persist(author2);
        entityManager.persist(author3);

        entityManager.getTransaction().commit();

        assertEquals(initSeqVal, author1.getId());
        assertEquals(initSeqVal + 1, author2.getId());
    }
}
