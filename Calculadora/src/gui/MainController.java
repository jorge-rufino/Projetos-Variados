package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javax.script.ScriptException;

import entities.Calculator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import util.Constraints;
import util.Utils;

public class MainController implements Initializable{
	@FXML
	private TextField txtResult;
	
	@FXML
	private Button btn0;
	@FXML
	private Button btn1;
	@FXML
	private Button btn2;
	@FXML
	private Button btn3;
	@FXML
	private Button btn4;
	@FXML
	private Button btn5;
	@FXML
	private Button btn6;
	@FXML
	private Button btn7;
	@FXML
	private Button btn8;
	@FXML
	private Button btn9;
	
	@FXML
	private Button btnSum;
	@FXML
	private Button btnSub;
	@FXML
	private Button btnMultiply;
	@FXML
	private Button btnDivide;
	@FXML
	private Button btnResult;
	
	public void onBtn0Action() {
		String strField = txtResult.getText();		
		txtResult.setText(strField+"0");
		Utils.gainFocus(txtResult);		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Constraints.setTextFieldNumbersOperations(txtResult);
		Constraints.impedeSinais(txtResult);
	}
	
	public void onBtn0Result() throws ScriptException {
		String strField = txtResult.getText();
		Calculator obj = new Calculator();
		txtResult.setText(""+obj.evaluate(strField));
//		ScriptEngineManager script = new ScriptEngineManager();
//		ScriptEngine javaScript = script.getEngineByName("JavaScript");
//		txtResult.setText(""+javaScript.eval(strField));
		
		Utils.gainFocus(txtResult);		
	}
	
	public void onBtnSum() {
		Utils.changeOperation(txtResult, "+");
		Utils.gainFocus(txtResult);		
	}
	
	public void onBtnSub() {
		Utils.changeOperation(txtResult, "-");
		Utils.gainFocus(txtResult);		
	}
	
	public void onBtnMultiply() {
		Utils.changeOperation(txtResult, "*");
		Utils.gainFocus(txtResult);		
	}
	
	public void onBtnDivide() {
		Utils.changeOperation(txtResult, "/");
		Utils.gainFocus(txtResult);		
	}
}
