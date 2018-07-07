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
@Table(name = "relhabilitacao", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relhabilitacao.findAll", query = "SELECT r FROM Relhabilitacao r")})
public class Relhabilitacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "distrito", length = 45)
    private String distrito;
    @Column(name = "posto", length = 45)
    private String posto;
    @Id
    @Basic(optional = false)
    @Column(name = "localidade", nullable = false, length = 45)
    private String localidade;
    @Column(name = "naosabelerh")
    private Integer naosabelerh;
    @Column(name = "naosabelerm")
    private Integer naosabelerm;
    @Column(name = "naosabelert")
    private Integer naosabelert;
    @Column(name = "basicocompletoh")
    private Integer basicocompletoh;
    @Column(name = "basicocompletom")
    private Integer basicocompletom;
    @Column(name = "basicocompletot")
    private Integer basicocompletot;
    @Column(name = "basicoincompletoh")
    private Integer basicoincompletoh;
    @Column(name = "basicoincompletom")
    private Integer basicoincompletom;
    @Column(name = "basicoincompletot")
    private Integer basicoincompletot;
    @Column(name = "secundarioincompletoh")
    private Integer secundarioincompletoh;
    @Column(name = "secundarioincompletom")
    private Integer secundarioincompletom;
    @Column(name = "secundarioincompletot")
    private Integer secundarioincompletot;
    @Column(name = "secundariocompletoh")
    private Integer secundariocompletoh;
    @Column(name = "secundariocompletom")
    private Integer secundariocompletom;
    @Column(name = "secundariocompletot")
    private Integer secundariocompletot;

    public Relhabilitacao() {
    }

    public Relhabilitacao(String localidade) {
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

    public Integer getNaosabelerh() {
        return naosabelerh;
    }

    public void setNaosabelerh(Integer naosabelerh) {
        this.naosabelerh = naosabelerh;
    }

    public Integer getNaosabelerm() {
        return naosabelerm;
    }

    public void setNaosabelerm(Integer naosabelerm) {
        this.naosabelerm = naosabelerm;
    }

    public Integer getNaosabelert() {
        return naosabelert;
    }

    public void setNaosabelert(Integer naosabelert) {
        this.naosabelert = naosabelert;
    }

    public Integer getBasicocompletoh() {
        return basicocompletoh;
    }

    public void setBasicocompletoh(Integer basicocompletoh) {
        this.basicocompletoh = basicocompletoh;
    }

    public Integer getBasicocompletom() {
        return basicocompletom;
    }

    public void setBasicocompletom(Integer basicocompletom) {
        this.basicocompletom = basicocompletom;
    }

    public Integer getBasicocompletot() {
        return basicocompletot;
    }

    public void setBasicocompletot(Integer basicocompletot) {
        this.basicocompletot = basicocompletot;
    }

    public Integer getBasicoincompletoh() {
        return basicoincompletoh;
    }

    public void setBasicoincompletoh(Integer basicoincompletoh) {
        this.basicoincompletoh = basicoincompletoh;
    }

    public Integer getBasicoincompletom() {
        return basicoincompletom;
    }

    public void setBasicoincompletom(Integer basicoincompletom) {
        this.basicoincompletom = basicoincompletom;
    }

    public Integer getBasicoincompletot() {
        return basicoincompletot;
    }

    public void setBasicoincompletot(Integer basicoincompletot) {
        this.basicoincompletot = basicoincompletot;
    }

    public Integer getSecundarioincompletoh() {
        return secundarioincompletoh;
    }

    public void setSecundarioincompletoh(Integer secundarioincompletoh) {
        this.secundarioincompletoh = secundarioincompletoh;
    }

    public Integer getSecundarioincompletom() {
        return secundarioincompletom;
    }

    public void setSecundarioincompletom(Integer secundarioincompletom) {
        this.secundarioincompletom = secundarioincompletom;
    }

    public Integer getSecundarioincompletot() {
        return secundarioincompletot;
    }

    public void setSecundarioincompletot(Integer secundarioincompletot) {
        this.secundarioincompletot = secundarioincompletot;
    }

    public Integer getSecundariocompletoh() {
        return secundariocompletoh;
    }

    public void setSecundariocompletoh(Integer secundariocompletoh) {
        this.secundariocompletoh = secundariocompletoh;
    }

    public Integer getSecundariocompletom() {
        return secundariocompletom;
    }

    public void setSecundariocompletom(Integer secundariocompletom) {
        this.secundariocompletom = secundariocompletom;
    }

    public Integer getSecundariocompletot() {
        return secundariocompletot;
    }

    public void setSecundariocompletot(Integer secundariocompletot) {
        this.secundariocompletot = secundariocompletot;
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
        if (!(object instanceof Relhabilitacao)) {
            return false;
        }
        Relhabilitacao other = (Relhabilitacao) object;
        if ((this.localidade == null && other.localidade != null) || (this.localidade != null && !this.localidade.equals(other.localidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Relhabilitacao[ localidade=" + localidade + " ]";
    }
    
}
