/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "formando", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formando.findAll", query = "SELECT f FROM Formando f"),
    @NamedQuery(name = "Formando.findByIdformando", query = "SELECT f FROM Formando f WHERE f.idformando = :idformando"),
    @NamedQuery(name = "Formando.findByNome", query = "SELECT f FROM Formando f WHERE f.nome = :nome"),
    @NamedQuery(name = "Formando.findByContacto", query = "SELECT f FROM Formando f WHERE f.contacto = :contacto"),
    @NamedQuery(name = "Formando.findBySexo", query = "SELECT f FROM Formando f WHERE f.sexo = :sexo"),
    @NamedQuery(name = "Formando.findByIdade", query = "SELECT f FROM Formando f WHERE f.idade = :idade"),
    @NamedQuery(name = "Formando.findByNragregado", query = "SELECT f FROM Formando f WHERE f.nragregado = :nragregado"),
    @NamedQuery(name = "Formando.findByChefefamilia", query = "SELECT f FROM Formando f WHERE f.chefefamilia = :chefefamilia"),
    @NamedQuery(name = "Formando.findByFilho", query = "SELECT f FROM Formando f WHERE f.filho = :filho"),
    @NamedQuery(name = "Formando.findByNrfilhos", query = "SELECT f FROM Formando f WHERE f.nrfilhos = :nrfilhos"),
    @NamedQuery(name = "Formando.findByCasapropria", query = "SELECT f FROM Formando f WHERE f.casapropria = :casapropria"),
    @NamedQuery(name = "Formando.findByDificiente", query = "SELECT f FROM Formando f WHERE f.dificiente = :dificiente"),
    @NamedQuery(name = "Formando.findByTipodificiencia", query = "SELECT f FROM Formando f WHERE f.tipodificiencia = :tipodificiencia"),
    @NamedQuery(name = "Formando.findByDiplomainefp", query = "SELECT f FROM Formando f WHERE f.diplomainefp = :diplomainefp"),
    @NamedQuery(name = "Formando.findByCurso", query = "SELECT f FROM Formando f WHERE f.curso = :curso"),
    @NamedQuery(name = "Formando.findByDiplomaprof", query = "SELECT f FROM Formando f WHERE f.diplomaprof = :diplomaprof"),
    @NamedQuery(name = "Formando.findByOutrahabilidades", query = "SELECT f FROM Formando f WHERE f.outrahabilidades = :outrahabilidades"),
    @NamedQuery(name = "Formando.findByActividadeprofissional", query = "SELECT f FROM Formando f WHERE f.actividadeprofissional = :actividadeprofissional"),
    @NamedQuery(name = "Formando.findByAreaactividade", query = "SELECT f FROM Formando f WHERE f.areaactividade = :areaactividade"),
    @NamedQuery(name = "Formando.findByEmpregado", query = "SELECT f FROM Formando f WHERE f.empregado = :empregado"),
    @NamedQuery(name = "Formando.findByEmpregador", query = "SELECT f FROM Formando f WHERE f.empregador = :empregador"),
    @NamedQuery(name = "Formando.findByRendimentomedio", query = "SELECT f FROM Formando f WHERE f.rendimentomedio = :rendimentomedio")})
