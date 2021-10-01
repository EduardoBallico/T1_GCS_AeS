package registros;

import models.Pedido;
import models.Usuario;

public class RegistroDePedidos {

	private Pedido[] pedidos;

	private Pedido[] pedido;

	public boolean verificaCargo(Usuario Usuario) {
		return false;
	}

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

	public boolean aprova(int codigo, Usuario usuario) {
		return false;
	}

	public boolean excluir(int codigo, Usuario usuario) {
		return false;
	}

	public String estaticasGerais(Usuario usuario) {
		return null;
	}

}
