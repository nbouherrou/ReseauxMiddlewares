package org.ikya.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.ikya.entities.Contact;
import org.ikya.entities.Message;
import org.ikya.entities.Messagerie;
import org.ikya.entities.User;
import org.ikya.utils.OpenJPAUtils;

/*** 
 * DAO pour la gestion de la Messagerie
 */
@Stateless
public class MessagerieDao{

	private static Logger logger = Logger.getLogger(MessagerieDao.class);

	
	/**
	 * Create messagerie
	 * @param messagerie Messagerie
	 * @return ID Messagerie
	 * @throws Exception Jette une exception standard
	 */
	public Integer create(Messagerie messagerie) throws Exception {

		Integer result = null;

		logger.info("DEBUT MessagerieDao create");

		EntityManager em = OpenJPAUtils.openEntityManager();

		EntityTransaction messagerieTransaction = em.getTransaction();

		messagerieTransaction.begin();

		try {

			em.persist(messagerie);

			messagerieTransaction.commit();

			result = messagerie.getId();

		} catch (Exception e) {

		}

		OpenJPAUtils.closeEntityManager(em);

		logger.info("FIN MessagerieDao create");

		return result;

	}
	
	/**
	 * Create message
	 * @param message Message
	 * @throws Exception Jette une exception standard
	 */
	public void createMessage(Message message) throws Exception {

		logger.info("DEBUT MessagerieDao create");

		EntityManager em = OpenJPAUtils.openEntityManager();

		EntityTransaction userTransaction = em.getTransaction();

		userTransaction.begin();
		
		try{

			em.persist(message);
			
			userTransaction.commit();
			
		}catch(Exception e){
			
			logger.info("SQL[2000] constraint , user not created, see your SQL documentation for more details");
			
		}

		OpenJPAUtils.closeEntityManager(em);

		logger.info("FIN MessagerieDao create");

	}

