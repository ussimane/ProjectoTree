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
@Table(name = "accaoformacao", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accaoformacao.findAll", query = "SELECT a FROM Accaoformacao a"),
    @NamedQuery(name = "Accaoformacao.findByIdformando", query = "SELECT a FROM Accaoformacao a WHERE a.idformando = :idformando"),
    @NamedQuery(name = "Accaoformacao.findByEntidadeformadora", query = "SELECT a FROM Accaoformacao a WHERE a.entidadeformadora = :entidadeformadora"),
    @NamedQuery(name = "Accaoformacao.findByDuracao", query = "SELECT a FROM Accaoformacao a WHERE a.duracao = :duracao"),
    @NamedQuery(name = "Accaoformacao.findByAno", query = "SELECT a FROM Accaoformacao a WHERE a.ano = :ano")})
public class Accaoformacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idformando", nullable = false)
    private Integer idformando;
    @Column(name = "entidadeformadora", length = 45)
    private String entidadeformadora;
    @Column(name = "duracao", length = 45)
    private String duracao;
    @Column(name = "ano")
    private Integer ano;
    @JoinColumn(name = "idformando", referencedColumnName = "idformando", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Formando formando;
    @JoinColumn(name = "idcurso", referencedColumnName = "idcurso")
    @ManyToOne
    private Curso idcurso;

    public Accaoformacao() {
    }

    public Accaoformacao(Integer idformando) {
        this.idformando = idformando;
    }

    public Integer getIdformando() {
        return idformando;
    }

    public void setIdformando(Integer idformando) {
        this.idformando = idformando;
    }

    public String getEntidadeformadora() {
        return entidadeformadora;
    }

    public void setEntidadeformadora(String entidadeformadora) {
        this.entidadeformadora = entidadeformadora;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Formando getFormando() {
        return formando;
    }

    public void setFormando(Formando formando) {
        this.formando = formando;
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
        hash += (idformando != null ? idformando.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accaoformacao)) {
            return false;
        }
        Accaoformacao other = (Accaoformacao) object;
        if ((this.idformando == null && other.idformando != null) || (this.idformando != null && !this.idformando.equals(other.idformando))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Accaoformacao[ idformando=" + idformando + " ]";
    }
    
}
