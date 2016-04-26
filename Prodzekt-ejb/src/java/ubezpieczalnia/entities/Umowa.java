/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jacek
 */
@Entity
@Table(name = "umowa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Umowa.findAll", query = "SELECT u FROM Umowa u"),
    @NamedQuery(name = "Umowa.findByUmowaId", query = "SELECT u FROM Umowa u WHERE u.umowaId = :umowaId"),
    @NamedQuery(name = "Umowa.findByUmowaDataWystawienia", query = "SELECT u FROM Umowa u WHERE u.umowaDataWystawienia = :umowaDataWystawienia"),
    @NamedQuery(name = "Umowa.findByUmowaDataZakonczenia", query = "SELECT u FROM Umowa u WHERE u.umowaDataZakonczenia = :umowaDataZakonczenia"),
    @NamedQuery(name = "Umowa.findByUmowaKwota", query = "SELECT u FROM Umowa u WHERE u.umowaKwota = :umowaKwota"),
    @NamedQuery(name = "Umowa.findByUmowaStatus", query = "SELECT u FROM Umowa u WHERE u.umowaStatus = :umowaStatus")})
public class Umowa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "umowa_id")
    private Integer umowaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "umowa_data_wystawienia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date umowaDataWystawienia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "umowa_data_zakonczenia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date umowaDataZakonczenia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "umowa_kwota")
    private int umowaKwota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "umowa_status")
    private String umowaStatus;
    @JoinColumn(name = "umowa_klient_id_fk", referencedColumnName = "klient_id")
    @ManyToOne(optional = false)
    private Klient umowaKlientIdFk;
    @JoinColumn(name = "umowa_pojazd_id_fk", referencedColumnName = "pojazd_id")
    @ManyToOne(optional = false)
    private Pojazd umowaPojazdIdFk;
    @JoinColumn(name = "umowa_pracownik_id_fk", referencedColumnName = "pracownik_id")
    @ManyToOne(optional = false)
    private Pracownik umowaPracownikIdFk;
    @JoinColumn(name = "umowa_rodzaj_ubezpieczenia_id_fk", referencedColumnName = "rodzaj_ubezpieczenia_id")
    @ManyToOne(optional = false)
    private RodzajUbezpieczenia umowaRodzajUbezpieczeniaIdFk;

    public Umowa() {
    }

    public Umowa(Integer umowaId) {
        this.umowaId = umowaId;
    }

    public Umowa(Integer umowaId, Date umowaDataWystawienia, Date umowaDataZakonczenia, int umowaKwota, String umowaStatus) {
        this.umowaId = umowaId;
        this.umowaDataWystawienia = umowaDataWystawienia;
        this.umowaDataZakonczenia = umowaDataZakonczenia;
        this.umowaKwota = umowaKwota;
        this.umowaStatus = umowaStatus;
    }

    public Integer getUmowaId() {
        return umowaId;
    }

    public void setUmowaId(Integer umowaId) {
        this.umowaId = umowaId;
    }

    public Date getUmowaDataWystawienia() {
        return umowaDataWystawienia;
    }

    public void setUmowaDataWystawienia(Date umowaDataWystawienia) {
        this.umowaDataWystawienia = umowaDataWystawienia;
    }

    public Date getUmowaDataZakonczenia() {
        return umowaDataZakonczenia;
    }

    public void setUmowaDataZakonczenia(Date umowaDataZakonczenia) {
        this.umowaDataZakonczenia = umowaDataZakonczenia;
    }

    public int getUmowaKwota() {
        return umowaKwota;
    }

    public void setUmowaKwota(int umowaKwota) {
        this.umowaKwota = umowaKwota;
    }

    public String getUmowaStatus() {
        return umowaStatus;
    }

    public void setUmowaStatus(String umowaStatus) {
        this.umowaStatus = umowaStatus;
    }

    public Klient getUmowaKlientIdFk() {
        return umowaKlientIdFk;
    }

    public void setUmowaKlientIdFk(Klient umowaKlientIdFk) {
        this.umowaKlientIdFk = umowaKlientIdFk;
    }

    public Pojazd getUmowaPojazdIdFk() {
        return umowaPojazdIdFk;
    }

    public void setUmowaPojazdIdFk(Pojazd umowaPojazdIdFk) {
        this.umowaPojazdIdFk = umowaPojazdIdFk;
    }

    public Pracownik getUmowaPracownikIdFk() {
        return umowaPracownikIdFk;
    }

    public void setUmowaPracownikIdFk(Pracownik umowaPracownikIdFk) {
        this.umowaPracownikIdFk = umowaPracownikIdFk;
    }

    public RodzajUbezpieczenia getUmowaRodzajUbezpieczeniaIdFk() {
        return umowaRodzajUbezpieczeniaIdFk;
    }

    public void setUmowaRodzajUbezpieczeniaIdFk(RodzajUbezpieczenia umowaRodzajUbezpieczeniaIdFk) {
        this.umowaRodzajUbezpieczeniaIdFk = umowaRodzajUbezpieczeniaIdFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (umowaId != null ? umowaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Umowa)) {
            return false;
        }
        Umowa other = (Umowa) object;
        if ((this.umowaId == null && other.umowaId != null) || (this.umowaId != null && !this.umowaId.equals(other.umowaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ubezpieczalnia.entities.Umowa[ umowaId=" + umowaId + " ]";
    }
    
}
