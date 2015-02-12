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

import org.ikya.dao.UserDao;
import org.ikya.entities.Contact;
import org.ikya.entities.User;
import org.ikya.utils.OpenJPAUtils;

/**
 * Servlet implementation class deleteContact
 */
@WebServlet(name = "deleteContact", urlPatterns = "/deleteContact")
public class deleteContact extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private UserDao userDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deleteContact() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			OpenJPAUtils.setUp();

			Integer id_contact = Integer.parseInt(request
					.getParameter("idcontact"));

			HttpSession session = request.getSession();

			User currentUser = (User) session.getAttribute("user");
			
			User UserContact = userDao.findByUserID(id_contact);
			
			Contact contact = currentUser.findContactByUser(UserContact);
			
			userDao.deleteContact(contact);
			
			session.setAttribute("user", userDao.findByUserID(currentUser.getId()));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
