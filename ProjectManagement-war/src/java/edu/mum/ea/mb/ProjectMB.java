/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.mb;


import edu.mum.ea.ejb.ProjectEJB;
import edu.mum.ea.entity.Project;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author MasbhaUddin
 */
@ManagedBean
@RequestScoped
public class ProjectMB {

    private Project project;
    
    @EJB
    private ProjectEJB projectEJB;
    
    private List<Project> projectList;
    /**
     * Creates a new instance of ProjectMB
     */
    public ProjectMB() {
        project = new Project();
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Project> getProjectList() {
        projectList = projectEJB.findAll();
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
    
    public String createProject() {
       projectEJB.save(project);
       return "project-list";
    }
    
    public String gotoUpdatePage(Long id){
        
        project = projectEJB.find(id);
           
        return "project-update";
    }
    
    public String updateProject(){
        
        projectEJB.edit(project);
    
        return "project-list";
    }
    
    public String deleteProject(Long projectId){
        projectEJB.delete(projectId);
  
        return "project-list";
    }
    
    public String projectItem() {
       return "success";
    }
}
