package models;

import java.util.ArrayList;

public class ListaDeItens {

	private ArrayList<Item> itens;

	public ListaDeItens() {
		itens = new ArrayList<>();
	}

	public boolean cadastraItem(Item item){
		if(item.getQuantidade()<=0 || item.getValorTotal()<=0){
			return false;
		}
		itens.add(item);
		return true;
	}

	public boolean removeItem(Item item){
		if(itens.remove(item)){
			return true;
		}
		return false;
	}

	public double getTotalValorItens() {

		int total = 0;

		for (Item i : itens) {
			total += i.getValorTotal();
		}

		return total;
	}

	@Override
	public String toString(){
		String aux="";
		for(int i=0; i<itens.size(); i++){
			aux += itens.get(i).toString();
		}
		return aux;
	}
}
