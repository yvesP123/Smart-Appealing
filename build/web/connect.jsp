<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%
String url="jdbc:mysql://localhost:3306/appealing";
String usera="root";
String pass="";
Connection conn=null;
Class.forName("com.mysql.jdbc.Driver");
conn=DriverManager.getConnection(url,usera,pass);

%>