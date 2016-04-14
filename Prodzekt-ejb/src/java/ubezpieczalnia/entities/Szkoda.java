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
 * @author layfl
 */
@Entity
@Table(name = "szkoda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Szkoda.findAll", query = "SELECT s FROM Szkoda s"),
    @NamedQuery(name = "Szkoda.findBySzkodaId", query = "SELECT s FROM Szkoda s WHERE s.szkodaId = :szkodaId"),
    @NamedQuery(name = "Szkoda.findBySzkodaDataZdarzenia", query = "SELECT s FROM Szkoda s WHERE s.szkodaDataZdarzenia = :szkodaDataZdarzenia"),
    @NamedQuery(name = "Szkoda.findBySzkodaDataZakonczenia", query = "SELECT s FROM Szkoda s WHERE s.szkodaDataZakonczenia = :szkodaDataZakonczenia"),
    @NamedQuery(name = "Szkoda.findBySzkodaStatus", query = "SELECT s FROM Szkoda s WHERE s.szkodaStatus = :szkodaStatus"),
    @NamedQuery(name = "Szkoda.findBySzkodaTyp", query = "SELECT s FROM Szkoda s WHERE s.szkodaTyp = :szkodaTyp"),
    @NamedQuery(name = "Szkoda.findBySzkodaOpis", query = "SELECT s FROM Szkoda s WHERE s.szkodaOpis = :szkodaOpis")})
public class Szkoda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "szkoda_id")
    private Integer szkodaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "szkoda_data_zdarzenia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date szkodaDataZdarzenia;
    @Column(name = "szkoda_data_zakonczenia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date szkodaDataZakonczenia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "szkoda_status")
    private String szkodaStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "szkoda_typ")
    private String szkodaTyp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "szkoda_opis")
    private String szkodaOpis;
    @JoinColumn(name = "szkoda_samochod_zastepczy_id_fk", referencedColumnName = "samochod_zastepczy_id")
    @ManyToOne
    private SamochodZastepczy szkodaSamochodZastepczyIdFk;
    @JoinColumn(name = "szkoda_ubezpieczenie_id_fk", referencedColumnName = "ubezpieczenie_id")
    @ManyToOne(optional = false)
    private Ubezpieczenie szkodaUbezpieczenieIdFk;
    @JoinColumn(name = "szkoda_uczestnik_id_fk", referencedColumnName = "uczestnik_id")
    @ManyToOne
    private Uczestnik szkodaUczestnikIdFk;
    @JoinColumn(name = "szkoda_wycena_id_fk", referencedColumnName = "wycena_id")
    @ManyToOne
    private Wycena szkodaWycenaIdFk;
    @JoinColumn(name = "szkoda_zaklad_id_fk", referencedColumnName = "zaklad_id")
    @ManyToOne
    private Zaklad szkodaZakladIdFk;

    public Szkoda() {
    }

    public Szkoda(Integer szkodaId) {
        this.szkodaId = szkodaId;
    }

    public Szkoda(Integer szkodaId, Date szkodaDataZdarzenia, String szkodaStatus, String szkodaTyp, String szkodaOpis) {
        this.szkodaId = szkodaId;
        this.szkodaDataZdarzenia = szkodaDataZdarzenia;
        this.szkodaStatus = szkodaStatus;
        this.szkodaTyp = szkodaTyp;
        this.szkodaOpis = szkodaOpis;
    }

    public Integer getSzkodaId() {
        return szkodaId;
    }

    public void setSzkodaId(Integer szkodaId) {
        this.szkodaId = szkodaId;
    }

    public Date getSzkodaDataZdarzenia() {
        return szkodaDataZdarzenia;
    }

    public void setSzkodaDataZdarzenia(Date szkodaDataZdarzenia) {
        this.szkodaDataZdarzenia = szkodaDataZdarzenia;
    }

    public Date getSzkodaDataZakonczenia() {
        return szkodaDataZakonczenia;
    }

    public void setSzkodaDataZakonczenia(Date szkodaDataZakonczenia) {
        this.szkodaDataZakonczenia = szkodaDataZakonczenia;
    }

    public String getSzkodaStatus() {
        return szkodaStatus;
    }

    public void setSzkodaStatus(String szkodaStatus) {
        this.szkodaStatus = szkodaStatus;
    }

    public String getSzkodaTyp() {
        return szkodaTyp;
    }

    public void setSzkodaTyp(String szkodaTyp) {
        this.szkodaTyp = szkodaTyp;
    }

    public String getSzkodaOpis() {
        return szkodaOpis;
    }

    public void setSzkodaOpis(String szkodaOpis) {
        this.szkodaOpis = szkodaOpis;
    }

    public SamochodZastepczy getSzkodaSamochodZastepczyIdFk() {
        return szkodaSamochodZastepczyIdFk;
    }

    public void setSzkodaSamochodZastepczyIdFk(SamochodZastepczy szkodaSamochodZastepczyIdFk) {
        this.szkodaSamochodZastepczyIdFk = szkodaSamochodZastepczyIdFk;
    }

    public Ubezpieczenie getSzkodaUbezpieczenieIdFk() {
        return szkodaUbezpieczenieIdFk;
    }

    public void setSzkodaUbezpieczenieIdFk(Ubezpieczenie szkodaUbezpieczenieIdFk) {
        this.szkodaUbezpieczenieIdFk = szkodaUbezpieczenieIdFk;
    }

    public Uczestnik getSzkodaUczestnikIdFk() {
        return szkodaUczestnikIdFk;
    }

    public void setSzkodaUczestnikIdFk(Uczestnik szkodaUczestnikIdFk) {
        this.szkodaUczestnikIdFk = szkodaUczestnikIdFk;
    }

    public Wycena getSzkodaWycenaIdFk() {
        return szkodaWycenaIdFk;
    }

    public void setSzkodaWycenaIdFk(Wycena szkodaWycenaIdFk) {
        this.szkodaWycenaIdFk = szkodaWycenaIdFk;
    }

    public Zaklad getSzkodaZakladIdFk() {
        return szkodaZakladIdFk;
    }

    public void setSzkodaZakladIdFk(Zaklad szkodaZakladIdFk) {
        this.szkodaZakladIdFk = szkodaZakladIdFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (szkodaId != null ? szkodaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Szkoda)) {
            return false;
        }
        Szkoda other = (Szkoda) object;
        if ((this.szkodaId == null && other.szkodaId != null) || (this.szkodaId != null && !this.szkodaId.equals(other.szkodaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ubezpieczalnia.entities.Szkoda[ szkodaId=" + szkodaId + " ]";
    }
    
}
