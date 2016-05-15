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
import ubezpieczalnia.entities.SamochodZastepczy;

/**
 *
 * @author layfl
 */
@Stateless
@LocalBean
public class SamochodZastepczyEJB extends AbstractModel<SamochodZastepczy> {

    @PersistenceContext(unitName = "Prodzekt-ejbPU")
    private EntityManager em;

    public SamochodZastepczyEJB() {
        super(SamochodZastepczy.class);
    }

    @Override
    public void update(SamochodZastepczy object) {
        SamochodZastepczy found = em.find(SamochodZastepczy.class, object.getSamochodZastepczyId());
        Logger.getLogger("INFO").log(Level.INFO, "UPDATE [SAMOCHOD]");
        if (found != null) {
            Logger.getLogger("INFO").log(Level.INFO, "ZNALAZLO, NADPISUJE!");
            found.setSamochodZastepczyCzyDostepny(object.getSamochodZastepczyCzyDostepny());
            found.setSamochodZastepczyMarka(object.getSamochodZastepczyMarka());
            found.setSamochodZastepczyModel(object.getSamochodZastepczyModel());
            em.flush();
        }
    }
}
