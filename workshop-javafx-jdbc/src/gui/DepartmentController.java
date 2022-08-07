package gui;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	public void onBtNewAction() {
		System.out.println("ButtonAction");
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
		
		//Pega o Scene principal e faz downcasting para pegar a superclasse que no caso � o nosso Stage definido na Main.class (primaryStage)
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
}