<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
    <%@page import="java.io.InputStream" %>
    <%@page import="java.io.FileInputStream" %>
    <%@page import="java.io.File" %>
    <%@page import="java.sql.ResultSet" %>
     <%@page import="java.sql.PreparedStatement" %>
      <%@page import="java.sql.SQLException"%>
      <%@page import="javax.servlet.http.Part"%>
      <%@page import="javax.servlet.annotation.MultipartConfig"%>
<%
String reg_number=request.getParameter("regnumber");
String c_name=request.getParameter("c_name");
String c_code=request.getParameter("c_code");
String lect=request.getParameter("lect");
String phone=request.getParameter("phone");
String reason=request.getParameter("reason");

InputStream inputStream = null; 

// obtains the upload file part in this multipart request
Part filePart = request.getPart("file1");
if (filePart != null) {
     
    // obtains input stream of the upload file
    inputStream = filePart.getInputStream();
}

%>
<%@ include file="connect.jsp" %>
<% 
PreparedStatement ps;
String sql="INSERT INTO remarks(regnumber,phone,status,course_id,lect_id,reason)values(?,?,?,?,?,?)";
ps=conn.prepareStatement(sql);

ps.setString(1, "reg_number");
ps.setString(2, "5");
ps.setString(3, "approved");
ps.setString(4, "5");
ps.setString(5, "6");
ps.setBlob(6, inputStream);


int i=ps.executeUpdate();
if(i>0)
{
	out.println("<script>alert('Data has been inserted');window.location='index.jsp';</script>");
}
else
{
	out.println("<script>alert('Data has not been inserted');window.location='index.jsp';</script>");
}
%>