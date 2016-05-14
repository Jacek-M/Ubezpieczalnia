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
import ubezpieczalnia.entities.Wycena;
import ubezpieczalnia.model.WycenaEJB;

/**
 *
 * @author Jacek
 */
@ManagedBean
@RequestScoped
public class WycenaController implements AbstractController<Wycena>{
    
    @ManagedProperty(value = "#{pracownikController}")
    private PracownikController pracownikController;
    
    @ManagedProperty(value = "#{szkodaController}")
    private SzkodaController szkodaController;
    
    @EJB
    private WycenaEJB wycenaEJB;

    private Wycena wycena = new Wycena();
    private List<Wycena> wycenaList = new ArrayList<>();

    public PracownikController getPracownikController() {
        return pracownikController;
    }

    public void setPracownikController(PracownikController pracownikController) {
        this.pracownikController = pracownikController;
    }

    public SzkodaController getSzkodaController() {
        return szkodaController;
    }

    public void setSzkodaController(SzkodaController szkodaController) {
        this.szkodaController = szkodaController;
    }

    public Wycena getWycena() {
        return wycena;
    }

    public void setWycena(Wycena wycena) {
        this.wycena = wycena;
    }

    public List<Wycena> getWycenaList() {
        this.findAll();
        return wycenaList;
    }

    public void setWycenaList(List<Wycena> wycenaList) {
        this.wycenaList = wycenaList;
    }
    
    public String registerIncident() {
        return "";
    }

    @Override
    public List<Wycena> findAll() {
        wycenaList = wycenaEJB.findAll();
        return wycenaList;
    }

    @Override
    public Wycena findById() throws Exception {
        wycena = wycenaEJB.findById(this.wycena.getWycenaId());
        return wycena;
    }

    @Override
    public String addNew() {
        wycenaEJB.addNew(this.wycena);
        return PageController.getPage("/adminPages/valuations/valuations.xhtml");
    }

    @Override
    public String update() {
        this.wycena.setWycenaPracownikIdFk(pracownikController.getPracownik());
        this.wycena.setWycenaSzkodaIdFk(szkodaController.getSzkoda());
        wycenaEJB.update(this.wycena);
        return PageController.getPage("/adminPages/valuations/valuations.xhtml");

    }

    @Override
    public String delete() {
        wycenaEJB.delete(this.wycena);
        return PageController.getPage("/adminPages/valuations/valuations.xhtml");

    }

    @PostConstruct
    public void receivedPost() {
        Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        if (requestParams.get("post_id") != null) {
            System.out.println(requestParams.get("post_id"));
            this.wycena.setWycenaId(Integer.parseInt(requestParams.get("post_id")));
            try {
                this.findById();
                pracownikController.setPracownik(this.wycena.getWycenaPracownikIdFk());
                szkodaController.setSzkoda(this.wycena.getWycenaSzkodaIdFk());
            } catch (Exception ex) {
                Logger.getLogger(WycenaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
