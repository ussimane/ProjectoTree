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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MASSINGUE
 */
@Entity
@Table(name = "produtos", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produtos.findAll", query = "SELECT p FROM Produtos p"),
    @NamedQuery(name = "Produtos.findByIdproduto", query = "SELECT p FROM Produtos p WHERE p.idproduto = :idproduto"),
    @NamedQuery(name = "Produtos.findByProduto", query = "SELECT p FROM Produtos p WHERE p.produto = :produto")})
public class Produtos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproduto", nullable = false)
    private Integer idproduto;
    @Column(name = "produto", length = 45)
    private String produto;
    @JoinColumn(name = "idcurso", referencedColumnName = "idcurso")
    @ManyToOne
    private Curso idcurso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produtos")
    private Collection<Artigofinal> artigofinalCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produtos")
    private Collection<Artigo> artigoCollection;

    public Produtos() {
    }

    public Produtos(Integer idproduto) {
        this.idproduto = idproduto;
    }

    public Integer getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Integer idproduto) {
        this.idproduto = idproduto;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Curso getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(Curso idcurso) {
        this.idcurso = idcurso;
    }

    @XmlTransient
    public Collection<Artigofinal> getArtigofinalCollection() {
        return artigofinalCollection;
    }

    public void setArtigofinalCollection(Collection<Artigofinal> artigofinalCollection) {
        this.artigofinalCollection = artigofinalCollection;
    }

    @XmlTransient
    public Collection<Artigo> getArtigoCollection() {
        return artigoCollection;
    }

    public void setArtigoCollection(Collection<Artigo> artigoCollection) {
        this.artigoCollection = artigoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproduto != null ? idproduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produtos)) {
            return false;
        }
        Produtos other = (Produtos) object;
        if ((this.idproduto == null && other.idproduto != null) || (this.idproduto != null && !this.idproduto.equals(other.idproduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return produto;
    }
    
}
