/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.ejb;

import edu.mum.ea.entity.Task;
import edu.mum.ea.entity.TaskComment;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author MdRuzdi
 */
@Stateless
@LocalBean
public class TaskCommentEJB {

    @PersistenceContext
    EntityManager em;
    
    public void create(Long taskId, TaskComment taskComment) {
        Task task = em.find(Task.class, taskId);
        System.out.println("=============Task :: "+task);
        taskComment.setTask(task);
        em.persist(taskComment);
    }
    
    public void update(TaskComment taskComment) {
        em.merge(taskComment);
    }
    
    public void delete(Long id) {
        TaskComment taskComment = find(id);
        em.remove(taskComment);
    }
    
    public TaskComment find(Long id) {
        return em.find(TaskComment.class, id);
    }
    
    public List<TaskComment> findAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<TaskComment> criteriaQuery = builder.createQuery(TaskComment.class);
        Root taskComment = criteriaQuery.from(TaskComment.class);
        criteriaQuery.select(taskComment);
        TypedQuery<TaskComment> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
   
}