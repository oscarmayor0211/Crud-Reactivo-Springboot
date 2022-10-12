/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitalthinking.bank.integration;




import com.digitalthinking.bank.entities.Customer;
import com.digitalthinking.bank.repository.CustomerRepository;
import com.digitalthinking.bank.service.CustomerService;
import com.digitalthinking.common.EntitiesFactory;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 *This set of test allow check the behavior the application
 * that start the application an listen like as it would do in production 
 *then send an http request and assert the response.
 * OscarMayor
 */
/*Tells spring boot look for a main class configuration, start the server with 
*a ramdon port usesfull for avoid conflicts on a test enviroments
*/
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
//@WebFluxTest(controllers = BankCustomerController.class)
@Import(CustomerService.class)
public class HttpRequestTest {
    
   @LocalServerPort //inject a ramdom port
   private int port;  
 
   @MockBean //mock the server layer in order to have a unit test for weblayer 
   private CustomerRepository cr;
   @Autowired //class for easy call a restfull service, and test webflux endpoits, it is similar to MockMvc 
   private WebTestClient webClient;  

    /**
     * Test of create method, of class BankCustomerController.
     */
    @Test
    public void testCreate() {  
        Customer mock = EntitiesFactory.buildCustomerDeep();     
 
        Mockito.when(cr.save(mock)).thenReturn(Mono.just(mock));
        //make a http request using reactive 
        webClient.post()
               .uri("http://localhost:" + port +"/customers")
               .contentType(MediaType.APPLICATION_JSON)
               .body(BodyInserters.fromObject(mock))
               .exchange()
               .expectStatus().isCreated();
       //check if the method was call with the same arguments
        Mockito.verify(cr, times(1)).save(mock);
    }

    /**
     * Test of update method, of class BankCustomerController.
     */
    @Test
    public void testUpdate() {
        Customer mock = EntitiesFactory.buildCustomer(0); 
           Mockito
            .when(cr.save(mock))
            .thenReturn(Mono.just(mock));
        //make a http request using reactive 
        webClient.put()               
                .uri("http://localhost:" + port +"/customers")         
               .contentType(MediaType.APPLICATION_JSON)
               .body(BodyInserters.fromObject(mock))
               .exchange()
               .expectStatus().isOk();
       //check if the method was call with the same arguments
        Mockito.verify(cr, times(1)).save(mock);
    }
    /**
     * Test of findById method, of class BankCustomerController.
     */
    @Test
    public void testFindById() {
       Customer mock = EntitiesFactory.buildCustomerDeep();  
       mock.setId(2);
       mock.setCode("Test");
        Mockito
            .when(cr.findById((long)2))
            .thenReturn(Mono.just(mock));
 
        webClient.get()
              .uri("http://localhost:" + port +"/customers/{id}", 2)              
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.firstName").isNotEmpty()
            .jsonPath("$.id").isEqualTo(2)
            .jsonPath("$.code").isEqualTo("Test");
        //check if the method was call with the same arguments
        Mockito.verify(cr, times(1)).findById((long)2);
    }

    /**
     * Test of findAll method, of class BankCustomerController.
     */
    @Test
    public void testFindAll() {                 
            List<Customer> customers = new ArrayList<>();
            for(int i=0; i<=1; i++){
                Customer mock = EntitiesFactory.buildCustomer(0);  
                customers.add(mock);
            }     
         when(cr.findAll()).thenReturn(Flux.fromIterable(customers));
        
         webClient.get()
            .uri("http://localhost:" + port +"/customers")           
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(Customer.class); 
         
        Mockito.verify(cr, times(1)).findAll();      
    }
    /**
     * Test of delete method, of class BankCustomerController.
     */
    @Test
    public void testDelete() {
         Mono<Void> voidReturn  = Mono.empty();
        Mockito
            .when(cr.deleteById((long)1))
            .thenReturn(voidReturn);
 
        webClient.delete()
                 .uri("http://localhost:" + port +"/customers/{id}", 1) 
            .exchange()
            .expectStatus().isOk();
    }
  
}











//  private final WebClient.Builder webClientBuilder;
    /* //define timeout
    TcpClient tcpClient = TcpClient
            .create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .doOnConnected(connection -> {
                connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
            });

    public CustomerService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public List<CustomerReq> getTransacctions() {
        WebClient client = webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .baseUrl("http://bank-transactions")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://bank-transactions"))
                .build();
        List<CustomerReq> block = client.method(HttpMethod.GET).uri("/transactions")
                .retrieve().bodyToFlux(CustomerReq.class).collectList().block();
        return block;
    }*/  
    