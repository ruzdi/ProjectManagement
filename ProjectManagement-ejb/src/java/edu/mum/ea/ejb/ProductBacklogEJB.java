/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.ejb;

import edu.mum.ea.entity.ProductBacklog;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MasbhaUddin
 */
@Stateless
@LocalBean
public class ProductBacklogEJB {
    @PersistenceContext
    private EntityManager em;
    
    
    public void edit(ProductBacklog productBacklog) {
        em.merge(productBacklog);
    }
    
    public ProductBacklog find(Long id) {
        return em.find(ProductBacklog.class, id);
    }
    
    public void delete(ProductBacklog productBacklog){
        //ProductBacklog productBacklog = find(productBacklogId);
        //System.out.println("============>>>>" + productBacklog.getProject().getName());
        em.remove(em.merge(productBacklog));        
    }
    
}
