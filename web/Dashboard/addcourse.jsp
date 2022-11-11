<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>

<%
String cname=request.getParameter("cname");
String code=request.getParameter("ccode");
String credit=request.getParameter("credit");
String lect=request.getParameter("lect");

%>
<%@ include file="connect.jsp" %>
<% 
PreparedStatement ps;
String sql="INSERT INTO courses(course_name,code,credit,lect_id)values(?,?,?,?)";
ps=conn.prepareStatement(sql);
ps.setString(1, cname);
ps.setString(2, code);
ps.setString(3, credit);
ps.setString(4, "2");
int i=ps.executeUpdate();
if(i>0)
{
	out.println("<script>alert('Course has been Added');window.location='index2.jsp';</script>");
}
else
{
	out.println("<script>alert('Course has not added');window.location='index2.jsp';</script>");
}
%>