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
@Table(name = "nivelprofissional", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nivelprofissional.findAll", query = "SELECT n FROM Nivelprofissional n"),
    @NamedQuery(name = "Nivelprofissional.findByIdnivelprofissional", query = "SELECT n FROM Nivelprofissional n WHERE n.idnivelprofissional = :idnivelprofissional"),
    @NamedQuery(name = "Nivelprofissional.findByNivelprofissional", query = "SELECT n FROM Nivelprofissional n WHERE n.nivelprofissional = :nivelprofissional")})
public class Nivelprofissional implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnivelprofissional", nullable = false)
    private Integer idnivelprofissional;
    @Column(name = "nivelprofissional", length = 45)
    private String nivelprofissional;
    @OneToMany(mappedBy = "idnivelprof")
    private Collection<Mestre> mestreCollection;
    @OneToMany(mappedBy = "idnivelprof")
    private Collection<Formando> formandoCollection;

    public Nivelprofissional() {
    }

    public Nivelprofissional(Integer idnivelprofissional) {
        this.idnivelprofissional = idnivelprofissional;
    }

    public Integer getIdnivelprofissional() {
        return idnivelprofissional;
    }

    public void setIdnivelprofissional(Integer idnivelprofissional) {
        this.idnivelprofissional = idnivelprofissional;
    }

    public String getNivelprofissional() {
        return nivelprofissional;
    }

    public void setNivelprofissional(String nivelprofissional) {
        this.nivelprofissional = nivelprofissional;
    }

    @XmlTransient
    public Collection<Mestre> getMestreCollection() {
        return mestreCollection;
    }

    public void setMestreCollection(Collection<Mestre> mestreCollection) {
        this.mestreCollection = mestreCollection;
    }

    @XmlTransient
    public Collection<Formando> getFormandoCollection() {
        return formandoCollection;
    }

    public void setFormandoCollection(Collection<Formando> formandoCollection) {
        this.formandoCollection = formandoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnivelprofissional != null ? idnivelprofissional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nivelprofissional)) {
            return false;
        }
        Nivelprofissional other = (Nivelprofissional) object;
        if ((this.idnivelprofissional == null && other.idnivelprofissional != null) || (this.idnivelprofissional != null && !this.idnivelprofissional.equals(other.idnivelprofissional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nivelprofissional;
    }
    
}
