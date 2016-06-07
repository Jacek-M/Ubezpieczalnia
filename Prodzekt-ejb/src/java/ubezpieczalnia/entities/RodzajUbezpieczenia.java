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
 * @author Jacek
 */
@Entity
@Table(name = "rodzaj_ubezpieczenia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RodzajUbezpieczenia.findAll", query = "SELECT r FROM RodzajUbezpieczenia r"),
    @NamedQuery(name = "RodzajUbezpieczenia.findByRodzajUbezpieczeniaId", query = "SELECT r FROM RodzajUbezpieczenia r WHERE r.rodzajUbezpieczeniaId = :rodzajUbezpieczeniaId"),
    @NamedQuery(name = "RodzajUbezpieczenia.findByRodzajUbezpieczeniaRodzaj", query = "SELECT r FROM RodzajUbezpieczenia r WHERE r.rodzajUbezpieczeniaRodzaj = :rodzajUbezpieczeniaRodzaj"),
    @NamedQuery(name = "RodzajUbezpieczenia.findByRodzajUbezpieczeniaCena", query = "SELECT r FROM RodzajUbezpieczenia r WHERE r.rodzajUbezpieczeniaCena = :rodzajUbezpieczeniaCena"),
    @NamedQuery(name = "RodzajUbezpieczenia.findByRodzajUbezpieczeniaCzyZastepczy", query = "SELECT r FROM RodzajUbezpieczenia r WHERE r.rodzajUbezpieczeniaCzyZastepczy = :rodzajUbezpieczeniaCzyZastepczy"),
    @NamedQuery(name = "RodzajUbezpieczenia.findByRodzajUbezpieczeniaNazwa", query = "SELECT r FROM RodzajUbezpieczenia r WHERE r.rodzajUbezpieczeniaNazwa = :rodzajUbezpieczeniaNazwa"),
    @NamedQuery(name = "RodzajUbezpieczenia.findByRodzajUbezpieczeniaOpis", query = "SELECT r FROM RodzajUbezpieczenia r WHERE r.rodzajUbezpieczeniaOpis = :rodzajUbezpieczeniaOpis")})
public class RodzajUbezpieczenia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rodzaj_ubezpieczenia_id")
    private Integer rodzajUbezpieczeniaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "rodzaj_ubezpieczenia_rodzaj")
    private String rodzajUbezpieczeniaRodzaj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "rodzaj_ubezpieczenia_cena")
    private String rodzajUbezpieczeniaCena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rodzaj_ubezpieczenia_czy_zastepczy")
    private int rodzajUbezpieczeniaCzyZastepczy;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "rodzaj_ubezpieczenia_nazwa")
    private String rodzajUbezpieczeniaNazwa;
    @Size(max = 200)
    @Column(name = "rodzaj_ubezpieczenia_opis")
    private String rodzajUbezpieczeniaOpis;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "umowaRodzajUbezpieczeniaIdFk")
    private Collection<Umowa> umowaCollection;

    public RodzajUbezpieczenia() {
    }

    public RodzajUbezpieczenia(Integer rodzajUbezpieczeniaId) {
        this.rodzajUbezpieczeniaId = rodzajUbezpieczeniaId;
    }

    public RodzajUbezpieczenia(Integer rodzajUbezpieczeniaId, String rodzajUbezpieczeniaRodzaj, String rodzajUbezpieczeniaCena, int rodzajUbezpieczeniaCzyZastepczy, String rodzajUbezpieczeniaNazwa) {
        this.rodzajUbezpieczeniaId = rodzajUbezpieczeniaId;
        this.rodzajUbezpieczeniaRodzaj = rodzajUbezpieczeniaRodzaj;
        this.rodzajUbezpieczeniaCena = rodzajUbezpieczeniaCena;
        this.rodzajUbezpieczeniaCzyZastepczy = rodzajUbezpieczeniaCzyZastepczy;
        this.rodzajUbezpieczeniaNazwa = rodzajUbezpieczeniaNazwa;
    }

    public Integer getRodzajUbezpieczeniaId() {
        return rodzajUbezpieczeniaId;
    }

    public void setRodzajUbezpieczeniaId(Integer rodzajUbezpieczeniaId) {
        this.rodzajUbezpieczeniaId = rodzajUbezpieczeniaId;
    }

    public String getRodzajUbezpieczeniaRodzaj() {
        return rodzajUbezpieczeniaRodzaj;
    }

    public void setRodzajUbezpieczeniaRodzaj(String rodzajUbezpieczeniaRodzaj) {
        this.rodzajUbezpieczeniaRodzaj = rodzajUbezpieczeniaRodzaj;
    }

    public String getRodzajUbezpieczeniaCena() {
        return rodzajUbezpieczeniaCena;
    }

    public void setRodzajUbezpieczeniaCena(String rodzajUbezpieczeniaCena) {
        this.rodzajUbezpieczeniaCena = rodzajUbezpieczeniaCena;
    }

    public int getRodzajUbezpieczeniaCzyZastepczy() {
        return rodzajUbezpieczeniaCzyZastepczy;
    }
    
    public String getZastepczyStatus() {
        return rodzajUbezpieczeniaCzyZastepczy == 1 ? "TAK" : "NIE";
    }

    public void setRodzajUbezpieczeniaCzyZastepczy(int rodzajUbezpieczeniaCzyZastepczy) {
        this.rodzajUbezpieczeniaCzyZastepczy = rodzajUbezpieczeniaCzyZastepczy;
    }

    public String getRodzajUbezpieczeniaNazwa() {
        return rodzajUbezpieczeniaNazwa;
    }

    public void setRodzajUbezpieczeniaNazwa(String rodzajUbezpieczeniaNazwa) {
        this.rodzajUbezpieczeniaNazwa = rodzajUbezpieczeniaNazwa;
    }

    public String getRodzajUbezpieczeniaOpis() {
        return rodzajUbezpieczeniaOpis;
    }

    public void setRodzajUbezpieczeniaOpis(String rodzajUbezpieczeniaOpis) {
        this.rodzajUbezpieczeniaOpis = rodzajUbezpieczeniaOpis;
    }

    @XmlTransient
    public Collection<Umowa> getUmowaCollection() {
        return umowaCollection;
    }

    public void setUmowaCollection(Collection<Umowa> umowaCollection) {
        this.umowaCollection = umowaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rodzajUbezpieczeniaId != null ? rodzajUbezpieczeniaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RodzajUbezpieczenia)) {
            return false;
        }
        RodzajUbezpieczenia other = (RodzajUbezpieczenia) object;
        if ((this.rodzajUbezpieczeniaId == null && other.rodzajUbezpieczeniaId != null) || (this.rodzajUbezpieczeniaId != null && !this.rodzajUbezpieczeniaId.equals(other.rodzajUbezpieczeniaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ubezpieczalnia.entities.RodzajUbezpieczenia[ rodzajUbezpieczeniaId=" + rodzajUbezpieczeniaId + " ]";
    }
    
}
