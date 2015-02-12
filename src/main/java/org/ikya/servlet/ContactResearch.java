package org.ikya.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.ikya.dao.UserDao;
import org.ikya.entities.User;

import com.google.gson.Gson;

public class ContactResearch extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(ContactResearch.class);
	
	private List<User> users;
	
	@Inject
	private UserDao userDao;
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) 
	
			throws ServletException, IOException {
		
		String pseudoUser = request.getParameter("pseudo");
			
		users = null;
		
		try {
			
			User currentUser = userDao.findByPseudo(pseudoUser);
			
			users = userDao.getAllContactByUser(currentUser);
			
			logger.info(users.toString());
			
			logger.info("All contacts got");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		ArrayList<String> nameTable = new ArrayList<String>();
		
		for (User user : users) {
			
			nameTable.add(user.getPseudo()); 
			
		}
				
		Gson gson = new Gson();
		 
		String result = gson.toJson(nameTable);;
		
		response.setContentType("text/plain");
		    
		response.setCharacterEncoding("UTF-8");
		    
		response.getWriter().write(result);
		
	}

}
