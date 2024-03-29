package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;
import model.services.SellerService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;

	@FXML
	private MenuItem menuItemDepartment;

	@FXML
	private MenuItem menuItemAbout;

	public void onMenuItemSellerAction() {
		loadView("/gui/SellerListView.fxml", (SellerController controller ) -> {
			controller.setSellerService(new SellerService());
			controller.updateTableView();
		});
	}

	public void onMenuItemSellerDepartmentAction() {
		loadView("/gui/DepartmentListView.fxml", (DepartmentController controller ) -> {
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		});
	}

	public void onMenuItemSellerAboutAction() {
		loadView("/gui/AboutView.fxml", x -> {});
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub

	}

	//Este m�todo carrega as views dentro do Stage principal
	//"synchronized" garante que o processamento de todo este m�todo n�o ser� interrompido durante o multithreading
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction ) {
		try {
			//Para carregar uma view dentro da VBox principal, temos que pegar ela e transformar em uma Children/filha do VBoxPrincipal
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();	
			VBox mainVbox = (VBox)((ScrollPane)mainScene.getRoot()).getContent();	
			
			//Pega o filho da VBox principal na posi�ao 0 que no caso � o MenuBar
			//Se tivessem outros childrens/filhos, teriamos que ir pegando todos para adicionar na ordem novamente
			Node mainMenu = mainVbox.getChildren().get(0);	
			
			mainVbox.getChildren().clear(); //Limpou todos os filhos 
			mainVbox.getChildren().add(mainMenu);	//Adicionou novamente o MenuBar
			mainVbox.getChildren().addAll(newVBox.getChildren());	//Adicionou todos os filhos da VBox da outra tela como filhos da VBox principal
			
			//Pega o controller do Consumer
			T controller = loader.getController();
			
			//Executa as fun�oes do controller
			initializingAction.accept(controller);
		}
		catch (IOException e) {
			Alerts.showAlert("IO Excepetion", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
		
}
