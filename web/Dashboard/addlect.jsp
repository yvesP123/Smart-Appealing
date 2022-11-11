<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>

<%
String lname=request.getParameter("lname");
String email=request.getParameter("email");
String phone=request.getParameter("phone");
String gender=request.getParameter("gender");
String Password="Smart@123";
%>
<%@ include file="connect.jsp" %>
<% 
PreparedStatement ps;
String sql="INSERT INTO lecturer_tbl(lect_name,email,telephone,gender,Password)values(?,?,?,?,?)";
ps=conn.prepareStatement(sql);
ps.setString(1, lname);
ps.setString(2, email);
ps.setString(3, phone);
ps.setString(4, gender);
ps.setString(5, Password);
int i=ps.executeUpdate();
if(i>0)
{
	out.println("<script>alert('Lecturer has been Added');window.location='index2.jsp';</script>");
}
else
{
	out.println("<script>alert('lecturer has not added');window.location='index2.jsp';</script>");
}
%>