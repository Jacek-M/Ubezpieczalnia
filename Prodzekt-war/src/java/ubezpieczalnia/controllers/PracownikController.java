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
import ubezpieczalnia.entities.Konto;

import ubezpieczalnia.entities.Pracownik;
import ubezpieczalnia.model.PracownikEJB;
import ubezpieczalnia.utils.SessionManager;

/**
 *
 * @author layfl
 */
@ManagedBean
@RequestScoped
public class PracownikController implements AbstractController<Pracownik> {

    @ManagedProperty(value = "#{adresController}")
    private AdresController adresController;

    @ManagedProperty(value = "#{kontoController}")
    private KontoController kontoController;

    @ManagedProperty(value = "#{oddzialController}")
    private OddzialController oddzialController;

    @ManagedProperty(value = "#{zakladController}")
    private ZakladController zakladController;

    @EJB
    private PracownikEJB pracownikEJB;

    private Pracownik pracownik = new Pracownik();
    private List<Pracownik> pracownikList = new ArrayList<>();
    private List<SelectItem> pracownikSelectList = new ArrayList<>();

    public List<SelectItem> getPracownikSelectList() {
        if (this.findAll().size() <= 0) {
            this.pracownikSelectList.add(new SelectItem(-1, "BRAK PRACOWNIKÓW"));
            return this.pracownikSelectList;
        } else {
            this.pracownikSelectList.add(new SelectItem(-1, "WYBIERZ PRACOWNIKA"));
            for (Pracownik pracownikList1 : this.pracownikList) {
                String text = pracownikList1.getPracownikImie() + " " + pracownikList1.getPracownikNazwisko() + " " + pracownikList1.getPracownikTyp();
                this.pracownikSelectList.add(new SelectItem(pracownikList1.getPracownikId(), text));
            }
        }
        return pracownikSelectList;
    }

    public void setPracownikSelectList(List<SelectItem> pracownikSelectList) {
        this.pracownikSelectList = pracownikSelectList;
    }

    public OddzialController getOddzialController() {
        return oddzialController;
    }

    public void setOddzialController(OddzialController oddzialController) {
        this.oddzialController = oddzialController;
    }

    public ZakladController getZakladController() {
        return zakladController;
    }

    public void setZakladController(ZakladController zakladController) {
        this.zakladController = zakladController;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }

    public List<Pracownik> getPracownikList() {
        this.findAll();
        return pracownikList;
    }

    public void setPracownikList(List<Pracownik> pracownikList) {
        this.pracownikList = pracownikList;
    }

    public AdresController getAdresController() {
        return adresController;
    }

    public void setAdresController(AdresController adresController) {
        this.adresController = adresController;
    }

    public KontoController getKontoController() {
        return kontoController;
    }

    public void setKontoController(KontoController kontoController) {
        this.kontoController = kontoController;
    }

    public String registerWorker() {
        Konto konto = kontoController.getKonto();
        if (kontoController.checkLogin(konto.getKontoLogin(), konto.getKontoHaslo()) == null) {
            konto.setKontoUprawnienia(this.pracownik.getPracownikTyp());
            kontoController.setKonto(konto);
            kontoController.addNew();
            adresController.addNew();

            pracownik.setPracownikAdresIdFk(adresController.getAdres());
            pracownik.setPracownikKontoIdFk(kontoController.getKonto());

            if (oddzialController.getOddzial() != null && oddzialController.getOddzial().getOddzialId() > 0) {
                pracownik.setPracownikOddzialIdFk(oddzialController.getOddzial());
                pracownik.setPracownikZakladIdFk(null);
            } else if (zakladController.getZaklad() != null && zakladController.getZaklad().getZakladId() > 0) {
                pracownik.setPracownikOddzialIdFk(null);
                pracownik.setPracownikZakladIdFk(zakladController.getZaklad());
            } else {
                pracownik.setPracownikOddzialIdFk(null);
                pracownik.setPracownikZakladIdFk(null);
            }
            pracownik = pracownikEJB.addNew(this.pracownik);
        } else {
            SessionManager.addToSession("REGISTER_ERROR", "Taki login już istnieje!");
            return PageController.getPage("/adminPages/employees/employeesAdd.xhtml");
        }

        return PageController.getPage("/adminPages/employees/employees.xhtml");
    }

    @Override
    public List<Pracownik> findAll() {
        pracownikList = pracownikEJB.findAll();
        return pracownikList;
    }

    @Override
    public Pracownik findById() throws Exception {
        pracownik = pracownikEJB.findById(this.pracownik.getPracownikId());
        return pracownik;
    }

    @Override
    public String addNew() {
        pracownikEJB.addNew(this.pracownik);
        return PageController.getPage("/adminPages/employees/employees.xhtml");
    }

    @Override
    public String update() {
        try {
            this.pracownik.setPracownikAdresIdFk(adresController.getAdres());
            if (oddzialController.getOddzial().getOddzialId() > 0) {
                oddzialController.findById();
                this.pracownik.setPracownikOddzialIdFk(oddzialController.getOddzial());
                this.pracownik.setPracownikZakladIdFk(null);
            }
            if (zakladController.getZaklad().getZakladId() > 0) {
                zakladController.findById();
                this.pracownik.setPracownikZakladIdFk(zakladController.getZaklad());
                this.pracownik.setPracownikOddzialIdFk(null);
            }
            pracownikEJB.update(this.pracownik);
            this.findById();

            Konto konto = new Konto();
            konto.setKontoId(this.pracownik.getPracownikKontoIdFk().getKontoId());
            kontoController.setKonto(konto);
            kontoController.findById();
            kontoController.getKonto().setKontoUprawnienia(this.pracownik.getPracownikTyp());
            kontoController.update();

            return PageController.getPage("/Prodzekt-war/faces/adminPages/employees/employees.xhtml");
        } catch (Exception ex) {
            Logger.getLogger(PracownikController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return PageController.getPage("/Prodzekt-war/faces/adminPages/employees/employees.xhtml");
    }

    @Override
    public String delete() {
        pracownikEJB.delete(this.pracownik);
        return PageController.getPage("/adminPages/employees/employees.xhtml");

    }

    public void receivedPost() {
        pracownikEJB.clearCache();
        Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        if (requestParams.get("post_id") != null) {
            this.pracownik.setPracownikId(Integer.parseInt(requestParams.get("post_id")));
            try {
                this.findById();
                adresController.setAdres(this.pracownik.getPracownikAdresIdFk());
                zakladController.setZaklad(this.pracownik.getPracownikZakladIdFk());
                oddzialController.setOddzial(this.pracownik.getPracownikOddzialIdFk());
            } catch (Exception ex) {
                Logger.getLogger(PracownikController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
