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
@Table(name = "actividadecontapropria", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividadecontapropria.findAll", query = "SELECT a FROM Actividadecontapropria a"),
    @NamedQuery(name = "Actividadecontapropria.findByIdformando", query = "SELECT a FROM Actividadecontapropria a WHERE a.idformando = :idformando"),
    @NamedQuery(name = "Actividadecontapropria.findByAreaactividade", query = "SELECT a FROM Actividadecontapropria a WHERE a.areaactividade = :areaactividade"),
    @NamedQuery(name = "Actividadecontapropria.findByRendimento", query = "SELECT a FROM Actividadecontapropria a WHERE a.rendimento = :rendimento"),
    @NamedQuery(name = "Actividadecontapropria.findByTempo", query = "SELECT a FROM Actividadecontapropria a WHERE a.tempo = :tempo"),
    @NamedQuery(name = "Actividadecontapropria.findByForacomunidade", query = "SELECT a FROM Actividadecontapropria a WHERE a.foracomunidade = :foracomunidade"),
    @NamedQuery(name = "Actividadecontapropria.findByLugar", query = "SELECT a FROM Actividadecontapropria a WHERE a.lugar = :lugar")})
public class Actividadecontapropria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idformando", nullable = false)
    private Integer idformando;
    @Column(name = "areaactividade", length = 45)
    private String areaactividade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rendimento", precision = 12)
    private Float rendimento;
    @Column(name = "tempo")
    private Integer tempo;
    @Column(name = "foracomunidade")
    private Boolean foracomunidade;
    @Column(name = "lugar", length = 45)
    private String lugar;
    @JoinColumn(name = "idformando", referencedColumnName = "idformando", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Formando formando;

    public Actividadecontapropria() {
    }

    public Actividadecontapropria(Integer idformando) {
        this.idformando = idformando;
    }

    public Integer getIdformando() {
        return idformando;
    }

    public void setIdformando(Integer idformando) {
        this.idformando = idformando;
    }

    public String getAreaactividade() {
        return areaactividade;
    }

    public void setAreaactividade(String areaactividade) {
        this.areaactividade = areaactividade;
    }

    public Float getRendimento() {
        return rendimento;
    }

    public void setRendimento(Float rendimento) {
        this.rendimento = rendimento;
    }

    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }

    public Boolean getForacomunidade() {
        return foracomunidade;
    }

    public void setForacomunidade(Boolean foracomunidade) {
        this.foracomunidade = foracomunidade;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Formando getFormando() {
        return formando;
    }

    public void setFormando(Formando formando) {
        this.formando = formando;
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
        if (!(object instanceof Actividadecontapropria)) {
            return false;
        }
        Actividadecontapropria other = (Actividadecontapropria) object;
        if ((this.idformando == null && other.idformando != null) || (this.idformando != null && !this.idformando.equals(other.idformando))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Actividadecontapropria[ idformando=" + idformando + " ]";
    }
    
}
