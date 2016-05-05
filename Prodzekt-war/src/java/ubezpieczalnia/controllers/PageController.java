/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import ubezpieczalnia.utils.SessionManager;

/**
 *
 * @author Jacek
 */
@ManagedBean
@RequestScoped
public class PageController {

    private static final String[] pages = {
        "/index.xhtml", "/contact.xhtml", "/offer.xhtml", "/crud.xhtml", "/login.xhtml", "/admin.xhtml",
        "/register.xhtml", "/admin/test.xhtml", "/adminPages/employees.xhtml", "/customerPages/insurance.xhtml"
    };
    
    public static boolean isPageExist(String page) {
        
        Logger.getLogger("INFO").log(Level.INFO, "RECIVED PAGE[isPageExist] = " + page);
        for (String tmp : pages) {
            if (tmp.equals(page)) {
                return true;
            }
        }
        return false;
    }

    public static String getPage(String page) {
        if (isPageExist(page)) {
            return (page + "?faces-redirect=true");
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public static String getPageParams(String page, String params) {
        StringBuilder pageUrlBuilder = new StringBuilder();
        pageUrlBuilder.append(page);
        pageUrlBuilder.append("?");
        pageUrlBuilder.append(params);

        if (isPageExist(page)) {
            return pageUrlBuilder.toString();
        }
        return "/index.xhtml?faces-redirect=true";
    }
    
    public static String getCurrentUrl() {
        HttpServletRequest req = (HttpServletRequest)SessionManager.getContext().getRequest();
        return req.getRequestURI();
    }

    public static void redirect(String url) {
        Logger.getLogger("INFO").log(Level.INFO, url);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
        myNav.handleNavigation(facesContext, null, getPage(url));
    }
}
