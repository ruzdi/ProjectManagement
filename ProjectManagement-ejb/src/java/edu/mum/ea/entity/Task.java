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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 * @author MdRuzdi
 */
@Entity
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String detail;
    @Column(nullable=true)
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(nullable=true)
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Max(value = 3, message = "Maximum can be 3")
    private Integer duration;
    @Max(value = 3, message = "Maximum can be 3")
    private Integer priority;
    @Max(value = 3, message = "Maximum can be 3")
    private Integer status;
//    @ManyToOne
//    private Sprint sprint;
    @JoinColumn(nullable = true)
    @ManyToOne(optional = true)
    private TaskCategory taskCategory;
//    @ManyToOne
//    private ProductBacklog productBacklog;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "task")
    private List<TaskComment> taskComments;
//    @OneToOne
//    private Resource resource;
    @ManyToOne
    @JoinTable(name="SPRINT_TASK", joinColumns=@JoinColumn(name="TASK_ID"), inverseJoinColumns=@JoinColumn(name="SPRINT_ID"))
    private Sprint sprint;
    public Task() {
        taskComments = new ArrayList<TaskComment>();
    }
    
    public Task(String title, TaskCategory taskCategory) {
        this.title = title;
        this.taskCategory = taskCategory;
        this.taskCategory.addTask(this);
    }
    
    
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public TaskCategory getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(TaskCategory taskCategory) {
        this.taskCategory = taskCategory;
    }
//
//    public ProductBacklog getProductBacklog() {
//        return productBacklog;
//    }
//
//    public void setProductBacklog(ProductBacklog productBacklog) {
//        this.productBacklog = productBacklog;
//    }
//

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
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.mum.ea.entity.Task[ id=" + id + " ]";
    }
    
}
