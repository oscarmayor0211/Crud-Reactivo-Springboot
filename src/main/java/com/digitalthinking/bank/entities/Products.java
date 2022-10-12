/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitalthinking.bank.entities;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * OscarMayor
 */
//@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Document
public class Products {
    
    @Id  
     @ApiModelProperty(notes = "Id of product.", name = "id",
            required = true, value = "id", example = "1")
    private long id;
     @ApiModelProperty(notes = "code of product.", name = "code",
            required = true, value = "code", example = "156287")
    private String code;
    private Date signedDate;
     @ApiModelProperty(notes = "name of product.", name = "name",
            required = true, value = "name", example = "Tarjeta multipuntos")
    private String name;
      @ApiModelProperty(notes = "Status of product.", name = "status",
            required = true, value = "status", example = "Active")
    private String status;  

    public Products() {
    }
    
    /**
     * 
     * @param id
     * @param code
     * @param signedDate
     * @param name
     * @param status 
     */
    public Products(long id, String code, Date signedDate, String name, String status) {
        this.id = id;
        this.code = code;
        this.signedDate = signedDate;
        this.name = name;
        this.status = status;
    }
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(Date signedDate) {
        this.signedDate = signedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
