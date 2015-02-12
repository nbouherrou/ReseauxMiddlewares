package org.ikya.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.ikya.constants.Constants;
import org.ikya.entities.Contact;
import org.ikya.entities.User;
import org.ikya.utils.OpenJPAUtils;

/***
 * DAO pour la gestion des Users
 */
@Stateless
public class UserDao {

	private static Logger logger = Logger.getLogger(UserDao.class);

	/**
	 * Persiste un user "User" dans la BDD
	 * @param user User a persister
	 * @throws Exception Jette une exception standard
	 */
	public void create(User user) throws Exception {

		logger.info("DEBUT UserDao create");

		EntityManager em = OpenJPAUtils.openEntityManager();

		EntityTransaction userTransaction = em.getTransaction();

		userTransaction.begin();
		
		try{

			em.persist(user);
			
			userTransaction.commit();
			
		}catch(Exception e){
			
			logger.info("SQL[2000] constraint , user not created, see your SQL documentation for more details");
			
		}

		OpenJPAUtils.closeEntityManager(em);

		logger.info("FIN UserDao create");

	}

	/**
	 * Update un utilisateur dans la BDD avec le nouveau "User"
	 * @param user User a update
	 * @throws Exception Jette une exception standard
	 */
	public void update(User user) throws Exception {

		logger.info("DEBUT UserDao update");

		EntityManager em = OpenJPAUtils.openEntityManager();

		EntityTransaction userTransaction = em.getTransaction();

		userTransaction.begin();

		em.merge(user);

		userTransaction.commit();

		OpenJPAUtils.closeEntityManager(em);

		logger.info("FIN UserDao update");

	}
	
	/**
	 * Update un score "Score" avec l'ID de l'utilisateur concerne
	 * @param Id ID du user a update	
	 * @param Score Nouveau score a update
	 * @throws Exception Jette une exception standard
	 */
	public void updateScoreById(Integer Id, Integer Score) throws Exception {

		logger.info("DEBUT UserDao updateScoreById");

		User user = findByUserID(Id);
		
		user.setScore(user.getScore() + Score);
		
		update(user);

		logger.info("FIN UserDao update");

	}

	/**
	 * Retourne un User en fonction du "pseudo"
	 * @param pseudo Pseudo de l'utilisateur a retrouver
	 * @return User correspondant au pseudo
	 * @throws Exception Jette une exception standard
	 */
	public User findByPseudo(String pseudo) throws Exception {

		logger.info("DEBUT findByPseudo");
		
		User result = null;

		EntityManager em = OpenJPAUtils.openEntityManager();

		try{
			
			TypedQuery<User> query = em.createQuery(
					
					"SELECT u FROM User u WHERE u.pseudo = :pseudo", User.class);
			
			result = query.setParameter("pseudo", pseudo).getSingleResult();
			
			result.setContacts(getAllContactByUserID(result.getId()));
			
		}catch(Exception e){
			
			logger.info("Pseudo not found !");
		}


		OpenJPAUtils.closeEntityManager(em);

		logger.info("FIN findByPseudo");

		return result;
	}

	/**
	 * Retourne un User en fonction de son ID
	 * @param ID ID de l'utilisateur a retrouver
	 * @return User correspondant a l'ID
	 * @throws Exception Jette une exception standard
	 */
	public User findByUserID(Integer ID) throws Exception {
		 
		EntityManager em = OpenJPAUtils.openEntityManager();
		
		User result = null;
		
		try{
 
		result = em.find(User.class, ID);
		
		HashSet<Contact> contacts = (HashSet<Contact>)getAllContactByUserID(result.getId());
		
		if (contacts != null) {
			
			result.setContacts(contacts);
			
		} else {
			
			result.setContacts(new HashSet<Contact>()); 
			
		}
		
		}catch(Exception e){
			
		}
 
		OpenJPAUtils.closeEntityManager(em);
 
		return result;	
		
	}
	
	/**
	 * Retourne un User en fonction de son Email
	 * @param email Email de l'utilisateur a retrouver
	 * @return User correspondant au mail
	 * @throws Exception Jette une exception standard
	 */
	public User findByEmail(String email) throws Exception {

		logger.info("DEBUT findByEmail");
		
		User user;

		EntityManager em = OpenJPAUtils.openEntityManager();

		
		try{
			TypedQuery<User> query = em.createQuery(
					
					"SELECT u FROM User u WHERE u.email = :email", User.class);
			
			query.setParameter("email", email);
			
			user = query.getSingleResult();
		 
			user.setContacts(getAllContactByUserID(user.getId()));
			
		}catch(Exception e){
			
			user = null;
		}


		OpenJPAUtils.closeEntityManager(em);

		logger.info("FIN findByEmail");

		return user;
	}
	
