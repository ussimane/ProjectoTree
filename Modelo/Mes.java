/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MASSINGUE
 */
@Entity
@Table(name = "mes", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mes.findAll", query = "SELECT m FROM Mes m"),
    @NamedQuery(name = "Mes.findByIdmes", query = "SELECT m FROM Mes m WHERE m.idmes = :idmes"),
    @NamedQuery(name = "Mes.findByMes", query = "SELECT m FROM Mes m WHERE m.mes = :mes")})
public class Mes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmes", nullable = false)
    private Integer idmes;
    @Column(name = "mes", length = 45)
    private String mes;
    @OneToMany(mappedBy = "idmes")
    private Collection<Artigofinal> artigofinalCollection;
    @OneToMany(mappedBy = "idmes")
    private Collection<Artigo> artigoCollection;

    public Mes() {
    }

    public Mes(Integer idmes) {
        this.idmes = idmes;
    }

    public Integer getIdmes() {
        return idmes;
    }

    public void setIdmes(Integer idmes) {
        this.idmes = idmes;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    @XmlTransient
    public Collection<Artigofinal> getArtigofinalCollection() {
        return artigofinalCollection;
    }

    public void setArtigofinalCollection(Collection<Artigofinal> artigofinalCollection) {
        this.artigofinalCollection = artigofinalCollection;
    }

    @XmlTransient
    public Collection<Artigo> getArtigoCollection() {
        return artigoCollection;
    }

    public void setArtigoCollection(Collection<Artigo> artigoCollection) {
        this.artigoCollection = artigoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmes != null ? idmes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mes)) {
            return false;
        }
        Mes other = (Mes) object;
        if ((this.idmes == null && other.idmes != null) || (this.idmes != null && !this.idmes.equals(other.idmes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return mes;
    }
    
}
