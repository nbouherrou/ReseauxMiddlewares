package org.ikya.servlet;

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
import org.ikya.entities.User;
import org.ikya.utils.OpenJPAUtils;

/**
 * Servlet implementation class SigninServlet
 */
@WebServlet(name = "SigninServlet", urlPatterns = "/signin")
public class SigninServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(SigninServlet.class);

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserDao userDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SigninServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		logger.debug("In SigninServlet (GET)");

		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/signin.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		logger.debug("In SigninServlet (POST)");


		String name 			= request.getParameter("name");
		String email 			= request.getParameter("email");
		String phone 			= request.getParameter("phone");
		String password 		= request.getParameter("password");
		String passwordConfirm  = request.getParameter("passwordConfirm");

		logger.info("POST Name : " 		+ name);
		logger.info("POST Email : " 	+ email);
		logger.info("POST Phone : " 	+ phone);
		logger.info("POST Password : "  + password);
		logger.info("POST PasswordC : " + passwordConfirm);

		try {

			OpenJPAUtils.setUp();

			User user = new User(name, email, password, phone);

			if (userDao.findByPseudo(name) == null
					&& userDao.findByEmail(email) == null) {

				userDao.create(user);
				response.sendRedirect("/ikya/login");

			} else {

				request.setAttribute("message", "Compte existant !");
				request.getRequestDispatcher("/signin.jsp").forward(request,
						response);

			}

			OpenJPAUtils.tearDown();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
