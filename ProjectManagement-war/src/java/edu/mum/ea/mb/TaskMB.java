/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.mb;

import edu.mum.ea.ejb.TaskEJB;
import edu.mum.ea.entity.Task;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author MdRuzdi
 */
@ManagedBean(name="taskMB")
@RequestScoped
public class TaskMB {

    /**
     * Creates a new instance of TaskMB
     */
    
    @EJB
    private TaskEJB taskEJB;
    
    private Task task;
    
    public TaskMB() {
        task = new Task();
    }
    
    @PostConstruct
    private void init() {
        
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
    
    public String create(){
        
        System.out.print("this is befor calling EJB for task create");
        task.setId(new Long("5"));
        taskEJB.create(task);
        System.out.print("this is after calling EJB for task create");
        return "index";
    }
    
    
    public String update(){
    
        return "";
    }
    
    
    public String delete(){
        return "";
    }
    
    public String find(){
        return "";
    }
    
    public String findAll(){
    
        return "";
    }
    
    
}



/*
public class AuthorMB {

    @EJB
    private AuthorEJB ejb;

    @ManagedProperty(value = "#{publisherMB}")
    private PublisherMB publisherMB;

    @ManagedProperty(value = "#{bookMB}")
    private BookMB bookMB;

    
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
    
    

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public States getState() {
        return state;
    }

    public void setState(States state) {
        this.state = state;
    }

    public BookMB getBookMB() {
        return bookMB;
    }

    public void setBookMB(BookMB bookMB) {
        this.bookMB = bookMB;
    }

    public PublisherMB getPublisherMB() {
        return publisherMB;
    }

    public void setPublisherMB(PublisherMB publisherMB) {
        this.publisherMB = publisherMB;
    }
    
    public void cleanup() {
        try {
            ejb.cleanDatabase();
            //this.setMessageText("Cleaning Database Successfull");
        } catch (Exception ex) {
            //this.setMessageText(ex.getMessage());
        }
    }
    
    
    public void findAll(){
        ejb.findAll();
    }

    public void fillTables() {
        createRow("IOWA", "986578", "Fairfield", "52557", "Ruzdi Islam", "3.5", "Magic Trics", 898, "568726815", "Las Vegus", "Nevada", "98654785", "8787", "Aprex", "4.5", "JinHow Lit.");
        createRow("Des Man", "123654", "Kirteai", "147789", "John Ala", "4.5", "Algorithm in Java", 474, "57548512", "California", "LA", "698547", "5454", "Head First", "3.5", "Head First Lit.");
        createRow("Texas", "2342", "Waco", "5475", "Shafinur Alam", "4.8", "Cancer the killer", 471, "3214789", "Jackson", "New York", "2343223", "4512", "Packet Pub", "5", "Packet Publisher Lit.");

    }

    public void createRow(String authorStateName, String authorStatePopulation, String authorCity, String authorZip, String authorName, String authorRank, String bookTitle, int bookPage, String bookIsbn, String companyCity, String companyStateName, String companyStatepopulation, String companyZip, String publisherName, String companyRating, String publisherCompany) {

        States authorState = new States(authorStateName, new BigInteger(authorStatePopulation));
        Address authorAddress = new Address(authorCity, authorZip, authorState);
        Author author = new Author(authorName, new Float(authorRank), authorAddress);

        States companyState = new States(companyStateName, new BigInteger(companyStatepopulation));
        Address companyAddress = new Address(companyCity, companyZip, companyState);
        Publisher publisher = new Publisher(publisherName, new Float(companyRating), publisherCompany, companyAddress);
        //System.out.println("Book ID : ========================================== "+ book.getId());

        Book book = new Book(bookTitle, bookPage, bookIsbn, author, publisher);
        ejb.create(author);

        //publisherMB.create(publisher);
    }

    public String createAuthorRelation() {

        States authorState = new States(this.state.getName(), this.state.getPopulation());
        Address authorAddress = new Address(this.address.getCity(), this.address.getZip(), authorState);
        Author author = new Author(this.author.getName(), this.author.getRank(), authorAddress);
        Book book = new Book(this.book.getTitle(), this.book.getPage(), this.book.getIsbn(), author);

        ejb.create(author);

        return "index";

    }

}

*/