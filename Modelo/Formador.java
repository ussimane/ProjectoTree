/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "formador", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formador.findAll", query = "SELECT f FROM Formador f"),
    @NamedQuery(name = "Formador.findByIdformador", query = "SELECT f FROM Formador f WHERE f.idformador = :idformador"),
    @NamedQuery(name = "Formador.findByFormador", query = "SELECT f FROM Formador f WHERE f.formador = :formador"),
    @NamedQuery(name = "Formador.findBySexo", query = "SELECT f FROM Formador f WHERE f.sexo = :sexo"),
    @NamedQuery(name = "Formador.findByMorada", query = "SELECT f FROM Formador f WHERE f.morada = :morada"),
    @NamedQuery(name = "Formador.findByEmail", query = "SELECT f FROM Formador f WHERE f.email = :email"),
    @NamedQuery(name = "Formador.findByTelefone", query = "SELECT f FROM Formador f WHERE f.telefone = :telefone"),
    @NamedQuery(name = "Formador.findByTelemovel", query = "SELECT f FROM Formador f WHERE f.telemovel = :telemovel"),
    @NamedQuery(name = "Formador.findByBi", query = "SELECT f FROM Formador f WHERE f.bi = :bi"),
    @NamedQuery(name = "Formador.findByNuit", query = "SELECT f FROM Formador f WHERE f.nuit = :nuit"),
    @NamedQuery(name = "Formador.findByDatanasc", query = "SELECT f FROM Formador f WHERE f.datanasc = :datanasc"),
    @NamedQuery(name = "Formador.findByProfissao", query = "SELECT f FROM Formador f WHERE f.profissao = :profissao"),
    @NamedQuery(name = "Formador.findByAnosexperiencia", query = "SELECT f FROM Formador f WHERE f.anosexperiencia = :anosexperiencia"),
    @NamedQuery(name = "Formador.findByEmpregado", query = "SELECT f FROM Formador f WHERE f.empregado = :empregado"),
    @NamedQuery(name = "Formador.findByEntidadeempregadora", query = "SELECT f FROM Formador f WHERE f.entidadeempregadora = :entidadeempregadora"),
    @NamedQuery(name = "Formador.findByEmpresario", query = "SELECT f FROM Formador f WHERE f.empresario = :empresario"),
    @NamedQuery(name = "Formador.findByNomeempresa", query = "SELECT f FROM Formador f WHERE f.nomeempresa = :nomeempresa"),
    @NamedQuery(name = "Formador.findByOutrosituacao", query = "SELECT f FROM Formador f WHERE f.outrosituacao = :outrosituacao"),
    @NamedQuery(name = "Formador.findByLicenciatura", query = "SELECT f FROM Formador f WHERE f.licenciatura = :licenciatura"),
    @NamedQuery(name = "Formador.findByAreaformacao", query = "SELECT f FROM Formador f WHERE f.areaformacao = :areaformacao"),
    @NamedQuery(name = "Formador.findBySecundario", query = "SELECT f FROM Formador f WHERE f.secundario = :secundario"),
    @NamedQuery(name = "Formador.findByNivel", query = "SELECT f FROM Formador f WHERE f.nivel = :nivel"),
    @NamedQuery(name = "Formador.findByTecnico", query = "SELECT f FROM Formador f WHERE f.tecnico = :tecnico"),
    @NamedQuery(name = "Formador.findByCurso", query = "SELECT f FROM Formador f WHERE f.curso = :curso"),
    @NamedQuery(name = "Formador.findByOutro", query = "SELECT f FROM Formador f WHERE f.outro = :outro"),
    @NamedQuery(name = "Formador.findByEspecifique", query = "SELECT f FROM Formador f WHERE f.especifique = :especifique"),
    @NamedQuery(name = "Formador.findByPsicopedagogia", query = "SELECT f FROM Formador f WHERE f.psicopedagogia = :psicopedagogia"),
    @NamedQuery(name = "Formador.findByFormadorINEFP", query = "SELECT f FROM Formador f WHERE f.formadorINEFP = :formadorINEFP"),
    @NamedQuery(name = "Formador.findByExperienciaformador", query = "SELECT f FROM Formador f WHERE f.experienciaformador = :experienciaformador"),
    @NamedQuery(name = "Formador.findByBasico", query = "SELECT f FROM Formador f WHERE f.basico = :basico"),
    @NamedQuery(name = "Formador.findByNivelbasico", query = "SELECT f FROM Formador f WHERE f.nivelbasico = :nivelbasico")})
