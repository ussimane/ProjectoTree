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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MASSINGUE
 */
@Entity
@Table(name = "oficinamestre", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Oficinamestre.findAll", query = "SELECT o FROM Oficinamestre o"),
    @NamedQuery(name = "Oficinamestre.findByIdmestre", query = "SELECT o FROM Oficinamestre o WHERE o.idmestre = :idmestre"),
    @NamedQuery(name = "Oficinamestre.findByQhomen", query = "SELECT o FROM Oficinamestre o WHERE o.qhomen = :qhomen"),
    @NamedQuery(name = "Oficinamestre.findByQmulher", query = "SELECT o FROM Oficinamestre o WHERE o.qmulher = :qmulher"),
    @NamedQuery(name = "Oficinamestre.findByTrabalhadorcontrato", query = "SELECT o FROM Oficinamestre o WHERE o.trabalhadorcontrato = :trabalhadorcontrato"),
    @NamedQuery(name = "Oficinamestre.findBySalariomedio", query = "SELECT o FROM Oficinamestre o WHERE o.salariomedio = :salariomedio"),
    @NamedQuery(name = "Oficinamestre.findByOutrasregalias", query = "SELECT o FROM Oficinamestre o WHERE o.outrasregalias = :outrasregalias"),
    @NamedQuery(name = "Oficinamestre.findBySocios", query = "SELECT o FROM Oficinamestre o WHERE o.socios = :socios"),
    @NamedQuery(name = "Oficinamestre.findByNrsocios", query = "SELECT o FROM Oficinamestre o WHERE o.nrsocios = :nrsocios"),
    @NamedQuery(name = "Oficinamestre.findByFormalizada", query = "SELECT o FROM Oficinamestre o WHERE o.formalizada = :formalizada"),
    @NamedQuery(name = "Oficinamestre.findByRecebeuaprendizes", query = "SELECT o FROM Oficinamestre o WHERE o.recebeuaprendizes = :recebeuaprendizes"),
    @NamedQuery(name = "Oficinamestre.findByQuantidade", query = "SELECT o FROM Oficinamestre o WHERE o.quantidade = :quantidade"),
    @NamedQuery(name = "Oficinamestre.findByDisponibilidade", query = "SELECT o FROM Oficinamestre o WHERE o.disponibilidade = :disponibilidade"),
    @NamedQuery(name = "Oficinamestre.findByAhomen", query = "SELECT o FROM Oficinamestre o WHERE o.ahomen = :ahomen"),
    @NamedQuery(name = "Oficinamestre.findByAmulher", query = "SELECT o FROM Oficinamestre o WHERE o.amulher = :amulher"),
    @NamedQuery(name = "Oficinamestre.findByRendimentomedio", query = "SELECT o FROM Oficinamestre o WHERE o.rendimentomedio = :rendimentomedio"),
    @NamedQuery(name = "Oficinamestre.findByNraprendizes", query = "SELECT o FROM Oficinamestre o WHERE o.nraprendizes = :nraprendizes"),
    @NamedQuery(name = "Oficinamestre.findByInvestimentos", query = "SELECT o FROM Oficinamestre o WHERE o.investimentos = :investimentos"),
    @NamedQuery(name = "Oficinamestre.findByEnergia", query = "SELECT o FROM Oficinamestre o WHERE o.energia = :energia"),
    @NamedQuery(name = "Oficinamestre.findByDescricaooficina", query = "SELECT o FROM Oficinamestre o WHERE o.descricaooficina = :descricaooficina"),
    @NamedQuery(name = "Oficinamestre.findByEquipamentofalta", query = "SELECT o FROM Oficinamestre o WHERE o.equipamentofalta = :equipamentofalta"),
    @NamedQuery(name = "Oficinamestre.findByProdutos", query = "SELECT o FROM Oficinamestre o WHERE o.produtos = :produtos"),
    @NamedQuery(name = "Oficinamestre.findByTemtrabalhador", query = "SELECT o FROM Oficinamestre o WHERE o.temtrabalhador = :temtrabalhador"),
    @NamedQuery(name = "Oficinamestre.findByEquipamentos", query = "SELECT o FROM Oficinamestre o WHERE o.equipamentos = :equipamentos"),
    @NamedQuery(name = "Oficinamestre.findByFaltaequipamento", query = "SELECT o FROM Oficinamestre o WHERE o.faltaequipamento = :faltaequipamento"),
    @NamedQuery(name = "Oficinamestre.findByQtdprod", query = "SELECT o FROM Oficinamestre o WHERE o.qtdprod = :qtdprod")})
