/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

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
public class RegisterController {

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

    public List<Klient> findAllKlienci() {
        klienci = klientEJB.findAll();
        return klienci;
    }

    public Klient findKlientById(int id) throws Exception {
        klient = klientEJB.findById(id);
        return klient;
    }

    public String registerAccount() {
        Konto konto = loginController.getKonto();
        try {
            loginController.findKontoByLoginAndPassword();
            SessionManager.addToSession("REGISTER_ERROR", "Błędny login");
            return PageController.getPage("register.xhtml");
        } catch (Exception ex) {
            loginController.setKonto(konto);
            loginController.getKonto().setKontoUprawnienia("KLIENT");
            adresController.addNew();
            loginController.addNewKonto();

            try {
                loginController.findKontoByLoginAndPassword();
                adresController.findAdresByCityAndStreet();

                klient.setKlientAdresIdFk(adresController.getAdres());
                klient.setKlientKontoIdFk(loginController.getKonto());

                klientEJB.addNew(klient);
                return PageController.getPage("login.xhtml");

            } catch (Exception ex1) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        SessionManager.addToSession("REGISTER_ERROR", "Błąd podczas rejestracji");
        return PageController.getPage("register.xhtml");
    }
}
