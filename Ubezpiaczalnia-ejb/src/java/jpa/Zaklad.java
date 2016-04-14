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
@Table(name = "zaklad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zaklad.findAll", query = "SELECT z FROM Zaklad z"),
    @NamedQuery(name = "Zaklad.findByZakladId", query = "SELECT z FROM Zaklad z WHERE z.zakladId = :zakladId"),
    @NamedQuery(name = "Zaklad.findByZakladNazwa", query = "SELECT z FROM Zaklad z WHERE z.zakladNazwa = :zakladNazwa"),
    @NamedQuery(name = "Zaklad.findByZakladDataRozpoczeciaWsp", query = "SELECT z FROM Zaklad z WHERE z.zakladDataRozpoczeciaWsp = :zakladDataRozpoczeciaWsp"),
    @NamedQuery(name = "Zaklad.findByZakladDataZakonczeniaWsp", query = "SELECT z FROM Zaklad z WHERE z.zakladDataZakonczeniaWsp = :zakladDataZakonczeniaWsp")})
public class Zaklad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "zaklad_id")
    private Integer zakladId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "zaklad_nazwa")
    private String zakladNazwa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "zaklad_data_rozpoczecia_wsp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date zakladDataRozpoczeciaWsp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "zaklad_data_zakonczenia_wsp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date zakladDataZakonczeniaWsp;
    @OneToMany(mappedBy = "pracownikZakladIdFk")
    private Collection<Pracownik> pracownikCollection;
    @JoinColumn(name = "zaklad_adres_id_fk", referencedColumnName = "adres_id")
    @ManyToOne(optional = false)
    private Adres zakladAdresIdFk;
    @OneToMany(mappedBy = "szkodaZakladIdFk")
    private Collection<Szkoda> szkodaCollection;

    public Zaklad() {
    }

    public Zaklad(Integer zakladId) {
        this.zakladId = zakladId;
    }

    public Zaklad(Integer zakladId, String zakladNazwa, Date zakladDataRozpoczeciaWsp, Date zakladDataZakonczeniaWsp) {
        this.zakladId = zakladId;
        this.zakladNazwa = zakladNazwa;
        this.zakladDataRozpoczeciaWsp = zakladDataRozpoczeciaWsp;
        this.zakladDataZakonczeniaWsp = zakladDataZakonczeniaWsp;
    }

    public Integer getZakladId() {
        return zakladId;
    }

    public void setZakladId(Integer zakladId) {
        this.zakladId = zakladId;
    }

    public String getZakladNazwa() {
        return zakladNazwa;
    }

    public void setZakladNazwa(String zakladNazwa) {
        this.zakladNazwa = zakladNazwa;
    }

    public Date getZakladDataRozpoczeciaWsp() {
        return zakladDataRozpoczeciaWsp;
    }

    public void setZakladDataRozpoczeciaWsp(Date zakladDataRozpoczeciaWsp) {
        this.zakladDataRozpoczeciaWsp = zakladDataRozpoczeciaWsp;
    }

    public Date getZakladDataZakonczeniaWsp() {
        return zakladDataZakonczeniaWsp;
    }

    public void setZakladDataZakonczeniaWsp(Date zakladDataZakonczeniaWsp) {
        this.zakladDataZakonczeniaWsp = zakladDataZakonczeniaWsp;
    }

    @XmlTransient
    public Collection<Pracownik> getPracownikCollection() {
        return pracownikCollection;
    }

    public void setPracownikCollection(Collection<Pracownik> pracownikCollection) {
        this.pracownikCollection = pracownikCollection;
    }

    public Adres getZakladAdresIdFk() {
        return zakladAdresIdFk;
    }

    public void setZakladAdresIdFk(Adres zakladAdresIdFk) {
        this.zakladAdresIdFk = zakladAdresIdFk;
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
        hash += (zakladId != null ? zakladId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zaklad)) {
            return false;
        }
        Zaklad other = (Zaklad) object;
        if ((this.zakladId == null && other.zakladId != null) || (this.zakladId != null && !this.zakladId.equals(other.zakladId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Zaklad[ zakladId=" + zakladId + " ]";
    }
    
}
