package com.balobanov.models.solr;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

public class SolrBank {

    @Id
    @Field
    private Long id;

    @Field
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
