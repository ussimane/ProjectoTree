/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MASSINGUE
 */
@Entity
@Table(name = "cursoformacao", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cursoformacao.findAll", query = "SELECT c FROM Cursoformacao c"),
    @NamedQuery(name = "Cursoformacao.findByIdcursoformacao", query = "SELECT c FROM Cursoformacao c WHERE c.idcursoformacao = :idcursoformacao"),
    @NamedQuery(name = "Cursoformacao.findByCodcurso", query = "SELECT c FROM Cursoformacao c WHERE c.codcurso = :codcurso"),
    @NamedQuery(name = "Cursoformacao.findByDatainicio", query = "SELECT c FROM Cursoformacao c WHERE c.datainicio = :datainicio"),
    @NamedQuery(name = "Cursoformacao.findByDatafim", query = "SELECT c FROM Cursoformacao c WHERE c.datafim = :datafim"),
    @NamedQuery(name = "Cursoformacao.findByTotalmatriculado", query = "SELECT c FROM Cursoformacao c WHERE c.totalmatriculado = :totalmatriculado"),
    @NamedQuery(name = "Cursoformacao.findByMatriculadoH", query = "SELECT c FROM Cursoformacao c WHERE c.matriculadoH = :matriculadoH"),
    @NamedQuery(name = "Cursoformacao.findByMatriculadoM", query = "SELECT c FROM Cursoformacao c WHERE c.matriculadoM = :matriculadoM"),
    @NamedQuery(name = "Cursoformacao.findByTotalformados", query = "SELECT c FROM Cursoformacao c WHERE c.totalformados = :totalformados"),
    @NamedQuery(name = "Cursoformacao.findByFormadosH", query = "SELECT c FROM Cursoformacao c WHERE c.formadosH = :formadosH"),
    @NamedQuery(name = "Cursoformacao.findByFormadosM", query = "SELECT c FROM Cursoformacao c WHERE c.formadosM = :formadosM"),
    @NamedQuery(name = "Cursoformacao.findByTotaldesistente", query = "SELECT c FROM Cursoformacao c WHERE c.totaldesistente = :totaldesistente"),
    @NamedQuery(name = "Cursoformacao.findByDesistenteH", query = "SELECT c FROM Cursoformacao c WHERE c.desistenteH = :desistenteH"),
    @NamedQuery(name = "Cursoformacao.findByDesistenteM", query = "SELECT c FROM Cursoformacao c WHERE c.desistenteM = :desistenteM"),
    @NamedQuery(name = "Cursoformacao.findByTotalreprovados", query = "SELECT c FROM Cursoformacao c WHERE c.totalreprovados = :totalreprovados"),
    @NamedQuery(name = "Cursoformacao.findByReprovadoH", query = "SELECT c FROM Cursoformacao c WHERE c.reprovadoH = :reprovadoH"),
    @NamedQuery(name = "Cursoformacao.findByReprovadoM", query = "SELECT c FROM Cursoformacao c WHERE c.reprovadoM = :reprovadoM"),
    @NamedQuery(name = "Cursoformacao.findByTaxafeminizacao", query = "SELECT c FROM Cursoformacao c WHERE c.taxafeminizacao = :taxafeminizacao"),
    @NamedQuery(name = "Cursoformacao.findByTaxaaprovacao", query = "SELECT c FROM Cursoformacao c WHERE c.taxaaprovacao = :taxaaprovacao"),
    @NamedQuery(name = "Cursoformacao.findByTaxadesistencia", query = "SELECT c FROM Cursoformacao c WHERE c.taxadesistencia = :taxadesistencia"),
    @NamedQuery(name = "Cursoformacao.findByTaxareaprovacao", query = "SELECT c FROM Cursoformacao c WHERE c.taxareaprovacao = :taxareaprovacao"),
    @NamedQuery(name = "Cursoformacao.findByCargahoraria", query = "SELECT c FROM Cursoformacao c WHERE c.cargahoraria = :cargahoraria"),
    @NamedQuery(name = "Cursoformacao.findByAuxiliar", query = "SELECT c FROM Cursoformacao c WHERE c.auxiliar = :auxiliar"),
    @NamedQuery(name = "Cursoformacao.findByOrcamentoepi", query = "SELECT c FROM Cursoformacao c WHERE c.orcamentoepi = :orcamentoepi"),
    @NamedQuery(name = "Cursoformacao.findByOrcamentoequipamento", query = "SELECT c FROM Cursoformacao c WHERE c.orcamentoequipamento = :orcamentoequipamento"),
    @NamedQuery(name = "Cursoformacao.findByOrcamentoconsumiveis", query = "SELECT c FROM Cursoformacao c WHERE c.orcamentoconsumiveis = :orcamentoconsumiveis"),
    @NamedQuery(name = "Cursoformacao.findByOrcamentohonorario", query = "SELECT c FROM Cursoformacao c WHERE c.orcamentohonorario = :orcamentohonorario")})
