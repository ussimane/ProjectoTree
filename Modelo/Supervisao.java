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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MASSINGUE
 */
@Entity
@Table(name = "supervisao", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Supervisao.findAll", query = "SELECT s FROM Supervisao s"),
    @NamedQuery(name = "Supervisao.findByIdsupervisao", query = "SELECT s FROM Supervisao s WHERE s.idsupervisao = :idsupervisao"),
    @NamedQuery(name = "Supervisao.findByEquipamentoadquado", query = "SELECT s FROM Supervisao s WHERE s.equipamentoadquado = :equipamentoadquado"),
    @NamedQuery(name = "Supervisao.findByRecoequipaadquado", query = "SELECT s FROM Supervisao s WHERE s.recoequipaadquado = :recoequipaadquado"),
    @NamedQuery(name = "Supervisao.findBySegurancaequipamento", query = "SELECT s FROM Supervisao s WHERE s.segurancaequipamento = :segurancaequipamento"),
    @NamedQuery(name = "Supervisao.findByRecoequipaseguranca", query = "SELECT s FROM Supervisao s WHERE s.recoequipaseguranca = :recoequipaseguranca"),
    @NamedQuery(name = "Supervisao.findByUsoequipamento", query = "SELECT s FROM Supervisao s WHERE s.usoequipamento = :usoequipamento"),
    @NamedQuery(name = "Supervisao.findByRecoequipauso", query = "SELECT s FROM Supervisao s WHERE s.recoequipauso = :recoequipauso"),
    @NamedQuery(name = "Supervisao.findByControlequipamento", query = "SELECT s FROM Supervisao s WHERE s.controlequipamento = :controlequipamento"),
    @NamedQuery(name = "Supervisao.findByRecocontrolequipamento", query = "SELECT s FROM Supervisao s WHERE s.recocontrolequipamento = :recocontrolequipamento"),
    @NamedQuery(name = "Supervisao.findByCalendario", query = "SELECT s FROM Supervisao s WHERE s.calendario = :calendario"),
    @NamedQuery(name = "Supervisao.findByRecocalendario", query = "SELECT s FROM Supervisao s WHERE s.recocalendario = :recocalendario"),
    @NamedQuery(name = "Supervisao.findByPlanotematico", query = "SELECT s FROM Supervisao s WHERE s.planotematico = :planotematico"),
    @NamedQuery(name = "Supervisao.findByRecoplanotematico", query = "SELECT s FROM Supervisao s WHERE s.recoplanotematico = :recoplanotematico"),
    @NamedQuery(name = "Supervisao.findByMudancaplanooriginal", query = "SELECT s FROM Supervisao s WHERE s.mudancaplanooriginal = :mudancaplanooriginal"),
    @NamedQuery(name = "Supervisao.findByRecomudancaplano", query = "SELECT s FROM Supervisao s WHERE s.recomudancaplano = :recomudancaplano"),
    @NamedQuery(name = "Supervisao.findByCondicoeslocaisformacao", query = "SELECT s FROM Supervisao s WHERE s.condicoeslocaisformacao = :condicoeslocaisformacao"),
    @NamedQuery(name = "Supervisao.findByRecocondicoeslocaisformacao", query = "SELECT s FROM Supervisao s WHERE s.recocondicoeslocaisformacao = :recocondicoeslocaisformacao"),
    @NamedQuery(name = "Supervisao.findByLayoutlocal", query = "SELECT s FROM Supervisao s WHERE s.layoutlocal = :layoutlocal"),
    @NamedQuery(name = "Supervisao.findByRecolayoutlocal", query = "SELECT s FROM Supervisao s WHERE s.recolayoutlocal = :recolayoutlocal"),
    @NamedQuery(name = "Supervisao.findByRecomendacoesdeixadas", query = "SELECT s FROM Supervisao s WHERE s.recomendacoesdeixadas = :recomendacoesdeixadas"),
    @NamedQuery(name = "Supervisao.findByRecorecomendacoesdeixadas", query = "SELECT s FROM Supervisao s WHERE s.recorecomendacoesdeixadas = :recorecomendacoesdeixadas"),
    @NamedQuery(name = "Supervisao.findByAcompanhamentochefeposto", query = "SELECT s FROM Supervisao s WHERE s.acompanhamentochefeposto = :acompanhamentochefeposto"),
    @NamedQuery(name = "Supervisao.findByRecoacompanhamentochefeposto", query = "SELECT s FROM Supervisao s WHERE s.recoacompanhamentochefeposto = :recoacompanhamentochefeposto"),
    @NamedQuery(name = "Supervisao.findByPontosrelevantes", query = "SELECT s FROM Supervisao s WHERE s.pontosrelevantes = :pontosrelevantes"),
    @NamedQuery(name = "Supervisao.findByRecopontosrelevantes", query = "SELECT s FROM Supervisao s WHERE s.recopontosrelevantes = :recopontosrelevantes"),
    @NamedQuery(name = "Supervisao.findByPresencamestre", query = "SELECT s FROM Supervisao s WHERE s.presencamestre = :presencamestre"),
    @NamedQuery(name = "Supervisao.findByRecopresencamestre", query = "SELECT s FROM Supervisao s WHERE s.recopresencamestre = :recopresencamestre"),
    @NamedQuery(name = "Supervisao.findByTotalpresentes", query = "SELECT s FROM Supervisao s WHERE s.totalpresentes = :totalpresentes"),
    @NamedQuery(name = "Supervisao.findByRecototalpresentes", query = "SELECT s FROM Supervisao s WHERE s.recototalpresentes = :recototalpresentes"),
    @NamedQuery(name = "Supervisao.findByTotaldesistente", query = "SELECT s FROM Supervisao s WHERE s.totaldesistente = :totaldesistente"),
    @NamedQuery(name = "Supervisao.findByRecodesistente", query = "SELECT s FROM Supervisao s WHERE s.recodesistente = :recodesistente"),
    @NamedQuery(name = "Supervisao.findByTotalrecrutados", query = "SELECT s FROM Supervisao s WHERE s.totalrecrutados = :totalrecrutados"),
    @NamedQuery(name = "Supervisao.findByRecototalrecrutados", query = "SELECT s FROM Supervisao s WHERE s.recototalrecrutados = :recototalrecrutados"),
    @NamedQuery(name = "Supervisao.findByGaranteassiduidade", query = "SELECT s FROM Supervisao s WHERE s.garanteassiduidade = :garanteassiduidade"),
    @NamedQuery(name = "Supervisao.findByRecogaranteassiduidade", query = "SELECT s FROM Supervisao s WHERE s.recogaranteassiduidade = :recogaranteassiduidade"),
    @NamedQuery(name = "Supervisao.findByUtilizainstrumentos", query = "SELECT s FROM Supervisao s WHERE s.utilizainstrumentos = :utilizainstrumentos"),
    @NamedQuery(name = "Supervisao.findByRecoutilizainstrumentos", query = "SELECT s FROM Supervisao s WHERE s.recoutilizainstrumentos = :recoutilizainstrumentos"),
    @NamedQuery(name = "Supervisao.findByComunicacao", query = "SELECT s FROM Supervisao s WHERE s.comunicacao = :comunicacao"),
    @NamedQuery(name = "Supervisao.findByRecocomunicacao", query = "SELECT s FROM Supervisao s WHERE s.recocomunicacao = :recocomunicacao"),
    @NamedQuery(name = "Supervisao.findByDificuldadestecnicas", query = "SELECT s FROM Supervisao s WHERE s.dificuldadestecnicas = :dificuldadestecnicas"),
    @NamedQuery(name = "Supervisao.findByRecodificuldadestecnicas", query = "SELECT s FROM Supervisao s WHERE s.recodificuldadestecnicas = :recodificuldadestecnicas"),
    @NamedQuery(name = "Supervisao.findByDificuldadesteste", query = "SELECT s FROM Supervisao s WHERE s.dificuldadesteste = :dificuldadesteste"),
    @NamedQuery(name = "Supervisao.findByRecodificuldadesteste", query = "SELECT s FROM Supervisao s WHERE s.recodificuldadesteste = :recodificuldadesteste")})
