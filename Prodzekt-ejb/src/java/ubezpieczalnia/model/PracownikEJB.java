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
import ubezpieczalnia.entities.Pracownik;

/**
 *
 * @author layfl
 */
@Stateless
@LocalBean
public class PracownikEJB extends AbstractModel<Pracownik> {

    @PersistenceContext(unitName = "Prodzekt-ejbPU")
    private EntityManager em;

    public PracownikEJB() {
        super(Pracownik.class);
    }

    @Override
    public void update(Pracownik object) {
            Logger.getLogger("INFO").log(Level.INFO, "EDYTUJE PRACOWNIKA");
            Pracownik found = em.find(Pracownik.class, object.getPracownikId());
            found.setPracownikImie(object.getPracownikImie());
            found.setPracownikNazwisko(object.getPracownikNazwisko());
            found.getPracownikAdresIdFk().setAdresMiejscowosc(object.getPracownikAdresIdFk().getAdresMiejscowosc());
            em.flush();
    }

}
