/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.ejb;

import edu.mum.ea.entity.TaskCategory;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author MdRuzdi
 */
@Stateless
@LocalBean
public class TaskCategoryEJB {

    @PersistenceContext
    EntityManager em;

    public void create(TaskCategory taskCategory) {
        em.persist(taskCategory);
    }

    public void update(int id) {
        TaskCategory taskCategory = find(id);
        em.merge(taskCategory);
    }
    
    public void delete(int id) {
        TaskCategory taskCategory = find(id);
        em.remove(taskCategory);
    }
    
    public TaskCategory find(int id) {
        return em.find(TaskCategory.class, id);
    }
    
    public List<TaskCategory> findAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<TaskCategory> criteriaQuery = builder.createQuery(TaskCategory.class);
        
        Root book = criteriaQuery.from(TaskCategory.class);
        criteriaQuery.select(book);
        TypedQuery<TaskCategory> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
   
    
    
    /*
    
    public List<Book> findBookByPopulation(BigInteger population) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = builder.createQuery(Book.class);

        Root book = criteriaQuery.from(Book.class);
        Join<Book, Author> author = book.join(Book_.author);
        Join<Author,Address> add = author.join(Author_.address);
        Join<Address, States> State = add.join(Address_.state);

        //Predicate predicate = builder.gt(author.get(Author_.rating), rank);
        criteriaQuery.where(builder.ge(State.get(States_.population), population));
        System.out.println("Population : "+population);
        criteriaQuery.select(book);
        TypedQuery<Book> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
    
    public List<Publisher> findPublisherByAuthorRankJPQL(Float rank) {
        TypedQuery<Publisher> query = em.createQuery("select p from Publisher p JOIN p.books b where b.author.rank >= :rank", Publisher.class);
        query.setParameter("rank", rank);
        return query.getResultList();

    }
    
    public List<Publisher> findPublisherByAuthorRank(Float rank) {
        
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Publisher> criteriaQuery = builder.createQuery(Publisher.class);

        Root publisher = criteriaQuery.from(Publisher.class);
        Join<Publisher, Book> book = publisher.join(Publisher_.books);
        Join<Book, Author> author = book.join(Book_.author);
        
        //Predicate predicate = builder.gt(author.get(Author_.rating), rank);
        criteriaQuery.where(builder.ge(author.get(Author_.rank), rank));
        System.out.println("Rank : "+rank);
        criteriaQuery.select(publisher);
        TypedQuery<Publisher> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
     
    public List<Book> findBookByAuthorRankAndPopulationJPQL(Float rank, BigInteger population) {
        TypedQuery<Book> query = em.createQuery("select b from Book b WHERE b.author.address.state.population >= :population AND b.author.rank >= :rank", Book.class);
        query.setParameter("population", population);
        query.setParameter("rank", rank);
        System.out.println(query.toString());
        return query.getResultList();
    }
    
    //CriteriaApi
    public List<Book> findBookByAuthorRankAndPopulation(Float rank, BigInteger population) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = builder.createQuery(Book.class);

        Root book = criteriaQuery.from(Book.class);
        Join<Book, Author> author = book.join(Book_.author);
        Join<Author,Address> add = author.join(Author_.address);
        Join<Address, States> State = add.join(Address_.state);

        //Predicate predicate = builder.gt(author.get(Author_.rating), rank);
        criteriaQuery.where(builder.ge(author.get(Author_.rank), rank),
                        builder.ge(State.get(States_.population), population));
        
        System.out.println("Rank : "+rank);
        System.out.println("Population : "+population);
        criteriaQuery.select(book);
        TypedQuery<Book> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
    
    public void cleanup() {

//        em.createNativeQuery("DELETE FROM Book").executeUpdate();
//        em.createNativeQuery("DELETE FROM Publisher").executeUpdate();
//        em.createNativeQuery("DELETE FROM Recycling").executeUpdate();
//        em.createNativeQuery("DELETE FROM Company").executeUpdate();
//        em.createNativeQuery("DELETE FROM Author").executeUpdate();
//        em.createNativeQuery("DELETE FROM Address").executeUpdate();
//        em.createNativeQuery("DELETE FROM Authorstate").executeUpdate();
//        
        em.createNativeQuery("truncate table book").executeUpdate();
        em.createNativeQuery("truncate table author").executeUpdate();
        em.createNativeQuery("truncate table address CASCADE").executeUpdate();
        em.createNativeQuery("truncate table address").executeUpdate();
        em.createNativeQuery("truncate table states").executeUpdate();
        em.createNativeQuery("truncate table publisher").executeUpdate();
        em.createNativeQuery("truncate table recycling").executeUpdate();
        em.createNativeQuery("truncate table company").executeUpdate();
        
    }
    
    public void cleanDatabase(){
        delete(Book.class);
        delete(Author.class);        
        delete(Publisher.class);
        delete(Recycling.class);
        delete(Company.class);
        delete(Address.class);
        delete(States.class);
        
    }

    public <T> void delete(Class<T> t) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();       
        CriteriaDelete<T> delete = cb.createCriteriaDelete(t);        
        delete.from(t);
        this.em.createQuery(delete).executeUpdate();
    }

    public List<Author> findAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = builder.createQuery(Author.class);

        Root book = criteriaQuery.from(Author.class);
        criteriaQuery.select(book);
        TypedQuery<Author> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
    
    
    public List<Book> findAllBook() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = builder.createQuery(Book.class);

        Root book = criteriaQuery.from(Book.class);
        criteriaQuery.select(book);
        TypedQuery<Book> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
    */
    
}
