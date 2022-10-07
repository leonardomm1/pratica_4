package com.unicamp.pedpao.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.unicamp.pedpao.domain.Padaria} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PadariaDTO implements Serializable {

    @NotNull
    @Min(value = 1L)
    private Long id;

    @NotNull
    @Size(min = 3, max = 255)
    private String nome;

    @NotNull
    @Size(min = 3, max = 255)
    private String fantasia;

    @NotNull
    @Size(min = 10, max = 15)
    private String telefone;

    @NotNull
    @Size(min = 14, max = 14)
    private String cnpj;

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

    private String contato;

    private UserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
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
        if (!(o instanceof PadariaDTO)) {
            return false;
        }

        PadariaDTO padariaDTO = (PadariaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, padariaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PadariaDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", fantasia='" + getFantasia() + "'" +
            ", telefone='" + getTelefone() + "'" +
            ", cnpj='" + getCnpj() + "'" +
            ", cep='" + getCep() + "'" +
            ", rua='" + getRua() + "'" +
            ", bairro='" + getBairro() + "'" +
            ", estado='" + getEstado() + "'" +
            ", numero='" + getNumero() + "'" +
            ", complemento='" + getComplemento() + "'" +
            ", contato='" + getContato() + "'" +
            ", user=" + getUser() +
            "}";
    }
}
