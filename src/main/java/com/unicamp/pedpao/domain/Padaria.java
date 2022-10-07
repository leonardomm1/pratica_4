package com.unicamp.pedpao.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Padaria.
 */
@Entity
@Table(name = "padaria")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Padaria implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Min(value = 1L)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @NotNull
    @Size(min = 3, max = 255)
    @Column(name = "nome", length = 255, nullable = false, unique = true)
    private String nome;

    @NotNull
    @Size(min = 3, max = 255)
    @Column(name = "fantasia", length = 255, nullable = false)
    private String fantasia;

    @NotNull
    @Size(min = 10, max = 15)
    @Column(name = "telefone", length = 15, nullable = false, unique = true)
    private String telefone;

    @NotNull
    @Size(min = 14, max = 14)
    @Column(name = "cnpj", length = 14, nullable = false, unique = true)
    private String cnpj;

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

    @Column(name = "contato")
    private String contato;

    @ManyToOne
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Padaria id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public Padaria nome(String nome) {
        this.setNome(nome);
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFantasia() {
        return this.fantasia;
    }

    public Padaria fantasia(String fantasia) {
        this.setFantasia(fantasia);
        return this;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public Padaria telefone(String telefone) {
        this.setTelefone(telefone);
        return this;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public Padaria cnpj(String cnpj) {
        this.setCnpj(cnpj);
        return this;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCep() {
        return this.cep;
    }

    public Padaria cep(String cep) {
        this.setCep(cep);
        return this;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return this.rua;
    }

    public Padaria rua(String rua) {
        this.setRua(rua);
        return this;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return this.bairro;
    }

    public Padaria bairro(String bairro) {
        this.setBairro(bairro);
        return this;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return this.estado;
    }

    public Padaria estado(String estado) {
        this.setEstado(estado);
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumero() {
        return this.numero;
    }

    public Padaria numero(String numero) {
        this.setNumero(numero);
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public Padaria complemento(String complemento) {
        this.setComplemento(complemento);
        return this;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getContato() {
        return this.contato;
    }

    public Padaria contato(String contato) {
        this.setContato(contato);
        return this;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Padaria user(User user) {
        this.setUser(user);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Padaria)) {
            return false;
        }
        return id != null && id.equals(((Padaria) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Padaria{" +
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
            "}";
    }
}
