package org.ikya.test;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.ikya.dao.MessagerieDao;
import org.ikya.dao.UserDao;
import org.ikya.entities.Message;
import org.ikya.entities.Messagerie;
import org.ikya.entities.User;
import org.ikya.utils.OpenJPAUtils;

/**
 * Servlet implementation class MessagerieTestServlet
 */
@WebServlet(name = "MessagerieTestServlet", urlPatterns = "/MessagerieTest")
public class MessagerieTestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger
			.getLogger(MessagerieTestServlet.class);

	@Inject
	private UserDao userDao;

	@Inject
	private MessagerieDao messagerieDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessagerieTestServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			OpenJPAUtils.setUp();	
			
			User user1 = new User("John", "John@ouioui.fr", "pass", "0608080465");
			
			User user2 = new User("Arthur", "Arthur@ouioui.fr", "pass", "0788888888");
			
			User user3 = new User("Louis", "Louis@ouioui.fr", "ssap", "0788888888");
			
			this.userDao.create(user1);
			
			this.userDao.create(user2);
			
			this.userDao.create(user3);
			
			User u3 = userDao.findByPseudo("John");
			

			Messagerie messagerie = new Messagerie(u3);
			
			@SuppressWarnings("unused")
			Integer messagerieId = this.messagerieDao.create(messagerie);
			
			// logger.info("Messagerie " + this.messagerieDao.findById(messagerieId));
			
			Message message = new Message();
			
			logger.info("Messagerie" + messagerie);
			
			logger.info("Message" + message);
			
			messagerie.addMessage(message, u3);
			
			messagerie.addUser(u3);
			
			this.messagerieDao.update(messagerie);
			

			
			OpenJPAUtils.tearDown();

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
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
