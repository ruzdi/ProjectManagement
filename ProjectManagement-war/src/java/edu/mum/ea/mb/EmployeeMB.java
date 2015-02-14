/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.mb;

import edu.mum.ea.ejb.EmployeeEJB;
import edu.mum.ea.entity.Address;
import edu.mum.ea.entity.Employee;
import edu.mum.ea.entity.EmployeeRole;
import edu.mum.ea.entity.Users;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author SyedAliAhmed
 */
@ManagedBean
@RequestScoped
public class EmployeeMB {

    /**
     * Creates a new instance of EmployeeMB
     */
    @EJB
    EmployeeEJB ejb;

    Employee employee;
    Address address;
    Users user;
//    EmployeeRole employeeRole;
    private String username;
//    private String username;
    
    List<Employee> employeeList;
    
    public EmployeeMB() {
        user = new Users();
        address = new Address();
        employee = new Employee();
//        employeeRole = new EmployeeRole();
        
        
        employee.setUser(user);
        employee.setAddress(address);
//        user.setEmployeeRole(employeeRole);
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String create() {
//        user.setPassword(username);
//        user.addRole(employeeRole);
        ejb.save(employee);

        return "employee-list";
    }

    
    
    public String delete(int employeeId) {
        employee = ejb.find(employeeId);
        ejb.delete(employee);
        
        return "employee-list";
    }

    public String gotoUpdatePage(int id) {
        employee =  ejb.find(id);
//        System.out.println("ID>>>>>>>>>>>>>>>>>>" + employee.getId());
        return "employee-update";
    }
    
    
    
//   
    
    public String update(){
//        System.out.println("ID::::::::::::" + employee.getId());
        ejb.edit(employee);
        
        return "employee-list";
    }

    
      public List<Employee> getEmployeeList() {
        employeeList = ejb.findAll();
        return employeeList;
    }

          
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void handleUsernameValidation(){
      
        if(ejb.findByUName(employee.getUser().getUsername())!=null)
        {
            setUsername("this name already exists");
        }
        else{
        
          setUsername("");
        }
    }
}

