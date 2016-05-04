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
@Table(name = "adres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adres.findAll", query = "SELECT a FROM Adres a"),
    @NamedQuery(name = "Adres.findByAdresId", query = "SELECT a FROM Adres a WHERE a.adresId = :adresId"),
    @NamedQuery(name = "Adres.findByAdresUlica", query = "SELECT a FROM Adres a WHERE a.adresUlica = :adresUlica"),
    @NamedQuery(name = "Adres.findByAdresMiejscowosc", query = "SELECT a FROM Adres a WHERE a.adresMiejscowosc = :adresMiejscowosc"),
    @NamedQuery(name = "Adres.findByAdresKodPocztowy", query = "SELECT a FROM Adres a WHERE a.adresKodPocztowy = :adresKodPocztowy"),
    @NamedQuery(name = "Adres.findByAdresPoczta", query = "SELECT a FROM Adres a WHERE a.adresPoczta = :adresPoczta"),
    @NamedQuery(name = "Adres.findByAdresTelefon", query = "SELECT a FROM Adres a WHERE a.adresTelefon = :adresTelefon"),
    @NamedQuery(name = "Adres.findAdresByCityAndStreet", query = "SELECT a FROM Adres a where a.adresUlica = :adresUlica AND a.adresMiejscowosc = :adresMiejscowosc AND a.adresKodPocztowy = :adresKodPocztowy")})
public class Adres implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "adres_id")
    private Integer adresId;
    @Size(max = 45)
    @Column(name = "adres_ulica")
    private String adresUlica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "adres_miejscowosc")
    private String adresMiejscowosc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "adres_kod_pocztowy")
    private String adresKodPocztowy;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "adres_poczta")
    private String adresPoczta;
    @Size(max = 45)
    @Column(name = "adres_telefon")
    private String adresTelefon;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pracownikAdresIdFk")
    private Collection<Pracownik> pracownikCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zakladAdresIdFk")
    private Collection<Zaklad> zakladCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klientAdresIdFk")
    private Collection<Klient> klientCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "oddzialAdresIdFk")
    private Collection<Oddzial> oddzialCollection;

    public Adres() {
    }

    public Adres(Integer adresId) {
        this.adresId = adresId;
    }

    public Adres(Integer adresId, String adresMiejscowosc, String adresKodPocztowy, String adresPoczta) {
        this.adresId = adresId;
        this.adresMiejscowosc = adresMiejscowosc;
        this.adresKodPocztowy = adresKodPocztowy;
        this.adresPoczta = adresPoczta;
    }

    public Integer getAdresId() {
        return adresId;
    }

    public void setAdresId(Integer adresId) {
        this.adresId = adresId;
    }

    public String getAdresUlica() {
        return adresUlica;
    }

    public void setAdresUlica(String adresUlica) {
        this.adresUlica = adresUlica;
    }

    public String getAdresMiejscowosc() {
        return adresMiejscowosc;
    }

    public void setAdresMiejscowosc(String adresMiejscowosc) {
        this.adresMiejscowosc = adresMiejscowosc;
    }

    public String getAdresKodPocztowy() {
        return adresKodPocztowy;
    }

    public void setAdresKodPocztowy(String adresKodPocztowy) {
        this.adresKodPocztowy = adresKodPocztowy;
    }

    public String getAdresPoczta() {
        return adresPoczta;
    }

    public void setAdresPoczta(String adresPoczta) {
        this.adresPoczta = adresPoczta;
    }

    public String getAdresTelefon() {
        return adresTelefon;
    }

    public void setAdresTelefon(String adresTelefon) {
        this.adresTelefon = adresTelefon;
    }

    @XmlTransient
    public Collection<Pracownik> getPracownikCollection() {
        return pracownikCollection;
    }

    public void setPracownikCollection(Collection<Pracownik> pracownikCollection) {
        this.pracownikCollection = pracownikCollection;
    }

    @XmlTransient
    public Collection<Zaklad> getZakladCollection() {
        return zakladCollection;
    }

    public void setZakladCollection(Collection<Zaklad> zakladCollection) {
        this.zakladCollection = zakladCollection;
    }

    @XmlTransient
    public Collection<Klient> getKlientCollection() {
        return klientCollection;
    }

    public void setKlientCollection(Collection<Klient> klientCollection) {
        this.klientCollection = klientCollection;
    }

    @XmlTransient
    public Collection<Oddzial> getOddzialCollection() {
        return oddzialCollection;
    }

    public void setOddzialCollection(Collection<Oddzial> oddzialCollection) {
        this.oddzialCollection = oddzialCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adresId != null ? adresId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adres)) {
            return false;
        }
        Adres other = (Adres) object;
        if ((this.adresId == null && other.adresId != null) || (this.adresId != null && !this.adresId.equals(other.adresId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ubezpieczalnia.entities.Adres[ adresId=" + adresId + " ]";
    }
    
}
