package gui;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.entities.Department;
import model.entities.Seller;
import model.exceptions.ValidationException;
import model.services.DepartmentService;
import model.services.SellerService;

public class SellerFormController implements Initializable {

	private SellerService sellerService;

	private DepartmentService departmentService;

	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

	private Seller entity;

	@FXML
	private TextField txtId;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtSalary;

	@FXML
	private DatePicker dpBirhtDate;

	@FXML
	private Label labelError;
	@FXML
	private Label labelErrorEmail;
	@FXML
	private Label labelErrorBirthDate;
	@FXML
	private Label labelErrorSalary;

	@FXML
	private Button btSave;
	@FXML
	private Button btCancel;

	@FXML
	private ComboBox<Department> comboBoxDepartment;

	private ObservableList<Department> obsList;

	public void setServices(SellerService sellerService, DepartmentService departmentService) {
		this.sellerService = sellerService;
		this.departmentService = departmentService;
	}

	public void setSeller(Seller entity) {
		this.entity = entity;
	}

	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}

	@FXML
	public void onBtSaveAction(ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("Entity was null!");
		}
		if (sellerService == null) {
			throw new IllegalStateException("Seller Service was null!");
		}

		try {
			entity = getFormData();
			sellerService.saveOrUpdate(entity);
			Alerts.showAlert("Salve complete", null, "Seller salve success", AlertType.INFORMATION);
			notifyDataChangeListeners();
			Utils.currentStage(event).close(); // Fecha a janela

		} catch (ValidationException e) {
			setErrorMessages(e.getErros());
		} catch (DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
	}

	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChange();
		}
	}

	private Seller getFormData() {
		Seller obj = new Seller();
		obj.setId(Utils.tryParseToInt(txtId.getText()));

		ValidationException exception = new ValidationException("Validation Error");

		if (txtName.getText() == null || txtName.getText().trim().equals("")) {
			exception.addError("name", "Field can't be empty");
		}

		obj.setName(txtName.getText());

		// Lança a exceção no final pois caso tenha varios campos e algum deles
		// adcionasse um erro, lançaria o erro de todos
		if (exception.getErros().size() > 0) {
			throw exception;
		}

		return obj;
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close(); // Fecha a janela
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 70);
		Constraints.setTextFieldDouble(txtSalary);
		Constraints.setTextFieldMaxLength(txtEmail, 70);
		Utils.formatDatePicker(dpBirhtDate, "dd/MM/yyyy");
		
		initializeComboBoxDepartment();
	}

	public void updateFormData() {
		Locale.setDefault(Locale.US);
		if (entity == null) {
			throw new IllegalStateException("Entity was null!");
		}
		txtId.setText(String.valueOf(entity.getId())); // Precisa converter para String pois o TextField so aceita tipo
														// String
		txtName.setText(entity.getName());
		txtEmail.setText(entity.getEmail());
//		txtSalary.setText(String.format("%.2f", entity.getSalary()));
		if (entity.getDate() != null) {
			dpBirhtDate.setValue(LocalDate.ofInstant(entity.getDate().toInstant(), ZoneId.systemDefault()));
		}
		
		if (entity.getDepartment() == null) {
			comboBoxDepartment.getSelectionModel().selectFirst();
		}
		else {
			comboBoxDepartment.setValue(entity.getDepartment());
		}
	}

	public void loadAssociatedObjects() {
		if (departmentService == null) {
			throw new IllegalStateException("Department Service was null");
		}

//		Buscou no banco a lista de departamentos
		List<Department> list = departmentService.findAll();

//		Instanciou a "obsList" com a lista dos departamentos
		obsList = FXCollections.observableArrayList(list);

//		Carregou o comboBox com a lista de departamentos
		comboBoxDepartment.setItems(obsList);
	}

	private void setErrorMessages(Map<String, String> erros) {
		for (String key : erros.keySet()) {
			if (key.equals("name")) {
				labelError.setText(erros.get(key));
			}
		}
	}

	private void initializeComboBoxDepartment() {
		Callback<ListView<Department>, ListCell<Department>> factory = lv -> new ListCell<Department>() {
			@Override
			protected void updateItem(Department item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getName());
			}
		};
		comboBoxDepartment.setCellFactory(factory);
		comboBoxDepartment.setButtonCell(factory.call(null));
	}

}
