/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.mb;


import edu.mum.ea.ejb.ProjectEJB;
import edu.mum.ea.ejb.ReleaseBacklogEJB;
import edu.mum.ea.ejb.SprintEJB;
import edu.mum.ea.entity.Project;
import edu.mum.ea.entity.ReleaseBacklog;
import edu.mum.ea.entity.Sprint;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;



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
    /**
     * Creates a new instance of ProjectMB
     */
    public ReleaseBacklogMB() {
       releaseBckLg = new ReleaseBacklog();
       releaseBckLgList = new ArrayList<ReleaseBacklog>();
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
    
    public String createReleaseBacklog() {
            releaseBckLgEJB.save(releaseBckLg);     
       return "release-backlog-list";
    }
    
    public String gotoUpdatePage(Long id){
        releaseBckLg = releaseBckLgEJB.find(id);
        return "release-backlog-update";
    }
    
    public String updateReleaseBacklog(){
        
        releaseBckLgEJB.edit(releaseBckLg);
        return "release-backlog-list";
    }
    
    public String deleteReleaseBacklog(Long releaseBckLgId){
       releaseBckLgEJB.delete(releaseBckLgId);
        return "release-backlog-list";
    }
      
}
