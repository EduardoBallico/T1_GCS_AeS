import departamentos.*;
import models.*;
import registros.*;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

	private static RegistroDePedidos rPedidos;
	private static RegistroDeUsuarios rUsuarios = new RegistroDeUsuarios();
	private static RegistroDeDepartamentos rDepartamentos = new RegistroDeDepartamentos();
	
	public static void main(String[] args) {

		System.out.println("Inicializando...");	

		preencheDados();
		executar();
		
	}

	public static void preencheDados(){

		ArrayList<Departamento> deps = new ArrayList<Departamento>();

		deps.add(new DepTI(0, 150000));
		deps.add(new DepComercial(1, 90000));
		deps.add(new DepEngenharia(2, 170000));
		deps.add(new DepFinanceiro(3, 00));
		deps.add(new DepManutencao(4, 50000));
		deps.add(new DepRH(5, 35000));

		// Cria e Registra os funcionarios;
		ArrayList<Usuario> funcs = new ArrayList<Usuario>();

		funcs.add(new Usuario(rDepartamentos.pesquisaDepartamento(0), "Eduardo", true));
		funcs.add(new Usuario(rDepartamentos.pesquisaDepartamento(1), "Guilherme", false));
		funcs.add(new Usuario(rDepartamentos.pesquisaDepartamento(2), "Vinicius", false));
		funcs.add(new Usuario(rDepartamentos.pesquisaDepartamento(3), "Arthur", true));
		funcs.add(new Usuario(rDepartamentos.pesquisaDepartamento(4), "Andre", true));
		funcs.add(new Usuario(rDepartamentos.pesquisaDepartamento(5), "Leandro", true));
		funcs.add(new Usuario(rDepartamentos.pesquisaDepartamento(0), "Daniel", true));
		funcs.add(new Usuario(rDepartamentos.pesquisaDepartamento(1), "Pedro", true));
		funcs.add(new Usuario(rDepartamentos.pesquisaDepartamento(2), "Eduarda", true));
		funcs.add(new Usuario(rDepartamentos.pesquisaDepartamento(3), "Vitoria", true));
		funcs.add(new Usuario(rDepartamentos.pesquisaDepartamento(4), "Gertrudes", true));
		funcs.add(new Usuario(rDepartamentos.pesquisaDepartamento(5), "Carla", true));
		funcs.add(new Usuario(rDepartamentos.pesquisaDepartamento(0), "Claudia", true));
		funcs.add(new Usuario(rDepartamentos.pesquisaDepartamento(1), "Marisa", true));
		funcs.add(new Usuario(rDepartamentos.pesquisaDepartamento(2), "Eveline", true));

		for (Usuario f : funcs){
			rUsuarios.insereUsuario(f);
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
		ArrayList<Pedido> ped = new ArrayList<Pedido>();

		ped.add(new Pedido(funcs.get(0), rDepartamentos.pesquisaDepartamento(0), list1));
		ped.add(new Pedido(funcs.get(1), rDepartamentos.pesquisaDepartamento(1), list2));
		ped.add(new Pedido(funcs.get(2), rDepartamentos.pesquisaDepartamento(2), list3));
		ped.add(new Pedido(funcs.get(3), rDepartamentos.pesquisaDepartamento(3), list4));
		ped.add(new Pedido(funcs.get(4), rDepartamentos.pesquisaDepartamento(4), list5));
		ped.add(new Pedido(funcs.get(5), rDepartamentos.pesquisaDepartamento(5), list6));

		rPedidos = new RegistroDePedidos(ped);
	}

	private static void executar(){
		Scanner in = new Scanner(System.in);

		while(true){
			exibeMenu();

			int input = Integer.parseInt(in.nextLine());

			switch (input) {
				case 1 -> {
					System.out.println(rPedidos.buscaAberto());
				}
				case 2 -> {
					System.out.println("Digite o nome do Funcionario:");
					String nome = in.nextLine();
					Usuario u = rUsuarios.pesquisaUsuario(nome);
					System.out.println(rPedidos.estatisticasGerais(u));
				}
				case 0 -> {
					System.out.println("Programa Finalizado!");
					return;
				}
				
				default -> {
					System.out.println("Opção invalida!");
				}
			}
		}
	}

	private static void exibeMenu(){
		System.out.println("1. Exibe pedidos em Aberto");
		System.out.println("2. Exibe estatisticas gerais do funcionario");
		System.out.println("0. Sair");
	}
}
