package org.ikya.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.log4j.Logger;

/**
 *	Table des messageries
 */

@Entity
@Table(name = "MESSAGERIE")
public class Messagerie {
	
	private static Logger logger = Logger.getLogger(Messagerie.class);
	
	/**
	 *	Id de la table
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	/**
	 *	Id de l'utilisateur (proprietaire de la messagerie)
	 */
	@Column(name = "ID_USER")
	private Integer id_user;
	
	/**
	 *	liste des utilisateurs participants à la cette messagerie
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<User> users;
	
	/**
	 *	Set des messages de cette messagerie
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Message> messages;

	public Messagerie(User user) {
		super();
		this.id_user = user.getId();
		this.users = new HashSet<User>();
		this.messages = new HashSet<Message>();
	}

	public Messagerie() {
		super();
		this.users = new HashSet<User>();
		this.messages = new HashSet<Message>();
	}
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Messagerie [id=");
		builder.append(id);
		builder.append(", id_user=");
		builder.append(id_user);
		builder.append(", messages=");
		builder.append(messages);
		builder.append("]");
		return builder.toString();
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Message> getMessages() {
		return messages;
	}
	
	public void addUser(User user) {

		this.users.add(user);
		
	}
	
	public void removeUser(User user) {

		this.users.remove(user);
		
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public void addMessage(Message message, User user) {
		
		message.setId_messagerie(this.getId());
		
		message.setId_user(user.getId());
		
		Date date = new Date();
		
		message.setDate(date);
		
		logger.info("Add Message " + message);
		
		this.messages.add(message);
	}
	
	
	
	public void removeMessage(Message message) {

		this.messages.remove(message);
	}
	
	

}
