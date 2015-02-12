package org.ikya.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import org.ikya.constants.Constants;
import org.ikya.dao.UserDao;
import org.ikya.entities.Contact;
import org.ikya.entities.User;
import org.ikya.utils.OpenJPAUtils;

/**
 * Servlet implementation class MyContactsServlet
 */
@WebServlet(name = "MyContactsServlet", urlPatterns = "/MyContacts")
public class MyContactsServlet extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(MyContactsServlet.class);
	
	@Inject
	private UserDao userDao;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyContactsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.debug("In MyContactsServlet (doGET)");
		
		try {
			
			OpenJPAUtils.setUp();
			
			User currentUser = null;
			
			HttpSession session = request.getSession();
			
			currentUser = (User) session.getAttribute("user");
			
			currentUser = userDao.findByUserID(currentUser.getId());
			
			Set<Contact> contacts = currentUser.getContacts();
			
			ArrayList<User> 	listContacts 		= new ArrayList<User>();
			
			Map<User, Contact> 	demandeContacts 	= new HashMap<User, Contact>();
			
			for (Contact c : contacts){
				
				Integer id_user 	= c.getId_user();
		
				Integer id_contact 	= c.getId_contact();

				
				if(!id_user.equals(currentUser.getId())){
					
					logger.info("if");
					
					if(c.getStatut() > Constants.CONTACT_VUE){
						
						listContacts.add( this.userDao.findByUserID(id_user));
						
					}else{
												
						userDao.changeContactStatut(c.getId(), Constants.CONTACT_VUE);
						
						c.setStatut(Constants.CONTACT_VUE);
						
						demandeContacts.put(this.userDao.findByUserID(id_user), c);
						
					}	
					
				}else{
					
					if(c.getStatut() > Constants.CONTACT_VUE){
						
						listContacts.add(this.userDao.findByUserID(id_contact));
						
					}else{
						
						demandeContacts.put(this.userDao.findByUserID(id_contact), c);
						
					}
					
				}
				
			}
						
			request.setAttribute("listContacts", listContacts);
			
			request.setAttribute("demandeContacts", demandeContacts);
			
			session.setAttribute("user", currentUser);
			
			
			OpenJPAUtils.tearDown();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/MyContacts.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
