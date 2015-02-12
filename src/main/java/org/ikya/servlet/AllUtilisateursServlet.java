package org.ikya.servlet;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class AllUtilisateursServlet
 */
@WebServlet(name = "AllUtilisateursServlet", urlPatterns = "/allutilisateur")
public class AllUtilisateursServlet extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(AllUtilisateursServlet.class);
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllUtilisateursServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.debug("In AllUtilisateursServlet (GET)");

		UserDao userDao = new UserDao();
		
		try {
			OpenJPAUtils.setUp();
			
			User currentUser = null;
			
			HttpSession session = request.getSession();
			
			currentUser = (User) session.getAttribute("user");
			
			List<User> listUsers = userDao.getAll(currentUser.getId());
			
			OpenJPAUtils.tearDown();
			
			request.setAttribute("listUsers", listUsers);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/All-utilisateurs.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
