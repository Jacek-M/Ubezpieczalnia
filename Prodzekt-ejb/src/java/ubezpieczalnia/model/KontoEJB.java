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
import ubezpieczalnia.entities.Konto;

/**
 *
 * @author Jacek
 */
@Stateless
@LocalBean
public class KontoEJB {

    @PersistenceContext(unitName = "Prodzekt-ejbPU")
    private EntityManager em;

    public List<Konto> findKonto() {
        TypedQuery<Konto> query = em.createNamedQuery("Konto.findAll", Konto.class);
        return query.getResultList();
    }
    
    public Konto checkoutLogin(String login, String pass) throws Exception {
        List<Konto> query = em.createNamedQuery("Konto.checkoutLogin", Konto.class).setParameter("kontoLogin", login).setParameter("kontoHaslo", pass).getResultList();
        if(query.get(0) != null) {
            return query.get(0);
        }
        else throw new Exception("login error");
    }
}