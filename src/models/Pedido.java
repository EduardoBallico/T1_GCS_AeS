package models;

import java.util.Date;

import departamentos.Departamento;

public class Pedido {
	private int codigo;
	private Usuario funcionario;
	private ListaDeItens listaDeItens;
	private Departamento departamento;
	private Date data;
	private StatusPedido status;
	private boolean concluido;
	private static int contadorDePedidos = 1; // Incrementa a cada novo pedido criado

	public Pedido(Usuario funcionario, Departamento departamento, ListaDeItens listaItens) {
		this.codigo = contadorDePedidos++;
		this.funcionario = funcionario;
		this.departamento = departamento;
		this.data = new Date(); // Registra a data de criação
		this.concluido = false;
		this.status = StatusPedido.ABERTO;
		this.listaDeItens = listaItens;
	}

	public boolean inserirItem(Item item) {
		if(item.getValorTotal() > departamento.getValorMaximo()){
			return false;
		}
		if(item.getValorTotal() + getValTot() > departamento.getValorMaximo()){ //Regra de negócio nova
			return false;
		}
		if (listaDeItens.incluirItem(item)) {
			return true;
		}
		return false;
	}

	public boolean removerItem(Item item) {
		if (listaDeItens.removeItem(item)) {
			return true;
		}
		return false;
	}

	public boolean getItem(String descricao) {
		if (listaDeItens.procuraPorDescricao(descricao)) {
			return true;
		}
		return false;
	}

	public double getValTot() {
		return listaDeItens.getTotalValorItens();
	}

	public StatusPedido getStatus() {
		return status;
	}

	public boolean aprovar(Usuario funcionario) { // Informar o usuario atual que esta tentando aprovar o pedido
		if (funcionario.administrador()) {
			this.status = StatusPedido.APROVADO;
			this.concluido = true;
			return true;
		}
		return false;
	}

	public boolean reprovar(Usuario funcionario) { // Informar o usuario atual que esta tentando reprovar o pedido
		if (funcionario.administrador()) {
			this.status = StatusPedido.REPROVADO;
			this.concluido = true;
			return true;
		}
		return false;
	}

	public Date getDataPed() {
		return data;
	}

	public int getCodigo() {
		return codigo;
	}

	public boolean concluido() {
		return concluido;
	}

	public Usuario getFunc() {
		return funcionario;
	}

	public Departamento getDepart() {
		return departamento;
	}

	@Override
	public String toString() {
		String aux = "Pedido " + getCodigo() + "\n";
		aux += "Itens pertencentes ao pedido:\n";
		aux += listaDeItens.toString() + "\n\n";
		return aux;
	}
}
