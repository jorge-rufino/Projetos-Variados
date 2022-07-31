package entities;

public class NotaPagamento {
	private Double pagamentoBase;
	private Double imposto;

	public NotaPagamento(Double pagamentoBase, Double imposto) {
		
		this.pagamentoBase = pagamentoBase;
		this.imposto = imposto;
	}

	public double pagamentoTotal() {
		return getPagamentoBase() + getImposto();
	}
	
	public Double getPagamentoBase() {
		return pagamentoBase;
	}

	public void setPagamentoBase(Double pagamentoBase) {
		this.pagamentoBase = pagamentoBase;
	}

	public Double getImposto() {
		return imposto;
	}

	public void setImposto(Double imposto) {
		this.imposto = imposto;
	}
		
}
