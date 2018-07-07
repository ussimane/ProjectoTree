/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MASSINGUE
 */
@Entity
@Table(name = "relidade", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relidade.findAll", query = "SELECT r FROM Relidade r")})
public class Relidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "localidade", nullable = false, length = 45)
    private String localidade;
    @Column(name = "distrito", length = 45)
    private String distrito;
    @Column(name = "posto", length = 45)
    private String posto;
    @Column(name = "menos1h")
    private Integer menos1h;
    @Column(name = "menos1m")
    private Integer menos1m;
    @Column(name = "meno1t")
    private Integer meno1t;
    @Column(name = "1a5h")
    private Integer a5h;
    @Column(name = "1a5m")
    private Integer a5m;
    @Column(name = "1a5t")
    private Integer a5t;
    @Column(name = "6a10h")
    private Integer a10h;
    @Column(name = "6a10m")
    private Integer a10m;
    @Column(name = "6a10t")
    private Integer a10t;
    @Column(name = "mais10h")
    private Integer mais10h;
    @Column(name = "mais10m")
    private Integer mais10m;
    @Column(name = "mais10t")
    private Integer mais10t;

    public Relidade() {
    }

    public Relidade(String localidade) {
        this.localidade = localidade;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getPosto() {
        return posto;
    }

    public void setPosto(String posto) {
        this.posto = posto;
    }

    public Integer getMenos1h() {
        return menos1h;
    }

    public void setMenos1h(Integer menos1h) {
        this.menos1h = menos1h;
    }

    public Integer getMenos1m() {
        return menos1m;
    }

    public void setMenos1m(Integer menos1m) {
        this.menos1m = menos1m;
    }

    public Integer getMeno1t() {
        return meno1t;
    }

    public void setMeno1t(Integer meno1t) {
        this.meno1t = meno1t;
    }

    public Integer getA5h() {
        return a5h;
    }

    public void setA5h(Integer a5h) {
        this.a5h = a5h;
    }

    public Integer getA5m() {
        return a5m;
    }

    public void setA5m(Integer a5m) {
        this.a5m = a5m;
    }

    public Integer getA5t() {
        return a5t;
    }

    public void setA5t(Integer a5t) {
        this.a5t = a5t;
    }

    public Integer getA10h() {
        return a10h;
    }

    public void setA10h(Integer a10h) {
        this.a10h = a10h;
    }

    public Integer getA10m() {
        return a10m;
    }

    public void setA10m(Integer a10m) {
        this.a10m = a10m;
    }

    public Integer getA10t() {
        return a10t;
    }

    public void setA10t(Integer a10t) {
        this.a10t = a10t;
    }

    public Integer getMais10h() {
        return mais10h;
    }

    public void setMais10h(Integer mais10h) {
        this.mais10h = mais10h;
    }

    public Integer getMais10m() {
        return mais10m;
    }

    public void setMais10m(Integer mais10m) {
        this.mais10m = mais10m;
    }

    public Integer getMais10t() {
        return mais10t;
    }

    public void setMais10t(Integer mais10t) {
        this.mais10t = mais10t;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (localidade != null ? localidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Relidade)) {
            return false;
        }
        Relidade other = (Relidade) object;
        if ((this.localidade == null && other.localidade != null) || (this.localidade != null && !this.localidade.equals(other.localidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Relidade[ localidade=" + localidade + " ]";
    }
    
}
