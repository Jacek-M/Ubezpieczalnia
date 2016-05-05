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
public class LoginController implements Serializable, AbstractController<Konto> {

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

    public String login() {
        try {
            if (konto != null && konto.getKontoLogin() != null && konto.getKontoHaslo() != null) {
                konto = kontoEJB.checkoutLogin(konto.getKontoLogin(), konto.getKontoHaslo());
                if (konto != null) {
                    if (checkLogged() == false) {
                        SessionManager.addToSession("logged", true);
                        SessionManager.addToSession("permission", konto.getKontoUprawnienia());
                    }
                    return PageController.getPage("/index.xhtml");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger("EXCEPTIONS").log(Level.WARNING, "Błędne dane logowania dla użytkownika=" + konto.getKontoLogin());

        }
        SessionManager.addToSession("LOGIN_ERROR", "Błędny login lub hasło");
        return PageController.getPage("/login.xhtml");
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

    @Override
    public List<Konto> findAll() {
        kontaList = kontoEJB.findAll();
        return kontaList;
    }

    @Override
    public Konto findById() throws Exception {
        konto = kontoEJB.findById(this.konto.getKontoId());
        return konto;
    }

    @Override
    public String addNew() {
        kontoEJB.addNew(this.konto);
        return PageController.getCurrentUrl();
    }

    @Override
    public String update() {
        return PageController.getCurrentUrl();
    }

    @Override
    public String delete() {
        kontoEJB.delete(this.konto);
        return PageController.getCurrentUrl();
    }
}
