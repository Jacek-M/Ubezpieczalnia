/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import ubezpieczalnia.entities.Klient;
import ubezpieczalnia.entities.Konto;
import ubezpieczalnia.model.KlientEJB;
import ubezpieczalnia.utils.SessionManager;

/**
 *
 * @author layfl
 */
@ManagedBean
@RequestScoped
public class RegisterController implements AbstractController<Klient> {

    @ManagedProperty(value = "#{adresController}")
    private AdresController adresController;

    @ManagedProperty(value = "#{loginController}")
    private LoginController loginController;

    @EJB
    private KlientEJB klientEJB;

    private Klient klient = new Klient();
    private List<Klient> klienci = new ArrayList<>();

    public AdresController getAdresController() {
        return adresController;
    }

    public void setAdresController(AdresController adresController) {
        this.adresController = adresController;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public List<Klient> getKlienci() {
        findAll();
        return klienci;
    }

    public void setKlienci(List<Klient> klienci) {
        this.klienci = klienci;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }



    public String registerAccount() {
        Konto konto = loginController.getKonto();
        try {
            loginController.findKontoByLoginAndPassword();
            SessionManager.addToSession("REGISTER_ERROR", "Błędny login");
            return PageController.getPage("/register.xhtml");
        } catch (Exception ex) {
            loginController.setKonto(konto);
            loginController.getKonto().setKontoUprawnienia("KLIENT");
            adresController.addNew();
            loginController.addNew();

            try {
                
                klient.setKlientAdresIdFk(adresController.getAdres());
                klient.setKlientKontoIdFk(loginController.getKonto());

                this.klient = klientEJB.addNew(klient);
                return PageController.getPage("/login.xhtml");

            } catch (Exception ex1) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        SessionManager.addToSession("REGISTER_ERROR", "Błąd podczas rejestracji");
        return PageController.getPage("/register.xhtml");
    }

    @Override
    public List<Klient> findAll() {
        klienci = klientEJB.findAll();
        return klienci;
    }

    @Override
    public Klient findById() throws Exception {
        klient = klientEJB.findById(this.klient.getKlientId());
        return klient;
    }

    @Override
    public String addNew() {
        return PageController.getCurrentUrl();
    }

    @Override
    public String update() {
        return PageController.getCurrentUrl();
    }

    @Override
    public String delete() {
        
        loginController.setKonto(klient.getKlientKontoIdFk());
        adresController.setAdres(klient.getKlientAdresIdFk());
        
        loginController.delete();
        adresController.delete();
        klientEJB.delete(this.klient);
        return PageController.getCurrentUrl();
    }
    
    @PostConstruct
    public void receivedPost() {
        Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        if (requestParams.get("post_id") != null) {
            System.out.println(requestParams.get("post_id"));
            this.klient.setKlientId(Integer.parseInt(requestParams.get("post_id")));
            try {
                this.findById();
            } catch (Exception ex) {
                Logger.getLogger(PracownikController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
