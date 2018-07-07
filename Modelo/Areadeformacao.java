/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MASSINGUE
 */
@Entity
@Table(name = "areadeformacao", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Areadeformacao.findAll", query = "SELECT a FROM Areadeformacao a"),
    @NamedQuery(name = "Areadeformacao.findByIdformando", query = "SELECT a FROM Areadeformacao a WHERE a.idformando = :idformando")})
public class Areadeformacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idformando", nullable = false)
    private Integer idformando;
    @JoinColumn(name = "idformando", referencedColumnName = "idformando", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Formando formando;
    @JoinColumn(name = "idcurso3", referencedColumnName = "idcurso")
    @ManyToOne
    private Curso idcurso3;
    @JoinColumn(name = "idcurso2", referencedColumnName = "idcurso")
    @ManyToOne
    private Curso idcurso2;
    @JoinColumn(name = "idcurso1", referencedColumnName = "idcurso")
    @ManyToOne
    private Curso idcurso1;

    public Areadeformacao() {
    }

    public Areadeformacao(Integer idformando) {
        this.idformando = idformando;
    }

    public Integer getIdformando() {
        return idformando;
    }

    public void setIdformando(Integer idformando) {
        this.idformando = idformando;
    }

    public Formando getFormando() {
        return formando;
    }

    public void setFormando(Formando formando) {
        this.formando = formando;
    }

    public Curso getIdcurso3() {
        return idcurso3;
    }

    public void setIdcurso3(Curso idcurso3) {
        this.idcurso3 = idcurso3;
    }

    public Curso getIdcurso2() {
        return idcurso2;
    }

    public void setIdcurso2(Curso idcurso2) {
        this.idcurso2 = idcurso2;
    }

    public Curso getIdcurso1() {
        return idcurso1;
    }

    public void setIdcurso1(Curso idcurso1) {
        this.idcurso1 = idcurso1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idformando != null ? idformando.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Areadeformacao)) {
            return false;
        }
        Areadeformacao other = (Areadeformacao) object;
        if ((this.idformando == null && other.idformando != null) || (this.idformando != null && !this.idformando.equals(other.idformando))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Areadeformacao[ idformando=" + idformando + " ]";
    }
    
}
