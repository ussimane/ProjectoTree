/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MASSINGUE
 */
@Entity
@Table(name = "utilizador", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilizador.findAll", query = "SELECT u FROM Utilizador u"),
    @NamedQuery(name = "Utilizador.findByIdutilizador", query = "SELECT u FROM Utilizador u WHERE u.idutilizador = :idutilizador"),
    @NamedQuery(name = "Utilizador.findByNome", query = "SELECT u FROM Utilizador u WHERE u.nome = :nome"),
    @NamedQuery(name = "Utilizador.findByUtilizador", query = "SELECT u FROM Utilizador u WHERE u.utilizador = :utilizador"),
    @NamedQuery(name = "Utilizador.findBySenha", query = "SELECT u FROM Utilizador u WHERE u.senha = :senha")})
public class Utilizador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idutilizador", nullable = false)
    private Integer idutilizador;
    @Column(name = "nome", length = 45)
    private String nome;
    @Column(name = "utilizador", length = 45)
    private String utilizador;
    @Column(name = "senha", length = 45)
    private String senha;
    @JoinColumn(name = "idprevilegio", referencedColumnName = "idprevilegio")
    @ManyToOne
    private Previlegio idprevilegio;

    public Utilizador() {
    }

    public Utilizador(Integer idutilizador) {
        this.idutilizador = idutilizador;
    }

    public Integer getIdutilizador() {
        return idutilizador;
    }

    public void setIdutilizador(Integer idutilizador) {
        this.idutilizador = idutilizador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(String utilizador) {
        this.utilizador = utilizador;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Previlegio getIdprevilegio() {
        return idprevilegio;
    }

    public void setIdprevilegio(Previlegio idprevilegio) {
        this.idprevilegio = idprevilegio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idutilizador != null ? idutilizador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilizador)) {
            return false;
        }
        Utilizador other = (Utilizador) object;
        if ((this.idutilizador == null && other.idutilizador != null) || (this.idutilizador != null && !this.idutilizador.equals(other.idutilizador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return utilizador;
    }
    
}
