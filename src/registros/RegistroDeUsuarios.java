package registros;

import java.util.ArrayList;

import models.Usuario;

public class RegistroDeUsuarios {
    ArrayList<Usuario> usuarios;

    public RegistroDeUsuarios(){
        usuarios = new ArrayList<>();
    }

    public boolean insereUsuario(Usuario us){
        if(us == null){
            return false;
        }
        usuarios.add(us);
        return true;
    }

    public boolean removeUsuario(int identificador){
        for(int i=0; i<usuarios.size(); i++){
            if(usuarios.get(i).getIdentificador() == identificador){
                usuarios.remove(i);
                return true;
            }
        }
        return false;
    }

    public Usuario pesquisaUsuario(int id){
        for(Usuario u : usuarios){
            if(u.getIdentificador() == id){
                return u;
            }
        }
        return null;
    }

    public Usuario pesquisaUsuario(String nome){
        for(Usuario u : usuarios){
            if(u.getNome() == nome){
                return u;
            }
        }
        return null;
    }

    public String exibeListaUsuarios() {
        String aux = "Lista de Funcionários cadastrados\n";
        if (usuarios.size() <= 0) {
            aux += "Sem funcionários cadastrados!";
            return aux;
        }
        for (int i = 0; i < usuarios.size(); i++) {
            aux += usuarios.get(i).toString();
        }
        return aux;
    }
}
