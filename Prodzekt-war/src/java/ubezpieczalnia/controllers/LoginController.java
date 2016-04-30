/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.controllers;

import Utils.SessionManager;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import ubezpieczalnia.entities.Konto;
import ubezpieczalnia.model.KontoEJB;

/**
 *
 * @author Jacek
 */
@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    private Konto konto = new Konto();

    @EJB
    private KontoEJB kontoEJB;

    public Konto getKonto() {
        return konto;
    }

    public void setKonto(Konto konto) {
        this.konto = konto;
    }

    public boolean checkLogged() {
        if (SessionManager.getContext() == null) {
            return false;
        } else if (SessionManager.getObjectFromSession("logged") == null) {
            return false;
        }
        return true;
    }


    public String getError() {
        if (SessionManager.getContext() != null) {
            return (String) SessionManager.removeObjectFromSession("LOGIN_ERROR");
        }
        return "";
    }

    public String checkPermission() {
        String permissions = (String) SessionManager.getObjectFromSession("permission");
        if (permissions != null) {
            return permissions;
        }
        return "";
    }

    public String login() throws Exception {
        try {
            konto = kontoEJB.checkoutLogin(konto.getKontoLogin(), konto.getKontoHaslo());
            if (konto != null) {
                if (SessionManager.getContext() == null) {
                    SessionManager.initSession();
                }

                if (checkLogged() == false) {
                    SessionManager.addToSession("logged", true);
                    SessionManager.addToSession("permission", konto.getKontoUprawnienia());
                }
                return "index.xhtml?faces-redirect=true";
            }
        } catch (Exception ex) {
            if (SessionManager.getContext() == null) {
                SessionManager.initSession();
            }
            SessionManager.addToSession("LOGIN_ERROR", "Błędny login lub hasło");
            return "login.xhtml?faces-redirect=true";
        }
        return "index.xhtml?faces-redirect=true";
    }

    public void logout() {
        if (SessionManager.getObjectFromSession("logged") != null) {
            SessionManager.destroySession();
            SessionManager.redirect("login.xhtml?faces-redirect=true");
        }
    }

}
