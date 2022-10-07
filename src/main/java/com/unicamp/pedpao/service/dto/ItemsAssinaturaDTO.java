package com.unicamp.pedpao.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.unicamp.pedpao.domain.ItemsAssinatura} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ItemsAssinaturaDTO implements Serializable {

    @NotNull
    @Min(value = 1L)
    private Long id;

    @NotNull
    @Min(value = 1L)
    private Long padariaId;

    @NotNull
    @Size(min = 3, max = 255)
    private String nome;

    @NotNull
    @DecimalMin(value = "1")
    private BigDecimal valor;

    @Lob
    private byte[] foto;

    private String fotoContentType;
    private AssinaturaDTO assinatura;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPadariaId() {
        return padariaId;
    }

    public void setPadariaId(Long padariaId) {
        this.padariaId = padariaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getFotoContentType() {
        return fotoContentType;
    }

    public void setFotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
    }

    public AssinaturaDTO getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(AssinaturaDTO assinatura) {
        this.assinatura = assinatura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemsAssinaturaDTO)) {
            return false;
        }

        ItemsAssinaturaDTO itemsAssinaturaDTO = (ItemsAssinaturaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, itemsAssinaturaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemsAssinaturaDTO{" +
            "id=" + getId() +
            ", padariaId=" + getPadariaId() +
            ", nome='" + getNome() + "'" +
            ", valor=" + getValor() +
            ", foto='" + getFoto() + "'" +
            ", assinatura=" + getAssinatura() +
            "}";
    }
}
