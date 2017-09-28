package com.accenture.webtest.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.accenture.webtest.dto.JosephCircleRequest;
import com.accenture.webtest.dto.JosephCircleResponse;
import com.accenture.webtest.exception.BusinessException;
import com.accenture.webtest.service.JosephService;

public class JosephBusiness {
	
	Logger logger = Logger.getLogger(JosephBusiness.class);
	
	/**
	 * Call the Joseph function in service level,request the input data and response the result,
	 * @param josephCircleRequest  get the input for Joseph
	 * @return responsePeople  the last person
	 * @throws BusinessException
	 */
    public JosephCircleResponse responseequestAndResponse(JosephCircleRequest josephCircleRequest) throws BusinessException {       																				 
		
		String start = josephCircleRequest.getCircle().getStart();
		String interval = josephCircleRequest.getCircle().getInterval();
		String[] arr = josephCircleRequest.getCircle().getPersons();

		List<String> list = new ArrayList<String>();

		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}

		JosephService joseph = new JosephService();
		String last = null;
        
		//set service exception as inner exception
		try {
			last = joseph.josephFunction(list, start, interval);
			
		} catch (Exception e) {
			throw new BusinessException("Business exception!",e);

		}						
		
		JosephCircleResponse responsePeople = new JosephCircleResponse();
		responsePeople.setLastPeople(last);
		return responsePeople;
    	
    }
}
