/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.mb;


import edu.mum.ea.ejb.ProductBacklogEJB;
import edu.mum.ea.ejb.ProjectEJB;
import edu.mum.ea.ejb.ReleaseBacklogEJB;
import edu.mum.ea.ejb.SprintEJB;
import edu.mum.ea.entity.ProductBacklog;
import edu.mum.ea.entity.Project;
import edu.mum.ea.entity.ReleaseBacklog;
import edu.mum.ea.entity.Sprint;
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

public class ReleaseBacklogMB {

    private ReleaseBacklog releaseBckLg;
    
   
    
    @EJB
    private ReleaseBacklogEJB releaseBckLgEJB;
    
    private List<ReleaseBacklog> releaseBckLgList;
    
    @EJB
    private ProjectEJB projectEJB;
    
    @EJB
    private ProductBacklogEJB productBacklogEJB;
    
    @ManagedProperty(value = "#{project}")
    private List<Project> projectList;
    private Long projectId;
    private List<ProductBacklog> productBacklogList = new ArrayList<ProductBacklog>();
    private List<String> selectedPrdBacklog = new ArrayList<String>();
    /**
     * Creates a new instance of ProjectMB
     */
    public ReleaseBacklogMB() {
       releaseBckLg = new ReleaseBacklog();
       releaseBckLgList = new ArrayList<ReleaseBacklog>();
    }
    
    @PostConstruct
    public void init() {
        Map<String, Object> sessionMap =  FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        productBacklogList = projectEJB.findProductBacklogList(Long.parseLong(sessionMap.get("pid").toString()));
    }

    public ReleaseBacklog getReleaseBckLg() {
        return releaseBckLg;
    }

    public void setReleaseBckLg(ReleaseBacklog releaseBckLg) {
        this.releaseBckLg = releaseBckLg;
    }
    
    public ReleaseBacklog getReleaseBacklog() {
        return releaseBckLg;
    }

    public void setReleaseBacklog(ReleaseBacklog releaseBckLg) {
        this.releaseBckLg = releaseBckLg;
    }

    public List<ReleaseBacklog> getReleaseBacklogList() {
        releaseBckLgList = releaseBckLgEJB.findAll();
        return releaseBckLgList;
    }

    public void setReleaseBacklogList(List<ReleaseBacklog> releaseBckLgList) {
        this.releaseBckLgList = releaseBckLgList;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public List<ReleaseBacklog> getReleaseBckLgList() {
        return releaseBckLgList;
    }

    public void setReleaseBckLgList(List<ReleaseBacklog> releaseBckLgList) {
        this.releaseBckLgList = releaseBckLgList;
    }

    public List<Project> getProjectList() {
        projectList = projectEJB.findAll();
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public List<ProductBacklog> getProductBacklogList() {
        return productBacklogList;
    }

    public void setProductBacklogList(List<ProductBacklog> productBacklogList) {
        this.productBacklogList = productBacklogList;
    }

    public List<String> getSelectedPrdBacklog() {
        return selectedPrdBacklog;
    }

    public void setSelectedPrdBacklog(List<String> selectedPrdBacklog) {
        this.selectedPrdBacklog = selectedPrdBacklog;
    }
            
    public String createReleaseBacklog() {
        this.releaseBckLg.setProject(projectEJB.find(this.projectId));
        releaseBckLgEJB.save(releaseBckLg);     
        return "release-backlog-list";
    }
    
    public String gotoUpdatePage(Long id){
        releaseBckLg = releaseBckLgEJB.find(id);
        try {
            setProjectId(releaseBckLg.getProject().getId());
        } catch(Exception e) {
        }
        
        return "release-backlog-update";
    }
    
    public String updateReleaseBacklog(){
        try {
            Project project = projectEJB.find(getProjectId());
            releaseBckLg.setProject(project);
        } catch (Exception e) {
            
        }
        releaseBckLgEJB.edit(releaseBckLg);
        return "release-backlog-list";
    }
    
    public String deleteReleaseBacklog(Long releaseBckLgId){
       releaseBckLgEJB.delete(releaseBckLgId);
       return "release-backlog-list";
    }
    
    public String viewReleaseBacklog(Long releaseBckLgId) {
        releaseBckLg = releaseBckLgEJB.find(releaseBckLgId);
        
        for (ProductBacklog pb : releaseBckLg.getProductBacklog()) {
            selectedPrdBacklog.add(pb.getId().toString());
        }
        return "release-product-backlog";
    }
    
    public void addProductBacklog() {
        
        releaseBckLg = releaseBckLgEJB.find(releaseBckLg.getId());
        List<ProductBacklog> productBacklogList = releaseBckLg.getProductBacklog();
        
        int size  = productBacklogList.size();
        for (int i = 0; i < size; i++) {
            productBacklogList.remove(productBacklogList.get(i));
            size--;
            --i;
        }
        
        for (String prdBacklogId : selectedPrdBacklog) {
            if (!productBacklogList.contains(productBacklogEJB.find(Long.parseLong(prdBacklogId)))) {
                releaseBckLg.getProductBacklog().add(productBacklogEJB.find(Long.parseLong(prdBacklogId)));
            }
        }
        releaseBckLgEJB.edit(releaseBckLg);
        
    }
      
}
