/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.model;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ubezpieczalnia.entities.Adres;

/**
 *
 * @author layfl
 */
@Stateless
@LocalBean
public class AdresEJB extends AbstractModel<Adres> {
    
    @PersistenceContext(unitName = "Prodzekt-ejbPU")
    private EntityManager em;
    
    public AdresEJB() {
        super(Adres.class);
    }
    
    public List<Adres> findAllAdres() {
        
        return super.findAll();
    }
    
    public Adres findAdresById(int id) throws Exception {
        return super.findById(id);
    }


    @Override
    public void update(Adres object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
