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
import javax.persistence.TypedQuery;
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
        ReleaseBacklog releaseBckLg = em.find(ReleaseBacklog.class, releaseBckLgId);
        em.remove(releaseBckLg);        
    }
    
    public List<ReleaseBacklog> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ReleaseBacklog.class));
        return (List<ReleaseBacklog>)em.createQuery(cq).getResultList();
    }
    
    public List<ReleaseBacklog> findAllRelBakByProject(long projectId) {
        TypedQuery<ReleaseBacklog> query = em.createQuery("select r from ReleaseBacklog r WHERE r.project.id = :projectId", ReleaseBacklog.class);
        query.setParameter("projectId", projectId);
        return query.getResultList();
    }
    
    
}
