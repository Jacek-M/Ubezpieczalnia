/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jacek
 */
@Entity
@Table(name = "klient")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Klient.findAll", query = "SELECT k FROM Klient k"),
    @NamedQuery(name = "Klient.findByKlientId", query = "SELECT k FROM Klient k WHERE k.klientId = :klientId"),
    @NamedQuery(name = "Klient.findByKlientImie", query = "SELECT k FROM Klient k WHERE k.klientImie = :klientImie"),
    @NamedQuery(name = "Klient.findByKlientNazwisko", query = "SELECT k FROM Klient k WHERE k.klientNazwisko = :klientNazwisko"),
    @NamedQuery(name = "Klient.findByKlientProcentZnizki", query = "SELECT k FROM Klient k WHERE k.klientProcentZnizki = :klientProcentZnizki")})
public class Klient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "klient_id")
    private Integer klientId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "klient_imie")
    private String klientImie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "klient_nazwisko")
    private String klientNazwisko;
    @Column(name = "klient_procent_znizki")
    private Integer klientProcentZnizki;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ubezpieczenieKlientIdFk")
    private Collection<Ubezpieczenie> ubezpieczenieCollection;
    @JoinColumn(name = "klient_adres_id_fk", referencedColumnName = "adres_id")
    @ManyToOne(optional = false)
    private Adres klientAdresIdFk;

    public Klient() {
    }

    public Klient(Integer klientId) {
        this.klientId = klientId;
    }

    public Klient(Integer klientId, String klientImie, String klientNazwisko) {
        this.klientId = klientId;
        this.klientImie = klientImie;
        this.klientNazwisko = klientNazwisko;
    }

    public Integer getKlientId() {
        return klientId;
    }

    public void setKlientId(Integer klientId) {
        this.klientId = klientId;
    }

    public String getKlientImie() {
        return klientImie;
    }

    public void setKlientImie(String klientImie) {
        this.klientImie = klientImie;
    }

    public String getKlientNazwisko() {
        return klientNazwisko;
    }

    public void setKlientNazwisko(String klientNazwisko) {
        this.klientNazwisko = klientNazwisko;
    }

    public Integer getKlientProcentZnizki() {
        return klientProcentZnizki;
    }

    public void setKlientProcentZnizki(Integer klientProcentZnizki) {
        this.klientProcentZnizki = klientProcentZnizki;
    }

    @XmlTransient
    public Collection<Ubezpieczenie> getUbezpieczenieCollection() {
        return ubezpieczenieCollection;
    }

    public void setUbezpieczenieCollection(Collection<Ubezpieczenie> ubezpieczenieCollection) {
        this.ubezpieczenieCollection = ubezpieczenieCollection;
    }

    public Adres getKlientAdresIdFk() {
        return klientAdresIdFk;
    }

    public void setKlientAdresIdFk(Adres klientAdresIdFk) {
        this.klientAdresIdFk = klientAdresIdFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (klientId != null ? klientId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Klient)) {
            return false;
        }
        Klient other = (Klient) object;
        if ((this.klientId == null && other.klientId != null) || (this.klientId != null && !this.klientId.equals(other.klientId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ubezpieczalnia.entities.Klient[ klientId=" + klientId + " ]";
    }
    
}
