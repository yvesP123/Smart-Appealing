<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>

<%
String reg_number=request.getParameter("regnumber");
String c_name=request.getParameter("cname");
String c_code=request.getParameter("code");
String lect=request.getParameter("lname");
String reason=request.getParameter("reason");
String letter=request.getParameter("letter");

%>
<%@ include file="connect.jsp" %>
<% 
Statement st=null;
String sql1="select * from courses where course_name='"+c_name+"' ";
st=conn.createStatement();
ResultSet rs;
rs=st.executeQuery(sql1);
PreparedStatement ps;
int id1 = 0;
while(rs.next()){
	id1 = rs.getInt("course_id");
	
}


String sql="INSERT INTO appeal(regnumber,course_id,lect_id,letter,reason,status)values(?,?,?,?,?,?)";
ps=conn.prepareStatement(sql);
ps.setString(1, reg_number);
ps.setInt(2, id1);
ps.setString(3, "3");
ps.setString(4, "letter");
ps.setString(5, "reason");
ps.setString(6, "Approved");
int i=ps.executeUpdate();
if(i>0)
{
	out.println("<script>alert('Appeal has been accepted wait for response');window.location='index2.jsp';</script>");
}
else
{
	out.println("<script>alert('not accepted');window.location='appealing.jsp';</script>");
}

%>