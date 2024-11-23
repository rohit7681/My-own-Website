package com.Jsp.InstGramProj;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/login")
public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user_name2 = req.getParameter("username");
		String password2 = req.getParameter("password");
		String user_name1="";
		String password1="";
		String q1 = "select user_name,password from user_details where user_name='" + user_name2
				+ "' and password='" + password2 + "'";
		try {
			Statement st=DbConnection.con.createStatement();
			ResultSet r = st.executeQuery(q1);
			while (r.next()) {
				user_name1 = r.getString("user_name");
				password1 = r.getString("password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//				DbConnection.con.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		if (user_name2.equals(user_name1)) {
			RequestDispatcher rd = req.getRequestDispatcher("Welcome2.html");
			rd.forward(req, resp);
		}
		else {
			PrintWriter pr=resp.getWriter();
			
			RequestDispatcher rd=req.getRequestDispatcher("Login1.html");
			rd.include(req, resp);
			//pr.print("<h1 align>Invalid User_name And Password");
		}
		
	}

}
