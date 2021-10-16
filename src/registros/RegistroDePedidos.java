package registros;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import models.Pedido;
import models.Usuario;

public class RegistroDePedidos {

	public RegistroDePedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	private ArrayList<Pedido> pedidos;

	// recebe datas string no formato: dd/MM/yyyy
	public ArrayList<Pedido> listaPedidosNoPeriodo(String dataInicial, String dataFinal) {
		// informa os pedidos entre data inicial e data final
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
		String resp = "";

		return null;
	}
}
