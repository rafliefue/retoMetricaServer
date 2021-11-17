package com.everis.bootcamp.patronesServer.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.bootcamp.patronesServer.endPoints.EndpointEstados;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
public class MetricaController {
	
	private final static Logger logger = LoggerFactory.getLogger(MetricaController.class);
	
	String status;
	
	@Autowired
	EndpointEstados estados;
	
	private Counter counterOpen;
	private Counter counterHalfOpen;
	private Counter counterClosed;
	private Counter total;
	
	/**Aqui hago el contador que se podra ver en locoalhost:8888/actuator/prometheus
	 * **/

	public MetricaController(MeterRegistry registry) {
		this.counterOpen = Counter.builder("invocaciones.open").description("Invocaciones open").register(registry);
		this.counterHalfOpen = Counter.builder("invocaciones.halfopen").description("Invocaciones halfopen").register(registry);
		this.counterClosed = Counter.builder("invocaciones.closed").description("Invocaciones closed").register(registry);
		this.total = Counter.builder("invocaciones.total").description("Invocaciones totales").register(registry);
	}
	
	@GetMapping("/closed")
	public ResponseEntity<String> closed(){
		//Incremento del contador
		counterClosed.increment();
		total.increment();
		this.estados.writeOperation("closed");
		
		status = "CLOSED";
		logger.info("ESTADO: "+ status);
		return new ResponseEntity<String>(HttpStatus.OK).ok(status);
	}
	
	@GetMapping("/open")
	public ResponseEntity<String> open(){
		//Incremento del contador
		counterOpen.increment();
		total.increment();
		this.estados.writeOperation("open");
		
		status = "OPEN";
		logger.info("ESTADO: "+ status);
		return new ResponseEntity<String>(HttpStatus.OK).ok(status);
	}
	
	@GetMapping("/half-open")
	public ResponseEntity<String> halfOpen(){
		
		//Incremento del contador
		counterHalfOpen.increment();
		total.increment();
		this.estados.writeOperation("half-open");
		
		status = "HALF_OPEN";
		logger.info("ESTADO: "+ status);
		return new ResponseEntity<String>(HttpStatus.OK).ok(status);
	}

}
