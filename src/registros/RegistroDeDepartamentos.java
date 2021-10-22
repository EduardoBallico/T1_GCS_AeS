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

    public Departamento pesquisaDepartamento(int identificador){   
        for(Departamento dep : departamentos){
            if(dep.getIdentificador() == identificador){
                return dep;
            }
        }
        return null;
    }        

    public String exibeListaDepartamento() {
        String aux = "Lista de Funcionarios cadastrados\n";
        if (departamentos.size() <= 0) {
            aux += "Sem funcionarios cadastrados!";
            return aux;
        }
        for (int i = 0; i < departamentos.size(); i++) {
            aux += departamentos.get(i).toString();
        }
        return aux;
    }
}
