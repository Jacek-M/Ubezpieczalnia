/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jacek
 */
@Entity
@Table(name = "pracownik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pracownik.findAll", query = "SELECT p FROM Pracownik p"),
    @NamedQuery(name = "Pracownik.findByPracownikId", query = "SELECT p FROM Pracownik p WHERE p.pracownikId = :pracownikId"),
    @NamedQuery(name = "Pracownik.findByPracownikImie", query = "SELECT p FROM Pracownik p WHERE p.pracownikImie = :pracownikImie"),
    @NamedQuery(name = "Pracownik.findByPracownikNazwisko", query = "SELECT p FROM Pracownik p WHERE p.pracownikNazwisko = :pracownikNazwisko"),
    @NamedQuery(name = "Pracownik.findByPracownikWynagrodzenie", query = "SELECT p FROM Pracownik p WHERE p.pracownikWynagrodzenie = :pracownikWynagrodzenie"),
    @NamedQuery(name = "Pracownik.findByPracownikTyp", query = "SELECT p FROM Pracownik p WHERE p.pracownikTyp = :pracownikTyp"),
    @NamedQuery(name = "Pracownik.findByPracownikDataZatrudnienia", query = "SELECT p FROM Pracownik p WHERE p.pracownikDataZatrudnienia = :pracownikDataZatrudnienia"),
    @NamedQuery(name = "Pracownik.findByPracownikDataZwolnienia", query = "SELECT p FROM Pracownik p WHERE p.pracownikDataZwolnienia = :pracownikDataZwolnienia")})
public class Pracownik implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pracownik_id")
    private Integer pracownikId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "pracownik_imie")
    private String pracownikImie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "pracownik_nazwisko")
    private String pracownikNazwisko;
    @Column(name = "pracownik_wynagrodzenie")
    private Integer pracownikWynagrodzenie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "pracownik_typ")
    private String pracownikTyp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pracownik_data_zatrudnienia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pracownikDataZatrudnienia;
    @Column(name = "pracownik_data_zwolnienia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pracownikDataZwolnienia;
    @JoinColumn(name = "pracownik_adres_id_fk", referencedColumnName = "adres_id")
    @ManyToOne(optional = false)
    private Adres pracownikAdresIdFk;
    @JoinColumn(name = "pracownik_oddzial_id_fk", referencedColumnName = "oddzial_id")
    @ManyToOne
    private Oddzial pracownikOddzialIdFk;
    @JoinColumn(name = "pracownik_zaklad_id_fk", referencedColumnName = "zaklad_id")
    @ManyToOne
    private Zaklad pracownikZakladIdFk;
    @OneToMany(mappedBy = "ubezpieczeniePracownikIdFk")
    private Collection<Ubezpieczenie> ubezpieczenieCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wycenaPracownikIdFk")
    private Collection<Wycena> wycenaCollection;

    public Pracownik() {
    }

    public Pracownik(Integer pracownikId) {
        this.pracownikId = pracownikId;
    }

    public Pracownik(Integer pracownikId, String pracownikImie, String pracownikNazwisko, String pracownikTyp, Date pracownikDataZatrudnienia) {
        this.pracownikId = pracownikId;
        this.pracownikImie = pracownikImie;
        this.pracownikNazwisko = pracownikNazwisko;
        this.pracownikTyp = pracownikTyp;
        this.pracownikDataZatrudnienia = pracownikDataZatrudnienia;
    }

    public Integer getPracownikId() {
        return pracownikId;
    }

    public void setPracownikId(Integer pracownikId) {
        this.pracownikId = pracownikId;
    }

    public String getPracownikImie() {
        return pracownikImie;
    }

    public void setPracownikImie(String pracownikImie) {
        this.pracownikImie = pracownikImie;
    }

    public String getPracownikNazwisko() {
        return pracownikNazwisko;
    }

    public void setPracownikNazwisko(String pracownikNazwisko) {
        this.pracownikNazwisko = pracownikNazwisko;
    }

    public Integer getPracownikWynagrodzenie() {
        return pracownikWynagrodzenie;
    }

    public void setPracownikWynagrodzenie(Integer pracownikWynagrodzenie) {
        this.pracownikWynagrodzenie = pracownikWynagrodzenie;
    }

    public String getPracownikTyp() {
        return pracownikTyp;
    }

    public void setPracownikTyp(String pracownikTyp) {
        this.pracownikTyp = pracownikTyp;
    }

    public Date getPracownikDataZatrudnienia() {
        return pracownikDataZatrudnienia;
    }

    public void setPracownikDataZatrudnienia(Date pracownikDataZatrudnienia) {
        this.pracownikDataZatrudnienia = pracownikDataZatrudnienia;
    }

    public Date getPracownikDataZwolnienia() {
        return pracownikDataZwolnienia;
    }

    public void setPracownikDataZwolnienia(Date pracownikDataZwolnienia) {
        this.pracownikDataZwolnienia = pracownikDataZwolnienia;
    }

    public Adres getPracownikAdresIdFk() {
        return pracownikAdresIdFk;
    }

    public void setPracownikAdresIdFk(Adres pracownikAdresIdFk) {
        this.pracownikAdresIdFk = pracownikAdresIdFk;
    }

    public Oddzial getPracownikOddzialIdFk() {
        return pracownikOddzialIdFk;
    }

    public void setPracownikOddzialIdFk(Oddzial pracownikOddzialIdFk) {
        this.pracownikOddzialIdFk = pracownikOddzialIdFk;
    }

    public Zaklad getPracownikZakladIdFk() {
        return pracownikZakladIdFk;
    }

    public void setPracownikZakladIdFk(Zaklad pracownikZakladIdFk) {
        this.pracownikZakladIdFk = pracownikZakladIdFk;
    }

    @XmlTransient
    public Collection<Ubezpieczenie> getUbezpieczenieCollection() {
        return ubezpieczenieCollection;
    }

    public void setUbezpieczenieCollection(Collection<Ubezpieczenie> ubezpieczenieCollection) {
        this.ubezpieczenieCollection = ubezpieczenieCollection;
    }

    @XmlTransient
    public Collection<Wycena> getWycenaCollection() {
        return wycenaCollection;
    }

    public void setWycenaCollection(Collection<Wycena> wycenaCollection) {
        this.wycenaCollection = wycenaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pracownikId != null ? pracownikId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pracownik)) {
            return false;
        }
        Pracownik other = (Pracownik) object;
        if ((this.pracownikId == null && other.pracownikId != null) || (this.pracownikId != null && !this.pracownikId.equals(other.pracownikId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ubezpieczalnia.entities.Pracownik[ pracownikId=" + pracownikId + " ]";
    }
    
}
