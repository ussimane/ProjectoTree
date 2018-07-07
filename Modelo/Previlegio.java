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
@Table(name = "previlegio", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Previlegio.findAll", query = "SELECT p FROM Previlegio p"),
    @NamedQuery(name = "Previlegio.findByIdprevilegio", query = "SELECT p FROM Previlegio p WHERE p.idprevilegio = :idprevilegio"),
    @NamedQuery(name = "Previlegio.findByPrevilegio", query = "SELECT p FROM Previlegio p WHERE p.previlegio = :previlegio")})
public class Previlegio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprevilegio", nullable = false)
    private Integer idprevilegio;
    @Column(name = "previlegio", length = 45)
    private String previlegio;
    @OneToMany(mappedBy = "idprevilegio")
    private Collection<Utilizador> utilizadorCollection;

    public Previlegio() {
    }

    public Previlegio(Integer idprevilegio) {
        this.idprevilegio = idprevilegio;
    }

    public Integer getIdprevilegio() {
        return idprevilegio;
    }

    public void setIdprevilegio(Integer idprevilegio) {
        this.idprevilegio = idprevilegio;
    }

    public String getPrevilegio() {
        return previlegio;
    }

    public void setPrevilegio(String previlegio) {
        this.previlegio = previlegio;
    }

    @XmlTransient
    public Collection<Utilizador> getUtilizadorCollection() {
        return utilizadorCollection;
    }

    public void setUtilizadorCollection(Collection<Utilizador> utilizadorCollection) {
        this.utilizadorCollection = utilizadorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprevilegio != null ? idprevilegio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Previlegio)) {
            return false;
        }
        Previlegio other = (Previlegio) object;
        if ((this.idprevilegio == null && other.idprevilegio != null) || (this.idprevilegio != null && !this.idprevilegio.equals(other.idprevilegio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return previlegio;
    }
    
}
