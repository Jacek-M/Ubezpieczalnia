/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "samochod_zastepczy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SamochodZastepczy.findAll", query = "SELECT s FROM SamochodZastepczy s"),
    @NamedQuery(name = "SamochodZastepczy.findBySamochodZastepczyId", query = "SELECT s FROM SamochodZastepczy s WHERE s.samochodZastepczyId = :samochodZastepczyId"),
    @NamedQuery(name = "SamochodZastepczy.findBySamochodZastepczyMarka", query = "SELECT s FROM SamochodZastepczy s WHERE s.samochodZastepczyMarka = :samochodZastepczyMarka"),
    @NamedQuery(name = "SamochodZastepczy.findBySamochodZastepczyModel", query = "SELECT s FROM SamochodZastepczy s WHERE s.samochodZastepczyModel = :samochodZastepczyModel"),
    @NamedQuery(name = "SamochodZastepczy.findBySamochodZastepczyCzyDostepny", query = "SELECT s FROM SamochodZastepczy s WHERE s.samochodZastepczyCzyDostepny = :samochodZastepczyCzyDostepny")})
public class SamochodZastepczy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "samochod_zastepczy_id")
    private Integer samochodZastepczyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "samochod_zastepczy_marka")
    private String samochodZastepczyMarka;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "samochod_zastepczy_model")
    private String samochodZastepczyModel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "samochod_zastepczy_czy_dostepny")
    private int samochodZastepczyCzyDostepny;
    @OneToMany(mappedBy = "szkodaSamochodZastepczyIdFk")
    private Collection<Szkoda> szkodaCollection;

    public SamochodZastepczy() {
    }

    public SamochodZastepczy(Integer samochodZastepczyId) {
        this.samochodZastepczyId = samochodZastepczyId;
    }

    public SamochodZastepczy(Integer samochodZastepczyId, String samochodZastepczyMarka, String samochodZastepczyModel, int samochodZastepczyCzyDostepny) {
        this.samochodZastepczyId = samochodZastepczyId;
        this.samochodZastepczyMarka = samochodZastepczyMarka;
        this.samochodZastepczyModel = samochodZastepczyModel;
        this.samochodZastepczyCzyDostepny = samochodZastepczyCzyDostepny;
    }

    public Integer getSamochodZastepczyId() {
        return samochodZastepczyId;
    }

    public void setSamochodZastepczyId(Integer samochodZastepczyId) {
        this.samochodZastepczyId = samochodZastepczyId;
    }

    public String getSamochodZastepczyMarka() {
        return samochodZastepczyMarka;
    }

    public void setSamochodZastepczyMarka(String samochodZastepczyMarka) {
        this.samochodZastepczyMarka = samochodZastepczyMarka;
    }

    public String getSamochodZastepczyModel() {
        return samochodZastepczyModel;
    }

    public void setSamochodZastepczyModel(String samochodZastepczyModel) {
        this.samochodZastepczyModel = samochodZastepczyModel;
    }

    public int getSamochodZastepczyCzyDostepny() {
        return samochodZastepczyCzyDostepny;
    }

    public void setSamochodZastepczyCzyDostepny(int samochodZastepczyCzyDostepny) {
        this.samochodZastepczyCzyDostepny = samochodZastepczyCzyDostepny;
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
        hash += (samochodZastepczyId != null ? samochodZastepczyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SamochodZastepczy)) {
            return false;
        }
        SamochodZastepczy other = (SamochodZastepczy) object;
        if ((this.samochodZastepczyId == null && other.samochodZastepczyId != null) || (this.samochodZastepczyId != null && !this.samochodZastepczyId.equals(other.samochodZastepczyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ubezpieczalnia.entities.SamochodZastepczy[ samochodZastepczyId=" + samochodZastepczyId + " ]";
    }
    
}
