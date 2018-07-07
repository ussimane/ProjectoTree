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
@Table(name = "producao", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producao.findAll", query = "SELECT p FROM Producao p"),
    @NamedQuery(name = "Producao.findByIdproducao", query = "SELECT p FROM Producao p WHERE p.idproducao = :idproducao"),
    @NamedQuery(name = "Producao.findByProducao", query = "SELECT p FROM Producao p WHERE p.producao = :producao")})
public class Producao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproducao", nullable = false)
    private Integer idproducao;
    @Column(name = "producao", length = 45)
    private String producao;
    @OneToMany(mappedBy = "idproducao")
    private Collection<Oficinamestre> oficinamestreCollection;

    public Producao() {
    }

    public Producao(Integer idproducao) {
        this.idproducao = idproducao;
    }

    public Integer getIdproducao() {
        return idproducao;
    }

    public void setIdproducao(Integer idproducao) {
        this.idproducao = idproducao;
    }

    public String getProducao() {
        return producao;
    }

    public void setProducao(String producao) {
        this.producao = producao;
    }

    @XmlTransient
    public Collection<Oficinamestre> getOficinamestreCollection() {
        return oficinamestreCollection;
    }

    public void setOficinamestreCollection(Collection<Oficinamestre> oficinamestreCollection) {
        this.oficinamestreCollection = oficinamestreCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproducao != null ? idproducao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producao)) {
            return false;
        }
        Producao other = (Producao) object;
        if ((this.idproducao == null && other.idproducao != null) || (this.idproducao != null && !this.idproducao.equals(other.idproducao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return producao;
    }
    
}
