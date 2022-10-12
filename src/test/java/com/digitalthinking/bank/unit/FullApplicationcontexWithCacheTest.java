/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitalthinking.bank.unit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;






/**
 *
 * OscarMayor
 */
@SpringBootTest()//Tells spring boot look for a main calss configuration
/*
Spring test support allow have spring context cached between test, if you have 
multiples methods on a test case or multiples tast cases with the same configuration
they incur in acost o starting application only once. this anotation @DirtiesContext allow custom control
*/
public class FullApplicationcontexWithCacheTest {    
     
     /**
     * Test of Sanity check that fail if spring context can't start.
     */
    @Test
    public void contextLoadsNoExceptions(){      
    }
          
}
