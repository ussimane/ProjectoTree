/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MASSINGUE
 */
@Entity
@Table(name = "formacaoturma", catalog = "ptree", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formacaoturma.findAll", query = "SELECT f FROM Formacaoturma f"),
    @NamedQuery(name = "Formacaoturma.findByIdformando", query = "SELECT f FROM Formacaoturma f WHERE f.idformando = :idformando"),
    @NamedQuery(name = "Formacaoturma.findByHorasmesprimeiro", query = "SELECT f FROM Formacaoturma f WHERE f.horasmesprimeiro = :horasmesprimeiro"),
    @NamedQuery(name = "Formacaoturma.findByHorasmesegundo", query = "SELECT f FROM Formacaoturma f WHERE f.horasmesegundo = :horasmesegundo"),
    @NamedQuery(name = "Formacaoturma.findByHorasmesterceiro", query = "SELECT f FROM Formacaoturma f WHERE f.horasmesterceiro = :horasmesterceiro"),
    @NamedQuery(name = "Formacaoturma.findByHorasfaltaprimeiro", query = "SELECT f FROM Formacaoturma f WHERE f.horasfaltaprimeiro = :horasfaltaprimeiro"),
    @NamedQuery(name = "Formacaoturma.findByHorasfaltasegundo", query = "SELECT f FROM Formacaoturma f WHERE f.horasfaltasegundo = :horasfaltasegundo"),
    @NamedQuery(name = "Formacaoturma.findByHorasfaltaterceiro", query = "SELECT f FROM Formacaoturma f WHERE f.horasfaltaterceiro = :horasfaltaterceiro"),
    @NamedQuery(name = "Formacaoturma.findByDinicio", query = "SELECT f FROM Formacaoturma f WHERE f.dinicio = :dinicio"),
    @NamedQuery(name = "Formacaoturma.findByDfim", query = "SELECT f FROM Formacaoturma f WHERE f.dfim = :dfim"),
    @NamedQuery(name = "Formacaoturma.findByAprovacao", query = "SELECT f FROM Formacaoturma f WHERE f.aprovacao = :aprovacao"),
    @NamedQuery(name = "Formacaoturma.findByDesistencia", query = "SELECT f FROM Formacaoturma f WHERE f.desistencia = :desistencia"),
    @NamedQuery(name = "Formacaoturma.findByClassificacao", query = "SELECT f FROM Formacaoturma f WHERE f.classificacao = :classificacao")})
public class Formacaoturma implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idformando", nullable = false)
    private Integer idformando;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "horasmesprimeiro", precision = 12)
    private Float horasmesprimeiro;
    @Column(name = "horasmesegundo", precision = 12)
    private Float horasmesegundo;
    @Column(name = "horasmesterceiro", precision = 12)
    private Float horasmesterceiro;
    @Column(name = "horasfaltaprimeiro", precision = 12)
    private Float horasfaltaprimeiro;
    @Column(name = "horasfaltasegundo", precision = 12)
    private Float horasfaltasegundo;
    @Column(name = "horasfaltaterceiro", precision = 12)
    private Float horasfaltaterceiro;
    @Column(name = "dinicio")
    @Temporal(TemporalType.DATE)
    private Date dinicio;
    @Column(name = "dfim")
    @Temporal(TemporalType.DATE)
    private Date dfim;
    @Column(name = "aprovacao")
    private Boolean aprovacao;
    @Column(name = "desistencia")
    private Boolean desistencia;
    @Column(name = "classificacao")
    private Integer classificacao;
    @JoinColumn(name = "idcursoformacao", referencedColumnName = "idcursoformacao")
    @ManyToOne
    private Cursoformacao idcursoformacao;
    @JoinColumn(name = "idmestre", referencedColumnName = "idmestre")
    @ManyToOne
    private Mestre idmestre;
    @JoinColumn(name = "idformador", referencedColumnName = "idformador")
    @ManyToOne
    private Formador idformador;
    @JoinColumn(name = "idformando", referencedColumnName = "idformando", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Formando formando;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formacaoturma")
    private Collection<Artigo> artigoCollection;

    public Formacaoturma() {
    }

    public Formacaoturma(Integer idformando) {
        this.idformando = idformando;
    }

    public Integer getIdformando() {
        return idformando;
    }

    public void setIdformando(Integer idformando) {
        this.idformando = idformando;
    }

    public Float getHorasmesprimeiro() {
        return horasmesprimeiro;
    }

    public void setHorasmesprimeiro(Float horasmesprimeiro) {
        this.horasmesprimeiro = horasmesprimeiro;
    }

    public Float getHorasmesegundo() {
        return horasmesegundo;
    }

    public void setHorasmesegundo(Float horasmesegundo) {
        this.horasmesegundo = horasmesegundo;
    }

    public Float getHorasmesterceiro() {
        return horasmesterceiro;
    }

    public void setHorasmesterceiro(Float horasmesterceiro) {
        this.horasmesterceiro = horasmesterceiro;
    }

    public Float getHorasfaltaprimeiro() {
        return horasfaltaprimeiro;
    }

    public void setHorasfaltaprimeiro(Float horasfaltaprimeiro) {
        this.horasfaltaprimeiro = horasfaltaprimeiro;
    }

    public Float getHorasfaltasegundo() {
        return horasfaltasegundo;
    }

    public void setHorasfaltasegundo(Float horasfaltasegundo) {
        this.horasfaltasegundo = horasfaltasegundo;
    }

    public Float getHorasfaltaterceiro() {
        return horasfaltaterceiro;
    }

    public void setHorasfaltaterceiro(Float horasfaltaterceiro) {
        this.horasfaltaterceiro = horasfaltaterceiro;
    }

    public Date getDinicio() {
        return dinicio;
    }

    public void setDinicio(Date dinicio) {
        this.dinicio = dinicio;
    }

    public Date getDfim() {
        return dfim;
    }

    public void setDfim(Date dfim) {
        this.dfim = dfim;
    }

    public Boolean getAprovacao() {
        return aprovacao;
    }

    public void setAprovacao(Boolean aprovacao) {
        this.aprovacao = aprovacao;
    }

    public Boolean getDesistencia() {
        return desistencia;
    }

    public void setDesistencia(Boolean desistencia) {
        this.desistencia = desistencia;
    }

    public Integer getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Integer classificacao) {
        this.classificacao = classificacao;
    }

    public Cursoformacao getIdcursoformacao() {
        return idcursoformacao;
    }

    public void setIdcursoformacao(Cursoformacao idcursoformacao) {
        this.idcursoformacao = idcursoformacao;
    }

    public Mestre getIdmestre() {
        return idmestre;
    }

    public void setIdmestre(Mestre idmestre) {
        this.idmestre = idmestre;
    }

    public Formador getIdformador() {
        return idformador;
    }

    public void setIdformador(Formador idformador) {
        this.idformador = idformador;
    }

    public Formando getFormando() {
        return formando;
    }

    public void setFormando(Formando formando) {
        this.formando = formando;
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
        hash += (idformando != null ? idformando.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formacaoturma)) {
            return false;
        }
        Formacaoturma other = (Formacaoturma) object;
        if ((this.idformando == null && other.idformando != null) || (this.idformando != null && !this.idformando.equals(other.idformando))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Formacaoturma[ idformando=" + idformando + " ]";
    }
    
}
