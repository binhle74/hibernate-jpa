package com.binhle.hjpa.inheritance.tableperclass;

import javax.persistence.EntityManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.binhle.hjpa.util.EntityManagerUtil;

public class TablePerClassTest {
    private final static Logger logger = LogManager.getLogger(TablePerClassTest.class);

    private EntityManager entityManager;

    @Before
    public void before() {
        entityManager = EntityManagerUtil.createEntityManager();
    }

    @Test
    public void testTablePerClass() {
        entityManager.getTransaction().begin();
        BillingDetails a = entityManager.find(BillingDetails.class, 2L);
//        TypedQuery<BillingDetails> query = entityManager.createQuery("select bd from BillingDetails bd",
//                BillingDetails.class);
//        List<BillingDetails> a = query.getResultList();
        logger.info("--- BillingDetails: {}", a);
//        CreditCard cc = new CreditCard();
//        cc.cardNumber = "1";
//        cc.expMonth = "02";
//        cc.expYear = "2025";
//        cc.owner = "Binh Le";
//        entityManager.persist(cc);

//        BankAccount ba = new BankAccount();
//        ba.account = "111";
//        ba.bankName = "ACB";
//        ba.swift = "HHH";
//        ba.owner = "BL";
//        entityManager.persist(ba);
        entityManager.getTransaction().commit();
    }

    @After
    public void after() {
        entityManager.close();
    }
}
