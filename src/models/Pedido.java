package models;

import java.util.Date;

import departamentos.Departamento;

public class Pedido {

	private Date data;
	private int codigo;
	private StatusPedido status;
	private boolean concluido;
	private Usuario funcionario;
	private ListaDeItens listaDeItens;
	private Departamento departamento;
	private static int contadorDePedidos = 0;

	public Pedido(Usuario funcionario, Departamento departamento) {
		this.codigo = contadorDePedidos++;
		this.funcionario = funcionario;
		this.departamento = departamento;
		this.data = new Date();
		this.concluido = false;
		this.status = status.ABERTO;
	}

	public boolean inseritItem(Item item){
		if(listaDeItens.cadastraItem(item)){
			return true;
		}
		return false;
	}

	public boolean removerItem(Item item){
		if(listaDeItens.removeItem(item)){
			return true;
		}
		return false;
	}

	public boolean getItem(String descricao){
		if(listaDeItens.procuraDescricao(descricao)){
			return true;
		}
		return false;
	}

	public double getValTot() {
		return listaDeItens.getTotalValorItens();
	}

	public String getStatus() {
		return status.name();
	}

	public boolean aprovar(Usuario funcionario){
		if(funcionario.administrador()){
			this.status = status.APROVADO;
			this.concluido = true;
			return true;
		}
		return false;
	}

	public boolean reprovar(Usuario funcionario){
		if(funcionario.administrador()){
			this.status = status.REPROVADO;
			this.concluido = true;
			return true;
		}
		return false;
	}

	public String getDataPed(){
		return data.toString();
	}

	public int getCodigo(){
		return codigo;
	}

	public boolean concluido(){
		return concluido;
	}

	public Usuario getFunc(){
		return funcionario;
	}

	public Departamento getDepart(){
		return departamento;
	}

	@Override
	public String toString() {
		String aux = "Pedido " + getCodigo() + "\n";
		aux+= "Itens pertencentes ao pedido:";
		aux += listaDeItens.toString();
		return aux;
	}
}
