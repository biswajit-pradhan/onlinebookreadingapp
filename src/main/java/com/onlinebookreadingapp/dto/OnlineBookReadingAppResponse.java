package com.onlinebookreadingapp.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OnlineBookReadingAppResponse {

	private String message;
	private HttpStatus status;
}