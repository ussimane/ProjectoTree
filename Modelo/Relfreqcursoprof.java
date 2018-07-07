/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author Ussimane
 */
@Entity
@Table(name = "relfreqcursoprof", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relfreqcursoprof.findAll", query = "SELECT r FROM Relfreqcursoprof r")})
public class Relfreqcursoprof implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "distrito", length = 45)
    private String distrito;
    @Column(name = "posto", length = 45)
    private String posto;
    @Id
    @Basic(optional = false)
    @Column(name = "localidade", nullable = false, length = 45)
    private String localidade;
    @Column(name = "tsh")
    private Integer tsh;
    @Column(name = "tnh")
    private Integer tnh;
    @Column(name = "tsm")
    private Integer tsm;
    @Column(name = "tnm")
    private Integer tnm;
    @Column(name = "tsim")
    private Integer tsim;
    @Column(name = "tnao")
    private Integer tnao;
    @Column(name = "total")
    private Integer total;

    public Relfreqcursoprof() {
    }

    public Relfreqcursoprof(String localidade) {
        this.localidade = localidade;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getPosto() {
        return posto;
    }

    public void setPosto(String posto) {
        this.posto = posto;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public Integer getTsh() {
        return tsh;
    }

    public void setTsh(Integer tsh) {
        this.tsh = tsh;
    }

    public Integer getTnh() {
        return tnh;
    }

    public void setTnh(Integer tnh) {
        this.tnh = tnh;
    }

    public Integer getTsm() {
        return tsm;
    }

    public void setTsm(Integer tsm) {
        this.tsm = tsm;
    }

    public Integer getTnm() {
        return tnm;
    }

    public void setTnm(Integer tnm) {
        this.tnm = tnm;
    }

    public Integer getTsim() {
        return tsim;
    }

    public void setTsim(Integer tsim) {
        this.tsim = tsim;
    }

    public Integer getTnao() {
        return tnao;
    }

    public void setTnao(Integer tnao) {
        this.tnao = tnao;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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
        if (!(object instanceof Relfreqcursoprof)) {
            return false;
        }
        Relfreqcursoprof other = (Relfreqcursoprof) object;
        if ((this.localidade == null && other.localidade != null) || (this.localidade != null && !this.localidade.equals(other.localidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Relfreqcursoprof[ localidade=" + localidade + " ]";
    }
    
}
