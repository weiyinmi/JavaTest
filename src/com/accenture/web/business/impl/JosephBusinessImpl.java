package com.accenture.web.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.accenture.web.business.JosephBusiness;
import com.accenture.web.dto.JosephCircleRequest;
import com.accenture.web.dto.JosephCircleResponse;
import com.accenture.web.exception.BusinessException;
import com.accenture.web.service.JosephService;
import com.accenture.web.service.impl.JosephServiceImpl;

public class JosephBusinessImpl implements JosephBusiness{
	
	private static final String BUSINESS_EXCEPTION = "Business exception!";
	Logger logger = Logger.getLogger(JosephBusinessImpl.class);
	
	/**
	 * Call the Joseph function in service level,request the input data and response the result,
	 * @param josephCircleRequest  get the input for Joseph
	 * @return responsePeople  the last person
	 * @throws BusinessException
	 */
    public JosephCircleResponse callJoseph(JosephCircleRequest josephCircleRequest) throws BusinessException {       																				 
		
		String start = josephCircleRequest.getCircle().getStart();
		String interval = josephCircleRequest.getCircle().getInterval();
		String[] arr = josephCircleRequest.getCircle().getPersons();

		List<String> list = new ArrayList<String>();

		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}

//		JosephServiceImpl validate =new JosephServiceImpl();
		JosephService josephService = new JosephServiceImpl();
//		JosephServiceImpl joseph = new JosephServiceImpl();
		String last = null;
        
		//set service exception as inner exception
		try {
//			last = josephService.josephFunction(list, start, interval);
			last =josephService.josephFunction(list, start, interval);
			
		} catch (Exception e) {
			throw new BusinessException(BUSINESS_EXCEPTION,e);

		}finally{
			
		}						
		
		JosephCircleResponse responsePeople = new JosephCircleResponse();
		responsePeople.setLastPeople(last);
		return responsePeople;
    	
    }
}
