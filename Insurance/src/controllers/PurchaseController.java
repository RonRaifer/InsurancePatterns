package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class PurchaseController {

    @FXML
    private ImageView insImage;
    
    @FXML
    private Label lblInsType;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnClear;

    @FXML
    private TextField tbFirstName;

    @FXML
    private TextField tbLastName;

    @FXML
    private TextField tbID;

    @FXML
    private TextField tbDate;

    @FXML
    private TextArea taRemarks;

    @FXML
    void Clear_btnClick(ActionEvent event) {

    }


    @FXML
    void Save_btnClick(ActionEvent event) {

    }
    
    void setLabelTypeText(String insType, String img) {
    	this.lblInsType.setText(insType);
    	this.insImage.setImage(new Image("/view/images/"+img+".png"));
    }

}
