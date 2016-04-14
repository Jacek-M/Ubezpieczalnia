/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

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
@Table(name = "uczestnik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uczestnik.findAll", query = "SELECT u FROM Uczestnik u"),
    @NamedQuery(name = "Uczestnik.findByUczestnikId", query = "SELECT u FROM Uczestnik u WHERE u.uczestnikId = :uczestnikId"),
    @NamedQuery(name = "Uczestnik.findByUczestnikImie", query = "SELECT u FROM Uczestnik u WHERE u.uczestnikImie = :uczestnikImie"),
    @NamedQuery(name = "Uczestnik.findByUczestnikNazwisko", query = "SELECT u FROM Uczestnik u WHERE u.uczestnikNazwisko = :uczestnikNazwisko"),
    @NamedQuery(name = "Uczestnik.findByUczestnikNumerKonta", query = "SELECT u FROM Uczestnik u WHERE u.uczestnikNumerKonta = :uczestnikNumerKonta")})
public class Uczestnik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "uczestnik_id")
    private Integer uczestnikId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "uczestnik_imie")
    private String uczestnikImie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "uczestnik_nazwisko")
    private String uczestnikNazwisko;
    @Size(max = 45)
    @Column(name = "uczestnik_numer_konta")
    private String uczestnikNumerKonta;
    @OneToMany(mappedBy = "szkodaUczestnikIdFk")
    private Collection<Szkoda> szkodaCollection;

    public Uczestnik() {
    }

    public Uczestnik(Integer uczestnikId) {
        this.uczestnikId = uczestnikId;
    }

    public Uczestnik(Integer uczestnikId, String uczestnikImie, String uczestnikNazwisko) {
        this.uczestnikId = uczestnikId;
        this.uczestnikImie = uczestnikImie;
        this.uczestnikNazwisko = uczestnikNazwisko;
    }

    public Integer getUczestnikId() {
        return uczestnikId;
    }

    public void setUczestnikId(Integer uczestnikId) {
        this.uczestnikId = uczestnikId;
    }

    public String getUczestnikImie() {
        return uczestnikImie;
    }

    public void setUczestnikImie(String uczestnikImie) {
        this.uczestnikImie = uczestnikImie;
    }

    public String getUczestnikNazwisko() {
        return uczestnikNazwisko;
    }

    public void setUczestnikNazwisko(String uczestnikNazwisko) {
        this.uczestnikNazwisko = uczestnikNazwisko;
    }

    public String getUczestnikNumerKonta() {
        return uczestnikNumerKonta;
    }

    public void setUczestnikNumerKonta(String uczestnikNumerKonta) {
        this.uczestnikNumerKonta = uczestnikNumerKonta;
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
        hash += (uczestnikId != null ? uczestnikId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uczestnik)) {
            return false;
        }
        Uczestnik other = (Uczestnik) object;
        if ((this.uczestnikId == null && other.uczestnikId != null) || (this.uczestnikId != null && !this.uczestnikId.equals(other.uczestnikId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Uczestnik[ uczestnikId=" + uczestnikId + " ]";
    }
    
}
