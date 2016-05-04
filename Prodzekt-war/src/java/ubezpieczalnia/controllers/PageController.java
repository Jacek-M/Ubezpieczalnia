/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.controllers;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jacek
 */
@ManagedBean
@RequestScoped
public class PageController {

    private static final String[] pages = {
        "index.xhtml", "contact.xhtml", "offer.xhtml", "crud.xhtml", "login.xhtml", "admin.xhtml",
        "register.xhtml", "employees.xhtml", "insurance.xhtml"
    };
    
    public static boolean isPageExist(String page) {
//        for (String tmp : pages) {
//            if (tmp.equals(page)) {
//                return true;
//            }
//        }
//        return false;
        return true;
    }

    public static String getPage(String page) {
        if (isPageExist(page)) {
            return (page + "?faces-redirect=true");
        }
        System.out.println(page);
        return "index.xhtml?faces-redirect=true";
    }

    public static String getPageParams(String page, String params) {
        StringBuilder pageUrlBuilder = new StringBuilder();
        pageUrlBuilder.append(page);
        pageUrlBuilder.append("?");
        pageUrlBuilder.append(params);

        if (isPageExist(page)) {
            return pageUrlBuilder.toString();
        }
        return "index.xhtml?faces-redirect=true";
    }

    public static void redirect(String url) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
        myNav.handleNavigation(facesContext, null, getPage(url));
    }
}
