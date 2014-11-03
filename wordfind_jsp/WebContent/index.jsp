<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="service.KeywordDetect"%>

<% String keyword = request.getParameter("keyword");
	int genre_num = Integer.parseInt(request.getParameter("genre_num"));
	int item_num=Integer.parseInt(request.getParameter("item_num"));
	//out.println("keyword is:"+keyword+"<br/>");
	//out.println("genre_num is:"+genre_num+"<br/>");
	//out.println("item_num is:"+item_num+"<br/>");
   KeywordDetect kd = new KeywordDetect();
   out.println(kd.findRelatedWords(keyword, genre_num, item_num));
%>

