package com.everis.bootcamp.patronesServer.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class gestionClienteController {

	private final static Logger logger = LoggerFactory.getLogger(gestionClienteController.class);
	
	String status;
	
	@GetMapping("/closed")
	public ResponseEntity<String> index(){
		status = "CLOSED";
		logger.info("ESTADO: "+ status);
		return new ResponseEntity<String>(HttpStatus.OK).ok(status);
	}
	
	@GetMapping("/open")
	public ResponseEntity<String> index2(){
		status = "OPEN";
		logger.info("ESTADO: "+ status);
		return new ResponseEntity<String>(HttpStatus.OK).ok(status);
	}
	
	@GetMapping("/half-open")
	public ResponseEntity<String> index3(){
		status = "HALF_OPEN";
		logger.info("ESTADO: "+ status);
		return new ResponseEntity<String>(HttpStatus.OK).ok(status);
	}
}
