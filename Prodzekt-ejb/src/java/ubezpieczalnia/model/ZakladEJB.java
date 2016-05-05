/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.model;

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
        
    }
    
}
