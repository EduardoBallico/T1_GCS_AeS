package models;

import departamentos.Departamento;

public class Usuario {
    private Departamento departamento;
    private static int geraIdentif=1;
    private int identificador;
    private String nome;
    private boolean administrador;
    private String tipo;

    public Usuario(Departamento departamento, String nome, boolean ehadministrador) {
        this.departamento = departamento;
        this.identificador = geraIdentif;
        geraIdentif++;
        this.nome = nome.toUpperCase();
        this.administrador = ehadministrador;
        if(administrador){
            this.tipo = "Administrador";
        } else {
            this.tipo = "Funcionario";
        }
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
        return "Usuario: Nome: " + nome + " | Identificador: " + identificador +  " | departamento: " + departamento.getNome() + " | tipo: " + tipo + "\n";
    }


    
}
