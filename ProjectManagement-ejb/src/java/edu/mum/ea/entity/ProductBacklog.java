/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.entity;

import edu.mum.ea.entity.Project;
import java.io.Serializable;
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

/**
 *
 * @author MdRuzdi
 */
@Entity
public class ProductBacklog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String details;
    private Boolean releaseBacklogStatus;
    private Integer priority;
//    
    @ManyToOne
    private Project project;
//    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productBacklog")
//    private List<Task> tasks;
//    
//    
//    @ManyToOne
//    @JoinTable(name="ReleaseBacklog_ProductBacklog", joinColumns=@JoinColumn(name="REL_BAK_ID"), inverseJoinColumns=@JoinColumn(name="PRD_BAK_ID"))
//    private ReleaseBacklog releaseBacklog;
//    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean getReleaseBacklogStatus() {
        return releaseBacklogStatus;
    }

    public void setReleaseBacklogStatus(Boolean releaseBacklogStatus) {
        this.releaseBacklogStatus = releaseBacklogStatus;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
//
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

//    public ReleaseBacklog getReleaseBacklog() {
//        return releaseBacklog;
//    }
//
//    public void setReleaseBacklog(ReleaseBacklog releaseBacklog) {
//        this.releaseBacklog = releaseBacklog;
//    }
    
    
//
//    public List<Task> getTasks() {
//        return tasks;
//    }
//
//    public void setTask(Task task) {
//        if(!this.tasks.contains(task)){
//            this.tasks.add(task);
//        }
//    }
//    
//    public ReleaseBacklog getReleaseBacklog() {
//        return releaseBacklog;
//    }
//
//    public void setReleaseBacklog(ReleaseBacklog releaseBacklog) {
//        this.releaseBacklog = releaseBacklog;
//    }
//    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductBacklog)) {
            return false;
        }
        ProductBacklog other = (ProductBacklog) object;
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
