import models.Item;

public class listaDeItens {

	private Item[] Itens;

	public listaDeItens() {
		// Incompleto
	}

	public double getTotalValorItens() {

		int total = 0;

		for (Item i : Itens) {
			total += i.getValorTotal();
		}

		return total;
	}

}
