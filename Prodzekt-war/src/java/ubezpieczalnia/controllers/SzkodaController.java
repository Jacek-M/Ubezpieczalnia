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
import ubezpieczalnia.entities.Szkoda;
import ubezpieczalnia.model.SzkodaEJB;

/**
 *
 * @author Jacek
 */
@ManagedBean
@RequestScoped
public class SzkodaController implements AbstractController<Szkoda> {

    @ManagedProperty(value = "#{umowaController}")
    private UmowaController umowaController;

    @ManagedProperty(value = "#{samochodZastController}")
    private SamochodZastController samochodZastController;

    @ManagedProperty(value = "#{zakladController}")
    private ZakladController zakladController;

    @ManagedProperty(value = "#{uczestnikController}")
    private UczestnikController uczestnikController;

    @EJB
    private SzkodaEJB szkodaEJB;

    private Szkoda szkoda = new Szkoda();
    private List<Szkoda> szkodaList = new ArrayList<>();

    public UmowaController getUmowaController() {
        return umowaController;
    }

    public void setUmowaController(UmowaController umowaController) {
        this.umowaController = umowaController;
    }

    public SamochodZastController getSamochodZastController() {
        return samochodZastController;
    }

    public void setSamochodZastController(SamochodZastController samochodZastController) {
        this.samochodZastController = samochodZastController;
    }

    public ZakladController getZakladController() {
        return zakladController;
    }

    public void setZakladController(ZakladController zakladController) {
        this.zakladController = zakladController;
    }

    public UczestnikController getUczestnikController() {
        return uczestnikController;
    }

    public void setUczestnikController(UczestnikController uczestnikController) {
        this.uczestnikController = uczestnikController;
    }

    public Szkoda getSzkoda() {
        return szkoda;
    }

    public void setSzkoda(Szkoda szkoda) {
        this.szkoda = szkoda;
    }

    public List<Szkoda> getSzkodaList() {
        this.findAll();
        return szkodaList;
    }

    public void setSzkodaList(List<Szkoda> szkodaList) {
        this.szkodaList = szkodaList;
    }

    public String registerIncident() {
        return "";
    }

    @Override
    public List<Szkoda> findAll() {
        szkodaList = szkodaEJB.findAll();
        return szkodaList;
    }

    @Override
    public Szkoda findById() throws Exception {
        szkoda = szkodaEJB.findById(this.szkoda.getSzkodaId());
        return szkoda;
    }

    @Override
    public String addNew() {
        szkodaEJB.addNew(this.szkoda);
        return PageController.getPage("/adminPages/incidents/incidents.xhtml");
    }

    @Override
    public String update() {
        this.szkoda.setSzkodaUmowaIdFk(umowaController.getUmowa());
        this.szkoda.setSzkodaSamochodZastepczyIdFk(samochodZastController.getSamochodZastepczy());
        this.szkoda.setSzkodaZakladIdFk(zakladController.getZaklad());
        this.szkoda.setSzkodaUczestnikIdFk(uczestnikController.getUczestnik());
        szkodaEJB.update(this.szkoda);
        return PageController.getPage("/adminPages/incidents/incidents.xhtml");

    }

    @Override
    public String delete() {
        szkodaEJB.delete(this.szkoda);
        return PageController.getPage("/adminPages/incidents/incidents.xhtml");

    }

    @PostConstruct
    public void receivedPost() {
        Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        if (requestParams.get("post_id") != null) {
            System.out.println(requestParams.get("post_id"));
            this.szkoda.setSzkodaId(Integer.parseInt(requestParams.get("post_id")));
            try {
                this.findById();
                umowaController.setUmowa(this.szkoda.getSzkodaUmowaIdFk());
                samochodZastController.setSamochodZastepczy(this.szkoda.getSzkodaSamochodZastepczyIdFk());
                zakladController.setZaklad(this.szkoda.getSzkodaZakladIdFk());
                uczestnikController.setUczestnik(this.szkoda.getSzkodaUczestnikIdFk());
            } catch (Exception ex) {
                Logger.getLogger(SzkodaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
