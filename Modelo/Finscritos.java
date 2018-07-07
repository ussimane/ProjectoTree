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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MASSINGUE
 */
@Entity
@Table(name = "finscritos", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Finscritos.findAll", query = "SELECT f FROM Finscritos f"),
    @NamedQuery(name = "Finscritos.findByIdlocalidade", query = "SELECT f FROM Finscritos f WHERE f.idlocalidade = :idlocalidade"),
    @NamedQuery(name = "Finscritos.findByQhomem", query = "SELECT f FROM Finscritos f WHERE f.qhomem = :qhomem"),
    @NamedQuery(name = "Finscritos.findByQmulher", query = "SELECT f FROM Finscritos f WHERE f.qmulher = :qmulher"),
    @NamedQuery(name = "Finscritos.findByTotal", query = "SELECT f FROM Finscritos f WHERE f.total = :total")})
public class Finscritos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idlocalidade", nullable = false)
    private Integer idlocalidade;
    @Column(name = "qhomem")
    private Integer qhomem;
    @Column(name = "qmulher")
    private Integer qmulher;
    @Column(name = "total")
    private Integer total;

    public Finscritos() {
    }

    public Finscritos(Integer idlocalidade) {
        this.idlocalidade = idlocalidade;
    }

    public Integer getIdlocalidade() {
        return idlocalidade;
    }

    public void setIdlocalidade(Integer idlocalidade) {
        this.idlocalidade = idlocalidade;
    }

    public Integer getQhomem() {
        return qhomem;
    }

    public void setQhomem(Integer qhomem) {
        this.qhomem = qhomem;
    }

    public Integer getQmulher() {
        return qmulher;
    }

    public void setQmulher(Integer qmulher) {
        this.qmulher = qmulher;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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
        if (!(object instanceof Finscritos)) {
            return false;
        }
        Finscritos other = (Finscritos) object;
        if ((this.idlocalidade == null && other.idlocalidade != null) || (this.idlocalidade != null && !this.idlocalidade.equals(other.idlocalidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Finscritos[ idlocalidade=" + idlocalidade + " ]";
    }
    
}
