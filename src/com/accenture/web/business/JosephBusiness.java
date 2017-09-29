package com.accenture.web.business;

import com.accenture.web.dto.JosephCircleRequest;
import com.accenture.web.dto.JosephCircleResponse;
import com.accenture.web.exception.BusinessException;

public interface JosephBusiness {
	public JosephCircleResponse callJoseph(JosephCircleRequest josephCircleRequest) throws BusinessException;
}

