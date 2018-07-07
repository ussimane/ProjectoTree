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
@Table(name = "areaespecializacao", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Areaespecializacao.findAll", query = "SELECT a FROM Areaespecializacao a"),
    @NamedQuery(name = "Areaespecializacao.findByIdformador", query = "SELECT a FROM Areaespecializacao a WHERE a.idformador = :idformador")})
public class Areaespecializacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idformador", nullable = false)
    private Integer idformador;
    @JoinColumn(name = "idformador", referencedColumnName = "idformador", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Formador formador;
    @JoinColumn(name = "idarea3", referencedColumnName = "idareacurso")
    @ManyToOne
    private Areacurso idarea3;
    @JoinColumn(name = "idarea2", referencedColumnName = "idareacurso")
    @ManyToOne
    private Areacurso idarea2;
    @JoinColumn(name = "idarea1", referencedColumnName = "idareacurso")
    @ManyToOne
    private Areacurso idarea1;

    public Areaespecializacao() {
    }

    public Areaespecializacao(Integer idformador) {
        this.idformador = idformador;
    }

    public Integer getIdformador() {
        return idformador;
    }

    public void setIdformador(Integer idformador) {
        this.idformador = idformador;
    }

    public Formador getFormador() {
        return formador;
    }

    public void setFormador(Formador formador) {
        this.formador = formador;
    }

    public Areacurso getIdarea3() {
        return idarea3;
    }

    public void setIdarea3(Areacurso idarea3) {
        this.idarea3 = idarea3;
    }

    public Areacurso getIdarea2() {
        return idarea2;
    }

    public void setIdarea2(Areacurso idarea2) {
        this.idarea2 = idarea2;
    }

    public Areacurso getIdarea1() {
        return idarea1;
    }

    public void setIdarea1(Areacurso idarea1) {
        this.idarea1 = idarea1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idformador != null ? idformador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Areaespecializacao)) {
            return false;
        }
        Areaespecializacao other = (Areaespecializacao) object;
        if ((this.idformador == null && other.idformador != null) || (this.idformador != null && !this.idformador.equals(other.idformador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Areaespecializacao[ idformador=" + idformador + " ]";
    }
    
}
