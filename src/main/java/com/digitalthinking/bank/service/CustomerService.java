/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitalthinking.bank.service;


import com.digitalthinking.bank.entities.Customer;
import com.digitalthinking.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * OscarMayor
 */
@Service
public class CustomerService {
    
     @Autowired
    CustomerRepository r;

    public Mono<Customer> create(Customer c) {
       return r.save(c);
    }

    public Mono<Customer> findById(long id) {
        return r.findById(id);
    }

    public Flux<Customer> findAll() {
        return r.findAll();
    }

    public void update(Customer c) {
        r.save(c).subscribe();
    }

    public Mono<Void> delete(long id) {
        return r.deleteById(id);
    }

}
