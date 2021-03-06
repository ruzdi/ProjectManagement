/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;

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
    @Pattern(regexp="[A-Za-z0-9]+")
    private String designation; 
    private String email;
    private String gender;
    @Pattern(regexp="\\(\\d{3}\\)\\d{3}-\\d{4}")
    private String phoneNumber;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Users user;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    
    @ManyToMany(mappedBy = "employeeList")
    private List<Project> projectList = new ArrayList<Project>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true,  mappedBy = "employee")
    private List<Task> tasks;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true,  mappedBy = "employee")
    private List<TaskComment> taskComments;

    public void employee(){
//        address = new Address();
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
    
    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
       
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
     public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    public void addTasks(Task task) {
        if(!this.tasks.contains(task)){
            this.tasks.add(task);
        }
    }
    
    public void removeTasks(Task task) {
        if(this.tasks.contains(task)){
            this.tasks.remove(task);
        }
    }

    public List<TaskComment> getTaskComments() {
        return taskComments;
    }

    public void setTaskComments(List<TaskComment> taskComments) {
        this.taskComments = taskComments;
    }
    
    public void addTaskComment(TaskComment taskComment) {
        if(!this.taskComments.contains(taskComment)){
            this.taskComments.add(taskComment);
        }
        
    }
    
    public void removeTaskComment(TaskComment taskComment) {
        if(this.taskComments.contains(taskComment)){
            this.taskComments.add(taskComment);
        }
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

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
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
