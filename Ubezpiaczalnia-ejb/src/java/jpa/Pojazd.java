/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "pojazd")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pojazd.findAll", query = "SELECT p FROM Pojazd p"),
    @NamedQuery(name = "Pojazd.findByPojazdId", query = "SELECT p FROM Pojazd p WHERE p.pojazdId = :pojazdId"),
    @NamedQuery(name = "Pojazd.findByPojazdMarka", query = "SELECT p FROM Pojazd p WHERE p.pojazdMarka = :pojazdMarka"),
    @NamedQuery(name = "Pojazd.findByPojazdModel", query = "SELECT p FROM Pojazd p WHERE p.pojazdModel = :pojazdModel"),
    @NamedQuery(name = "Pojazd.findByPojazdRokProdukcji", query = "SELECT p FROM Pojazd p WHERE p.pojazdRokProdukcji = :pojazdRokProdukcji"),
    @NamedQuery(name = "Pojazd.findByPojazdNrVin", query = "SELECT p FROM Pojazd p WHERE p.pojazdNrVin = :pojazdNrVin"),
    @NamedQuery(name = "Pojazd.findByPojazdPojemnosc", query = "SELECT p FROM Pojazd p WHERE p.pojazdPojemnosc = :pojazdPojemnosc"),
    @NamedQuery(name = "Pojazd.findByPojazdPaliwo", query = "SELECT p FROM Pojazd p WHERE p.pojazdPaliwo = :pojazdPaliwo")})
public class Pojazd implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pojazd_id")
    private Integer pojazdId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "pojazd_marka")
    private String pojazdMarka;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "pojazd_model")
    private String pojazdModel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pojazd_rok_produkcji")
    private int pojazdRokProdukcji;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "pojazd_nr_vin")
    private String pojazdNrVin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pojazd_pojemnosc")
    private int pojazdPojemnosc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "pojazd_paliwo")
    private String pojazdPaliwo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ubezpieczeniePojazdIdFk")
    private Collection<Ubezpieczenie> ubezpieczenieCollection;

    public Pojazd() {
    }

    public Pojazd(Integer pojazdId) {
        this.pojazdId = pojazdId;
    }

    public Pojazd(Integer pojazdId, String pojazdMarka, String pojazdModel, int pojazdRokProdukcji, String pojazdNrVin, int pojazdPojemnosc, String pojazdPaliwo) {
        this.pojazdId = pojazdId;
        this.pojazdMarka = pojazdMarka;
        this.pojazdModel = pojazdModel;
        this.pojazdRokProdukcji = pojazdRokProdukcji;
        this.pojazdNrVin = pojazdNrVin;
        this.pojazdPojemnosc = pojazdPojemnosc;
        this.pojazdPaliwo = pojazdPaliwo;
    }

    public Integer getPojazdId() {
        return pojazdId;
    }

    public void setPojazdId(Integer pojazdId) {
        this.pojazdId = pojazdId;
    }

    public String getPojazdMarka() {
        return pojazdMarka;
    }

    public void setPojazdMarka(String pojazdMarka) {
        this.pojazdMarka = pojazdMarka;
    }

    public String getPojazdModel() {
        return pojazdModel;
    }

    public void setPojazdModel(String pojazdModel) {
        this.pojazdModel = pojazdModel;
    }

    public int getPojazdRokProdukcji() {
        return pojazdRokProdukcji;
    }

    public void setPojazdRokProdukcji(int pojazdRokProdukcji) {
        this.pojazdRokProdukcji = pojazdRokProdukcji;
    }

    public String getPojazdNrVin() {
        return pojazdNrVin;
    }

    public void setPojazdNrVin(String pojazdNrVin) {
        this.pojazdNrVin = pojazdNrVin;
    }

    public int getPojazdPojemnosc() {
        return pojazdPojemnosc;
    }

    public void setPojazdPojemnosc(int pojazdPojemnosc) {
        this.pojazdPojemnosc = pojazdPojemnosc;
    }

    public String getPojazdPaliwo() {
        return pojazdPaliwo;
    }

    public void setPojazdPaliwo(String pojazdPaliwo) {
        this.pojazdPaliwo = pojazdPaliwo;
    }

    @XmlTransient
    public Collection<Ubezpieczenie> getUbezpieczenieCollection() {
        return ubezpieczenieCollection;
    }

    public void setUbezpieczenieCollection(Collection<Ubezpieczenie> ubezpieczenieCollection) {
        this.ubezpieczenieCollection = ubezpieczenieCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pojazdId != null ? pojazdId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pojazd)) {
            return false;
        }
        Pojazd other = (Pojazd) object;
        if ((this.pojazdId == null && other.pojazdId != null) || (this.pojazdId != null && !this.pojazdId.equals(other.pojazdId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Pojazd[ pojazdId=" + pojazdId + " ]";
    }
    
}
