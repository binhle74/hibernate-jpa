package com.binhle.hjpa.inheritance.associations.manytoone;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.binhle.hjpa.util.EntityManagerUtil;

public class PolymorphicAssociationTest {
    private final static Logger logger = LogManager.getLogger(PolymorphicAssociationTest.class);

    private EntityManager entityManager;

    @Before
    public void before() {
        entityManager = EntityManagerUtil.createEntityManager();
    }

    @Test
    public void testMany2OneAssociations() {
        entityManager.getTransaction().begin();
        CreditCard cc = new CreditCard();
        cc.cardNumber = "111";
        cc.expMonth = "12";
        cc.expYear = "2025";
        cc.owner = "Binh Le";
        entityManager.persist(cc);

        User user = new User();
        user.username = "binhle";
        user.firstName = "Binh";
        user.lastName = "Le";
        user.defaultBilling = cc;
        entityManager.persist(user);

        entityManager.getTransaction().commit();

        User queriedUser = entityManager.find(User.class, user.id);

        logger.info("===> Queried user {}", queriedUser);
        logger.info("===> Queried user Id {}", queriedUser.id);
        logger.info("===> Queried Default Billing {}", queriedUser.defaultBilling);

        assertNotNull(queriedUser);
    }

    @Test
    public void testQueryUser() {
        User user = entityManager.find(User.class, 2L);
        logger.info("===> Queried user: {}", user);

        BillingDetails defaultBilling = user.getDefaultBilling();
        logger.info("===> DefaultBilling: {}", defaultBilling);
        assertFalse(defaultBilling instanceof CreditCard);
    }

    @Test
    public void testProxySafeTypeCast() {
        User user = entityManager.find(User.class, 2L);
        logger.info("===> Queried user: {}", user);

        BillingDetails defaultBilling = user.getDefaultBilling();

        CreditCard cc = entityManager.getReference(CreditCard.class, defaultBilling.getId());
        assertTrue(cc instanceof CreditCard);
    }

    @Test
    public void testEagerFetchQuery() {
        User user = entityManager
                .createQuery("select u from Users u left join fetch u.defaultBilling where u.id = :id", User.class)
                .setParameter("id", 2L).getSingleResult();
        logger.info("===> Queried user: {}", user);

        BillingDetails defaultBilling = user.getDefaultBilling();

        assertTrue(defaultBilling instanceof CreditCard);
    }

    private List<User> getAll() {
        TypedQuery<User> query = entityManager
                .createQuery("select usr from com.binhle.hjpa.inheritance.associations.manytoone.User usr", User.class);
        List<User> userList = query.getResultList();
        return userList;
    }

    protected void cleanData() {
        entityManager.getTransaction().begin();
        List<User> userList = getAll();
        for (User b : userList) {
            entityManager.remove(b);
        }
        entityManager.getTransaction().commit();
    }

    @After
    public void after() {
//        cleanData();
        entityManager.close();
    }
}
