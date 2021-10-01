package models;

import java.util.Date;

import departamentos.Departamento;

public class Pedido {

	private static int contadorDePedidos = 0;

	private int codigo;

	private Usuario funcionario;

	private Departamento departamento;

	private Date data;

	private String status;

	private boolean concluido;

	private static int numeroPedido;

	private listaDeItens listaDeItens;

	/**
	 * Construtores
	 */

	public Pedido() {
		this.codigo = contadorDePedidos++;

	}

	public Pedido(Usuario funcionario, Departamento departamento) {
		this.codigo = contadorDePedidos++;
		this.funcionario = funcionario;
		this.departamento = departamento;
		this.data = data;

	}

	/*
	 * Metodos
	 */

	public double getValorTotal() {
		// return listaDeItens.getValorTotalItens();
		return 0;
	}

	public String getStatus() {
		return null;
	}

}
