/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitalthinking.bank.unit;




import com.digitalthinking.bank.entities.Customer;
import com.digitalthinking.bank.service.CustomerService;
import com.digitalthinking.bank.web.controller.BankCustomerController;
import com.digitalthinking.common.EntitiesFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;


/**
 * OscarMayor
 *This set of test allow check the behavior of the web layer application
 * listening like as it would do in production, sending and http requests and assert if method was called
 * and http status is the expected.
 */
@ExtendWith(SpringExtension.class)//support test in junit5
@WebFluxTest(controllers = BankCustomerController.class) // allow to test specific controller ,without start the server and autocinfigure the WebTestClient 
public class WebFluxHttpRequestTest {
 
   @MockBean //mock the server layer in order to have a unit test for weblayer 
   private CustomerService cs;
   @Autowired //class for easy call a restfull service, and test webflux endpoits, it is similar to MockMvc 
   private WebTestClient webClient;
   
     /**
     * Test call of create method, of class BankCustomerController.
     */
    @Test
    public void testCreate() {       
        Customer mock = EntitiesFactory.buildCustomer(0); 
        Mockito.when(cs.create(mock)).thenReturn(Mono.just(mock));
       //make a http request using reactive
        webClient.post()
               .uri("/customers")
               .contentType(MediaType.APPLICATION_JSON)
               .body(BodyInserters.fromObject(mock))
               .exchange()
               .expectStatus().isCreated();
       //check if the method was call with the same arguments
        Mockito.verify(cs, times(1)).create(mock);
    }

    /**
     * Test of update method, of class BankCustomerController.
     */
    @Test
    public void testUpdate() {
        Customer mock = EntitiesFactory.buildCustomer(0);  
      
        Mockito.doNothing().when(cs).update(mock);         
        //make a http request using reactive 
        webClient.put()
               .uri("/customers")
               .contentType(MediaType.APPLICATION_JSON)
               .body(BodyInserters.fromObject(mock))
               .exchange()
               .expectStatus().isOk();
       //check if the method was call with the same arguments
        Mockito.verify(cs, times(1)).update(mock);
    }
    /**
     * Test of findById method, of class BankCustomerController.
     */
    @Test
    public void testFindById() {
       Customer mock = EntitiesFactory.buildCustomer(2);  
       mock.setCode("Test");
        Mockito
            .when(cs.findById(2))
            .thenReturn(Mono.just(mock));
 
       webClient.get().uri("/customers/{id}", 2)
               .exchange()
               .expectStatus().isOk()
               .expectBody()
               .jsonPath("$.firstName").isNotEmpty()
               .jsonPath("$.id").isEqualTo(2)
               .jsonPath("$.code").isEqualTo("Test");
        //check if the method was call with the same arguments
        Mockito.verify(cs, times(1)).findById(2);
    }  

    /**
     * Test of delete method, of class BankCustomerController.
     */
    @Test
    public void testDelete() {
         Mono<Void> voidReturn  = Mono.empty();
        Mockito
            .when(cs.delete(1))
            .thenReturn(voidReturn);
 
        webClient.delete().uri("/customers/{id}", 1)
            .exchange()
            .expectStatus().isOk();
    }
    
    /**
     * Test of findAll method, of class BankCustomerController.
     */
  /*  @Test
    public void testFindAll() {                 
            List<Customer> customers = new ArrayList<>();
            for(int i=0; i<=1; i++){
                Customer mock = EntitiesFactory.buildCustomerDeep();  
                customers.add(mock);
            }     
            
         when(cs.findAll()).thenReturn(Flux.fromIterable(customers));        
         webClient.get().uri("/customers")           
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(Customer.class); 
         
        Mockito.verify(cs, times(1)).findAll();
      
    }*/
}
