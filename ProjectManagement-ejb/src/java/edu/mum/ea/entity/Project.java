/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

/**
 *
 * @author SyedAliAhmed
 */
@Entity
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp="[A-Za-z0-9 _]+")
    private String name;
    private String detail;
    
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<ProductBacklog> productBacklog;

    @ManyToMany
    @JoinTable(name = "project_employee", joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employeeList = new ArrayList<Employee>();
    //Fetch Type eager will be changed it is for temporary work
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true,  mappedBy = "project", fetch = FetchType.EAGER)
    private List<ReleaseBacklog> releaseBacklogList;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
     public List<ProductBacklog> getProductBacklog(){
        return productBacklog;
    }

    public void setProductBacklog(List<ProductBacklog> productBacklog) {    
        this.productBacklog = productBacklog;
    }
    
    public List<ReleaseBacklog> getReleaseBacklogList() {
        return releaseBacklogList;
    }

    public void setReleaseBacklogList(List<ReleaseBacklog> releaseBacklogList) {
        this.releaseBacklogList = releaseBacklogList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
    

    public void addBacklog(ProductBacklog productBacklog) {
        if(!this.productBacklog.contains(productBacklog)){
            this.productBacklog.add(productBacklog);
            productBacklog.setProject(this);
        }
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "java.edu.mum.ea.entity.Project[ id=" + id + " ]";
    }
    
}
