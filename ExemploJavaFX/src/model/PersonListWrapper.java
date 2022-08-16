package model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Classe auxiliar para envolver uma lista de pessoas. Esta é usada para salvar a
 * lista de pessoas em XML. 
 */
@XmlRootElement(name = "persons")
@XmlAccessorType (XmlAccessType.FIELD)
public class PersonListWrapper {

	private List<Person> persons;

	@XmlElement(name = "person")
	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
}