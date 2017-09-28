<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*"%>
<%@ page import="java.math.BigInteger"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		/*  List<BigInteger> list=(List<BigInteger>) request.getAttribute("sequence"); */
		List<BigInteger> list = (List<BigInteger>) session.getAttribute("sequence");

		out.println("The Fibonacci sequence is: ");
		out.println(" <ul>");
		for (int i = 0; i < list.size(); i++) {			
			out.println("<li>" + list.get(i) + "</li>");			
		}
		out.println("</ul>");
	%>
	<%-- 
	<%=list %> --%>

</body>
</html>