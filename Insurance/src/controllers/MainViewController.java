package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainViewController {
	
    
	/*
	@FXML
    private AnchorPane apMainView;

    @FXML
    private Button btCarIns;

    @FXML
    private Button btApartmentIns;

    @FXML
    private Button btLifeIns;

    @FXML
    private Button btHealthIns;

    @FXML
    private Button btViewPurchases;

    @FXML
    private Button btSue;

    @FXML
    void ApartmentInsurance_btnClick(ActionEvent event) {
    	createPurchaseWindow("Apartment", event);
    }

    @FXML
    void CarInsurance_btnClick(ActionEvent event) {
    	createPurchaseWindow("Car", event);
    }

    @FXML
    void HealthInsurance_btnClick(ActionEvent event) {
    	createPurchaseWindow("Health", event);
    }

    @FXML
    void LifeInsurance_btnClick(ActionEvent event) {
    	createPurchaseWindow("Life", event);
    }

    @FXML
    void Sue_btnClick(ActionEvent event) {

    }

    @FXML
    void ViewPurchases_btnClick(ActionEvent event) {
    	//createPurchaseWindow("")
    }
    
    void createPurchaseWindow(String insType, ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		AnchorPane root;
		try {
			root = loader.load(getClass().getResource("/view/PurchaseView.fxml").openStream());
			PurchaseController purchase = loader.getController();	
			//option.setBack(btnOptions.getScene()); //set back scene
			Scene scene = new Scene(root);
			stage.setTitle(insType + " Purchase");
			purchase.setLabelTypeText(insType);
			stage.setScene(scene);		
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
*/
}
