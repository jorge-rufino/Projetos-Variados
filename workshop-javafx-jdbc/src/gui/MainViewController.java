package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;

	@FXML
	private MenuItem menuItemDepartment;

	@FXML
	private MenuItem menuItemAbout;

	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}

	public void onMenuItemSellerDepartmentAction() {
		loadView2("/gui/DepartmentListView.fxml");
	}

	public void onMenuItemSellerAboutAction() {
		loadView("/gui/AboutView.fxml");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub

	}

	//"synchronized" garante que o processamento de todo este método não será interrompido durante o multithreading
	private synchronized void loadView(String absoluteName) {
		try {
//Para carregar uma view dentro da VBox principal, temos que pegar ela e transformar em uma Children/filha do VBoxPrincipal
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();	
			VBox mainVbox = (VBox)((ScrollPane)mainScene.getRoot()).getContent();	
			
			//Pega o filho da VBox principal na posiçao 0 que no caso é o MenuBar
			Node mainMenu = mainVbox.getChildren().get(0);	
			
			mainVbox.getChildren().clear(); //Limpou todos os filhos 
			mainVbox.getChildren().add(mainMenu);	//Adicionou novamente o MenuBar
			mainVbox.getChildren().addAll(newVBox.getChildren());	//Adicionou todos os filhos da VBox da outra tela como filhos da VBox principal
			
		}
		catch (IOException e) {
			Alerts.showAlert("IO Excepetion", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	
	private synchronized void loadView2(String absoluteName) {
		try {
//Para carregar uma view dentro da VBox principal, temos que pegar ela e transformar em uma Children/filha do VBoxPrincipal
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();	
			VBox mainVbox = (VBox)((ScrollPane)mainScene.getRoot()).getContent();	
			
			//Pega o filho da VBox principal na posiçao 0 que no caso é o MenuBar
			Node mainMenu = mainVbox.getChildren().get(0);	
			
			mainVbox.getChildren().clear(); //Limpou todos os filhos 
			mainVbox.getChildren().add(mainMenu);	//Adicionou novamente o MenuBar
			mainVbox.getChildren().addAll(newVBox.getChildren());	//Adicionou todos os filhos da VBox da outra tela como filhos da VBox principal
			
			DepartmentController controller = loader.getController();	//Criou a referencia para o controller da classe Department
			controller.setDepartmentService(new DepartmentService());	//Criou o serviço para poder chamar o metodo updateTableView
			controller.updateTableView();								//Chama o metodo para atualizar a tabela
		}
		catch (IOException e) {
			Alerts.showAlert("IO Excepetion", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}
