package models;

public class Item {

	private String descricao;
	private double valorUnitario;
	private int quantidade;

	public Item(String descricao, double valorUnitario, int quantidade) {
		this.descricao = descricao;
		this.valorUnitario = valorUnitario;
		this.quantidade = quantidade;
	}

	public double getValorTotal() {
		return (valorUnitario * quantidade);
	}

	@Override
	public String toString() {
		return descricao + ": Quantidade:" + quantidade + ", Valor Unitario:" + valorUnitario + ", Valor Total: " + getValorTotal();
	}

	public int getQuantidade() {
		return quantidade;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public String getDescricao() {
		return descricao;
	}
}
