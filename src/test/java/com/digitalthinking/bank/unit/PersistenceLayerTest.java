/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitalthinking.bank.unit;

import com.digitalthinking.bank.entities.Customer;
import com.digitalthinking.bank.repository.CustomerRepository;
import com.digitalthinking.common.EntitiesFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 *
 * OscarMayor
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class PersistenceLayerTest {   
    
    @Autowired
    CustomerRepository cr;

    @DisplayName("Given object When save object using MongoDB template Then object can be found")
    @Test
    void testGivenACustomerThenSave() throws Exception {    
       Customer mock = EntitiesFactory.buildCustomerDeep();    
        Mono<Customer> saved = cr.save(mock);
     
         StepVerifier
                .create(saved)
                .assertNext(customer -> assertNotNull(customer.getId()))
                .expectComplete()
                .verify();
    }
    
     @Test
    public void testGivenId_whenFindCustomerById_thenFindProduct() {
            Customer mock = EntitiesFactory.buildCustomerDeep();   
            mock.setFullName("carlos garcia");
             cr.save(mock).block();
      
        Flux<Customer> customerFlux = cr.findByFullName(Mono.just("carlos garcia"));

        StepVerifier.create(customerFlux)
                .assertNext(customer -> {
                    assertEquals("carlos garcia", customer.getFullName());               
                    assertNotNull(customer.getId());
                })
                .expectComplete();
                
    }
  
}