	/**
	 * Verifie si le couple Email/Password d'un utilisateur est correct
	 * @param email Email de l'utilisateur
	 * @param password Password de l'utilisateur
	 * @return True si le couple Email/Password existe, false sinon
	 * @throws Exception Jette une exception standard
	 */
	public Boolean authenticateUser(String email, String password) throws Exception {

		logger.info("DEBUT authenticateUser");
		
		User user;

		EntityManager em = OpenJPAUtils.openEntityManager();

		TypedQuery<User> query = em.createQuery(

		"SELECT u FROM User u WHERE u.email = :email AND u.password = :password ", User.class);
		
		query.setParameter("email", email);
		
		query.setParameter("password", password);
		
		try{
			
			user = query.getSingleResult();
		 
		}catch(Exception e){
			
			user = null;
		}

		Boolean result  = (user != null ) ? true  : false ; 

		OpenJPAUtils.closeEntityManager(em);

		logger.info("FIN authenticateUser");

		return result;
	}
	
	/**
	 * Verifie si un pseudo "Pseudo" existe deja dans la BDD
	 * @param pseudo Pseudo a verifier
	 * @return True si le pseudo est present, False sinon
	 * @throws Exception Jette une exception standard
	 */
	public Boolean ifPseudoExist(String pseudo) throws Exception {

		logger.info("DEBUT ifPseudoExist ");
		
		User user;
		EntityManager em = OpenJPAUtils.openEntityManager();

		TypedQuery<User> query = em.createQuery(

		"SELECT u FROM User u WHERE u.pseudo = :pseudo",
		User.class);
		
		try{
			
			user = query.setParameter("pseudo",pseudo).getSingleResult();
		 
		}catch(Exception e){
			
			user = null;
		}
		
		OpenJPAUtils.closeEntityManager(em);
		
		Boolean result  = (user != null ) ? true  : false ; 
		logger.info("FIN ifPseudoExist " + result);
		return result;
	}
	
	/**
	 * Verifie si un email "email" existe deja dans la BDD
	 * @param email Email a verifier
	 * @return True si le pseudo est present, False sinon
	 * @throws Exception Jette une exception standard
	 */
	public Boolean ifMailExist(String email) throws Exception {

		logger.info("DEBUT ifMailExist ");
		
		User user;
		EntityManager em = OpenJPAUtils.openEntityManager();

		TypedQuery<User> query = em.createQuery(

		"SELECT u FROM User u WHERE u.email = :email",
		User.class);
		
		try{
			
			user = query.setParameter("email",email).getSingleResult();
		 
		}catch(Exception e){
			
			user = null;
		}
		
		OpenJPAUtils.closeEntityManager(em);
		
		Boolean result  = (user != null ) ? true  : false ; 
		logger.info("FIN ifMailExist " + result);
		return result;
	}
	
	/**
	 * Retourne un Set de Contact d'un utilisateur en fonction de son ID
	 * @param ID ID de l'utilisateur
	 * @return Set des contacts de l'utilisateur correspondant a l'ID
	 * @throws Exception Jette une exception standard
	 */ 
	public Set<Contact> getAllContactByUserID(Integer ID) throws Exception {

		logger.info("DEBUT UserDao getAllContactByID");

		EntityManager em = OpenJPAUtils.openEntityManager();

		TypedQuery<Contact> queryContact = em.createQuery(

		"SELECT c FROM Contact c WHERE c.id_user = :id OR c.id_contact = :id",
		Contact.class);
		
		List<Contact> contactslist = queryContact.setParameter("id",ID).getResultList();
		
		OpenJPAUtils.closeEntityManager(em);

		return new HashSet<Contact>(contactslist);
	}
	
	/**
	 * Retourne un Set de Contact d'un utilisateur en fonction de son ID
	 * @param User user
	 * @return Set des contacts de l'utilisateur correspondant a l'ID
	 * @throws Exception Jette une exception standard
	 */ 
	public List<User> getAllContactByUser(User user) throws Exception {

		logger.info("DEBUT UserDao getAllContactByUser");
		
		Set<Contact> 		contacts 			=  	user.getContacts();
		
		ArrayList<User> 	listContacts 		= 	new ArrayList<User>();

		for (Contact c : contacts){
			
			Integer id_user 	= c.getId_user();
	
			Integer id_contact 	= c.getId_contact();
			
			if(c.getStatut() > Constants.CONTACT_VUE){
				
				if(!id_user.equals(user.getId())){

					listContacts.add( this.findByUserID(id_user));	
					
				}else{
					
					listContacts.add( this.findByUserID(id_contact));	
				}
				
			}

			
		}			
		
		logger.info("END UserDao getAllContactByUser");

		return listContacts;
	}
	
