import departamentos.*;
import models.*;
import registros.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class App {

	private static RegistroDePedidos rPedidos;
	private static RegistroDeUsuarios rUsuarios = new RegistroDeUsuarios();
	private static RegistroDeDepartamentos rDepartamentos = new RegistroDeDepartamentos();

	private static Usuario usuarioAtivo;

	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("Inicializando...\n\n\n");

		preencheDados();
		executar();
	}
	
	public static void preencheDados() {

		Departamento[] deps = { 
			new DepTI(0, 150000, "Departamento de TI"), 
			new DepComercial(1, 90000, "Departamento Comercial"), 
			new DepEngenharia(2, 170000, "Departamento de Engenharia"),
			new DepFinanceiro(3, 120000, "Departamento Financeiro"), 
			new DepManutencao(4, 50000, "Departamento de Manutencao"), 
			new DepRH(5, 35000, "Departamento de Recursos Humanos"),
		};
		for (Departamento d : deps) {
			rDepartamentos.insereDepartamento(d);
		}

		// Cria e Registra os funcionarios;
		Usuario[] funcs = {
			new Usuario(rDepartamentos.pesquisaDepartamento(0), "Eduardo", true),
			new Usuario(rDepartamentos.pesquisaDepartamento(1), "Guilherme", false),
			new Usuario(rDepartamentos.pesquisaDepartamento(2), "Vinicius", true),
			new Usuario(rDepartamentos.pesquisaDepartamento(3), "Arthur", true),
			new Usuario(rDepartamentos.pesquisaDepartamento(4), "Andre", true),
			new Usuario(rDepartamentos.pesquisaDepartamento(5), "Leandro", true),
			new Usuario(rDepartamentos.pesquisaDepartamento(0), "Daniel", true),
			new Usuario(rDepartamentos.pesquisaDepartamento(1), "Pedro", true),
			new Usuario(rDepartamentos.pesquisaDepartamento(2), "Eduarda", true),
			new Usuario(rDepartamentos.pesquisaDepartamento(3), "Vitoria", true),
			new Usuario(rDepartamentos.pesquisaDepartamento(4), "Gertrudes", true),
			new Usuario(rDepartamentos.pesquisaDepartamento(5), "Carla", true),
			new Usuario(rDepartamentos.pesquisaDepartamento(0), "Claudia", true),
			new Usuario(rDepartamentos.pesquisaDepartamento(1), "Marisa", true),
			new Usuario(rDepartamentos.pesquisaDepartamento(2), "Eveline", true)
		};
		for (Usuario f : funcs) {
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
		Pedido[] ped = {
			new Pedido(rUsuarios.pesquisaUsuario(1), rDepartamentos.pesquisaDepartamento(1), list2),
			new Pedido(rUsuarios.pesquisaUsuario(2), rDepartamentos.pesquisaDepartamento(2), list3),
			new Pedido(rUsuarios.pesquisaUsuario(3), rDepartamentos.pesquisaDepartamento(3), list4),
			new Pedido(rUsuarios.pesquisaUsuario(4), rDepartamentos.pesquisaDepartamento(4), list5),
			new Pedido(rUsuarios.pesquisaUsuario(5), rDepartamentos.pesquisaDepartamento(5), list6),
			new Pedido(rUsuarios.pesquisaUsuario(6), rDepartamentos.pesquisaDepartamento(0), list1)
		};
		rPedidos = new RegistroDePedidos(new ArrayList<Pedido>(Arrays.asList(ped)));
	}

	private static void executar() {

		logar();
		while (true) {
			exibeMenu();
			System.err.print("Sua opcao: ");
			int input = Integer.parseInt(in.nextLine());

			switch (input) {
				case 1 -> {
					logar();
					break;
				}
				case 2 -> {
					System.out.println(rPedidos.buscaAberto());
					break;
				}
				case 3 -> {
					if (usuarioAtivo == null) {
						System.out.println("Nenhum usuario logado. Por favor selecione o seu usuario.");
					} else {
						System.out.println("");
						System.out.println(rPedidos.estatisticasGerais(usuarioAtivo));
						break;
					}
				}
				case 4 -> {

					ListaDeItens l = new ListaDeItens();
					int hasNext = 0;

					do{	
						System.out.print("Digite o nome do Item:");
						String pNome = in.nextLine();
						System.out.print("Digite o valor unitario:");
						double pValor = Double.parseDouble(in.nextLine());
						System.out.print("Digite a quantidade:");
						int pQnt = Integer.parseInt(in.nextLine());
						
						l.incluirItem(new Item(pNome, pValor, pQnt));
						while(hasNext != 1 && hasNext != 2){
							System.out.println("Deseja Adicionar mais um item ao Pedido?:");
							System.out.println("1. Sim;");
							System.out.println("2. Nao.");
							System.out.print("Sua opcao: ");
							hasNext = Integer.parseInt(in.nextLine());
							if(hasNext != 1 && hasNext != 2){
								System.out.println("Opcao Invalida! \n");
							}
						}
					} while(hasNext != 2);

					Pedido p = new Pedido(usuarioAtivo, usuarioAtivo.getDepartamento(), l);
					boolean inseriu = rPedidos.inserePedido(p);
					if(inseriu) System.out.println("Pedido cadastrado!");
					else System.out.println("Erro ao cadastrar novo pedido.");	
					break;
				}	
				case 0 -> {
					System.out.println("Programa Finalizado!");
					return;
				}
				default -> {
					System.out.println("Opcao invalida!");
					break;
				}
			}
		}
	}

	private static void exibeMenu() {
		if(usuarioAtivo != null) System.out.println("Voce esta logado como: "+usuarioAtivo.getNome());
		else System.out.println("Voce nao esta logado, por favor selecione seu usuario.");
		System.out.println(" === MENU === ");
		System.out.println("1. Selecione seu usuario");
		System.out.println("2. Exibe pedidos em Aberto");
		System.out.println("3. Exibe estatisticas gerais do funcionario");
		System.out.println("4. Criar novo pedido");
		System.out.println("0. Sair");
	}

	public static void logar(){
		System.out.println(rUsuarios.exibeListaUsuarios());
		System.out.print("Digite o codigo do usuario desejado:");
		int cod = Integer.parseInt(in.nextLine());
		Usuario usuarioBuscado = rUsuarios.pesquisaUsuario(cod);
		if (usuarioBuscado != null) {
			usuarioAtivo = usuarioBuscado;
			System.out.println("Perfil selecionado com sucesso.");
		}
		else System.out.println("Codigo de usuario inexistente, tente novamente.");
	}
}