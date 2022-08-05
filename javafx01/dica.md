*******Caso esteja dando erro ao importar o javafx.fxml.FXMLLoader, utilizar essas linhas no arquivo "module-info.java":

  requires javafx.controls;
  requires javafx.fxml;
	
  opens application to javafx.graphics, javafx.fxml;


*******Caso esteja dando erro para encontrar o a pasta do JavaFX-JDK, adicionar esta linha abaixo:
--module-path="C:\java libs\javafx-sdk\lib" --add-modules=javafx.fxml,javafx.controls

em Bota direito no Projeto -> Run As -> Arguments -> VM Arguments
(Lembrando de escolher corretamento caminho da pasta do JavaFX-JDK)