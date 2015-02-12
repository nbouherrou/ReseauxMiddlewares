package org.ikya.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.ikya.dao.MessagerieDao;
import org.ikya.entities.Message;
import org.ikya.entities.Messagerie;
import org.ikya.entities.User;
import org.ikya.utils.OpenJPAUtils;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet(name = "MessageServlet", urlPatterns = "/mess")
public class MessageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(MessageServlet.class);

	@Inject
	private MessagerieDao messagerieDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.debug("In MessageServlet (GET)");
		
		Integer idMessagerie = Integer.parseInt(request.getParameter("idMessagerie"));
		
		Set<User> usersList = null;
		
		logger.info("MessageServlet idMessagerie " + idMessagerie);
		
		try {
			
			
			OpenJPAUtils.setUp();
			
			Messagerie messagerie = this.messagerieDao.findById(idMessagerie);
			
			logger.info("Messagerie : " + messagerie);
			
			usersList = this.messagerieDao.getAllUserByMessagerieId(idMessagerie);
			
			
			
			logger.info("Users : " + usersList);
			
			HashSet<String> pseudoUsers = new HashSet<String>();
			
			
			for (User user : usersList) {
				
				pseudoUsers.add(user.getPseudo());
			
			}
			
			Set<Message> listMessage  = null;
			
			listMessage = this.messagerieDao.getAllMessageByMessagerieId(idMessagerie);
			
			logger.info("List Message : " + listMessage);
			
			
			request.setAttribute("listUser", pseudoUsers);
			
			request.setAttribute("listMessage", listMessage);
			
			request.setAttribute("idMessagerie", idMessagerie);
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/messagerie.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
