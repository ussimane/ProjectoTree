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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MASSINGUE
 */
@Entity
@Table(name = "relatorioformando")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relatorioformando.findAll", query = "SELECT r FROM Relatorioformando r"),
    @NamedQuery(name = "Relatorioformando.findByIdrelatorioformando", query = "SELECT r FROM Relatorioformando r WHERE r.idrelatorioformando = :idrelatorioformando"),
    @NamedQuery(name = "Relatorioformando.findByNome", query = "SELECT r FROM Relatorioformando r WHERE r.nome = :nome"),
    @NamedQuery(name = "Relatorioformando.findBySexo", query = "SELECT r FROM Relatorioformando r WHERE r.sexo = :sexo"),
    @NamedQuery(name = "Relatorioformando.findByIdade", query = "SELECT r FROM Relatorioformando r WHERE r.idade = :idade"),
    @NamedQuery(name = "Relatorioformando.findByEstadocivil", query = "SELECT r FROM Relatorioformando r WHERE r.estadocivil = :estadocivil"),
    @NamedQuery(name = "Relatorioformando.findByLocalidade", query = "SELECT r FROM Relatorioformando r WHERE r.localidade = :localidade"),
    @NamedQuery(name = "Relatorioformando.findByNragregado", query = "SELECT r FROM Relatorioformando r WHERE r.nragregado = :nragregado"),
    @NamedQuery(name = "Relatorioformando.findByNivelhabilitacao", query = "SELECT r FROM Relatorioformando r WHERE r.nivelhabilitacao = :nivelhabilitacao"),
    @NamedQuery(name = "Relatorioformando.findByActividadeprofissional", query = "SELECT r FROM Relatorioformando r WHERE r.actividadeprofissional = :actividadeprofissional"),
    @NamedQuery(name = "Relatorioformando.findByAreaactividade", query = "SELECT r FROM Relatorioformando r WHERE r.areaactividade = :areaactividade"),
    @NamedQuery(name = "Relatorioformando.findByActividadecontapropria", query = "SELECT r FROM Relatorioformando r WHERE r.actividadecontapropria = :actividadecontapropria"),
    @NamedQuery(name = "Relatorioformando.findByAreaactvidadeconta", query = "SELECT r FROM Relatorioformando r WHERE r.areaactvidadeconta = :areaactvidadeconta"),
    @NamedQuery(name = "Relatorioformando.findByEmpregado", query = "SELECT r FROM Relatorioformando r WHERE r.empregado = :empregado"),
    @NamedQuery(name = "Relatorioformando.findByNomeempregador", query = "SELECT r FROM Relatorioformando r WHERE r.nomeempregador = :nomeempregador")})
public class Relatorioformando implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idrelatorioformando")
    private Integer idrelatorioformando;
    @Column(name = "nome")
    private String nome;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "idade")
    private String idade;
    @Column(name = "estadocivil")
    private String estadocivil;
    @Column(name = "localidade")
    private String localidade;
    @Column(name = "nragregado")
    private String nragregado;
    @Column(name = "nivelhabilitacao")
    private String nivelhabilitacao;
    @Column(name = "actividadeprofissional")
    private String actividadeprofissional;
    @Column(name = "areaactividade")
    private String areaactividade;
    @Column(name = "actividadecontapropria")
    private String actividadecontapropria;
    @Column(name = "areaactvidadeconta")
    private String areaactvidadeconta;
    @Column(name = "empregado")
    private String empregado;
    @Column(name = "nomeempregador")
    private String nomeempregador;

    public Relatorioformando() {
    }

    public Relatorioformando(Integer idrelatorioformando) {
        this.idrelatorioformando = idrelatorioformando;
    }

    public Integer getIdrelatorioformando() {
        return idrelatorioformando;
    }

    public void setIdrelatorioformando(Integer idrelatorioformando) {
        this.idrelatorioformando = idrelatorioformando;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getNragregado() {
        return nragregado;
    }

    public void setNragregado(String nragregado) {
        this.nragregado = nragregado;
    }

    public String getNivelhabilitacao() {
        return nivelhabilitacao;
    }

    public void setNivelhabilitacao(String nivelhabilitacao) {
        this.nivelhabilitacao = nivelhabilitacao;
    }

    public String getActividadeprofissional() {
        return actividadeprofissional;
    }

    public void setActividadeprofissional(String actividadeprofissional) {
        this.actividadeprofissional = actividadeprofissional;
    }

    public String getAreaactividade() {
        return areaactividade;
    }

    public void setAreaactividade(String areaactividade) {
        this.areaactividade = areaactividade;
    }

    public String getActividadecontapropria() {
        return actividadecontapropria;
    }

    public void setActividadecontapropria(String actividadecontapropria) {
        this.actividadecontapropria = actividadecontapropria;
    }

    public String getAreaactvidadeconta() {
        return areaactvidadeconta;
    }

    public void setAreaactvidadeconta(String areaactvidadeconta) {
        this.areaactvidadeconta = areaactvidadeconta;
    }

    public String getEmpregado() {
        return empregado;
    }

    public void setEmpregado(String empregado) {
        this.empregado = empregado;
    }

    public String getNomeempregador() {
        return nomeempregador;
    }

    public void setNomeempregador(String nomeempregador) {
        this.nomeempregador = nomeempregador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrelatorioformando != null ? idrelatorioformando.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Relatorioformando)) {
            return false;
        }
        Relatorioformando other = (Relatorioformando) object;
        if ((this.idrelatorioformando == null && other.idrelatorioformando != null) || (this.idrelatorioformando != null && !this.idrelatorioformando.equals(other.idrelatorioformando))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Relatorioformando[ idrelatorioformando=" + idrelatorioformando + " ]";
    }
    
}