public class Cursoformacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcursoformacao", nullable = false)
    private Integer idcursoformacao;
    @Column(name = "codcurso", length = 45)
    private String codcurso;
    @Column(name = "datainicio")
    @Temporal(TemporalType.DATE)
    private Date datainicio;
    @Column(name = "datafim")
    @Temporal(TemporalType.DATE)
    private Date datafim;
    @Column(name = "totalmatriculado")
    private Integer totalmatriculado;
    @Column(name = "matriculadoH")
    private Integer matriculadoH;
    @Column(name = "matriculadoM")
    private Integer matriculadoM;
    @Column(name = "totalformados")
    private Integer totalformados;
    @Column(name = "formadosH")
    private Integer formadosH;
    @Column(name = "formadosM")
    private Integer formadosM;
    @Column(name = "totaldesistente")
    private Integer totaldesistente;
    @Column(name = "desistenteH")
    private Integer desistenteH;
    @Column(name = "desistenteM")
    private Integer desistenteM;
    @Column(name = "totalreprovados")
    private Integer totalreprovados;
    @Column(name = "reprovadoH")
    private Integer reprovadoH;
    @Column(name = "reprovadoM")
    private Integer reprovadoM;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "taxafeminizacao", precision = 12)
    private Float taxafeminizacao;
    @Column(name = "taxaaprovacao", precision = 12)
    private Float taxaaprovacao;
    @Column(name = "taxadesistencia", precision = 12)
    private Float taxadesistencia;
    @Column(name = "taxareaprovacao", precision = 12)
    private Float taxareaprovacao;
    @Column(name = "cargahoraria", length = 45)
    private String cargahoraria;
    @Column(name = "auxiliar", length = 45)
    private String auxiliar;
    @Column(name = "orcamentoepi", precision = 12)
    private Float orcamentoepi;
    @Column(name = "orcamentoequipamento", precision = 12)
    private Float orcamentoequipamento;
    @Column(name = "orcamentoconsumiveis", precision = 12)
    private Float orcamentoconsumiveis;
    @Column(name = "orcamentohonorario", precision = 12)
    private Float orcamentohonorario;
    @JoinColumn(name = "idpontofocal", referencedColumnName = "idpontofocal")
    @ManyToOne
    private Pontofocal idpontofocal;
    @JoinColumn(name = "idlocalidade", referencedColumnName = "idlocalidade")
    @ManyToOne
    private Localidade idlocalidade;
    @JoinColumn(name = "idcurso", referencedColumnName = "idcurso")
    @ManyToOne
    private Curso idcurso;
    @OneToMany(mappedBy = "idcursoformacao")
    private Collection<Supervisao> supervisaoCollection;
    @OneToMany(mappedBy = "idcursoformacao")
    private Collection<Formacaoturma> formacaoturmaCollection;

    public Cursoformacao() {
    }

    public Cursoformacao(Integer idcursoformacao) {
        this.idcursoformacao = idcursoformacao;
    }

    public Integer getIdcursoformacao() {
        return idcursoformacao;
    }

    public void setIdcursoformacao(Integer idcursoformacao) {
        this.idcursoformacao = idcursoformacao;
    }

    public String getCodcurso() {
        return codcurso;
    }

    public void setCodcurso(String codcurso) {
        this.codcurso = codcurso;
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public Date getDatafim() {
        return datafim;
    }

    public void setDatafim(Date datafim) {
        this.datafim = datafim;
    }

    public Integer getTotalmatriculado() {
        return totalmatriculado;
    }

    public void setTotalmatriculado(Integer totalmatriculado) {
        this.totalmatriculado = totalmatriculado;
    }

    public Integer getMatriculadoH() {
        return matriculadoH;
    }

    public void setMatriculadoH(Integer matriculadoH) {
        this.matriculadoH = matriculadoH;
    }

    public Integer getMatriculadoM() {
        return matriculadoM;
    }

    public void setMatriculadoM(Integer matriculadoM) {
        this.matriculadoM = matriculadoM;
    }

    public Integer getTotalformados() {
        return totalformados;
    }

    public void setTotalformados(Integer totalformados) {
        this.totalformados = totalformados;
    }

    public Integer getFormadosH() {
        return formadosH;
    }

    public void setFormadosH(Integer formadosH) {
        this.formadosH = formadosH;
    }

    public Integer getFormadosM() {
        return formadosM;
    }

    public void setFormadosM(Integer formadosM) {
        this.formadosM = formadosM;
    }

    public Integer getTotaldesistente() {
        return totaldesistente;
    }

    public void setTotaldesistente(Integer totaldesistente) {
        this.totaldesistente = totaldesistente;
    }

    public Integer getDesistenteH() {
        return desistenteH;
    }

    public void setDesistenteH(Integer desistenteH) {
        this.desistenteH = desistenteH;
    }

    public Integer getDesistenteM() {
        return desistenteM;
    }

    public void setDesistenteM(Integer desistenteM) {
        this.desistenteM = desistenteM;
    }

    public Integer getTotalreprovados() {
        return totalreprovados;
    }

    public void setTotalreprovados(Integer totalreprovados) {
        this.totalreprovados = totalreprovados;
    }

    public Integer getReprovadoH() {
        return reprovadoH;
    }

    public void setReprovadoH(Integer reprovadoH) {
        this.reprovadoH = reprovadoH;
    }

    public Integer getReprovadoM() {
        return reprovadoM;
    }

    public void setReprovadoM(Integer reprovadoM) {
        this.reprovadoM = reprovadoM;
    }

    public Float getTaxafeminizacao() {
        return taxafeminizacao;
    }

    public void setTaxafeminizacao(Float taxafeminizacao) {
        this.taxafeminizacao = taxafeminizacao;
    }

    public Float getTaxaaprovacao() {
        return taxaaprovacao;
    }

    public void setTaxaaprovacao(Float taxaaprovacao) {
        this.taxaaprovacao = taxaaprovacao;
    }

    public Float getTaxadesistencia() {
        return taxadesistencia;
    }

    public void setTaxadesistencia(Float taxadesistencia) {
        this.taxadesistencia = taxadesistencia;
    }

    public Float getTaxareaprovacao() {
        return taxareaprovacao;
    }

    public void setTaxareaprovacao(Float taxareaprovacao) {
        this.taxareaprovacao = taxareaprovacao;
    }

    public String getCargahoraria() {
        return cargahoraria;
    }

    public void setCargahoraria(String cargahoraria) {
        this.cargahoraria = cargahoraria;
    }

    public String getAuxiliar() {
        return auxiliar;
    }

    public void setAuxiliar(String auxiliar) {
        this.auxiliar = auxiliar;
    }

    public Float getOrcamentoepi() {
        return orcamentoepi;
    }

    public void setOrcamentoepi(Float orcamentoepi) {
        this.orcamentoepi = orcamentoepi;
    }

    public Float getOrcamentoequipamento() {
        return orcamentoequipamento;
    }

    public void setOrcamentoequipamento(Float orcamentoequipamento) {
        this.orcamentoequipamento = orcamentoequipamento;
    }

    public Float getOrcamentoconsumiveis() {
        return orcamentoconsumiveis;
    }

    public void setOrcamentoconsumiveis(Float orcamentoconsumiveis) {
        this.orcamentoconsumiveis = orcamentoconsumiveis;
    }

    public Float getOrcamentohonorario() {
        return orcamentohonorario;
    }

    public void setOrcamentohonorario(Float orcamentohonorario) {
        this.orcamentohonorario = orcamentohonorario;
    }

    public Pontofocal getIdpontofocal() {
        return idpontofocal;
    }

    public void setIdpontofocal(Pontofocal idpontofocal) {
        this.idpontofocal = idpontofocal;
    }

    public Localidade getIdlocalidade() {
        return idlocalidade;
    }

    public void setIdlocalidade(Localidade idlocalidade) {
        this.idlocalidade = idlocalidade;
    }

    public Curso getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(Curso idcurso) {
        this.idcurso = idcurso;
    }

    @XmlTransient
    public Collection<Supervisao> getSupervisaoCollection() {
        return supervisaoCollection;
    }

    public void setSupervisaoCollection(Collection<Supervisao> supervisaoCollection) {
        this.supervisaoCollection = supervisaoCollection;
    }

    @XmlTransient
    public Collection<Formacaoturma> getFormacaoturmaCollection() {
        return formacaoturmaCollection;
    }

    public void setFormacaoturmaCollection(Collection<Formacaoturma> formacaoturmaCollection) {
        this.formacaoturmaCollection = formacaoturmaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcursoformacao != null ? idcursoformacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cursoformacao)) {
            return false;
        }
        Cursoformacao other = (Cursoformacao) object;
        if ((this.idcursoformacao == null && other.idcursoformacao != null) || (this.idcursoformacao != null && !this.idcursoformacao.equals(other.idcursoformacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Cursoformacao[ idcursoformacao=" + idcursoformacao + " ]";
    }
    
}
