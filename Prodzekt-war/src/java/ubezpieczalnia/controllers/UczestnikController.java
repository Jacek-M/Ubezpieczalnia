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
import javax.faces.context.FacesContext;
import ubezpieczalnia.entities.Uczestnik;
import ubezpieczalnia.model.UczestnikEJB;

/**
 *
 * @author Jacek
 */
public class UczestnikController implements AbstractController<Uczestnik> {

    @EJB
    private UczestnikEJB uczestnikEJB;

    private Uczestnik uczestnik = new Uczestnik();
    private List<Uczestnik> uczestnikList = new ArrayList<>();

    public Uczestnik getUczestnik() {
        return uczestnik;
    }

    public void setUczestnik(Uczestnik uczestnik) {
        this.uczestnik = uczestnik;
    }

    public List<Uczestnik> getUczestnikList() {
        this.findAll();
        return uczestnikList;
    }

    public void setUczestnikList(List<Uczestnik> uczestnikList) {
        this.uczestnikList = uczestnikList;
    }

    @Override
    public List<Uczestnik> findAll() {

        uczestnikList = uczestnikEJB.findAll();
        return uczestnikList;
    }

    @Override
    public Uczestnik findById() throws Exception {
        uczestnik = uczestnikEJB.findById(this.uczestnik.getUczestnikId());
        return uczestnik;
    }

    @Override
    public String addNew() {
        uczestnikEJB.addNew(this.uczestnik);
        return PageController.getPage("/adminPages/employees.xhtml");
    }

    @Override
    public String update() {
        uczestnikEJB.update(this.uczestnik);
        return PageController.getPage("/adminPages/employees.xhtml");

    }

    @Override
    public String delete() {
        uczestnikEJB.delete(this.uczestnik);
        return PageController.getPage("/adminPages/employees.xhtml");

    }

    public String registerWorker() {
        return "";
    }

    @PostConstruct
    public void receivedPost() {
        Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        if (requestParams.get("post_id") != null) {
            System.out.println(requestParams.get("post_id"));
            this.uczestnik.setUczestnikId(Integer.parseInt(requestParams.get("post_id")));
            try {
                this.findById();
            } catch (Exception ex) {
                Logger.getLogger(PracownikController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}