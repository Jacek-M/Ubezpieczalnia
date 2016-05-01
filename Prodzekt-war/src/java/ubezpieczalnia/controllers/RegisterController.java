/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ubezpieczalnia.entities.Konto;
import ubezpieczalnia.entities.Adres;
import ubezpieczalnia.entities.Klient;
import ubezpieczalnia.model.AdresEJB;
import ubezpieczalnia.model.KlientEJB;
import ubezpieczalnia.model.KontoEJB;
import ubezpieczalnia.utils.SessionManager;

/**
 *
 * @author layfl
 */
@ManagedBean
@RequestScoped
public class RegisterController {

    @EJB
    private AdresEJB adresEJB;
    @EJB
    private KontoEJB kontoEJB;
    @EJB
    private KlientEJB klientEJB;

    private Konto konto = new Konto();
    private Adres adres = new Adres();
    private Klient klient = new Klient();

    public Konto getKonto() {
        return konto;
    }

    public void setKonto(Konto konto) {
        this.konto = konto;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

//    public String registerAccount() {
//
//        if (this.konto != null && this.adres != null && this.klient != null) {
//
//            try {
//                this.konto = this.kontoEJB.addNewKonto(this.konto);
//                this.adres = this.adresEJB.addNewAdres(this.adres);
//
//                this.konto = kontoEJB.checkoutLogin(this.konto.getKontoLogin(), this.konto.getKontoHaslo());
//                this.adres = adresEJB.findAdresByCityAndStreet(this.adres.getAdresMiejscowosc(), this.adres.getAdresUlica(), this.adres.getAdresKodPocztowy());
//
//                this.klient.setKlientKontoIdFk(this.konto);
//                this.klient.setKlientAdresIdFk(this.adres);
//
//                this.klient = this.klientEJB.addNewKlient(this.klient);
//
//                return PageController.getPage("login.xhtml");
//
//            } catch (Exception ex) {
//                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        SessionManager.addToSession("REGISTER_ERROR", "Błąd podczas rejestracji. Spróbuj ponownie");
//        return PageController.getPage("register.xhtml");
//    }

}
