/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.ejb;

import edu.mum.ea.entity.Project;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author MasbhaUddin
 */
@Stateless
@LocalBean
public class ProjectEJB {

    @PersistenceContext
    private EntityManager em;
    
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
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Project.class));
        return (List<Project>)em.createQuery(cq).getResultList();
    }
    
    
}
