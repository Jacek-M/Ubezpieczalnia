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
import javax.faces.model.SelectItem;
import javax.inject.Named;
import ubezpieczalnia.entities.Zaklad;
import ubezpieczalnia.model.ZakladEJB;

/**
 *
 * @author layfl
 */
@ManagedBean
@RequestScoped
public class ZakladController implements AbstractController<Zaklad> {

    @ManagedProperty(value = "#{adresController}")
    private AdresController adresController;
    
    @EJB
    private ZakladEJB zakladEJB;

    private Zaklad zaklad = new Zaklad();
    private List<Zaklad> zakladList = new ArrayList<>();
    private List<SelectItem> zakladSelectList = new ArrayList<>();

    public AdresController getAdresController() {
        return adresController;
    }

    public void setAdresController(AdresController adresController) {
        this.adresController = adresController;
    }

    
    public Zaklad getZaklad() {
        return zaklad;
    }

    public void setZaklad(Zaklad zaklad) {
        this.zaklad = zaklad;
    }

    public List<Zaklad> getZakladList() {
        this.findAll();
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
            this.zakladSelectList.add(new SelectItem(-1, "BRAK ZAKŁADÓW"));
            return this.zakladSelectList;
        } else {
            this.zakladSelectList.add(new SelectItem(-1, "WYBIERZ ZAKŁAD"));
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
        return PageController.getPage("/adminPages/services/services.xhtml");
    }

    @Override
    public String update() {
        this.zaklad.setZakladAdresIdFk(adresController.getAdres());
        zakladEJB.update(this.zaklad);
        return PageController.getPage("/adminPages/services/services.xhtml");

    }

    @Override
    public String delete() {
        zakladEJB.delete(zaklad);
        return PageController.getPage("/adminPages/services/services.xhtml");

    }
    
    public String registerService(){
        adresController.addNew();
        this.zaklad.setZakladAdresIdFk(this.adresController.getAdres());
        this.addNew();
        return PageController.getPage("/adminPages/services/services.xhtml");
    }
    
    public void receivedPost() {
        zakladEJB.clearCache();
        Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        if (requestParams.get("post_id") != null) {
            this.zaklad.setZakladId(Integer.parseInt(requestParams.get("post_id")));
            try {
                this.findById();
                adresController.setAdres(this.zaklad.getZakladAdresIdFk());
            } catch (Exception ex) {
                Logger.getLogger(ZakladController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
