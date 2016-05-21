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
import ubezpieczalnia.entities.Pojazd;
import ubezpieczalnia.model.PojazdEJB;

/**
 *
 * @author Jacek
 */
@ManagedBean
@RequestScoped
public class PojazdController implements AbstractController<Pojazd>{

    @EJB
    private PojazdEJB pojazdEJB;
    
    private Pojazd pojazd = new Pojazd();
    private List<Pojazd> pojazdList = new ArrayList<>();
    private List<SelectItem> pojazdSelectList = new ArrayList<>();

    public List<SelectItem> getPojazdSelectList() {
        return pojazdSelectList;
    }

    public void setPojazdSelectList(List<SelectItem> pojazdSelectList) {
        this.pojazdSelectList = pojazdSelectList;
    }
    
    

    public Pojazd getPojazd() {
        return pojazd;
    }

    public void setPojazd(Pojazd pojazd) {
        this.pojazd = pojazd;
    }

    public List<Pojazd> getPojazdList() {
        this.findAll();
        return pojazdList;
    }

    public void setPojazdList(List<Pojazd> pojazdList) {
        this.pojazdList = pojazdList;
    }
    
    
    
    @Override
    public List<Pojazd> findAll() {
        pojazdList = pojazdEJB.findAll();
        return pojazdList;
    }
    
    @Override
    public Pojazd findById() throws Exception {
        pojazd = pojazdEJB.findById(this.pojazd.getPojazdId());
        return pojazd;
    }
    
    @Override
    public String addNew() {
        pojazdEJB.addNew(this.pojazd);
        return PageController.getPage("/adminPages/employees.xhtml");
    }
    
    @Override
    public String update() {
        pojazdEJB.update(this.pojazd);
        return PageController.getPage("/adminPages/employees.xhtml");
        
    }
    
    @Override
    public String delete() {
        pojazdEJB.delete(this.pojazd);
        return PageController.getPage("/adminPages/employees.xhtml");
        
    }
    
//    @PostConstruct
    public void receivedPost() {
        pojazdEJB.clearCache();
        Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        if (requestParams.get("post_id") != null) {
            System.out.println(requestParams.get("post_id"));
            this.pojazd.setPojazdId(Integer.parseInt(requestParams.get("post_id")));
            try {
                this.findById();
            } catch (Exception ex) {
                Logger.getLogger(PojazdController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
