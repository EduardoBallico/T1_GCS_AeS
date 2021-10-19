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

	public boolean inserePedido(Pedido pedAux){
		if(pedAux == null){
			return false;
		}
		pedidos.add(pedAux);
		return true;
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

	public boolean excluirPedido(Usuario usuario, int codigo){
		for(Pedido p: pedidos){
			if(p.getStatus() == StatusPedido.ABERTO && p.getCodigo() == codigo && p.getFunc() == usuario){
				pedidos.remove(p);
				return true;
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
		String totalPedidosDesc = "Total de pedidos: "+totalPedidos;
		totalPedidosDesc += pedidosAprovados != 0 ?  "\n	Pedidos aprovados: "+pedidosAprovados+" ("+(((double)totalPedidos/pedidosAprovados)*100)+"%)" : "\n	Pedidos aprovados: 0";
		totalPedidosDesc += pedidosReprovados != 0 ? "\n	Pedidos reprovados: "+pedidosAprovados+" ("+(((double)totalPedidos/pedidosReprovados)*100)+"%)" : "\n	Pedidos reprovados: 0";
				
		String dataAtual = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		String dia = dataAtual.substring(0,2);
		int mes = (Integer.parseInt(dataAtual.substring(3, 5))-1);
		// se mes for janeiro (1),menos 1 fica 0, entao pegamos o mes anterior, dezembro (12)
		String mesStr = mes < 10 ? "0"+mes : ""+mes;
		int ano = Integer.parseInt(dataAtual.substring(6, 10));
		String dataMesAnteriorStr = mes == 0 ? dia +"/12/"+ (ano-1) : dia +"/"+ mesStr +"/"+ ano;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		double valorMedio = 0;
		int pedidosNoUltimoMes = 0;
		double valorDepComercial = 0, valorDepEngenharia = 0, valorDepFinanceiro = 0, valorDepManutencao = 0, valorDepRH = 0, valorDepTI = 0;
		Pedido pedidoAbertoMaior = null;
		try {
			Date dataMesAnterior = formatter.parse(dataMesAnteriorStr);
			for(Pedido p : pedidos){
				if(dataMesAnterior.before(p.getDataPed())){
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
					if(pedidoAbertoMaior == null || p.getValTot() > pedidoAbertoMaior.getValTot()){
						pedidoAbertoMaior = p;
					}
				}
			}
			valorMedio = pedidosNoUltimoMes != 0 ? valorMedio / pedidosNoUltimoMes : 0;
		} catch (Exception e) {
			return null;
		}
		String numeroPedidosDesc = "Numero de pedidos nos ultimos 30 dias: "+pedidosNoUltimoMes+"\n	Valor médio: "+valorMedio;
		String[] departamentos = {"Departamento Comercial", "Departamento Engenharia", "Departamento Financeiro", "Departamento Manutencao", "Departamento RH", "Departamento TI"};
		double[] valorPorDepartamento = {valorDepComercial, valorDepEngenharia, valorDepFinanceiro, valorDepManutencao, valorDepRH, valorDepTI};
		String descDepartamentos = "Valor por departamento nos últimos 30 dias:";
		for (int i = 0; i < departamentos.length; i++) {
			descDepartamentos += "\n	"+departamentos[i]+": "+valorPorDepartamento[i];
		}
		String pedidoAbertoMaiorDesc = "\nPedido aberto de maior valor:\n"+pedidoAbertoMaior.toString();
		return totalPedidosDesc+"\n"+numeroPedidosDesc+"\n"+descDepartamentos+"\n"+pedidoAbertoMaiorDesc;
	}
}
