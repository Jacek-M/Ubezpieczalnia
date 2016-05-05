
package ubezpieczalnia.model;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ubezpieczalnia.entities.Oddzial;
/**
 *
 * @author layfl
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
        
    }
    
    
}
