/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.mb;


import edu.mum.ea.ejb.ProjectEJB;
import edu.mum.ea.entity.Employee;
import edu.mum.ea.entity.Project;
import java.util.List;
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
    
    @ManagedProperty(value="#{projectEmployeeMB}")
    private ProjectEmployeeMB projectEmployeeMB;
    
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

    public ProjectEmployeeMB getProjectEmployeeMB() {
        return projectEmployeeMB;
    }

    public void setProjectEmployeeMB(ProjectEmployeeMB projectEmployeeMB) {
        this.projectEmployeeMB = projectEmployeeMB;
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
        return "/project-backlog/project-list?faces-redirect=true";
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
    
    
    public String createProject() {

        try {
            projectEJB.save(project);     
            this.getSessionMB().setUserSelectedProject(project);   
            sessionMB.populateUserProjectList();
        } catch (Exception e) {
            //gotoLogin();
            return gotoLogin();
        }
       
       return "project-list?faces-redirect=true";
    }
    
    public String gotoUpdatePage(Long id){
        project = projectEJB.find(id);           
        return "/project/project-update";
    }
    
    public String updateProject(){
        
        projectEJB.edit(project);
        this.getSessionMB().setUserSelectedProject(project);
        sessionMB.populateUserProjectList();
        return "/project/project-list";
    }
    
    public String deleteProject(Long projectId){
        project = projectEJB.find(projectId);      
        if(project.equals(this.getSessionMB().getUserSelectedProject())){
            this.getSessionMB().setUserSelectedProject(null);   
        }
        projectEJB.delete(projectId);
        sessionMB.populateUserProjectList();
        return "/project/project-list?faces-redirect=true"; 
    }
   
    public String projectItem() {
       return "success";
    }
        
    public String getProjectDetail(long projectId) {
        project = projectEJB.find(projectId);
        for (Employee e : project.getEmployeeList()) {
            projectEmployeeMB.getSelectedEmp().add(e.getId() + "");            
        }
        return "project-detail";
    }
    
    public String gotoLogin(){
        return "/login?faces-redirect=true";
    }

}
