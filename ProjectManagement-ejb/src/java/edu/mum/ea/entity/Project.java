/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.entity;

import edu.mum.ea.entity.ProductBacklog;
import edu.mum.ea.entity.Sprint;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author SyedAliAhmed
 */
@Entity
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String detail;
    
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
//    private List<Sprint> sprints;
//    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
//    private ProductBacklog productBacklog;
//    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
//    private Resource resource;
    
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

//    public List<Sprint> getSprints() {
//        return sprints;
//    }
//
//    public void setSprint(Sprint sprint) {
//        if(!this.sprints.contains(sprint)){
//            this.sprints.add(sprint);
//        }
//    }
//
//    public ProductBacklog getProductBacklog() {
//        return productBacklog;
//    }
//
//    public void setProductBacklog(ProductBacklog productBacklog) {
//        this.productBacklog = productBacklog;
//    }
//
//    public Resource getResource() {
//        return resource;
//    }
//
//    public void setResource(Resource resource) {
//        this.resource = resource;
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
