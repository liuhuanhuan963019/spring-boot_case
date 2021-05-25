package com.yxyz.conf;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
* Copyright @ 2020 Zonlyn. All rights reserved.

* @Description: 该类的功能描述
*
* @version: v1.0.0
*/
@RestControllerAdvice
public class GloabException
{
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public String MethodArgumentNotValiExceptionHandler(MethodArgumentNotValidException e)
	{
		ObjectError objectError = e.getBindingResult().getAllErrors().get(0);

		return objectError.getDefaultMessage();
	}

}
