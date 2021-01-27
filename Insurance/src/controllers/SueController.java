package controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import infrastructures.Dao.ClaimDao;
import infrastructures.Dao.PolicyDao;
import infrastructures.Factories.ClaimFactory;
import infrastructures.Factories.IClaimFactory;
import infrastructures.Logger.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import models.Claim;
import models.Policy;

public class SueController implements Initializable {

    @FXML
    private TextField tbId;

    @FXML
    private Button btnSearch;

    @FXML
    private ScrollPane spSearchResults;

    @FXML
    private TableView<Policy> tblSearchResults;

    @FXML
    private TableColumn<Policy, String> nameCol;

    @FXML
    private TableColumn<Policy, String> lastCol;

    @FXML
    private TableColumn<Policy, String> typeCol;

    @FXML
    private TableColumn<Policy, Date> dateCol;

    @FXML
    private TableColumn<Policy, String> remarksCol;
    
    @FXML
    private TableColumn<Policy, Policy> sueCol;
    
    @FXML
    private TableView<Claim> tblClaims;

    @FXML
    private TableColumn<Claim, String> policyIDCol;

    @FXML
    private TableColumn<Claim, String> claimTypeCol;

    @FXML
    private TableColumn<Claim, String> claimRemarksCol;

    @FXML
    private TableColumn<Claim, String> amountCol;

    @FXML
    private TableColumn<Claim, String> statusCol;

    @FXML
    private TableColumn<Claim, Claim> optionsCol;
    @FXML
    private Pane pAddSue;

    @FXML
    private JFXButton btnSue;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnCancel;

    @FXML
    private TextField tbAmount;
    
    @FXML
    private TextArea taRemarks;
    
    @FXML
    private ComboBox<String> cmbStatus;

    @FXML
    private Label lblCustomerID;

    @FXML
    private Label lblFullName;

    @FXML
    private Label lblInsType;
    
    @FXML
    private Label lblError;
    
