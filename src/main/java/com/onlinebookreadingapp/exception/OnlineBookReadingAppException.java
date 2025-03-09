package com.onlinebookreadingapp.exception;

import com.onlinebookreadingapp.dto.OnlineBookReadingAppResponse;

public class OnlineBookReadingAppException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private OnlineBookReadingAppResponse onlineBookReadingAppResponse;
	
	public OnlineBookReadingAppException(OnlineBookReadingAppResponse onlineBookReadingAppResponse) {
		this.onlineBookReadingAppResponse = onlineBookReadingAppResponse;
	}

	public OnlineBookReadingAppResponse getOnlineBookReadingAppResponse() {
		return onlineBookReadingAppResponse;
	}

}
