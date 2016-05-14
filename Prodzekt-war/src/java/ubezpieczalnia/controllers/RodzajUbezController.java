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
import ubezpieczalnia.entities.RodzajUbezpieczenia;
import ubezpieczalnia.model.RodzajUbezpieczeniaEJB;


/**
 *
 * @author Jacek
 */
@ManagedBean
@RequestScoped
public class RodzajUbezController implements AbstractController<RodzajUbezpieczenia>{

    @EJB
    private RodzajUbezpieczeniaEJB rodzajUbezpieczeniaEJB;

    private RodzajUbezpieczenia rodzajUbez = new RodzajUbezpieczenia();
    private List<RodzajUbezpieczenia> rodzajUbezList = new ArrayList<>();
    private List<SelectItem> rodzajUbezSelectList = new ArrayList<>();

    public RodzajUbezpieczenia getRodzajUbez() {
        return rodzajUbez;
    }

    public void setRodzajUbez(RodzajUbezpieczenia rodzajUbez) {
        this.rodzajUbez = rodzajUbez;
    }

    public List<RodzajUbezpieczenia> getRodzajUbezList() {
        this.findAll();
        return rodzajUbezList;
    }

    public void setRodzajUbezList(List<RodzajUbezpieczenia> rodzajUbezList) {
        this.rodzajUbezList = rodzajUbezList;
    }

    public List<SelectItem> getRodzajUbezSelectList() {
        return rodzajUbezSelectList;
    }

    public void setRodzajUbezSelectList(List<SelectItem> rodzajUbezSelectList) {
        this.rodzajUbezSelectList = rodzajUbezSelectList;
    }
    
    
    @Override
    public List<RodzajUbezpieczenia> findAll() {
        this.rodzajUbezList = rodzajUbezpieczeniaEJB.findAll();
        return this.rodzajUbezList;
    }

    @Override
    public RodzajUbezpieczenia findById() throws Exception {
        this.rodzajUbez = rodzajUbezpieczeniaEJB.findById(this.rodzajUbez.getRodzajUbezpieczeniaId());
        return rodzajUbez;
    }

    @Override
    public String addNew() {
        rodzajUbezpieczeniaEJB.addNew(rodzajUbez);
        return PageController.getPage("/adminPages/insurances/insurances.xhtml"); 
    }

    @Override
    public String update() {
        rodzajUbezpieczeniaEJB.update(this.rodzajUbez);
        return PageController.getPage("/adminPages/insurances/insurances.xhtml");

    }

    @Override
    public String delete() {
        rodzajUbezpieczeniaEJB.delete(rodzajUbez);
        return PageController.getPage("/adminPages/insurances/insurances.xhtml"); 

    }
    
  
    
    @PostConstruct
    public void receivedPost() {
        Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        if (requestParams.get("post_id") != null) {
            System.out.println("PARAAAAAAAAAAAMMMMMMMM ============" + requestParams.get("post_id"));
            this.rodzajUbez.setRodzajUbezpieczeniaId(Integer.parseInt(requestParams.get("post_id")));
            try {
                this.findById();
            } catch (Exception ex) {
                Logger.getLogger(RodzajUbezController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
