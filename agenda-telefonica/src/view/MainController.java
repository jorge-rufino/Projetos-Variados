package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.Alerts;

public class MainController implements Initializable {

	@FXML
	private MenuItem menuItemAgenda;
	
	@FXML
	private MenuItem menuItemAbout;

	public MainController() {
	}

	public void onMenuItemAgenda() {
		loadView("/view/ContatoView.fxml", x -> {
		});
	}
	
	public void onMenuItemAbout() {
		loadViewAbout("/view/AboutView.fxml", x -> {
		});
	}

	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(absoluteName));
			AnchorPane contatoOverview = (AnchorPane) loader.load();

			// Define o Contato overview dentro do mainLayout.
			Main.getMainBorderPane().setCenter(contatoOverview);

			// Pega o controller do Consumer
			T controller = loader.getController();

			// Executa as funçoes do controller
			initializingAction.accept(controller);
		} catch (IOException e) {
			Alerts.showAlert("IO Excepetion", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	private synchronized <T> void loadViewAbout(String absoluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(absoluteName));
			VBox aboutView = (VBox) loader.load();

			// Define o Contato overview dentro do mainLayout.
			Main.getMainBorderPane().setCenter(aboutView);

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

	}

}
