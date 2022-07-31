package secao_18_Interfaces.entities;

import secao_18_Interfaces.interfaces.InterestService;

public class BrazilInterestService implements InterestService{
	private Double interestRate;

	public BrazilInterestService(Double interestRate) {
		
		this.interestRate = interestRate;
	}
	
	/*					este método virou "Default Method" na classe InterestService, assim nao precisa ficar implementando ele
	 * 					toda vez que alguma classe implementar a interface
	@Override
	public double payment(double amount, int month) {
		return amount * Math.pow(1 + (interestRate / 100), month);
	}
	*/
	
	@Override
	public double getInterestRate() {
		return interestRate;
	}
	
	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}
	
}
