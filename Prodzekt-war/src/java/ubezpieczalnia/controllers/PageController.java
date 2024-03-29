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
        "/register.xhtml", "/admin/test.xhtml", "/customerPages/agreements/agreements.xhtml", "/customerPages/customer/customerView.xhtml", "/customerPages/customer/customerEdit.xhtml", "/customerPages/agreements/buyAgreements.xhtml",
        "/customerPages/agreements/agreementsView.xhtml", "/customerPages/payments/payments.xhtml", "/customerPages/incidents/incidentsView.xhtml", "/customerPages/incidents/incidentsAdd.xhtml",
        "/adminPages/employees/employees.xhtml", "/adminPages/employees/employeesAdd.xhtml", "/adminPages/employees/employeesEdit.xhtml", "/adminPages/employees/employeesView.xhtml",
        "/adminPages/clients/clients.xhtml", "/adminPages/clients/clientsAdd.xhtml", "/adminPages/clients/clientsEdit.xhtml", "/adminPages/clients/clientsView.xhtml",
        "/adminPages/services/services.xhtml", "/adminPages/services/servicesAdd.xhtml", "/adminPages/services/servicesEdit.xhtml", "/adminPages/services/servicesView.xhtml",
        "/adminPages/branches/branches.xhtml", "/adminPages/branches/branchesAdd.xhtml", "/adminPages/branches/branchesEdit.xhtml", "/adminPages/branches/branchesView.xhtml",
        "/adminPages/repCars/repCars.xhtml", "/adminPages/repCars/repCarsAdd.xhtml", "/adminPages/repCars/repCarsEdit.xhtml", "/adminPages/repCars/repCarsView.xhtml",
        "/adminPages/participants/participants.xhtml", "/adminPages/participants/participantsAdd.xhtml", "/adminPages/participants/participantsEdit.xhtml", "/adminPages/participants/participantsView.xhtml",
        "/adminPages/insurances/insurances.xhtml", "/adminPages/insurances/insurancesAdd.xhtml", "/adminPages/insurances/insurancesEdit.xhtml", "/adminPages/insurances/insurancesView.xhtml",
        "/adminPages/agreements/agreements.xhtml", "/adminPages/agreements/agreementsAdd.xhtml", "/adminPages/agreements/agreementsEdit.xhtml", "/adminPages/agreements/agreementsView.xhtml",
        "/adminPages/incidents/incidents.xhtml", "/adminPages/incidents/incidentsAdd.xhtml", "/adminPages/incidents/incidentsEdit.xhtml", "/adminPages/incidents/incidentsView.xhtml",
        "/adminPages/valuations/valuations.xhtml", "/adminPages/valuations/valuationsAdd.xhtml", "/adminPages/valuations/valuationsEdit.xhtml", "/adminPages/valuations/valuationsView.xhtml",
        "/why/services/services.xhtml",
        "/why/order/order.xhtml", "/why/order/orderAdd.xhtml", "/why/order/orderEdit.xhtml", "/why/order/orderView.xhtml", "/why/order/orderHistory.xhtml",
        "/why/valuations/valuationsView.xhtml",
        "/adminPages/admin/admin.xhtml", "/customerPages/valuations/valuationsView.xhtml"

    };

    public static boolean isPageExist(String page) {
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
        HttpServletRequest req = (HttpServletRequest) SessionManager.getContext().getRequest();
        return req.getRequestURI();
    }

    public static String getCurrentAllList() {
        HttpServletRequest req = (HttpServletRequest) SessionManager.getContext().getRequest();
        String uri = req.getRequestURI();

        if (uri.contains("Edit")) {
            uri = uri.split("Edit")[0] + ".xhtml";
        } else if (uri.contains("Add")) {
            uri = uri.split("Add")[0] + ".xhtml";
        } else if (uri.contains("View")) {
            uri = uri.split("View")[0] + ".xhtml";
        } else {
            uri = "/index.xhtml";
        }

        return uri;
    }

    public static void redirect(String url) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
        myNav.handleNavigation(facesContext, null, getPage(url));
    }
}
