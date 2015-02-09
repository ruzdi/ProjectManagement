/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.mb;

import edu.mum.ea.ejb.ProductBacklogEJB;
import edu.mum.ea.ejb.ProjectEJB;
import edu.mum.ea.entity.ProductBacklog;
import edu.mum.ea.entity.Project;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author MasbhaUddin
 */
@ManagedBean
@RequestScoped
public class ProjectBacklogMB {
    
    //@ManagedProperty(value = "#{projectMB}")  
    //private ProjectMB projectMB;
    private ProductBacklog productBacklog;
    private Long projectId;
    private Project project;
    
    @EJB
    private ProjectEJB projectEJB;
    @EJB
    private ProductBacklogEJB productBacklogEJB;
    //private ProjectBac
    /**
     * Creates a new instance of ProjectBacklogMB
     */
    public ProjectBacklogMB() {
        productBacklog = new ProductBacklog();
    }

    public ProductBacklog getProductBacklog() {
        return productBacklog;
    }

    public void setProductBacklog(ProductBacklog productBacklog) {
        this.productBacklog = productBacklog;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
       
    public String create() {
        Map<String, Object> sessionMap =  FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        project = projectEJB.find(Long.parseLong(sessionMap.get("pid").toString()));
        productBacklog.setReleaseBacklogStatus(Boolean.FALSE);
        productBacklog.setPriority(2);
        project.addBacklog(productBacklog);    
        projectEJB.edit(project);
        return "/product-backlog/product-backlog-list";
    }
    
    public String getBacklogList(Long projectId) {
        Map<String, Object> sessionMap =  FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("pid", projectId);
        project = projectEJB.find(projectId);
        return "/product-backlog/product-backlog-list";
    }
    
     public String updatePrductBacklog(){ 
        productBacklogEJB.edit(productBacklog);
        return "/product-backlog/product-backlog-list";
    }
     
    public String gotoUpdateBacklogPage(Long id){
        productBacklog = productBacklogEJB.find(id);
        return "/product-backlog/product-backlog-update";
    }
    
     public String deletePrductBacklog(Long prductBacklogId){
        productBacklogEJB.delete(prductBacklogId);
        return "/product-backlog/product-backlog-list";
    }
    
    
}
