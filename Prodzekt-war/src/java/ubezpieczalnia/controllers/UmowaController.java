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
import ubezpieczalnia.entities.Umowa;
import ubezpieczalnia.model.UmowaEJB;

/**
 *
 * @author Jacek
 */
@ManagedBean
@RequestScoped
public class UmowaController implements AbstractController<Umowa> {

    @ManagedProperty(value = "#{pracownikController}")
    private PracownikController pracownikController;

    @ManagedProperty(value = "#{registerController}")
    private RegisterController registerController;

    @ManagedProperty(value = "#{pojazdController}")
    private PojazdController pojazdController;

    @ManagedProperty(value = "#{rodzajUbezController}")
    private RodzajUbezController rodzajUbezController;

    @EJB
    private UmowaEJB umowaEJB;

    private Umowa umowa = new Umowa();
    private List<Umowa> umowaList = new ArrayList<>();
    private List<SelectItem> umowaSelectList = new ArrayList<>();

    public List<SelectItem> getUmowaSelectList() {
        if (this.findAll().size() <= 0) {
            this.umowaSelectList.add(new SelectItem(-1, "Brak umów"));
            return this.umowaSelectList;
        } else {
            this.umowaSelectList.add(new SelectItem(-1, "-- WYBIERZ UMOWE --"));
            for (Umowa umowaList1 : this.umowaList) {
                String text = umowaList1.getUmowaId().toString() + "| " + umowaList1.getUmowaKlientIdFk().getKlientImie() + " " + umowaList1.getUmowaKlientIdFk().getKlientNazwisko() + "| " + umowaList1.getUmowaDataWystawienia().toString();
                this.umowaSelectList.add(new SelectItem(umowaList1.getUmowaId(), text));
            }
        }
        return umowaSelectList;
    }

    public void setUmowaSelectList(List<SelectItem> umowaSelectList) {
        this.umowaSelectList = umowaSelectList;
    }

    public PracownikController getPracownikController() {
        return pracownikController;
    }

    public void setPracownikController(PracownikController pracownikController) {
        this.pracownikController = pracownikController;
    }

    public RegisterController getRegisterController() {
        return registerController;
    }

    public void setRegisterController(RegisterController registerController) {
        this.registerController = registerController;
    }

    public PojazdController getPojazdController() {
        return pojazdController;
    }

    public void setPojazdController(PojazdController pojazdController) {
        this.pojazdController = pojazdController;
    }

    public RodzajUbezController getRodzajUbezController() {
        return rodzajUbezController;
    }

    public void setRodzajUbezController(RodzajUbezController rodzajUbezController) {
        this.rodzajUbezController = rodzajUbezController;
    }

    public Umowa getUmowa() {
        return umowa;
    }

    public void setUmowa(Umowa umowa) {
        this.umowa = umowa;
    }

    public List<Umowa> getUmowaList() {
        this.findAll();
        return umowaList;
    }

    public void setUmowaList(List<Umowa> umowaList) {
        this.umowaList = umowaList;
    }

    public String registerAgreement() {
        return "";
    }

    @Override
    public List<Umowa> findAll() {
        umowaList = umowaEJB.findAll();
        return umowaList;
    }

    @Override
    public Umowa findById() throws Exception {
        umowa = umowaEJB.findById(this.umowa.getUmowaId());
        return umowa;
    }

    @Override
    public String addNew() {
        umowaEJB.addNew(this.umowa);
        return PageController.getPage("/adminPages/agreements/agreements.xhtml");
    }

    @Override
    public String update() {
        this.umowa.setUmowaKlientIdFk(registerController.getKlient());
        this.umowa.setUmowaPojazdIdFk(pojazdController.getPojazd());
        this.umowa.setUmowaPracownikIdFk(pracownikController.getPracownik());
        this.umowa.setUmowaRodzajUbezpieczeniaIdFk(rodzajUbezController.getRodzajUbez());
        umowaEJB.update(this.umowa);
        return PageController.getPage("/adminPages/agreements/agreements.xhtml");

    }

    @Override
    public String delete() {
        umowaEJB.delete(this.umowa);
        return PageController.getPage("/adminPages/agreements/agreements.xhtml");

    }

    @PostConstruct
    public void receivedPost() {
        Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        if (requestParams.get("post_id") != null) {
            System.out.println(requestParams.get("post_id"));
            this.umowa.setUmowaId(Integer.parseInt(requestParams.get("post_id")));
            try {
                this.findById();
                registerController.setKlient(this.umowa.getUmowaKlientIdFk());
                pojazdController.setPojazd(this.umowa.getUmowaPojazdIdFk());
                pracownikController.setPracownik(this.umowa.getUmowaPracownikIdFk());
                rodzajUbezController.setRodzajUbez(this.umowa.getUmowaRodzajUbezpieczeniaIdFk());
            } catch (Exception ex) {
                Logger.getLogger(UmowaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (requestParams.get("post_type") != null) {
            System.out.println("requestParams.get(\"post_type\") " + requestParams.get("post_type"));
            acceptAgreement();
        }
    }

    private void acceptAgreement() {
        if (this.umowa.getUmowaStatus().equals("OCZEKUJE NA AKCEPTACJĘ")) {
            System.out.println("(this.umowa.getUmowaStatus().equals(\"OCZEKUJE NA AKCEPTACJĘ\")  TRUE");
            this.umowa.setUmowaStatus("ZAAKCEPTOWANA");
            umowaEJB.update(this.umowa);
        }
    }

}
