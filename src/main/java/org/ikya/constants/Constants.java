package org.ikya.constants;

import java.util.HashMap;

public class Constants {
	
	/**
	 *	constants for User entity
	 */
	
	public static final 	Integer		CONTACT_NON_VUE 		= 10;
	
	public static final 	Integer		CONTACT_VUE 			= 20;
	
	public static final 	Integer		CONTACT_ACCEPTE 		= 30;
	
	public static final 	Integer		CONTACT_REFUSE 			= 40;
	

	/**
	 *	constants for Challenge entitys
	 */
	
	public static final 	Integer		CHALLENGE_ON_HOLD 		= 10;
	
	public static final 	Integer		CHALLENGE_ACCEPTED 		= 20;
	
	public static final 	Integer		CHALLENGE_REFUSED 		= 30;
	
	public static final 	Integer		CHALLENGE_DONE 			= 40;
	
	public static final 	Integer		CHALLENGE_CHECKED		= 50;
	
	public static final 	HashMap<Integer, String> hashMapChallenge 	= new HashMap<Integer, String>();
	
	public static final 	HashMap<Integer, String> hashMapContact 	= new HashMap<Integer, String>();
	 
	static{
 
		/**
		 *	Challenge 
		 */
		
		hashMapChallenge.put(10, "en attente");
 
		hashMapChallenge.put(20, "accepté");
 
		hashMapChallenge.put(30, "refusé");
 
		hashMapChallenge.put(40, "terminé");
 
		hashMapChallenge.put(50, "validé");
		

		/**
		 *	Contact 
		 */
		
		hashMapContact.put(10, "Non vue");
		 
		hashMapContact.put(20, "Vue");
 
	}
	 
}
