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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "mestre", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mestre.findAll", query = "SELECT m FROM Mestre m"),
    @NamedQuery(name = "Mestre.findByIdmestre", query = "SELECT m FROM Mestre m WHERE m.idmestre = :idmestre"),
    @NamedQuery(name = "Mestre.findByNome", query = "SELECT m FROM Mestre m WHERE m.nome = :nome"),
    @NamedQuery(name = "Mestre.findByContacto", query = "SELECT m FROM Mestre m WHERE m.contacto = :contacto"),
    @NamedQuery(name = "Mestre.findBySexo", query = "SELECT m FROM Mestre m WHERE m.sexo = :sexo"),
    @NamedQuery(name = "Mestre.findByIdade", query = "SELECT m FROM Mestre m WHERE m.idade = :idade"),
    @NamedQuery(name = "Mestre.findByNacionalidade", query = "SELECT m FROM Mestre m WHERE m.nacionalidade = :nacionalidade"),
    @NamedQuery(name = "Mestre.findByDiplomainefp", query = "SELECT m FROM Mestre m WHERE m.diplomainefp = :diplomainefp"),
    @NamedQuery(name = "Mestre.findByCurso", query = "SELECT m FROM Mestre m WHERE m.curso = :curso"),
    @NamedQuery(name = "Mestre.findByDiplomaprof", query = "SELECT m FROM Mestre m WHERE m.diplomaprof = :diplomaprof"),
    @NamedQuery(name = "Mestre.findByFormacaotecnica", query = "SELECT m FROM Mestre m WHERE m.formacaotecnica = :formacaotecnica"),
    @NamedQuery(name = "Mestre.findByEntidadeformadora", query = "SELECT m FROM Mestre m WHERE m.entidadeformadora = :entidadeformadora"),
    @NamedQuery(name = "Mestre.findByProducao", query = "SELECT m FROM Mestre m WHERE m.producao = :producao"),
    @NamedQuery(name = "Mestre.findByOutrahabilidade", query = "SELECT m FROM Mestre m WHERE m.outrahabilidade = :outrahabilidade"),
    @NamedQuery(name = "Mestre.findByAreaactividade", query = "SELECT m FROM Mestre m WHERE m.areaactividade = :areaactividade")})
public class Mestre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmestre", nullable = false)
    private Integer idmestre;
    @Column(name = "nome", length = 45)
    private String nome;
    @Column(name = "contacto")
    private Integer contacto;
    @Column(name = "sexo")
    private Boolean sexo;
    @Column(name = "idade")
    private Integer idade;
    @Column(name = "nacionalidade", length = 45)
    private String nacionalidade;
    @Column(name = "diplomainefp")
    private Boolean diplomainefp;
    @Column(name = "curso", length = 45)
    private String curso;
    @Column(name = "diplomaprof")
    private Boolean diplomaprof;
    @Column(name = "formacaotecnica")
    private Boolean formacaotecnica;
    @Column(name = "entidadeformadora", length = 100)
    private String entidadeformadora;
    @Column(name = "producao", length = 100)
    private String producao;
    @Column(name = "outrahabilidade", length = 45)
    private String outrahabilidade;
    @Column(name = "areaactividade", length = 45)
    private String areaactividade;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "mestre")
    private Oficinamestre oficinamestre;
    @JoinColumn(name = "idnivelprof", referencedColumnName = "idnivelprofissional")
    @ManyToOne
    private Nivelprofissional idnivelprof;
    @JoinColumn(name = "idnivelhabilitacao", referencedColumnName = "idnivelhabilitacao")
    @ManyToOne
    private Nivelhabilitacao idnivelhabilitacao;
    @JoinColumn(name = "idlocalidade", referencedColumnName = "idlocalidade")
    @ManyToOne
    private Localidade idlocalidade;
    @JoinColumn(name = "idcurso", referencedColumnName = "idcurso")
    @ManyToOne
    private Curso idcurso;
    @OneToMany(mappedBy = "idmestre")
    private Collection<Formacaoturma> formacaoturmaCollection;
    @OneToMany(mappedBy = "idmestre")
    private Collection<Artigofinal> artigofinalCollection;

    public Mestre() {
    }

    public Mestre(Integer idmestre) {
        this.idmestre = idmestre;
    }

    public Integer getIdmestre() {
        return idmestre;
    }

    public void setIdmestre(Integer idmestre) {
        this.idmestre = idmestre;
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

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
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

    public Boolean getFormacaotecnica() {
        return formacaotecnica;
    }

    public void setFormacaotecnica(Boolean formacaotecnica) {
        this.formacaotecnica = formacaotecnica;
    }

    public String getEntidadeformadora() {
        return entidadeformadora;
    }

    public void setEntidadeformadora(String entidadeformadora) {
        this.entidadeformadora = entidadeformadora;
    }

    public String getProducao() {
        return producao;
    }

    public void setProducao(String producao) {
        this.producao = producao;
    }

    public String getOutrahabilidade() {
        return outrahabilidade;
    }

    public void setOutrahabilidade(String outrahabilidade) {
        this.outrahabilidade = outrahabilidade;
    }

    public String getAreaactividade() {
        return areaactividade;
    }

    public void setAreaactividade(String areaactividade) {
        this.areaactividade = areaactividade;
    }

    public Oficinamestre getOficinamestre() {
        return oficinamestre;
    }

    public void setOficinamestre(Oficinamestre oficinamestre) {
        this.oficinamestre = oficinamestre;
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

    public Curso getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(Curso idcurso) {
        this.idcurso = idcurso;
    }

    @XmlTransient
    public Collection<Formacaoturma> getFormacaoturmaCollection() {
        return formacaoturmaCollection;
    }

    public void setFormacaoturmaCollection(Collection<Formacaoturma> formacaoturmaCollection) {
        this.formacaoturmaCollection = formacaoturmaCollection;
    }

    @XmlTransient
    public Collection<Artigofinal> getArtigofinalCollection() {
        return artigofinalCollection;
    }

    public void setArtigofinalCollection(Collection<Artigofinal> artigofinalCollection) {
        this.artigofinalCollection = artigofinalCollection;
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
        if (!(object instanceof Mestre)) {
            return false;
        }
        Mestre other = (Mestre) object;
        if ((this.idmestre == null && other.idmestre != null) || (this.idmestre != null && !this.idmestre.equals(other.idmestre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
    
}
