package registros;

import java.util.Date;

import departamentos.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import models.Pedido;
import models.StatusPedido;
import models.Usuario;

public class RegistroDePedidos {
	
	private ArrayList<Pedido> pedidos;

	public RegistroDePedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	// recebe datas string no formato: dd/MM/yyyy
	public ArrayList<Pedido> listaPedidosNoPeriodo(String dataInicial, String dataFinal) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date dataInic = formatter.parse(dataInicial);
			Date dataFin = formatter.parse(dataFinal);
			ArrayList<Pedido> listAux = new ArrayList<>();
			for (Pedido p : pedidos) {
				Date dataPed = p.getDataPed();
				if (dataPed.before(dataFin) && dataPed.after(dataInic)) {
					listAux.add(p);
				}
			}
			return listAux;
		} catch (Exception e) {
			return null;
		}
	}

	public ArrayList<Pedido> buscaSolicitante(Usuario funcionario) {
		ArrayList<Pedido> pedSolic = new ArrayList<Pedido>();
		for (Pedido p : pedidos) {
			if (p.getFunc() == funcionario) {
				pedSolic.add(p);
			}
		}
		return pedSolic;
	}

	public ArrayList<Pedido> buscaDescricao(String descricao) {
		ArrayList<Pedido> pedDesc = new ArrayList<Pedido>();
		for (Pedido p : pedidos) {
			if (p.getItem(descricao)) {
				pedDesc.add(p);
			}
		}
		return pedDesc;
	}

	public ArrayList<Pedido> buscaAberto() {
		ArrayList<Pedido> pedAberto = new ArrayList<Pedido>();
		for (Pedido p : pedidos) {
			if (!p.concluido()) {
				pedAberto.add(p);
			}
		}
		return pedAberto;
	}

	public boolean aprovar(int codigo, Usuario usuario) {
		for (int i = 0; i < pedidos.size(); i++) {
			if (pedidos.get(i).getCodigo() == codigo) {
				if (pedidos.get(i).aprovar(usuario)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean reprovar(int codigo, Usuario usuario) {
		for (int i = 0; i < pedidos.size(); i++) {
			if (pedidos.get(i).getCodigo() == codigo) {
				if (pedidos.get(i).reprovar(usuario)) {
					return true;
				}
			}
		}
		return false;
	}

	public String estatisticasGerais(Usuario usuario) {
		int pedidosAprovados = 0, pedidosReprovados = 0 , totalPedidos = 0;
		for(Pedido p : pedidos){
			if(p.getStatus() == StatusPedido.APROVADO) pedidosAprovados++;
			else if (p.getStatus() == StatusPedido.REPROVADO) pedidosReprovados++;
			totalPedidos++;
		}
		String totalPedidosDesc = "Total de pedidos: "+totalPedidos
		+"\n	Pedidos aprovados: "+pedidosAprovados+"("+(((double)totalPedidos/pedidosAprovados)*100)+"%)\n	Pedidos reprovados: "+pedidosAprovados+"("+(((double)totalPedidos/pedidosReprovados)*100)+"%)";
				
		// essa classe Calendar.MONTH tem indexes 0-11 para os meses, por isso, se o mes for 0 colocamos 12 pois um mes antes de janeiro vem dezembro, e dimin.uimos 1 do ano atual
		String data = Calendar.MONTH == 0 ? Calendar.DATE +"/"+ 12 +"/"+ (Calendar.YEAR-1) : Calendar.DAY_OF_WEEK +"/"+ Calendar.MONTH +"/"+ Calendar.YEAR;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		double valorMedio = 0, pedidosNoUltimoMes = 0;
		double valorDepComercial = 0, valorDepEngenharia = 0, valorDepFinanceiro = 0, valorDepManutencao = 0, valorDepRH = 0, valorDepTI = 0;
		Pedido pedidoAbertoMaior = null;
		try {
			Date dataMesAnterior = formatter.parse(data);
			for(Pedido p : pedidos){
				if(dataMesAnterior.after(p.getDataPed())){
					valorMedio += p.getValTot();
					pedidosNoUltimoMes++;
					if(p.getDepart() instanceof DepComercial) valorDepComercial+= p.getValTot();
					else if(p.getDepart() instanceof DepEngenharia) valorDepEngenharia+= p.getValTot();
					else if(p.getDepart() instanceof DepFinanceiro) valorDepFinanceiro+= p.getValTot();
					else if(p.getDepart() instanceof DepManutencao) valorDepManutencao+= p.getValTot();
					else if(p.getDepart() instanceof DepRH) valorDepRH+= p.getValTot();
					else if(p.getDepart() instanceof DepTI) valorDepTI+= p.getValTot();
				}
				if(p.getStatus() == StatusPedido.ABERTO){
					if(pedidoAbertoMaior == null && p.getValTot() > pedidoAbertoMaior.getValTot()){
						pedidoAbertoMaior = p;
					}
				}
			}
			valorMedio = valorMedio / pedidosNoUltimoMes;
		} catch (Exception e) {
			return null;
		}
		String numeroPedidosDesc = "Numero de pedidos nos ultimos 30 dias: "+pedidosNoUltimoMes+"\n	Valor médio: "+valorMedio;
		String[] departamentos = {"Departamento Comercial", "Departamento Engenharia", "Departamento Financeiro", "Departamento Manutencao", "Departamento RH", "Departamento TI"};
		double[] valorPorDepartamento = {valorDepComercial, valorDepEngenharia, valorDepFinanceiro, valorDepManutencao, valorDepRH, valorDepTI};
		String descDepartamentos = "";
		for (int i = 0; i < departamentos.length; i++) {
			descDepartamentos += "\n"+departamentos[i]+": "+valorPorDepartamento;
		}
		String pedidoAbertoMaiorDesc = pedidoAbertoMaior.toString();
		return totalPedidosDesc+"\n"+numeroPedidosDesc+"\n"+descDepartamentos+"\n"+pedidoAbertoMaiorDesc;
	}
}
