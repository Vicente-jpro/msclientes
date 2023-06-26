package com.example.msclientes.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.msclientes.exceptions.ClienteNotFoundException;
import com.example.msclientes.utils.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	private String mensagemErro;

	@ResponseBody
	@ExceptionHandler(ClienteNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors clienteNotFoundExceptionHandle(ClienteNotFoundException ex) {
		this.mensagemErro = ex.getMessage();
		return new ApiErrors(mensagemErro);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors validateFieldsHandle(MethodArgumentNotValidException ex) {
		List<String> erros = ex.getBindingResult()
				.getAllErrors()
				.stream()
				.map(erro -> erro.getDefaultMessage()).collect(Collectors.toList());

		return new ApiErrors(erros);
	}
}
