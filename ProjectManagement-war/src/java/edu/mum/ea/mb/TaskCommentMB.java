/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.mb;

import edu.mum.ea.ejb.TaskCommentEJB;
import edu.mum.ea.ejb.TaskEJB;
import edu.mum.ea.entity.TaskComment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author MdRuzdi
 */
@ManagedBean(name="taskCommentMB")
@RequestScoped
public class TaskCommentMB {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
        
    @EJB
    private TaskCommentEJB taskCommentEJB;
    
    private TaskComment taskComment;
    private int currentTaskId;
    
    public TaskCommentMB() {
        taskComment = new TaskComment();
    }
    
    @PostConstruct
    private void init() {
        
    }

    public TaskComment getTaskComment() {
        return taskComment;
    }

    public void setTaskComment(TaskComment taskComment) {
        this.taskComment = taskComment;
    }
    
    public int getCurrentTaskId() {
        return currentTaskId;
    }

    public void setCurrentTaskId(int currentTaskId) {
        this.currentTaskId = currentTaskId;
    }
    
    public String create(long taskId){
        taskId = 2;
        System.out.println("========= Current Task Id :: "+taskId);
        taskCommentEJB.create(taskId, taskComment);
        return "success";
        //return "/task/task-view";
    }
    
    
    public String update(){
    
        return "";
    }
    
    
    public String delete(){
    
        return "";
    }
    
    public String find(){
    
        return "";
    }
    
    
    public String findAll(){
    
        return "";
    }
    
    
}
