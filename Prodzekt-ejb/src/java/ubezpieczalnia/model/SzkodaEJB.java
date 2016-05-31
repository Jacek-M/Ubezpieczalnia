/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.model;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ubezpieczalnia.entities.Szkoda;

/**
 *
 * @author layfl
 */
@Stateless
@LocalBean
public class SzkodaEJB extends AbstractModel<Szkoda> {

    @PersistenceContext(unitName = "Prodzekt-ejbPU")
    private EntityManager em;

    public SzkodaEJB() {
        super(Szkoda.class);
    }

    @Override
    public void update(Szkoda object) {
        Szkoda found = em.find(Szkoda.class, object.getSzkodaId());
        Logger.getLogger("INFO").log(Level.INFO, "UPDATE [SZKODA]");
        if (found != null) {
            Logger.getLogger("INFO").log(Level.INFO, "ZNALAZLO, NADPISUJE!");
            if (object.getSzkodaDataZakonczenia() != null) {
                found.setSzkodaDataZakonczenia(object.getSzkodaDataZakonczenia());
            }
            if (object.getSzkodaDataZdarzenia() != null) {
                found.setSzkodaDataZdarzenia(object.getSzkodaDataZdarzenia());
            }
            if (object.getSzkodaOpis() != null) {
                found.setSzkodaOpis(object.getSzkodaOpis());
            }
            if (object.getSzkodaStatus() != null) {
                found.setSzkodaStatus(object.getSzkodaStatus());
            }
            if (object.getSzkodaTyp() != null) {
                found.setSzkodaTyp(object.getSzkodaTyp());
            }
            if (object.getSzkodaSamochodZastepczyIdFk() != null) {
                found.setSzkodaSamochodZastepczyIdFk(object.getSzkodaSamochodZastepczyIdFk());
            }
            if (object.getSzkodaUczestnikIdFk() != null) {
                found.setSzkodaUczestnikIdFk(object.getSzkodaUczestnikIdFk());
            }
            if (object.getSzkodaUmowaIdFk() != null) {
                found.setSzkodaUmowaIdFk(object.getSzkodaUmowaIdFk());
            }
            if (object.getSzkodaZakladIdFk() != null) {
                found.setSzkodaZakladIdFk(object.getSzkodaZakladIdFk());
            }
            em.flush();
        }
    }

    public void addZaklad(Szkoda object) {
        Szkoda found = em.find(Szkoda.class, object.getSzkodaId());
        Logger.getLogger("INFO").log(Level.INFO, "UPDATE [SZKODA]");
        if (found != null) {
            Logger.getLogger("INFO").log(Level.INFO, "ZNALAZLO, NADPISUJE!");
            found.setSzkodaStatus(object.getSzkodaStatus());
            found.setSzkodaZakladIdFk(object.getSzkodaZakladIdFk());
            found.setSzkodaDataZakonczenia(object.getSzkodaDataZakonczenia());

            em.flush();
        }
    }
}
