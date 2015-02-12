/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.entity;

import edu.mum.ea.entity.Employee;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author SyedAliAhmed
 */
@Entity
public class EmployeeRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String roleName;
        
//    @ManyToOne
//    private Employee employee;

    
//    @OneToOne
//    private Users user;

    public EmployeeRole(){
//        user = new Users();
    }
//    public Users getUser() {
//        return user;
//    }
//
//    public void setUser(Users user) {
//        this.user = user;
//    }
       
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final EmployeeRole other = (EmployeeRole) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
  
    @Override
    public String toString() {
        return "java.edu.mum.ea.entity.EmployeeRole[ id=" + id + " ]";
    }
    
}
