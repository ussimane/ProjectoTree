/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "equipamentofalta", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipamentofalta.findAll", query = "SELECT e FROM Equipamentofalta e"),
    @NamedQuery(name = "Equipamentofalta.findByIdoficina", query = "SELECT e FROM Equipamentofalta e WHERE e.equipamentofaltaPK.idoficina = :idoficina"),
    @NamedQuery(name = "Equipamentofalta.findByIdequipamento", query = "SELECT e FROM Equipamentofalta e WHERE e.equipamentofaltaPK.idequipamento = :idequipamento"),
    @NamedQuery(name = "Equipamentofalta.findByQuantidade", query = "SELECT e FROM Equipamentofalta e WHERE e.quantidade = :quantidade")})
public class Equipamentofalta implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EquipamentofaltaPK equipamentofaltaPK;
    @Column(name = "quantidade")
    private Integer quantidade;
    @JoinColumn(name = "idequipamento", referencedColumnName = "idequipamento", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Equipamento equipamento;
    @JoinColumn(name = "idoficina", referencedColumnName = "idmestre", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Oficinamestre oficinamestre;

    public Equipamentofalta() {
    }

    public Equipamentofalta(EquipamentofaltaPK equipamentofaltaPK) {
        this.equipamentofaltaPK = equipamentofaltaPK;
    }

    public Equipamentofalta(int idoficina, int idequipamento) {
        this.equipamentofaltaPK = new EquipamentofaltaPK(idoficina, idequipamento);
    }

    public EquipamentofaltaPK getEquipamentofaltaPK() {
        return equipamentofaltaPK;
    }

    public void setEquipamentofaltaPK(EquipamentofaltaPK equipamentofaltaPK) {
        this.equipamentofaltaPK = equipamentofaltaPK;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Oficinamestre getOficinamestre() {
        return oficinamestre;
    }

    public void setOficinamestre(Oficinamestre oficinamestre) {
        this.oficinamestre = oficinamestre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (equipamentofaltaPK != null ? equipamentofaltaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipamentofalta)) {
            return false;
        }
        Equipamentofalta other = (Equipamentofalta) object;
        if ((this.equipamentofaltaPK == null && other.equipamentofaltaPK != null) || (this.equipamentofaltaPK != null && !this.equipamentofaltaPK.equals(other.equipamentofaltaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Equipamentofalta[ equipamentofaltaPK=" + equipamentofaltaPK + " ]";
    }
    
}
