/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.ea.mb;

import edu.mum.ea.entity.EnumClass;
import edu.mum.ea.entity.EnumClass.GenderEnum;
import edu.mum.ea.entity.EnumClass.PriorityEnum;
import edu.mum.ea.entity.EnumClass.StatusEnum;
import edu.mum.ea.entity.EnumClass.UserRoleEnum;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author SyedAliAhmed
 */
@ManagedBean
@ApplicationScoped
public class UtilityMB {

    /**
     * Creates a new instance of UtilityMB
     */
    public UtilityMB() {
        
    }
    
    
    
    public UserRoleEnum[] getUserRoles(){
    
        return UserRoleEnum.values();
    }
    
    public GenderEnum[] getGenders(){
    
        return GenderEnum.values();
    }
    
    public PriorityEnum[] getPriority(){
    
        return PriorityEnum.values();
    }
    
    public StatusEnum[] getStatuses(){
    
        return StatusEnum.values();
    }
}
