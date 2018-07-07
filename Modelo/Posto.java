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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "posto", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Posto.findAll", query = "SELECT p FROM Posto p"),
    @NamedQuery(name = "Posto.findByIdposto", query = "SELECT p FROM Posto p WHERE p.idposto = :idposto"),
    @NamedQuery(name = "Posto.findByPosto", query = "SELECT p FROM Posto p WHERE p.posto = :posto")})
public class Posto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idposto", nullable = false)
    private Integer idposto;
    @Column(name = "posto", length = 45)
    private String posto;
    @OneToMany(mappedBy = "idposto")
    private Collection<Localidade> localidadeCollection;
    @JoinColumn(name = "iddistrito", referencedColumnName = "iddistrito")
    @ManyToOne
    private Distrito iddistrito;

    public Posto() {
    }

    public Posto(Integer idposto) {
        this.idposto = idposto;
    }

    public Integer getIdposto() {
        return idposto;
    }

    public void setIdposto(Integer idposto) {
        this.idposto = idposto;
    }

    public String getPosto() {
        return posto;
    }

    public void setPosto(String posto) {
        this.posto = posto;
    }

    @XmlTransient
    public Collection<Localidade> getLocalidadeCollection() {
        return localidadeCollection;
    }

    public void setLocalidadeCollection(Collection<Localidade> localidadeCollection) {
        this.localidadeCollection = localidadeCollection;
    }

    public Distrito getIddistrito() {
        return iddistrito;
    }

    public void setIddistrito(Distrito iddistrito) {
        this.iddistrito = iddistrito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idposto != null ? idposto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Posto)) {
            return false;
        }
        Posto other = (Posto) object;
        if ((this.idposto == null && other.idposto != null) || (this.idposto != null && !this.idposto.equals(other.idposto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Posto[ idposto=" + idposto + " ]";
    }
    
}
