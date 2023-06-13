package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable  {

	private static Scene mainScene;
	
	@FXML
	private TextField txtUser;

	@FXML
	private PasswordField pfPassword;

	@FXML
	private Button btEnter;

	@FXML
	private Button btCancel;

	public void onBtEnterAction(ActionEvent event) {
	    String username = txtUser.getText();
	    String password = pfPassword.getText();

	    if (username.equals("1") && password.equals("1")) {
	        try {
	            Stage currentStage = (Stage) btEnter.getScene().getWindow();
	            Parent mainViewParent = FXMLLoader.load(getClass().getResource("/gui/MainView.fxml"));
	            mainScene = new Scene(mainViewParent);
	            currentStage.setScene(mainScene);
	            currentStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else {
	        Alerts.showAlert("User or password incorrects", null, "User or password invalid", AlertType.ERROR);
	    }
	}

	public void onBtCancelAction() {

	}

	@Override
	public void initialize(URL url, ResourceBundle db) {

	}





}
