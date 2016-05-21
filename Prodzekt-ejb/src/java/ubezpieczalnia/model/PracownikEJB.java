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
        Pracownik found = em.find(Pracownik.class, object.getPracownikId());
        Logger.getLogger("INFO").log(Level.INFO, "UPDATE [PRACOWNIK]");
        if (found != null) {
            System.out.println("Not null");
            found.setPracownikImie(object.getPracownikImie());
            found.setPracownikNazwisko(object.getPracownikNazwisko());
            found.setPracownikDataZatrudnienia(object.getPracownikDataZatrudnienia());
            found.setPracownikTyp(object.getPracownikTyp());
            found.setPracownikWynagrodzenie(object.getPracownikWynagrodzenie());
            found.getPracownikAdresIdFk().setAdresKodPocztowy(object.getPracownikAdresIdFk().getAdresKodPocztowy());
            found.getPracownikAdresIdFk().setAdresMiejscowosc(object.getPracownikAdresIdFk().getAdresMiejscowosc());
            found.getPracownikAdresIdFk().setAdresPoczta(object.getPracownikAdresIdFk().getAdresPoczta());
            found.getPracownikAdresIdFk().setAdresTelefon(object.getPracownikAdresIdFk().getAdresTelefon());
            found.getPracownikAdresIdFk().setAdresUlica(object.getPracownikAdresIdFk().getAdresUlica());

            if (object.getPracownikOddzialIdFk() != null && object.getPracownikOddzialIdFk().getOddzialId() != null) {
                found.setPracownikOddzialIdFk(object.getPracownikOddzialIdFk());
                found.setPracownikZakladIdFk(null);
            }
            if (object.getPracownikZakladIdFk() != null && object.getPracownikZakladIdFk().getZakladId() != null) {
                found.setPracownikZakladIdFk(object.getPracownikZakladIdFk());
                found.setPracownikOddzialIdFk(null);
            }
            em.flush();
        }
    }

}
