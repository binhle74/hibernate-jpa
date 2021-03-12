package com.binhle.hjpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.binhle.hjpa.domain.Book;


public class HJPAApplication {
	private final static EntityManagerFactory ENTITY_MANAGER_FACTORY;
	private final static String PERSISTENCE_UNIT_HIBERNATE_JPA = "hibernatejpa-pu";
	
	static {
		ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_HIBERNATE_JPA);
	}
	
	public static void main(String[] args) {
		EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
		entityManager.getTransaction().begin();
		Book book = new Book();
		book.setTitle("ABC");
		entityManager.persist(book);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
