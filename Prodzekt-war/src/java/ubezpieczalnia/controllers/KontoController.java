/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import ubezpieczalnia.entities.Konto;
import ubezpieczalnia.model.KontoEJB;

/**
 *
 * @author layfl Tworzenie kont zewnętrznych nie związanych z sesją
 */
@ManagedBean
@RequestScoped
public class KontoController implements AbstractController<Konto> {

    private Konto konto = new Konto();

    @EJB
    private KontoEJB kontoEJB;

    public KontoController() {

    }

    public Konto getKonto() {
        return konto;
    }

    public void setKonto(Konto konto) {
        this.konto = konto;
    }

    public Konto checkLogin(String login, String pass) {
        Konto test = null;
        try {
            test = kontoEJB.checkoutLogin(login, pass);
        } catch (Exception ex) {
            Logger.getLogger(KontoController.class.getName()).log(Level.INFO, "Cannot find login: " + login);
            test = null;
        }
        return test;
    }

    @Override
    public List<Konto> findAll() {
        throw new UnsupportedOperationException("Nope"); 
    }

    @Override
    public Konto findById() throws Exception {
        this.konto = kontoEJB.findById(this.konto.getKontoId());
        return this.konto;
    }

    @Override
    public String addNew() {
        this.konto = kontoEJB.addNew(this.konto);
        return PageController.getCurrentUrl();
    }

    @Override
    public String update() {
        kontoEJB.update(this.konto);
        return PageController.getCurrentUrl();
    }

    @Override
    public String delete() {
        throw new UnsupportedOperationException("Nope");
    }

}
