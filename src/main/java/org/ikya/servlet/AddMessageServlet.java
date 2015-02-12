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
import org.ikya.dao.MessagerieDao;
import org.ikya.dao.UserDao;
import org.ikya.entities.Message;
import org.ikya.entities.Messagerie;
import org.ikya.entities.User;
import org.ikya.utils.OpenJPAUtils;

/**
 * Servlet implementation class AddMessageServlet
 */
@WebServlet(name = "AddMessageServlet", urlPatterns = "/AddMessage")
public class AddMessageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(AddServlet.class);

	@Inject
	private UserDao userDao;

	@Inject
	private MessagerieDao messagerieDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddMessageServlet() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		logger.debug("In AddMessageServlet (doPost)");
		
		Message m = new Message();
		
		String message = null;
		
		if (request.getParameter("message") != null) {
			
			message = request.getParameter("message");
		}

		Integer idMessagerie = Integer.parseInt(request
				.getParameter("idMessagerie"));
		
		HttpSession session = request.getSession();
		
		User currentUser = (User) session.getAttribute("user");
		

			try {
				
				OpenJPAUtils.tearDown();

				OpenJPAUtils.setUp();
				
				currentUser = userDao.findByUserID(currentUser.getId());

				Messagerie messagerie = this.messagerieDao
						.findById(idMessagerie);

				if (!message.isEmpty()) {
				
				m.setMessage(message);
				
				messagerie.addMessage(m, currentUser);
				
				messagerieDao.createMessage(m);
				
				}
				

				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			


			

		}
		

		
		 

	

}
