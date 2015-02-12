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
import org.ikya.constants.Constants;
import org.ikya.dao.UserDao;
import org.ikya.entities.Contact;
import org.ikya.entities.User;
import org.ikya.utils.OpenJPAUtils;

/**
 * Servlet implementation class deleteContact
 */
@WebServlet(name = "TraitementContact", urlPatterns = "/TraitementContact")
public class TraitementContact extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(TraitementContact.class);

	@Inject
	private UserDao userDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TraitementContact() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("IN TraitementContact (GET)");
		
		HttpSession session = request.getSession();

		String action = request.getParameter("action");
		
		switch(action){
		
		
		case "accepted":
			
			try{
				
				OpenJPAUtils.setUp();
				
				Integer contactLineID = Integer.parseInt(request.getParameter("contactLineID"));
				
				Contact c = userDao.findContactById(contactLineID);
				
				userDao.changeContactStatut(c.getId(), Constants.CONTACT_ACCEPTE);
				
				User currentUser = (User) session.getAttribute("user");
				
				session.setAttribute("user", userDao.findByUserID(currentUser.getId()));
				
				OpenJPAUtils.tearDown();

			}catch(Exception e){
				
				e.printStackTrace();
				
			}
			
			break;
		
		case "refused":
			
			try{
				
				OpenJPAUtils.setUp();
				
				Integer contactLineID = Integer.parseInt(request.getParameter("contactLineID"));
				
				userDao.deleteContact(userDao.findContactById(contactLineID));
				
				User currentUser = (User) session.getAttribute("user");
				
				session.setAttribute("user", userDao.findByUserID(currentUser.getId()));
				
				OpenJPAUtils.tearDown();

				
			}catch(Exception e){
				
				e.printStackTrace();
				
			}
			
			break;
		
		
		default:
			
			break;
		
		
		
		
		}
		
	
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/MyContacts");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
