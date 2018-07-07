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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MASSINGUE
 */
@Entity
@Table(name = "formadorturma", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formadorturma.findAll", query = "SELECT f FROM Formadorturma f"),
    @NamedQuery(name = "Formadorturma.findByIdformador", query = "SELECT f FROM Formadorturma f WHERE f.idformador = :idformador"),
    @NamedQuery(name = "Formadorturma.findByArealeccionar", query = "SELECT f FROM Formadorturma f WHERE f.arealeccionar = :arealeccionar"),
    @NamedQuery(name = "Formadorturma.findByTotalhorascurso", query = "SELECT f FROM Formadorturma f WHERE f.totalhorascurso = :totalhorascurso"),
    @NamedQuery(name = "Formadorturma.findByPracticacurso", query = "SELECT f FROM Formadorturma f WHERE f.practicacurso = :practicacurso"),
    @NamedQuery(name = "Formadorturma.findByTeoricacurso", query = "SELECT f FROM Formadorturma f WHERE f.teoricacurso = :teoricacurso"),
    @NamedQuery(name = "Formadorturma.findByTotalhorasmesmes", query = "SELECT f FROM Formadorturma f WHERE f.totalhorasmesmes = :totalhorasmesmes"),
    @NamedQuery(name = "Formadorturma.findByTeoricames", query = "SELECT f FROM Formadorturma f WHERE f.teoricames = :teoricames"),
    @NamedQuery(name = "Formadorturma.findByPracticames", query = "SELECT f FROM Formadorturma f WHERE f.practicames = :practicames"),
    @NamedQuery(name = "Formadorturma.findByTotalfalta", query = "SELECT f FROM Formadorturma f WHERE f.totalfalta = :totalfalta"),
    @NamedQuery(name = "Formadorturma.findByTeoricafalta", query = "SELECT f FROM Formadorturma f WHERE f.teoricafalta = :teoricafalta"),
    @NamedQuery(name = "Formadorturma.findByPracticafalta", query = "SELECT f FROM Formadorturma f WHERE f.practicafalta = :practicafalta"),
    @NamedQuery(name = "Formadorturma.findByAvaliacao", query = "SELECT f FROM Formadorturma f WHERE f.avaliacao = :avaliacao"),
    @NamedQuery(name = "Formadorturma.findByOcorrencias", query = "SELECT f FROM Formadorturma f WHERE f.ocorrencias = :ocorrencias"),
    @NamedQuery(name = "Formadorturma.findByTeoricames2", query = "SELECT f FROM Formadorturma f WHERE f.teoricames2 = :teoricames2"),
    @NamedQuery(name = "Formadorturma.findByPracticames2", query = "SELECT f FROM Formadorturma f WHERE f.practicames2 = :practicames2"),
    @NamedQuery(name = "Formadorturma.findByTeoricames3", query = "SELECT f FROM Formadorturma f WHERE f.teoricames3 = :teoricames3"),
    @NamedQuery(name = "Formadorturma.findByPracticames3", query = "SELECT f FROM Formadorturma f WHERE f.practicames3 = :practicames3"),
    @NamedQuery(name = "Formadorturma.findByTeoricafalta2", query = "SELECT f FROM Formadorturma f WHERE f.teoricafalta2 = :teoricafalta2"),
    @NamedQuery(name = "Formadorturma.findByPracticafalta2", query = "SELECT f FROM Formadorturma f WHERE f.practicafalta2 = :practicafalta2"),
    @NamedQuery(name = "Formadorturma.findByPracticafalta3", query = "SELECT f FROM Formadorturma f WHERE f.practicafalta3 = :practicafalta3"),
    @NamedQuery(name = "Formadorturma.findByTeoricafalta3", query = "SELECT f FROM Formadorturma f WHERE f.teoricafalta3 = :teoricafalta3"),
    @NamedQuery(name = "Formadorturma.findByTeoricacurso2", query = "SELECT f FROM Formadorturma f WHERE f.teoricacurso2 = :teoricacurso2"),
    @NamedQuery(name = "Formadorturma.findByPracticacurso2", query = "SELECT f FROM Formadorturma f WHERE f.practicacurso2 = :practicacurso2"),
    @NamedQuery(name = "Formadorturma.findByPracticacurso3", query = "SELECT f FROM Formadorturma f WHERE f.practicacurso3 = :practicacurso3"),
    @NamedQuery(name = "Formadorturma.findByTeoricacurso3", query = "SELECT f FROM Formadorturma f WHERE f.teoricacurso3 = :teoricacurso3")})
