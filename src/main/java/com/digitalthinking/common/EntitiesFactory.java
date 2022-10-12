/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitalthinking.common;

import com.digitalthinking.bank.entities.Customer;
import com.digitalthinking.bank.entities.Products;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * OscarMayor
 */
public class EntitiesFactory {
    
    private static final Faker FAKER = new Faker(new Locale("es-MX"));  
    /**
     * 
     * @param id use 0 for autogenerateid
     * @return Customer entity filled
     */
    public static Customer buildCustomer(long id){
         Customer mock = new Customer(id !=0 ? id: FAKER.random().nextLong(), FAKER.code().isbn10(), FAKER.name().firstName());     
         return mock;
    }
    /**
     * Return a full and deep Customer entity filled 
     * @return 
     */
    public static Customer buildCustomerDeep(){
        Products mp = new Products(FAKER.random().nextLong(), FAKER.business().creditCardNumber(), new Date(),FAKER.business().creditCardType(), "Active");
        Products mp2 = new Products(FAKER.random().nextLong(), FAKER.business().creditCardNumber(), new Date(),FAKER.business().creditCardType(), "Active");
        List<Products> ps = new ArrayList<>();
        ps.add(mp);
        ps.add(mp2); 
         Customer mock = new Customer(FAKER.random().nextLong(),new Date(),
                 FAKER.code().asin(), FAKER.name().firstName(),
         FAKER.name().name(),FAKER.name().lastName(),FAKER.name().lastName(),FAKER.name().fullName(),
                 FAKER.internet().emailAddress(),
         FAKER.phoneNumber().phoneNumber(),FAKER.address().fullAddress(),FAKER.nation().nationality(),ps);     
         return mock;
    }
}
