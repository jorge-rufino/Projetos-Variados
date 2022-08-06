package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.entities.Person;

//Implementa Initializable para poder determinar ações que serão executadas quando o controlador for criado
public class PersonController implements Initializable {

	@FXML
	private ComboBox<Person> comboBoxPerson;

	// Tipo especial de lista que fica associado ao componente visual, no caso ao
	// ComboBox
	private ObservableList<Person> obsList;

	@FXML
	private Button btShowAll;
	
	public void onBtShowAllAction() {
		comboBoxPerson.getItems().stream().sorted(Comparator.comparing(Person::getName)).forEach(System.out::println);
	}
	
	public void onComboBoxPersonAction() {
		//Pega o item/objeto selecionado no ComboBox
		Person person = comboBoxPerson.getSelectionModel().getSelectedItem();
		System.out.println(person);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		List<Person> list = new ArrayList<Person>();
		list.add(new Person(1, "Maria", "maria@gmail.com"));
		list.add(new Person(2, "Alex", "alex@gmail.com"));
		list.add(new Person(3, "Bob", "bob@gmail.com"));

		// Instacia o ObservableList com a lista de Person
		obsList = FXCollections.observableArrayList(list);

		// Carrega os elementos no ComboBox
		comboBoxPerson.setItems(obsList);

		//Todo o código abaixo é para fazer aparecer somente o Name no ComboBox
		Callback<ListView<Person>, ListCell<Person>> factory = lv -> new ListCell<Person>() {
			@Override
			protected void updateItem(Person item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getName());	//Aqui define o que vai aparecer
			}
		};
		comboBoxPerson.setCellFactory(factory);
		comboBoxPerson.setButtonCell(factory.call(null));
	}
}
