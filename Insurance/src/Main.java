import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main  extends Application {
	/**
	   * Loads the first window of system
	   * a
	   */ 
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(controllers.MainViewController.class.getResource("/view/MainView.fxml"));
			
			AnchorPane requestsMain = loader.load();
			Scene scene = new Scene(requestsMain);
			primaryStage.setTitle("Requests Update Menu");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e) {
	          e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}