/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

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
@Table(name = "ubezpieczenie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ubezpieczenie.findAll", query = "SELECT u FROM Ubezpieczenie u"),
    @NamedQuery(name = "Ubezpieczenie.findByUbezpieczenieId", query = "SELECT u FROM Ubezpieczenie u WHERE u.ubezpieczenieId = :ubezpieczenieId"),
    @NamedQuery(name = "Ubezpieczenie.findByUbezpieczenieDataWystawienia", query = "SELECT u FROM Ubezpieczenie u WHERE u.ubezpieczenieDataWystawienia = :ubezpieczenieDataWystawienia"),
    @NamedQuery(name = "Ubezpieczenie.findByUbezpieczenieDataZakonczenia", query = "SELECT u FROM Ubezpieczenie u WHERE u.ubezpieczenieDataZakonczenia = :ubezpieczenieDataZakonczenia"),
    @NamedQuery(name = "Ubezpieczenie.findByUbezpieczenieKwota", query = "SELECT u FROM Ubezpieczenie u WHERE u.ubezpieczenieKwota = :ubezpieczenieKwota"),
    @NamedQuery(name = "Ubezpieczenie.findByUbezpieczenieStatus", query = "SELECT u FROM Ubezpieczenie u WHERE u.ubezpieczenieStatus = :ubezpieczenieStatus")})
public class Ubezpieczenie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ubezpieczenie_id")
    private Integer ubezpieczenieId;
    @Column(name = "ubezpieczenie_data_wystawienia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ubezpieczenieDataWystawienia;
    @Column(name = "ubezpieczenie_data_zakonczenia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ubezpieczenieDataZakonczenia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ubezpieczenie_kwota")
    private int ubezpieczenieKwota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ubezpieczenie_status")
    private String ubezpieczenieStatus;
    @JoinColumn(name = "ubezpieczenie_klient_id_fk", referencedColumnName = "klient_id")
    @ManyToOne(optional = false)
    private Klient ubezpieczenieKlientIdFk;
    @JoinColumn(name = "ubezpieczenie_pojazd_id_fk", referencedColumnName = "pojazd_id")
    @ManyToOne(optional = false)
    private Pojazd ubezpieczeniePojazdIdFk;
    @JoinColumn(name = "ubezpieczenie_pracownik_id_fk", referencedColumnName = "pracownik_id")
    @ManyToOne
    private Pracownik ubezpieczeniePracownikIdFk;
    @JoinColumn(name = "ubezpieczenie_rodzaj_ubezpieczenia_id_fk", referencedColumnName = "rodzaj_ubezpieczenia_id")
    @ManyToOne(optional = false)
    private RodzajUbezpieczenia ubezpieczenieRodzajUbezpieczeniaIdFk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "szkodaUbezpieczenieIdFk")
    private Collection<Szkoda> szkodaCollection;

    public Ubezpieczenie() {
    }

    public Ubezpieczenie(Integer ubezpieczenieId) {
        this.ubezpieczenieId = ubezpieczenieId;
    }

    public Ubezpieczenie(Integer ubezpieczenieId, int ubezpieczenieKwota, String ubezpieczenieStatus) {
        this.ubezpieczenieId = ubezpieczenieId;
        this.ubezpieczenieKwota = ubezpieczenieKwota;
        this.ubezpieczenieStatus = ubezpieczenieStatus;
    }

    public Integer getUbezpieczenieId() {
        return ubezpieczenieId;
    }

    public void setUbezpieczenieId(Integer ubezpieczenieId) {
        this.ubezpieczenieId = ubezpieczenieId;
    }

    public Date getUbezpieczenieDataWystawienia() {
        return ubezpieczenieDataWystawienia;
    }

    public void setUbezpieczenieDataWystawienia(Date ubezpieczenieDataWystawienia) {
        this.ubezpieczenieDataWystawienia = ubezpieczenieDataWystawienia;
    }

    public Date getUbezpieczenieDataZakonczenia() {
        return ubezpieczenieDataZakonczenia;
    }

    public void setUbezpieczenieDataZakonczenia(Date ubezpieczenieDataZakonczenia) {
        this.ubezpieczenieDataZakonczenia = ubezpieczenieDataZakonczenia;
    }

    public int getUbezpieczenieKwota() {
        return ubezpieczenieKwota;
    }

    public void setUbezpieczenieKwota(int ubezpieczenieKwota) {
        this.ubezpieczenieKwota = ubezpieczenieKwota;
    }

    public String getUbezpieczenieStatus() {
        return ubezpieczenieStatus;
    }

    public void setUbezpieczenieStatus(String ubezpieczenieStatus) {
        this.ubezpieczenieStatus = ubezpieczenieStatus;
    }

    public Klient getUbezpieczenieKlientIdFk() {
        return ubezpieczenieKlientIdFk;
    }

    public void setUbezpieczenieKlientIdFk(Klient ubezpieczenieKlientIdFk) {
        this.ubezpieczenieKlientIdFk = ubezpieczenieKlientIdFk;
    }

    public Pojazd getUbezpieczeniePojazdIdFk() {
        return ubezpieczeniePojazdIdFk;
    }

    public void setUbezpieczeniePojazdIdFk(Pojazd ubezpieczeniePojazdIdFk) {
        this.ubezpieczeniePojazdIdFk = ubezpieczeniePojazdIdFk;
    }

    public Pracownik getUbezpieczeniePracownikIdFk() {
        return ubezpieczeniePracownikIdFk;
    }

    public void setUbezpieczeniePracownikIdFk(Pracownik ubezpieczeniePracownikIdFk) {
        this.ubezpieczeniePracownikIdFk = ubezpieczeniePracownikIdFk;
    }

    public RodzajUbezpieczenia getUbezpieczenieRodzajUbezpieczeniaIdFk() {
        return ubezpieczenieRodzajUbezpieczeniaIdFk;
    }

    public void setUbezpieczenieRodzajUbezpieczeniaIdFk(RodzajUbezpieczenia ubezpieczenieRodzajUbezpieczeniaIdFk) {
        this.ubezpieczenieRodzajUbezpieczeniaIdFk = ubezpieczenieRodzajUbezpieczeniaIdFk;
    }

    @XmlTransient
    public Collection<Szkoda> getSzkodaCollection() {
        return szkodaCollection;
    }

    public void setSzkodaCollection(Collection<Szkoda> szkodaCollection) {
        this.szkodaCollection = szkodaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ubezpieczenieId != null ? ubezpieczenieId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ubezpieczenie)) {
            return false;
        }
        Ubezpieczenie other = (Ubezpieczenie) object;
        if ((this.ubezpieczenieId == null && other.ubezpieczenieId != null) || (this.ubezpieczenieId != null && !this.ubezpieczenieId.equals(other.ubezpieczenieId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Ubezpieczenie[ ubezpieczenieId=" + ubezpieczenieId + " ]";
    }
    
}
