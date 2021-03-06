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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author MdRuzdi
 */
@Entity
public class ReleaseBacklog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String releaseName;
    
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    @ManyToOne(optional = true)
    private Project project;
    
    @OneToMany(cascade = CascadeType.ALL)
    //@JoinTable(name="ReleaseBacklog_ProductBacklog", joinColumns=@JoinColumn(name="PRD_BAK_ID"), inverseJoinColumns=@JoinColumn(name="REL_BAK_ID"))
    private List<ProductBacklog> productBacklog = new ArrayList<ProductBacklog>();
//    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "releaseBacklog")
    private List<Sprint> sprint = new ArrayList<Sprint>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReleaseName() {
        return releaseName;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<ProductBacklog> getProductBacklog() {
        return productBacklog;
    }

    public void setProductBacklog(List<ProductBacklog> productBacklog) {
        this.productBacklog = productBacklog;
    }

    public List<Sprint> getSprint() {
        return sprint;
    }

    public void setSprint(List<Sprint> sprint) {
        this.sprint = sprint;
    }
    
    

//    public ProductBacklog getProductBacklog() {
//        return productBacklog;
//    }
//
//    public void setProductBacklog(ProductBacklog productBacklog) {
//        this.productBacklog = productBacklog;
//    }
//
//    public Sprint getSprint() {
//        return sprint;
//    }
//
//    public void setSprint(Sprint sprint) {
//        this.sprint = sprint;
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
        if (!(object instanceof ReleaseBacklog)) {
            return false;
        }
        ReleaseBacklog other = (ReleaseBacklog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.mum.ea.entity.ProductBacklog[ id=" + id + " ]";
    }
    
}
