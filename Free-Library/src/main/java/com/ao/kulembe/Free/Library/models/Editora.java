package com.ao.kulembe.Free.Library.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nif", nullable = false, length = 25)
    private String nif;

    @Column(name = "endereco", nullable = true, length = 60)
    private String endereco;

    @OneToMany(mappedBy = "editora", fetch = FetchType.LAZY)
    private Set<Livro> livro;

    public Editora() {
    }

    public Editora(Long id,
                   String nif,
                   String endereco,
                   Set<Livro> livro) {
        this.id = id;
        this.nif = nif;
        this.endereco = endereco;
        this.livro = livro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Set<Livro> getLivro() {
        return livro;
    }

    public void setLivro(Set<Livro> livro) {
        this.livro = livro;
    }
}
