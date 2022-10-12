package com.digitalthinking.bank.repository;


import com.digitalthinking.bank.entities.Customer;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 *
 * OscarMayor
 */
/*Even we can use RxJava2CrudRepository, we have the same behavior as the ReactiveCrudRepository,
but with the results and parameter types from RxJava*/
@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, Long> {
    @Query("{ 'setFullName': ?0 }")
    Flux<Customer> findByFullName(Mono<String> setFullName);   
}