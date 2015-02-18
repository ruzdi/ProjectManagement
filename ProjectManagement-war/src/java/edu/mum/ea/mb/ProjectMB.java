/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.mb;


import edu.mum.ea.ejb.ProjectEJB;
import edu.mum.ea.entity.Project;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


/**
 *
 * @author MasbhaUddin
 */
@ManagedBean
@RequestScoped
//@RolesAllowed("admin")
public class ProjectMB {

    private Project project;
    
    @EJB
    private ProjectEJB projectEJB;
    
    @ManagedProperty(value="#{sessionMB}")
    private SessionMB sessionMB;
    
    private List<Project> projectList;
    /**
     * Creates a new instance of ProjectMB
     */
    public ProjectMB() {
        project = new Project();
    }

    public SessionMB getSessionMB() {
        return sessionMB;
    }

    public void setSessionMB(SessionMB sessionMB) { 
        this.sessionMB = sessionMB;
    }
    
    

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Project> getProjectList() {
        try {
             projectList = projectEJB.findAll();
        } catch (Exception e) {
            gotoLogin();
        }
       
        return projectList;
    }
    
     public String showProjectList() {
        return "/project-backlog/project-list";
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
    
    
    public String createProject() {

        try {
            projectEJB.save(project);     
            sessionMB.populateUserProjectList();
        } catch (Exception e) {
            gotoLogin();
            return null;
        }
       
       return "project-list";
    }
    
    public String gotoUpdatePage(Long id){
        project = projectEJB.find(id);           
        return "/project/project-update";
    }
    
    public String updateProject(){
        
        projectEJB.edit(project);
        sessionMB.populateUserProjectList();
        return "/project/project-list";
    }
    
    public String deleteProject(Long projectId){
        projectEJB.delete(projectId);
        sessionMB.populateUserProjectList();
        return "/project/project-list"; 
    }
    
   
    public String projectItem() {
       return "success";
    }
    
    
    public String gotoLogin(){
//        System.out.println("Go to Login");
        return "/login";
    }

}
