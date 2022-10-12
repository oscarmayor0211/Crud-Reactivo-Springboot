package com.digitalthinking.bank.entities;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author sOscarMayor
 */
@Document
public class Customer implements Serializable {

    @Id  
     @ApiModelProperty(notes = "Id of client.", name = "id",
            required = true, value = "id", example = "1")
    private long id;
    private Date creationDate = new Date();
     @ApiModelProperty(notes = "code of cliente.", name = "code",
            required = true, value = "id", example = "AKSI")
    private String code;
      @ApiModelProperty(notes = "firts name of client", name = "firstName",
            required = true, value = "code", example = "Jaime")
    private String firstName;
    private String secondName;
     @ApiModelProperty(notes = "first surname of client", name = "firstSurName",
            required = true, value = "firstSurName", example = "garcia")
    private String firstSurName;
    private String secondSurname;
    private String fullName;
     @ApiModelProperty(notes = "email of client", name = "email",
            required = true, value = "email", example = "garcia@lmail.com")
    private String email;
      @ApiModelProperty(notes = "phone of client", name = "phone",
            required = true, value = "phone", example = "65985742")
    private String phone;
    private String addres;
    private String nationality;
    private List<Products> products;

    
    public Customer() {       
    }
    /**
     * 
     * @param id
     * @param code
     * @param firstName 
     */
    public Customer(long id, String code, String firstName) {
        this.creationDate = new Date();
        this.id = id;
        this.code = code;
        this.firstName = firstName;
    }
/**
 * 
 * @param id
 * @param creationDate
 * @param code
 * @param firstName
 * @param secondName
 * @param firstSurName
 * @param secondSurname
 * @param fullName
 * @param email
 * @param phone
 * @param addres
 * @param nationality
 * @param products 
 */
    public Customer(long id, Date creationDate, String code, String firstName, String secondName, String firstSurName, String secondSurname, String fullName, String email, String phone, String addres, String nationality, List<Products> products) {
        this.id = id;
        this.creationDate = creationDate;
        this.code = code;
        this.firstName = firstName;
        this.secondName = secondName;
        this.firstSurName = firstSurName;
        this.secondSurname = secondSurname;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.addres = addres;
        this.nationality = nationality;
        this.products = products;
    }
   
    
    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstSurName() {
        return firstSurName;
    }

    public void setFirstSurName(String firstSurName) {
        this.firstSurName = firstSurName;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    

}
