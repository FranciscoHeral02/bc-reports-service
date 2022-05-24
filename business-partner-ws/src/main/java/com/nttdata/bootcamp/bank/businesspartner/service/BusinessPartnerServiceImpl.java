package com.nttdata.bootcamp.bank.businesspartner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.bank.businesspartner.documents.BusinessPartner;
import com.nttdata.bootcamp.bank.businesspartner.repository.BusinessPartnerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * En los Services va: 
 	-Logica de negocio
 	-Mapeo de DTOs a Objetos
 	-Se usan las operaciones Crud de los repositositorios
 	-Se golpean otros microservicios con algun cliente REST
 	
 * @author fhernala
 *
 */

@Service
public class BusinessPartnerServiceImpl implements BusinessPartnerService {

	@Autowired
	private BusinessPartnerRepository bsPartnerRepository;
	
	@Override
	public Flux<BusinessPartner> findAll() {
		return bsPartnerRepository.findAll();
	}

	@Override
	public Mono<BusinessPartner> findById(String id) {
		return bsPartnerRepository.findById(id);
	}

	@Override
	public Mono<BusinessPartner> save(BusinessPartner bp) {
		return bsPartnerRepository.save(bp);
	}

	@Override
	public Mono<Void> delete(BusinessPartner bp) {

		return bsPartnerRepository.delete(bp);
	}

}
