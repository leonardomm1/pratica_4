package com.unicamp.pedpao.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A ItemsAssinatura.
 */
@Entity
@Table(name = "items_assinatura")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ItemsAssinatura implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Min(value = 1L)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @NotNull
    @Min(value = 1L)
    @Column(name = "padaria_id", nullable = false)
    private Long padariaId;

    @NotNull
    @Size(min = 3, max = 255)
    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @NotNull
    @DecimalMin(value = "1")
    @Column(name = "valor", precision = 21, scale = 2, nullable = false)
    private BigDecimal valor;

    @Lob
    @Column(name = "foto")
    private byte[] foto;

    @Column(name = "foto_content_type")
    private String fotoContentType;

    @ManyToOne
    @JsonIgnoreProperties(value = { "padaria", "user" }, allowSetters = true)
    private Assinatura assinatura;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ItemsAssinatura id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPadariaId() {
        return this.padariaId;
    }

    public ItemsAssinatura padariaId(Long padariaId) {
        this.setPadariaId(padariaId);
        return this;
    }

    public void setPadariaId(Long padariaId) {
        this.padariaId = padariaId;
    }

    public String getNome() {
        return this.nome;
    }

    public ItemsAssinatura nome(String nome) {
        this.setNome(nome);
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public ItemsAssinatura valor(BigDecimal valor) {
        this.setValor(valor);
        return this;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public byte[] getFoto() {
        return this.foto;
    }

    public ItemsAssinatura foto(byte[] foto) {
        this.setFoto(foto);
        return this;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getFotoContentType() {
        return this.fotoContentType;
    }

    public ItemsAssinatura fotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
        return this;
    }

    public void setFotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
    }

    public Assinatura getAssinatura() {
        return this.assinatura;
    }

    public void setAssinatura(Assinatura assinatura) {
        this.assinatura = assinatura;
    }

    public ItemsAssinatura assinatura(Assinatura assinatura) {
        this.setAssinatura(assinatura);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemsAssinatura)) {
            return false;
        }
        return id != null && id.equals(((ItemsAssinatura) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemsAssinatura{" +
            "id=" + getId() +
            ", padariaId=" + getPadariaId() +
            ", nome='" + getNome() + "'" +
            ", valor=" + getValor() +
            ", foto='" + getFoto() + "'" +
            ", fotoContentType='" + getFotoContentType() + "'" +
            "}";
    }
}
