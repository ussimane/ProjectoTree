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
@Table(name = "mercado", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mercado.findAll", query = "SELECT m FROM Mercado m"),
    @NamedQuery(name = "Mercado.findByIdmercado", query = "SELECT m FROM Mercado m WHERE m.idmercado = :idmercado"),
    @NamedQuery(name = "Mercado.findByMercado", query = "SELECT m FROM Mercado m WHERE m.mercado = :mercado")})
public class Mercado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmercado", nullable = false)
    private Integer idmercado;
    @Column(name = "mercado", length = 45)
    private String mercado;
    @OneToMany(mappedBy = "idmercado")
    private Collection<Oficinamestre> oficinamestreCollection;

    public Mercado() {
    }

    public Mercado(Integer idmercado) {
        this.idmercado = idmercado;
    }

    public Integer getIdmercado() {
        return idmercado;
    }

    public void setIdmercado(Integer idmercado) {
        this.idmercado = idmercado;
    }

    public String getMercado() {
        return mercado;
    }

    public void setMercado(String mercado) {
        this.mercado = mercado;
    }

    @XmlTransient
    public Collection<Oficinamestre> getOficinamestreCollection() {
        return oficinamestreCollection;
    }

    public void setOficinamestreCollection(Collection<Oficinamestre> oficinamestreCollection) {
        this.oficinamestreCollection = oficinamestreCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmercado != null ? idmercado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mercado)) {
            return false;
        }
        Mercado other = (Mercado) object;
        if ((this.idmercado == null && other.idmercado != null) || (this.idmercado != null && !this.idmercado.equals(other.idmercado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return mercado;
    }
    
}
