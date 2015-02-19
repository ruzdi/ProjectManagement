/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.mb;


import edu.mum.ea.ejb.ProjectEJB;
import edu.mum.ea.ejb.ReleaseBacklogEJB;
import edu.mum.ea.ejb.SprintEJB;
import edu.mum.ea.ejb.TaskEJB;
import edu.mum.ea.entity.Project;
import edu.mum.ea.entity.ReleaseBacklog;
import edu.mum.ea.entity.Sprint;
import edu.mum.ea.entity.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;



/**
 *
 * @author Syed
 */
@ManagedBean
@RequestScoped

public class SprintMB {

    private Sprint sprint;
    
    @EJB
    private SprintEJB sprintEJB;
    
    @EJB
    private ProjectEJB projectEJB;
    
    @EJB
    private ReleaseBacklogEJB releaseBacklogEJB;
    
    @EJB
    private TaskEJB taskEJB;
    
    @ManagedProperty(value = "#{sessionMB}")
    private SessionMB sessionMB;
    
    private List<Task> taskList = new ArrayList<Task>();
    
    private List<ReleaseBacklog> releaseBacklogList = new ArrayList<ReleaseBacklog>();
    private long relBacklogId;
    private List<String> selectedTasks = new ArrayList<String>();
    
    private List<Sprint> sprintList;
    /**
     * Creates a new instance of ProjectMB
     */
    public SprintMB() {
        sprint = new Sprint();
        sprintList = new ArrayList<Sprint>();
    }
    @PostConstruct
    public void init() {
        //Map<String, Object> sessionMap =  FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        //Project project = projectEJB.find(sessionMB.getUserSelectedProject().getId());
        releaseBacklogList = releaseBacklogEJB.findAllRelBakByProject(sessionMB.getUserSelectedProject().getId());//project.getReleaseBacklogList();   
    }

    public SessionMB getSessionMB() {
        return sessionMB;
    }

    public void setSessionMB(SessionMB sessionMB) {
        this.sessionMB = sessionMB;
    }
    
    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public List<Sprint> getSprintList() {
        sprintList = sprintEJB.findAllSprintByProject(sessionMB.getUserSelectedProject().getId());//sprintEJB.findAll();
        return sprintList;
    }

    public void setSprintList(List<Sprint> sprintList) {
        this.sprintList = sprintList;
    }

    public List<ReleaseBacklog> getReleaseBacklogList() {
        return releaseBacklogList;
    }

    public void setReleaseBacklogList(List<ReleaseBacklog> releaseBacklogList) {
        this.releaseBacklogList = releaseBacklogList;
    }

    public long getRelBacklogId() {
        return relBacklogId;
    }

    public void setRelBacklogId(long relBacklogId) {
        this.relBacklogId = relBacklogId;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<String> getSelectedTasks() {
        return selectedTasks;
    }

    public void setSelectedTasks(List<String> selectedTasks) {
        this.selectedTasks = selectedTasks;
    }
    
    
    public String createSprint() {  
        sprint.setReleaseBacklog(releaseBacklogEJB.find(getRelBacklogId()));
        sprintEJB.save(sprint);     
        return "sprint-list";
    }
    
    public String gotoUpdatePage(Long id){
        sprint = sprintEJB.find(id);
        try {
            setRelBacklogId(sprint.getReleaseBacklog().getId());
        } catch(Exception e) {
            //System.out.println("-----" +  e.getMessage());
        }
        return "sprint-update";
    }
    
    public String updateSprint(){
        try {
            sprint.setReleaseBacklog(releaseBacklogEJB.find(getRelBacklogId()));
        } catch (Exception e) {
            //System.out.println("-----" + e.getMessage());
        }
        sprintEJB.edit(sprint);
        return "sprint-list";
    }
    
    public String deleteSprint(Long sprintId){
        sprintEJB.delete(sprintId);
        return "sprint-list";
    }
    
    public String sprintDetail(Long id) {
        sprint = sprintEJB.find(id);
        taskList = taskEJB.findAll();
        for (Task t : sprint.getTasks()) {
            selectedTasks.add(t.getId().toString());
        }
        return "sprint-view";
    }
    
    public String addTaskToSprint() {
        long sprintId = sprint.getId();
        
        sprint = sprintEJB.find(sprintId);
        taskList = sprint.getTasks();
        int size  = taskList.size();
        for (int i = 0; i < size; i++) {
            taskList.remove(taskList.get(i));
            size--;
            --i;
        }
        
        for (String taskId : selectedTasks) {
            if (!taskList.contains(taskEJB.find(Long.parseLong(taskId)))) {
                sprint.getTasks().add(taskEJB.find(Long.parseLong(taskId)));
            }
        }
        sprintEJB.edit(sprint);
        return "/sprint/sprint-list";
    }
    
      
}
