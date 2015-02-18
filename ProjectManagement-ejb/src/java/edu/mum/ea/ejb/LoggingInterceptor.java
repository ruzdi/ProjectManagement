/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.ejb;

import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import jdk.nashorn.internal.objects.NativeRegExp;

/**
 *
 * @author SyedAliAhmed
 */
public class LoggingInterceptor {
    
    private Logger logger = Logger.getLogger("edu.mum.ea.ejb");
    
    @AroundInvoke
    private Object logMethod(InvocationContext ic) throws Exception {
        logger.entering(ic.getTarget().toString(), ic.getMethod().getName());
        try {
            System.out.println("Intercepting ------>"+ic.getMethod().getName());
            return ic.proceed();
        } finally {
            logger.exiting(ic.getTarget().toString(), ic.getMethod().getName());
        }
    }
}
