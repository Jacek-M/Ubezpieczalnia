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
import ubezpieczalnia.entities.Ustawienia;

/**
 *
 * @author layfl
 */
@Stateless
@LocalBean
public class UstawieniaEJB extends AbstractModel<Ustawienia> {

    @PersistenceContext(unitName = "Prodzekt-ejbPU")
    private EntityManager em;
    

    public UstawieniaEJB() {
        super(Ustawienia.class);
    }
    
    
    public Ustawienia findUstawieniaByKlucz(String klucz) throws Exception {
         TypedQuery<Ustawienia> queryResult = em.createNamedQuery("Ustawienia.findByUstawieniaKlucz", Ustawienia.class).setParameter("ustawieniaKlucz", klucz);
         List<Ustawienia> ustawieniaList = queryResult.getResultList();
         if(ustawieniaList != null && ustawieniaList.size() > 0 && ustawieniaList.get(0) != null) {
             return ustawieniaList.get(0);
         }
         throw new Exception("Cannot find Ustawienia with key=" + klucz);
    }

    @Override
    public void update(Ustawienia object) {
        em.getTransaction().begin();
        Ustawienia ustawieniaFromDb = em.find(object.getClass(), object.getUstawieniaId());
        ustawieniaFromDb.setUstawieniaWartosc(object.getUstawieniaWartosc());
        em.getTransaction().commit();
    }
    
}
