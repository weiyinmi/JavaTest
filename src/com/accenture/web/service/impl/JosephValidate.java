package com.accenture.web.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.accenture.web.service.JosephService;

public class JosephValidate implements JosephService {
	private static final String POSITIVE_NUMBER_REGEX = "^\\d+$";
	Logger logger = Logger.getLogger(JosephValidate.class);
	
	/**
	 * Achieve joseph problem,when josephFunction is working, peoples would be
	 * removed one by one,until leave the last one.
	 * 
	 * @param list
	 *            input several peoples as a list
	 * @param start
	 *            start index
	 * @param interval
	 *            interval
	 * @return the last person
	 * @throws IllegalArgumentException
	 */
	public String josephFunction(List<String> list, String start, String interval) {
		
//		int intStart = Integer.parseInt(start);
//		int intInterval = Integer.parseInt(interval);

		if (list != null) {
			if (start.matches(POSITIVE_NUMBER_REGEX)) {
	//		if (intStart > 0) {
				if (interval.matches(POSITIVE_NUMBER_REGEX)) {
					
					int a = Integer.parseInt(start) - 1; // index
					while (list.size() > 1) {
						a = a + Integer.parseInt(interval);
						a = a % (list.size());
						if (a < 0) {
							list.remove(list.size() - 1);
							a = 0;
						} else {
							list.remove(a);
						}
					}

				} else {
					logger.info("Interval isn`t a non-negative integer!");
					throw new IllegalArgumentException("Interval isn`t a non-negative integer!");
				}
			} else {
				logger.info("Start isn`t a positive integer!");
				throw new IllegalArgumentException("Start isn`t a positive integer!");
			}
		} else {
			logger.info("Persons cann`t be null!");
			throw new IllegalArgumentException("Persons cann`t be null!");
		}
		return list.get(0);
	}
}
