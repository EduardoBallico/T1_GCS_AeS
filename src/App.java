import java.lang.reflect.Array;

import departamentos.*;
import models.*;
import registros.RegistroDePedidos;
import java.util.ArrayList;

public class App {
	public static void main(String[] args) {
		RegistroDePedidos registro = new RegistroDePedidos();
		System.out.println("Inicializando...");
		ArrayList<Pedido> listAux = preencheDados();
		System.out.println(listAux.size());
		for(int i=0; i<listAux.size(); i++){
			registro.inserePedido(listAux.get(i));
		}
		listAux.clear();		
		System.out.println(registro.buscaAberto());
		
	}

	public static ArrayList<Pedido> preencheDados(){
		ArrayList<Pedido> listaAux = new ArrayList<Pedido>();
		Departamento depTi = new DepTI(150000);
		Departamento depComercial = new DepComercial(90000);
		Departamento depEngenharia = new DepEngenharia(170000);
		Departamento depFinanceiro = new DepFinanceiro(00);
		Departamento depManutencao = new DepManutencao(50000);
		Departamento depRh = new DepRH(35000);

		Usuario funcionario1 = new Usuario(depTi, "Eduardo", true);
		Usuario funcionario2 = new Usuario(depComercial, "Guilherme", false);
		Usuario funcionario3 = new Usuario(depEngenharia, "Vinicius", false);
		Usuario funcionario4 = new Usuario(depFinanceiro, "Arthur", true);
		Usuario funcionario5 = new Usuario(depManutencao, "André", true);
		Usuario funcionario6 = new Usuario(depRh, "Leandro", true);
		Usuario funcionario7 = new Usuario(depTi, "Daniel", true);
		Usuario funcionario8 = new Usuario(depComercial, "Pedro", true);
		Usuario funcionario9 = new Usuario(depEngenharia, "Eduarda", true);
		Usuario funcionario10 = new Usuario(depFinanceiro, "Vitoria", true);
		Usuario funcionario11 = new Usuario(depManutencao, "Gertrudes", true);
		Usuario funcionario12 = new Usuario(depRh, "Carla", true);
		Usuario funcionario13 = new Usuario(depTi, "Claudia", true);
		Usuario funcionario14 = new Usuario(depComercial, "Marisa", true);
		Usuario funcionario15 = new Usuario(depEngenharia, "Eveline", true);

		ListaDeItens list1 = new ListaDeItens();
		list1.incluirItem(new Item("Computador Intel i7, RTX 3080, 32gb de ram", 15000, 1));
		list1.incluirItem(new Item("Monitor 17'", 1750, 2));

		ListaDeItens list2 = new ListaDeItens();
		list2.incluirItem(new Item("Telefone Comercial", 250, 220));
		list2.incluirItem(new Item("Central de Comunicação", 5000, 5));

		ListaDeItens list3 = new ListaDeItens();
		list3.incluirItem(new Item("Caminhao Mercedes-Benz GenH2", 330000, 1));

		ListaDeItens list4 = new ListaDeItens();
		list4.incluirItem(new Item("Computador de Escritório", 2500, 10));
		list4.incluirItem(new Item("Impressora HP Series-T", 1250, 10));
		list4.incluirItem(new Item("Folhas A4 para impressora", 19, 200));

		ListaDeItens list5 = new ListaDeItens();
		list5.incluirItem(new Item("Kit de chaves Allen", 50, 50));
		list5.incluirItem(new Item("Jogo de chaves Philips", 50, 100));
		list5.incluirItem(new Item("Jogo de chaves de Fenda", 50, 100));

		ListaDeItens list6 = new ListaDeItens();
		list6.incluirItem(new Item("Folhas A4 para impressora", 19, 200));

		Pedido ped1 = new Pedido(funcionario1, depTi, list1);
		listaAux.add(ped1);
		Pedido ped2 = new Pedido(funcionario2, depComercial, list2);
		listaAux.add(ped2);
		Pedido ped3 = new Pedido(funcionario3, depEngenharia, list3);
		listaAux.add(ped3);
		Pedido ped4 = new Pedido(funcionario4, depFinanceiro, list4);
		listaAux.add(ped4);
		Pedido ped5 = new Pedido(funcionario4, depManutencao, list5);
		listaAux.add(ped5);
		Pedido ped6 = new Pedido(funcionario4, depRh, list6);
		listaAux.add(ped6);
		
		return listaAux;
	}
}
