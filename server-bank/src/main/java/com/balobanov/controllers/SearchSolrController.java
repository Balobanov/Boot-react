package com.balobanov.controllers;

import com.balobanov.models.solr.SolrBank;
import com.balobanov.repositories.solr.SolrBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/search")
public class SearchSolrController {

    private SolrBankRepository solrCrudRepository;

    @RequestMapping(value = "/find")
    public Iterable<SolrBank> searchById(){
        return solrCrudRepository.findAll();
    }

    @Autowired
    public void setSolrCrudRepository(SolrBankRepository solrCrudRepository) {
        this.solrCrudRepository = solrCrudRepository;
    }
}