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
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import ubezpieczalnia.entities.Oddzial;
import ubezpieczalnia.model.OddzialEJB;

/**
 *
 * @author layfl
 */
@ManagedBean
@RequestScoped
public class OddzialController implements AbstractController<Oddzial> {

    @ManagedProperty(value = "#{adresController}")
    private AdresController adresController;

    @EJB
    private OddzialEJB oddzialEJB;

    private Oddzial oddzial = new Oddzial();
    private List<Oddzial> oddzialList = new ArrayList<>();
    private List<SelectItem> oddzialSelectList = new ArrayList<>();

    public AdresController getAdresController() {
        return adresController;
    }

    public void setAdresController(AdresController adresController) {
        this.adresController = adresController;
    }

    public Oddzial getOddzial() {
        return oddzial;
    }

    public void setOddzial(Oddzial oddzial) {
        this.oddzial = oddzial;
    }

    public List<Oddzial> getOddzialList() {
        this.findAll();
        return oddzialList;
    }

    public void setOddzialList(List<Oddzial> oddzialList) {
        this.oddzialList = oddzialList;
    }

    public List<SelectItem> getOddzialSelectList() {
        if (this.findAll().size() <= 0) {
            this.oddzialSelectList.add(new SelectItem(-1, "BRAK ODDZIAŁÓW"));
            return this.oddzialSelectList;
        } else {
            this.oddzialSelectList.add(new SelectItem(-1, "WYBIERZ ODDZIAŁ"));
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
        return PageController.getPage("/adminPages/services/services.xhtml");
    }

    @Override
    public String update() {
        this.oddzial.setOddzialAdresIdFk(adresController.getAdres());
        oddzialEJB.update(this.oddzial);
        return PageController.getPage("/adminPages/branches/branches.xhtml");
    }

    @Override
    public String delete() {
        oddzialEJB.delete(this.oddzial);
        return PageController.getPage("/adminPages/branches/branches.xhtml");
    }

    public String registerBranche() {
        adresController.addNew();
        this.oddzial.setOddzialAdresIdFk(this.adresController.getAdres());
        this.addNew();
        return PageController.getPage("/adminPages/branches/branches.xhtml");
    }

//    @PostConstruct
    public void receivedPost() {
        oddzialEJB.clearCache();
        Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        if (requestParams.get("post_id") != null) {
            System.out.println(requestParams.get("post_id"));
            this.oddzial.setOddzialId(Integer.parseInt(requestParams.get("post_id")));
            try {
                this.findById();
                this.adresController.setAdres(this.oddzial.getOddzialAdresIdFk());
            } catch (Exception ex) {
                Logger.getLogger(OddzialController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
