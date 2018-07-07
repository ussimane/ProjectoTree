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
@Table(name = "actividadeprofissao", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividadeprofissao.findAll", query = "SELECT a FROM Actividadeprofissao a"),
    @NamedQuery(name = "Actividadeprofissao.findByIdactividadeprofissao", query = "SELECT a FROM Actividadeprofissao a WHERE a.idactividadeprofissao = :idactividadeprofissao"),
    @NamedQuery(name = "Actividadeprofissao.findByAnosprofissao", query = "SELECT a FROM Actividadeprofissao a WHERE a.anosprofissao = :anosprofissao")})
public class Actividadeprofissao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idactividadeprofissao", nullable = false)
    private Integer idactividadeprofissao;
    @Column(name = "anosprofissao", length = 45)
    private String anosprofissao;
    @OneToMany(mappedBy = "idanosoficina")
    private Collection<Oficinamestre> oficinamestreCollection;
    @OneToMany(mappedBy = "idanosexperiencia")
    private Collection<Oficinamestre> oficinamestreCollection1;

    public Actividadeprofissao() {
    }

    public Actividadeprofissao(Integer idactividadeprofissao) {
        this.idactividadeprofissao = idactividadeprofissao;
    }

    public Integer getIdactividadeprofissao() {
        return idactividadeprofissao;
    }

    public void setIdactividadeprofissao(Integer idactividadeprofissao) {
        this.idactividadeprofissao = idactividadeprofissao;
    }

    public String getAnosprofissao() {
        return anosprofissao;
    }

    public void setAnosprofissao(String anosprofissao) {
        this.anosprofissao = anosprofissao;
    }

    @XmlTransient
    public Collection<Oficinamestre> getOficinamestreCollection() {
        return oficinamestreCollection;
    }

    public void setOficinamestreCollection(Collection<Oficinamestre> oficinamestreCollection) {
        this.oficinamestreCollection = oficinamestreCollection;
    }

    @XmlTransient
    public Collection<Oficinamestre> getOficinamestreCollection1() {
        return oficinamestreCollection1;
    }

    public void setOficinamestreCollection1(Collection<Oficinamestre> oficinamestreCollection1) {
        this.oficinamestreCollection1 = oficinamestreCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idactividadeprofissao != null ? idactividadeprofissao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividadeprofissao)) {
            return false;
        }
        Actividadeprofissao other = (Actividadeprofissao) object;
        if ((this.idactividadeprofissao == null && other.idactividadeprofissao != null) || (this.idactividadeprofissao != null && !this.idactividadeprofissao.equals(other.idactividadeprofissao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return anosprofissao;
    }
    
}
