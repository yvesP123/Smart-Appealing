<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
 <%
 String id = request.getParameter("id");
String stutus="Denied";
 
 int pid = Integer.parseInt(id);
%>
<%@ include file="connect.jsp" %>
<% 
PreparedStatement ps;
 String sql="UPDATE appeal SET status=? WHERE app_id="+id;
 ps=conn.prepareStatement(sql);
 
 ps.setString(1, stutus);
 
 int i=ps.executeUpdate();
 if(i>0)
 {
	 out.println("<script>alert('Appeal Has been Denied');window.location='manage.jsp';</script>");
	 
 }
 else{
	 out.println("<script>alert('Samething Went Wrong');window.location='manage.jsp';</script>");
	  
 }
 
 %>
 