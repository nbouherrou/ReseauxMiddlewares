package org.ikya.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *	Class utile aux connexions OpenJPA
 */

public class OpenJPAUtils {
	
	/**
	 *	Factory unique de l'application, pour avoir accès aux entityManager(s)
	 */
	private static EntityManagerFactory entityManagerFactory;
	
	/**
	 *	Pour vérifier que la session factory est montée
	 */
	@SuppressWarnings("unused")
	private static boolean isSetup = false;

	/**
	 * Set Up de la EntityManagerFactory
	 * @throws Exception
	 */
	public static void setUp() throws Exception {

		OpenJPAUtils.entityManagerFactory = Persistence.createEntityManagerFactory("ikya_project");
		
	}
	
	/**
	 * Recuperation d'un EntityManager
	 * @return EntityManager
	 * @throws Exception
	 */
	public static EntityManager openEntityManager() throws Exception {
		
		EntityManager entityManager = OpenJPAUtils.entityManagerFactory.createEntityManager();
		
		return entityManager;

	}
	
	/**
	 * Fermeture d'une connexion EntityManager
	 * @param EntityManager entityManager
	 * @throws Exception
	 */
	public static void closeEntityManager(EntityManager entityManager) throws Exception {

		entityManager.close();

	}
	/**
	 * Fermeture de la entityManagerFactory
	 * @throws Exception
	 */
	public static void tearDown() throws Exception {

		OpenJPAUtils.entityManagerFactory.close();

	}

}
