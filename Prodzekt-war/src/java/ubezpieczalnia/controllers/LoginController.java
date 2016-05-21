/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.controllers;

import ubezpieczalnia.utils.SessionManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import ubezpieczalnia.entities.Klient;
import ubezpieczalnia.entities.Konto;
import ubezpieczalnia.entities.Pracownik;
import ubezpieczalnia.entities.Szkoda;
import ubezpieczalnia.entities.Umowa;
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
                        this.konto = kontoEJB.refresh(this.konto);
                        SessionManager.addToSession("id_konta", konto.getKontoId());
                        SessionManager.addToSession("logged", true);
                        SessionManager.addToSession("permission", konto.getKontoUprawnienia());
                        SessionManager.addToSession("id", this.getPracownikAccount().getPracownikId());
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

    public Klient getKlientAccount() {
        if (this.konto.getKlientCollection() == null) {
            return null;
        }
        this.konto = kontoEJB.refresh(this.konto);
        for (Klient klient : this.konto.getKlientCollection()) {
            if (klient != null) {
                System.out.println("IN NULL");
                Logger.getLogger("INFO").log(Level.INFO, "CLIENT FROM ACCOUNT: {0}", klient.getKlientImie());
                return klient;
            }
        }
        return null;
    }

    public Pracownik getPracownikAccount() {
        if (this.konto.getPracownikCollection() == null) {
            return null;
        }
        this.konto = kontoEJB.refresh(this.konto);
        for (Pracownik pracownik : this.konto.getPracownikCollection()) {
            if (pracownik != null) {
                return pracownik;
            }
        }
        return null;
    }

    public ArrayList<Umowa> getUmowaPayments() {
        ArrayList<Umowa> temp = new ArrayList<>();
        this.konto = kontoEJB.refresh(this.konto);
        for (Umowa umowa : this.getKlientAccount().getUmowaCollection()) {
            if (umowa != null && !umowa.getUmowaStatus().equals("ZAAKCEPTOWANA")) {
                temp.add(umowa);
            }
        }
        return temp;
    }

    public ArrayList<Szkoda> getSzkodaPayments() {
        ArrayList<Szkoda> temp = new ArrayList<>();
        this.konto = kontoEJB.refresh(this.konto);

        for (Umowa umowa : this.getKlientAccount().getUmowaCollection()) {
            if (umowa != null) {
                for (Szkoda szkoda : umowa.getSzkodaCollection()) {
                    if (szkoda != null && szkoda.getSzkodaTyp().equals("WINNY")) {
                        temp.add(szkoda);
                    }
                }
            }
        }
        return temp;
    }

    public List<SelectItem> getUmowaSelectList() {
        this.konto = kontoEJB.refresh(this.konto);
        List<SelectItem> umowaSelectList = new ArrayList<>();
        for (Umowa umowa : this.getKlientAccount().getUmowaCollection()) {
            if (!umowa.getUmowaStatus().equals("NOWA") && umowa.getUmowaDataZakonczenia().after(new Date())) {
                String label = umowa.getUmowaId() + " | " + umowa.getUmowaPojazdIdFk().getPojazdMarka() + " " + umowa.getUmowaPojazdIdFk().getPojazdModel();
                umowaSelectList.add(new SelectItem(umowa.getUmowaId(), label));
            }
        }
        
        if(umowaSelectList.size() <= 0) umowaSelectList.add(new SelectItem(-1, "Brak opłaconych umów"));
        return umowaSelectList;
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
        this.konto = kontoEJB.addNew(this.konto);
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

    public void receivedPost() {
        Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (requestParams.get("post_id") != null && requestParams.get("post_type") != null) {

        }
    }

//    @PostConstruct
//    public void receivedPost() {
//        Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        if (requestParams.get("post_id") != null && requestParams.get("post_type") != null) {
//            System.out.println("requestParams.get(\"post_type\") " + requestParams.get("post_type"));
//            takeRepair(requestParams.get("post_id"));
//        }
//    }
//    
//    private void takeRepair(String szkodaId){
//        
//    }
}