public class Oficinamestre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idmestre", nullable = false)
    private Integer idmestre;
    @Column(name = "qhomen")
    private Integer qhomen;
    @Column(name = "qmulher")
    private Integer qmulher;
    @Column(name = "trabalhadorcontrato")
    private Boolean trabalhadorcontrato;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salariomedio", precision = 12)
    private Float salariomedio;
    @Column(name = "outrasregalias", length = 45)
    private String outrasregalias;
    @Column(name = "socios", length = 45)
    private String socios;
    @Column(name = "nrsocios")
    private Integer nrsocios;
    @Column(name = "formalizada", length = 45)
    private String formalizada;
    @Column(name = "recebeuaprendizes")
    private Boolean recebeuaprendizes;
    @Column(name = "quantidade", length = 45)
    private String quantidade;
    @Column(name = "disponibilidade")
    private Boolean disponibilidade;
    @Column(name = "ahomen")
    private Integer ahomen;
    @Column(name = "amulher")
    private Integer amulher;
    @Column(name = "rendimentomedio", precision = 12)
    private Float rendimentomedio;
    @Column(name = "nraprendizes")
    private Integer nraprendizes;
    @Column(name = "investimentos", length = 45)
    private String investimentos;
    @Column(name = "energia")
    private Boolean energia;
    @Column(name = "descricaooficina", length = 100)
    private String descricaooficina;
    @Column(name = "equipamentofalta")
    private Boolean equipamentofalta;
    @Column(name = "produtos", length = 100)
    private String produtos;
    @Column(name = "temtrabalhador")
    private Boolean temtrabalhador;
    @Column(name = "equipamentos", length = 500)
    private String equipamentos;
    @Column(name = "faltaequipamento", length = 500)
    private String faltaequipamento;
    @Column(name = "qtdprod")
    private Integer qtdprod;
    @JoinColumn(name = "idproducao", referencedColumnName = "idproducao")
    @ManyToOne
    private Producao idproducao;
    @JoinColumn(name = "nrtrabalhador", referencedColumnName = "idtabalhador")
    @ManyToOne
    private Trabalhador nrtrabalhador;
    @JoinColumn(name = "idmestre", referencedColumnName = "idmestre", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Mestre mestre;
    @JoinColumn(name = "idmercado", referencedColumnName = "idmercado")
    @ManyToOne
    private Mercado idmercado;
    @JoinColumn(name = "idanoinvestimentosequipamentos", referencedColumnName = "idinvestimento")
    @ManyToOne
    private Investimento idanoinvestimentosequipamentos;
    @JoinColumn(name = "idanosoficina", referencedColumnName = "idactividadeprofissao")
    @ManyToOne
    private Actividadeprofissao idanosoficina;
    @JoinColumn(name = "idanosexperiencia", referencedColumnName = "idactividadeprofissao")
    @ManyToOne
    private Actividadeprofissao idanosexperiencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "oficinamestre")
    private Collection<Equipamentofalta> equipamentofaltaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "oficinamestre")
    private Collection<Oficinaequipamento> oficinaequipamentoCollection;

    public Oficinamestre() {
    }

    public Oficinamestre(Integer idmestre) {
        this.idmestre = idmestre;
    }

    public Integer getIdmestre() {
        return idmestre;
    }

    public void setIdmestre(Integer idmestre) {
        this.idmestre = idmestre;
    }

    public Integer getQhomen() {
        return qhomen;
    }

    public void setQhomen(Integer qhomen) {
        this.qhomen = qhomen;
    }

    public Integer getQmulher() {
        return qmulher;
    }

    public void setQmulher(Integer qmulher) {
        this.qmulher = qmulher;
    }

    public Boolean getTrabalhadorcontrato() {
        return trabalhadorcontrato;
    }

    public void setTrabalhadorcontrato(Boolean trabalhadorcontrato) {
        this.trabalhadorcontrato = trabalhadorcontrato;
    }

    public Float getSalariomedio() {
        return salariomedio;
    }

    public void setSalariomedio(Float salariomedio) {
        this.salariomedio = salariomedio;
    }

    public String getOutrasregalias() {
        return outrasregalias;
    }

    public void setOutrasregalias(String outrasregalias) {
        this.outrasregalias = outrasregalias;
    }

    public String getSocios() {
        return socios;
    }

    public void setSocios(String socios) {
        this.socios = socios;
    }

    public Integer getNrsocios() {
        return nrsocios;
    }

    public void setNrsocios(Integer nrsocios) {
        this.nrsocios = nrsocios;
    }

    public String getFormalizada() {
        return formalizada;
    }

    public void setFormalizada(String formalizada) {
        this.formalizada = formalizada;
    }

    public Boolean getRecebeuaprendizes() {
        return recebeuaprendizes;
    }

    public void setRecebeuaprendizes(Boolean recebeuaprendizes) {
        this.recebeuaprendizes = recebeuaprendizes;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public Boolean getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public Integer getAhomen() {
        return ahomen;
    }

    public void setAhomen(Integer ahomen) {
        this.ahomen = ahomen;
    }

    public Integer getAmulher() {
        return amulher;
    }

    public void setAmulher(Integer amulher) {
        this.amulher = amulher;
    }

    public Float getRendimentomedio() {
        return rendimentomedio;
    }

    public void setRendimentomedio(Float rendimentomedio) {
        this.rendimentomedio = rendimentomedio;
    }

    public Integer getNraprendizes() {
        return nraprendizes;
    }

    public void setNraprendizes(Integer nraprendizes) {
        this.nraprendizes = nraprendizes;
    }

    public String getInvestimentos() {
        return investimentos;
    }

    public void setInvestimentos(String investimentos) {
        this.investimentos = investimentos;
    }

    public Boolean getEnergia() {
        return energia;
    }

    public void setEnergia(Boolean energia) {
        this.energia = energia;
    }

    public String getDescricaooficina() {
        return descricaooficina;
    }

    public void setDescricaooficina(String descricaooficina) {
        this.descricaooficina = descricaooficina;
    }

    public Boolean getEquipamentofalta() {
        return equipamentofalta;
    }

    public void setEquipamentofalta(Boolean equipamentofalta) {
        this.equipamentofalta = equipamentofalta;
    }

    public String getProdutos() {
        return produtos;
    }

    public void setProdutos(String produtos) {
        this.produtos = produtos;
    }

    public Boolean getTemtrabalhador() {
        return temtrabalhador;
    }

    public void setTemtrabalhador(Boolean temtrabalhador) {
        this.temtrabalhador = temtrabalhador;
    }

    public String getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(String equipamentos) {
        this.equipamentos = equipamentos;
    }

    public String getFaltaequipamento() {
        return faltaequipamento;
    }

    public void setFaltaequipamento(String faltaequipamento) {
        this.faltaequipamento = faltaequipamento;
    }

    public Integer getQtdprod() {
        return qtdprod;
    }

    public void setQtdprod(Integer qtdprod) {
        this.qtdprod = qtdprod;
    }

    public Producao getIdproducao() {
        return idproducao;
    }

    public void setIdproducao(Producao idproducao) {
        this.idproducao = idproducao;
    }

    public Trabalhador getNrtrabalhador() {
        return nrtrabalhador;
    }

    public void setNrtrabalhador(Trabalhador nrtrabalhador) {
        this.nrtrabalhador = nrtrabalhador;
    }

    public Mestre getMestre() {
        return mestre;
    }

    public void setMestre(Mestre mestre) {
        this.mestre = mestre;
    }

    public Mercado getIdmercado() {
        return idmercado;
    }

    public void setIdmercado(Mercado idmercado) {
        this.idmercado = idmercado;
    }

    public Investimento getIdanoinvestimentosequipamentos() {
        return idanoinvestimentosequipamentos;
    }

    public void setIdanoinvestimentosequipamentos(Investimento idanoinvestimentosequipamentos) {
        this.idanoinvestimentosequipamentos = idanoinvestimentosequipamentos;
    }

    public Actividadeprofissao getIdanosoficina() {
        return idanosoficina;
    }

    public void setIdanosoficina(Actividadeprofissao idanosoficina) {
        this.idanosoficina = idanosoficina;
    }

    public Actividadeprofissao getIdanosexperiencia() {
        return idanosexperiencia;
    }

    public void setIdanosexperiencia(Actividadeprofissao idanosexperiencia) {
        this.idanosexperiencia = idanosexperiencia;
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
        hash += (idmestre != null ? idmestre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oficinamestre)) {
            return false;
        }
        Oficinamestre other = (Oficinamestre) object;
        if ((this.idmestre == null && other.idmestre != null) || (this.idmestre != null && !this.idmestre.equals(other.idmestre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Oficinamestre[ idmestre=" + idmestre + " ]";
    }
    
}
