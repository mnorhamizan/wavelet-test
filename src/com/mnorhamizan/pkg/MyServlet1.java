package com.mnorhamizan.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet1
 */
@WebServlet("/MyServlet1")
public class MyServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String ql;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con=DriverManager.getConnection(
			"jdbc:oracle:thin:@localhost:1521:xe","mnorhamizan","password");

			Statement stmt=con.createStatement();
            System.out.println("connection established successfully...!!");     

			ql = "SELECT * FROM EMPLOYEE";
			ResultSet rs=stmt.executeQuery(ql);
			System.out.println(ql);

			while(rs.next())
			{
				out.println("<p>");
				out.print("Staff ID: " + rs.getInt(1));
				out.print(", Staff Name: " + rs.getString(2));
				out.print(", Rank: " + rs.getInt(3));
				out.print(", Gross pay: " + rs.getInt(4));
				out.print("</p>");
			}
			

			con.close();
			}catch(Exception e){ System.out.println(e);System.out.println("ayy");}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
