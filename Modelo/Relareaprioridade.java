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
@Table(name = "relareaprioridade", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relareaprioridade.findAll", query = "SELECT r FROM Relareaprioridade r")})
public class Relareaprioridade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "distrito", length = 45)
    private String distrito;
    @Column(name = "posto", length = 45)
    private String posto;
    @Id
    @Basic(optional = false)
    @Column(name = "localidade", nullable = false, length = 45)
    private String localidade;
    @Column(name = "p1h")
    private Integer p1h;
    @Column(name = "p1m")
    private Integer p1m;
    @Column(name = "p1t")
    private Integer p1t;
    @Column(name = "p2h")
    private Integer p2h;
    @Column(name = "p2m")
    private Integer p2m;
    @Column(name = "p2t")
    private Integer p2t;
    @Column(name = "p3h")
    private Integer p3h;
    @Column(name = "p3m")
    private Integer p3m;
    @Column(name = "p3t")
    private Integer p3t;

    public Relareaprioridade() {
    }

    public Relareaprioridade(String localidade) {
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

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public Integer getP1h() {
        return p1h;
    }

    public void setP1h(Integer p1h) {
        this.p1h = p1h;
    }

    public Integer getP1m() {
        return p1m;
    }

    public void setP1m(Integer p1m) {
        this.p1m = p1m;
    }

    public Integer getP1t() {
        return p1t;
    }

    public void setP1t(Integer p1t) {
        this.p1t = p1t;
    }

    public Integer getP2h() {
        return p2h;
    }

    public void setP2h(Integer p2h) {
        this.p2h = p2h;
    }

    public Integer getP2m() {
        return p2m;
    }

    public void setP2m(Integer p2m) {
        this.p2m = p2m;
    }

    public Integer getP2t() {
        return p2t;
    }

    public void setP2t(Integer p2t) {
        this.p2t = p2t;
    }

    public Integer getP3h() {
        return p3h;
    }

    public void setP3h(Integer p3h) {
        this.p3h = p3h;
    }

    public Integer getP3m() {
        return p3m;
    }

    public void setP3m(Integer p3m) {
        this.p3m = p3m;
    }

    public Integer getP3t() {
        return p3t;
    }

    public void setP3t(Integer p3t) {
        this.p3t = p3t;
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
        if (!(object instanceof Relareaprioridade)) {
            return false;
        }
        Relareaprioridade other = (Relareaprioridade) object;
        if ((this.localidade == null && other.localidade != null) || (this.localidade != null && !this.localidade.equals(other.localidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Relareaprioridade[ localidade=" + localidade + " ]";
    }
    
}
