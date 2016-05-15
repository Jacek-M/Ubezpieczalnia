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
import ubezpieczalnia.entities.Uczestnik;

/**
 *
 * @author layfl
 */
@Stateless
@LocalBean
public class UczestnikEJB extends AbstractModel<Uczestnik> {

    @PersistenceContext(unitName = "Prodzekt-ejbPU")
    private EntityManager em;

    public UczestnikEJB() {
        super(Uczestnik.class);
    }

    @Override
    public void update(Uczestnik object) {
        Uczestnik found = em.find(Uczestnik.class, object.getUczestnikId());
        Logger.getLogger("INFO").log(Level.INFO, "UPDATE [UCZESTNIK]");
        if (found != null) {
            Logger.getLogger("INFO").log(Level.INFO, "ZNALAZLO, NADPISUJE!");
            found.setUczestnikImie(object.getUczestnikImie());
            found.setUczestnikNazwisko(object.getUczestnikNazwisko());
            found.setUczestnikNumerKonta(object.getUczestnikNumerKonta());
            em.flush();
        }
    }
}
