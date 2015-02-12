package org.ikya.test;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.ikya.dao.UserDao;
import org.ikya.entities.Contact;
import org.ikya.entities.User;
import org.ikya.utils.OpenJPAUtils;

/**
 * Servlet implementation class HelloAnnotServlet
 */
@WebServlet(name="App", urlPatterns="/App")
public class ServletTestDao extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(ServletTestDao.class);

	// INJECTION of  EJB in this class (WITHOUT USING NEW)
	@Inject
	private UserDao userDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletTestDao() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * URL to use if you want to see the result : http://localhost:8080/tdweb/LivreServlet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		logger.info("DEBUT App");
		try
		{
			OpenJPAUtils.setUp();	
					
			User u1 = new User("Laure", "laure@gmail.com", "Laure", "06677769");
			
			logger.info("Object before persistance" + u1);
			
			userDao.create(u1);
			
			logger.info("Object after persistance" + u1);
	
			User u2 = new User("Kev", "kev@gmail.com", "Kev", "06677769");
			
			logger.info("Object before persistance" +u2);
			
			userDao.create(u2);
			
			logger.info("Object after persistance" +u2);
			
			User u0 = new User("Sarah", "sarah@gmail.com", "Sarah", "06677769");
			
			User u01 = new User("James", "James@gmail.com", "James", "06677769");
			
			userDao.create(u0);
			
			userDao.create(u01);
	
			u1.addContact(u2);
			
			u1.addContact(u0);
			
			u01.addContact(u1);
			
			userDao.update(u1);		
			
			// --------------------------- //
			
			logger.info("Finding step ...");
			
			User u3 = userDao.findByPseudo("Kev");		
			logger.info("User3 userDao.findByName : " + u3);
			
			User u4 = userDao.findByPseudo("Laure");		
			logger.info("User4 userDao.findByName : " + u4);
	
			Contact c1 = u3.findContactByUser(u4);
			logger.info("Contact found  : " + c1);
			
			userDao.deleteContact(c1);
	
			User u5 = userDao.findByPseudo("Laure");	
			logger.info("User5 userDao.findByName : " + u5);
			
			User u6 = userDao.findByPseudo("Kev");		
			logger.info("User6 userDao.findByName : " + u6);
			
			User u7 = userDao.findByPseudo("Sarah");		
			logger.info("User7 userDao.findByName : " + u7);
			
			User u8 = userDao.findByPseudo("James");		
			logger.info("User8 userDao.findByName : " + u8);
			
			// --------------- //
			
			logger.info("ifContactExist : " + userDao.ifContactExist(c1));
			
			logger.info("authenticateUser Kev : " + userDao.authenticateUser("kev@gmail.com", "Kev"));
			
			logger.info("authenticateUser Tom : " + userDao.authenticateUser("tom@gmail.com", "Kev"));
			
			
			logger.info("ifPseudoExist Tom : " + userDao.ifPseudoExist("tom"));			
			logger.info("ifMailExist @Tom : " + userDao.ifMailExist("tom@gmail.com"));
			
			logger.info("ifPseudoExist Sarah : " + userDao.ifPseudoExist("Sarah"));		
			logger.info("ifMailExist @Sarah : " + userDao.ifMailExist("sarah@gmail.com"));
			
			// --------------- //
			
			logger.info(" findContactById  3  : " + userDao.findContactById(3));	
			
			logger.info(" findContactById  20 : " + userDao.findContactById(20));
			
			userDao.changeContactStatut(3, 30);
			
			userDao.changeContactStatut(20, 30);
			
			logger.info(" changeContactStatut  3 : " + userDao.findContactById(3));
			
			logger.info(" findByEmail  : " + userDao.findByEmail("sarah@gmail.com"));
			
			
			OpenJPAUtils.tearDown();
			
			
			logger.info("END App.");
		}
		catch(Exception e)
		{
			
		}
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jspLivres.jsp");
		dispatcher.forward(request, response);
	}


}
