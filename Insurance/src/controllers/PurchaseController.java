package controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import infrastructures.Factories.GenericFactory;
import infrastructures.Factories.IFactory;
import infrastructures.Factories.IPolicyFactory;
import infrastructures.Factories.Policy;
import infrastructures.Factories.PolicyFactory;
import infrastructures.Logger.Logger;
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

    private IPolicyFactory policyFactory = new PolicyFactory();
    IFactory<Policy> genericFactory = new GenericFactory<Policy>(Policy.class);
    String policyType="car";
    @FXML
    void Clear_btnClick(ActionEvent event) {
    	tbFirstName.setText("");
    	tbLastName.setText("");
    	tbID.setText("");
    	tbDate.setText("");
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
            Date startDate = new Date(0);  
            String remarks = taRemarks.getText();

            Policy policy = policyFactory.create(policyType, firstName, lastName, startDate.getTime(), remarks);
            Logger logger = Logger.getInstance();
            logger.log("contumer: " + firstName + " " + lastName + ", ID: " + ID + " joined " + policyType + " insurance. starting Date: "+ startDate.getTime());
    	}
    }
    
    void setLabelTypeText(String insType, String img) {
    	this.lblInsType.setText(insType);
    	this.insImage.setImage(new Image("/view/images/"+img+".png"));
    }
    
    boolean AreFieldsComplete()
    {
    	if(tbFirstName.getText() == "" || tbLastName.getText() =="" || tbID.getText() == "" || tbDate.getText() == "" || taRemarks.getText() == "" )
    		return false;
    	return true;
    }
}
