package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentController implements Initializable{
	
	private DepartmentService departmentService;
	
	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department, String> tableColumnName;
	
	@FXML
	private Button btNew;
	
	private ObservableList<Department> obsDepartmentList;
	
	@FXML
	public void onBtNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Department obj = new Department();
		createDialogForm(obj, "/gui/DepartmentForm.fxml", parentStage);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();		
	}

	//Injecao de Dependencia
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		//Pega o Scene principal e faz downcasting para pegar a superclasse que no caso é o nosso Stage definido na Main.class (primaryStage)
		Stage stage = (Stage) Main.getMainScene().getWindow();
		
		//Define o "height" da tabela igual ao "height" do Stage/Palco fazendo assim que a tabela preencha toda a tela/stage/palco
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (departmentService == null) {
			throw new IllegalStateException("Service was null");
		}
		
		List<Department> list = departmentService.findAll();			//Cria lista com todos os Departments
		list.sort(Comparator.comparing(Department::getName));			//Ordena a lista pelo nome 
		obsDepartmentList = FXCollections.observableArrayList(list);	//Instancia o ObservableList com a lista
		tableViewDepartment.setItems(obsDepartmentList);				//Carrega os dados na tabela
	}
	
	private void createDialogForm(Department obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();	//Carrega a view DepartmentForm
			
			DepartmentFormController controller = loader.getController();
			controller.setDepartment(obj);
			controller.updateFormData();
			
			//Cria um novo Stage/Palco que vai ficar por cima do Stage principal
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Enter department data");
			dialogStage.setScene(new Scene(pane));			//Passa o form como cena
			dialogStage.setResizable(false);				//Nao pode redimensionar a janela
			dialogStage.initOwner(parentStage);				//Informa quem é o "PAI" dessa janela
			dialogStage.initModality(Modality.WINDOW_MODAL);//Janela será Modal, ou seja, enquanto nao fechar ela, não acessa outra janela
			dialogStage.setWidth(parentStage.getWidth());	//Seta o tamanho maximo igual ao tamanho da janela Pai mas caso a janela seja maximida,
															//Fica limitada ao tamanhao abaixo
			dialogStage.setMaxWidth(720);					//Seta a largura maxima em 720
			dialogStage.setMaxHeight(220);					//Seta a altura maxima em 220
			
			dialogStage.showAndWait();
			
		} catch (IOException e) {
			Alerts.showAlert("IO Excpetion", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}

