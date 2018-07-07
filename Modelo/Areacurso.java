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
@Table(name = "areacurso", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Areacurso.findAll", query = "SELECT a FROM Areacurso a"),
    @NamedQuery(name = "Areacurso.findByIdareacurso", query = "SELECT a FROM Areacurso a WHERE a.idareacurso = :idareacurso"),
    @NamedQuery(name = "Areacurso.findByEspecializacao", query = "SELECT a FROM Areacurso a WHERE a.especializacao = :especializacao")})
public class Areacurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idareacurso", nullable = false)
    private Integer idareacurso;
    @Column(name = "especializacao", length = 45)
    private String especializacao;
    @OneToMany(mappedBy = "idarea3")
    private Collection<Areaespecializacao> areaespecializacaoCollection;
    @OneToMany(mappedBy = "idarea2")
    private Collection<Areaespecializacao> areaespecializacaoCollection1;
    @OneToMany(mappedBy = "idarea1")
    private Collection<Areaespecializacao> areaespecializacaoCollection2;

    public Areacurso() {
    }

    public Areacurso(Integer idareacurso) {
        this.idareacurso = idareacurso;
    }

    public Integer getIdareacurso() {
        return idareacurso;
    }

    public void setIdareacurso(Integer idareacurso) {
        this.idareacurso = idareacurso;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }

    @XmlTransient
    public Collection<Areaespecializacao> getAreaespecializacaoCollection() {
        return areaespecializacaoCollection;
    }

    public void setAreaespecializacaoCollection(Collection<Areaespecializacao> areaespecializacaoCollection) {
        this.areaespecializacaoCollection = areaespecializacaoCollection;
    }

    @XmlTransient
    public Collection<Areaespecializacao> getAreaespecializacaoCollection1() {
        return areaespecializacaoCollection1;
    }

    public void setAreaespecializacaoCollection1(Collection<Areaespecializacao> areaespecializacaoCollection1) {
        this.areaespecializacaoCollection1 = areaespecializacaoCollection1;
    }

    @XmlTransient
    public Collection<Areaespecializacao> getAreaespecializacaoCollection2() {
        return areaespecializacaoCollection2;
    }

    public void setAreaespecializacaoCollection2(Collection<Areaespecializacao> areaespecializacaoCollection2) {
        this.areaespecializacaoCollection2 = areaespecializacaoCollection2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idareacurso != null ? idareacurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Areacurso)) {
            return false;
        }
        Areacurso other = (Areacurso) object;
        if ((this.idareacurso == null && other.idareacurso != null) || (this.idareacurso != null && !this.idareacurso.equals(other.idareacurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Areacurso[ idareacurso=" + idareacurso + " ]";
    }
    
}
