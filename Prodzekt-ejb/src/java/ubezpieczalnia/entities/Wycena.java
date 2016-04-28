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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jacek
 */
@Entity
@Table(name = "wycena")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wycena.findAll", query = "SELECT w FROM Wycena w"),
    @NamedQuery(name = "Wycena.findByWycenaId", query = "SELECT w FROM Wycena w WHERE w.wycenaId = :wycenaId"),
    @NamedQuery(name = "Wycena.findByWycenaKwota", query = "SELECT w FROM Wycena w WHERE w.wycenaKwota = :wycenaKwota"),
    @NamedQuery(name = "Wycena.findByWycenaData", query = "SELECT w FROM Wycena w WHERE w.wycenaData = :wycenaData")})
public class Wycena implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "wycena_id")
    private Integer wycenaId;
    @Column(name = "wycena_kwota")
    private Integer wycenaKwota;
    @Column(name = "wycena_data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date wycenaData;
    @JoinColumn(name = "wycena_pracownik_id_fk", referencedColumnName = "pracownik_id")
    @ManyToOne(optional = false)
    private Pracownik wycenaPracownikIdFk;
    @JoinColumn(name = "wycena_szkoda_id_fk", referencedColumnName = "szkoda_id")
    @ManyToOne(optional = false)
    private Szkoda wycenaSzkodaIdFk;

    public Wycena() {
    }

    public Wycena(Integer wycenaId) {
        this.wycenaId = wycenaId;
    }

    public Integer getWycenaId() {
        return wycenaId;
    }

    public void setWycenaId(Integer wycenaId) {
        this.wycenaId = wycenaId;
    }

    public Integer getWycenaKwota() {
        return wycenaKwota;
    }

    public void setWycenaKwota(Integer wycenaKwota) {
        this.wycenaKwota = wycenaKwota;
    }

    public Date getWycenaData() {
        return wycenaData;
    }

    public void setWycenaData(Date wycenaData) {
        this.wycenaData = wycenaData;
    }

    public Pracownik getWycenaPracownikIdFk() {
        return wycenaPracownikIdFk;
    }

    public void setWycenaPracownikIdFk(Pracownik wycenaPracownikIdFk) {
        this.wycenaPracownikIdFk = wycenaPracownikIdFk;
    }

    public Szkoda getWycenaSzkodaIdFk() {
        return wycenaSzkodaIdFk;
    }

    public void setWycenaSzkodaIdFk(Szkoda wycenaSzkodaIdFk) {
        this.wycenaSzkodaIdFk = wycenaSzkodaIdFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wycenaId != null ? wycenaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wycena)) {
            return false;
        }
        Wycena other = (Wycena) object;
        if ((this.wycenaId == null && other.wycenaId != null) || (this.wycenaId != null && !this.wycenaId.equals(other.wycenaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ubezpieczalnia.entities.Wycena[ wycenaId=" + wycenaId + " ]";
    }
    
}
