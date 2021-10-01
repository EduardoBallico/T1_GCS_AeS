package departamentos;

public abstract class Departamento {
    protected double valorMaximo;

    public Departamento(double valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public double getValorMaximo() {
        return valorMaximo;
    }
}
