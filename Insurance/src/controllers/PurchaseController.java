package controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Optional;

import infrastructures.Factories.ClaimFactory;
import infrastructures.Factories.IFactory;
import infrastructures.Factories.IPolicyFactory;
import infrastructures.Factories.Policy;
import infrastructures.Factories.PolicyFactory;
import infrastructures.Logger.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.StageStyle;


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
    private DatePicker tbDate;

    @FXML
    private TextArea taRemarks;

    private IPolicyFactory policyFactory = new PolicyFactory();
    private String policyType;
    @FXML
    void Clear_btnClick(ActionEvent event) {
    	tbFirstName.setText("");
    	tbLastName.setText("");
    	tbID.setText("");
    	tbDate.setValue(null);
    	taRemarks.setText("");
    }


    @FXML
    void Save_btnClick(ActionEvent event) {
    	if(AreFieldsComplete())
    	{
    		String firstName = tbFirstName.getText();
            String lastName = tbLastName.getText();
            String ID = tbID.getText();
            
            Date startDate = new Date(tbDate.getValue().toEpochDay() * 24 * 60 * 60 * 1000); 
            String remarks = taRemarks.getText();
            
            Policy obj = policyFactory.create(policyType, firstName, lastName, ID ,startDate.getTime(), remarks);
            if(obj!=null)
            {
            	//POP UP MESSAGE OF SUCCESFUL ADDITION HERE	
            	PUP("Successfully added!", "Confirmation");
            }
            else 
            {
            	//POP UP MESSAGE OF FAILURE ADDITION HERE
            	PUP("Failure adding new Insurance member.\n for more details see log file", "Error");
            }

            
    	}
    }
    
    
	static public void PUP(String str, String title)
	{
		if(title.equals("Error"))
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initStyle(StageStyle.DECORATED);
			ButtonType btn = new ButtonType("ok", ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().clear();
			alert.setHeaderText(title);
			alert.setContentText(str);
			alert.setTitle("OBL");
			alert.getButtonTypes().addAll(btn);
			Optional<ButtonType> result = alert.showAndWait();
		}
		if(title.equals("Information"))
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initStyle(StageStyle.DECORATED);
			ButtonType btn = new ButtonType("ok", ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().clear();
			alert.setHeaderText(title);
			alert.setContentText(str);
			alert.setTitle("OBL");
			alert.getButtonTypes().addAll(btn);
			Optional<ButtonType> result = alert.showAndWait();
		}
		if(title.equals("Confirmation"))
		{
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initStyle(StageStyle.DECORATED);
			ButtonType btn = new ButtonType("ok", ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().clear();
			alert.setHeaderText(title);
			alert.setContentText(str);
			alert.setTitle("OBL");
			alert.getButtonTypes().addAll(btn);
			Optional<ButtonType> result = alert.showAndWait();
		}
	}
    
    
    void setLabelTypeText(String insType) {
    	this.policyType = insType;
    	this.lblInsType.setText(insType + " Insurance Sell");
    	this.insImage.setImage(new Image("/view/images/"+insType+".png"));
    }
    void updatePolicy(Policy pol) {
    	this.lblInsType.setText(pol.type + " Update");
    	this.insImage.setImage(new Image("/view/images/"+pol.type+".png"));
    	
    	
    }
    boolean AreFieldsComplete()
    {
    	if(tbFirstName.getText() == "" || tbLastName.getText() =="" || tbID.getText() == "" || tbDate.getValue() == null || taRemarks.getText() == "" )
    		return false;
    	return true;
    }
}
