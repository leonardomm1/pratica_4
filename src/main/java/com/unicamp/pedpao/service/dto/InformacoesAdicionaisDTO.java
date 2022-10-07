package com.unicamp.pedpao.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.unicamp.pedpao.domain.InformacoesAdicionais} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InformacoesAdicionaisDTO implements Serializable {

    @NotNull
    @Min(value = 1L)
    private Long id;

    @NotNull
    @Size(min = 10, max = 15)
    private String telefone;

    @NotNull
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    @Size(min = 8, max = 8)
    private String cep;

    @NotNull
    @Size(min = 3, max = 255)
    private String rua;

    @NotNull
    @Size(min = 3, max = 255)
    private String bairro;

    @NotNull
    @Size(min = 2, max = 2)
    private String estado;

    @NotNull
    @Size(min = 1, max = 255)
    private String numero;

    private String complemento;

    private UserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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
        if (!(o instanceof InformacoesAdicionaisDTO)) {
            return false;
        }

        InformacoesAdicionaisDTO informacoesAdicionaisDTO = (InformacoesAdicionaisDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, informacoesAdicionaisDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InformacoesAdicionaisDTO{" +
            "id=" + getId() +
            ", telefone='" + getTelefone() + "'" +
            ", cpf='" + getCpf() + "'" +
            ", cep='" + getCep() + "'" +
            ", rua='" + getRua() + "'" +
            ", bairro='" + getBairro() + "'" +
            ", estado='" + getEstado() + "'" +
            ", numero='" + getNumero() + "'" +
            ", complemento='" + getComplemento() + "'" +
            ", user=" + getUser() +
            "}";
    }
}
