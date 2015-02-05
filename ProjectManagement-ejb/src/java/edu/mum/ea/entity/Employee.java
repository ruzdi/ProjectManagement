/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author SyedAliAhmed
 */
@Entity
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;
    private String designation;
    private String email;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Username user;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public void employee(){
//        address = new Address();
    }

    public Username getUser() {
        return user;
    }

    public void setUser(Username user) {
        this.user = user;
    }
    
    
    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    
   
    
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employee")
//    private Resource resource;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
//    private EmployeeRole employeeRole;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.designation, other.designation)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name=" + name + ", designation=" + designation + '}';
    }
    


  

    
}
