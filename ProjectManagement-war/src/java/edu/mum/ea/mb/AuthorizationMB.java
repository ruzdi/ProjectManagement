/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SyedAliAhmed
 */
@ManagedBean
@RequestScoped
public class AuthorizationMB {

    /**
     * Creates a new instance of AuthorizationMB
     */
    public AuthorizationMB() {

    }

    public String logout() {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        if (session != null) {
            session.invalidate();
        }
//        System.out.println("logout");

        return "index";
    }

    public boolean isLoggedIn() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.isUserInRole("developer") || request.isUserInRole("admin");
    }

    public boolean isLoggedOut() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return !request.isUserInRole("developer") && !request.isUserInRole("admin");
    }
    public boolean isLoginAsAdmin() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.isUserInRole("admin");
    }

    public boolean isLoginAsDeveloper() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.isUserInRole("developer");
    }

    public String login() {
//        System.out.println("test");
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        if (session != null) {
            session.invalidate();
        }
        return "login";
    }

}
