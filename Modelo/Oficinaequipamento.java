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
@Table(name = "oficinaequipamento", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Oficinaequipamento.findAll", query = "SELECT o FROM Oficinaequipamento o"),
    @NamedQuery(name = "Oficinaequipamento.findByIdoficina", query = "SELECT o FROM Oficinaequipamento o WHERE o.oficinaequipamentoPK.idoficina = :idoficina"),
    @NamedQuery(name = "Oficinaequipamento.findByIdequipamento", query = "SELECT o FROM Oficinaequipamento o WHERE o.oficinaequipamentoPK.idequipamento = :idequipamento"),
    @NamedQuery(name = "Oficinaequipamento.findByQuantidade", query = "SELECT o FROM Oficinaequipamento o WHERE o.quantidade = :quantidade")})
public class Oficinaequipamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OficinaequipamentoPK oficinaequipamentoPK;
    @Column(name = "quantidade")
    private Integer quantidade;
    @JoinColumn(name = "idoficina", referencedColumnName = "idmestre", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Oficinamestre oficinamestre;
    @JoinColumn(name = "idequipamento", referencedColumnName = "idequipamento", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Equipamento equipamento;

    public Oficinaequipamento() {
    }

    public Oficinaequipamento(OficinaequipamentoPK oficinaequipamentoPK) {
        this.oficinaequipamentoPK = oficinaequipamentoPK;
    }

    public Oficinaequipamento(int idoficina, int idequipamento) {
        this.oficinaequipamentoPK = new OficinaequipamentoPK(idoficina, idequipamento);
    }

    public OficinaequipamentoPK getOficinaequipamentoPK() {
        return oficinaequipamentoPK;
    }

    public void setOficinaequipamentoPK(OficinaequipamentoPK oficinaequipamentoPK) {
        this.oficinaequipamentoPK = oficinaequipamentoPK;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Oficinamestre getOficinamestre() {
        return oficinamestre;
    }

    public void setOficinamestre(Oficinamestre oficinamestre) {
        this.oficinamestre = oficinamestre;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oficinaequipamentoPK != null ? oficinaequipamentoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oficinaequipamento)) {
            return false;
        }
        Oficinaequipamento other = (Oficinaequipamento) object;
        if ((this.oficinaequipamentoPK == null && other.oficinaequipamentoPK != null) || (this.oficinaequipamentoPK != null && !this.oficinaequipamentoPK.equals(other.oficinaequipamentoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Oficinaequipamento[ oficinaequipamentoPK=" + oficinaequipamentoPK + " ]";
    }
    
}
