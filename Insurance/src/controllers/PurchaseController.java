package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PurchaseController {

    @FXML
    private AnchorPane apPurchaseView;

    @FXML
    private TextField tbFirstName;

    @FXML
    private Label lblInsType;

    @FXML
    private TextField tbLastName;

    @FXML
    private TextField tbDate;

    @FXML
    private TextField tbRemarks;

    @FXML
    private TextField tbID;

    @FXML
    private Button btBack;

    @FXML
    private Button btSave;

    @FXML
    void Back_btnClick(ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		AnchorPane root;
		try {
			root = loader.load(getClass().getResource("/view/MainView.fxml").openStream());			
			Scene scene = new Scene(root);	
			stage.setTitle("Insurance");
			stage.setScene(scene);	
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void Save_btnClick(ActionEvent event) {

    }
    
    void setLabelTypeText(String insType) {
    	this.lblInsType.setText(insType);
    }

}
