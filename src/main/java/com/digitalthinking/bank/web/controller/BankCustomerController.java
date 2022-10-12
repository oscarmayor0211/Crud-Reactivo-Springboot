package com.digitalthinking.bank.web.controller;


import aj.org.objectweb.asm.Opcodes;
import com.digitalthinking.bank.entities.Customer;
import com.digitalthinking.bank.exception.BusinessRuleExceptions;
import com.digitalthinking.bank.service.CustomerService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



/**
 *
 * OscarMayor
 */
@RestController
@RequestMapping(value = "/customers")//Maps http request to handler methods and path
@EnableHypermediaSupport(type = { EnableHypermediaSupport.HypermediaType.HAL })//Allow activate Hypermedia Aplication Language
public class BankCustomerController {
     
    @Autowired
    CustomerService cs;
  
  
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Customer> create(@RequestBody Customer e) {   
      Optional.ofNullable(e.getCode()).filter(c -> !c.isEmpty())
              .orElseThrow(() -> {
          return new BusinessRuleExceptions("Bad request", "The customer document need have not emty code",HttpStatus.BAD_REQUEST);
      });
      return cs.create(e);
    }
 
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Customer e) {
         cs.update(e);
    }
    //using ResponseEntity we can manage a whole of http response, boby,status code and header
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Customer>> findById(@PathVariable("id") long id) {
        return cs.findById(id).map(x -> ResponseEntity.ok(x)).defaultIfEmpty(ResponseEntity.notFound().build());
    }
   /*The http response requeried only one Mono or Flux, as the body horever we should use
    *Content-Type header to let the client know that it can be consumed as a the usal application/json 
    *or as stream application/stream+json 
    *(Using MediaType.APPLICATION_STREAM_JSON_VALUE the response is sent as Server sent events, this suscriber approach 
    *is ussually when we have a constant message flow)
    *HATEOAST the idea in hypermedia is enrich the representation of resource 
    *with hypermedia lements, the simples form that iare links Oficial documentations is a TODO for webflux
    *https://docs.spring.io/spring-hateoas/docs/current/reference/html/#server.link-builder.webmvc
    */    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)  
    public Flux<ResponseEntity<EntityModel<Customer>>> findAll() { 
      return cs.findAll().map(x -> ResponseEntity.ok(EntityModel.of(x).add(              
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BankCustomerController.class).findById(x.getId())).withSelfRel())))
                .switchIfEmpty(Mono.error(new BusinessRuleExceptions("02-emty","There are no content",HttpStatus.NO_CONTENT)));
     }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        cs.delete(id).subscribe();
    }    
}



