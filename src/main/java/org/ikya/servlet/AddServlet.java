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
import org.ikya.dao.MessagerieDao;
import org.ikya.dao.UserDao;
import org.ikya.entities.Messagerie;
import org.ikya.entities.User;
import org.ikya.utils.OpenJPAUtils;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet(name = "AddServlet", urlPatterns = "/Add")
public class AddServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(AddServlet.class);
	
	@Inject
	private UserDao userDao;
	
	@Inject
	private MessagerieDao messagerieDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.debug("In AddServlet (doGET)");
		
		Integer idMessagerie = Integer.parseInt(request.getParameter("idMessagerie"));
		
		Integer idUser = Integer.parseInt(request.getParameter("idUser"));
		
		try {
			
			OpenJPAUtils.setUp();
			
			Messagerie messagerie = this.messagerieDao.findById(idMessagerie);
			
			User user = this.userDao.findByUserID(idUser);
			
			messagerie.addUser(user);
			
			messagerieDao.update(messagerie);
			
			OpenJPAUtils.tearDown();
			
		}catch(Exception e){
			
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/mess?idMessagerie=" + idMessagerie);
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
