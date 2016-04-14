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
import javax.persistence.TypedQuery;
import ubezpieczalnia.entities.Adres;
/**
 *
 * @author layfl
 */
@Stateless
@LocalBean
public class AdresEJB {
   
    @PersistenceContext(unitName = "Prodzekt-ejbPU")
    private EntityManager em; 
    
    
    public List<Adres> findAdresy() {
        TypedQuery<Adres> query = em.createNamedQuery("Adres.findAll", Adres.class);
        return query.getResultList();
    }
    
    
    public Adres addNewAdres(Adres adr) {
        em.persist(adr);
        return adr;
    }
    
}
