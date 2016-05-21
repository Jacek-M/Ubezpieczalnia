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
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import ubezpieczalnia.entities.SamochodZastepczy;
import ubezpieczalnia.model.SamochodZastepczyEJB;

/**
 *
 * @author Jacek
 */

@ManagedBean
@RequestScoped
public class SamochodZastController implements AbstractController<SamochodZastepczy>{

    @EJB
    private SamochodZastepczyEJB samochodZastepczyEJB;

    private SamochodZastepczy samochodZastepczy = new SamochodZastepczy();
    private List<SamochodZastepczy> samochodZastepczyList = new ArrayList<>();
    private List<SelectItem> samochodZastepczySelectList = new ArrayList<>();

    public SamochodZastepczy getSamochodZastepczy() {
        return samochodZastepczy;
    }

    public void setSamochodZastepczy(SamochodZastepczy samochodZastepczy) {
        this.samochodZastepczy = samochodZastepczy;
    }

    public List<SamochodZastepczy> getSamochodZastepczyList() {
        this.findAll();
        return samochodZastepczyList;
    }

    public void setSamochodZastepczyList(List<SamochodZastepczy> samochodZastepczyList) {
        this.samochodZastepczyList = samochodZastepczyList;
    }

    public List<SelectItem> getSamochodZastepczySelectList() {
         if (this.findAll().size() <= 0) {
            this.samochodZastepczySelectList.add(new SelectItem(-1, "Brak samochodów"));
            return this.samochodZastepczySelectList;
        } else {
            this.samochodZastepczySelectList.add(new SelectItem(-1, "-- WYBIERZ --"));
            for (SamochodZastepczy samochodZastepczyList1 : this.samochodZastepczyList) {
                if(samochodZastepczyList1.getSamochodZastepczyCzyDostepny() == 0) continue;
                String text = samochodZastepczyList1.getSamochodZastepczyMarka() + " " + samochodZastepczyList1.getSamochodZastepczyModel();
                this.samochodZastepczySelectList.add(new SelectItem(samochodZastepczyList1.getSamochodZastepczyId(), text));
            }
        }
        return samochodZastepczySelectList;
    }

    public void setSamochodZastepczySelectList(List<SelectItem> samochodZastepczySelectList) {
        this.samochodZastepczySelectList = samochodZastepczySelectList;
    }
    
    
    
    @Override
    public List<SamochodZastepczy> findAll() {
        this.samochodZastepczyList = samochodZastepczyEJB.findAll();
        return this.samochodZastepczyList;
    }

    @Override
    public SamochodZastepczy findById() throws Exception {
        this.samochodZastepczy = samochodZastepczyEJB.findById(this.samochodZastepczy.getSamochodZastepczyId());
        return this.samochodZastepczy;
    }

    @Override
    public String addNew() {
        samochodZastepczyEJB.addNew(this.samochodZastepczy);
        return PageController.getPage("/adminPages/repCars/repCars.xhtml");
    }

    @Override
    public String update() {
        samochodZastepczyEJB.update(this.samochodZastepczy);
        return PageController.getPage("/adminPages/repCars/repCars.xhtml");
    }

    @Override
    public String delete() {
        samochodZastepczyEJB.delete(this.samochodZastepczy);
        return PageController.getPage("/adminPages/repCars/repCars.xhtml");
    }

    
//    @PostConstruct
    public void receivedPost() {
        samochodZastepczyEJB.clearCache();
        Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        if (requestParams.get("post_id") != null) {
            System.out.println(requestParams.get("post_id"));
            this.samochodZastepczy.setSamochodZastepczyId(Integer.parseInt(requestParams.get("post_id")));
            try {
                this.findById();
            } catch (Exception ex) {
                Logger.getLogger(PracownikController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
