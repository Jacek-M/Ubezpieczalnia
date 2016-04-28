/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import ubezpieczalnia.entities.Konto;
import ubezpieczalnia.model.KontoEJB;

/**
 *
 * @author Jacek
 */
@ManagedBean
@SessionScoped
public class loginController {

    private String username;
    private String password;

    @EJB
    private KontoEJB kontoEJB;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() throws Exception {
        Konto konto;
        try {
            konto = kontoEJB.checkoutLogin(username, password);
            if (konto != null) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getSessionMap().put("konto", konto);
                context.getExternalContext().getSessionMap().put("permission", konto.getKontoUprawnienia());
                return "index.xhtml?faces-redirect=true";
            }
                
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("LoginError", "Błędne hasło lub login");
        }
        return "login.xhtml?faces-redirect=true";
    }

    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "index.xhtml?faces-redirect=true");
//        return "index.xhtml";
    }

}
