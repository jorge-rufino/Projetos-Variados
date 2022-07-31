package secao_18_Interfaces.application;

import application.Util;
import secao_18_Interfaces.entities.UsaInterestService;
import secao_18_Interfaces.interfaces.InterestService;

public class TestInterestService {

	public static void main(String[] args) {
		double amount = Util.inputDouble("Amount:");
		int month = Util.inputInt("Months:");
		
		InterestService interestServ =  new UsaInterestService(2.0);
		
		System.out.println("Payment after " + month + " months:");
		Util.output(""+String.format("%.2f", interestServ.payment(amount, month)), 1);

	}

}
