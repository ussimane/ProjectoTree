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
@Table(name = "trabalhador", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trabalhador.findAll", query = "SELECT t FROM Trabalhador t"),
    @NamedQuery(name = "Trabalhador.findByIdtabalhador", query = "SELECT t FROM Trabalhador t WHERE t.idtabalhador = :idtabalhador"),
    @NamedQuery(name = "Trabalhador.findByNumerotrab", query = "SELECT t FROM Trabalhador t WHERE t.numerotrab = :numerotrab")})
public class Trabalhador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtabalhador", nullable = false)
    private Integer idtabalhador;
    @Column(name = "numerotrab", length = 45)
    private String numerotrab;
    @OneToMany(mappedBy = "nrtrabalhador")
    private Collection<Oficinamestre> oficinamestreCollection;

    public Trabalhador() {
    }

    public Trabalhador(Integer idtabalhador) {
        this.idtabalhador = idtabalhador;
    }

    public Integer getIdtabalhador() {
        return idtabalhador;
    }

    public void setIdtabalhador(Integer idtabalhador) {
        this.idtabalhador = idtabalhador;
    }

    public String getNumerotrab() {
        return numerotrab;
    }

    public void setNumerotrab(String numerotrab) {
        this.numerotrab = numerotrab;
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
        hash += (idtabalhador != null ? idtabalhador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trabalhador)) {
            return false;
        }
        Trabalhador other = (Trabalhador) object;
        if ((this.idtabalhador == null && other.idtabalhador != null) || (this.idtabalhador != null && !this.idtabalhador.equals(other.idtabalhador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Trabalhador[ idtabalhador=" + idtabalhador + " ]";
    }
    
}
