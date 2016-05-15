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
import ubezpieczalnia.entities.RodzajUbezpieczenia;

/**
 *
 * @author layfl
 */
@Stateless
@LocalBean
public class RodzajUbezpieczeniaEJB extends AbstractModel<RodzajUbezpieczenia> {

    @PersistenceContext(unitName = "Prodzekt-ejbPU")
    private EntityManager em;

    public RodzajUbezpieczeniaEJB() {
        super(RodzajUbezpieczenia.class);
    }

    @Override
    public void update(RodzajUbezpieczenia object) {
        RodzajUbezpieczenia found = em.find(RodzajUbezpieczenia.class, object.getRodzajUbezpieczeniaId());
        Logger.getLogger("INFO").log(Level.INFO, "UPDATE [UBEZPIECZENIE]");
        if (found != null) {
            Logger.getLogger("INFO").log(Level.INFO, "ZNALAZLO, NADPISUJE!");
            found.setRodzajUbezpieczeniaCena(object.getRodzajUbezpieczeniaCena());
            found.setRodzajUbezpieczeniaCzyZastepczy(object.getRodzajUbezpieczeniaCzyZastepczy());
            found.setRodzajUbezpieczeniaNazwa(object.getRodzajUbezpieczeniaNazwa());
            found.setRodzajUbezpieczeniaOpis(object.getRodzajUbezpieczeniaOpis());
            found.setRodzajUbezpieczeniaRodzaj(object.getRodzajUbezpieczeniaRodzaj());
            em.flush();
        }
    }

}
