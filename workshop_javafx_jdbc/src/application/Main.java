package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	private static Scene mainScene;

	private static final String USERNAME = "usuariopadrao";
	private static final String PASSWORD = "senhapadrao";

	@Override
	public void start(Stage primaryStage) {
		
		Dialog<Boolean> loginDialog = createLoginDialog();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            loginDialog.setResult(true);
            loginDialog.close();
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            loginDialog.setResult(false);
            loginDialog.close();
        });

        VBox root = new VBox(10, loginButton, cancelButton);
        root.setPadding(new Insets(10));

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Login Dialog Example");
        primaryStage.show();

        boolean loggedIn = loginDialog.showAndWait().orElse(false);
        if (loggedIn) {
        	try {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
    			ScrollPane scrollPane = loader.load();
    			mainScene = new Scene(scrollPane);

    			scrollPane.setFitToHeight(true);
    			scrollPane.setFitToWidth(true);
    			primaryStage.setScene(mainScene);
    			primaryStage.setTitle("Sample JavaFX application");
    			primaryStage.show();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}

        } else {
        	primaryStage.close();
            
        }
        

		
	}

	public static Scene getMainScene() {
		return mainScene;
	}

	public static void main(String[] args) {
		launch(args);
	}

	private Dialog<Boolean> createLoginDialog() {
		Dialog<Boolean> dialog = new Dialog<>();
		dialog.setTitle("Login");
		dialog.setHeaderText("Enter your credentials");

		DialogPane dialogPane = dialog.getDialogPane();
		dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		TextField usernameField = new TextField();
		PasswordField passwordField = new PasswordField();

		dialogPane
				.setContent(new VBox(10, new Label("Username:"), usernameField, new Label("Password:"), passwordField));

		dialog.setResultConverter(buttonType -> {
			if (buttonType == ButtonType.OK) {
				String username = usernameField.getText();
				String password = passwordField.getText();

				if (username.equals(USERNAME) && password.equals(PASSWORD)) {
					return true;
				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Login Error");
					alert.setHeaderText("Invalid credentials");
					alert.setContentText("Please enter valid username and password.");
					alert.showAndWait();
					
				}
			}
			return false;
		});

		return dialog;
	}
}
