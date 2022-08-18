package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entities.Pessoa;
import util.Utils;

public class ContatoDialogController {
	
	private Pessoa pessoa;
	
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
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public void onBtnCancelar(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
}
