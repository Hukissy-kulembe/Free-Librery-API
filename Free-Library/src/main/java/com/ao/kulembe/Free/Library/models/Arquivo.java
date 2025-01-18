package com.ao.kulembe.Free.Library.models;

import jakarta.persistence.*;

@Entity
public class Arquivo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private byte[] capa;
    private byte[] arquivo;

    @OneToOne(mappedBy = "arquivo")
    private Livro livro;

    public Arquivo() {
    }

    public Arquivo( byte[] capa, byte[] arquivo) {
        this.capa = capa;
        this.arquivo = arquivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public byte[] getCapa() {
        return capa;
    }

    public void setCapa(byte[] capa) {
        this.capa = capa;
    }
}