    ObservableList<Policy> list = FXCollections.observableArrayList();
    ObservableList<Claim> cList = FXCollections.observableArrayList();
    private IClaimFactory claimFactory = new ClaimFactory();
    private Policy policy;
    private Claim claim = new Claim();
    String idToSearch;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	spSearchResults.setVisible(false);
    	pAddSue.setVisible(false);
    }   
    
    @FXML
    void Search_btnClick(ActionEvent event) {
    	idToSearch = tbId.getText();
    	lblError.setText("");
    	spSearchResults.setVisible(false);
    	if(idToSearch == "") {
    		lblError.setText("ID should not be empty");
    	}
    	else {
    		updateSearchResults(idToSearch);
    	}

    }
    
    @FXML
    void Cancel_btnClick(ActionEvent event) {
    	pAddSue.setVisible(false);
    	tbAmount.clear();
    	taRemarks.clear();
    }
    
    @FXML
    void Save_btnClick(ActionEvent event) {
    	if(AreFieldsComplete()) {
    		String amount = tbAmount.getText();
            String status = cmbStatus.getValue();
            String remarks = taRemarks.getText();
            if(btnSue.getText().equals("Update")) {
            	claim.amount = amount;
            	claim.status = status;
            	claim.remarks = remarks;
            	try {
					ClaimDao.GetInstance().update(claim);
				} catch (SQLException e) {
					PurchaseController.PUP("Failure updating claim.\n for more details see log file", "Error");
					pAddSue.setVisible(false);
					Logger.GetInstance().log(e.getMessage());
				}
            	pAddSue.setVisible(false);
            	PurchaseController.PUP("Successfully updated!", "Confirmation");
            }
            else {
            	 Calendar cal = Calendar.getInstance();
                 long milliseconds = cal.getTimeInMillis();
                 String pID = policy.pID;
                 
                 Claim obj = claimFactory.create(null, pID, amount, status, milliseconds, remarks);
                 if(obj!=null)
                 {
                 	PurchaseController.PUP("Successfully added!", "Confirmation");
                 	pAddSue.setVisible(false);
                 }
                 else 
                 {
                 	PurchaseController.PUP("Failure adding new SUE.\n for more details see log file", "Error");
                 }
         	}
    	}
    	else {
     		PurchaseController.PUP("Fields must not be empty!", "Information");
     	}
    	updateSearchResults(idToSearch);
    }
    @FXML
    void Delete_btnClick(ActionEvent event) {
    	try {
	    	ClaimDao.GetInstance().delete(claim);	//delete claim for this policy id
		} catch (SQLException e) {
			PurchaseController.PUP("Failure deleting claim.\n for more details see log file", "Error");
			Logger.GetInstance().log(e.getMessage());
			pAddSue.setVisible(false);
		}
    	PurchaseController.PUP("Successfully deleted!", "Confirmation");
    	updateSearchResults(idToSearch);
    	pAddSue.setVisible(false);
    }
    private void updateSearchResults(String id) {
    	List<Policy> pl = PolicyDao.GetInstance().getByID(id);
    	List<Claim> cl = new ArrayList<Claim>();
    	
    	if(pl == null) {
    		lblError.setText("No such ID");
    	}
    	else {
    		for(Policy poli : pl) {
    			List<Claim> cTemp = ClaimDao.GetInstance().getByIDaddType(poli);
    			if(cTemp != null)
    				cl.addAll(cTemp);
    		}
    		updateSueResults(cl, pl.get(0));
        	nameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        	lastCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        	typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        	dateCol.setCellValueFactory(new PropertyValueFactory<>("startDay"));
        	remarksCol.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        	
        	sueCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        	sueCol.setCellFactory(param -> new TableCell<Policy, Policy>() { 
				@FXML
			    private final Button hlSue = new Button("Add Sue");
			    protected void updateItem(Policy pol, boolean empty) {
			        super.updateItem(pol, empty);

			        if (pol == null) {
			            setGraphic(null);
			            return;
			        }

			        setGraphic(hlSue);
			        hlSue.setOnAction(
			            event -> {
			            	Platform.runLater(new Runnable() {
			            	    @Override
			            	    public void run() {
			            	    	initSueWindow(pol);
			            	    }
			            	});
			            }
			        );
			    }
			});
        	
        	list = FXCollections.observableArrayList(pl);
        	tblSearchResults.setItems(list);
        	spSearchResults.setVisible(true);
    	}
    }
    
    private void updateSueResults(List<Claim> cl, Policy pl) {
    	if(cl == null) {
    		return;
    	}
    	else {
        	policyIDCol.setCellValueFactory(new PropertyValueFactory<>("pID"));
        	amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        	statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        	claimRemarksCol.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        	claimTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        	
        	optionsCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        	optionsCol.setCellFactory(param -> new TableCell<Claim, Claim>() { 
				@FXML
			    private final Button hlUpdate = new Button("Update/Delete");

			    protected void updateItem(Claim cla, boolean empty) {
			        super.updateItem(cla, empty);

			        if (cla == null) {
			            setGraphic(null);
			            return;
			        }

			        setGraphic(hlUpdate);
			        hlUpdate.setOnAction(
			            event -> {
			            	Platform.runLater(new Runnable() {
			            	    @Override
			            	    public void run() {
			            	    	initUpdateWindow(cla, pl);
			            	    }
			            	});
			            }
			        );
			    }
			});
        	
        	cList = FXCollections.observableArrayList(cl);
        	tblClaims.setItems(cList);
    	}
    }
    
    private void initSueWindow(Policy pol) {
    	List<String> status = new ArrayList<String>();
    	status.add("Approved");
    	status.add("Not Approved");
    	this.policy = pol;
        ObservableList<String> obList = FXCollections.observableList(status);	
    	cmbStatus.setItems(obList);
    	lblCustomerID.setText(pol.id);
    	lblFullName.setText(pol.firstName+" "+pol.lastName);
    	lblInsType.setText(pol.type);
    	tbAmount.clear();
    	taRemarks.clear();
    	btnSue.setText("Save Sue");
    	btnDelete.setVisible(false);
    	pAddSue.setVisible(true);
    }
    
    private void initUpdateWindow(Claim cla, Policy pol) {
    	List<String> status = new ArrayList<String>();
    	status.add("Approved");
    	status.add("Not Approved");
        ObservableList<String> obList = FXCollections.observableList(status);
        cmbStatus.setPromptText(cla.status);
    	cmbStatus.setItems(obList);
    	lblCustomerID.setText(pol.id);
    	lblFullName.setText(pol.firstName+" "+pol.lastName);
    	lblInsType.setText(pol.type);
    	btnSue.setText("Update");
    	tbAmount.setText(cla.amount);
    	taRemarks.setText(cla.remarks);
    	this.claim = cla;
    	btnDelete.setVisible(true);
    	pAddSue.setVisible(true);
    }
    
    boolean AreFieldsComplete()
    {
    	if(tbAmount.getText() == "" || cmbStatus.getValue() == null || taRemarks.getText() == "")
    		return false;
    	return true;
    }

}
