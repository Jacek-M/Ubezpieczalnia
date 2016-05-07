/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import ubezpieczalnia.entities.Oddzial;
import ubezpieczalnia.model.OddzialEJB;

/**
 *
 * @author layfl
 */
@Named(value = "oddzialController")
@ManagedBean
@RequestScoped
public class OddzialController implements AbstractController<Oddzial> {

    @EJB
    private OddzialEJB oddzialEJB;

    private Oddzial oddzial = new Oddzial();
    private List<Oddzial> oddzialList = new ArrayList<>();
    private List<SelectItem> oddzialSelectList = new ArrayList<>();

    public Oddzial getOddzial() {
        return oddzial;
    }

    public void setOddzial(Oddzial oddzial) {
        this.oddzial = oddzial;
    }

    public List<Oddzial> getOddzialList() {
        return oddzialList;
    }

    public void setOddzialList(List<Oddzial> oddzialList) {
        this.oddzialList = oddzialList;
    }

    public List<SelectItem> getOddzialSelectList() {
        if (this.findAll().size() <= 0) {
            this.oddzialSelectList.add(new SelectItem(-1, "Brak oddziałów"));
            return this.oddzialSelectList;
        } else {
            this.oddzialSelectList.add(new SelectItem(-1, "-- WYBIERZ ODDZIAŁ --"));
            for (Oddzial oddzialList1 : this.oddzialList) {
                String miejscowosc = oddzialList1.getOddzialAdresIdFk().getAdresMiejscowosc() + " " + oddzialList1.getOddzialAdresIdFk().getAdresUlica();
                this.oddzialSelectList.add(new SelectItem(oddzialList1.getOddzialId(), miejscowosc));
            }
        }
        return oddzialSelectList;
    }

    public void setOddzialSelectList(List<SelectItem> oddzialSelectList) {
        this.oddzialSelectList = oddzialSelectList;
    }

    @Override
    public List<Oddzial> findAll() {
        this.oddzialList = oddzialEJB.findAll();
        return this.oddzialList;
    }

    @Override
    public Oddzial findById() throws Exception {
        this.oddzial = oddzialEJB.findById(this.oddzial.getOddzialId());
        System.out.println("ZNALEZIONO ODDZIAL = " + this.oddzial.getOddzialId() + " " + this.oddzial.getOddzialTelefon());
        return this.oddzial;
    }

    @Override
    public String addNew() {
        oddzialEJB.addNew(this.oddzial);
        return PageController.getCurrentUrl();
    }

    @Override
    public String update() {
        oddzialEJB.update(this.oddzial);
        return PageController.getCurrentUrl();
    }

    @Override
    public String delete() {
        oddzialEJB.delete(this.oddzial);
        return PageController.getCurrentUrl();
    }

}
