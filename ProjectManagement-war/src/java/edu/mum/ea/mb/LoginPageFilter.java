/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.mb;

import java.io.IOException;
import javax.servlet.Filter;
//import java.util.logging.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SyedAliAhmed
 */
public class LoginPageFilter implements Filter{
    public void init(FilterConfig filterConfig) throws ServletException
   {

   }

   public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,   FilterChain filterChain) throws IOException, ServletException
   {
       HttpServletRequest request = (HttpServletRequest) servletRequest;
       HttpServletResponse response = (HttpServletResponse) servletResponse;

       if(request.getUserPrincipal() != null){ //If user is already authenticated
           response.sendRedirect("/index.jsf");// or, forward using RequestDispatcher
       } else{
           filterChain.doFilter(servletRequest, servletResponse);
       }
   }

   public void destroy()
   {

   }
    
}
