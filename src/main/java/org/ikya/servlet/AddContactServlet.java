package org.ikya.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.ikya.dao.UserDao;
import org.ikya.entities.User;
import org.ikya.utils.OpenJPAUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "AddContactServlet", urlPatterns = "/AddContact")
public class AddContactServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(AddContactServlet.class);
	
	@Inject
	private UserDao userDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddContactServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		logger.debug("In AddContactServlet (GET)");	
		
		try {
			
			OpenJPAUtils.setUp();
		
			Integer 	id_contact 		= 	Integer.parseInt(request.getParameter("idcontact"));
			
			HttpSession session 		= 	request.getSession();
			
			User 		currentUser		= 	(User) session.getAttribute("user");
			
			currentUser.addContact(userDao.findByUserID(id_contact));
			
			userDao.update(currentUser);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/allutilisateur");
		dispatcher.forward(request, response);

	}

	

}
