/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import ubezpieczalnia.entities.Szkoda;
import ubezpieczalnia.entities.Wycena;
import ubezpieczalnia.model.SzkodaEJB;
import ubezpieczalnia.utils.SessionManager;

/**
 *
 * @author Jacek
 */
@ManagedBean
@RequestScoped
public class SzkodaController implements AbstractController<Szkoda> {

    @ManagedProperty("#{umowaController}")
    private UmowaController umowaController;

    @ManagedProperty(value = "#{samochodZastController}")
    private SamochodZastController samochodZastController;

    @ManagedProperty(value = "#{zakladController}")
    private ZakladController zakladController;

    @ManagedProperty(value = "#{uczestnikController}")
    private UczestnikController uczestnikController;

    @ManagedProperty(value = "#{pracownikController}")
    private PracownikController pracownikController;

    @EJB
    private SzkodaEJB szkodaEJB;

    private Szkoda szkoda = new Szkoda();
    private List<Szkoda> szkodaList = new ArrayList<>();
    private List<SelectItem> szkodaSelectList = new ArrayList<>();

    public PracownikController getPracownikController() {
        return pracownikController;
    }

    public void setPracownikController(PracownikController pracownikController) {
        this.pracownikController = pracownikController;
    }

    public List<SelectItem> getSzkodaSelectList() {
        if (this.findAll().size() <= 0) {
            this.szkodaSelectList.add(new SelectItem(-1, "Brak szkód"));
            return this.szkodaSelectList;
        } else {
            this.szkodaSelectList.add(new SelectItem(-1, "-- WYBIERZ SZKODĘ --"));
            for (Szkoda szkodaList1 : this.szkodaList) {
                if (szkodaList1.getSzkodaStatus().equals("DO WYCENY")) {
                    String text = szkodaList1.getSzkodaId() + "| " + szkodaList1.getSzkodaUmowaIdFk().getUmowaKlientIdFk().getKlientImie() + " " + szkodaList1.getSzkodaUmowaIdFk().getUmowaKlientIdFk().getKlientNazwisko();
                    this.szkodaSelectList.add(new SelectItem(szkodaList1.getSzkodaId(), text));
                }
            }
        }
        return szkodaSelectList;
    }

    public ArrayList<Wycena> getWycenaList() {
        ArrayList<Wycena> wycenaList = new ArrayList<>();

        for (Wycena wycena : this.szkoda.getWycenaCollection()) {
            if (this.szkoda.getSzkodaUmowaIdFk().getUmowaRodzajUbezpieczeniaIdFk().getRodzajUbezpieczeniaRodzaj().contains("AC")) {
                if (!this.szkoda.getSzkodaStatus().equals("NOWA") && !this.szkoda.getSzkodaStatus().equals("DO WYCENY")) {
                    wycenaList.add(wycena);
                }
            } else {
                if (!this.szkoda.getSzkodaTyp().equals("WINNY")) {
                    if (!this.szkoda.getSzkodaStatus().equals("NOWA") && !this.szkoda.getSzkodaStatus().equals("DO WYCENY")) {
                        wycenaList.add(wycena);
                    }
                }
            }
        }

        return wycenaList;
    }

