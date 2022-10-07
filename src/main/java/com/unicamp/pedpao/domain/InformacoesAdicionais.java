package com.unicamp.pedpao.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A InformacoesAdicionais.
 */
@Entity
@Table(name = "informacoes_adicionais")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InformacoesAdicionais implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Min(value = 1L)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @NotNull
    @Size(min = 10, max = 15)
    @Column(name = "telefone", length = 15, nullable = false, unique = true)
    private String telefone;

    @NotNull
    @Size(min = 11, max = 11)
    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @NotNull
    @Size(min = 8, max = 8)
    @Column(name = "cep", length = 8, nullable = false)
    private String cep;

    @NotNull
    @Size(min = 3, max = 255)
    @Column(name = "rua", length = 255, nullable = false)
    private String rua;

    @NotNull
    @Size(min = 3, max = 255)
    @Column(name = "bairro", length = 255, nullable = false)
    private String bairro;

    @NotNull
    @Size(min = 2, max = 2)
    @Column(name = "estado", length = 2, nullable = false)
    private String estado;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "numero", length = 255, nullable = false)
    private String numero;

    @Column(name = "complemento")
    private String complemento;

    @ManyToOne
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public InformacoesAdicionais id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public InformacoesAdicionais telefone(String telefone) {
        this.setTelefone(telefone);
        return this;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return this.cpf;
    }

    public InformacoesAdicionais cpf(String cpf) {
        this.setCpf(cpf);
        return this;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return this.cep;
    }

    public InformacoesAdicionais cep(String cep) {
        this.setCep(cep);
        return this;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return this.rua;
    }

    public InformacoesAdicionais rua(String rua) {
        this.setRua(rua);
        return this;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return this.bairro;
    }

    public InformacoesAdicionais bairro(String bairro) {
        this.setBairro(bairro);
        return this;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return this.estado;
    }

    public InformacoesAdicionais estado(String estado) {
        this.setEstado(estado);
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumero() {
        return this.numero;
    }

    public InformacoesAdicionais numero(String numero) {
        this.setNumero(numero);
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public InformacoesAdicionais complemento(String complemento) {
        this.setComplemento(complemento);
        return this;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public InformacoesAdicionais user(User user) {
        this.setUser(user);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InformacoesAdicionais)) {
            return false;
        }
        return id != null && id.equals(((InformacoesAdicionais) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InformacoesAdicionais{" +
            "id=" + getId() +
            ", telefone='" + getTelefone() + "'" +
            ", cpf='" + getCpf() + "'" +
            ", cep='" + getCep() + "'" +
            ", rua='" + getRua() + "'" +
            ", bairro='" + getBairro() + "'" +
            ", estado='" + getEstado() + "'" +
            ", numero='" + getNumero() + "'" +
            ", complemento='" + getComplemento() + "'" +
            "}";
    }
}
