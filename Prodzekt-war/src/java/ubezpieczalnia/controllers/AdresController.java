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
import javax.faces.bean.RequestScoped;
import ubezpieczalnia.entities.Adres;
import ubezpieczalnia.model.AdresEJB;

/**
 *
 * @author layfl
 */
@ManagedBean
@RequestScoped
public class AdresController implements AbstractController<Adres> {

    @EJB
    private AdresEJB adresEJB;

    private Adres adres = new Adres();
    private List<Adres> adresList = new ArrayList<>();

    public Adres getAdres() {
        return this.adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public List<Adres> getAdresList() {
        adresList = adresEJB.findAll();
        return adresList;
    }

    public String findAdresByCityAndStreet() {
        try {
            this.adres = adresEJB.findByCityAndStreet(this.adres.getAdresUlica(), this.adres.getAdresMiejscowosc(), this.adres.getAdresKodPocztowy());
        } catch (Exception ex) {
            Logger.getLogger(AdresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @Override
    public List<Adres> findAll() {
        this.adresList = adresEJB.findAll();
        return adresList;
    }

    @Override
    public Adres findById() throws Exception {
        this.adres = adresEJB.findById(this.adres.getAdresId());
        return adres;
    }

    @Override
    public String addNew() {
        adresEJB.addNew(this.adres);
        return PageController.getCurrentUrl();
    }

    @Override
    public String update() {
        adresEJB.update(this.adres);
        return PageController.getCurrentUrl();
    }

    @Override
    public String delete() {
        adresEJB.delete(this.adres);
        return PageController.getCurrentUrl();
    }


}
