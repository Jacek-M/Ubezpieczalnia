/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.controllers;

import java.util.ArrayList;
import java.util.List;
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
    
    
    public List<Adres> getAdresList() {
        adresList = adresEJB.findAdresy();
        return adresList;
    }
    
}