public class Formadorturma implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idformador", nullable = false)
    private Integer idformador;
    @Column(name = "arealeccionar", length = 45)
    private String arealeccionar;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalhorascurso", precision = 12)
    private Float totalhorascurso;
    @Column(name = "practicacurso", precision = 12)
    private Float practicacurso;
    @Column(name = "teoricacurso", precision = 12)
    private Float teoricacurso;
    @Column(name = "totalhorasmesmes", precision = 12)
    private Float totalhorasmesmes;
    @Column(name = "teoricames", precision = 12)
    private Float teoricames;
    @Column(name = "practicames", precision = 12)
    private Float practicames;
    @Column(name = "totalfalta", precision = 12)
    private Float totalfalta;
    @Column(name = "teoricafalta", precision = 12)
    private Float teoricafalta;
    @Column(name = "practicafalta", precision = 12)
    private Float practicafalta;
    @Column(name = "avaliacao", length = 500)
    private String avaliacao;
    @Column(name = "ocorrencias", length = 500)
    private String ocorrencias;
    @Column(name = "teoricames2", precision = 12)
    private Float teoricames2;
    @Column(name = "practicames2", precision = 12)
    private Float practicames2;
    @Column(name = "teoricames3", precision = 12)
    private Float teoricames3;
    @Column(name = "practicames3", precision = 12)
    private Float practicames3;
    @Column(name = "teoricafalta2", precision = 12)
    private Float teoricafalta2;
    @Column(name = "practicafalta2", precision = 12)
    private Float practicafalta2;
    @Column(name = "practicafalta3", precision = 12)
    private Float practicafalta3;
    @Column(name = "teoricafalta3", precision = 12)
    private Float teoricafalta3;
    @Column(name = "teoricacurso2", precision = 12)
    private Float teoricacurso2;
    @Column(name = "practicacurso2", precision = 12)
    private Float practicacurso2;
    @Column(name = "practicacurso3", precision = 12)
    private Float practicacurso3;
    @Column(name = "teoricacurso3", precision = 12)
    private Float teoricacurso3;
    @JoinColumn(name = "idformador", referencedColumnName = "idformador", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Formador formador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formadorturma")
    private Collection<Artigofinal> artigofinalCollection;

    public Formadorturma() {
    }

    public Formadorturma(Integer idformador) {
        this.idformador = idformador;
    }

    public Integer getIdformador() {
        return idformador;
    }

    public void setIdformador(Integer idformador) {
        this.idformador = idformador;
    }

    public String getArealeccionar() {
        return arealeccionar;
    }

    public void setArealeccionar(String arealeccionar) {
        this.arealeccionar = arealeccionar;
    }

    public Float getTotalhorascurso() {
        return totalhorascurso;
    }

    public void setTotalhorascurso(Float totalhorascurso) {
        this.totalhorascurso = totalhorascurso;
    }

    public Float getPracticacurso() {
        return practicacurso;
    }

    public void setPracticacurso(Float practicacurso) {
        this.practicacurso = practicacurso;
    }

    public Float getTeoricacurso() {
        return teoricacurso;
    }

    public void setTeoricacurso(Float teoricacurso) {
        this.teoricacurso = teoricacurso;
    }

    public Float getTotalhorasmesmes() {
        return totalhorasmesmes;
    }

    public void setTotalhorasmesmes(Float totalhorasmesmes) {
        this.totalhorasmesmes = totalhorasmesmes;
    }

    public Float getTeoricames() {
        return teoricames;
    }

    public void setTeoricames(Float teoricames) {
        this.teoricames = teoricames;
    }

    public Float getPracticames() {
        return practicames;
    }

    public void setPracticames(Float practicames) {
        this.practicames = practicames;
    }

    public Float getTotalfalta() {
        return totalfalta;
    }

    public void setTotalfalta(Float totalfalta) {
        this.totalfalta = totalfalta;
    }

    public Float getTeoricafalta() {
        return teoricafalta;
    }

    public void setTeoricafalta(Float teoricafalta) {
        this.teoricafalta = teoricafalta;
    }

    public Float getPracticafalta() {
        return practicafalta;
    }

    public void setPracticafalta(Float practicafalta) {
        this.practicafalta = practicafalta;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(String ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    public Float getTeoricames2() {
        return teoricames2;
    }

    public void setTeoricames2(Float teoricames2) {
        this.teoricames2 = teoricames2;
    }

    public Float getPracticames2() {
        return practicames2;
    }

    public void setPracticames2(Float practicames2) {
        this.practicames2 = practicames2;
    }

    public Float getTeoricames3() {
        return teoricames3;
    }

    public void setTeoricames3(Float teoricames3) {
        this.teoricames3 = teoricames3;
    }

    public Float getPracticames3() {
        return practicames3;
    }

    public void setPracticames3(Float practicames3) {
        this.practicames3 = practicames3;
    }

    public Float getTeoricafalta2() {
        return teoricafalta2;
    }

    public void setTeoricafalta2(Float teoricafalta2) {
        this.teoricafalta2 = teoricafalta2;
    }

    public Float getPracticafalta2() {
        return practicafalta2;
    }

    public void setPracticafalta2(Float practicafalta2) {
        this.practicafalta2 = practicafalta2;
    }

    public Float getPracticafalta3() {
        return practicafalta3;
    }

    public void setPracticafalta3(Float practicafalta3) {
        this.practicafalta3 = practicafalta3;
    }

    public Float getTeoricafalta3() {
        return teoricafalta3;
    }

    public void setTeoricafalta3(Float teoricafalta3) {
        this.teoricafalta3 = teoricafalta3;
    }

    public Float getTeoricacurso2() {
        return teoricacurso2;
    }

    public void setTeoricacurso2(Float teoricacurso2) {
        this.teoricacurso2 = teoricacurso2;
    }

    public Float getPracticacurso2() {
        return practicacurso2;
    }

    public void setPracticacurso2(Float practicacurso2) {
        this.practicacurso2 = practicacurso2;
    }

    public Float getPracticacurso3() {
        return practicacurso3;
    }

    public void setPracticacurso3(Float practicacurso3) {
        this.practicacurso3 = practicacurso3;
    }

    public Float getTeoricacurso3() {
        return teoricacurso3;
    }

    public void setTeoricacurso3(Float teoricacurso3) {
        this.teoricacurso3 = teoricacurso3;
    }

    public Formador getFormador() {
        return formador;
    }

    public void setFormador(Formador formador) {
        this.formador = formador;
    }

    @XmlTransient
    public Collection<Artigofinal> getArtigofinalCollection() {
        return artigofinalCollection;
    }

    public void setArtigofinalCollection(Collection<Artigofinal> artigofinalCollection) {
        this.artigofinalCollection = artigofinalCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idformador != null ? idformador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formadorturma)) {
            return false;
        }
        Formadorturma other = (Formadorturma) object;
        if ((this.idformador == null && other.idformador != null) || (this.idformador != null && !this.idformador.equals(other.idformador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Formadorturma[ idformador=" + idformador + " ]";
    }
    
}
