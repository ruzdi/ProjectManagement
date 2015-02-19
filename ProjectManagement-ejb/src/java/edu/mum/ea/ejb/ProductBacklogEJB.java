/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.ejb;

import edu.mum.ea.entity.ProductBacklog;
import edu.mum.ea.entity.Project;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

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

    public List<ProductBacklog> getProductBacklogListByProjectId(Long projectId) {
        
        /*CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ProductBacklog> criteriaQuery = builder.createQuery(ProductBacklog.class);

        Root productBacklog = criteriaQuery.from(ProductBacklog.class);
        //Join<ProductBacklog, Project> project = productBacklog.join(ProductBacklog_.project);
        
        
        //Join<Author,Address> add = author.join(Author_.address);
        //Join<Address, States> State = add.join(Address_.state);

        //Predicate predicate = builder.gt(author.get(Author_.rating), rank);
        //criteriaQuery.where(builder.ge(State.get(States_.population), population));
        //System.out.println("Population : "+population);
        
        //criteriaQuery.where(builder.equal(Project.get(Project_.id), id));
        
        criteriaQuery.select(productBacklog);
        TypedQuery<ProductBacklog> query = em.createQuery(criteriaQuery);
        return query.getResultList();*/
        
        
        TypedQuery<ProductBacklog> query = em.createQuery("select pb from ProductBacklog pb WHERE pb.project.id = :projectId", ProductBacklog.class);
        query.setParameter("projectId", projectId);
        return query.getResultList();
        
    }
    
    
    public List<ProductBacklog> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ProductBacklog.class));
        return (List<ProductBacklog>) em.createQuery(cq).getResultList();
    }
}
