package com.webtest.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.json.JSONObject;
import net.sf.json.JSONObject;

import com.webtest.fibonacciAndJoseph.Joseph;
import com.webtest.forxml.JosephProblemRequest;


/**
 * Servlet implementation class JsonServlet
 */
@WebServlet("/JsonServlet")
public class JsonServlet extends HttpServlet {
	private static final String Encoded_FORMANT = "utf-8";
	private static final long serialVersionUID = 1L;
	private static final String POSITIVE_NUMBER_REGEX = "^\\d+$";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JsonServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    response.setCharacterEncoding(Encoded_FORMANT);
	    response.setContentType("application/json");
	    PrintWriter out =null; 
		out = response.getWriter();
	    StringBuffer json = new StringBuffer();
	    BufferedReader reader = request.getReader();
	    String line = null;
	    while((line = reader.readLine())!=null){
	    	json.append(line);
	    }	  
	    
	//    JSONObject josephObj = new JSONObject(json.toString());
	    JSONObject josephObj = JSONObject.fromObject(json.toString());
	   
	    System.out.println(josephObj);
	   
	    String peoples = (String)josephObj.get("people");
	    String start = (String)josephObj.get("start");
	    String interval = (String)josephObj.get("interval");
		
	    String[] arr = peoples.split("\\,");
	    List<String> list = new ArrayList<String>();
	    list.addAll(Arrays.asList(arr));
	    
	    Joseph joseph=new Joseph();
	    String last =null;
	    if(start.matches( POSITIVE_NUMBER_REGEX )){
	    	if(interval.matches( POSITIVE_NUMBER_REGEX )){
	    		last = joseph.josephFunction(list,Integer.parseInt(start),Integer.parseInt(interval));
	    	}else{
	    		System.out.println("Interval isn`t a number!");
	    	}
	    }else{
	    	System.out.println("Start isn`t a number!");
	    }
	    	  	   
	    System.out.println(last);
	    
	    JSONObject lastOne=new JSONObject();
	    lastOne.put("lastPeople", last);
	    out.write(lastOne.toString());
	         
        out.flush();
        out.close();
	}

}
