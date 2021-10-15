import departamentos.*;
import models.*;
import registros.RegistroDePedidos;
import registros.RegistroDeUsuarios;

import java.util.ArrayList;

public class App {

	private static RegistroDePedidos registroDePedidos = new RegistroDePedidos();
	private static RegistroDeUsuarios registroDeUsuarios = new RegistroDeUsuarios();
	public static void main(String[] args) {

		System.out.println("Inicializando...");	

		preencheDados();
		System.out.println(registroDePedidos.buscaAberto());
		
	}

	public static void preencheDados(){

		Departamento depTi = new DepTI(150000);
		Departamento depComercial = new DepComercial(90000);
		Departamento depEngenharia = new DepEngenharia(170000);
		Departamento depFinanceiro = new DepFinanceiro(00);
		Departamento depManutencao = new DepManutencao(50000);
		Departamento depRh = new DepRH(35000);

		// Cria e Registra os funcionarios;
		Usuario[] funcs = new Usuario[14];

		funcs[0] = new Usuario(depTi, "Eduardo", true);
		funcs[1] = new Usuario(depComercial, "Guilherme", false);
		funcs[2] = new Usuario(depEngenharia, "Vinicius", false);
		funcs[3] = new Usuario(depFinanceiro, "Arthur", true);
		funcs[4] = new Usuario(depManutencao, "André", true);
		funcs[5] = new Usuario(depRh, "Leandro", true);
		funcs[6] = new Usuario(depTi, "Daniel", true);
		funcs[7] = new Usuario(depComercial, "Pedro", true);
		funcs[8] = new Usuario(depEngenharia, "Eduarda", true);
		funcs[9] = new Usuario(depFinanceiro, "Vitoria", true);
		funcs[10] = new Usuario(depManutencao, "Gertrudes", true);
		funcs[11] = new Usuario(depRh, "Carla", true);
		funcs[12] = new Usuario(depTi, "Claudia", true);
		funcs[13] = new Usuario(depComercial, "Marisa", true);
		funcs[14] = new Usuario(depEngenharia, "Eveline", true);

		for (Usuario f : funcs){
			registroDeUsuarios.insereUsuario(f);
		}

		// Cria listas de itens
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

		// Cria e Registra
		Pedido[] ped = new Pedido[6];

		ped[0] = new Pedido(funcs[1], depTi, list1);
		ped[1] = new Pedido(funcs[2], depComercial, list2);
		ped[2] = new Pedido(funcs[3], depEngenharia, list3);
		ped[3] = new Pedido(funcs[4], depFinanceiro, list4);
		ped[4] = new Pedido(funcs[5], depManutencao, list5);
		ped[5] = new Pedido(funcs[6], depRh, list6);

		for (Pedido p : ped){
			registroDePedidos.inserePedido(p);
		}
		
	}
}