    public void setSzkodaSelectList(List<SelectItem> szkodaSelectList) {
        this.szkodaSelectList = szkodaSelectList;
    }

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
        try {
            umowaController.findById();
            this.szkoda.setSzkodaUmowaIdFk(umowaController.getUmowa());
            if (uczestnikController.getUczestnik().getUczestnikImie().length() > 0 && uczestnikController.getUczestnik().getUczestnikNazwisko().length() > 0) {
                uczestnikController.addNew();
                this.szkoda.setSzkodaUczestnikIdFk(uczestnikController.getUczestnik());
            }
            this.szkoda.setSzkodaStatus("NOWA");
            this.addNew();
        } catch (Exception ex) {
            Logger.getLogger(SzkodaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return PageController.getPage("/adminPages/incidents/incidents.xhtml");
    }

    public String registerIncidentByClient() {
        try {
            umowaController.findById();
            if (samochodZastController.getSamochodZastepczy().getSamochodZastepczyId() > 0) {
                if (umowaController.getUmowa().getUmowaRodzajUbezpieczeniaIdFk().getRodzajUbezpieczeniaCzyZastepczy() != 1) {
                    SessionManager.addToSession("REGISTER_ERROR", "W Twoim ubezpieczeniu nie można wybrać auta zastępczego!");
                    return PageController.getPage("/customerPages/incidents/incidentsAdd.xhtml");
                }
                samochodZastController.findById();
                this.szkoda.setSzkodaSamochodZastepczyIdFk(samochodZastController.getSamochodZastepczy());
            } else {
                this.szkoda.setSzkodaSamochodZastepczyIdFk(null);
            }

            this.szkoda.setSzkodaUmowaIdFk(umowaController.getUmowa());
            if (uczestnikController.getUczestnik().getUczestnikImie().length() > 0 && uczestnikController.getUczestnik().getUczestnikNazwisko().length() > 0) {
                uczestnikController.addNew();
                this.szkoda.setSzkodaUczestnikIdFk(uczestnikController.getUczestnik());
            }
            this.szkoda.setSzkodaStatus("NOWA");
            this.addNew();

        } catch (Exception e) {
            Logger.getLogger(SzkodaController.class.getName()).log(Level.SEVERE, null, e);
        }
        return PageController.getPage("/customerPages/customer/customerView.xhtml");
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
        try {
            szkodaEJB.update(this.szkoda);
        } catch (Exception ex) {
            Logger.getLogger(SzkodaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return PageController.getPage("/adminPages/incidents/incidents.xhtml");
    }

    public String acceptIncident() {
        System.out.println("this.szkoda.getSzkodaTyp() " + this.szkoda.getSzkodaTyp());
        if (this.szkoda.getSzkodaStatus().equals("NOWA")) {
            this.szkoda.setSzkodaStatus("DO WYCENY");
            return this.update();
        }
        return PageController.getPage("/adminPages/incidents/incidents.xhtml");
    }

    @Override
    public String delete() {
        szkodaEJB.delete(this.szkoda);
        return PageController.getPage("/adminPages/incidents/incidents.xhtml");
    }

//    @PostConstruct
    public void receivedPost() {
        szkodaEJB.clearCache();
        Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (requestParams.get("post_id") != null) {
            this.szkoda.setSzkodaId(Integer.parseInt(requestParams.get("post_id")));
            System.out.println(requestParams.get("post_id"));
            try {
                this.findById();

                if (requestParams.get("post_type") != null) {
                    if (this.szkoda.getSzkodaStatus().equals("WYCENIONE")) {
                        if (Integer.parseInt(requestParams.get("post_type")) == 10) {
                            System.out.println("ZMIENIAM DO NAPRAWYYYYYYYYYYYY");
                            this.szkoda.setSzkodaStatus("DO NAPRAWY");
                            this.update();
                        } else if (Integer.parseInt(requestParams.get("post_type")) == 11) {
                            this.szkoda.setSzkodaStatus("DO WYPŁATY");
                            System.out.println("ZMIENIAM DO WYPŁATYYYYYYYY");
                            this.update();
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(SzkodaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (SessionManager.getObjectFromSession("id") != null) {
            try {
                int id = (Integer) SessionManager.getObjectFromSession("id");
                if (id > 0) {
                    pracownikController.getPracownik().setPracownikId(id);
                    pracownikController.findById();
                }
            } catch (Exception ex) {
                Logger.getLogger(SzkodaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (requestParams.get("post_id") != null && requestParams.get("post_type") != null) {
            if (requestParams.get("post_type").equals("2")) {
                getRepair();
            } else if (requestParams.get("post_type").equals("3")) {
                endRepair();
            }
        }
    }

    public ArrayList<Szkoda> getSzkodaZakladZlecenia() {
        ArrayList<Szkoda> temp = new ArrayList<>();
        for (Szkoda szkoda : this.getSzkodaList()) {
            if (szkoda != null && szkoda.getSzkodaZakladIdFk() != null && szkoda.getSzkodaZakladIdFk().getZakladId() == pracownikController.getPracownik().getPracownikZakladIdFk().getZakladId() && szkoda.getSzkodaStatus().equals("W NAPRAWIE")) {
                temp.add(szkoda);
            }
        }
        return temp;
    }

    public ArrayList<Szkoda> getSzkodaZakladZakonczone() {
        ArrayList<Szkoda> temp = new ArrayList<>();
        for (Szkoda szkoda : this.getSzkodaList()) {
            if (szkoda != null && szkoda.getSzkodaZakladIdFk() != null && szkoda.getSzkodaZakladIdFk().getZakladId() == pracownikController.getPracownik().getPracownikZakladIdFk().getZakladId() && szkoda.getSzkodaStatus().equals("NAPRAWIONO")) {
                temp.add(szkoda);
            }
        }
        return temp;
    }

    public ArrayList<Szkoda> getSzkodaToRepair() {
        ArrayList<Szkoda> temp = new ArrayList<>();

        for (Szkoda szkoda : this.getSzkodaList()) {
            if (szkoda != null && szkoda.getSzkodaStatus().equals("DO NAPRAWY")) {
                temp.add(szkoda);
            }
        }
        return temp;
    }

    private void getRepair() {
        try {
            this.szkoda.setSzkodaZakladIdFk(pracownikController.getPracownik().getPracownikZakladIdFk());
            this.szkoda.setSzkodaStatus("W NAPRAWIE");
            szkodaEJB.addZaklad(szkoda);

        } catch (Exception ex) {
            Logger.getLogger(SzkodaController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void endRepair() {
        try {
            this.szkoda.setSzkodaZakladIdFk(pracownikController.getPracownik().getPracownikZakladIdFk());
            this.szkoda.setSzkodaStatus("NAPRAWIONO");
            this.szkoda.setSzkodaDataZakonczenia(new Date());
            szkodaEJB.addZaklad(szkoda);

        } catch (Exception ex) {
            Logger.getLogger(SzkodaController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
