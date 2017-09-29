package com.accenture.web.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
//import net.sf.json.JSONObject;

import com.accenture.web.business.JosephBusiness;
import com.accenture.web.business.impl.JosephBusinessImpl;
import com.accenture.web.dto.JosephCircleRequest;
import com.accenture.web.dto.RequestConverter;
import com.accenture.web.dto.ResponseConverter;

/**
 * Servlet implementation class StringServle
 */
@WebServlet("/ConverterServlet")
public class ConverterServlet extends HttpServlet {
	private static final String FAILED_RUN_JOSEPH = "Failed to run Joseph!";
	private static final String CONTENT_TYPE = "application/json";
	private static final String Encoded_FORMANT = "utf-8";
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ConverterServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConverterServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * Receive a json object from front-end,achieve Joseph problem ,then response
	 * result to js.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding(Encoded_FORMANT);
		response.setContentType(CONTENT_TYPE);

		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			StringBuilder jsonBuilder = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line = null;
			while ((line = reader.readLine()) != null) {
				jsonBuilder.append(line);
			}

			JSONObject jsonObject = new JSONObject(jsonBuilder.toString());

			RequestConverter requestCon = new RequestConverter();        // use the RequestConverter	
			JosephCircleRequest josephCircleRequest = requestCon.fromJson(jsonObject);																			 
																						
			JosephBusiness josephBusiness = new JosephBusinessImpl(); 
	//		JosephBusinessImpl josephBusiness = new JosephBusinessImpl();    //Business level
			josephBusiness.callJoseph(josephCircleRequest);
			
			ResponseConverter responseConverter = new ResponseConverter();     //use the ResponseConverter	
			JSONObject lastOne = responseConverter.toJson(josephBusiness.callJoseph(josephCircleRequest));
			out.write(lastOne.toString());
			out.flush();  
             
		} catch (Exception e) {
			logger.error(FAILED_RUN_JOSEPH,e);
		}finally{
			if(out!=null){
				
				out.close();
			}
		}
	}

}