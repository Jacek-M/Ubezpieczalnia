/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.controllers;

import ubezpieczalnia.utils.SessionManager;
import java.io.Serializable;
import java.util.Map;
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
        return SessionManager.getObjectFromSession("logged") != null;
    }

    public String getError() {
        return (String) SessionManager.removeObjectFromSession("LOGIN_ERROR");
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
            if (konto == null) {
                return "";
            }
            konto = kontoEJB.checkoutLogin(konto.getKontoLogin(), konto.getKontoHaslo());
            if (konto != null) {
                if (checkLogged() == false) {
                    SessionManager.addToSession("logged", true);
                    SessionManager.addToSession("permission", konto.getKontoUprawnienia());
                }
                return "index.xhtml?faces-redirect=true";
            }
        } catch (Exception ex) {
            SessionManager.addToSession("LOGIN_ERROR", "Błędny login lub hasło");
            return "login.xhtml?faces-redirect=true";
        }
        return "index.xhtml?faces-redirect=true";
    }

    public String checkParam(String param) {
        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();

        return params.get(param);
    }

    public void logout() {
        if (SessionManager.getObjectFromSession("logged") != null) {

            SessionManager.destroySession();
            SessionManager.redirect("login.xhtml?faces-redirect=true");

        }
    }

}
