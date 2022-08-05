Caso esteja dando erro ao importar o javafx.fxml.FXMLLoader, utilizar essas linhas no arquivo "module-info.java":
{
  requires javafx.controls;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
}
