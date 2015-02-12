package org.ikya.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.ikya.dao.MessagerieDao;
import org.ikya.dao.UserDao;
import org.ikya.entities.Message;
import org.ocpsoft.prettytime.PrettyTime;

/**
 * Servlet implementation class RefreshServlet
 */
@WebServlet(name = "RefreshServlet", urlPatterns = "/RefreshServlet")
public class RefreshServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(AddServlet.class);

	@Inject
	private UserDao userDao;

	@Inject
	private MessagerieDao messagerieDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RefreshServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.debug("In RefreshServlet (doPost)");
		
		Set<Message> listMessage  = null;
		
		Integer idMessagerie = Integer.parseInt(request
				.getParameter("idMessagerie"));
		
		
		
		try {
			
			listMessage = this.messagerieDao.getAllMessageByMessagerieId(idMessagerie);
			
			
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			List<Message> list = new ArrayList(listMessage);
			
			Collections.sort(list, new Comparator<Message>(){

				public int compare(Message c1, Message c2){
		 
					return c1.getId().compareTo(c2.getId());
		 
				}
		 
			});
			
			response.setContentType("text/html");
			PrettyTime p = new PrettyTime();
			
			if(list != null){
				
				for( Message mess : list){
						
						response.getWriter().println(""
								+ "<ul class=\"chat\">"
									+ "<li class=\"left clearfix\">"
										+ "<div class=\"clearfix\">"
											+ "<div class=\"header\">"
												+ "<strong class=\"primary-font\">"
													+ this.userDao.findByUserID(mess.getId_user()).getPseudo()
												+ "</strong>"
												+ "<small "
													+"class=\"pull-right text-muted\"> <i "
													+"class=\"fa fa-clock-o fa-fw\"></i>" + p.format(this.messagerieDao.getMessageDate(mess.getId()))
												+ "</small>"
											+ "</div>"
											+ "<p>"+ mess.getMessage() + "</p>"
										+ "</div>"
									+ "</li>"
								+"</ul>");
					
				}
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
