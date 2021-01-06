package controllers;

import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class LayoutController implements Initializable {
	
	@FXML
	private JFXButton btnQuit;

	@FXML
	private JFXButton btnCarIns;

	@FXML
	private JFXButton btnHealthIns;

	@FXML
	private JFXButton btnLifeIns;

	@FXML
	private JFXButton btnApartmentIns;

	@FXML
	private JFXButton btnSue;

	@FXML
	private JFXButton btnViewPurchases;
    @FXML
    private VBox vbContent;

    @FXML
    private Label lblBread;

    
    @FXML
	private void handleButtonAction(MouseEvent event) {
    	Object clicked = event.getSource();
    	if(clicked == btnCarIns)
    		updateView("Car");
    	if(clicked == btnHealthIns)
    		updateView("Health");
    	if(clicked == btnLifeIns)
    		updateView("Life");
    	if(clicked == btnApartmentIns)
    		updateView("Apartment");
    	if(clicked == btnViewPurchases)
    		loadView("showPurchases");
    	if(clicked == btnSue)
    		loadView("sueView");
    	if(clicked == btnQuit)
    		Platform.exit();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         //refreshNodes();
    }    
    
    private void updateView(String insType)
    {
    	vbContent.getChildren().clear();
    	FXMLLoader loader = new FXMLLoader();
        Node content;
        
        try {
			loader.setLocation(controllers.PurchaseController.class.getResource("/view/PurchaseView.fxml"));
			content =  loader.load();
			PurchaseController purchase = loader.getController();
			purchase.setLabelTypeText(insType);
            vbContent.getChildren().add(content);
                
        	} catch (IOException ex) {
                Logger.getLogger(LayoutController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
    
    private void loadView(String fxmlName)
    {
    	vbContent.getChildren().clear();
    	FXMLLoader loader = new FXMLLoader();
        Node content;
        
        try {
			loader.setLocation(controllers.ViewPurchasesController.class.getResource("/view/"+fxmlName+".fxml"));
			content =  loader.load();
			//ViewPurchasesController viewPurchases = loader.getController();

            vbContent.getChildren().add(content);
                
        	} catch (IOException ex) {
                Logger.getLogger(LayoutController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
    
}

