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
@Table(name = "investimento", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Investimento.findAll", query = "SELECT i FROM Investimento i"),
    @NamedQuery(name = "Investimento.findByIdinvestimento", query = "SELECT i FROM Investimento i WHERE i.idinvestimento = :idinvestimento"),
    @NamedQuery(name = "Investimento.findByInvestimento", query = "SELECT i FROM Investimento i WHERE i.investimento = :investimento")})
public class Investimento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idinvestimento", nullable = false)
    private Integer idinvestimento;
    @Column(name = "investimento", length = 45)
    private String investimento;
    @OneToMany(mappedBy = "idanoinvestimentosequipamentos")
    private Collection<Oficinamestre> oficinamestreCollection;

    public Investimento() {
    }

    public Investimento(Integer idinvestimento) {
        this.idinvestimento = idinvestimento;
    }

    public Integer getIdinvestimento() {
        return idinvestimento;
    }

    public void setIdinvestimento(Integer idinvestimento) {
        this.idinvestimento = idinvestimento;
    }

    public String getInvestimento() {
        return investimento;
    }

    public void setInvestimento(String investimento) {
        this.investimento = investimento;
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
        hash += (idinvestimento != null ? idinvestimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Investimento)) {
            return false;
        }
        Investimento other = (Investimento) object;
        if ((this.idinvestimento == null && other.idinvestimento != null) || (this.idinvestimento != null && !this.idinvestimento.equals(other.idinvestimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return investimento;
    }
    
}
