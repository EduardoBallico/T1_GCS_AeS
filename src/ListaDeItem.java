public class ListaDeItem {

	private Item[] Itens;

	public ListaDeItem(){
		// Incompleto
	}

	public double getTotalValorItens() {
		
		total = 0;
		
		for(Item i : Itens){
			total += i.getValorTotal;
		}
		
		return total;
	}

}
