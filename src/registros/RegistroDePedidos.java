package registros;

import java.util.ArrayList;

import models.Pedido;
import models.Usuario;

public class RegistroDePedidos {

	private ArrayList<Pedido> pedidos;

	public Pedido[] listaData(String dataInicial, String dataFinal) {
		return null;
	}

	public Pedido[] buscaSolicitante(Usuario funcionario) {
		return null;
	}

	public Pedido[] buscaDescricao(String descricao) {
		return null;
	}

	public Pedido[] buscaAberto() {
		return null;
	}

	public boolean aprovar(int codigo, Usuario usuario) {
		for(int i=0; i<pedidos.size(); i++){
			if(pedidos.get(i).getCodigo() == codigo){
				if(pedidos.get(i).aprovar(usuario)){
					return true;
				}
			}
		}
		return false;
	}

	public boolean reprovar(int codigo, Usuario usuario) {
		for(int i=0; i<pedidos.size(); i++){
			if(pedidos.get(i).getCodigo() == codigo){
				if(pedidos.get(i).reprovar(usuario)){
					return true;
				}
			}
		}
		return false;
	}

	public String estaticasGerais(Usuario usuario) {
		return null;
	}

}
