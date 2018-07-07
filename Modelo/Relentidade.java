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
@Table(name = "relentidade", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relentidade.findAll", query = "SELECT r FROM Relentidade r")})
public class Relentidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "localidade", nullable = false, length = 45)
    private String localidade;
    @Column(name = "distrito", length = 45)
    private String distrito;
    @Column(name = "posto", length = 45)
    private String posto;
    @Column(name = "inefph")
    private Integer inefph;
    @Column(name = "inefpm")
    private Integer inefpm;
    @Column(name = "inefpt")
    private Integer inefpt;
    @Column(name = "iabilh")
    private Integer iabilh;
    @Column(name = "iabilm")
    private Integer iabilm;
    @Column(name = "iabilt")
    private Integer iabilt;
    @Column(name = "coreh")
    private Integer coreh;
    @Column(name = "corem")
    private Integer corem;
    @Column(name = "coret")
    private Integer coret;
    @Column(name = "idpph")
    private Integer idpph;
    @Column(name = "idppm")
    private Integer idppm;
    @Column(name = "idppt")
    private Integer idppt;
    @Column(name = "outro")
    private Integer outro;

    public Relentidade() {
    }

    public Relentidade(String localidade) {
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

    public Integer getInefph() {
        return inefph;
    }

    public void setInefph(Integer inefph) {
        this.inefph = inefph;
    }

    public Integer getInefpm() {
        return inefpm;
    }

    public void setInefpm(Integer inefpm) {
        this.inefpm = inefpm;
    }

    public Integer getInefpt() {
        return inefpt;
    }

    public void setInefpt(Integer inefpt) {
        this.inefpt = inefpt;
    }

    public Integer getIabilh() {
        return iabilh;
    }

    public void setIabilh(Integer iabilh) {
        this.iabilh = iabilh;
    }

    public Integer getIabilm() {
        return iabilm;
    }

    public void setIabilm(Integer iabilm) {
        this.iabilm = iabilm;
    }

    public Integer getIabilt() {
        return iabilt;
    }

    public void setIabilt(Integer iabilt) {
        this.iabilt = iabilt;
    }

    public Integer getCoreh() {
        return coreh;
    }

    public void setCoreh(Integer coreh) {
        this.coreh = coreh;
    }

    public Integer getCorem() {
        return corem;
    }

    public void setCorem(Integer corem) {
        this.corem = corem;
    }

    public Integer getCoret() {
        return coret;
    }

    public void setCoret(Integer coret) {
        this.coret = coret;
    }

    public Integer getIdpph() {
        return idpph;
    }

    public void setIdpph(Integer idpph) {
        this.idpph = idpph;
    }

    public Integer getIdppm() {
        return idppm;
    }

    public void setIdppm(Integer idppm) {
        this.idppm = idppm;
    }

    public Integer getIdppt() {
        return idppt;
    }

    public void setIdppt(Integer idppt) {
        this.idppt = idppt;
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
        if (!(object instanceof Relentidade)) {
            return false;
        }
        Relentidade other = (Relentidade) object;
        if ((this.localidade == null && other.localidade != null) || (this.localidade != null && !this.localidade.equals(other.localidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Relentidade[ localidade=" + localidade + " ]";
    }
    
}
