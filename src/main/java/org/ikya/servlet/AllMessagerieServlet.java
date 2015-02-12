package org.ikya.servlet;

import java.io.IOException;
import java.util.Set;

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
import org.ikya.entities.Messagerie;
import org.ikya.entities.User;
import org.ikya.utils.OpenJPAUtils;

/**
 * Servlet implementation class AllMessagerie
 */
@WebServlet(name = "AllMessagerie", urlPatterns = "/AllMessagerie")
public class AllMessagerieServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private static Logger logger = Logger.getLogger(AllMessagerieServlet.class);
	
	@Inject
	private MessagerieDao messagerieDao; 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllMessagerieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.debug("In AllMessagerie (doGET)");
		
		
		try {
			OpenJPAUtils.setUp();
			
			Set<Messagerie> messagerieList = null;
			
			User currentUser = null;
			
			HttpSession session = request.getSession();
			
			currentUser = (User) session.getAttribute("user");
			
			messagerieList = messagerieDao.findMessagerieByUserId(currentUser.getId());
			
			logger.info("List Messagerie : " + messagerieList);
			
			request.setAttribute("messagerieList", messagerieList);
			
		}catch(Exception e){
				
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/AllMessagerie.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
