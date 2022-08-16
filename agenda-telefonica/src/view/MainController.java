package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import util.Alerts;

public class MainController implements Initializable {

	
	public MainController() {}
	
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));			
			AnchorPane contatoOverview = (AnchorPane) loader.load();
			
			// Define o Contato overview dentro do mainLayout.
			Scene mainScene = Main.getMainScene();
			BorderPane borderPane = (BorderPane)mainScene.getRoot();
			
			borderPane.setCenter(contatoOverview);

			// Pega o controller do Consumer
			T controller = loader.getController();

			// Executa as funçoes do controller
			initializingAction.accept(controller);
		} catch (IOException e) {
			Alerts.showAlert("IO Excepetion", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		loadView("/gui/ContatoView.fxml", x -> {
		});
	}

}
