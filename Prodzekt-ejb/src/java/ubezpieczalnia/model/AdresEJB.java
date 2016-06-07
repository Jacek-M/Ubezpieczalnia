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
import javax.persistence.TypedQuery;
import ubezpieczalnia.entities.Adres;

/**
 *
 * @author Filip
 */
@Stateless
@LocalBean
public class AdresEJB extends AbstractModel<Adres> {

    @PersistenceContext(unitName = "Prodzekt-ejbPU")
    private EntityManager em;

    public AdresEJB() {
        super(Adres.class);
    }

    public Adres findByCityAndStreet(String street, String city, String code) throws Exception {

        TypedQuery<Adres> query = em.createNamedQuery("Adres.findAdresByCityAndStreet", Adres.class)
                .setParameter("adresUlica", street)
                .setParameter("adresMiejscowosc", city)
                .setParameter("adresKodPocztowy", code);
        if (query.getResultList().get(0) != null) {
            return query.getResultList().get(0);
        }
        throw new Exception("Cannot findByCityAndStreet(" + street + "," + city + "," + code + ")");
    }

    @Override
    public void update(Adres object) {
        Adres found = em.find(Adres.class, object.getAdresId());
        Logger.getLogger("INFO").log(Level.INFO, "UPDATE [ADRES]");
        if(found != null) {
            Logger.getLogger("INFO").log(Level.INFO, "ZNALAZLO, NADPISUJE!");
            found.setAdresMiejscowosc(object.getAdresMiejscowosc());
            found.setAdresKodPocztowy(object.getAdresKodPocztowy());
            found.setAdresUlica(object.getAdresUlica());
            found.setAdresPoczta(object.getAdresPoczta());
            found.setAdresTelefon(object.getAdresTelefon());
            em.flush();
        }
    }
}
