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
import ubezpieczalnia.entities.Klient;

/**
 *
 * @author layfl
 */
@Stateless
@LocalBean
public class KlientEJB {

    @PersistenceContext(unitName = "Prodzekt-ejbPU")
    private EntityManager em;

    public List<Klient> findKlienci() {
        TypedQuery<Klient> query = em.createNamedQuery("Klient.findAll", Klient.class);
        return query.getResultList();
    }

    public Klient findKlientById(int id) throws Exception {
        List<Klient> query = em.createNamedQuery("Klient.findByAdresId", Klient.class).setParameter("klientId", id).getResultList();
        if (query.get(0) != null) {
            return query.get(0);
        } else {
            throw new Exception("Cannot find Klient with id = " + id);
        }
    }

    public Klient addNewKlient(Klient klient) {
        em.persist(klient);
        return klient;
    }

}
