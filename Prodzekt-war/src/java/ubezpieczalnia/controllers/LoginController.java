/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.controllers;

import ubezpieczalnia.utils.SessionManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private List<Konto> kontaList = new ArrayList<>();

    @EJB
    private KontoEJB kontoEJB;

    public Konto getKonto() {
        return konto;
    }

    public void setKonto(Konto konto) {
        this.konto = konto;
    }

    public List<Konto> findAllKonta() {
        kontaList = kontoEJB.findAll();
        return kontaList;
    }

    public Konto findKondoById(int id) throws Exception {
        konto = kontoEJB.findById(id);
        return konto;
    }

    public Konto addNewKonto() {
        kontoEJB.addNew(this.konto);
        return konto;
    }

    public boolean checkLogged() {
        return SessionManager.getObjectFromSession("logged") != null;
    }

    public String checkPermission() {
        String permissions = (String) SessionManager.getObjectFromSession("permission");
        if (permissions != null) {
            return permissions;
        }
        return "";
    }

    public Konto findKontoByLoginAndPassword() throws Exception {
        konto = kontoEJB.checkoutLogin(this.konto.getKontoLogin(), this.konto.getKontoHaslo());
        return konto;
    }

    public String login() throws Exception {

        if (konto != null && konto.getKontoLogin() != null && konto.getKontoHaslo() != null) {
            konto = kontoEJB.checkoutLogin(konto.getKontoLogin(), konto.getKontoHaslo());
            if (konto != null) {
                if (checkLogged() == false) {
                    SessionManager.addToSession("logged", true);
                    SessionManager.addToSession("permission", konto.getKontoUprawnienia());
                }
                return PageController.getPage("index.xhtml");
            }
        }
        SessionManager.addToSession("LOGIN_ERROR", "Błędny login lub hasło");
        return PageController.getPage("login.xhtml");
    }

    public String checkParam(String param) {
        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();

        return params.get(param);
    }

    public void logout() {
        if (SessionManager.getObjectFromSession("logged") != null) {
            SessionManager.destroySession();
        }
    }
}
