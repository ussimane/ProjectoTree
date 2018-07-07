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
@Table(name = "distrito", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Distrito.findAll", query = "SELECT d FROM Distrito d"),
    @NamedQuery(name = "Distrito.findByIddistrito", query = "SELECT d FROM Distrito d WHERE d.iddistrito = :iddistrito"),
    @NamedQuery(name = "Distrito.findByDistrito", query = "SELECT d FROM Distrito d WHERE d.distrito = :distrito")})
public class Distrito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddistrito", nullable = false)
    private Integer iddistrito;
    @Column(name = "distrito", length = 45)
    private String distrito;
    @OneToMany(mappedBy = "iddistrito")
    private Collection<Posto> postoCollection;
    @OneToMany(mappedBy = "iddistrito")
    private Collection<Pontofocal> pontofocalCollection;

    public Distrito() {
    }

    public Distrito(Integer iddistrito) {
        this.iddistrito = iddistrito;
    }

    public Integer getIddistrito() {
        return iddistrito;
    }

    public void setIddistrito(Integer iddistrito) {
        this.iddistrito = iddistrito;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    @XmlTransient
    public Collection<Posto> getPostoCollection() {
        return postoCollection;
    }

    public void setPostoCollection(Collection<Posto> postoCollection) {
        this.postoCollection = postoCollection;
    }

    @XmlTransient
    public Collection<Pontofocal> getPontofocalCollection() {
        return pontofocalCollection;
    }

    public void setPontofocalCollection(Collection<Pontofocal> pontofocalCollection) {
        this.pontofocalCollection = pontofocalCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddistrito != null ? iddistrito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distrito)) {
            return false;
        }
        Distrito other = (Distrito) object;
        if ((this.iddistrito == null && other.iddistrito != null) || (this.iddistrito != null && !this.iddistrito.equals(other.iddistrito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return distrito;
    }
    
}
