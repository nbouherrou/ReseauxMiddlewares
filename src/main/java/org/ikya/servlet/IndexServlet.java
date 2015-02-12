package org.ikya.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.log4j.Logger;
import org.ikya.dao.UserDao;
import org.ikya.entities.User;
import org.ikya.utils.OpenJPAUtils;

/**
 * Servlet implementation class BlankServlet
 */
@WebServlet(name = "IndexServlet", urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Logger logger = Logger.getLogger(IndexServlet.class);
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		User currentUser = null;
		
		currentUser = (User) session.getAttribute("user");
		
		if (currentUser == null) {
			
			response.sendRedirect("/ikya/login");
		}else {
			
			logger.debug("In AllUtilisateursServlet (GET)");

			UserDao userDao = new UserDao();
			
			try {
				OpenJPAUtils.setUp();
				
				List<User> listUsersTemp = userDao.getAll();
				
				List<User> listUsers = new ArrayList<User>(listUsersTemp);
				
				OpenJPAUtils.tearDown();
				
				Comparator<User> comparator = new Comparator<User>(){
					public int compare(User u1, User u2){
					 
						return u1.getScore().compareTo(u2.getScore());
					 
					}
				 
				};
				
				Collections.sort(listUsers, new ReverseComparator(comparator)); 
				
				request.setAttribute("listUsers", listUsers);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			RequestDispatcher dispatcher = this.getServletContext()
					.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
