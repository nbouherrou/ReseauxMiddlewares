package org.ikya.entities;


import javax.persistence.*;

/**
 *	Table des challenges
 */

@Entity
@Table(name = "CHALLENGE")
public class Challenge {
	
	
	/**
	 *	Id of the challenge
	 */
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "ID" )
	private Integer id;
	
	/**
	 *	Id of the contact who throw a challenge
	 */
	@Column( name = "ID_USER" )
	private Integer id_user;
	
	/**
	 *	Id of the contact who received the challenge
	 */
	@Column( name = "ID_CONTACT" )
	private Integer id_contact;
	
	@Column( name = "DESCRIPTION" )
	private String description;
	
	/**
	 *	Status of the current challenge.
	 *	This attribute only could be 4 final string value
	 *	@value 		: ON_HOLD
	 *	@value 		: ACCEPTED
	 *	@value 		: REFUSED
	 *	@value 		: DONE
	 *	@value 		: CHECKED
	 */
	@Column( name = "STATUT" )
	private Integer statut;
	
	/**
	 * 	In case of victory, the id_winner owner will get 3 pts and he other will get 0 pt.
	 * 	@value 		: id_user or id_contact
	 * 	In case of equality, both users will get 1 pt.
	 * 	@value 		: -1
	 */
	@Column( name = "ID_WINNER" )
	private Integer id_winner;

	
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

	public Integer getId_winner() {
		
		return id_winner;
		
	}

	public void setId_winner(Integer id_winner) {
		
		this.id_winner = id_winner;
		
	}
	
	public String getDescription() {
		
		return description;
		
	}

	public void setDescription(String description) {
		
		this.description = description;
		
	}


	
	public Challenge() {
		
		super();
		
	}

	public Challenge(Integer id_user, Integer id_contact, Integer statut,
			Integer id_winner) {
		super();
		this.id_user = id_user;
		this.id_contact = id_contact;
		this.statut = statut;
		this.id_winner = id_winner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((id_contact == null) ? 0 : id_contact.hashCode());
		result = prime * result + ((id_user == null) ? 0 : id_user.hashCode());
		result = prime * result
				+ ((id_winner == null) ? 0 : id_winner.hashCode());
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
		Challenge other = (Challenge) obj;
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
		if (id_winner == null) {
			if (other.id_winner != null)
				return false;
		} else if (!id_winner.equals(other.id_winner))
			return false;
		if (statut == null) {
			if (other.statut != null)
				return false;
		} else if (!statut.equals(other.statut))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Challenge [id=" + id + ", id_user=" + id_user + ", id_contact="
				+ id_contact + ", statut=" + statut + ", description=" + description + ", id_winner="
				+ id_winner + "]";
	}
	
}
