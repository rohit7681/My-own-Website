package com.Jsp.InstGramProj;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/signup")
public class SignUp extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		String age=req.getParameter("age");
		String mobile=req.getParameter("mobile");
		String email=req.getParameter("email");
		String user_name=req.getParameter("username");
		String password=req.getParameter("password");
		int age1=Integer.parseInt(age);
		
		String exp="(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{4,}";
		Pattern p=Pattern.compile(exp);
		Matcher m=p.matcher(password);
		if(age1>13 && m.matches()) {
			String Query="insert into user_details values(?,?,?,?,?,?)";
			try {
				PrintWriter out=resp.getWriter();
				PreparedStatement st=DbConnection.con.prepareStatement(Query);
				st.setString(1, name);
				st.setString(2, age);
				st.setString(3, mobile);
				st.setString(4, email);
				st.setString(5, password);
				st.setString(6, user_name);
				int n=st.executeUpdate();
				if(n>0) {
					out.print("<h1>Sign up Completed Sucessfully</h1>");
					RequestDispatcher rs=req.getRequestDispatcher("Login2.html");
					rs.forward(req, resp);
				}
				else {
					out.print("<h1>Plese follow the Criteria</h1>");
					RequestDispatcher rs=req.getRequestDispatcher("SignUp.html");
					rs.include(req, resp);	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			RequestDispatcher rs=req.getRequestDispatcher("InvalidSignup.html");
			rs.forward(req, resp);
		}
		
	
	}

}
