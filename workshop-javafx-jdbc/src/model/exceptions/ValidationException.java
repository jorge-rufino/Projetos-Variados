package model.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	//A key vai ser o nome do campo e valor vai ser a mensagem de erro deste campo
	private Map<String, String> erros = new HashMap<>();
	
	public ValidationException (String msg) {
		super(msg);
	}
	
	public Map<String, String> getErros(){
		return erros;
	}
		
	public void addError(String fieldName, String errorMessage) {
		erros.put(fieldName, errorMessage);
	}
}
