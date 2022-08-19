package view;

import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

import db.DbException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Endereco;
import model.entities.Pessoa;
import model.entities.Telefone;
import modelo.dao.DaoFactory;
import modelo.exception.ValidationException;
import services.PessoaService;
import util.Alerts;
import util.Constraints;
import util.Utils;

public class ContatoDialogController implements Initializable{
	
	private Pessoa pessoa;
	private Telefone telefone;
	
	private PessoaService pessoaService;
	
	@FXML
	private TextField txtDDD;
	@FXML
	private TextField txtNumero;
	@FXML
	private TextField txtTipo;
	
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtApelido;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtEndereco;
	@FXML
	private TextField txtCpf;
	@FXML
	private TextField txtSexo;
	@FXML
	private TextField txtCategoria;
	
	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnCancelar;
	
	@FXML
	private Label labelErroDdd;
	@FXML
	private Label labelErroNumero;
	@FXML
	private Label labelErroTipo;
	@FXML
	private Label labelErroNome;
	@FXML
	private Label labelErroCategoria;
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public void setPessoaService(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	@FXML
	public void onBtnCancelar(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	public void onBtnSalvar(ActionEvent event) {
		if (pessoa == null) {
			throw new IllegalStateException("Pessoa was null!");
		}
		if(pessoaService == null) {
			throw new IllegalStateException("Pessoa Service was null!");
		}
		
		try {
			pessoa = getDialogDataPessoa();			
			pessoaService.salvaContato(pessoa, telefone);
			Alerts.showAlert("Salve complete", null, "Contato salve success", AlertType.INFORMATION);
			Utils.currentStage(event).close();	

		}catch (ValidationException e) {
			setErrorMessagesInLabel(e.getErros());
		}catch (DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
	}
	
	private void setErrorMessagesInLabel(Map<String, String> erros) {		
		for (String key : erros.keySet()) {
			if (key.equals("Nome")) {
				labelErroNome.setText(erros.get(key));				
			}
			if (key.equals("ddd")) {
				labelErroDdd.setText(erros.get(key));				
			}
			if (key.equals("Numero")) {
				labelErroNumero.setText(erros.get(key));				
			}
			if (key.equals("Tipo")) {
				labelErroTipo.setText(erros.get(key));				
			}			
			if (key.equals("Categoria")) {
				labelErroCategoria.setText(erros.get(key));				
			}
		}
		
	}

	private Pessoa getDialogDataPessoa() {
		Pessoa pessoa = new Pessoa();
		Endereco endereco = new Endereco();
		telefone = new Telefone();
		
		ValidationException exception = new ValidationException("Validation Error");
		
		if (txtDDD.getText() == null || txtDDD.getText().trim().equals("")) {
			exception.addError("ddd", "Field can't be empty");		
		}
		
		if (txtNumero.getText() == null || txtNumero.getText().trim().equals("")) {
			exception.addError("Numero", "Field can't be empty");		
		}
		
		if (txtTipo.getText() == null || txtNumero.getText().trim().equals("")) {
			exception.addError("Tipo", "Field can't be empty");		
		}
		
		if (txtNome.getText() == null || txtNome.getText().trim().equals("")) {
			exception.addError("Nome", "Field can't be empty");		
		}
		
		if (txtCategoria.getText() == null || txtCategoria.getText().trim().equals("")) {
			exception.addError("Categoria", "Field can't be empty");		
		}
		
		if (exception.getErros().size() > 0) {
			throw exception;
		}
		
		telefone.setDdd(txtDDD.getText());
		telefone.setNumero(txtNumero.getText());
		telefone.setTipo(txtTipo.getText());
		
		//Faz endereço igual a Nulo pois getText traz uma String vazia, fazendo com que seja adicionado novo Endereço no banco
		if (txtEndereco.getText() == null || txtEndereco.getText().trim().equals("")) {
			endereco = null;
		}else {
			endereco.setRua(txtEndereco.getText().trim());	
		}
		
		pessoa.setNome(txtNome.getText());
		pessoa.setApelido(txtApelido.getText());
		pessoa.setEmail(txtEmail.getText());
		pessoa.setEndereco(endereco);
		pessoa.setCpf(txtCpf.getText());
		pessoa.setSexo(txtSexo.getText());
		pessoa.setCategoria(DaoFactory.createCategoriaDAO().findById(Integer.parseInt(txtCategoria.getText())));
		pessoa.setData_cadastro(new Date());
		
		return pessoa;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Constraints.setTextFieldInteger(txtDDD);
		Constraints.setTextFieldMaxLength(txtDDD,3);
		Constraints.setTextFieldInteger(txtNumero);
		Constraints.setTextFieldMaxLength(txtNumero,10);
		Constraints.setTextFieldMaxLength(txtApelido, 30);
		Constraints.setTextFieldMaxLength(txtSexo, 1);
		Constraints.setTextFieldMaxLength(txtCpf, 14);
	}
	
}
