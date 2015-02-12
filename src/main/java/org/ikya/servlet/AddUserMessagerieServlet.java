package org.ikya.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
import org.ikya.dao.UserDao;
import org.ikya.entities.Contact;
import org.ikya.entities.User;
import org.ikya.utils.OpenJPAUtils;

/**
 * Servlet implementation class AddUserMessagerieServlet
 */
@WebServlet(name = "AddUserMessagerieServlet", urlPatterns = "/AddUser")
public class AddUserMessagerieServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(AddUserMessagerieServlet.class);
	
	@Inject
	private UserDao userDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserMessagerieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.debug("In AddUserMessagerieServlet (doGET)");
		
		Integer idMessagerie = Integer.parseInt(request.getParameter("idMessagerie"));
		
		try {
			
			OpenJPAUtils.setUp();
			
			User currentUser = null;
			
			HttpSession session = request.getSession();
			
			currentUser = (User) session.getAttribute("user");
			
			currentUser = userDao.findByUserID(currentUser.getId());
			
			Set<Contact> contacts = currentUser.getContacts();
			
			
			Map<User , Integer> listContacts = new HashMap<User,Integer>();
			
			for (Contact c : contacts){
				
				Integer id_user 	= c.getId_user();
		
				Integer id_contact 	= c.getId_contact();
				
	
				
				if(!id_user.equals(currentUser.getId())){
					
					logger.info("if");
					
					listContacts.put( this.userDao.findByUserID(id_user), c.getStatut());
					
				}else{
					
					listContacts.put( this.userDao.findByUserID(id_contact), c.getStatut());
				}
				
			}
						
			request.setAttribute("listContacts", listContacts);
			
			request.setAttribute("idMessagerie", idMessagerie);
			
			
			
			
			OpenJPAUtils.tearDown();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/AddUser.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
