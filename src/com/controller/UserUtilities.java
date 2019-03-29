package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.SME;
import com.bean.Skill;
import com.bean.Trainer;
import com.service.ServClass;

/**
 * Servlet implementation class Controller
 */
public class UserUtilities extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UserUtilities() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    ServClass sc=new ServClass();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
		
		HttpSession hsn=request.getSession(false);
		if(hsn==null)
		{
			pw.println("Sorry, there's something wrong with the page. Kindly log back in !");
			RequestDispatcher rd=request.getRequestDispatcher("Index.html");
			rd.forward(request, response);
		}
		else
		{
			String username=(String) hsn.getAttribute("UserName");
			String userType=(String) hsn.getAttribute("User");
			
			try {
			
					String action=request.getParameter("Action");
					
					if(userType.equals("Trainer"))
					{
						Trainer t=sc.getTrainersByUsername(username).get(0);
						if (action!=null)
						{
							if(action.equals("Create Request"))
							{
								RequestDispatcher rd=request.getRequestDispatcher("JSP/RequestCreation.jsp");
								rd.forward(request, response);
							}
							
							else if(action.equals("CreateRequest"))
							{
								Date tDateOfBegin= sdf.parse(request.getParameter("dob"));
								Date tDateOfEnd=sdf.parse(request.getParameter("doe"));
								String []skills=request.getParameterValues("Skills");
								
								sc.addTrainer(t);
								
								t.settDateOfBegin(tDateOfBegin);
								t.settDateOfEnd(tDateOfEnd);
								
								for(String str:skills)
								{
									Skill s=sc.getSkillBySkName(str);
									sc.addTSkill(t.getTrainerId(), s.getSkillId());
								}
								
								t.settStatus(1);
								sc.updateTrainer(t);
								hsn.setAttribute("UserObject", t);
								pw.println("agya");
								RequestDispatcher rd=request.getRequestDispatcher("JSP/RequestSuccessful.jsp");
								rd.forward(request, response);
							}
							else if(action.equals("Program Allocated"))
							{
								RequestDispatcher rd=request.getRequestDispatcher("JSP/ProgramAllocated.jsp");
								rd.forward(request, response);
							}
							else if(action.equals("Update Request"))
							{
								RequestDispatcher rd=request.getRequestDispatcher("RequestCreation.jsp");
								rd.forward(request, response);
							}
							else if(t.gettStatus()>1)
							{
								//confirmation mail
								RequestDispatcher rd=request.getRequestDispatcher("RequestAccept.jsp");
								rd.forward(request, response);
							}
							else if(action.equals("Edit Profile"))
							{
								RequestDispatcher rd=request.getRequestDispatcher("JSP/EditProfile.jsp");
								rd.forward(request, response);
							}
							else if(action.equals("Save Changes"))
							{
								RequestDispatcher rd=request.getRequestDispatcher(userType + "Dashboard.jsp");
								rd.forward(request, response);
							}
							else if (request.getParameter("action").equals("Home")) {
								System.out.println();
								response.sendRedirect("JSP/"+hsn.getAttribute("User")+"Dashboard.jsp");	
							}
							else if (request.getParameter("action").equals("LogOut")) {
								System.out.println();
								hsn.invalidate();
								response.sendRedirect("../HTML/Index.html");	
							}
						}
						else
						{
							response.sendRedirect("JSP/SomethingWentWrong.jsp");
						}
						
					}
					
					if(userType.equals("SME"))
					{
						SME sme=sc.getSMEByUsername(username);
						hsn.setAttribute("smeObject", sme);
						
						if (request.getParameter("action").equals("Home")) {
							System.out.println();
							response.sendRedirect("JSP/"+hsn.getAttribute("User")+"Dashboard.jsp");	
						}
						if (request.getParameter("action").equals("LogOut")) {
							System.out.println();
							hsn.invalidate();
							response.sendRedirect("../HTML/Index.html");	
						}
						
						
						if(action.equals("Create Nomination"))
						{
							RequestDispatcher rd=request.getRequestDispatcher("JSP/NominationCreation.jsp");
							rd.forward(request, response);
						}
						
						if(action.equals("CreateNomination"))
						{
							Date sDateOfBegin= sdf.parse(request.getParameter("dob"));
							Date sDateOfEnd=sdf.parse(request.getParameter("doe"));
							String []skills=request.getParameterValues("skills");
							
							sme.setsDateOfBegin(sDateOfBegin);
							sme.setsDateOfEnd(sDateOfEnd);
							
							sc.setTDates(sDateOfBegin, sDateOfEnd);
							for(String str:skills)
							{
								Skill s=sc.getSkillBySkName(str);
								sc.addTSkill(sme.getSMEId(), s.getSkillId());
							}
							
							sme.setsStatus(1);
							
							RequestDispatcher rd=request.getRequestDispatcher("NominationSuccessful.jsp");
							rd.forward(request, response);
						}
						if(action.equals("UpdateNomination"))
						{
							RequestDispatcher rd=request.getRequestDispatcher("NominationCreation.jsp");
							rd.forward(request, response);
						}
						if(sme.getsStatus()>1)
						{
							Trainer t=new Trainer(sme.getsFname(), sme.getsLname(), sme.getsAge(), sme.getsGender(), sme.getsContactNumber(), sme.getSmail(), sme.getsUsername(), sme.getsPassword(),0, 0);
							sc.addTrainer(t);
							sc.setSDates(sme.getsDateOfBegin(),sme.getsDateOfEnd());
							hsn.setAttribute("tObject", t);
							sc.deleteSME(sme.getSMEId());
							RequestDispatcher rd=request.getRequestDispatcher("NominationAccept.jsp");
							rd.forward(request, response);
						}
						if(action.equals("Re-login as Trainer"))
						{
							RequestDispatcher rd=request.getRequestDispatcher("TrainerDashboard.jsp");
							rd.forward(request, response);
						}
						if(action.equals("Edit Profile"))
						{
							RequestDispatcher rd=request.getRequestDispatcher("EditProfile.jsp");
							rd.forward(request, response);
						}
						if(action.equals("SaveChanges"))
						{
							RequestDispatcher rd=request.getRequestDispatcher(userType + "Dashboard.jsp");
							rd.forward(request, response);
						}
						
					}
					
					
				} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
			} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		pw.close();
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
