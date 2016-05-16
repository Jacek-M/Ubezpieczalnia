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
import ubezpieczalnia.entities.Klient;

/**
 *
 * @author layfl
 */
@Stateless
@LocalBean
public class KlientEJB extends AbstractModel<Klient>  {

    @PersistenceContext(unitName = "Prodzekt-ejbPU")
    private EntityManager em;
    
    
    public KlientEJB() {
        super(Klient.class);
    }

    @Override
    public void update(Klient object) {
        //update tylko znizki
        Klient found = em.find(Klient.class, object.getKlientId());
        Logger.getLogger("INFO").log(Level.INFO, "UPDATE [KLIENT]");
        if(found != null) {
            Logger.getLogger("INFO").log(Level.INFO, "ZNALAZLO, NADPISUJE!");
            found.setKlientProcentZnizki(object.getKlientProcentZnizki());
            em.flush();
        }
    }

}
