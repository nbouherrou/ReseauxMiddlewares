package org.ikya.servlet;

import java.io.IOException;

import javax.inject.Inject;
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
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(LoginServlet.class);
	
	@Inject
	private UserDao userDao;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		logger.debug("In LoginServlet (GET)");
		
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		
		request.getRequestDispatcher("/login.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		logger.debug("In LoginServlet (POST)");
		

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		logger.info("POST Email : " + email);
		logger.info("POST Password : " + password);

		
		try {
			OpenJPAUtils.setUp();
			
			User user = new User();
			
			if (userDao.authenticateUser(email, password) == true) {
				
				user = userDao.findByEmail(email);
				
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				response.sendRedirect("/ikya/index");
				
			}else {
				
				logger.info("User not found");
				
				request.setAttribute("message", "Ce compte n'existe pas !");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
			
			
			OpenJPAUtils.tearDown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
