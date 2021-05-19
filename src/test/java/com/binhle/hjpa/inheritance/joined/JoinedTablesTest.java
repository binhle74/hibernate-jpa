package com.binhle.hjpa.inheritance.joined;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.binhle.hjpa.util.EntityManagerUtil;

public class JoinedTablesTest {
    private final static Logger logger = LogManager.getLogger(JoinedTablesTest.class);

    private EntityManager entityManager;

    @Before
    public void before() {
        entityManager = EntityManagerUtil.createEntityManager();
        entityManager.getTransaction().begin();
    }
    
    @Test
    public void testJoinedTablesStrategy() {
        // Create
        List<Long> creditCardList = createCreditCard(1).stream()
                .map(billingDetails -> billingDetails.id)
                .collect(Collectors.toList());
        logger.info("---> Credit Card: {}", creditCardList);
        
        List<Long> bankAccountList = createBankAccount(1).stream()
                .map(bankAccount -> bankAccount.id)
                .collect(Collectors.toList());
        logger.info("---> Bank Account: {}", bankAccountList);

        // Verify
        List<Long> billingDetailsList = getAll().stream()
                .map(billingDetails -> billingDetails.id)
                .collect(Collectors.toList());
        logger.info("---> All: {}", billingDetailsList);
        
        assertFalse(billingDetailsList.isEmpty());
        assertFalse(creditCardList.isEmpty());
        assertFalse(bankAccountList.isEmpty());
        assertTrue(billingDetailsList.containsAll(creditCardList));
        assertTrue(billingDetailsList.containsAll(bankAccountList));
    }

    private List<CreditCard> createCreditCard(int numOfEntity) {
        List<CreditCard> creditCardList = new ArrayList<>();
        int count = numOfEntity > 0 ? numOfEntity : 1;
        for (int i = 0; i < count; i++) {
            CreditCard cc = new CreditCard();
            cc.cardNumber = String.format("%s", i + 1);
            cc.expMonth = String.format("%s", (i + 1) % 12);
            cc.expYear = "2025";
            cc.owner = String.format("Binh Le CC%s", i + 1);
            entityManager.persist(cc);
            creditCardList.add(cc);
        }
        return creditCardList;
    }

    private List<BankAccount> createBankAccount(int numOfEntity) {
        List<BankAccount> bankAccountList = new ArrayList<>();
        int count = numOfEntity > 0 ? numOfEntity : 1;
        for (int i = 0; i < count; i++) {
            BankAccount ba = new BankAccount();
            ba.account = String.format("001%s", i + 1);
            ba.bankName = "ABC";
            ba.swift = String.format("UI%s", i + 1);
            ba.owner = String.format("Binh Le%s", i + 1);
            entityManager.persist(ba);
            bankAccountList.add(ba);
        }
        return bankAccountList;
    }

    private List<BillingDetails> getAll() {
        TypedQuery<BillingDetails> query = entityManager.createQuery(
                "select bd from com.binhle.hjpa.inheritance.joined.BillingDetails bd", BillingDetails.class);
        List<BillingDetails> billingDetailsList = query.getResultList();
        return billingDetailsList;
    }

    @After
    public void after() {
        List<BillingDetails> billingDetailsList = getAll();
        for (BillingDetails b : billingDetailsList) {
            entityManager.remove(b);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
