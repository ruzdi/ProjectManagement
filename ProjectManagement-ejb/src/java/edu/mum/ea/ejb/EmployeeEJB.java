/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.ejb;

import edu.mum.ea.entity.Address;
import edu.mum.ea.entity.Employee;
import edu.mum.ea.entity.Project;
import edu.mum.ea.entity.Username;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author SyedAliAhmed
 */
@Stateless
@LocalBean
public class EmployeeEJB {

    @PersistenceContext
    private EntityManager em;
    
    Address addres = new Address();

    public void save(Employee employee) {
        em.persist(employee);
    }

    public void edit(Employee employee) {
        em.merge(employee);
    }
    
    public void delete(Employee employee){
//        em.merge(employee);
        em.remove(em.merge(employee));
    }
    
    public Username findByUName(String username){
//        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//        cq.select(cq.from(Username.class)).where();
        TypedQuery<Username> query = null;
        Username usernameOb = null;
        System.out.println("Username>>>>>>>>>>>>>>>>>>>>>>>" + username);
        try {
            query = em.createQuery("Select u from Username u where u.username=:userName", Username.class);
            query.setParameter("userName", username);
            usernameOb = query.getSingleResult();
        } catch (Exception e) {
        
        }
        
        
        return usernameOb;
    }

    public Employee find(int id) {
        return em.find(Employee.class, id);
    }

    public List<Employee> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Employee.class));
        return (List<Employee>) em.createQuery(cq).getResultList();
    }
}