public class Formador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idformador", nullable = false)
    private Integer idformador;
    @Column(name = "formador", length = 45)
    private String formador;
    @Column(name = "sexo")
    private Boolean sexo;
    @Column(name = "morada", length = 45)
    private String morada;
    @Column(name = "email", length = 45)
    private String email;
    @Column(name = "telefone")
    private Integer telefone;
    @Column(name = "telemovel")
    private Integer telemovel;
    @Column(name = "bi", length = 45)
    private String bi;
    @Column(name = "nuit")
    private Integer nuit;
    @Column(name = "datanasc")
    @Temporal(TemporalType.DATE)
    private Date datanasc;
    @Column(name = "profissao", length = 45)
    private String profissao;
    @Column(name = "anosexperiencia")
    private Integer anosexperiencia;
    @Column(name = "empregado")
    private Boolean empregado;
    @Column(name = "entidadeempregadora", length = 45)
    private String entidadeempregadora;
    @Column(name = "empresario")
    private Boolean empresario;
    @Column(name = "nomeempresa", length = 45)
    private String nomeempresa;
    @Column(name = "outrosituacao", length = 45)
    private String outrosituacao;
    @Column(name = "Licenciatura")
    private Boolean licenciatura;
    @Column(name = "areaformacao", length = 45)
    private String areaformacao;
    @Column(name = "secundario")
    private Boolean secundario;
    @Column(name = "nivel", length = 45)
    private String nivel;
    @Column(name = "tecnico")
    private Boolean tecnico;
    @Column(name = "curso", length = 45)
    private String curso;
    @Column(name = "outro")
    private Boolean outro;
    @Column(name = "especifique", length = 45)
    private String especifique;
    @Column(name = "psicopedagogia")
    private Boolean psicopedagogia;
    @Column(name = "formadorINEFP")
    private Boolean formadorINEFP;
    @Lob
    @Column(name = "curricula")
    private byte[] curricula;
    @Column(name = "experienciaformador")
    private Integer experienciaformador;
    @Column(name = "basico")
    private Boolean basico;
    @Column(name = "nivelbasico", length = 45)
    private String nivelbasico;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "formador")
    private Formadorturma formadorturma;
    @JoinColumn(name = "iddistrito", referencedColumnName = "idlocalidade")
    @ManyToOne
    private Localidade iddistrito;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "formador")
    private Areaespecializacao areaespecializacao;
    @OneToMany(mappedBy = "idformador")
    private Collection<Formacaoturma> formacaoturmaCollection;

    public Formador() {
    }

    public Formador(Integer idformador) {
        this.idformador = idformador;
    }

    public Integer getIdformador() {
        return idformador;
    }

    public void setIdformador(Integer idformador) {
        this.idformador = idformador;
    }

    public String getFormador() {
        return formador;
    }

    public void setFormador(String formador) {
        this.formador = formador;
    }

    public Boolean getSexo() {
        return sexo;
    }

    public void setSexo(Boolean sexo) {
        this.sexo = sexo;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public Integer getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(Integer telemovel) {
        this.telemovel = telemovel;
    }

    public String getBi() {
        return bi;
    }

    public void setBi(String bi) {
        this.bi = bi;
    }

    public Integer getNuit() {
        return nuit;
    }

    public void setNuit(Integer nuit) {
        this.nuit = nuit;
    }

    public Date getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(Date datanasc) {
        this.datanasc = datanasc;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public Integer getAnosexperiencia() {
        return anosexperiencia;
    }

    public void setAnosexperiencia(Integer anosexperiencia) {
        this.anosexperiencia = anosexperiencia;
    }

    public Boolean getEmpregado() {
        return empregado;
    }

    public void setEmpregado(Boolean empregado) {
        this.empregado = empregado;
    }

    public String getEntidadeempregadora() {
        return entidadeempregadora;
    }

    public void setEntidadeempregadora(String entidadeempregadora) {
        this.entidadeempregadora = entidadeempregadora;
    }

    public Boolean getEmpresario() {
        return empresario;
    }

    public void setEmpresario(Boolean empresario) {
        this.empresario = empresario;
    }

    public String getNomeempresa() {
        return nomeempresa;
    }

    public void setNomeempresa(String nomeempresa) {
        this.nomeempresa = nomeempresa;
    }

    public String getOutrosituacao() {
        return outrosituacao;
    }

    public void setOutrosituacao(String outrosituacao) {
        this.outrosituacao = outrosituacao;
    }

    public Boolean getLicenciatura() {
        return licenciatura;
    }

    public void setLicenciatura(Boolean licenciatura) {
        this.licenciatura = licenciatura;
    }

    public String getAreaformacao() {
        return areaformacao;
    }

    public void setAreaformacao(String areaformacao) {
        this.areaformacao = areaformacao;
    }

    public Boolean getSecundario() {
        return secundario;
    }

    public void setSecundario(Boolean secundario) {
        this.secundario = secundario;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Boolean getTecnico() {
        return tecnico;
    }

    public void setTecnico(Boolean tecnico) {
        this.tecnico = tecnico;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Boolean getOutro() {
        return outro;
    }

    public void setOutro(Boolean outro) {
        this.outro = outro;
    }

    public String getEspecifique() {
        return especifique;
    }

    public void setEspecifique(String especifique) {
        this.especifique = especifique;
    }

    public Boolean getPsicopedagogia() {
        return psicopedagogia;
    }

    public void setPsicopedagogia(Boolean psicopedagogia) {
        this.psicopedagogia = psicopedagogia;
    }

    public Boolean getFormadorINEFP() {
        return formadorINEFP;
    }

    public void setFormadorINEFP(Boolean formadorINEFP) {
        this.formadorINEFP = formadorINEFP;
    }

    public byte[] getCurricula() {
        return curricula;
    }

    public void setCurricula(byte[] curricula) {
        this.curricula = curricula;
    }

    public Integer getExperienciaformador() {
        return experienciaformador;
    }

    public void setExperienciaformador(Integer experienciaformador) {
        this.experienciaformador = experienciaformador;
    }

    public Boolean getBasico() {
        return basico;
    }

    public void setBasico(Boolean basico) {
        this.basico = basico;
    }

    public String getNivelbasico() {
        return nivelbasico;
    }

    public void setNivelbasico(String nivelbasico) {
        this.nivelbasico = nivelbasico;
    }

    public Formadorturma getFormadorturma() {
        return formadorturma;
    }

    public void setFormadorturma(Formadorturma formadorturma) {
        this.formadorturma = formadorturma;
    }

    public Localidade getIddistrito() {
        return iddistrito;
    }

    public void setIddistrito(Localidade iddistrito) {
        this.iddistrito = iddistrito;
    }

    public Areaespecializacao getAreaespecializacao() {
        return areaespecializacao;
    }

    public void setAreaespecializacao(Areaespecializacao areaespecializacao) {
        this.areaespecializacao = areaespecializacao;
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
        hash += (idformador != null ? idformador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formador)) {
            return false;
        }
        Formador other = (Formador) object;
        if ((this.idformador == null && other.idformador != null) || (this.idformador != null && !this.idformador.equals(other.idformador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return formador;
    }
    
}
