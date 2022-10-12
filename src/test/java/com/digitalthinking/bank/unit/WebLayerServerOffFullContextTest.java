/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitalthinking.bank.unit;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author OscarMayor
 */
@SpringBootTest()//Tells spring boot look for a main calss configuration
@AutoConfigureMockMvc/*allow test only http incoming request layer without start the serve, 
        but starting the full spring application context*/
public class WebLayerServerOffFullContextTest {

   @Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldReturnOkAndArrayList() throws Exception {
		this.mockMvc.perform(get("/customers")).andDo(print()).andExpect(status().isOk());			
	}

}
