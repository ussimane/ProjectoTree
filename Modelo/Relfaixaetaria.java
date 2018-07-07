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
@Table(name = "relfaixaetaria", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relfaixaetaria.findAll", query = "SELECT r FROM Relfaixaetaria r")})
public class Relfaixaetaria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "distrito", length = 45)
    private String distrito;
    @Column(name = "posto", length = 45)
    private String posto;
    @Id
    @Basic(optional = false)
    @Column(name = "localidade", nullable = false, length = 45)
    private String localidade;
    @Column(name = "quinzeh")
    private Integer quinzeh;
    @Column(name = "quinzem")
    private Integer quinzem;
    @Column(name = "quinzet")
    private Integer quinzet;
    @Column(name = "desanoveh")
    private Integer desanoveh;
    @Column(name = "desanovem")
    private Integer desanovem;
    @Column(name = "desanovet")
    private Integer desanovet;
    @Column(name = "vintecincoh")
    private Integer vintecincoh;
    @Column(name = "vintecincom")
    private Integer vintecincom;
    @Column(name = "vintecincot")
    private Integer vintecincot;
    @Column(name = "maish")
    private Integer maish;
    @Column(name = "maism")
    private Integer maism;
    @Column(name = "maist")
    private Integer maist;

    public Relfaixaetaria() {
    }

    public Relfaixaetaria(String localidade) {
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

    public Integer getQuinzeh() {
        return quinzeh;
    }

    public void setQuinzeh(Integer quinzeh) {
        this.quinzeh = quinzeh;
    }

    public Integer getQuinzem() {
        return quinzem;
    }

    public void setQuinzem(Integer quinzem) {
        this.quinzem = quinzem;
    }

    public Integer getQuinzet() {
        return quinzet;
    }

    public void setQuinzet(Integer quinzet) {
        this.quinzet = quinzet;
    }

    public Integer getDesanoveh() {
        return desanoveh;
    }

    public void setDesanoveh(Integer desanoveh) {
        this.desanoveh = desanoveh;
    }

    public Integer getDesanovem() {
        return desanovem;
    }

    public void setDesanovem(Integer desanovem) {
        this.desanovem = desanovem;
    }

    public Integer getDesanovet() {
        return desanovet;
    }

    public void setDesanovet(Integer desanovet) {
        this.desanovet = desanovet;
    }

    public Integer getVintecincoh() {
        return vintecincoh;
    }

    public void setVintecincoh(Integer vintecincoh) {
        this.vintecincoh = vintecincoh;
    }

    public Integer getVintecincom() {
        return vintecincom;
    }

    public void setVintecincom(Integer vintecincom) {
        this.vintecincom = vintecincom;
    }

    public Integer getVintecincot() {
        return vintecincot;
    }

    public void setVintecincot(Integer vintecincot) {
        this.vintecincot = vintecincot;
    }

    public Integer getMaish() {
        return maish;
    }

    public void setMaish(Integer maish) {
        this.maish = maish;
    }

    public Integer getMaism() {
        return maism;
    }

    public void setMaism(Integer maism) {
        this.maism = maism;
    }

    public Integer getMaist() {
        return maist;
    }

    public void setMaist(Integer maist) {
        this.maist = maist;
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
        if (!(object instanceof Relfaixaetaria)) {
            return false;
        }
        Relfaixaetaria other = (Relfaixaetaria) object;
        if ((this.localidade == null && other.localidade != null) || (this.localidade != null && !this.localidade.equals(other.localidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Relfaixaetaria[ localidade=" + localidade + " ]";
    }
    
}
