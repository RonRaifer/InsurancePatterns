package controllers;

import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import infrastructures.Dao.PolicyDao;
import infrastructures.Factories.Claim;
import infrastructures.Factories.ClaimFactory;
import infrastructures.Factories.IClaimFactory;
import infrastructures.Factories.IPolicyFactory;
import infrastructures.Factories.Policy;
import infrastructures.Factories.PolicyFactory;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

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
    private Pane pAddSue;

    @FXML
    private JFXButton btnSue;

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
    
    private IClaimFactory claimFactory = new ClaimFactory();
    private Policy policy;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	spSearchResults.setVisible(false);
    	pAddSue.setVisible(false);
    }   
    
    @FXML
    void Search_btnClick(ActionEvent event) {
    	String idToSearch = tbId.getText();
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
    	String amount = tbAmount.getText();
        String status = cmbStatus.getValue();
        String remarks = taRemarks.getText();
        
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(0);
        Long sDate = date.getTime();
        String pID = policy.pID;
       //dateFormat.format(date)
        
        Claim obj = claimFactory.create(null, pID, amount, status, sDate, remarks);
        if(obj!=null)
        {
        	//POP UP MESSAGE OF SUCCESFUL ADDITION HERE	
        	PurchaseController.PUP("Successfully added!", "Confirmation");
        }
        else 
        {
        	//POP UP MESSAGE OF FAILURE ADDITION HERE
        	PurchaseController.PUP("Failure adding new SUE.\n for more details see log file", "Error");
        }
    }
    
    private void updateSearchResults(String id) {
    	List<Policy> pl = PolicyDao.GetInstance().getByID(id);
    	if(pl == null) {
    		lblError.setText("No such ID");
    	}
    	else {
    		//idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        	nameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        	lastCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        	typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        	dateCol.setCellValueFactory(new PropertyValueFactory<>("startDay"));
        	remarksCol.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        	
        	sueCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        	sueCol.setCellFactory(param -> new TableCell<Policy, Policy>() { 
				@FXML
			    private final Hyperlink hlSue = new Hyperlink("Add Sue");

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
    	pAddSue.setVisible(true);
    }

}
