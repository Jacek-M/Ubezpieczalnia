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
 * @author layfl
 */
@Entity
@Table(name = "konto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Konto.findAll", query = "SELECT k FROM Konto k"),
    @NamedQuery(name = "Konto.findByKontoId", query = "SELECT k FROM Konto k WHERE k.kontoId = :kontoId"),
    @NamedQuery(name = "Konto.findByKontoLogin", query = "SELECT k FROM Konto k WHERE k.kontoLogin = :kontoLogin"),
    @NamedQuery(name = "Konto.findByKontoHaslo", query = "SELECT k FROM Konto k WHERE k.kontoHaslo = :kontoHaslo"),
    @NamedQuery(name = "Konto.findByKontoUprawnienia", query = "SELECT k FROM Konto k WHERE k.kontoUprawnienia = :kontoUprawnienia")})
public class Konto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "konto_id")
    private Integer kontoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "konto_login")
    private String kontoLogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "konto_haslo")
    private String kontoHaslo;
    @Size(max = 45)
    @Column(name = "konto_uprawnienia")
    private String kontoUprawnienia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pracownikKontoIdFk")
    private Collection<Pracownik> pracownikCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klientKontoIdFk")
    private Collection<Klient> klientCollection;

    public Konto() {
    }

    public Konto(Integer kontoId) {
        this.kontoId = kontoId;
    }

    public Konto(Integer kontoId, String kontoLogin, String kontoHaslo) {
        this.kontoId = kontoId;
        this.kontoLogin = kontoLogin;
        this.kontoHaslo = kontoHaslo;
    }

    public Integer getKontoId() {
        return kontoId;
    }

    public void setKontoId(Integer kontoId) {
        this.kontoId = kontoId;
    }

    public String getKontoLogin() {
        return kontoLogin;
    }

    public void setKontoLogin(String kontoLogin) {
        this.kontoLogin = kontoLogin;
    }

    public String getKontoHaslo() {
        return kontoHaslo;
    }

    public void setKontoHaslo(String kontoHaslo) {
        this.kontoHaslo = kontoHaslo;
    }

    public String getKontoUprawnienia() {
        return kontoUprawnienia;
    }

    public void setKontoUprawnienia(String kontoUprawnienia) {
        this.kontoUprawnienia = kontoUprawnienia;
    }

    @XmlTransient
    public Collection<Pracownik> getPracownikCollection() {
        return pracownikCollection;
    }

    public void setPracownikCollection(Collection<Pracownik> pracownikCollection) {
        this.pracownikCollection = pracownikCollection;
    }

    @XmlTransient
    public Collection<Klient> getKlientCollection() {
        return klientCollection;
    }

    public void setKlientCollection(Collection<Klient> klientCollection) {
        this.klientCollection = klientCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kontoId != null ? kontoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Konto)) {
            return false;
        }
        Konto other = (Konto) object;
        if ((this.kontoId == null && other.kontoId != null) || (this.kontoId != null && !this.kontoId.equals(other.kontoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ubezpieczalnia.model.Konto[ kontoId=" + kontoId + " ]";
    }
    
}
