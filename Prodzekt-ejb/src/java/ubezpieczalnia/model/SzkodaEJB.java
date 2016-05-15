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
        if(found != null) {
            Logger.getLogger("INFO").log(Level.INFO, "ZNALAZLO, NADPISUJE!");
            found.setSzkodaDataZakonczenia(object.getSzkodaDataZakonczenia());
            found.setSzkodaDataZdarzenia(object.getSzkodaDataZdarzenia());
            found.setSzkodaOpis(object.getSzkodaOpis());
            found.setSzkodaStatus(object.getSzkodaStatus());
            found.setSzkodaTyp(object.getSzkodaTyp());
            
            found.setSzkodaSamochodZastepczyIdFk(object.getSzkodaSamochodZastepczyIdFk());
            found.setSzkodaUczestnikIdFk(object.getSzkodaUczestnikIdFk());
            found.setSzkodaUmowaIdFk(object.getSzkodaUmowaIdFk());
            found.setSzkodaZakladIdFk(object.getSzkodaZakladIdFk());
            em.flush();
        }
    }
}
