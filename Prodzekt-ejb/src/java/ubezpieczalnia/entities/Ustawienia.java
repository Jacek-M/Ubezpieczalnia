/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jacek
 */
@Entity
@Table(name = "ustawienia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ustawienia.findAll", query = "SELECT u FROM Ustawienia u"),
    @NamedQuery(name = "Ustawienia.findByUstawieniaId", query = "SELECT u FROM Ustawienia u WHERE u.ustawieniaId = :ustawieniaId"),
    @NamedQuery(name = "Ustawienia.findByUstawieniaKlucz", query = "SELECT u FROM Ustawienia u WHERE u.ustawieniaKlucz = :ustawieniaKlucz"),
    @NamedQuery(name = "Ustawienia.findByUstawieniaWartosc", query = "SELECT u FROM Ustawienia u WHERE u.ustawieniaWartosc = :ustawieniaWartosc")})
public class Ustawienia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ustawienia_id")
    private Integer ustawieniaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ustawienia_klucz")
    private String ustawieniaKlucz;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ustawienia_wartosc")
    private String ustawieniaWartosc;

    public Ustawienia() {
    }

    public Ustawienia(Integer ustawieniaId) {
        this.ustawieniaId = ustawieniaId;
    }

    public Ustawienia(Integer ustawieniaId, String ustawieniaKlucz, String ustawieniaWartosc) {
        this.ustawieniaId = ustawieniaId;
        this.ustawieniaKlucz = ustawieniaKlucz;
        this.ustawieniaWartosc = ustawieniaWartosc;
    }

    public Integer getUstawieniaId() {
        return ustawieniaId;
    }

    public void setUstawieniaId(Integer ustawieniaId) {
        this.ustawieniaId = ustawieniaId;
    }

    public String getUstawieniaKlucz() {
        return ustawieniaKlucz;
    }

    public void setUstawieniaKlucz(String ustawieniaKlucz) {
        this.ustawieniaKlucz = ustawieniaKlucz;
    }

    public String getUstawieniaWartosc() {
        return ustawieniaWartosc;
    }

    public void setUstawieniaWartosc(String ustawieniaWartosc) {
        this.ustawieniaWartosc = ustawieniaWartosc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ustawieniaId != null ? ustawieniaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ustawienia)) {
            return false;
        }
        Ustawienia other = (Ustawienia) object;
        if ((this.ustawieniaId == null && other.ustawieniaId != null) || (this.ustawieniaId != null && !this.ustawieniaId.equals(other.ustawieniaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ubezpieczalnia.entities.Ustawienia[ ustawieniaId=" + ustawieniaId + " ]";
    }
    
}
