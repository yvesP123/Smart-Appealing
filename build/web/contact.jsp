<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%

String name=request.getParameter("name");
String email=request.getParameter("email");
String subject=request.getParameter("subject");
String message=request.getParameter("message");
%>
<%@ include file="connect.jsp" %>
<%
PreparedStatement Ps;
String Sql="INSERT INTO `contact`(`name`, `email`, `subject`, `message`) VALUES (?,?,?,?)";
Ps=conn.prepareStatement(Sql);
Ps.setString(1, name);
Ps.setString(2,email);
Ps.setString(3,subject);
Ps.setString(4,message);
int i;
i=Ps.executeUpdate();
if(i>0)
{
	out.println("<script>alert('Contact Sucessfull inserted');window.location='index.jsp';</script>");
}
else
{
	out.println("<script>alert('Try Again');window.location='index.jsp';</script>");
}
%>