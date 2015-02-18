/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.mb;

import edu.mum.ea.ejb.TaskCategoryEJB;
import edu.mum.ea.ejb.TaskCommentEJB;
import edu.mum.ea.ejb.TaskEJB;
import edu.mum.ea.entity.Task;
import edu.mum.ea.entity.TaskCategory;
import edu.mum.ea.entity.TaskComment;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author MdRuzdi
 */
@ManagedBean(name = "taskMB")
@RequestScoped
public class TaskMB {

    /**
     * Creates a new instance of TaskMB
     */
    @EJB
    private TaskEJB taskEJB;

    @EJB
    private TaskCategoryEJB taskCategoryEJB;

    @EJB
    private TaskCommentEJB taskCommentEJB;

    private Task task;
    private List<Task> taskList;
    private List<TaskCategory> taskCategoryList;
    private int taskCategoryId;

    private TaskComment taskComment;

    private String comment;

    @ManagedProperty(value = "#{taskCategoryMB}")
    private TaskCategoryMB taskCategoryMB;

    @ManagedProperty(value = "#{taskCcommentMB}")
    private TaskCommentMB taskCommentMB;

    @ManagedProperty(value = "#{sessionMB}")
    private SessionMB sessionMB;

    @ManagedProperty(value = "#{emailMB}")
    private EmailMB emailMB;

    public TaskMB() {
        task = new Task();
    }

    @PostConstruct
    private void init() {

    }

    public TaskCategoryMB getTaskCategoryMB() {
        return taskCategoryMB;
    }

    public void setTaskCategoryMB(TaskCategoryMB taskCategoryMB) {
        this.taskCategoryMB = taskCategoryMB;
    }

    public TaskCommentMB getTaskCommentMB() {
        return taskCommentMB;
    }

    public void setTaskCommentMB(TaskCommentMB taskCommentMB) {
        this.taskCommentMB = taskCommentMB;
    }

    public SessionMB getSessionMB() {
        return sessionMB;
    }

    public void setSessionMB(SessionMB sessionMB) {
        this.sessionMB = sessionMB;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<Task> getTaskList() {
        taskList = taskEJB.findAll();
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<TaskCategory> getTaskCategoryList() {
        taskCategoryList = taskCategoryEJB.findAll();
        //System.out.println("==========Task Categories "+taskCategoryList.toString());
        return taskCategoryList;
    }

    public void setTaskCategoryList(List<TaskCategory> taskCategoryList) {
        this.taskCategoryList = taskCategoryList;
    }

    public TaskComment getTaskComment() {
        return taskComment;
    }

    public void setTaskComment(TaskComment TaskComment) {
        this.taskComment = taskComment;
    }

    public int getTaskCategoryId() {
        return taskCategoryId;
    }

    public void setTaskCategoryId(int taskCategoryId) {
        this.taskCategoryId = taskCategoryId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public EmailMB getEmailMB() {
        return emailMB;
    }

    public void setEmailMB(EmailMB emailMB) {
        this.emailMB = emailMB;
    }

    public String create() {
        this.task.setTaskCategory(taskCategoryEJB.find(new Long(this.taskCategoryId)));
        taskEJB.create(task);
        this.sendTaskEmail("Task Created", "A new task is <b>created</b>.");
        return "/task/task-list";
    }

    public String view(int id) {
        this.task = taskEJB.find(new Long(id));
        this.getSessionMB().setUserSelectedTask(task);
        return "/task/task-view";
    }

    public String edit(int id) {
        this.task = taskEJB.find(new Long(id));
        this.setTaskCategoryId(Integer.parseInt(this.task.getTaskCategory().getId() + ""));
        return "/task/task-update";
    }

    public String update() {
        this.task.setTaskCategory(taskCategoryEJB.find(new Long(this.taskCategoryId)));
        taskEJB.update(this.task);
        this.sendTaskEmail("Task Updated", "A task information is <b>updated</b>.");
        return "/task/task-list";
    }

    public String delete(int id) {
        this.task = taskEJB.find(new Long(id));
        this.sendTaskEmail("Task Deleted", "A task has been <b>deleted</b>.");
        taskEJB.delete(new Long(id));
        return "/task/task-list";
    }

    public String find(int id) {
        this.task = taskEJB.find(new Long(id));
        return "/task/task-list";
    }

    public String findAll() {
        taskList = taskEJB.findAll();
        return "/task/task-list";
    }

    public String createComment() {
        Task myTask = taskEJB.find(task.getId());
        System.out.println("===================== Task " + task + "   ============  New Task " + myTask + "  ==============  Comment " + this.comment);

        TaskComment myTaskComment = new TaskComment();
        myTaskComment.setComment(this.comment);
        myTaskComment.setTask(myTask);
        taskEJB.createComment(myTaskComment);

        this.task = myTask;
        return "/task/task-view";
    }

    public void sendTaskEmail(String actionString, String actionMessage) {
        try {
            this.emailMB.setEmailTo("ruzdibd@gmail.com");
            this.emailMB.setEmailSubject("PM Team :: " + actionString + " :: " + task.getTitle());
            this.emailMB.setEmailBody(
                    "Dear AAA, <br /><br />"
                    + actionString + "<br /><br />"
                    + "Please take a look at this task detail : <br /><br />"
                    + "<b>Title</b> :  " + task.getTitle() + "<br /><br />"
                    + "<b>Category</b> :  " + task.getTaskCategory().getName() + "<br /><br />"
                    + "<b>Start Date</b> :  " + task.getStartDate() + "<br /><br />"
                    + "<b>End Date</b> :  " + task.getEndDate() + "<br /><br />"
                    + "<b>Duration</b> :  " + task.getDuration() + "<br /><br />"
                    + "<b>Priority</b> :  " + task.getPriority() + "<br /><br />"
                    + "<b>Status</b> :  " + task.getStatus() + "<br /><br />"
                    + "<b>Detail</b> :  " + task.getDetail() + "<br /><br />"
                    + "Thanks<br /><br/>"
                    + "Admin of Project Management Team"
            );
            this.emailMB.sendEmail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public String createComment(long id, String comment){
//        Task myTask = taskEJB.find(id);        
//        System.out.println("===================== Task "+ myTask+ "  ==============  Comment "+comment);
//        taskComment.setComment(comment);
//        taskComment.setTask(myTask);
//        taskEJB.createComment(taskComment);
//        //this.taskCommentMB.createComment(myTask, comment);
//        //this.task.setTaskCategory(taskCategoryEJB.find(new Long(this.taskCategoryId)));
//        //taskEJB.create(task);
//        this.task = myTask;
//        return "/task/task-view";
//    }  
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
