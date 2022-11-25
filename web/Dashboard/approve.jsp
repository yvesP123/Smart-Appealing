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
String stutus="Approved";
 
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
 OkHttpClient client = new OkHttpClient().newBuilder()
  .build();
MediaType mediaType = MediaType.parse("text/plain");
RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
  .addFormDataPart("to", "+250784703564")
  .addFormDataPart("from", "Smart")
  .addFormDataPart("unicode", "0")
  .addFormDataPart("sms", "Your are Approved")
  .addFormDataPart("action", "send-sms")
  .build();
Request request = new Request.Builder()
  .url("https://api.mista.io/sms")
  .method("POST", body)
  .addHeader("x-api-key", "160|k1K6UtnE4ZFiNweOXMM8l0xCKMqCEE6LPGGUxUG9 ")
  .build();
Response response = client.newCall(request).execute();
	 out.println("<script>alert('Appeal Has been Approved');window.location='manage.jsp';</script>");
	 
 }
 else{
	 out.println("<script>alert('Samething Went Wrong');window.location='manage.jsp';</script>");
	  
 }
 
 %>
 
