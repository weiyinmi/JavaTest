package com.accenture.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.accenture.web.exception.BusinessException;
import com.accenture.web.service.impl.Fibonacci;
import com.accenture.web.service.impl.Joseph;

/**
 * Servlet implementation class ServletTest
 */
@WebServlet("/FibonacciJosephServlet")
public class FibonacciJosephServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String POSITIVE_NUMBER_REGEX = "^\\d+$";
	private static final Logger logger = Logger.getLogger(FibonacciJosephServlet.class);
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FibonacciJosephServlet() {
		super();	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//	    PrintWriter out=null;
		response.setContentType("text/html");
//		out = response.getWriter();

		String lengt;
		lengt = request.getParameter("length");

		Fibonacci fibonacci = new Fibonacci();

		List<BigInteger> value = new ArrayList<>();
		if (lengt.matches(POSITIVE_NUMBER_REGEX)) {
			for (int i = 0; i < Integer.parseInt(lengt); i++) {
				if (i <= 92) {
					Long val = fibonacci.fibonacciFunction(i);
					BigInteger big = new BigInteger(val.toString());
					value.add(big);
				} else {
					value.add(fibonacci.fibonacciBigN(i));
				}
			}
		} else {
			logger.info("Please input a number!");
		}
		// request.setAttribute("sequence", value);
		request.getSession().setAttribute("sequence", value);
		request.getRequestDispatcher("/ResponseFibonacci.jsp").forward(request, response);
		// response.sendRedirect(request.getContextPath() +
		// "/ResponseFibonacci.jsp");

		/*
		 * if(lengt.matches( POSITIVE_NUMBER_REGEX )){ out.
		 * println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">"
		 * ); out.println("<HTML>");
		 * out.println("  <HEAD><TITLE>Fabonacci</TITLE></HEAD>");
		 * out.println("  <BODY>"); out.
		 * print("<span style='font-weight: bold'>The fibonacci sequences are:</span>"
		 * +"<br/>"); out.println("  <ul>"); for(int
		 * i=0;i<Integer.parseInt(lengt);i++){ if(i<=92){
		 * out.println("<li><span style='font-weight: bold'>"+fibonacci.
		 * fibonacciFunction(i)+"</span></li>"); }else{
		 * out.println("<li><span style='font-weight: bold'>"+fibonacci.
		 * fibonacciBigN(i)+"</span></li>"); } } }else{
		 * out.println("Please input a number!"); } out.println("  </ul>");
		 * out.println("  </BODY>"); out.println("</HTML>"); out.flush();
		 * out.close();
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String people;
		String start;
		String interval;
		people = request.getParameter("peoples");
		start = request.getParameter("start");
		interval = request.getParameter("interval");

		String[] arr = people.split("\\ ");
		List<String> list = new ArrayList<>();
		list.addAll(Arrays.asList(arr));

		Joseph joseph = new Joseph();
		try {
			joseph.josephFunction(list, Integer.parseInt(start), Integer.parseInt(interval));

			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>Joseph</TITLE></HEAD>");
			out.println("  <BODY>");
			out.print("<span style='font-weight: bold'>The last one is:</span>");
			out.print("<span style='font-weight: bold'>"
					+ joseph.josephFunction(list, Integer.parseInt(start), Integer.parseInt(interval)) + "</span>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();

		} catch (NumberFormatException | BusinessException e) {
			logger.warn(e);	
		}finally{
			if(out!=null){
			
			out.close();
			}
		}
	}

}
