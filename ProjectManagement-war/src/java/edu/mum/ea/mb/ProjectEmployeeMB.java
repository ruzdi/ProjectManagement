/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.mb;

import edu.mum.ea.ejb.EmployeeEJB;
import edu.mum.ea.ejb.ProjectEJB;
import edu.mum.ea.entity.Employee;
import edu.mum.ea.entity.Project;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
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

    public void setSelectedEmp(List<String> selectedEmp) {
        this.selectedEmp = selectedEmp;
    }
    
    public void addEmployeeToProject() {
        Project prj = projectEJB.find(getProjectId());
        List<Employee> eList = prj.getEmployeeList();

        for (Employee e : eList) {
            eList.remove(e);
        }
        
        for (String emplpyeeId : selectedEmp) {
           prj.getEmployeeList().add(employeeEJB.find(Integer.parseInt(emplpyeeId)));
        }
        
        projectEJB.edit(prj);
            
    }
    
    
}
