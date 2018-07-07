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
@Table(name = "relemprego", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relemprego.findAll", query = "SELECT r FROM Relemprego r")})
public class Relemprego implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "localidade", nullable = false, length = 45)
    private String localidade;
    @Column(name = "distrito", length = 45)
    private String distrito;
    @Column(name = "posto", length = 45)
    private String posto;
    @Column(name = "tcoh")
    private Integer tcoh;
    @Column(name = "tcom")
    private Integer tcom;
    @Column(name = "tcph")
    private Integer tcph;
    @Column(name = "tcm")
    private Integer tcm;
    @Column(name = "outro")
    private Integer outro;
    @Column(name = "tcot")
    private Integer tcot;
    @Column(name = "tcpt")
    private Integer tcpt;

    public Relemprego() {
    }

    public Relemprego(String localidade) {
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

    public Integer getTcoh() {
        return tcoh;
    }

    public void setTcoh(Integer tcoh) {
        this.tcoh = tcoh;
    }

    public Integer getTcom() {
        return tcom;
    }

    public void setTcom(Integer tcom) {
        this.tcom = tcom;
    }

    public Integer getTcph() {
        return tcph;
    }

    public void setTcph(Integer tcph) {
        this.tcph = tcph;
    }

    public Integer getTcm() {
        return tcm;
    }

    public void setTcm(Integer tcm) {
        this.tcm = tcm;
    }

    public Integer getOutro() {
        return outro;
    }

    public void setOutro(Integer outro) {
        this.outro = outro;
    }

    public Integer getTcot() {
        return tcot;
    }

    public void setTcot(Integer tcot) {
        this.tcot = tcot;
    }

    public Integer getTcpt() {
        return tcpt;
    }

    public void setTcpt(Integer tcpt) {
        this.tcpt = tcpt;
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
        if (!(object instanceof Relemprego)) {
            return false;
        }
        Relemprego other = (Relemprego) object;
        if ((this.localidade == null && other.localidade != null) || (this.localidade != null && !this.localidade.equals(other.localidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Relemprego[ localidade=" + localidade + " ]";
    }
    
}
