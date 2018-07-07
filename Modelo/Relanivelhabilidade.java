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
@Table(name = "relanivelhabilidade", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relanivelhabilidade.findAll", query = "SELECT r FROM Relanivelhabilidade r")})
public class Relanivelhabilidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "localidade", nullable = false, length = 45)
    private String localidade;
    @Column(name = "distrito", length = 45)
    private String distrito;
    @Column(name = "posto", length = 45)
    private String posto;
    @Column(name = "liceh")
    private Integer liceh;
    @Column(name = "licem")
    private Integer licem;
    @Column(name = "lict")
    private Integer lict;
    @Column(name = "sech")
    private Integer sech;
    @Column(name = "secm")
    private Integer secm;
    @Column(name = "sect")
    private Integer sect;
    @Column(name = "bash")
    private Integer bash;
    @Column(name = "basm")
    private Integer basm;
    @Column(name = "bast")
    private Integer bast;
    @Column(name = "tech")
    private Integer tech;
    @Column(name = "tecm")
    private Integer tecm;
    @Column(name = "tect")
    private Integer tect;
    @Column(name = "outro")
    private Integer outro;

    public Relanivelhabilidade() {
    }

    public Relanivelhabilidade(String localidade) {
        this.localidade = localidade;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
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

    public Integer getLiceh() {
        return liceh;
    }

    public void setLiceh(Integer liceh) {
        this.liceh = liceh;
    }

    public Integer getLicem() {
        return licem;
    }

    public void setLicem(Integer licem) {
        this.licem = licem;
    }

    public Integer getLict() {
        return lict;
    }

    public void setLict(Integer lict) {
        this.lict = lict;
    }

    public Integer getSech() {
        return sech;
    }

    public void setSech(Integer sech) {
        this.sech = sech;
    }

    public Integer getSecm() {
        return secm;
    }

    public void setSecm(Integer secm) {
        this.secm = secm;
    }

    public Integer getSect() {
        return sect;
    }

    public void setSect(Integer sect) {
        this.sect = sect;
    }

    public Integer getBash() {
        return bash;
    }

    public void setBash(Integer bash) {
        this.bash = bash;
    }

    public Integer getBasm() {
        return basm;
    }

    public void setBasm(Integer basm) {
        this.basm = basm;
    }

    public Integer getBast() {
        return bast;
    }

    public void setBast(Integer bast) {
        this.bast = bast;
    }

    public Integer getTech() {
        return tech;
    }

    public void setTech(Integer tech) {
        this.tech = tech;
    }

    public Integer getTecm() {
        return tecm;
    }

    public void setTecm(Integer tecm) {
        this.tecm = tecm;
    }

    public Integer getTect() {
        return tect;
    }

    public void setTect(Integer tect) {
        this.tect = tect;
    }

    public Integer getOutro() {
        return outro;
    }

    public void setOutro(Integer outro) {
        this.outro = outro;
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
        if (!(object instanceof Relanivelhabilidade)) {
            return false;
        }
        Relanivelhabilidade other = (Relanivelhabilidade) object;
        if ((this.localidade == null && other.localidade != null) || (this.localidade != null && !this.localidade.equals(other.localidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Relanivelhabilidade[ localidade=" + localidade + " ]";
    }
    
}
