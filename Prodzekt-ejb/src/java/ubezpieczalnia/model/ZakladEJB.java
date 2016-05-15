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
import ubezpieczalnia.entities.Zaklad;

/**
 *
 * @author layfl
 */
@Stateless
@LocalBean
public class ZakladEJB extends AbstractModel<Zaklad> {

    @PersistenceContext(unitName = "Prodzekt-ejbPU")
    private EntityManager em;
    
    
    public ZakladEJB() {
        super(Zaklad.class);
    }

    @Override
    public void update(Zaklad object) {
        Zaklad found = em.find(Zaklad.class, object.getZakladId());
        Logger.getLogger("INFO").log(Level.INFO, "UPDATE [ZAKLAD]");
        if(found != null) {
            Logger.getLogger("INFO").log(Level.INFO, "ZNALAZLO, NADPISUJE!");
            found.setZakladDataRozpoczeciaWsp(object.getZakladDataRozpoczeciaWsp());
            found.setZakladDataZakonczeniaWsp(object.getZakladDataZakonczeniaWsp());
            found.setZakladNazwa(object.getZakladNazwa());
            found.getZakladAdresIdFk().setAdresKodPocztowy(object.getZakladAdresIdFk().getAdresKodPocztowy());
            found.getZakladAdresIdFk().setAdresMiejscowosc(object.getZakladAdresIdFk().getAdresMiejscowosc());
            found.getZakladAdresIdFk().setAdresPoczta(object.getZakladAdresIdFk().getAdresPoczta());
            found.getZakladAdresIdFk().setAdresTelefon(object.getZakladAdresIdFk().getAdresTelefon());
            found.getZakladAdresIdFk().setAdresUlica(object.getZakladAdresIdFk().getAdresUlica());
            em.flush();
        }
    }
    
}
