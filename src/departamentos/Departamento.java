package departamentos;

public abstract class Departamento {
    protected double valorMaximo;
    protected int identificador;

    public Departamento(int identificador, double valorMaximo) {
        this.identificador = identificador;
        this.valorMaximo = valorMaximo;
    }

    public int getIdentificador() {
        return identificador;
    }

    public double getValorMaximo() {
        return valorMaximo;
    }
}
