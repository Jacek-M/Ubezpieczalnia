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
import javax.faces.model.SelectItem;
import javax.inject.Named;
import ubezpieczalnia.entities.Zaklad;
import ubezpieczalnia.model.ZakladEJB;

/**
 *
 * @author layfl
 */
@Named(value = "zakladController")
@ManagedBean
@RequestScoped
public class ZakladController implements AbstractController<Zaklad> {

    @EJB
    private ZakladEJB zakladEJB;

    private Zaklad zaklad = new Zaklad();
    private List<Zaklad> zakladList = new ArrayList<>();
    private List<SelectItem> zakladSelectList = new ArrayList<>();

    public Zaklad getZaklad() {
        return zaklad;
    }

    public void setZaklad(Zaklad zaklad) {
        this.zaklad = zaklad;
    }

    public List<Zaklad> getZakladList() {
        return zakladList;
    }

    public void setZakladList(List<Zaklad> zakladList) {
        this.zakladList = zakladList;
    }
    
    public void setZakladSelectList(List<SelectItem> zakladSelectList) {
        this.zakladSelectList = zakladSelectList;
    }

    public List<SelectItem> getZakladSelectList() {
        if (this.findAll().size() <= 0) {
            this.zakladSelectList.add(new SelectItem(-1, "Brak zakładów"));
            return this.zakladSelectList;
        } else {
            this.zakladSelectList.add(new SelectItem(-1, "-- WYBIERZ ZAKŁAD --"));
            for (Zaklad zakladList1 : this.zakladList) {
                String miejscowosc = zakladList1.getZakladAdresIdFk().getAdresMiejscowosc() + " " + zakladList1.getZakladAdresIdFk().getAdresUlica();
                this.zakladSelectList.add(new SelectItem(zakladList1.getZakladId(), miejscowosc));
            }
        }
        return zakladSelectList;
    }

    @Override
    public List<Zaklad> findAll() {
        this.zakladList = zakladEJB.findAll();
        return this.zakladList;
    }

    @Override
    public Zaklad findById() throws Exception {
        this.zaklad = zakladEJB.findById(this.zaklad.getZakladId());
        return zaklad;
    }

    @Override
    public String addNew() {
        zakladEJB.addNew(zaklad);
        return PageController.getPage("/adminPages/employees.xhtml"); // zmienic na liste zakladow :d
    }

    @Override
    public String update() {
        zakladEJB.update(this.zaklad);
        return PageController.getPage("/adminPages/employees.xhtml"); // zmienic na liste zakladow :d

    }

    @Override
    public String delete() {
        zakladEJB.delete(zaklad);
        return PageController.getPage("/adminPages/employees.xhtml"); // zmienic na liste zakladow :d

    }

}
