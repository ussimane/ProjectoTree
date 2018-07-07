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
@Table(name = "equipamento", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipamento.findAll", query = "SELECT e FROM Equipamento e"),
    @NamedQuery(name = "Equipamento.findByIdequipamento", query = "SELECT e FROM Equipamento e WHERE e.idequipamento = :idequipamento"),
    @NamedQuery(name = "Equipamento.findByEquipamento", query = "SELECT e FROM Equipamento e WHERE e.equipamento = :equipamento")})
public class Equipamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idequipamento", nullable = false)
    private Integer idequipamento;
    @Column(name = "equipamento", length = 45)
    private String equipamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipamento")
    private Collection<Equipamentofalta> equipamentofaltaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipamento")
    private Collection<Oficinaequipamento> oficinaequipamentoCollection;

    public Equipamento() {
    }

    public Equipamento(Integer idequipamento) {
        this.idequipamento = idequipamento;
    }

    public Integer getIdequipamento() {
        return idequipamento;
    }

    public void setIdequipamento(Integer idequipamento) {
        this.idequipamento = idequipamento;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    @XmlTransient
    public Collection<Equipamentofalta> getEquipamentofaltaCollection() {
        return equipamentofaltaCollection;
    }

    public void setEquipamentofaltaCollection(Collection<Equipamentofalta> equipamentofaltaCollection) {
        this.equipamentofaltaCollection = equipamentofaltaCollection;
    }

    @XmlTransient
    public Collection<Oficinaequipamento> getOficinaequipamentoCollection() {
        return oficinaequipamentoCollection;
    }

    public void setOficinaequipamentoCollection(Collection<Oficinaequipamento> oficinaequipamentoCollection) {
        this.oficinaequipamentoCollection = oficinaequipamentoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idequipamento != null ? idequipamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipamento)) {
            return false;
        }
        Equipamento other = (Equipamento) object;
        if ((this.idequipamento == null && other.idequipamento != null) || (this.idequipamento != null && !this.idequipamento.equals(other.idequipamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return equipamento ;
    }
    
}
