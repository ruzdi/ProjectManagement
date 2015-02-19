/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.mb;

import edu.mum.ea.ejb.EmployeeEJB;
import edu.mum.ea.ejb.ProductBacklogEJB;
import edu.mum.ea.ejb.ProjectEJB;
import edu.mum.ea.ejb.TaskCategoryEJB;
import edu.mum.ea.ejb.TaskCommentEJB;
import edu.mum.ea.ejb.TaskEJB;
import edu.mum.ea.entity.Employee;
import edu.mum.ea.entity.ProductBacklog;
import edu.mum.ea.entity.Resource;
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
    
    @EJB
    private ProductBacklogEJB productBacklogEJB;
    
    @EJB
    private EmployeeEJB employeeEJB;

    
    private Task task;
    private List<Task> taskList;
    private List<TaskCategory> taskCategoryList;
    private int taskCategoryId;
    
    private Long productBacklogId;
    private List<ProductBacklog> productBacklogList;
//    private List<Resource> resourceList;
    private int employeeId;
    private List<Employee> projectEmployeeList;

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
        productBacklogList = productBacklogEJB.getProductBacklogListByProjectId(sessionMB.getUserSelectedProject().getId());
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

    public Long getProductBacklogId() {
        return productBacklogId;
    }

    public void setProductBacklogId(Long productBacklogId) {
        this.productBacklogId = productBacklogId;
    }
    
    public List<ProductBacklog> getProductBacklogList() {
        productBacklogList = productBacklogEJB.getProductBacklogListByProjectId(sessionMB.getUserSelectedProject().getId());
        return productBacklogList;
    }
    
    public void setProductBacklogList(List<ProductBacklog> productBacklogList) {
        this.productBacklogList = productBacklogList;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    
    public List<Employee> getProjectEmployeeList() {
        projectEmployeeList = sessionMB.getUserSelectedProject().getEmployeeList();
        return projectEmployeeList;
    }

    public void setProjectEmployeeList(List<Employee> projectEmployeeList) {
        this.projectEmployeeList = projectEmployeeList;
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
        System.out.println("");
        this.task.setEmployee(employeeEJB.find(employeeId));
        this.task.setProductBacklog(productBacklogEJB.find(productBacklogId));
        this.task.setTaskCategory(taskCategoryEJB.find(new Long(this.taskCategoryId)));
        taskEJB.create(task);
        this.sendTaskEmail("Task Created", "A new task is <b>created</b>.");
        return "/task/task-list?faces-redirect=true";
    }

    public String view(int id) {
        this.task = taskEJB.find(new Long(id));
        this.getSessionMB().setUserSelectedTask(task);
        return "/task/task-view?faces-redirect=true";
    }
    
    public String edit(int id) {
        this.task = taskEJB.find(new Long(id));
        this.setTaskCategoryId(Integer.parseInt(this.task.getTaskCategory().getId().toString()));
        if(this.task.getProductBacklog() != null){
            this.setProductBacklogId(this.task.getProductBacklog().getId());
        }
        if(task.getEmployee() != null){
            this.setEmployeeId(this.task.getEmployee().getId());
        }
        return "/task/task-update";
    }

    public String update() {
        this.task.setEmployee(employeeEJB.find(this.employeeId));
        this.task.setProductBacklog(productBacklogEJB.find(this.productBacklogId));
        this.task.setTaskCategory(taskCategoryEJB.find(new Long(this.taskCategoryId)));
        taskEJB.update(this.task);
        this.sendTaskEmail("Task Updated", "A task information is <b>updated</b>.");
        return "/task/task-list";
    }

    public String delete(int id) {
        this.task = taskEJB.find(new Long(id));
        this.sendTaskEmail("Task Deleted", "A task has been <b>deleted</b>.");
        taskEJB.delete(new Long(id));
        return "/task/task-list?faces-redirect=true";
    }

    public String find(int id) {
        this.task = taskEJB.find(new Long(id));
        return "/task/task-list?faces-redirect=true";
    }

    public String findAll() {
        taskList = taskEJB.findAll();
        return "/task/task-list?faces-redirect=true";
    }

    public String createComment() {
        Task myTask = taskEJB.find(task.getId());
        System.out.println("===================== Task " + task + "   ============  New Task " + myTask + "  ==============  Comment " + this.comment);

        TaskComment myTaskComment = new TaskComment();
        myTaskComment.setComment(this.comment);
        myTaskComment.setTask(myTask);
        taskEJB.createComment(myTaskComment);

        this.task = myTask;
        return "/task/task-view?faces-redirect=true";
    }

    public void sendTaskEmail(String actionString, String actionMessage) {
        try {
            this.emailMB.setEmailTo(task.getEmployee().getEmail());
            this.emailMB.setEmailSubject("PM Team :: " + actionString + " :: " + task.getTitle());
            this.emailMB.setEmailBody(
                    "Dear "+task.getEmployee().getName()+", <br /><br />"
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

}