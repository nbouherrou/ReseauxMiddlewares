package org.ikya.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.ikya.constants.Constants;

/**
 *	Table des contacts & demandes de contacts
 */

@Entity
@Table(name = "CONTACT")
public class Contact {

	/**
	 *	Id de la table
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	/**
	 *	Id de l'utilisateur qui fait la demande de contact
	 */
	@Column(name = "ID_USER")
	private Integer id_user;

	/**
	 *	Id de l'utilisateur demandé
	 */
	@Column(name = "ID_CONTACT")
	private Integer id_contact;

	/**
	 *	Status de la demande de contact
	 *	@value 		: CONTACT_NON_VUE
	 *	@value 		: CONTACT_VUE
	 *	@value 		: CONTACT_ACCEPTE
	 *	@value 		: CONTACT_REFUSE
	 */
	@Column(name = "STATUT")
	private Integer statut;

	
	
	public Contact(User u1, User u2) {
		super();
		this.id_user 		= u1.getId();
		this.id_contact 	= u2.getId();
		this.statut 		= Constants.CONTACT_NON_VUE;
	}

	public Contact() {
		super();
		this.statut 		= Constants.CONTACT_NON_VUE;
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

	public Integer getId_contact() {
		return id_contact;
	}

	public void setId_contact(Integer id_contact) {
		this.id_contact = id_contact;
	}

	public Integer getStatut() {
		return statut;
	}

	public void setStatut(Integer statut) {
		this.statut = statut;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", id_user=" + id_user + ", id_contact="
				+ id_contact + ", statut=" + statut + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((id_contact == null) ? 0 : id_contact.hashCode());
		result = prime * result + ((id_user == null) ? 0 : id_user.hashCode());
		result = prime * result + ((statut == null) ? 0 : statut.hashCode());
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
		
		Contact other = (Contact) obj;
		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		
		if (id_contact == null) {
			if (other.id_contact != null)
				return false;
		} else if (!id_contact.equals(other.id_contact))
			return false;
		
		
		if (id_user == null) {
			if (other.id_user != null)
				return false;
		} else if (!id_user.equals(other.id_user))
			return false;
		
		
		return true;
	}


}
