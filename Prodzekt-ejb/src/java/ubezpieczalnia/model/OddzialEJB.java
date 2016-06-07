
package ubezpieczalnia.model;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ubezpieczalnia.entities.Oddzial;
/**
 *
 * @author Filip
 */
@Stateless
@LocalBean
public class OddzialEJB extends AbstractModel<Oddzial> {
    @PersistenceContext(unitName = "Prodzekt-ejbPU")
    private EntityManager em;
    
    public OddzialEJB() {
        super(Oddzial.class);
    }

    @Override
    public void update(Oddzial object) {
        Oddzial found = em.find(Oddzial.class, object.getOddzialId());
        Logger.getLogger("INFO").log(Level.INFO, "UPDATE [ODDZIAL]");
        if(found != null) {
            Logger.getLogger("INFO").log(Level.INFO, "ZNALAZLO, NADPISUJE!");
            found.setOddzialTelefon(object.getOddzialTelefon());
            found.getOddzialAdresIdFk().setAdresKodPocztowy(object.getOddzialAdresIdFk().getAdresKodPocztowy());
            found.getOddzialAdresIdFk().setAdresMiejscowosc(object.getOddzialAdresIdFk().getAdresMiejscowosc());
            found.getOddzialAdresIdFk().setAdresPoczta(object.getOddzialAdresIdFk().getAdresPoczta());
            found.getOddzialAdresIdFk().setAdresTelefon(object.getOddzialAdresIdFk().getAdresTelefon());
            found.getOddzialAdresIdFk().setAdresUlica(object.getOddzialAdresIdFk().getAdresUlica());
            em.flush();
        }
    }
    
    
}
