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
    private String capaUrl;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arquivo_id")
    private Arquivo arquivo;

    public Livro() {}

    public Livro(String titulo,
                 LocalDate anoDePublicacao,
                 String isbn,
                 int numeroDePagina,
                 String idioma,
                 String sinopse,
                 Editora editora,
                 Set<Autor> autores,
                 Set<Genero> generos,
                 String capaUrl,
                 Arquivo arquivo) {
        this.titulo = titulo;
        this.anoDePublicacao = anoDePublicacao;
        this.isbn = isbn;
        this.numeroDePagina = numeroDePagina;
        this.idioma = idioma;
        this.sinopse = sinopse;
        this.editora = editora;
        this.autores = autores;
        this.generos = generos;
        this.capaUrl = capaUrl;
        this.arquivo = arquivo;
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

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }


    public String getCapaUrl() {
        return capaUrl;
    }

    public void setCapaUrl(String capaUrl) {
        this.capaUrl = capaUrl;
    }
}