	/**
	 * Find Messagerie by user ID
	 * @param idUser Integer (ID User)
	 * @return Set of messagerie
	 */
	public Set<Messagerie> findMessagerieByUserId(Integer idUser) {

		logger.info("DEBUT MessagerieDao findMessagerieByUserId");

		HashSet<Messagerie> m = new HashSet<Messagerie>();

		try {

			EntityManager em = OpenJPAUtils.openEntityManager();

			Query query = em
					.createNativeQuery("SELECT messagerie_id FROM messagerie_user m WHERE m.users_id = ?");

			@SuppressWarnings("unchecked")
			List<Integer> usersList = query.setParameter(1, idUser)
					.getResultList();

			usersList.forEach(id -> {

				try {

					m.add(findById(id));

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});

		} catch (Exception e) {

			e.printStackTrace();
		}

		return m;
	}

	/**
	 * Find Messagerie by ID
	 * @param id Integer (ID Messagerie)
	 * @return Messagerie
	 * @throws Exception Jette une exception standard
	 */
	public Messagerie findById(Integer id) throws Exception {

		logger.info("DEBUT findById");

		Messagerie m = null;

		try {
			EntityManager em = OpenJPAUtils.openEntityManager();

			m = em.find(Messagerie.class, id);

			Set<Message> message = getAllMessageByUserID(m.getId_user());

			Set<User> users = getAllUserByMessagerieId(m.getId());

			if (!message.isEmpty()) {

				m.setMessages(message);
			} else {
				m.setMessages(new HashSet<Message>());
			}

			if (!users.isEmpty()) {

				m.setUsers(users);
			} else {
				m.setUsers(new HashSet<User>());
			}

			OpenJPAUtils.closeEntityManager(em);
		} catch (Exception e) {

		}

		logger.info("FIN findById");

		return m;
	}
	
	/**
	 * Get all messages by user ID
	 * @param ID Integer (ID User)
	 * @return Set of message
	 * @throws Exception Jette une exception standard
	 */
	public Set<Message> getAllMessageByUserID(Integer ID) throws Exception {

		logger.info("DEBUT MessagerieDao getAllMessageByID");

		List<Message> messagesList = null;

		try {
			EntityManager em = OpenJPAUtils.openEntityManager();

			TypedQuery<Message> queryContact = em.createQuery(

			"SELECT m FROM Message m WHERE m.id_user = :id", Message.class);

			messagesList = queryContact.setParameter("id", ID).getResultList();

			OpenJPAUtils.closeEntityManager(em);
		} catch (Exception e) {

		}

		return new HashSet<Message>(messagesList);
	}
	
	/**
	 * Get all messages by messagerie ID
	 * @param messagerieId Integer (ID Messagerie)
	 * @return Set of message
	 * @throws Exception Jette une exception standard
	 */
	public Set<Message> getAllMessageByMessagerieId(Integer messagerieId) throws Exception {

		logger.info("DEBUT MessagerieDao getAllMessageByID");

		List<Message> messagesList = null;

		try {
			
			EntityManager em = OpenJPAUtils.openEntityManager();

			TypedQuery<Message> queryContact = em.createQuery(

			"SELECT m FROM Message m WHERE m.id_messagerie = :id ORDER BY m.id DESC", Message.class);

			messagesList = queryContact.setParameter("id", messagerieId).getResultList();

			OpenJPAUtils.closeEntityManager(em);
			
		} catch (Exception e) {

		}

		return new HashSet<Message>(messagesList);
	}
	
	/**
	 * Get all users by messagerie ID
	 * @param ID Integer (ID Messagerie)
	 * @return Set of user
	 * @throws Exception Jette une exception standard
	 */
	public Set<User> getAllUserByMessagerieId(Integer ID) throws Exception {

		logger.info("DEBUT MessagerieDao getAllUserByMessagerieId");

		HashSet<User> user = new HashSet<User>();

		try {
			EntityManager em = OpenJPAUtils.openEntityManager();

			Query query = em
					.createNativeQuery("SELECT id FROM user u JOIN messagerie_user m ON  u.id = m.users_id  WHERE m.messagerie_id = ?");

			@SuppressWarnings("unchecked")
			List<Integer> usersList = query.setParameter(1, ID).getResultList();

			logger.info("List Integer : " + usersList);

			for (Integer integer : usersList) {

				user.add(findByUserID(integer));
			}

			OpenJPAUtils.closeEntityManager(em);

		} catch (Exception e) {

		}

		return new HashSet<User>(user);
	}

	/**
	 * Find by user ID 
	 * @param ID Integer (ID User)
	 * @return User
	 * @throws Exception Jette une exception standard
	 */
	public User findByUserID(Integer ID) throws Exception {

		EntityManager em = OpenJPAUtils.openEntityManager();

		User result = em.find(User.class, ID);

		HashSet<Contact> contacts = (HashSet<Contact>) getAllContactByUserID(result
				.getId());

		if (contacts != null) {

			result.setContacts(contacts);

		} else {

			result.setContacts(new HashSet<Contact>());

		}

		OpenJPAUtils.closeEntityManager(em);

		return result;

	}

	/**
	 * Get all contacts by user ID
	 * @param ID Integer (ID user)
	 * @return Set of contact
	 * @throws Exception Jette une exception standard
	 */
	public Set<Contact> getAllContactByUserID(Integer ID) throws Exception {

		logger.info("DEBUT MessagerieDao getAllContactByID");

		EntityManager em = OpenJPAUtils.openEntityManager();

		TypedQuery<Contact> queryContact = em.createQuery(

		"SELECT c FROM Contact c WHERE c.id_user = :id OR c.id_contact = :id",
				Contact.class);

		List<Contact> contactslist = queryContact.setParameter("id", ID)
				.getResultList();

		OpenJPAUtils.closeEntityManager(em);

		return new HashSet<Contact>(contactslist);
	}
	

	/**
	 * Get message Date
	 * @param idMessage Integer (ID Message)
	 * @return Date
	 * @throws Exception Jette une exception standard
	 */
	public Date getMessageDate(Integer idMessage) throws Exception {

		logger.info("DEBUT MessagerieDao getAllContactByID");
		
		Date date = null;

		EntityManager em = OpenJPAUtils.openEntityManager();

		TypedQuery<Message> queryContact = em.createQuery(

		"SELECT m FROM Message m WHERE m.id = :id",
			Message.class);

		List<Message> listMessage = queryContact.setParameter("id", idMessage)
				.getResultList();
		
		for (Message message : listMessage) {
			
			date = message.getDate();
		}

		OpenJPAUtils.closeEntityManager(em);

		return date;
	}


	/**
	 * Update messagerie
	 * @param messagerie Integer (ID Messagerie)
	 * @throws Exception Jette une exception standard
	 */
	public void update(Messagerie messagerie) throws Exception {

		logger.info("DEBUT MessagerieDao update");

		EntityManager em = OpenJPAUtils.openEntityManager();

		EntityTransaction userTransaction = em.getTransaction();

		userTransaction.begin();

		em.merge(messagerie);

		userTransaction.commit();

		OpenJPAUtils.closeEntityManager(em);

		logger.info("FIN MessagerieDao update");

	}

}
