package com.binhle.hjpa.inheritance.mappedsuperclass;

import javax.persistence.EntityManager;

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
        BillingDetails a = entityManager.find(BillingDetails.class, 1);
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
