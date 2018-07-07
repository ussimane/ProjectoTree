/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MASSINGUE
 */
@Entity
@Table(name = "inscritos", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inscritos.findAll", query = "SELECT i FROM Inscritos i"),
    @NamedQuery(name = "Inscritos.findByIdinscritos", query = "SELECT i FROM Inscritos i WHERE i.idinscritos = :idinscritos"),
    @NamedQuery(name = "Inscritos.findByIdlocalidade", query = "SELECT i FROM Inscritos i WHERE i.idlocalidade = :idlocalidade"),
    @NamedQuery(name = "Inscritos.findByHomens", query = "SELECT i FROM Inscritos i WHERE i.homens = :homens"),
    @NamedQuery(name = "Inscritos.findByMulheres", query = "SELECT i FROM Inscritos i WHERE i.mulheres = :mulheres"),
    @NamedQuery(name = "Inscritos.findByTotal", query = "SELECT i FROM Inscritos i WHERE i.total = :total")})
public class Inscritos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idinscritos", nullable = false)
    private Integer idinscritos;
    @Column(name = "idlocalidade")
    private Integer idlocalidade;
    @Column(name = "homens")
    private Integer homens;
    @Column(name = "mulheres")
    private Integer mulheres;
    @Column(name = "total")
    private Integer total;
    @JoinColumn(name = "idcurso", referencedColumnName = "idcurso")
    @ManyToOne
    private Curso idcurso;

    public Inscritos() {
    }

    public Inscritos(Integer idinscritos) {
        this.idinscritos = idinscritos;
    }

    public Integer getIdinscritos() {
        return idinscritos;
    }

    public void setIdinscritos(Integer idinscritos) {
        this.idinscritos = idinscritos;
    }

    public Integer getIdlocalidade() {
        return idlocalidade;
    }

    public void setIdlocalidade(Integer idlocalidade) {
        this.idlocalidade = idlocalidade;
    }

    public Integer getHomens() {
        return homens;
    }

    public void setHomens(Integer homens) {
        this.homens = homens;
    }

    public Integer getMulheres() {
        return mulheres;
    }

    public void setMulheres(Integer mulheres) {
        this.mulheres = mulheres;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Curso getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(Curso idcurso) {
        this.idcurso = idcurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinscritos != null ? idinscritos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inscritos)) {
            return false;
        }
        Inscritos other = (Inscritos) object;
        if ((this.idinscritos == null && other.idinscritos != null) || (this.idinscritos != null && !this.idinscritos.equals(other.idinscritos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Inscritos[ idinscritos=" + idinscritos + " ]";
    }
    
}
