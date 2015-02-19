/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.mb;

import edu.mum.ea.ejb.EmployeeEJB;
import edu.mum.ea.ejb.ProjectEJB;
import edu.mum.ea.ejb.TaskEJB;
import edu.mum.ea.entity.Employee;
import edu.mum.ea.entity.Project;
import edu.mum.ea.entity.Task;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author MasbhaUddin
 */
@ManagedBean
@RequestScoped
public class ProjectEmployeeMB {

    private long projectId;
    private List<Project> projectList;
    private List<Employee> employeeList;
    private List<String> selectedEmp = new ArrayList<String>();
    
    @EJB
    private ProjectEJB projectEJB;
    @EJB
    private EmployeeEJB employeeEJB;
    
    @EJB
    private TaskEJB taskEJB;
    
    @ManagedProperty(value = "#{sessionMB}")
    private SessionMB sessionMB;
    
    private List<Task> tasks = new ArrayList<Task>();
    /**
     * Creates a new instance of ProjectEmployeeMB
     */
    public ProjectEmployeeMB() {
       
    }
    
    @PostConstruct
    public void init() {
        projectList = projectEJB.findAll();
        employeeList = employeeEJB.findAll();
        
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    

    public List<String> getSelectedEmp() {
        return selectedEmp;
    }

    public List<Task> getTasks() {
        tasks = taskEJB.findAll();
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    
    public void setSelectedEmp(List<String> selectedEmp) {
        this.selectedEmp = selectedEmp;
    }

    public SessionMB getSessionMB() {
        return sessionMB;
    }

    public void setSessionMB(SessionMB sessionMB) {
        this.sessionMB = sessionMB;
    }
    
    public void addEmployeeToProject() {
        Project prj = projectEJB.find(getProjectId());
        List<Employee> eList = prj.getEmployeeList();
        int size  = eList.size();
        for (int i = 0; i < size; i++) {
            eList.remove(eList.get(i));
            size--;
            --i;
        }
//        for (Employee e : eList) {
//            eList.remove(e);
//        }
        
        for (String emplpyeeId : selectedEmp) {
           if (!eList.contains(employeeEJB.find(Integer.parseInt(emplpyeeId)))) {
                prj.getEmployeeList().add(employeeEJB.find(Integer.parseInt(emplpyeeId)));
           }
        }
        
        projectEJB.edit(prj);
            
    }
    
    public void addEmployeeToProject1() {
        Project prj = projectEJB.find(sessionMB.getUserSelectedProject().getId());
        List<Employee> eList = prj.getEmployeeList();
        int size  = eList.size();
        for (int i = 0; i < size; i++) {
            eList.remove(eList.get(i));
            size--;
            --i;
        }

        for (String emplpyeeId : selectedEmp) {
           if (!eList.contains(employeeEJB.find(Integer.parseInt(emplpyeeId)))) {
                prj.getEmployeeList().add(employeeEJB.find(Integer.parseInt(emplpyeeId)));
           }
        }
        
        projectEJB.edit(prj);
            
    }
    
    
}
