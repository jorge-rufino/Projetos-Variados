package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import db.DbIntegrityException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Seller;
import model.services.SellerService;

public class SellerController implements Initializable, DataChangeListener {

	private SellerService departmentService;

	@FXML
	private TableView<Seller> tableViewSeller;

	@FXML
	private TableColumn<Seller, Integer> tableColumnId;
	@FXML
	private TableColumn<Seller, String> tableColumnName;
	@FXML
	private TableColumn<Seller, String> tableColumnEmail;
	@FXML
	private TableColumn<Seller, Date> tableColumnBithDate;
	@FXML
	private TableColumn<Seller, Double> tableColumnSalary;

	@FXML
	TableColumn<Seller, Seller> tableColumnEDIT;

	@FXML
	TableColumn<Seller, Seller> tableColumnREMOVE;

	@FXML
	private Button btNew;

	private ObservableList<Seller> obsSellerList;

	@FXML
	public void onBtNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Seller obj = new Seller();
		createDialogForm(obj, "/gui/SellerFormView.fxml", parentStage);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	// Injecao de Dependencia
	public void setSellerService(SellerService departmentService) {
		this.departmentService = departmentService;
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		tableColumnBithDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		Utils.formatTableColumnDate(tableColumnBithDate, "dd/MM/yyyy");
		
		tableColumnSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
		//Segundo parametro é o numero de casas decimais
		Utils.formatTableColumnDouble(tableColumnSalary, 2);
		
		// Pega o Scene principal e faz downcasting para pegar a superclasse que no caso
		// é o nosso Stage definido na Main.class (primaryStage)
		Stage stage = (Stage) Main.getMainScene().getWindow();

		// Define o "height" da tabela igual ao "height" do Stage/Palco fazendo assim
		// que a tabela preencha toda a tela/stage/palco
		tableViewSeller.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (departmentService == null) {
			throw new IllegalStateException("Service was null");
		}

		List<Seller> list = departmentService.findAll(); // Cria lista com todos os Sellers
		list.sort(Comparator.comparing(Seller::getName)); // Ordena a lista pelo nome
		obsSellerList = FXCollections.observableArrayList(list); // Instancia o ObservableList com a lista
		tableViewSeller.setItems(obsSellerList); // Carrega os dados na tabela
		initEditButtons();
		initRemoveButtons();
	}

	private void createDialogForm(Seller obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load(); // Carrega a view SellerForm

			SellerFormController controller = loader.getController();
			controller.setSeller(obj);
			controller.setSellerService(new SellerService());
			controller.subscribeDataChangeListener(this); // Se inscreve para receber o Evento (Assim fica
															// ouvindo/esperando para atualizar a tabela)
			controller.updateFormData();

			// Cria um novo Stage/Palco que vai ficar por cima do Stage principal
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Enter Seller data:");
			dialogStage.setScene(new Scene(pane)); // Passa o form como cena
			dialogStage.setResizable(false); // Nao pode redimensionar a janela
			dialogStage.initOwner(parentStage); // Informa quem é o "PAI" dessa janela
			dialogStage.initModality(Modality.WINDOW_MODAL);// Janela será Modal, ou seja, enquanto nao fechar ela, não
															// acessa outra janela
			dialogStage.setWidth(parentStage.getWidth()); // Seta o tamanho maximo igual ao tamanho da janela Pai mas
															// caso a janela seja maximida,
															// Fica limitada ao tamanhao abaixo
			dialogStage.setMaxWidth(720); // Seta a largura maxima em 720
			dialogStage.setMaxHeight(220); // Seta a altura maxima em 220

			dialogStage.showAndWait();

		} catch (IOException e) {
			Alerts.showAlert("IO Excpetion", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChange() {
		updateTableView();
	}

	private void initEditButtons() {
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDIT.setCellFactory(param -> new TableCell<Seller, Seller>() {
			private final Button button = new Button("edit");

			@Override
			protected void updateItem(Seller obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(
						event -> createDialogForm(obj, "/gui/SellerFormView.fxml", Utils.currentStage(event)));
			}
		});
	}

	private void initRemoveButtons() {
		tableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnREMOVE.setCellFactory(param -> new TableCell<Seller, Seller>() {
			private final Button button = new Button("remove");

			@Override
			protected void updateItem(Seller obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> removeEntity(obj));
			}			
		});
	}
	
	private void removeEntity(Seller obj) {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmation", "Are you sure to delele " + obj.getName() +" ?");
		
		if(result.get() == ButtonType.OK) {
			if (departmentService == null) {
				throw new IllegalStateException("Service was null");
			}
			try {
				departmentService.remove(obj);
				updateTableView();
			}
			catch (DbIntegrityException e) {
				Alerts.showAlert("Error removing object", null, e.getMessage(), AlertType.ERROR);
			}
		}
	}

}
