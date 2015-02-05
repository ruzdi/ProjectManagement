/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.mb;

import edu.mum.ea.ejb.EmployeeEJB;
import edu.mum.ea.entity.Address;
import edu.mum.ea.entity.Employee;
import edu.mum.ea.entity.Username;
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
    Username user;
    
    List<Employee> employeeList;
    
//    @PostConstruct
//    public void init(){
//        address = new Address();
//    }
    
    public EmployeeMB() {
        user = new Username();
        address = new Address();
        employee = new Employee();
        employee.setUser(user);
        employee.setAddress(address);
//        
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String create() {
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
    
    public String update(){
//        System.out.println("ID::::::::::::" + employee.getId());
        ejb.edit(employee);
        
        return "employee-list";
    }

    
      public List<Employee> getEmployeeList() {
        employeeList = ejb.findAll();
        return employeeList;
    }

}

