import java.util.ArrayList;

public class RegistroDeUsuarios {
    ArrayList<Usuario> usuarios = new ArrayList<>();

    public String exibeListaUsuarios(){
        String aux = "Lista de Funcionários cadastrados\n";
        if(usuarios.size()<=0){
            aux += "Sem funcionários cadastrados!";
            return aux;
        }
        for(int i=0; i<usuarios.size(); i++){
            aux += usuarios.get(i).toString();
        }
        return aux;
    }
}
