/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "artigo", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artigo.findAll", query = "SELECT a FROM Artigo a"),
    @NamedQuery(name = "Artigo.findByIdformando", query = "SELECT a FROM Artigo a WHERE a.artigoPK.idformando = :idformando"),
    @NamedQuery(name = "Artigo.findByIdproduto", query = "SELECT a FROM Artigo a WHERE a.artigoPK.idproduto = :idproduto"),
    @NamedQuery(name = "Artigo.findByQuantidade", query = "SELECT a FROM Artigo a WHERE a.quantidade = :quantidade")})
public class Artigo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ArtigoPK artigoPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantidade", precision = 12)
    private Float quantidade;
    @JoinColumn(name = "idmes", referencedColumnName = "idmes")
    @ManyToOne
    private Mes idmes;
    @JoinColumn(name = "idproduto", referencedColumnName = "idproduto", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produtos produtos;
    @JoinColumn(name = "idformando", referencedColumnName = "idformando", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Formacaoturma formacaoturma;

    public Artigo() {
    }

    public Artigo(ArtigoPK artigoPK) {
        this.artigoPK = artigoPK;
    }

    public Artigo(int idformando, int idproduto) {
        this.artigoPK = new ArtigoPK(idformando, idproduto);
    }

    public ArtigoPK getArtigoPK() {
        return artigoPK;
    }

    public void setArtigoPK(ArtigoPK artigoPK) {
        this.artigoPK = artigoPK;
    }

    public Float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }

    public Mes getIdmes() {
        return idmes;
    }

    public void setIdmes(Mes idmes) {
        this.idmes = idmes;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
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
        hash += (artigoPK != null ? artigoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artigo)) {
            return false;
        }
        Artigo other = (Artigo) object;
        if ((this.artigoPK == null && other.artigoPK != null) || (this.artigoPK != null && !this.artigoPK.equals(other.artigoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Artigo[ artigoPK=" + artigoPK + " ]";
    }
    
}
