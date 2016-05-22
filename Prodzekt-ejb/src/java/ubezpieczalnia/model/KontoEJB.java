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
import ubezpieczalnia.entities.Konto;

/**
 *
 * @author Jacek
 */
@Stateless
@LocalBean
public class KontoEJB extends AbstractModel<Konto> {

    @PersistenceContext(unitName = "Prodzekt-ejbPU")
    private EntityManager em;

    public KontoEJB() {
        super(Konto.class);
    }

    public Konto checkoutLogin(String login, String pass) throws Exception {
        if (login == null || pass == null || login.length() <= 0 || pass.length() <= 0) {
            return null;
        }
        List<Konto> query = em.createNamedQuery("Konto.checkoutLogin", Konto.class).
                setParameter("kontoLogin", login).
                setParameter("kontoHaslo", pass).getResultList();
        if (query != null && query.size() > 0 && query.get(0) != null) {
            return query.get(0);
        }
        throw new Exception("Cannot find login");
    }

    @Override
    public void update(Konto object) {
        Konto found = em.find(Konto.class, object.getKontoId());
        if (object.getKontoUprawnienia() != null && object.getKontoUprawnienia().length() > 0) {
            found.setKontoUprawnienia(object.getKontoUprawnienia());
        }
        em.flush();
    }

    public Konto refresh(Konto konto) {
        konto = em.find(Konto.class, konto.getKontoId());
        em.refresh(konto);
        em.flush();
        return konto;
    }

}
