package exceptions;

public class DomainExceptions extends Exception{
	private static final long serialVersionUID = 1L;
	
	public DomainExceptions(String msg) {	//Construtor para receber a mensagem que iremos criar e passa-la para a superclasse
		super(msg);
	}
}
