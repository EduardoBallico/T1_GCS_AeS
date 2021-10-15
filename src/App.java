import departamentos.DepComercial;
import departamentos.DepEngenharia;
import departamentos.DepFinanceiro;
import departamentos.DepTI;
import departamentos.Departamento;
import models.Item;
import models.ListaDeItens;
import models.Pedido;
import models.Usuario;

public class App {
	public static void main(String[] args) {
		System.out.print("Inicializando...");
		preencheDados();

	}

	public static void preencheDados() {
		Departamento dep1 = new DepTI(50000);
		Departamento dep2 = new DepComercial(80000);
		Departamento dep3 = new DepEngenharia(90000);
		Departamento dep4 = new DepFinanceiro(100000);

		Usuario funcionario1 = new Usuario(dep1, 1111, "Eduardo", true);
		Usuario funcionario2 = new Usuario(dep2, 1112, "Guilherme", false);
		Usuario funcionario3 = new Usuario(dep3, 1113, "Vinicius", false);
		Usuario funcionario4 = new Usuario(dep4, 1114, "Arthur", true);

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

		Pedido ped1 = new Pedido(funcionario1, dep1, list1);
		Pedido ped2 = new Pedido(funcionario2, dep2, list2);
		Pedido ped3 = new Pedido(funcionario3, dep3, list3);
		Pedido ped4 = new Pedido(funcionario4, dep4, list4);
	}
}
