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
@Table(name = "nivelhabilitacao", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nivelhabilitacao.findAll", query = "SELECT n FROM Nivelhabilitacao n"),
    @NamedQuery(name = "Nivelhabilitacao.findByIdnivelhabilitacao", query = "SELECT n FROM Nivelhabilitacao n WHERE n.idnivelhabilitacao = :idnivelhabilitacao"),
    @NamedQuery(name = "Nivelhabilitacao.findByNivelhabilitacao", query = "SELECT n FROM Nivelhabilitacao n WHERE n.nivelhabilitacao = :nivelhabilitacao")})
public class Nivelhabilitacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnivelhabilitacao", nullable = false)
    private Integer idnivelhabilitacao;
    @Column(name = "nivelhabilitacao", length = 45)
    private String nivelhabilitacao;
    @OneToMany(mappedBy = "idnivelhabilitacao")
    private Collection<Mestre> mestreCollection;
    @OneToMany(mappedBy = "idnivelhabilitacao")
    private Collection<Pontofocal> pontofocalCollection;
    @OneToMany(mappedBy = "idnivelhabilitacao")
    private Collection<Formando> formandoCollection;

    public Nivelhabilitacao() {
    }

    public Nivelhabilitacao(Integer idnivelhabilitacao) {
        this.idnivelhabilitacao = idnivelhabilitacao;
    }

    public Integer getIdnivelhabilitacao() {
        return idnivelhabilitacao;
    }

    public void setIdnivelhabilitacao(Integer idnivelhabilitacao) {
        this.idnivelhabilitacao = idnivelhabilitacao;
    }

    public String getNivelhabilitacao() {
        return nivelhabilitacao;
    }

    public void setNivelhabilitacao(String nivelhabilitacao) {
        this.nivelhabilitacao = nivelhabilitacao;
    }

    @XmlTransient
    public Collection<Mestre> getMestreCollection() {
        return mestreCollection;
    }

    public void setMestreCollection(Collection<Mestre> mestreCollection) {
        this.mestreCollection = mestreCollection;
    }

    @XmlTransient
    public Collection<Pontofocal> getPontofocalCollection() {
        return pontofocalCollection;
    }

    public void setPontofocalCollection(Collection<Pontofocal> pontofocalCollection) {
        this.pontofocalCollection = pontofocalCollection;
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
        hash += (idnivelhabilitacao != null ? idnivelhabilitacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nivelhabilitacao)) {
            return false;
        }
        Nivelhabilitacao other = (Nivelhabilitacao) object;
        if ((this.idnivelhabilitacao == null && other.idnivelhabilitacao != null) || (this.idnivelhabilitacao != null && !this.idnivelhabilitacao.equals(other.idnivelhabilitacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nivelhabilitacao;
    }
    
}
