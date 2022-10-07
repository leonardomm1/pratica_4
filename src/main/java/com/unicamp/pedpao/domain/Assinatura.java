package com.unicamp.pedpao.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Assinatura.
 */
@Entity
@Table(name = "assinatura")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Assinatura implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Min(value = 1L)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @NotNull
    @Column(name = "valor", precision = 21, scale = 2, nullable = false)
    private BigDecimal valor;

    @NotNull
    @Size(min = 3, max = 255)
    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @NotNull
    @Min(value = 1)
    @Column(name = "quantidade_dias", nullable = false)
    private Integer quantidadeDias;

    @NotNull
    @Column(name = "ativa", nullable = false)
    private Boolean ativa;

    @NotNull
    @Min(value = 1L)
    @Column(name = "pagamento_recorrencia_id", nullable = false)
    private Long pagamentoRecorrenciaId;

    @Lob
    @Column(name = "foto")
    private byte[] foto;

    @Column(name = "foto_content_type")
    private String fotoContentType;

    @Min(value = 1)
    @Column(name = "quantidade")
    private Integer quantidade;

    @NotNull
    @Column(name = "horario_recebimento", nullable = false)
    private ZonedDateTime horarioRecebimento;

    @NotNull
    @Size(min = 3)
    @Column(name = "tipo_assinatura", nullable = false)
    private String tipoAssinatura;

    @Lob
    @Column(name = "dia_da_semana", nullable = false)
    private String diaDaSemana;

    @ManyToOne
    @JsonIgnoreProperties(value = { "user" }, allowSetters = true)
    private Padaria padaria;

    @ManyToOne
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Assinatura id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public Assinatura valor(BigDecimal valor) {
        this.setValor(valor);
        return this;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNome() {
        return this.nome;
    }

    public Assinatura nome(String nome) {
        this.setNome(nome);
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidadeDias() {
        return this.quantidadeDias;
    }

    public Assinatura quantidadeDias(Integer quantidadeDias) {
        this.setQuantidadeDias(quantidadeDias);
        return this;
    }

    public void setQuantidadeDias(Integer quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
    }

    public Boolean getAtiva() {
        return this.ativa;
    }

    public Assinatura ativa(Boolean ativa) {
        this.setAtiva(ativa);
        return this;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    public Long getPagamentoRecorrenciaId() {
        return this.pagamentoRecorrenciaId;
    }

    public Assinatura pagamentoRecorrenciaId(Long pagamentoRecorrenciaId) {
        this.setPagamentoRecorrenciaId(pagamentoRecorrenciaId);
        return this;
    }

    public void setPagamentoRecorrenciaId(Long pagamentoRecorrenciaId) {
        this.pagamentoRecorrenciaId = pagamentoRecorrenciaId;
    }

    public byte[] getFoto() {
        return this.foto;
    }

    public Assinatura foto(byte[] foto) {
        this.setFoto(foto);
        return this;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getFotoContentType() {
        return this.fotoContentType;
    }

    public Assinatura fotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
        return this;
    }

    public void setFotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
    }

    public Integer getQuantidade() {
        return this.quantidade;
    }

    public Assinatura quantidade(Integer quantidade) {
        this.setQuantidade(quantidade);
        return this;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public ZonedDateTime getHorarioRecebimento() {
        return this.horarioRecebimento;
    }

    public Assinatura horarioRecebimento(ZonedDateTime horarioRecebimento) {
        this.setHorarioRecebimento(horarioRecebimento);
        return this;
    }

    public void setHorarioRecebimento(ZonedDateTime horarioRecebimento) {
        this.horarioRecebimento = horarioRecebimento;
    }

    public String getTipoAssinatura() {
        return this.tipoAssinatura;
    }

    public Assinatura tipoAssinatura(String tipoAssinatura) {
        this.setTipoAssinatura(tipoAssinatura);
        return this;
    }

    public void setTipoAssinatura(String tipoAssinatura) {
        this.tipoAssinatura = tipoAssinatura;
    }

    public String getDiaDaSemana() {
        return this.diaDaSemana;
    }

    public Assinatura diaDaSemana(String diaDaSemana) {
        this.setDiaDaSemana(diaDaSemana);
        return this;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public Padaria getPadaria() {
        return this.padaria;
    }

    public void setPadaria(Padaria padaria) {
        this.padaria = padaria;
    }

    public Assinatura padaria(Padaria padaria) {
        this.setPadaria(padaria);
        return this;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Assinatura user(User user) {
        this.setUser(user);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Assinatura)) {
            return false;
        }
        return id != null && id.equals(((Assinatura) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Assinatura{" +
            "id=" + getId() +
            ", valor=" + getValor() +
            ", nome='" + getNome() + "'" +
            ", quantidadeDias=" + getQuantidadeDias() +
            ", ativa='" + getAtiva() + "'" +
            ", pagamentoRecorrenciaId=" + getPagamentoRecorrenciaId() +
            ", foto='" + getFoto() + "'" +
            ", fotoContentType='" + getFotoContentType() + "'" +
            ", quantidade=" + getQuantidade() +
            ", horarioRecebimento='" + getHorarioRecebimento() + "'" +
            ", tipoAssinatura='" + getTipoAssinatura() + "'" +
            ", diaDaSemana='" + getDiaDaSemana() + "'" +
            "}";
    }
}
