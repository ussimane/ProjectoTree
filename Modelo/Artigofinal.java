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
@Table(name = "artigofinal", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artigofinal.findAll", query = "SELECT a FROM Artigofinal a"),
    @NamedQuery(name = "Artigofinal.findByIdformador", query = "SELECT a FROM Artigofinal a WHERE a.artigofinalPK.idformador = :idformador"),
    @NamedQuery(name = "Artigofinal.findByIdproduto", query = "SELECT a FROM Artigofinal a WHERE a.artigofinalPK.idproduto = :idproduto"),
    @NamedQuery(name = "Artigofinal.findByQuantidade", query = "SELECT a FROM Artigofinal a WHERE a.quantidade = :quantidade")})
public class Artigofinal implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ArtigofinalPK artigofinalPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantidade", precision = 12)
    private Float quantidade;
    @JoinColumn(name = "idmes", referencedColumnName = "idmes")
    @ManyToOne
    private Mes idmes;
    @JoinColumn(name = "idmestre", referencedColumnName = "idmestre")
    @ManyToOne
    private Mestre idmestre;
    @JoinColumn(name = "idproduto", referencedColumnName = "idproduto", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produtos produtos;
    @JoinColumn(name = "idformador", referencedColumnName = "idformador", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Formadorturma formadorturma;

    public Artigofinal() {
    }

    public Artigofinal(ArtigofinalPK artigofinalPK) {
        this.artigofinalPK = artigofinalPK;
    }

    public Artigofinal(int idformador, int idproduto) {
        this.artigofinalPK = new ArtigofinalPK(idformador, idproduto);
    }

    public ArtigofinalPK getArtigofinalPK() {
        return artigofinalPK;
    }

    public void setArtigofinalPK(ArtigofinalPK artigofinalPK) {
        this.artigofinalPK = artigofinalPK;
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

    public Mestre getIdmestre() {
        return idmestre;
    }

    public void setIdmestre(Mestre idmestre) {
        this.idmestre = idmestre;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

    public Formadorturma getFormadorturma() {
        return formadorturma;
    }

    public void setFormadorturma(Formadorturma formadorturma) {
        this.formadorturma = formadorturma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (artigofinalPK != null ? artigofinalPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artigofinal)) {
            return false;
        }
        Artigofinal other = (Artigofinal) object;
        if ((this.artigofinalPK == null && other.artigofinalPK != null) || (this.artigofinalPK != null && !this.artigofinalPK.equals(other.artigofinalPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Artigofinal[ artigofinalPK=" + artigofinalPK + " ]";
    }
    
}
