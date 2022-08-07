package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {
	
	//Retorna o palco do evento que for passado como parametro
	public static Stage currentStage(ActionEvent event) {
		//1 - Pega o Evento e faz o downcasting para Node 
		//2 - Como Node, pega a janela da Scene
		//3 - Faz o downcasting para o Stage
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}
}
