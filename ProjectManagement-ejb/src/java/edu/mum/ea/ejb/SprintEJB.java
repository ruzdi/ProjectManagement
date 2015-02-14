/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.ejb;

import edu.mum.ea.entity.Project;
import edu.mum.ea.entity.Sprint;
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
 * @author Syed
 */
@Stateless
@LocalBean
public class SprintEJB {

    @PersistenceContext
    private EntityManager em;
    
    public void save(Sprint sprint) {
        em.persist(sprint);
    }
    
    public void edit(Sprint sprint) {
        em.merge(sprint);
    }
    
    public Sprint find(Long id) {
        return em.find(Sprint.class, id);
    }
    
    public void delete(Long sprintId){
        Sprint sprint = find(sprintId);
        
        em.remove(em.merge(sprint));        
    }
    
    public List<Sprint> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Sprint.class));
        return (List<Sprint>)em.createQuery(cq).getResultList();
    }
    
    
}
