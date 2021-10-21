import departamentos.*;
import models.*;
import registros.*;

import java.util.*;

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
			new DepManutencao(4, 80000, "Departamento de Manutencao"), 
			new DepRH(5, 75000, "Departamento de Recursos Humanos"),
		};
		for (Departamento d : deps) {
			rDepartamentos.insereDepartamento(d);
		}

		// Cria e Registra os funcionarios;
		Usuario[] funcs = {
			new Usuario(rDepartamentos.pesquisaDepartamento(0), "Eduardo", true),
			new Usuario(rDepartamentos.pesquisaDepartamento(1), "Guilherme", false),
			new Usuario(rDepartamentos.pesquisaDepartamento(2), "Vinicius", false),
			new Usuario(rDepartamentos.pesquisaDepartamento(3), "Arthur", true),
			new Usuario(rDepartamentos.pesquisaDepartamento(4), "Andre", true),
			new Usuario(rDepartamentos.pesquisaDepartamento(5), "Leandro", true),
			new Usuario(rDepartamentos.pesquisaDepartamento(0), "Daniel", false),
			new Usuario(rDepartamentos.pesquisaDepartamento(1), "Pedro", true),
			new Usuario(rDepartamentos.pesquisaDepartamento(2), "Eduarda", true),
			new Usuario(rDepartamentos.pesquisaDepartamento(3), "Vitoria", false),
			new Usuario(rDepartamentos.pesquisaDepartamento(4), "Gertrudes", true),
			new Usuario(rDepartamentos.pesquisaDepartamento(5), "Carla", false),
			new Usuario(rDepartamentos.pesquisaDepartamento(0), "Claudia", true),
			new Usuario(rDepartamentos.pesquisaDepartamento(1), "Marisa", true),
			new Usuario(rDepartamentos.pesquisaDepartamento(2), "Eveline", false)
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
		list2.incluirItem(new Item("Central de Comunicacao", 5000, 5));

		ListaDeItens list3 = new ListaDeItens();
		list3.incluirItem(new Item("Caminhao Mercedes-Benz GenH2", 52000, 1));

		ListaDeItens list4 = new ListaDeItens();
		list4.incluirItem(new Item("Computador de Escritorio", 2500, 10));
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

	private static void executar(){

		logar();
		while (true) {
			wait(700);
			exibeMenu();
			System.err.print("Sua opcao: ");
			int input = Integer.parseInt(in.nextLine());

			switch (input) {
				case 1 : {
					logar();
					break;
				}
				case 2 : {
					System.out.println(rPedidos.buscaAberto());
					break;
				}
				case 3 : {
					if (usuarioAtivo == null) {
						System.out.println("Nenhum usuario logado. Por favor selecione o seu usuario.");
					} else {
						System.out.println("");
						System.out.println(rPedidos.estatisticasGerais(usuarioAtivo));
						break;
					}
				}
				case 4 : {

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
				case 5 : {
					if(usuarioAtivo.administrador() == false){
						System.out.println("Usuario logado nao eh adiministrador!");
						break;
					} else{
						System.out.println("Informe a data INICIAL no formato dd/MM/yyyy");
						String dInicial = in.nextLine();
						System.out.println("Informe a data FINAL  no formato dd/MM/yyyy");
						String dFinal = in.nextLine();
						var pedidosNoPeriodo = rPedidos.listaPedidosNoPeriodo(dInicial, dFinal);
						if( pedidosNoPeriodo == null)
							System.out.println("Data informadas sao invalidas!");
						else if(pedidosNoPeriodo.size() <= 0) 
							System.out.println("Nenhum pedido feito no periodo informado.");
						else System.out.println(pedidosNoPeriodo);
					}
					break;
				}
				case 6 : {
					if(usuarioAtivo.administrador() == false){
						System.out.println("Usuario logado nao eh adiministrador!");
						break;
					} else{
						System.out.println("Informe a descricao do Item desejado!");
						String desc = in.nextLine();
						System.out.println("\n" + rPedidos.buscaDescricao(desc) + "\n");
						break;
					}
				}
				case 7 : {
					if(usuarioAtivo.administrador() == false){
						System.out.println("Usuario logado nao eh adiministrador!");
						break;
					} else{
						System.out.print("Informe o codigo do usuario para buscar seus pedidos feitos: ");
						int codUs = Integer.parseInt(in.nextLine());
						System.out.println(rPedidos.buscaSolicitante(rUsuarios.pesquisaUsuario(codUs)));
					}
					break;
				}
				case 8 : {
					if(usuarioAtivo.administrador() == false){
						System.out.println("Usuario logado nao eh adiministrador!");
						break;
					} else{
						System.out.print("Informe o codigo do pedido ABERTO: ");
						int codigoPedido = Integer.parseInt(in.nextLine());
						Pedido pedidoBuscado = rPedidos.buscaPorCodigo(codigoPedido);
						if(pedidoBuscado != null){
							System.out.println("Pedido encontrado: "+pedidoBuscado);
							System.out.print("Digite 1 APROVAR | 2 REJEITAR | 3 cancelar operacao: ");
							int opc = Integer.parseInt(in.nextLine());
							while(opc != 1 && opc != 2 && opc != 3){
								System.out.println("Opcao invalida, tente novamente: ");
								opc = Integer.parseInt(in.nextLine());
							}
							alteraStatusPedido(pedidoBuscado, opc);
						}
						else{
							System.out.println("Nenhum pedido aberto encontrado com o codigo informado.");
						}
					}
					break;
				}
				case 9 : {
					System.out.println("Informe o codigo do Pedido a ser excluido");
					int cod = Integer.parseInt(in.nextLine());
					if( rPedidos.buscaPorCodigo(cod) == null){
						System.out.println("Pedido nao encontrado!");
						} else if(rPedidos.excluirPedido(usuarioAtivo, cod)){
							System.out.println("Pedido excluido com sucesso!");
						} else {
							System.out.println("Usuario logado nao eh o mesmo do pedido informado!");
							System.out.println("OBS: Apenas o proprio funcionario que criou o pedido podera exclui-lo");
						}
					break;
				}
				case 0 : {
					System.out.println("Programa Finalizado!");
					return;
				}
				default : {
					System.out.println("Opcao invalida!");
					break;
				}
			}
		}
	}

	private static void exibeMenu() {
		System.out.println(" ====== MENU ====== ");
		if(usuarioAtivo != null) System.out.println("Voce esta logado como: "+usuarioAtivo.getNome());
		else System.out.println("Voce nao esta logado, por favor selecione seu usuario.");
		System.out.println("1. Selecionar seu usuario;");
		System.out.println("2. Exibir pedidos em Aberto;");
		System.out.println("3. Exibir estatisticas gerais do funcionario;");
		System.out.println("4. Criar novo pedido;");
		System.out.println("5. Listar todos os pedidos entre duas datas;");
		System.out.println("6. Buscar pedidos pela descricao de um item;");
		System.out.println("7. Buscar pedidos por funcionario solicitante;");
		System.out.println("8. APROVAR ou REPROVAR pedido;");
		System.out.println("9. EXCLUIR um pedido;");
		System.out.println("0. Sair.");
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

	public static void alteraStatusPedido(Pedido pedido, int opcaoEscolhida){
		if(pedido != null){
			switch (opcaoEscolhida) {
				case 1:
					pedido.aprovar(usuarioAtivo);
					System.out.println("Pedido APROVADO com sucesso.");
					break;
					case 2:
					pedido.reprovar(usuarioAtivo);
					System.out.println("Pedido REPROVADO com sucesso.");
					break;
				case 3:
					System.out.println("Operacao cancelada.");
					break;
				default:
					break;
			}
		}
	}

	public static void wait(int ms)
{
    try
    {
        Thread.sleep(ms);
    }
    catch(InterruptedException ex)
    {
        Thread.currentThread().interrupt();
    }
}
}