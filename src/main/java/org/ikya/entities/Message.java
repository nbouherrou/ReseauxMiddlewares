package org.ikya.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *	Table des messages
 */

@Entity
@Table(name = "MESSAGE")
public class Message {

	/**
	 *	Id de la table
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	/**
	 *	Id de la messagerie
	 */
	@Column(name = "ID_MESSAGERIE")
	private Integer id_messagerie;
	
	/**
	 *	Id de l'utilisateur
	 */
	@Column(name = "ID_USER")
	private Integer id_user;
	
	/**
	 *	Contenu du message
	 */
	@Column(name = "MESSAGE")
	private String message;
	
	/**
	 *	Date de message
	 */
	@Column(name = "DATE")
	private Date date;

	public Message() {
		super();
	}

	public Message(Integer id_messagerie, Integer id_user, String message) {
		super();
		this.id_messagerie = id_messagerie;
		this.id_user = id_user;
		this.message = message;
		this.date = new Date();
	}
	
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Message [id=");
		builder.append(id);
		builder.append(", id_messagerie=");
		builder.append(id_messagerie);
		builder.append(", id_user=");
		builder.append(id_user);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_messagerie() {
		return id_messagerie;
	}

	public void setId_messagerie(Integer id_messagerie) {
		this.id_messagerie = id_messagerie;
	}

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
