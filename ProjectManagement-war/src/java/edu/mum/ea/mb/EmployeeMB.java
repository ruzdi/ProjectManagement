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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;
import org.jboss.weld.servlet.SessionHolder;

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
//    @
//    LoginContext lc;
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
//        try {
//            lc = new LoginContext("asd");
//            lc.l
////        user.setEmployeeRole(employeeRole);
//        } catch (LoginException ex) {
//            Logger.getLogger(EmployeeMB.class.getName()).log(Level.SEVERE, null, ex);
//        }
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

        return "employee-list?faces-redirect=true";
    }

    public String delete(int employeeId) {
        employee = ejb.find(employeeId);
        ejb.delete(employee);

        return "employee-list?faces-redirect=true";
    }

    public String gotoUpdatePage(int id) {
        employee = ejb.find(id);
        return "employee-update?faces-redirect=true";
    }

    public String update() {
        ejb.edit(employee);

        return "employee-list?faces-redirect=true";
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

    public void handleUsernameValidation() {

        if (ejb.findByUName(employee.getUser().getUsername()) != null) {
            setUsername("This name already exists");
        } else {

            setUsername("");
        }
    }
}
