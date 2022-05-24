package com.nttdata.bootcamp.bank.businesspartner.service;

import com.nttdata.bootcamp.bank.businesspartner.documents.BusinessPartner;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BusinessPartnerService {
	
	/** 
	 * Flux funciona como una lista para la programacion reactiva en JAVA WEBFLUX
	 * Mono funciona como un solo objeto
	 */	
	
	/**
	 * metodo para buscar a todos los business partner
	 * @return  (lista de todos los business partner)
	 */
	
	public Flux<BusinessPartner> findAll ();
	
	
	/** 
	 * @param id (id del business partner)
	 * @return (retorna un solo objeto business partner)
	 */
	public Mono<BusinessPartner> findById(String id);
	
	public Mono<BusinessPartner> save (BusinessPartner bp);
	
	public Mono<Void> delete(BusinessPartner bp);
 }
