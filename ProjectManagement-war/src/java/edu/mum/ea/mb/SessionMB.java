/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.mb;

import edu.mum.ea.entity.ProductBacklog;
import edu.mum.ea.entity.Project;
import edu.mum.ea.entity.ReleaseBacklog;
import edu.mum.ea.entity.Sprint;
import edu.mum.ea.entity.Task;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author MdRuzdi
 */
@ManagedBean(name="sessionMB")
@SessionScoped
public class SessionMB {
    
    private Project userSelectedProject;
    private ProductBacklog userSelectedProjectBacklog;
    private ReleaseBacklog userSelectedReleaseBacklog;
    private Sprint userSelectedSprint;
    private Task userSelectedTask;

    /**
     * Creates a new instance of SessionMB
     */
    public SessionMB() {
        
    }

    public Project getUserSelectedProject() {
        return userSelectedProject;
    }

    public void setUserSelectedProject(Project userSelectedProject) {
        this.userSelectedProject = userSelectedProject;
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