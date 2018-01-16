package com.balobanov.repositories.solr;

import com.balobanov.models.solr.SolrBank;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolrBankRepository extends SolrCrudRepository<SolrBank, Long> {}