public class Formando implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idformando", nullable = false)
    private Integer idformando;
    @Column(name = "nome", length = 45)
    private String nome;
    @Column(name = "contacto")
    private Integer contacto;
    @Column(name = "sexo")
    private Boolean sexo;
    @Column(name = "idade")
    private Integer idade;
    @Column(name = "nragregado")
    private Integer nragregado;
    @Column(name = "chefefamilia")
    private Boolean chefefamilia;
    @Column(name = "filho")
    private Boolean filho;
    @Column(name = "nrfilhos")
    private Integer nrfilhos;
    @Column(name = "casapropria")
    private Boolean casapropria;
    @Column(name = "dificiente")
    private Boolean dificiente;
    @Column(name = "tipodificiencia", length = 45)
    private String tipodificiencia;
    @Column(name = "diplomainefp")
    private Boolean diplomainefp;
    @Column(name = "curso", length = 45)
    private String curso;
    @Column(name = "diplomaprof")
    private Boolean diplomaprof;
    @Column(name = "outrahabilidades", length = 45)
    private String outrahabilidades;
    @Column(name = "actividadeprofissional")
    private Boolean actividadeprofissional;
    @Column(name = "areaactividade", length = 45)
    private String areaactividade;
    @Column(name = "empregado")
    private Boolean empregado;
    @Column(name = "empregador", length = 45)
    private String empregador;
    @Column(name = "rendimentomedio")
    private Integer rendimentomedio;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "formando")
    private Accaoformacao accaoformacao;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "formando")
    private Actividadecontapropria actividadecontapropria;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "formando")
    private Areadeformacao areadeformacao;
    @JoinColumn(name = "idnivelprof", referencedColumnName = "idnivelprofissional")
    @ManyToOne
    private Nivelprofissional idnivelprof;
    @JoinColumn(name = "idnivelhabilitacao", referencedColumnName = "idnivelhabilitacao")
    @ManyToOne
    private Nivelhabilitacao idnivelhabilitacao;
    @JoinColumn(name = "idlocalidade", referencedColumnName = "idlocalidade")
    @ManyToOne
    private Localidade idlocalidade;
    @JoinColumn(name = "idestadocivil", referencedColumnName = "idestadocivil")
    @ManyToOne
    private Estadocivil idestadocivil;
    @JoinColumn(name = "idcurso", referencedColumnName = "idcurso", nullable = false)
    @ManyToOne(optional = false)
    private Curso idcurso;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "formando")
    private Formacaoturma formacaoturma;

    public Formando() {
    }

    public Formando(Integer idformando) {
        this.idformando = idformando;
    }

    public Integer getIdformando() {
        return idformando;
    }

    public void setIdformando(Integer idformando) {
        this.idformando = idformando;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getContacto() {
        return contacto;
    }

    public void setContacto(Integer contacto) {
        this.contacto = contacto;
    }

    public Boolean getSexo() {
        return sexo;
    }

    public void setSexo(Boolean sexo) {
        this.sexo = sexo;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Integer getNragregado() {
        return nragregado;
    }

    public void setNragregado(Integer nragregado) {
        this.nragregado = nragregado;
    }

    public Boolean getChefefamilia() {
        return chefefamilia;
    }

    public void setChefefamilia(Boolean chefefamilia) {
        this.chefefamilia = chefefamilia;
    }

    public Boolean getFilho() {
        return filho;
    }

    public void setFilho(Boolean filho) {
        this.filho = filho;
    }

    public Integer getNrfilhos() {
        return nrfilhos;
    }

    public void setNrfilhos(Integer nrfilhos) {
        this.nrfilhos = nrfilhos;
    }

    public Boolean getCasapropria() {
        return casapropria;
    }

    public void setCasapropria(Boolean casapropria) {
        this.casapropria = casapropria;
    }

    public Boolean getDificiente() {
        return dificiente;
    }

    public void setDificiente(Boolean dificiente) {
        this.dificiente = dificiente;
    }

    public String getTipodificiencia() {
        return tipodificiencia;
    }

    public void setTipodificiencia(String tipodificiencia) {
        this.tipodificiencia = tipodificiencia;
    }

    public Boolean getDiplomainefp() {
        return diplomainefp;
    }

    public void setDiplomainefp(Boolean diplomainefp) {
        this.diplomainefp = diplomainefp;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Boolean getDiplomaprof() {
        return diplomaprof;
    }

    public void setDiplomaprof(Boolean diplomaprof) {
        this.diplomaprof = diplomaprof;
    }

    public String getOutrahabilidades() {
        return outrahabilidades;
    }

    public void setOutrahabilidades(String outrahabilidades) {
        this.outrahabilidades = outrahabilidades;
    }

    public Boolean getActividadeprofissional() {
        return actividadeprofissional;
    }

    public void setActividadeprofissional(Boolean actividadeprofissional) {
        this.actividadeprofissional = actividadeprofissional;
    }

    public String getAreaactividade() {
        return areaactividade;
    }

    public void setAreaactividade(String areaactividade) {
        this.areaactividade = areaactividade;
    }

    public Boolean getEmpregado() {
        return empregado;
    }

    public void setEmpregado(Boolean empregado) {
        this.empregado = empregado;
    }

    public String getEmpregador() {
        return empregador;
    }

    public void setEmpregador(String empregador) {
        this.empregador = empregador;
    }

    public Integer getRendimentomedio() {
        return rendimentomedio;
    }

    public void setRendimentomedio(Integer rendimentomedio) {
        this.rendimentomedio = rendimentomedio;
    }

    public Accaoformacao getAccaoformacao() {
        return accaoformacao;
    }

    public void setAccaoformacao(Accaoformacao accaoformacao) {
        this.accaoformacao = accaoformacao;
    }

    public Actividadecontapropria getActividadecontapropria() {
        return actividadecontapropria;
    }

    public void setActividadecontapropria(Actividadecontapropria actividadecontapropria) {
        this.actividadecontapropria = actividadecontapropria;
    }

    public Areadeformacao getAreadeformacao() {
        return areadeformacao;
    }

    public void setAreadeformacao(Areadeformacao areadeformacao) {
        this.areadeformacao = areadeformacao;
    }

    public Nivelprofissional getIdnivelprof() {
        return idnivelprof;
    }

    public void setIdnivelprof(Nivelprofissional idnivelprof) {
        this.idnivelprof = idnivelprof;
    }

    public Nivelhabilitacao getIdnivelhabilitacao() {
        return idnivelhabilitacao;
    }

    public void setIdnivelhabilitacao(Nivelhabilitacao idnivelhabilitacao) {
        this.idnivelhabilitacao = idnivelhabilitacao;
    }

    public Localidade getIdlocalidade() {
        return idlocalidade;
    }

    public void setIdlocalidade(Localidade idlocalidade) {
        this.idlocalidade = idlocalidade;
    }

    public Estadocivil getIdestadocivil() {
        return idestadocivil;
    }

    public void setIdestadocivil(Estadocivil idestadocivil) {
        this.idestadocivil = idestadocivil;
    }

    public Curso getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(Curso idcurso) {
        this.idcurso = idcurso;
    }

    public Formacaoturma getFormacaoturma() {
        return formacaoturma;
    }

    public void setFormacaoturma(Formacaoturma formacaoturma) {
        this.formacaoturma = formacaoturma;
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
        if (!(object instanceof Formando)) {
            return false;
        }
        Formando other = (Formando) object;
        if ((this.idformando == null && other.idformando != null) || (this.idformando != null && !this.idformando.equals(other.idformando))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
    
}
