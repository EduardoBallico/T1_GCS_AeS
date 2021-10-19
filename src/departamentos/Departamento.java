package departamentos;

public abstract class Departamento {
    protected double valorMaximo;
    protected int identificador;
    protected String nome;

    public Departamento(int identificador, double valorMaximo, String nome) {
        this.identificador = identificador;
        this.valorMaximo = valorMaximo;
        this.nome = nome;
    }

    public int getIdentificador() {
        return identificador;
    }

    public double getValorMaximo() {
        return valorMaximo;
    }

    public String getNome(){
        return nome;
    }
}