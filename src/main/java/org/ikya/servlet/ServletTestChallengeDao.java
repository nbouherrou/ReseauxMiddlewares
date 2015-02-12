package org.ikya.servlet;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.ikya.constants.Constants;
import org.ikya.dao.ChallengeDao;
import org.ikya.dao.UserDao;
import org.ikya.entities.Challenge;
import org.ikya.entities.User;
import org.ikya.utils.OpenJPAUtils;

/**
 * Servlet implementation class HelloAnnotServlet
 */
@WebServlet(name="Chall", urlPatterns="/Chall")
public class ServletTestChallengeDao extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(ServletTestChallengeDao.class);

	// INJECTION of  EJB in this class (WITHOUT USING NEW)
	@Inject
	private UserDao userDao;
	
	@Inject
	private ChallengeDao challengeDao;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletTestChallengeDao() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * URL to use if you want to see the result : http://localhost:8080/tdweb/LivreServlet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		logger.info("DEBUT Chall");
		try
		{
			OpenJPAUtils.setUp();	
			
			User user1 = new User("John", "John@ouioui.fr", "pass", "0608080465");
			
			User user2 = new User("Arthur", "Arthur@ouioui.fr", "pass", "0788888888");
			
			User user3 = new User("Louis", "Louis@ouioui.fr", "ssap", "0788888888");
			
			this.userDao.create(user1);
			
			this.userDao.create(user2);
			
			this.userDao.create(user3);
			
			Challenge challenge1 = new Challenge(user1.getId(), user2.getId(), Constants.CHALLENGE_ON_HOLD, -1);
			
			Challenge challenge2 = new Challenge(user2.getId(), user3.getId(), Constants.CHALLENGE_ON_HOLD, -1);
			
			this.challengeDao.create(challenge1);
			
			this.challengeDao.create(challenge2);
			
			logger.info("IfChallengeExist ? : " + challengeDao.ifChallengeExist(challenge1));
			
			logger.info("findByID : challenge1 : " + challengeDao.findByID(challenge1.getId()));
			
			List<Challenge> challengesByUserId = challengeDao.findByUserID(user2.getId());
			
			for(Challenge c:challengesByUserId) {
				
				logger.info("FindByUserId Found : " + c);
			}
			
			logger.info("Avant modif : " + challenge1);
			
			challenge1.setId_user(user3.getId());
			
			this.challengeDao.update(challenge1);
			
			this.challengeDao.updateStatut(challenge1, Constants.CHALLENGE_ACCEPTED);
			
			Challenge challengesById = challengeDao.findByID(challenge1.getId());
			
			logger.info("Apres modif : " + challengesById);
			
			logger.info("Actualisation Score ...");
			
			challenge2.setId_winner(user2.getId());
			
			challengeDao.update(challenge2);
			
			challengeDao.updateStatut(challenge2, Constants.CHALLENGE_CHECKED);
			
			challengeDao.setScore(challenge2);
			
			logger.info("... Done");
			
			logger.info("Suppression challenge ...");
			
			challengeDao.deleteChallenge(challenge2);
			
			userDao.delete(user3);
			
			boolean isSuppr = challengeDao.ifChallengeExist(challenge2);
			
			logger.info("... " + isSuppr);

			OpenJPAUtils.tearDown();
			
			logger.info("END Chall.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jspLivres.jsp");
		dispatcher.forward(request, response);
	}


}
