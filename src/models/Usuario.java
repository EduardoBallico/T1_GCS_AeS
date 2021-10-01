package models;

import departamentos.Departamento;

public class Usuario {
    private Departamento departamento;
    private int identificador;
    private String nome;
    private boolean administrador;

    public Usuario(Departamento departamento, int identificador, String nome, boolean ehadministrador) {
        this.departamento = departamento;
        this.identificador = identificador;
        this.nome = nome;
        this.administrador = ehadministrador;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public boolean administrador() {
        return administrador;
    }

    @Override
    public String toString() {
        return "Usuario: Nome: " + nome + " | Identificador: " + identificador +  " | departamento: " + departamento + " | administrador: " + administrador ;
    }


    
}
