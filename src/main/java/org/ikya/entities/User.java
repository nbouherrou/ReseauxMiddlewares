package org.ikya.entities;

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
import javax.persistence.UniqueConstraint;

/**
 *	Table des utilisateurs
 */

@Entity
@Table(name = "USER", 
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "PSEUDO"),
				@UniqueConstraint(columnNames = "EMAIL")
		})
public class User {

	/**
	 *	Id de la table
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	/**
	 *	Pseudo de l'utilisateur (UNIQUE)
	 */
	@Column(name = "PSEUDO")
	private String pseudo;

	/**
	 *	Email de l'utilisateur (UNIQUE)
	 */
	@Column(name = "EMAIL")
	private String email;

	/**
	 *	Password de l'utilisateur
	 */
	@Column(name = "PASSWORD")
	private String password;

	/**
	 *	Telephone de l'utilisateur
	 */
	@Column(name = "PHONE")
	private String phone;

	/**
	 *	Score de l'utilisateur
	 */
	@Column(name = "SCORE")
	private Integer score;
	
	/**
	 *	Liste de demandes de contacts et contacts de l'utilisateur
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Contact> contacts;

	
	public User(String pseudo, String email, String password, String phone) {
		super();
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.score = 0;
		this.contacts = new HashSet<Contact>();
	}

	public User() {
		super();
		this.contacts = new HashSet<Contact>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	public Set<Contact> getContacts() {
		return contacts;
	}

	public Contact findContactByUser(User user) {
		Contact result = null;
		for (Contact c : contacts) {
			if (c.getId_user().equals(user.getId())
					|| c.getId_contact().equals(user.getId())) {
				result = c;
			}
		}

		return result;

	}

	public void addContact(User user) {

		Contact contact = new Contact(this, user);

		this.contacts.add(contact);
		
	}

	public void removeContact(Contact contact) {
		
		this.contacts.remove(contact);
		
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}


	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((pseudo == null) ? 0 : pseudo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (pseudo == null) {
			if (other.pseudo != null)
				return false;
		} else if (!pseudo.equals(other.pseudo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", pseudo=");
		builder.append(pseudo);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", score=");
		builder.append(score);
		builder.append(", contacts=");
		builder.append(contacts);
		builder.append("]");
		return builder.toString();
	}

}
