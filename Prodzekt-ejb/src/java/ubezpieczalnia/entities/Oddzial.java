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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author layfl
 */
@Entity
@Table(name = "oddzial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Oddzial.findAll", query = "SELECT o FROM Oddzial o"),
    @NamedQuery(name = "Oddzial.findByOddzialId", query = "SELECT o FROM Oddzial o WHERE o.oddzialId = :oddzialId"),
    @NamedQuery(name = "Oddzial.findByOddzialTelefon", query = "SELECT o FROM Oddzial o WHERE o.oddzialTelefon = :oddzialTelefon")})
public class Oddzial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "oddzial_id")
    private Integer oddzialId;
    @Size(max = 45)
    @Column(name = "oddzial_telefon")
    private String oddzialTelefon;
    @OneToMany(mappedBy = "pracownikOddzialIdFk")
    private Collection<Pracownik> pracownikCollection;
    @JoinColumn(name = "oddzial_adres_id_fk", referencedColumnName = "adres_id")
    @ManyToOne(optional = false)
    private Adres oddzialAdresIdFk;

    public Oddzial() {
    }

    public Oddzial(Integer oddzialId) {
        this.oddzialId = oddzialId;
    }

    public Integer getOddzialId() {
        return oddzialId;
    }

    public void setOddzialId(Integer oddzialId) {
        this.oddzialId = oddzialId;
    }

    public String getOddzialTelefon() {
        return oddzialTelefon;
    }

    public void setOddzialTelefon(String oddzialTelefon) {
        this.oddzialTelefon = oddzialTelefon;
    }

    @XmlTransient
    public Collection<Pracownik> getPracownikCollection() {
        return pracownikCollection;
    }

    public void setPracownikCollection(Collection<Pracownik> pracownikCollection) {
        this.pracownikCollection = pracownikCollection;
    }

    public Adres getOddzialAdresIdFk() {
        return oddzialAdresIdFk;
    }

    public void setOddzialAdresIdFk(Adres oddzialAdresIdFk) {
        this.oddzialAdresIdFk = oddzialAdresIdFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oddzialId != null ? oddzialId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oddzial)) {
            return false;
        }
        Oddzial other = (Oddzial) object;
        if ((this.oddzialId == null && other.oddzialId != null) || (this.oddzialId != null && !this.oddzialId.equals(other.oddzialId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ubezpieczalnia.entities.Oddzial[ oddzialId=" + oddzialId + " ]";
    }
    
}
