package org.ikya.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.apache.commons.collections.ListUtils;
import org.ikya.constants.Constants;
import org.ikya.entities.Challenge;
import org.ikya.utils.OpenJPAUtils;

/*** 
 * DAO pour la gestion des challenges
 */
@Stateless
public class ChallengeDao {

	/**
	 * Create challenge
	 * @param challenge Challenge challenge
	 * @throws Exception Jette une exception standard
	 */
	public void create(Challenge challenge) throws Exception {

		EntityManager em = OpenJPAUtils.openEntityManager();

		EntityTransaction challengeTransaction = em.getTransaction();

		challengeTransaction.begin();

		try{

			em.persist(challenge);

			challengeTransaction.commit();

		} catch(Exception e) {

			System.out.println("SQL[2000] constraint , challenge not created, see your SQL documentation for more details");

		}

		OpenJPAUtils.closeEntityManager(em);

	}

	/**
	 * update challenge
	 * @param challenge  Challenge challenge
	 * @throws Exception Jette une exception standard
	 */
	public void update(Challenge challenge) throws Exception {

		EntityManager em = OpenJPAUtils.openEntityManager();

		EntityTransaction challengeTransaction = em.getTransaction();

		challengeTransaction.begin();

		em.merge(challenge);

		challengeTransaction.commit();

		OpenJPAUtils.closeEntityManager(em);

	}
	
	/**
	 * update winner challenge
	 * @param challenge Challenge challenge
	 * @param idWinner Integer idWinner
	 * @throws Exception Jette une exception standard
	 */
	public void updateWinner(Challenge challenge, Integer idWinner) throws Exception {

		challenge.setId_winner(idWinner);;

		EntityManager em = OpenJPAUtils.openEntityManager();

		EntityTransaction challengeTransaction = em.getTransaction();

		challengeTransaction.begin();

		em.merge(challenge);

		challengeTransaction.commit();

		OpenJPAUtils.closeEntityManager(em);

	}

	/**
	 * update statut challenge
	 * @param challenge Challenge challenge
	 * @param statut Integer statut
	 * @throws Exception Jette une exception standard
	 */
	public void updateStatut(Challenge challenge, Integer statut) throws Exception {

		challenge.setStatut(statut);

		EntityManager em = OpenJPAUtils.openEntityManager();

		EntityTransaction challengeTransaction = em.getTransaction();

		challengeTransaction.begin();

		em.merge(challenge);

		challengeTransaction.commit();

		OpenJPAUtils.closeEntityManager(em);

	}

	/**
	 * set score challenge
	 * @param challenge Challenge challenge
	 * @throws Exception Jette une exception standard
	 */
	public void setScore(Challenge challenge) throws Exception {

		UserDao userDao = new UserDao();

		if(challenge.getStatut() == Constants.CHALLENGE_CHECKED) {

			if(challenge.getId_winner() == -1) {

				userDao.updateScoreById(challenge.getId_contact(), 1);

				userDao.updateScoreById(challenge.getId_user(), 1);
			}
			else {

				userDao.updateScoreById(challenge.getId_winner(), 3);
			}
		}
		else {

			System.out.println("Challenge pas encore terminé");
		}

	}

	/**
	 * find Challenge by ID
	 * @param ID Integer ID
	 * @return Challenge
	 * @throws Exception Jette une exception standard
	 */
	public Challenge findByID(Integer ID) throws Exception {

		EntityManager em = OpenJPAUtils.openEntityManager();

		Challenge result = em.find(Challenge.class, ID);

		OpenJPAUtils.closeEntityManager(em);

		return result;
	}

	/**
	 *  
	 * @param ID Integer ID
	 * @return List of Challenge
	 * @throws Exception Jette une exception standard
	 */
	@SuppressWarnings("unchecked")
	public List<Challenge> findByUserID(Integer ID) throws Exception {

		List<Challenge> result = null;

		EntityManager em = OpenJPAUtils.openEntityManager();

		try{

			TypedQuery<Challenge> query = em.createQuery(

					"SELECT c FROM Challenge c WHERE c.id_user = :id_user", Challenge.class);

			result = query.setParameter("id_user", ID).getResultList();

			TypedQuery<Challenge> query2 = em.createQuery(

					"SELECT c FROM Challenge c WHERE c.id_contact = :id_contact", Challenge.class);

			List<Challenge> result2 = query2.setParameter("id_contact", ID).getResultList();

			result = ListUtils.union(result, result2);

		}catch(Exception e){

			e.printStackTrace();
		}


		OpenJPAUtils.closeEntityManager(em);

		return result;

	}

	/**
	 * IfChallengeExist
	 * @param c Challenge c
	 * @return Boolean
	 * @throws Exception Jette une exception standard
	 */
	public Boolean ifChallengeExist(Challenge c) throws Exception {

		Challenge challenge;

		EntityManager em = OpenJPAUtils.openEntityManager();

		TypedQuery<Challenge> queryChallenge = em.createQuery(

				"SELECT c FROM Challenge c WHERE c.id = :id",Challenge.class);

		try{

			challenge = queryChallenge.setParameter("id",c.getId()).getSingleResult();

		}catch(Exception e){

			challenge = null;
		}

		OpenJPAUtils.closeEntityManager(em);

		Boolean result  = (challenge != null ) ? true  : false ; 

		return result;
	}

	/**
	 * deleteChallenge
	 * @param challenge Challenge challenge
	 * @throws Exception Jette une exception standard
	 */
	public void deleteChallenge(Challenge challenge) throws Exception {
		
		EntityManager em = OpenJPAUtils.openEntityManager();

		EntityTransaction challengeTransaction = em.getTransaction();

		challengeTransaction.begin();
		
		Challenge c = em.find(Challenge.class, challenge.getId());

		em.remove(c);

		challengeTransaction.commit();

		OpenJPAUtils.closeEntityManager(em);

	}

}
