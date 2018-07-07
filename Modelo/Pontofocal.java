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
@Table(name = "pontofocal", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pontofocal.findAll", query = "SELECT p FROM Pontofocal p"),
    @NamedQuery(name = "Pontofocal.findByIdpontofocal", query = "SELECT p FROM Pontofocal p WHERE p.idpontofocal = :idpontofocal"),
    @NamedQuery(name = "Pontofocal.findByNome", query = "SELECT p FROM Pontofocal p WHERE p.nome = :nome"),
    @NamedQuery(name = "Pontofocal.findBySexo", query = "SELECT p FROM Pontofocal p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "Pontofocal.findByMorada", query = "SELECT p FROM Pontofocal p WHERE p.morada = :morada"),
    @NamedQuery(name = "Pontofocal.findByEmail", query = "SELECT p FROM Pontofocal p WHERE p.email = :email"),
    @NamedQuery(name = "Pontofocal.findByTelefone", query = "SELECT p FROM Pontofocal p WHERE p.telefone = :telefone"),
    @NamedQuery(name = "Pontofocal.findByBi", query = "SELECT p FROM Pontofocal p WHERE p.bi = :bi"),
    @NamedQuery(name = "Pontofocal.findByNuit", query = "SELECT p FROM Pontofocal p WHERE p.nuit = :nuit"),
    @NamedQuery(name = "Pontofocal.findByDatanasc", query = "SELECT p FROM Pontofocal p WHERE p.datanasc = :datanasc"),
    @NamedQuery(name = "Pontofocal.findByProfissao", query = "SELECT p FROM Pontofocal p WHERE p.profissao = :profissao")})
public class Pontofocal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpontofocal", nullable = false)
    private Integer idpontofocal;
    @Column(name = "nome", length = 45)
    private String nome;
    @Column(name = "sexo")
    private Boolean sexo;
    @Column(name = "morada", length = 45)
    private String morada;
    @Column(name = "email", length = 45)
    private String email;
    @Column(name = "telefone")
    private Integer telefone;
    @Column(name = "bi", length = 45)
    private String bi;
    @Column(name = "nuit")
    private Integer nuit;
    @Column(name = "datanasc")
    @Temporal(TemporalType.DATE)
    private Date datanasc;
    @Column(name = "profissao", length = 45)
    private String profissao;
    @OneToMany(mappedBy = "idpontofocal")
    private Collection<Cursoformacao> cursoformacaoCollection;
    @JoinColumn(name = "idnivelhabilitacao", referencedColumnName = "idnivelhabilitacao")
    @ManyToOne
    private Nivelhabilitacao idnivelhabilitacao;
    @JoinColumn(name = "iddistrito", referencedColumnName = "iddistrito")
    @ManyToOne
    private Distrito iddistrito;

    public Pontofocal() {
    }

    public Pontofocal(Integer idpontofocal) {
        this.idpontofocal = idpontofocal;
    }

    public Integer getIdpontofocal() {
        return idpontofocal;
    }

    public void setIdpontofocal(Integer idpontofocal) {
        this.idpontofocal = idpontofocal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    @XmlTransient
    public Collection<Cursoformacao> getCursoformacaoCollection() {
        return cursoformacaoCollection;
    }

    public void setCursoformacaoCollection(Collection<Cursoformacao> cursoformacaoCollection) {
        this.cursoformacaoCollection = cursoformacaoCollection;
    }

    public Nivelhabilitacao getIdnivelhabilitacao() {
        return idnivelhabilitacao;
    }

    public void setIdnivelhabilitacao(Nivelhabilitacao idnivelhabilitacao) {
        this.idnivelhabilitacao = idnivelhabilitacao;
    }

    public Distrito getIddistrito() {
        return iddistrito;
    }

    public void setIddistrito(Distrito iddistrito) {
        this.iddistrito = iddistrito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpontofocal != null ? idpontofocal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pontofocal)) {
            return false;
        }
        Pontofocal other = (Pontofocal) object;
        if ((this.idpontofocal == null && other.idpontofocal != null) || (this.idpontofocal != null && !this.idpontofocal.equals(other.idpontofocal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
    
}
