/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.ejb;


import edu.mum.ea.entity.Address;
import edu.mum.ea.entity.Employee;
import edu.mum.ea.entity.Project;
import edu.mum.ea.entity.Users;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;
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
@Interceptors(LoggingInterceptor.class)
public class EmployeeEJB {

    @PersistenceContext
    private EntityManager em;

//    private Logger logger = Logger.getLogger("edu.mum.ea.ejb");
    Address addres = new Address();

    public void save(Employee employee) {
        em.persist(employee);
    }

    public void edit(Employee employee) {
        em.merge(employee);
    }

    public void delete(Employee employee) {
        em.remove(em.merge(employee));
    }

    public Users findByUName(String username) {
        TypedQuery<Users> query = null;
        Users usernameOb = null;

        try {
            query = em.createQuery("Select u from Users u where u.username=:userName", Users.class);
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

//    @AroundInvoke
//    private Object logMethod(InvocationContext ic) throws Exception {
//        logger.entering(ic.getTarget().toString(), ic.getMethod().getName());
//        try {
//            return ic.proceed();
//        } finally {
//            logger.exiting(ic.getTarget().toString(), ic.getMethod().getName());
//        }
//    }
}
