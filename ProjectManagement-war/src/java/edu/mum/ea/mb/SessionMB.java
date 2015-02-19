/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.mb;

import edu.mum.ea.ejb.ProjectEJB;
import edu.mum.ea.entity.ProductBacklog;
import edu.mum.ea.entity.Project;
import edu.mum.ea.entity.ReleaseBacklog;
import edu.mum.ea.entity.Sprint;
import edu.mum.ea.entity.Task;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author MdRuzdi
 */
@ManagedBean(name="sessionMB")
@SessionScoped
public class SessionMB {
    
    private List<Project> userProjectList;
    private Long userSelectedProjectId;
    private Project userSelectedProject;
    private ProductBacklog userSelectedProjectBacklog;
    private Long userSelectedProjectBacklogId;
    private ReleaseBacklog userSelectedReleaseBacklog;
    private Sprint userSelectedSprint;
    private Task userSelectedTask;
    
    @EJB
    private ProjectEJB projectEJB;

    /**
     * Creates a new instance of SessionMB
     */
    public SessionMB() {
        userProjectList = new ArrayList<>();
        
    }
    
    @PostConstruct
    public void init(){
        this.userProjectList =  projectEJB.findAll();
    }

    public List<Project> getUserProjectList() {
        return userProjectList;
    }

    public void setUserProjectList(List<Project> userProjectList) {
        this.userProjectList = userProjectList;
    }
    
    public void populateUserProjectList() {
        this.userProjectList = projectEJB.findAll();
    }

    public Project getUserSelectedProject() { 
        return userSelectedProject;
    }

    public void setUserSelectedProject(Project userSelectedProject) {
        this.userSelectedProject = userSelectedProject;
        this.setUserSelectedProjectId(this.userSelectedProject.getId());
        System.out.println("======setUserSelectedProject  :: "+this.userSelectedProject);
    }
    
    public void setUserSelectedProjectOnChange(ValueChangeEvent e) {
        System.out.println("======setUserSelectedProjec3333333333333tOnChange :: "+e);
        this.userSelectedProjectId = (Long)e.getNewValue();
        this.userSelectedProject = projectEJB.find(userSelectedProjectId);
        System.out.println("======setUserSelectedProjectOnChange :: "+this.userSelectedProjectId+ "   ::::   "+this.userSelectedProject);
    }

    public Long getUserSelectedProjectId() {
        return userSelectedProjectId;
    }

    public void setUserSelectedProjectId(Long userSelectedProjectId) {
        this.userSelectedProjectId = userSelectedProjectId;
    }
    
    public ProductBacklog getUserSelectedProjectBacklog() {
        return userSelectedProjectBacklog;
    }

    public void setUserSelectedProjectBacklog(ProductBacklog userSelectedProjectBacklog) {
        this.userSelectedProjectBacklog = userSelectedProjectBacklog;
    }

    public ReleaseBacklog getUserSelectedReleaseBacklog() {
        return userSelectedReleaseBacklog;
    }

    public void setUserSelectedReleaseBacklog(ReleaseBacklog userSelectedReleaseBacklog) {
        this.userSelectedReleaseBacklog = userSelectedReleaseBacklog;
    }

    public Long getUserSelectedProjectBacklogId() {
        return userSelectedProjectBacklogId;
    }

    public void setUserSelectedProjectBacklogId(Long userSelectedProjectBacklogId) {
        this.userSelectedProjectBacklogId = userSelectedProjectBacklogId;
    }
    
    public Sprint getUserSelectedSprint() {
        return userSelectedSprint;
    }

    public void setUserSelectedSprint(Sprint userSelectedSprint) {
        this.userSelectedSprint = userSelectedSprint;
    }

    public Task getUserSelectedTask() {
        return userSelectedTask;
    }

    public void setUserSelectedTask(Task userSelectedTask) {
        this.userSelectedTask = userSelectedTask;
    }
        
    
}