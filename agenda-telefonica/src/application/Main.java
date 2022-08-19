package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import services.PessoaService;
import view.ContatoController;


public class Main extends Application {
	
	private static Scene mainScene;
	private static Stage mainStage;
	private static BorderPane borderPane;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));
			borderPane = loader.load();
			
			mainStage = primaryStage;
			mainScene = new Scene(borderPane);
			
			mainStage = primaryStage;
			
			mainStage.setScene(mainScene);
			mainStage.setTitle("Agenda Telefonica - By Rufino");
			mainStage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/imagens/agenda.png")));
			
			showContatoView();
			
			mainStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}
	
	public static Stage getMainStage() {
		return mainStage;
	}
	
	public static BorderPane getMainBorderPane() {
		return borderPane;
	}
	
	public void showContatoView() {
        try {
            // Carrega o person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/ContatoView.fxml"));
            AnchorPane contatoOverview = (AnchorPane) loader.load();
            
            ContatoController controller = loader.getController();
            controller.setPessoaService(new PessoaService());
            controller.updateTable();
            
            // Define o Contato overview dentro do mainLayout.
            borderPane.setCenter(contatoOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
