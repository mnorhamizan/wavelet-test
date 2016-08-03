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
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		out.print("Hello world");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String staffID = request.getParameter("staffID");
		String whw = request.getParameter("whw");
		int workHours = Integer.parseInt(whw);
		
		int rsStaffID = 0;
		String rsName = "";
		int rsRank = 0;
		int rsGrossPay = 0;

		String ql;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con=DriverManager.getConnection(
			"jdbc:oracle:thin:@localhost:1521:xe","mnorhamizan","password");

			Statement stmt=con.createStatement();
            System.out.println("connection established successfully...!!");     

			ql = "SELECT STAFFID,STAFFNAME,RANK FROM EMPLOYEE WHERE STAFFID = ";
			ql += staffID;
			ResultSet rs=stmt.executeQuery(ql);
			System.out.println(ql);

			while(rs.next())
			{
				rsStaffID = rs.getInt(1);
				rsName = rs.getString(2);
				rsRank = rs.getInt(3);
			}
			
			rsGrossPay = rsRank * workHours;
			
			ql = "UPDATE EMPLOYEE SET GROSSPAY = " + rsGrossPay
					+ " WHERE STAFFID= "
					+ staffID;
			
			System.out.println(ql);

			stmt.executeQuery(ql);

			
			System.out.println("Grosspay : " + rsGrossPay);
			
			out.println("Staff ID: " + rsStaffID);
			out.println(", Name: " + rsName);
			out.println(", Rank: " + rsRank);
			out.println(", Gross pay: " + rsGrossPay);
			con.close();
			}catch(Exception e){ System.out.println(e);System.out.println("ayy");}
		
	}

}