public class Supervisao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idsupervisao", nullable = false)
    private Integer idsupervisao;
    @Column(name = "equipamentoadquado")
    private Boolean equipamentoadquado;
    @Column(name = "recoequipaadquado", length = 500)
    private String recoequipaadquado;
    @Column(name = "segurancaequipamento")
    private Boolean segurancaequipamento;
    @Column(name = "recoequipaseguranca", length = 500)
    private String recoequipaseguranca;
    @Column(name = "usoequipamento")
    private Boolean usoequipamento;
    @Column(name = "recoequipauso", length = 500)
    private String recoequipauso;
    @Column(name = "controlequipamento")
    private Boolean controlequipamento;
    @Column(name = "recocontrolequipamento", length = 500)
    private String recocontrolequipamento;
    @Column(name = "calendario")
    private Boolean calendario;
    @Column(name = "recocalendario", length = 500)
    private String recocalendario;
    @Column(name = "planotematico")
    private Boolean planotematico;
    @Column(name = "recoplanotematico", length = 500)
    private String recoplanotematico;
    @Column(name = "mudancaplanooriginal")
    private Boolean mudancaplanooriginal;
    @Column(name = "recomudancaplano", length = 500)
    private String recomudancaplano;
    @Column(name = "condicoeslocaisformacao")
    private Boolean condicoeslocaisformacao;
    @Column(name = "recocondicoeslocaisformacao", length = 500)
    private String recocondicoeslocaisformacao;
    @Column(name = "layoutlocal")
    private Boolean layoutlocal;
    @Column(name = "recolayoutlocal", length = 500)
    private String recolayoutlocal;
    @Column(name = "recomendacoesdeixadas")
    private Boolean recomendacoesdeixadas;
    @Column(name = "recorecomendacoesdeixadas", length = 500)
    private String recorecomendacoesdeixadas;
    @Column(name = "acompanhamentochefeposto")
    private Boolean acompanhamentochefeposto;
    @Column(name = "recoacompanhamentochefeposto", length = 500)
    private String recoacompanhamentochefeposto;
    @Column(name = "pontosrelevantes")
    private Boolean pontosrelevantes;
    @Column(name = "recopontosrelevantes", length = 500)
    private String recopontosrelevantes;
    @Column(name = "presencamestre")
    private Boolean presencamestre;
    @Column(name = "recopresencamestre", length = 500)
    private String recopresencamestre;
    @Column(name = "totalpresentes")
    private Integer totalpresentes;
    @Column(name = "recototalpresentes", length = 500)
    private String recototalpresentes;
    @Column(name = "totaldesistente")
    private Integer totaldesistente;
    @Column(name = "recodesistente", length = 500)
    private String recodesistente;
    @Column(name = "totalrecrutados")
    private Integer totalrecrutados;
    @Column(name = "recototalrecrutados", length = 500)
    private String recototalrecrutados;
    @Column(name = "garanteassiduidade")
    private Boolean garanteassiduidade;
    @Column(name = "recogaranteassiduidade", length = 500)
    private String recogaranteassiduidade;
    @Column(name = "utilizainstrumentos")
    private Boolean utilizainstrumentos;
    @Column(name = "recoutilizainstrumentos", length = 500)
    private String recoutilizainstrumentos;
    @Column(name = "comunicacao")
    private Boolean comunicacao;
    @Column(name = "recocomunicacao", length = 500)
    private String recocomunicacao;
    @Column(name = "dificuldadestecnicas")
    private Boolean dificuldadestecnicas;
    @Column(name = "recodificuldadestecnicas", length = 500)
    private String recodificuldadestecnicas;
    @Column(name = "dificuldadesteste")
    private Boolean dificuldadesteste;
    @Column(name = "recodificuldadesteste", length = 500)
    private String recodificuldadesteste;
    @JoinColumn(name = "idcursoformacao", referencedColumnName = "idcursoformacao")
    @ManyToOne
    private Cursoformacao idcursoformacao;

    public Supervisao() {
    }

    public Supervisao(Integer idsupervisao) {
        this.idsupervisao = idsupervisao;
    }

    public Integer getIdsupervisao() {
        return idsupervisao;
    }

    public void setIdsupervisao(Integer idsupervisao) {
        this.idsupervisao = idsupervisao;
    }

    public Boolean getEquipamentoadquado() {
        return equipamentoadquado;
    }

    public void setEquipamentoadquado(Boolean equipamentoadquado) {
        this.equipamentoadquado = equipamentoadquado;
    }

    public String getRecoequipaadquado() {
        return recoequipaadquado;
    }

    public void setRecoequipaadquado(String recoequipaadquado) {
        this.recoequipaadquado = recoequipaadquado;
    }

    public Boolean getSegurancaequipamento() {
        return segurancaequipamento;
    }

    public void setSegurancaequipamento(Boolean segurancaequipamento) {
        this.segurancaequipamento = segurancaequipamento;
    }

    public String getRecoequipaseguranca() {
        return recoequipaseguranca;
    }

    public void setRecoequipaseguranca(String recoequipaseguranca) {
        this.recoequipaseguranca = recoequipaseguranca;
    }

    public Boolean getUsoequipamento() {
        return usoequipamento;
    }

    public void setUsoequipamento(Boolean usoequipamento) {
        this.usoequipamento = usoequipamento;
    }

    public String getRecoequipauso() {
        return recoequipauso;
    }

    public void setRecoequipauso(String recoequipauso) {
        this.recoequipauso = recoequipauso;
    }

    public Boolean getControlequipamento() {
        return controlequipamento;
    }

    public void setControlequipamento(Boolean controlequipamento) {
        this.controlequipamento = controlequipamento;
    }

    public String getRecocontrolequipamento() {
        return recocontrolequipamento;
    }

    public void setRecocontrolequipamento(String recocontrolequipamento) {
        this.recocontrolequipamento = recocontrolequipamento;
    }

    public Boolean getCalendario() {
        return calendario;
    }

    public void setCalendario(Boolean calendario) {
        this.calendario = calendario;
    }

    public String getRecocalendario() {
        return recocalendario;
    }

    public void setRecocalendario(String recocalendario) {
        this.recocalendario = recocalendario;
    }

    public Boolean getPlanotematico() {
        return planotematico;
    }

    public void setPlanotematico(Boolean planotematico) {
        this.planotematico = planotematico;
    }

    public String getRecoplanotematico() {
        return recoplanotematico;
    }

    public void setRecoplanotematico(String recoplanotematico) {
        this.recoplanotematico = recoplanotematico;
    }

    public Boolean getMudancaplanooriginal() {
        return mudancaplanooriginal;
    }

    public void setMudancaplanooriginal(Boolean mudancaplanooriginal) {
        this.mudancaplanooriginal = mudancaplanooriginal;
    }

    public String getRecomudancaplano() {
        return recomudancaplano;
    }

    public void setRecomudancaplano(String recomudancaplano) {
        this.recomudancaplano = recomudancaplano;
    }

    public Boolean getCondicoeslocaisformacao() {
        return condicoeslocaisformacao;
    }

    public void setCondicoeslocaisformacao(Boolean condicoeslocaisformacao) {
        this.condicoeslocaisformacao = condicoeslocaisformacao;
    }

    public String getRecocondicoeslocaisformacao() {
        return recocondicoeslocaisformacao;
    }

    public void setRecocondicoeslocaisformacao(String recocondicoeslocaisformacao) {
        this.recocondicoeslocaisformacao = recocondicoeslocaisformacao;
    }

    public Boolean getLayoutlocal() {
        return layoutlocal;
    }

    public void setLayoutlocal(Boolean layoutlocal) {
        this.layoutlocal = layoutlocal;
    }

    public String getRecolayoutlocal() {
        return recolayoutlocal;
    }

    public void setRecolayoutlocal(String recolayoutlocal) {
        this.recolayoutlocal = recolayoutlocal;
    }

    public Boolean getRecomendacoesdeixadas() {
        return recomendacoesdeixadas;
    }

    public void setRecomendacoesdeixadas(Boolean recomendacoesdeixadas) {
        this.recomendacoesdeixadas = recomendacoesdeixadas;
    }

    public String getRecorecomendacoesdeixadas() {
        return recorecomendacoesdeixadas;
    }

    public void setRecorecomendacoesdeixadas(String recorecomendacoesdeixadas) {
        this.recorecomendacoesdeixadas = recorecomendacoesdeixadas;
    }

    public Boolean getAcompanhamentochefeposto() {
        return acompanhamentochefeposto;
    }

    public void setAcompanhamentochefeposto(Boolean acompanhamentochefeposto) {
        this.acompanhamentochefeposto = acompanhamentochefeposto;
    }

    public String getRecoacompanhamentochefeposto() {
        return recoacompanhamentochefeposto;
    }

    public void setRecoacompanhamentochefeposto(String recoacompanhamentochefeposto) {
        this.recoacompanhamentochefeposto = recoacompanhamentochefeposto;
    }

    public Boolean getPontosrelevantes() {
        return pontosrelevantes;
    }

    public void setPontosrelevantes(Boolean pontosrelevantes) {
        this.pontosrelevantes = pontosrelevantes;
    }

    public String getRecopontosrelevantes() {
        return recopontosrelevantes;
    }

    public void setRecopontosrelevantes(String recopontosrelevantes) {
        this.recopontosrelevantes = recopontosrelevantes;
    }

    public Boolean getPresencamestre() {
        return presencamestre;
    }

    public void setPresencamestre(Boolean presencamestre) {
        this.presencamestre = presencamestre;
    }

    public String getRecopresencamestre() {
        return recopresencamestre;
    }

    public void setRecopresencamestre(String recopresencamestre) {
        this.recopresencamestre = recopresencamestre;
    }

    public Integer getTotalpresentes() {
        return totalpresentes;
    }

    public void setTotalpresentes(Integer totalpresentes) {
        this.totalpresentes = totalpresentes;
    }

    public String getRecototalpresentes() {
        return recototalpresentes;
    }

    public void setRecototalpresentes(String recototalpresentes) {
        this.recototalpresentes = recototalpresentes;
    }

    public Integer getTotaldesistente() {
        return totaldesistente;
    }

    public void setTotaldesistente(Integer totaldesistente) {
        this.totaldesistente = totaldesistente;
    }

    public String getRecodesistente() {
        return recodesistente;
    }

    public void setRecodesistente(String recodesistente) {
        this.recodesistente = recodesistente;
    }

    public Integer getTotalrecrutados() {
        return totalrecrutados;
    }

    public void setTotalrecrutados(Integer totalrecrutados) {
        this.totalrecrutados = totalrecrutados;
    }

    public String getRecototalrecrutados() {
        return recototalrecrutados;
    }

    public void setRecototalrecrutados(String recototalrecrutados) {
        this.recototalrecrutados = recototalrecrutados;
    }

    public Boolean getGaranteassiduidade() {
        return garanteassiduidade;
    }

    public void setGaranteassiduidade(Boolean garanteassiduidade) {
        this.garanteassiduidade = garanteassiduidade;
    }

    public String getRecogaranteassiduidade() {
        return recogaranteassiduidade;
    }

    public void setRecogaranteassiduidade(String recogaranteassiduidade) {
        this.recogaranteassiduidade = recogaranteassiduidade;
    }

    public Boolean getUtilizainstrumentos() {
        return utilizainstrumentos;
    }

    public void setUtilizainstrumentos(Boolean utilizainstrumentos) {
        this.utilizainstrumentos = utilizainstrumentos;
    }

    public String getRecoutilizainstrumentos() {
        return recoutilizainstrumentos;
    }

    public void setRecoutilizainstrumentos(String recoutilizainstrumentos) {
        this.recoutilizainstrumentos = recoutilizainstrumentos;
    }

    public Boolean getComunicacao() {
        return comunicacao;
    }

    public void setComunicacao(Boolean comunicacao) {
        this.comunicacao = comunicacao;
    }

    public String getRecocomunicacao() {
        return recocomunicacao;
    }

    public void setRecocomunicacao(String recocomunicacao) {
        this.recocomunicacao = recocomunicacao;
    }

    public Boolean getDificuldadestecnicas() {
        return dificuldadestecnicas;
    }

    public void setDificuldadestecnicas(Boolean dificuldadestecnicas) {
        this.dificuldadestecnicas = dificuldadestecnicas;
    }

    public String getRecodificuldadestecnicas() {
        return recodificuldadestecnicas;
    }

    public void setRecodificuldadestecnicas(String recodificuldadestecnicas) {
        this.recodificuldadestecnicas = recodificuldadestecnicas;
    }

    public Boolean getDificuldadesteste() {
        return dificuldadesteste;
    }

    public void setDificuldadesteste(Boolean dificuldadesteste) {
        this.dificuldadesteste = dificuldadesteste;
    }

    public String getRecodificuldadesteste() {
        return recodificuldadesteste;
    }

    public void setRecodificuldadesteste(String recodificuldadesteste) {
        this.recodificuldadesteste = recodificuldadesteste;
    }

    public Cursoformacao getIdcursoformacao() {
        return idcursoformacao;
    }

    public void setIdcursoformacao(Cursoformacao idcursoformacao) {
        this.idcursoformacao = idcursoformacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsupervisao != null ? idsupervisao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supervisao)) {
            return false;
        }
        Supervisao other = (Supervisao) object;
        if ((this.idsupervisao == null && other.idsupervisao != null) || (this.idsupervisao != null && !this.idsupervisao.equals(other.idsupervisao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Supervisao[ idsupervisao=" + idsupervisao + " ]";
    }
    
}
