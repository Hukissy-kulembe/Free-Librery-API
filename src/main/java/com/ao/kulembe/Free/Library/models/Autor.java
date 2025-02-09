package com.ao.kulembe.Free.Library.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String nacionalidade;

    @Column
    private LocalDate dataDeNascimento;
    @Column(length = 500)
    private String biografia;

    @ManyToMany(mappedBy = "autores", fetch = FetchType.LAZY)
    private Set<Livro> livro;

    public Autor() {
    }

    public Autor(
                 String nome,
                 String nacionalidade,
                 LocalDate dataDeNascimento,
                 String biografia) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.dataDeNascimento = dataDeNascimento;
        this.biografia = biografia;
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

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public Set<Livro> getLivro() {
        return livro;
    }

    public void setLivro(Set<Livro> livro) {
        this.livro = livro;
    }
}
