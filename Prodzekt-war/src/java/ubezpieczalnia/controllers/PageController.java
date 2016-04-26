/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Jacek
 */
@ManagedBean
@RequestScoped
public class PageController {
    
    public String getPage(String page){
        return  page + "?faces-redirect=true";
    }
    
}
