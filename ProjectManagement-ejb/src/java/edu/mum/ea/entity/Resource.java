/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.entity;

import edu.mum.ea.entity.Employee;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author SyedAliAhmed
 */
@Entity
public class Resource implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String actAs;
    private double costPerHour;
    
//    @ManyToMany
//    private List<Project> projects;
//    
//    @OneToOne
//    private Employee employee;
//    
    
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true,  mappedBy = "resource")
//    private List<Task> tasks;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getActAs() {
        return actAs;
    }

    public void setActAs(String actAs) {
        this.actAs = actAs;
    }

    public double getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(double costPerHour) {
        this.costPerHour = costPerHour;
    }

//    public List<Project> getProjects() {
//        return projects;
//    }
//
//    public void setProject(Project project) {
//        if(!this.projects.contains(project)){
//            this.projects.add(project);
//        }
//    }
//
//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }

//    public List<Task> getTasks() {
//        return tasks;
//    }
//
//    public void setTasks(List<Task> tasks) {
//        this.tasks = tasks;
//    }
//    
//    public void addTasks(Task task) {
//        if(!this.tasks.contains(task)){
//            this.tasks.add(task);
//        }
//    }
//    
//    public void removeTasks(Task task) {
//        if(this.tasks.contains(task)){
//            this.tasks.remove(task);
//        }
//    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resource)) {
            return false;
        }
        Resource other = (Resource) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "java.edu.mum.ea.entity.Resource[ id=" + id + " ]";
    }
    
}
