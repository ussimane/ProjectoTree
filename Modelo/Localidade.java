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
@Table(name = "localidade", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Localidade.findAll", query = "SELECT l FROM Localidade l"),
    @NamedQuery(name = "Localidade.findByIdlocalidade", query = "SELECT l FROM Localidade l WHERE l.idlocalidade = :idlocalidade"),
    @NamedQuery(name = "Localidade.findByLocalidade", query = "SELECT l FROM Localidade l WHERE l.localidade = :localidade")})
public class Localidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlocalidade", nullable = false)
    private Integer idlocalidade;
    @Column(name = "localidade", length = 45)
    private String localidade;
    @JoinColumn(name = "idposto", referencedColumnName = "idposto")
    @ManyToOne
    private Posto idposto;
    @OneToMany(mappedBy = "idlocalidade")
    private Collection<Cursoformacao> cursoformacaoCollection;
    @OneToMany(mappedBy = "idlocalidade")
    private Collection<Mestre> mestreCollection;
    @OneToMany(mappedBy = "iddistrito")
    private Collection<Formador> formadorCollection;
    @OneToMany(mappedBy = "idlocalidade")
    private Collection<Formando> formandoCollection;

    public Localidade() {
    }

    public Localidade(Integer idlocalidade) {
        this.idlocalidade = idlocalidade;
    }

    public Integer getIdlocalidade() {
        return idlocalidade;
    }

    public void setIdlocalidade(Integer idlocalidade) {
        this.idlocalidade = idlocalidade;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public Posto getIdposto() {
        return idposto;
    }

    public void setIdposto(Posto idposto) {
        this.idposto = idposto;
    }

    @XmlTransient
    public Collection<Cursoformacao> getCursoformacaoCollection() {
        return cursoformacaoCollection;
    }

    public void setCursoformacaoCollection(Collection<Cursoformacao> cursoformacaoCollection) {
        this.cursoformacaoCollection = cursoformacaoCollection;
    }

    @XmlTransient
    public Collection<Mestre> getMestreCollection() {
        return mestreCollection;
    }

    public void setMestreCollection(Collection<Mestre> mestreCollection) {
        this.mestreCollection = mestreCollection;
    }

    @XmlTransient
    public Collection<Formador> getFormadorCollection() {
        return formadorCollection;
    }

    public void setFormadorCollection(Collection<Formador> formadorCollection) {
        this.formadorCollection = formadorCollection;
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
        hash += (idlocalidade != null ? idlocalidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Localidade)) {
            return false;
        }
        Localidade other = (Localidade) object;
        if ((this.idlocalidade == null && other.idlocalidade != null) || (this.idlocalidade != null && !this.idlocalidade.equals(other.idlocalidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return localidade;
    }
    
}
