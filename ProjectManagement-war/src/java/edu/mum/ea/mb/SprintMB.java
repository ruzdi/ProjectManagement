/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.mb;


import edu.mum.ea.ejb.ProjectEJB;
import edu.mum.ea.ejb.SprintEJB;
import edu.mum.ea.entity.Project;
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

public class SprintMB {

    private Sprint sprint;
    
    @EJB
    private SprintEJB sprintEJB;
    
    private List<Sprint> sprintList;
    /**
     * Creates a new instance of ProjectMB
     */
    public SprintMB() {
        sprint = new Sprint();
        sprintList = new ArrayList<Sprint>();
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public List<Sprint> getSprintList() {
        sprintList = sprintEJB.findAll();
        return sprintList;
    }

    public void setSprintList(List<Sprint> sprintList) {
        this.sprintList = sprintList;
    }
    
    public String createSprint() {
            sprintEJB.save(sprint);     
       return "sprint-list";
    }
    
    public String gotoUpdatePage(Long id){
        sprint = sprintEJB.find(id);
        return "sprint-update";
    }
    
    public String updateSprint(){
        
        sprintEJB.edit(sprint);
        return "sprint-list";
    }
    
    public String deleteProject(Long sprintId){
        sprintEJB.delete(sprintId);
        return "sprint-list";
    }
      
}
