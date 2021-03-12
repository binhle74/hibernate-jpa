package com.binhle.hjpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	private final static EntityManagerFactory ENTITY_MANAGER_FACTORY;
	private final static String PERSISTENCE_UNIT_HIBERNATE_JPA = "hibernatejpa-pu";
	
	static {
		ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_HIBERNATE_JPA);
	}
	
	public static EntityManager createEntityManager() {
		return ENTITY_MANAGER_FACTORY.createEntityManager();
	}
	
	public static void closeEntityManager(EntityManager entityManager) {
		entityManager.close();
	}
}
