package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Person;
import view.PersonEditDialogController;
import view.PersonOverviewController;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Person> personData = FXCollections.observableArrayList();

	/**
	 * Construtor
	 */
	public Main() {
		// Add some sample data
		personData.add(new Person("Hans", "Muster", "Av Joao Fanjas", 68795000, "Benevides"));
		personData.add(new Person("Ruth", "Mueller","Rod. Br 316", 67000000, "Ananindeua"));
		personData.add(new Person("Heinz", "Kurz","Almirante Barroso", 68795000, "Belem"));
		personData.add(new Person("Cornelia", "Meier","Alameda Jacuraru", 68795000, "Benevides"));
		personData.add(new Person("Werner", "Meyer","Rua General Gurjao", 68795000, "Marituba"));
		personData.add(new Person("Lydia", "Kunz","Av Joao Fanjas", 68795000, "Benevides"));
		personData.add(new Person("Anna", "Best","Rua Paulo Sodre", 68795000, "Mosqueiro"));
		personData.add(new Person("Stefan", "Meier","Rua das Acacias", 68795000, "Santarem"));
		personData.add(new Person("Martin", "Mueller","Av Joao Fanjas", 68795000, "Benevides"));
	}
  
	/**
	 * Retorna os dados como uma observable list de Persons. 
	 * @return
	 */
	public ObservableList<Person> getPersonData() {
		return personData;
	}
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showPersonOverview();
    }
    
    /**
     * Inicializa o root layout (layout base).
     */
    public void initRootLayout() {
        try {
            // Carrega o root layout do arquivo fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Mostra a scene (cena) contendo o root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mostra o person overview dentro do root layout.
     */
    public void showPersonOverview() {
        try {
            // Carrega a person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Define a person overview no centro do root layout.
            rootLayout.setCenter(personOverview);

            // D� ao controlador acesso � the main app.
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	/**
	 * Retorna o palco principal.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Abre uma janela para editar detalhes para a pessoa especificada. Se o usu�rio clicar
     * OK, as mudan�as s�o salvasno objeto pessoa fornecido e retorna true.
     * 
     * @param person O objeto pessoa a ser editado
     * @return true Se o usu�rio clicou OK,  caso contr�rio false.
     */
    public boolean showPersonEditDialog(Person person) {
        try {
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Define a pessoa no controller.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Mostra a janela e espera at� o usu�rio fechar.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
