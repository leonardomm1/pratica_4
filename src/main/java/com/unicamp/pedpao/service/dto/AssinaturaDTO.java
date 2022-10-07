package com.unicamp.pedpao.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.unicamp.pedpao.domain.Assinatura} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AssinaturaDTO implements Serializable {

    @NotNull
    @Min(value = 1L)
    private Long id;

    @NotNull
    private BigDecimal valor;

    @NotNull
    @Size(min = 3, max = 255)
    private String nome;

    @NotNull
    @Min(value = 1)
    private Integer quantidadeDias;

    @NotNull
    private Boolean ativa;

    @NotNull
    @Min(value = 1L)
    private Long pagamentoRecorrenciaId;

    @Lob
    private byte[] foto;

    private String fotoContentType;

    @Min(value = 1)
    private Integer quantidade;

    @NotNull
    private ZonedDateTime horarioRecebimento;

    @NotNull
    @Size(min = 3)
    private String tipoAssinatura;

    @Lob
    private String diaDaSemana;

    private PadariaDTO padaria;

    private UserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidadeDias() {
        return quantidadeDias;
    }

    public void setQuantidadeDias(Integer quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    public Long getPagamentoRecorrenciaId() {
        return pagamentoRecorrenciaId;
    }

    public void setPagamentoRecorrenciaId(Long pagamentoRecorrenciaId) {
        this.pagamentoRecorrenciaId = pagamentoRecorrenciaId;
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public ZonedDateTime getHorarioRecebimento() {
        return horarioRecebimento;
    }

    public void setHorarioRecebimento(ZonedDateTime horarioRecebimento) {
        this.horarioRecebimento = horarioRecebimento;
    }

    public String getTipoAssinatura() {
        return tipoAssinatura;
    }

    public void setTipoAssinatura(String tipoAssinatura) {
        this.tipoAssinatura = tipoAssinatura;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public PadariaDTO getPadaria() {
        return padaria;
    }

    public void setPadaria(PadariaDTO padaria) {
        this.padaria = padaria;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AssinaturaDTO)) {
            return false;
        }

        AssinaturaDTO assinaturaDTO = (AssinaturaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, assinaturaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AssinaturaDTO{" +
            "id=" + getId() +
            ", valor=" + getValor() +
            ", nome='" + getNome() + "'" +
            ", quantidadeDias=" + getQuantidadeDias() +
            ", ativa='" + getAtiva() + "'" +
            ", pagamentoRecorrenciaId=" + getPagamentoRecorrenciaId() +
            ", foto='" + getFoto() + "'" +
            ", quantidade=" + getQuantidade() +
            ", horarioRecebimento='" + getHorarioRecebimento() + "'" +
            ", tipoAssinatura='" + getTipoAssinatura() + "'" +
            ", diaDaSemana='" + getDiaDaSemana() + "'" +
            ", padaria=" + getPadaria() +
            ", user=" + getUser() +
            "}";
    }
}
