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
@Table(name = "relformandoarea", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relformandoarea.findAll", query = "SELECT r FROM Relformandoarea r")})
public class Relformandoarea implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "localidade", nullable = false, length = 45)
    private String localidade;
    @Column(name = "nome", length = 45)
    private String nome;
    @Column(name = "idade", length = 45)
    private String idade;
    @Column(name = "contacto", length = 45)
    private String contacto;
    @Column(name = "nivelhabilitacao", length = 45)
    private String nivelhabilitacao;
    @Column(name = "sexo", length = 45)
    private String sexo;

    public Relformandoarea() {
    }

    public Relformandoarea(String localidade) {
        this.localidade = localidade;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getNivelhabilitacao() {
        return nivelhabilitacao;
    }

    public void setNivelhabilitacao(String nivelhabilitacao) {
        this.nivelhabilitacao = nivelhabilitacao;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (localidade != null ? localidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Relformandoarea)) {
            return false;
        }
        Relformandoarea other = (Relformandoarea) object;
        if ((this.localidade == null && other.localidade != null) || (this.localidade != null && !this.localidade.equals(other.localidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Relformandoarea[ localidade=" + localidade + " ]";
    }
    
}
