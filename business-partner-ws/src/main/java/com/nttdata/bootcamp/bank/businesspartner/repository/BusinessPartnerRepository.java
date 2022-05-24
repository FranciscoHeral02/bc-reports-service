package com.nttdata.bootcamp.bank.businesspartner.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.bank.businesspartner.documents.BusinessPartner;

public interface BusinessPartnerRepository extends ReactiveMongoRepository<BusinessPartner, String> {

}
