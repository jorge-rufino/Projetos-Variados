package application;
	
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Person;
import model.PersonListWrapper;
import util.Alerts;
import view.PersonEditDialogController;
import view.PersonOverviewController;
import view.RootLayoutController;

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

     // Set the application icon.
        this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/imagens/agenda.png")));
        
        initRootLayout();

        showPersonOverview();
    }
    
    /**
     * Inicializa o root layout e tenta carregar o último arquivo
     * de pessoa aberto.
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

            // Dá ao controller o acesso ao main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Tenta carregar o último arquivo de pessoa aberto.
        File file = getPersonFilePath();
        if (file != null) {
            loadPersonDataFromFile(file);
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

            // Dá ao controlador acesso à the main app.
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
     * Abre uma janela para editar detalhes para a pessoa especificada. Se o usuário clicar
     * OK, as mudanças são salvasno objeto pessoa fornecido e retorna true.
     * 
     * @param person O objeto pessoa a ser editado
     * @return true Se o usuário clicou OK,  caso contrário false.
     */
    public boolean showPersonEditDialog(Person person) {
        try {
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            //Adiciona uma borda branca
            final String cssDefault = "-fx-border-color: white;-fx-border-width: 1;";        
            page.setStyle(cssDefault);
            
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
            
            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Retorna o arquivo de preferências da pessoa, o último arquivo que foi aberto.
     * As preferências são lidas do registro específico do SO (Sistema Operacional). 
     * Se tais prefêrencias não puderem  ser encontradas, ele retorna null.
     * 
     * @return
     */
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);        
        String filePath = prefs.get("filePath", null);
        
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Define o caminho do arquivo do arquivo carregado atual. O caminho é persistido no
     * registro específico do SO (Sistema Operacional).
     * 
     * @param file O arquivo ou null para remover o caminho
     */
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("AddressApp");
        }
    }
    
    /**
     * Carrega os dados da pessoa do arquivo especificado. A pessoa atual
     * será substituída.
     * 
     * @param file
     */
    public void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

            personData.clear();
            personData.addAll(wrapper.getPersons());

            // Save the file path to the registry.
            setPersonFilePath(file);

        } catch (Exception e) { // catches ANY exception
        	Alerts.showAlert("Erro", "Erro de Carregamento", "Não foi possível carregar dados do arquivo:\n" 
                              + file.getPath(), AlertType.ERROR);            
        }
    }

    /**
     * Salva os dados da pessoa atual no arquivo especificado.
     * 
     * @param file
     */
    public void savePersonDataToFile(File file) {    	
        try {
            JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);            
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            // Envolvendo nossos dados da pessoa.
            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(personData);
            
            // Enpacotando e salvando XML  no arquivo.
            m.marshal(wrapper, file);

            // Saalva o caminho do arquivo no registro.
            setPersonFilePath(file);
        } catch (Exception e) { // catches ANY exception
        	Alerts.showAlert("Erro", "Erro ao Salvar", "Não foi possível salvar os dados no arquivo:\n" 
                    + file.getPath(), AlertType.ERROR);
        }
    }
}
