/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.ejb;

import edu.mum.ea.entity.Project;
import edu.mum.ea.entity.ReleaseBacklog;
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
public class ReleaseBacklogEJB {

    @PersistenceContext
    private EntityManager em;
    
    public void save(ReleaseBacklog releaseBckLg) {
        em.persist(releaseBckLg);
    }
    
    public void edit(ReleaseBacklog releaseBckLg) {
        em.merge(releaseBckLg);
    }
    
    public ReleaseBacklog find(Long id) {
        return em.find(ReleaseBacklog.class, id);
    }
    
    public void delete(Long releaseBckLgId){
        ReleaseBacklog releaseBckLg = find(releaseBckLgId);
        
        em.remove(em.merge(releaseBckLg));        
    }
    
    public List<ReleaseBacklog> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ReleaseBacklog.class));
        return (List<ReleaseBacklog>)em.createQuery(cq).getResultList();
    }
    
    
}