package org.ikya.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;

import org.ikya.dao.ChallengeDao;
import org.ikya.dao.UserDao;
import org.ikya.entities.Challenge;
import org.ikya.entities.User;
import org.ikya.utils.OpenJPAUtils;



public class InitChallengeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserDao userDao;
	
	@Inject
	private ChallengeDao challengeDao;
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) 
			
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		User userTest = (User) session.getAttribute("user");;

		ArrayList<Challenge> challenges = null;

		try {

			OpenJPAUtils.setUp();

			challenges = (ArrayList<Challenge>)challengeDao.findByUserID(userTest.getId());

		} catch (Exception e) {

			e.printStackTrace();

		} 
			
		request.setAttribute("userFinded", userTest);
		
		request.setAttribute("challenges", challenges);
		
		request.setAttribute("userDao", userDao);
		
		this.getServletContext().getRequestDispatcher( "/Challenge.jsp" ).forward( request, response );
				
	}

}
