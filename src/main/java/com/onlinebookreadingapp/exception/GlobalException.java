package com.onlinebookreadingapp.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalException {

	@ExceptionHandler(OnlineBookReadingAppException.class)
	public ResponseEntity<?> handleBasicBankingSystemException(OnlineBookReadingAppException e) {
		log.error("Some error occured!!");
		return ResponseEntity.status(e.getOnlineBookReadingAppResponse().getStatus())
				.body(e.getOnlineBookReadingAppResponse());
	}

//	@ExceptionHandler(UserAlredayExistException.class)
//	public ResponseEntity<?> handleUserAlredayExistException(UserAlredayExistException e) {
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//	}
//
//	@ExceptionHandler(JwtExpiredException.class)
//	public ResponseEntity<?> handleJwtExpiredException(JwtExpiredException e) {
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> response = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			response.put(fieldName, message);
		});

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
}
