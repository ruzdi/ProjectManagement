/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.ejb;

import edu.mum.ea.entity.ProductBacklog;
import edu.mum.ea.entity.Project;
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.http.HttpServletRequest;

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
    @PermitAll
    public void edit(Project project) {
        em.merge(project);
    }
    @PermitAll
    public Project find(Long id) {
        return em.find(Project.class, id);
    }

    public void delete(Long projectId) {
        Project project = find(projectId);

        em.remove(em.merge(project));
    }
    @PermitAll
    public List<Project> findAll() {
//        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        System.out.println("" + ctx.isCallerInRole("admin"));
//        System.out.println("" + ctx.isCallerInRole("developer"));
//        if (request.isUserInRole("developer")) {
//            System.out.println("adminbb" );
//            return null;
//        } else {
//            System.out.println("developer");
//        }

        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Project.class));
        return (List<Project>) em.createQuery(cq).getResultList();
    }

    public List<ProductBacklog> findProductBacklogList(long projectId) {
        TypedQuery<ProductBacklog> query = em.createQuery("SELECT pb FROM ProductBacklog pb WHERE pb.project.id = :projectId", ProductBacklog.class);
        query.setParameter("projectId", projectId);
        return query.getResultList();

    }

}
