package controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;

import infrastructures.Factories.ClaimFactory;
import infrastructures.Factories.IFactory;
import infrastructures.Factories.IPolicyFactory;
import infrastructures.Factories.Policy;
import infrastructures.Factories.PolicyFactory;
import infrastructures.Logger.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
            Date startDate = new Date(tbDate.getValue().toEpochDay() * 24 * 60 * 60 * 1000); 
            String remarks = taRemarks.getText();
            
            policyFactory.create(policyType, firstName, lastName, ID ,startDate.getTime(), remarks);

            
    	}
    }
    
    void setLabelTypeText(String insType) {
    	this.policyType = insType;
    	this.lblInsType.setText(insType + " Insurance Sell");
    	this.insImage.setImage(new Image("/view/images/"+insType+".png"));
    }
    
    boolean AreFieldsComplete()
    {
    	if(tbFirstName.getText() == "" || tbLastName.getText() =="" || tbID.getText() == "" || tbDate.getValue() == null || taRemarks.getText() == "" )
    		return false;
    	return true;
    }
}
