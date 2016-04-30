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
public class AdresController {

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

    public String addAdress() {
        adresEJB.addNewAdres(this.adres);
        return "crud.xhtml?faces-redirect=true";
    }

    public List<Adres> getAdresList() {
        adresList = adresEJB.findAdresy();
        return adresList;
    }

    public String findAdresById() {
        Adres adr;
        try {
            adr = adresEJB.findAdresById(this.adres.getAdresId());
            if (adr != null) {
                this.adres = adr;
            }
        } catch (Exception ex) {
            Logger.getLogger(AdresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "crud.xhtml";
    }

}