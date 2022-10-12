/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitalthinking.bank.unit;


import com.digitalthinking.bank.entities.Customer;
import com.digitalthinking.bank.service.CustomerService;
import com.digitalthinking.bank.web.controller.BankCustomerController;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import static org.hamcrest.Matchers.isA;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import reactor.core.publisher.Flux;

/**
 *
 * OscarMayor
 */
@WebMvcTest(BankCustomerController.class)/*allow test only http incoming request layer without start the server, 
        spring boot instatiates only the BankCustomercontroller rather than the whole context*/
public class WebLayerServerOffTest {

   @Autowired //allow mock the weblayer without start the server
	private MockMvc mockMvc;   
   
   @MockBean //mock the server layer in order to have a unit test for weblayer 
   private CustomerService cs;
   
	@Test
	public void shouldReturnArrayList() throws Exception {              
             Faker faker = new Faker(new Locale("es-MX"));            
            List<Customer> customers = new ArrayList<>();
            for(int i=0; i<=1; i++){
                Customer mock = new Customer(faker.random().nextLong(), faker.code().isbn10(), faker.name().firstName());
                customers.add(mock);
            }     
         when(cs.findAll()).thenReturn(Flux.fromIterable(customers));
        this.mockMvc.perform(get("/customers")).andDo(print()).andExpect(status().isOk());
        // .andExpect(jsonPath("$.*", isA(ArrayList.class)));        
	}

}
