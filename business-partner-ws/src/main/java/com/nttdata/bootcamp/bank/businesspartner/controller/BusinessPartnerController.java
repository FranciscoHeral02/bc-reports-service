package com.nttdata.bootcamp.bank.businesspartner.controller;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.nttdata.bootcamp.bank.businesspartner.documents.BusinessPartner;
import com.nttdata.bootcamp.bank.businesspartner.service.BusinessPartnerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/BusinessPartnerService")
public class BusinessPartnerController {
	
	private static final Logger LOGGER = LogManager.getLogger(BusinessPartnerController.class);
	
	@Autowired
	private BusinessPartnerService bsPartnerService;
	
	@PostMapping
	public Mono<ResponseEntity<Map<String, Object>>> registerNewBusinessPartner(@Valid @RequestBody Mono<BusinessPartner> monoBsPartner){
		Map<String, Object>	response = new HashMap<>();
		
		return monoBsPartner.flatMap(bsPartner -> {
			return bsPartnerService.save(bsPartner).map(c->{
				response.put("cliente", c);
				response.put("mensaje", "guardado con exito");
				response.put("timestamp", new Date());
				
				return ResponseEntity
					   .created(URI.create("/BusinessPartnerService/".concat(c.getId())))
					   .contentType(MediaType.APPLICATION_JSON)
					   .body(response);
			});
		}).onErrorResume(t->{
		    return Mono.just(t).cast(WebExchangeBindException.class)
		    	     .flatMap(e -> Mono.just(e.getFieldErrors()))
		    	     .flatMapMany(Flux::fromIterable)
		    	     .map(fieldError->"El campo:" + fieldError.getField() +" "+ fieldError.getDefaultMessage())
		    	     .collectList()
		    	     .flatMap(list->{
		    	 
		    	    	response.put("errors", list);
		 				response.put("timestamp", new Date());
		 				response.put("status", HttpStatus.BAD_REQUEST.value());
		 				
		 				return Mono.just(ResponseEntity.badRequest().body(response));
		    	    	 
		    	     });
		    	     
		    		
		});
		
	}
}
