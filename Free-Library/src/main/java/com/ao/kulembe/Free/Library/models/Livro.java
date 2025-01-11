package com.ao.kulembe.Free.Library.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "publicadoEm", nullable = true)
    private LocalDate anoDePublicacao;
    @Column(unique = true, nullable = false)
    private String isbn;
    @Column(unique = false)
    private int numeroDePagina;
    @Column(nullable = true)
    private String idioma;


    private String sinopse;
    @Column(unique = true, nullable = false)
    private byte[] capa;
    @Column(unique = true, nullable = false)
    private byte[] pdf;

    @ManyToOne
    @JoinColumn(name = "editora_id")
    private Editora editora;

    @ManyToMany
    @JoinTable(
            name = "Autor_Livro",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id ")
    )
    private Set<Autor> autores;

    @ManyToMany
    @JoinTable(
            name = "Favoritar",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> usuarios;

    @ManyToMany
    @JoinTable(
            name = "livro_genero",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "genero_id")
    )
    private Set<Genero> generos;

    public Livro() {}

    public Livro(Long id,
                 String titulo,
                 LocalDate anoDePublicacao,
                 String isbn,
                 int numeroDePagina,
                 String idioma,
                 String sinopse,
                 byte[] capa, byte[] pdf,
                 Editora editora,
                 Set<Autor> autores,
                 Set<Usuario> usuarios,
                 Set<Genero> generos) {
        this.id = id;
        this.titulo = titulo;
        this.anoDePublicacao = anoDePublicacao;
        this.isbn = isbn;
        this.numeroDePagina = numeroDePagina;
        this.idioma = idioma;
        this.sinopse = sinopse;
        this.capa = capa;
        this.pdf = pdf;
        this.editora = editora;
        this.autores = autores;
        this.usuarios = usuarios;
        this.generos = generos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public void setAnoDePublicacao(LocalDate anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNumeroDePagina() {
        return numeroDePagina;
    }

    public void setNumeroDePagina(int numeroDePagina) {
        this.numeroDePagina = numeroDePagina;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public byte[] getCapa() {
        return capa;
    }

    public void setCapa(byte[] capa) {
        this.capa = capa;
    }

    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Set<Autor> getAutores() {
        return autores;
    }

    public void setAutores(Set<Autor> autores) {
        this.autores = autores;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(Set<Genero> generos) {
        this.generos = generos;
    }
}
