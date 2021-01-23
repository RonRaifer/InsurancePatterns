import java.io.IOException;

import infrastructures.Logger.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main  extends Application {
	private double x, y;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/Layout.fxml"));
	        
	        Scene scene = new Scene(root);
	        
	        primaryStage.setTitle("Braude Insurance");
	        primaryStage.setResizable(false);
	        primaryStage.initStyle(StageStyle.UNDECORATED);
	       
	        root.setOnMousePressed(event -> {
	            x = event.getSceneX();
	            y = event.getSceneY();
	        });
	        root.setOnMouseDragged(event -> {

	            primaryStage.setX(event.getScreenX() - x);
	            primaryStage.setY(event.getScreenY() - y);

	        });
	        primaryStage.setScene(scene);
	        primaryStage.show();
			
		} catch (IOException e) {
			Logger.GetInstance().log(e.getMessage());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}