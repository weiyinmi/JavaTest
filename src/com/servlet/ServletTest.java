package com.servlet;

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

import com.test2.Fibonacci;
import com.test2.Joseph;

/**
 * Servlet implementation class ServletTest
 */
@WebServlet("/ServletTest")
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTest() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 
		String lengt;
    	lengt = request.getParameter("length");
    	
    	List<BigInteger> value = new ArrayList<>();
    	Fibonacci fibonacci = new Fibonacci();
    	    	
    	for(int i=0;i<Integer.parseInt(lengt);i++){
      	  if(i<=92){
      		  Long val =fibonacci.fibonacciFunction(i);
      		  BigInteger big = new BigInteger(val.toString());
      		  value.add(big);
      	  }else{
      		  value.add(fibonacci.fibonacciBigN(i));
      	  }	 	         
	  }
/*    	request.setAttribute("sequence", value);
        request.getRequestDispatcher("/ResponseFibonacci.jsp").forward(request,response);*/

		 out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		 out.println("<HTML>");
		 out.println("  <HEAD><TITLE>Fabonacci</TITLE></HEAD>");
		 out.println("  <BODY>");
		 out.println(value);
		 out.println("  </BODY>");
		 out.println("</HTML>");
		 out.flush();
		 out.close();
	}		 	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String people;
		String start;
		String interval;
		people = request.getParameter("peoples");
	    start = request.getParameter("start");
		interval = request.getParameter("interval");
		
	    String[] arr = people.split("\\ ");
	    List<String> list = new ArrayList<String>();
	    list.addAll(Arrays.asList(arr));
	    
	    Joseph joseph=new Joseph();
	    joseph.josephFunction(list,Integer.parseInt(start),Integer.parseInt(interval));
		

		 out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		 out.println("<HTML>");
		 out.println("  <HEAD><TITLE>Joseph</TITLE></HEAD>");
		 out.println("  <BODY>");
		 out.print("The last one is:");
		 out.print(joseph.josephFunction(list,Integer.parseInt(start),Integer.parseInt(interval)));
		 out.println("  </BODY>");
		 out.println("</HTML>");
		 out.flush();
		 out.close();
	}

}
