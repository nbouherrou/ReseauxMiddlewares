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
import org.ikya.dao.MessagerieDao;
import org.ikya.dao.UserDao;
import org.ikya.entities.Messagerie;
import org.ikya.entities.User;
import org.ikya.utils.OpenJPAUtils;

/**
 * Servlet implementation class MessagerieServlet
 */
@WebServlet(name = "MessagerieServlet", urlPatterns = "/Messagerie")
public class MessagerieServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(MessagerieServlet.class);

	@Inject
	private UserDao userDao;

	@Inject
	private MessagerieDao messagerieDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessagerieServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		logger.debug("In MessagerieServlet (GET)");

		try {

			String id = request.getParameter("id");

			Integer idUser = Integer.parseInt(id);

			logger.info("ID : " + idUser);

			HttpSession session = request.getSession();

			User user = (User) session.getAttribute("user");

			logger.info("USER : " + user);

			Messagerie messagerie = new Messagerie(user);

			OpenJPAUtils.setUp();

			messagerieDao.create(messagerie);

			messagerie.addUser(userDao.findByUserID(idUser));
			
			messagerie.addUser(user);
			
			messagerieDao.update(messagerie);

			OpenJPAUtils.tearDown();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/AllMessagerie");
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
