package com.binhle.hjpa.inheritance.associations.onetomany;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.binhle.hjpa.util.EntityManagerUtil;

public class PolymorphicCollectionTest {
    private final static Logger logger = LogManager.getLogger(PolymorphicCollectionTest.class);

    private EntityManager entityManager;

    @Before
    public void before() {
        entityManager = EntityManagerUtil.createEntityManager();
    }

    @Test
    public void testQueryUser() {
        User user = entityManager.find(User.class, 3L);
        logger.info("===> Queried user: {}", user);
        Set<BillingDetails> billingDetails = user.billingDetails;
        logger.info("===> Queried billings: {}", billingDetails);
        
        assertNotNull(user);
        assertFalse(billingDetails.isEmpty());
    }

    @Test
    public void testPolymorphicCollection() {
        entityManager.getTransaction().begin();

        User user = new User();
        user.username = "binhle";

        CreditCard cc = new CreditCard();
        cc.cardNumber = "111";
        cc.expMonth = "11";
        cc.expYear = "2020";
        cc.owner = user;

        BankAccount ba = new BankAccount();
        ba.account = "A22";
        ba.bankName = "ABC";
        ba.swift = "112";
        ba.owner = user;

        entityManager.persist(user);
        entityManager.persist(cc);
        entityManager.persist(ba);

        user.billingDetails.add(cc);
        user.billingDetails.add(ba);

        entityManager.getTransaction().commit();
    }

    @After
    public void after() {
        entityManager.close();
    }
}
