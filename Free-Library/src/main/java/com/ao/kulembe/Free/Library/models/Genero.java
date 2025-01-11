package com.ao.kulembe.Free.Library.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String nome;
    @Column(unique = true, nullable = false, length = 300)
    private String descricao;

    @ManyToMany(mappedBy = "generos")
    private Set<Livro> livro;

    public Genero() {}

    public Genero(Long id, String nome,
                  String descricao, Set<Livro> livro) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.livro = livro;
    }

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Livro> getLivro() {
        return livro;
    }

    public void setLivro(Set<Livro> livro) {
        this.livro = livro;
    }
}
