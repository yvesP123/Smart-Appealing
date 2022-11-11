<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>

<%
String names1=request.getParameter("names1");
String email=request.getParameter("email");
String phone=request.getParameter("phone");
String username=request.getParameter("username");
String Password=request.getParameter("password");
%>
<%@ include file="connect.jsp" %>
<% 
PreparedStatement ps,sp;
String sql="INSERT INTO students(regnumber,names,email,phone,Password)values(?,?,?,?,?)";
ps=conn.prepareStatement(sql);
String sql2="INSERT INTO users(username,password,category)values(?,?,?)";
sp=conn.prepareStatement(sql2);

ps.setString(1, username);
ps.setString(2, names1);
ps.setString(3, email);
ps.setString(4, phone);
ps.setString(5, Password);
sp.setString(1, username);
sp.setString(2, Password);
sp.setString(3, "user");
int i=ps.executeUpdate();
int k=sp.executeUpdate();
if(i>0 && k>0)
{
	out.println("<script>alert('your account has been created then login...');window.location='login.jsp';</script>");
}
else
{
	out.println("<script>alert('account has not created');window.location='index2.jsp';</script>");
}
%>