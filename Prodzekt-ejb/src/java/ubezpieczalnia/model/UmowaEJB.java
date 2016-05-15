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
import ubezpieczalnia.entities.Umowa;

/**
 *
 * @author layfl
 */
@Stateless
@LocalBean
public class UmowaEJB extends AbstractModel<Umowa> {

    @PersistenceContext(unitName = "Prodzekt-ejbPU")
    private EntityManager em;
    
    public UmowaEJB() {
        super(Umowa.class);
    }

    @Override
    public void update(Umowa object) {
        //Tylko zmiana statusu
        Umowa found = em.find(Umowa.class, object.getUmowaId());
        Logger.getLogger("INFO").log(Level.INFO, "UPDATE [UMOWA]");
        if(found != null) {
            Logger.getLogger("INFO").log(Level.INFO, "ZNALAZLO, NADPISUJE!");
            found.setUmowaStatus(object.getUmowaStatus());
            em.flush();
        }
    }
}
