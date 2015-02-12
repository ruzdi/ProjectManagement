/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.ejb;

import edu.mum.ea.entity.Project;
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author MasbhaUddin
 */
@Stateless
@LocalBean
@RolesAllowed({"admin"})
public class ProjectEJB {

    @PersistenceContext
    private EntityManager em;
    
    @Resource
    private SessionContext ctx;
    
     public void save(Project project) {
        em.persist(project);
    }
    
    public void edit(Project project) {
        em.merge(project);
    }
    
    public Project find(Long id) {
        return em.find(Project.class, id);
    }
    
    public void delete(Long projectId){
        Project project = find(projectId);
        
        em.remove(em.merge(project));        
    }
    
    public List<Project> findAll() {
        System.out.println("" +ctx.isCallerInRole("admin"));
        System.out.println("" +ctx.isCallerInRole("developer"));
//        ctx.isCallerInRole("admin");
//        ctx.isCallerInRole("developer");
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Project.class));
        return (List<Project>)em.createQuery(cq).getResultList();
    }
    
    
}
