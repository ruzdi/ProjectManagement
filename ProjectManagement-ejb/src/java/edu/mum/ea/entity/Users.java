/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author SyedAliAhmed
 */
@Entity
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int id;
    private String username;
    private String password;
    private String roleName;

    @OneToOne(mappedBy = "user")
    private Employee employee;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private EmployeeRole employeeRole;
//
//    public EmployeeRole getEmployeeRole() {
//        return employeeRole;
//    }
//
//    public void setEmployeeRole(EmployeeRole employeeRole) {
//        this.employeeRole = employeeRole;
//    }
    
//    public void addRole(EmployeeRole employeeRole) {
//        this.employeeRole = employeeRole;
//        employeeRole.setUser(this);
//    }
    
    public Users(){}

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Users other = (Users) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Users{" + "username=" + username +  ", employee=" + employee +  '}';
    }

    
    
    
    
    
}
