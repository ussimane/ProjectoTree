/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MASSINGUE
 */
@Entity
@Table(name = "avaliacaoformando", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Avaliacaoformando.findAll", query = "SELECT a FROM Avaliacaoformando a"),
    @NamedQuery(name = "Avaliacaoformando.findByIdavaliacaoformando", query = "SELECT a FROM Avaliacaoformando a WHERE a.idavaliacaoformando = :idavaliacaoformando"),
    @NamedQuery(name = "Avaliacaoformando.findByIdcursoformacao", query = "SELECT a FROM Avaliacaoformando a WHERE a.idcursoformacao = :idcursoformacao"),
    @NamedQuery(name = "Avaliacaoformando.findByAvaliacaocurso", query = "SELECT a FROM Avaliacaoformando a WHERE a.avaliacaocurso = :avaliacaocurso"),
    @NamedQuery(name = "Avaliacaoformando.findByAvaliarequipamento", query = "SELECT a FROM Avaliacaoformando a WHERE a.avaliarequipamento = :avaliarequipamento"),
    @NamedQuery(name = "Avaliacaoformando.findByCapacidademestre", query = "SELECT a FROM Avaliacaoformando a WHERE a.capacidademestre = :capacidademestre"),
    @NamedQuery(name = "Avaliacaoformando.findByCondicoeslocal", query = "SELECT a FROM Avaliacaoformando a WHERE a.condicoeslocal = :condicoeslocal"),
    @NamedQuery(name = "Avaliacaoformando.findByImportanciacurso", query = "SELECT a FROM Avaliacaoformando a WHERE a.importanciacurso = :importanciacurso"),
    @NamedQuery(name = "Avaliacaoformando.findByConhecimentoadquiridos", query = "SELECT a FROM Avaliacaoformando a WHERE a.conhecimentoadquiridos = :conhecimentoadquiridos"),
    @NamedQuery(name = "Avaliacaoformando.findByConhecimentomestre", query = "SELECT a FROM Avaliacaoformando a WHERE a.conhecimentomestre = :conhecimentomestre"),
    @NamedQuery(name = "Avaliacaoformando.findByDuracaocurso", query = "SELECT a FROM Avaliacaoformando a WHERE a.duracaocurso = :duracaocurso"),
    @NamedQuery(name = "Avaliacaoformando.findByIndiquecurso", query = "SELECT a FROM Avaliacaoformando a WHERE a.indiquecurso = :indiquecurso"),
    @NamedQuery(name = "Avaliacaoformando.findByMelhoriacurso", query = "SELECT a FROM Avaliacaoformando a WHERE a.melhoriacurso = :melhoriacurso"),
    @NamedQuery(name = "Avaliacaoformando.findByEntidadeformadora", query = "SELECT a FROM Avaliacaoformando a WHERE a.entidadeformadora = :entidadeformadora"),
    @NamedQuery(name = "Avaliacaoformando.findByData", query = "SELECT a FROM Avaliacaoformando a WHERE a.data = :data")})
public class Avaliacaoformando implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idavaliacaoformando", nullable = false)
    private Integer idavaliacaoformando;
    @Column(name = "idcursoformacao")
    private Integer idcursoformacao;
    @Column(name = "avaliacaocurso")
    private Integer avaliacaocurso;
    @Column(name = "avaliarequipamento")
    private Integer avaliarequipamento;
    @Column(name = "capacidademestre")
    private Integer capacidademestre;
    @Column(name = "condicoeslocal")
    private Integer condicoeslocal;
    @Column(name = "importanciacurso")
    private Integer importanciacurso;
    @Column(name = "conhecimentoadquiridos")
    private Integer conhecimentoadquiridos;
    @Column(name = "conhecimentomestre")
    private Integer conhecimentomestre;
    @Column(name = "duracaocurso")
    private Integer duracaocurso;
    @Column(name = "indiquecurso")
    private Integer indiquecurso;
    @Column(name = "melhoriacurso", length = 500)
    private String melhoriacurso;
    @Column(name = "entidadeformadora", length = 45)
    private String entidadeformadora;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;

    public Avaliacaoformando() {
    }

    public Avaliacaoformando(Integer idavaliacaoformando) {
        this.idavaliacaoformando = idavaliacaoformando;
    }

    public Integer getIdavaliacaoformando() {
        return idavaliacaoformando;
    }

    public void setIdavaliacaoformando(Integer idavaliacaoformando) {
        this.idavaliacaoformando = idavaliacaoformando;
    }

    public Integer getIdcursoformacao() {
        return idcursoformacao;
    }

    public void setIdcursoformacao(Integer idcursoformacao) {
        this.idcursoformacao = idcursoformacao;
    }

    public Integer getAvaliacaocurso() {
        return avaliacaocurso;
    }

    public void setAvaliacaocurso(Integer avaliacaocurso) {
        this.avaliacaocurso = avaliacaocurso;
    }

    public Integer getAvaliarequipamento() {
        return avaliarequipamento;
    }

    public void setAvaliarequipamento(Integer avaliarequipamento) {
        this.avaliarequipamento = avaliarequipamento;
    }

    public Integer getCapacidademestre() {
        return capacidademestre;
    }

    public void setCapacidademestre(Integer capacidademestre) {
        this.capacidademestre = capacidademestre;
    }

    public Integer getCondicoeslocal() {
        return condicoeslocal;
    }

    public void setCondicoeslocal(Integer condicoeslocal) {
        this.condicoeslocal = condicoeslocal;
    }

    public Integer getImportanciacurso() {
        return importanciacurso;
    }

    public void setImportanciacurso(Integer importanciacurso) {
        this.importanciacurso = importanciacurso;
    }

    public Integer getConhecimentoadquiridos() {
        return conhecimentoadquiridos;
    }

    public void setConhecimentoadquiridos(Integer conhecimentoadquiridos) {
        this.conhecimentoadquiridos = conhecimentoadquiridos;
    }

    public Integer getConhecimentomestre() {
        return conhecimentomestre;
    }

    public void setConhecimentomestre(Integer conhecimentomestre) {
        this.conhecimentomestre = conhecimentomestre;
    }

    public Integer getDuracaocurso() {
        return duracaocurso;
    }

    public void setDuracaocurso(Integer duracaocurso) {
        this.duracaocurso = duracaocurso;
    }

    public Integer getIndiquecurso() {
        return indiquecurso;
    }

    public void setIndiquecurso(Integer indiquecurso) {
        this.indiquecurso = indiquecurso;
    }

    public String getMelhoriacurso() {
        return melhoriacurso;
    }

    public void setMelhoriacurso(String melhoriacurso) {
        this.melhoriacurso = melhoriacurso;
    }

    public String getEntidadeformadora() {
        return entidadeformadora;
    }

    public void setEntidadeformadora(String entidadeformadora) {
        this.entidadeformadora = entidadeformadora;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idavaliacaoformando != null ? idavaliacaoformando.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avaliacaoformando)) {
            return false;
        }
        Avaliacaoformando other = (Avaliacaoformando) object;
        if ((this.idavaliacaoformando == null && other.idavaliacaoformando != null) || (this.idavaliacaoformando != null && !this.idavaliacaoformando.equals(other.idavaliacaoformando))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Avaliacaoformando[ idavaliacaoformando=" + idavaliacaoformando + " ]";
    }
    
}
