package registros;

import java.util.ArrayList;

import models.Pedido;
import models.Usuario;

public class RegistroDePedidos {

	private ArrayList<Pedido> pedidos;

	public RegistroDePedidos(){
		pedidos = new ArrayList<Pedido>();
	}

	public ArrayList<Pedido> listaData(String dataInicial, String dataFinal) {
		return null;
	}

	public ArrayList<Pedido> buscaSolicitante(Usuario funcionario) {
		ArrayList<Pedido> pedSolic = new ArrayList<Pedido>();
		for(Pedido p: pedidos){
			if(p.getFunc() == funcionario){
				pedSolic.add(p);
			}
		}
		return pedSolic;
	}

	public boolean inserePedido(Pedido pedAux){
		if(pedAux == null){
			return false;
		}
		pedidos.add(pedAux);
		return true;
	}

	public ArrayList<Pedido> buscaDescricao(String descricao) {
		ArrayList<Pedido> pedDesc = new ArrayList<Pedido>();
		for(Pedido p: pedidos){
			if(p.getItem(descricao)){
				pedDesc.add(p);
			}
		}
		return pedDesc;
	}

	public ArrayList<Pedido> buscaAberto() {
		ArrayList<Pedido> pedAberto = new ArrayList<Pedido>();
		for(Pedido p: pedidos){
			if(!p.concluido()){
				pedAberto.add(p);
			}
		}
		return pedAberto;
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
