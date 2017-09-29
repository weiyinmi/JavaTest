package com.accenture.web.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.accenture.web.exception.BusinessException;
import com.accenture.web.service.impl.Joseph;

//import org.json.JSONObject;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class JsonServlet
 */
@WebServlet("/JsonServlet")
public class JsonServlet extends HttpServlet {
	private static final String Encoded_FORMANT = "utf-8";
	private static final long serialVersionUID = 1L;
	private static final String POSITIVE_NUMBER_REGEX = "^\\d+$";
	Logger logger = Logger.getLogger(JsonServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JsonServlet() {
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
	 * Receive a json object from front-end,achieve joseph problem ,then response
	 * result to js.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding(Encoded_FORMANT);
		response.setContentType("application/json");
		PrintWriter out = null;
		
		try {
		out = response.getWriter();
		StringBuilder json = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line = null;
		while ((line = reader.readLine()) != null) {
			json.append(line);
		}

		// JSONObject josephObj = new JSONObject(json.toString());
		JSONObject josephObj = JSONObject.fromObject(json.toString());

		// String peoples = (String)josephObj.get("people");

		List<String> list = (List<String>) josephObj.get("people");
		String start = (String) josephObj.get("start");
		String interval = (String) josephObj.get("interval");	
				
		Joseph joseph = new Joseph();
		String last = null;
		if (start.matches(POSITIVE_NUMBER_REGEX)) {
			if (interval.matches(POSITIVE_NUMBER_REGEX)) {
				
					last = joseph.josephFunction(list, Integer.parseInt(start), Integer.parseInt(interval));
			
			} else {
				logger.info("Interval isn`t a number!");
			}
		} else {
			logger.info("Start isn`t a number!");
		}

		JSONObject lastOne = new JSONObject();
		lastOne.put("lastPeople", last);
		out.write(lastOne.toString());
		out.flush();
		
		} catch (NumberFormatException | BusinessException e) {
			logger.info(e);
		}finally{
			if(out!=null){
				
				out.close();
			}
		}
	}

}
