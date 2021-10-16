package registros;

import java.util.ArrayList;

import departamentos.Departamento;

public class RegistroDeDepartamentos {
    ArrayList<Departamento> departamentos;

    public RegistroDeDepartamentos(){
        departamentos = new ArrayList<>();
    }

    public boolean insereDepartamento(Departamento dp){
        if(dp == null){
            return false;
        }
        departamentos.add(dp);
        return true;
    }

    // Implementar
    public Departamento pesquisaDepartamento(int identificador){
        return null;
    }

    public String exibeListaDepartamento() {
        String aux = "Lista de Funcionários cadastrados\n";
        if (departamentos.size() <= 0) {
            aux += "Sem funcionários cadastrados!";
            return aux;
        }
        for (int i = 0; i < departamentos.size(); i++) {
            aux += departamentos.get(i).toString();
        }
        return aux;
    }
}