	/**
	 * Verifie si un contact "c" est present dans la BDD
	 * @param c Contact a verifier
	 * @return True si le contact est present, False sinon
	 * @throws Exception Jette une exception standard
	 */
	public Boolean ifContactExist(Contact c) throws Exception {

		logger.info("DEBUT UserDao ifContactExist");
		
		Contact contact;

		EntityManager em = OpenJPAUtils.openEntityManager();

		TypedQuery<Contact> queryContact = em.createQuery(

		"SELECT c FROM Contact c WHERE c.id = :id",
		Contact.class);
		
		try{
			
			contact = queryContact.setParameter("id",c.getId()).getSingleResult();
		 
		}catch(Exception e){
			
			contact = null;
		}
		
		OpenJPAUtils.closeEntityManager(em);
		
		Boolean result  = (contact != null ) ? true  : false ; 

		return result;
	}

	/**
	 * Supprime un contact "contact" de la BDD
	 * @param contact Contact a supprimer
	 * @throws Exception Jette une exception standard
	 */
	public void deleteContact(Contact contact) throws Exception {

		logger.info("DEBUT UserDao deleteContact");

		if(this.ifContactExist(contact)){

			EntityManager 	em 			= OpenJPAUtils.openEntityManager();
			
			User 			user 		= findByUserID(contact.getId_user());
			
			User 			userC 		= findByUserID(contact.getId_contact());
			
			Contact c = user.findContactByUser(userC);
			
			logger.info("contact to be deleted : " + contact);
			
			user.removeContact(c);
			
			logger.info("after removeContact  : " + user);
			
			update(user);
						
			OpenJPAUtils.closeEntityManager(em);
			
		}else{
		
			logger.info("delete failure ! inexistant contact en DB");
		}

	}
	
	/** 
	 * Retourne un Contact en fonction de l'ID du contact
	 * @param id ID du contact a retourner
	 * @return Le contact correspondant a l'ID
	 * @throws Exception Jette une exception standard
	 */
	public Contact findContactById(Integer id) throws Exception {

		logger.info("DEBUT findContactById");
		
		Contact c;

		EntityManager em = OpenJPAUtils.openEntityManager();

		c = em.find(Contact.class, id);

		OpenJPAUtils.closeEntityManager(em);

		logger.info("FIN findContactById");

		return c;
	}
	
	/**
	 * Modifie le status d'un contact
	 * @param contactID ID du contact a modifier
	 * @param statut code du nouveau status
	 * @throws Exception Jette une exception standard
	 */
	public void changeContactStatut(Integer contactID, Integer statut) throws Exception {

		logger.info("DEBUT changeContactStatut");

		EntityManager em = OpenJPAUtils.openEntityManager();
		
		Contact c = this.findContactById(contactID);
		
		if(c != null){
			
			User user = this.findByUserID(c.getId_user());
			
			user.getContacts().forEach(contact  -> {
				
				if(contact.equals(c)){
					
					contact.setStatut(statut);
					
					logger.info("changeContactStatut contact after modif : " + contact);
					
				}
				
			});
			
			this.update(user);

		}
		
		OpenJPAUtils.closeEntityManager(em);
			
		logger.info("END changeContactStatut");

	}
	
	/**
	 * Retourne une liste de tous les utilisateurs presents en BDD
	 * @return La liste de tous les utilisateurs
	 * @throws Exception Jette une exception standard
	 */
	public List<User> getAll() throws Exception {

		logger.info("DEBUT UserDao getAll");

		EntityManager em = OpenJPAUtils.openEntityManager();

		TypedQuery<User> query = em.createQuery("SELECT u FROM User u",
				User.class);

		List<User> result = query.getResultList();

		OpenJPAUtils.closeEntityManager(em);

		logger.info("FIN UserDao getAll");

		return result;
	}
	
	/**
	 * Retourne une liste de tous les utilisateurs presents en BDD, sauf l'utilisateur avec l'id "ID"
	 * @param ID ID de l'utilisateur
	 * @return La liste de tous les utilisateurs, moins l'utilisateur correspondant a l'ID
	 * @throws Exception Jette une exception standard
	 */
	public List<User> getAll(Integer ID) throws Exception {

		logger.info("DEBUT UserDao getAll ID");

		EntityManager em = OpenJPAUtils.openEntityManager();

		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.id <> :id ORDER by u.score",
				User.class);
		query.setParameter("id", ID);

		List<User> result = query.getResultList();

		OpenJPAUtils.closeEntityManager(em);

		logger.info("FIN UserDao getAll ID");

		return result;
	}

	/**
	 * Supprime un User de la BDD
	 * @param user User a supprimer
	 * @throws Exception Jette une exception standard
	 */
	public void delete(User user) throws Exception {

		logger.info("DEBUT UserDao delete");

		EntityManager em = OpenJPAUtils.openEntityManager();

		EntityTransaction userTransaction = em.getTransaction();

		userTransaction.begin();
		
		User u = em.find(User.class, user.getId());

		em.remove(u);

		userTransaction.commit();

		OpenJPAUtils.closeEntityManager(em);

		logger.info("FIN UserDao delete");

	}

}
