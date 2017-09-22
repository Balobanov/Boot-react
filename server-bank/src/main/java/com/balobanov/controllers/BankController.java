/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.balobanov.controllers;

import com.balobanov.models.Bank;
import com.balobanov.services.abstraction.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankController {

    private BankService service;

    @Autowired
    public void setService(BankService service) {
        this.service = service;
    }

    @RequestMapping(value = "banks", method = RequestMethod.GET)
    public List<Bank> all(){
       return service.getAll();
    }

    @RequestMapping(value = "banks", method = RequestMethod.POST, consumes = "application/json")
    public Bank save(@RequestBody Bank bank){
        return service.save(bank);
    }

    @RequestMapping(value = "banks", method = RequestMethod.PUT, consumes = "application/json")
    public Bank update(@RequestBody Bank bank){
        return service.update(bank);
    }

    @RequestMapping(value = "banks", method = RequestMethod.DELETE, consumes = "application/json")
    public Bank delete(@RequestBody Bank bank){
        return service.delete(bank);
    }
}
