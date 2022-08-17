package view;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import db.DbException;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Categoria;
import model.entities.Pessoa;
import model.entities.Telefone;
import modelo.dao.DaoFactory;
import modelo.dao.PessoaDao;
import util.Alerts;

public class ContatoController implements Initializable{
	
	PessoaDao pessoaDao = DaoFactory.createPessoaDao();
	
	@FXML
	private TableView<Pessoa> tableViewPessoa;
	@FXML
	private TableColumn<Pessoa, String> tableColumnNome;
	@FXML
	private TableColumn<Pessoa, Pessoa> tableColumnTelefones;
	@FXML
	private TableColumn<Pessoa, Categoria> tableColumnCategoria;
	
	@FXML
	private Label labelNome;
	@FXML
	private Label labelApelido;
	@FXML
	private Label labelEmail;
	@FXML
	private Label labelEndereco;
	@FXML
	private Label labelCpf;
	@FXML
	private Label labelSexo;
	@FXML
	private Label labelCategoria;
	@FXML
	private Label labelDataCadastro;
	
	@FXML
	private Button btDelete;
	
	private ObservableList<Pessoa> obsPessoaList;	
	
	@Override	
	public void initialize(URL url, ResourceBundle rb) {
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		updateTable();
		
	    // Detecta mudanças de seleção e mostra os detalhes da pessoa quando houver mudança.
	    tableViewPessoa.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> showPessoaDetails(newValue));
		
		
	}
	
	public void updateTable() {
		List<Pessoa> listaPessoas = pessoaDao.findAll();
		listaPessoas.sort(Comparator.comparing(Pessoa::getNome));
		obsPessoaList = FXCollections.observableArrayList(listaPessoas);
		tableViewPessoa.setItems(obsPessoaList);
		
		tableColumnTelefones.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnTelefones.setCellFactory(coluna -> {
		    return new TableCell<Pessoa, Pessoa>(){
		    	
		        @Override
		        protected void updateItem(Pessoa item, boolean empty) {
		            super.updateItem(item, empty);
		            		            
		            if(item == null) {
		            	setText("");
		            	return;
		            }
		            
		            StringBuilder telefones = new StringBuilder();
	            	
		            if (item.getTelefone().size() > 0) {
		            	for (Telefone telefone : item.getTelefone()) {
							telefones.append("("); 
							telefones.append(telefone.getDdd());
							telefones.append(") ");
							telefones.append(telefone.getNumero() +"\n");
						}
		            }
		            else {
		            	telefones.append("Sem telefones");
		            }
	                setText(telefones.toString());
		        }
		    };
		 });
		
		tableColumnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		tableColumnCategoria.setCellFactory(coluna -> {
		    return new TableCell<Pessoa, Categoria>(){
		        @Override
		        protected void updateItem(Categoria item, boolean empty) {
		            super.updateItem(item, empty);
		            if(item != null && !empty) {
		                setText(item.getNome());
		            } else {
		                setText("");
		            }
		        }
		    };
		 });
	}
	
	public void showPessoaDetails(Pessoa pessoa) {
		if (pessoa != null) {
			labelNome.setText(pessoa.getNome());
			labelApelido.setText(pessoa.getApelido());
			labelEmail.setText(pessoa.getEmail());
			labelEndereco.setText(pessoa.getEndereco().getRua()+", "+ pessoa.getEndereco().getBairro() + ", " 
					+pessoa.getEndereco().getComplemento() + ", " + pessoa.getEndereco().getCidade()) ;
			labelCpf.setText(pessoa.getCpf());
			labelSexo.setText(pessoa.getSexo());
			labelCategoria.setText(pessoa.getCategoria().getNome());
			labelDataCadastro.setText("" + pessoa.getData_cadastro());
		} else {
			labelNome.setText("");
			labelApelido.setText("");
			labelEmail.setText("");
			labelEndereco.setText("");
			labelCpf.setText("");
			labelSexo.setText("");
			labelCategoria.setText("");
			labelDataCadastro.setText("");
		}
		
	}
	
	public void onDeletePessoa() {
		int selectedIndex = tableViewPessoa.getSelectionModel().getSelectedIndex();
		
		if (selectedIndex >= 0) {
			Pessoa pessoa = tableViewPessoa.getItems().get(selectedIndex);
			Optional<ButtonType> result = Alerts.showConfirmation("Confirmation", "Are you sure to delele " + pessoa.getNome() +" ?");
			
			if(result.get() == ButtonType.OK) {
				if (pessoaDao == null) {
					throw new IllegalStateException("Service was null");
				}
				try {
					pessoaDao.deleteById(pessoa.getId());
					updateTable();
				}
				catch (DbException e) {
					Alerts.showAlert("Error removing object", null, e.getMessage(), AlertType.ERROR);
				}
			}
			
			
	    } else {
			Alerts.showAlert("Mensagem Error", "Erro ao deletar contato", "Selecione um contato na lista.", AlertType.ERROR);
		}
	}
}
