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
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author MasbhaUddin
 */
@ManagedBean
@RequestScoped
public class ProjectBacklogMB {
    
    private ProductBacklog productBacklog;
    private Project project;
    private List<ProductBacklog> pbList;
    
    @EJB
    private ProjectEJB projectEJB;
    @EJB
    private ProductBacklogEJB productBacklogEJB;
    
    @ManagedProperty(value = "#{sessionMB}")
    private SessionMB sessionMB;
  
    public ProjectBacklogMB() {
        productBacklog = new ProductBacklog();
    }

    public ProductBacklog getProductBacklog() {
        return productBacklog;
    }

    public void setProductBacklog(ProductBacklog productBacklog) {
        this.productBacklog = productBacklog;
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

    public List<ProductBacklog> getPbList() {
        Map<String, Object> sessionMap =  FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        pbList = projectEJB.findProductBacklogList(Long.parseLong(sessionMap.get("pid").toString()));
        return pbList;
    }

    public void setPbList(List<ProductBacklog> pbList) {
        this.pbList = pbList;
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
        this.sessionMB.setUserSelectedProject(project);
        System.out.println("User Selected Project :1:: "+this.getSessionMB().getUserSelectedProject());
        System.out.println("User Selected Project :2:: "+project);
        return "/product-backlog/product-backlog-list";
    }
    
  
    public String updatePrductBacklog(){
        Map<String, Object> sessionMap =  FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        project = projectEJB.find(Long.parseLong(sessionMap.get("pid").toString())); 
        productBacklog.setProject(project);
        productBacklog.setReleaseBacklogStatus(Boolean.FALSE);
        productBacklog.setPriority(2);
        productBacklogEJB.edit(productBacklog);
        return "/product-backlog/product-backlog-list";
    }
     
    public String gotoUpdateBacklogPage(Long id){
        productBacklog = productBacklogEJB.find(id);
        return "/product-backlog/product-backlog-update";
    }
    
     public String deletePrductBacklog(Long prductBacklogId){
        productBacklog =  productBacklogEJB.find(prductBacklogId);
        project = productBacklog.getProject();
        productBacklogEJB.delete(productBacklog);
        return "/product-backlog/product-backlog-list";
    }   
    
}
