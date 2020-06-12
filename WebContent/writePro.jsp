<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="chap13.board.BoardDataBean"%>
<%@ page import="chap13.board.BoardDBBean"%>
<%
	//
	// 	String sub = request.getParameter("subject");
	String subject = request.getParameter("subject");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String content = request.getParameter("content");
	String password = request.getParameter("password");

	System.out.println("subject = " + subject);
	System.out.println("name = " + name);
	System.out.println("emaile = " + email);
	System.out.println("content = " + content);
	System.out.println("password = " + password);

	BoardDBBean bdb = BoardDBBean.getInstance();
	bdb.insertDetailArticle(name, subject, email, content, password);

	BoardDataBean dataBean = new BoardDataBean();
	dataBean.setContent(content);
	dataBean.setWriter(name);
	dataBean.setEmail(email);
	dataBean.setSubject(subject);
	dataBean.setPasswd(password);
	
	bdb.insertArticle(dataBean);
	
	//  	dataBean.setSubject(sub);
	//  	out.println("sub = "+sub);
	//  	out.println(dataBean);
	//  	out.println(dataBean.toString());

	//  	BoardDBBean bdb = BoardDBBean.getInstance();
		

	out.println("writePro.jsp");
%>
