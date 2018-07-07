/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "curso", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findByIdcurso", query = "SELECT c FROM Curso c WHERE c.idcurso = :idcurso"),
    @NamedQuery(name = "Curso.findByCurso", query = "SELECT c FROM Curso c WHERE c.curso = :curso")})
public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcurso", nullable = false)
    private Integer idcurso;
    @Column(name = "curso", length = 45)
    private String curso;
    @OneToMany(mappedBy = "idcurso")
    private Collection<Accaoformacao> accaoformacaoCollection;
    @OneToMany(mappedBy = "idcurso")
    private Collection<Cursoformacao> cursoformacaoCollection;
    @OneToMany(mappedBy = "idcurso")
    private Collection<Produtos> produtosCollection;
    @OneToMany(mappedBy = "idcurso")
    private Collection<Inscritos> inscritosCollection;
    @OneToMany(mappedBy = "idcurso")
    private Collection<Mestre> mestreCollection;
    @OneToMany(mappedBy = "idcurso3")
    private Collection<Areadeformacao> areadeformacaoCollection;
    @OneToMany(mappedBy = "idcurso2")
    private Collection<Areadeformacao> areadeformacaoCollection1;
    @OneToMany(mappedBy = "idcurso1")
    private Collection<Areadeformacao> areadeformacaoCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcurso")
    private Collection<Formando> formandoCollection;

    public Curso() {
    }

    public Curso(Integer idcurso) {
        this.idcurso = idcurso;
    }

    public Integer getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(Integer idcurso) {
        this.idcurso = idcurso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @XmlTransient
    public Collection<Accaoformacao> getAccaoformacaoCollection() {
        return accaoformacaoCollection;
    }

    public void setAccaoformacaoCollection(Collection<Accaoformacao> accaoformacaoCollection) {
        this.accaoformacaoCollection = accaoformacaoCollection;
    }

    @XmlTransient
    public Collection<Cursoformacao> getCursoformacaoCollection() {
        return cursoformacaoCollection;
    }

    public void setCursoformacaoCollection(Collection<Cursoformacao> cursoformacaoCollection) {
        this.cursoformacaoCollection = cursoformacaoCollection;
    }

    @XmlTransient
    public Collection<Produtos> getProdutosCollection() {
        return produtosCollection;
    }

    public void setProdutosCollection(Collection<Produtos> produtosCollection) {
        this.produtosCollection = produtosCollection;
    }

    @XmlTransient
    public Collection<Inscritos> getInscritosCollection() {
        return inscritosCollection;
    }

    public void setInscritosCollection(Collection<Inscritos> inscritosCollection) {
        this.inscritosCollection = inscritosCollection;
    }

    @XmlTransient
    public Collection<Mestre> getMestreCollection() {
        return mestreCollection;
    }

    public void setMestreCollection(Collection<Mestre> mestreCollection) {
        this.mestreCollection = mestreCollection;
    }

    @XmlTransient
    public Collection<Areadeformacao> getAreadeformacaoCollection() {
        return areadeformacaoCollection;
    }

    public void setAreadeformacaoCollection(Collection<Areadeformacao> areadeformacaoCollection) {
        this.areadeformacaoCollection = areadeformacaoCollection;
    }

    @XmlTransient
    public Collection<Areadeformacao> getAreadeformacaoCollection1() {
        return areadeformacaoCollection1;
    }

    public void setAreadeformacaoCollection1(Collection<Areadeformacao> areadeformacaoCollection1) {
        this.areadeformacaoCollection1 = areadeformacaoCollection1;
    }

    @XmlTransient
    public Collection<Areadeformacao> getAreadeformacaoCollection2() {
        return areadeformacaoCollection2;
    }

    public void setAreadeformacaoCollection2(Collection<Areadeformacao> areadeformacaoCollection2) {
        this.areadeformacaoCollection2 = areadeformacaoCollection2;
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
        hash += (idcurso != null ? idcurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.idcurso == null && other.idcurso != null) || (this.idcurso != null && !this.idcurso.equals(other.idcurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return curso;
    }
    
}
