package com.binhle.hjpa.inheritance.mappedsuperclass;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.binhle.hjpa.util.EntityManagerUtil;

public class ImplicitInheritanceTest {
    private final static Logger logger = LogManager.getLogger(ImplicitInheritanceTest.class);
    
    private EntityManager entityManager;
    
    @Before
    public void before() {
        entityManager = EntityManagerUtil.createEntityManager();
    }
    
    @Test
    public void testMappedSuperClass() {
        logger.info("----DAHDS");
//        entityManager.getTransaction().begin();
//        BillingDetails a = entityManager.find(BillingDetails.class, 1);
        TypedQuery<BillingDetails> query = entityManager.createQuery("select bd from BillingDetails", BillingDetails.class);
        List<BillingDetails> a = query.getResultList();
        logger.info("-------- ABC {}", a);
//        CreditCard cc = new CreditCard();
//        cc.cardNumber = "1";
//        cc.expMonth = "02";
//        cc.expYear = "2025";
//        cc.owner = "Binh Le";
//        entityManager.persist(cc);
//        entityManager.getTransaction().commit();
    }
    
    @After
    public void after() {
        entityManager.close();
    }
}
