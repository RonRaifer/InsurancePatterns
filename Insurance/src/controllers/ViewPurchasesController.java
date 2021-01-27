package controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import infrastructures.Dao.ClaimDao;
import infrastructures.Dao.PolicyDao;
import infrastructures.Logger.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import models.Claim;
import models.Policy;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewPurchasesController implements Initializable{

    @FXML
    private TableView<Policy> tblPurchases;

    @FXML
    private TableColumn<Policy, String> idCol;

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
    private TableColumn<Policy, Policy> optionsCol;
    
    @FXML
    private Pane pUpdate;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private TextArea taRemarks;

    @FXML
    private Label lblPolicyID;

    @FXML
    private Label lblInsType;

    @FXML
    private Label lblFullName;

    @FXML
    private DatePicker dpDate;

    ObservableList<Policy> list = FXCollections.observableArrayList();
    Policy policy = new Policy();
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	pUpdate.setVisible(false);
    	setAllPurchasesTable();
    }
    public void setAllPurchasesTable() {
    	List<Policy> pList = PolicyDao.GetInstance().getAll();
    	if(pList == null) return;
    	idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    	nameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    	lastCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    	typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
    	dateCol.setCellValueFactory(new PropertyValueFactory<>("startDay"));
    	remarksCol.setCellValueFactory(new PropertyValueFactory<>("remarks"));
	
    	optionsCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
    	optionsCol.setCellFactory(param -> new TableCell<Policy, Policy>() { 
    		@FXML
		    private final Button hlUpdate = new Button("Update/Delete");
    		@FXML
    		private final HBox pane = new HBox(hlUpdate);
    		{
    			hlUpdate.setOnAction(
			            event -> {
			            	Policy p = getTableView().getItems().get(getIndex());
			            	initUpdateWindow(p);
			            }
			        );
    		}
    		@Override
		    protected void updateItem(Policy pol, boolean empty) {
		        super.updateItem(pol, empty);
		        setGraphic(empty ? null : pane);    
		    }
		});
    	list = FXCollections.observableArrayList(pList);
    	tblPurchases.setItems(list);
    }
    
    @FXML
    void Cancel_btnClick(ActionEvent event) {
    	pUpdate.setVisible(false);
    }

    @FXML
    void Update_btnClick(ActionEvent event) {
    	if(AreFieldsComplete()) {
    		Date startDate = new Date(dpDate.getValue().toEpochDay() * 24 * 60 * 60 * 1000); 
            
            policy.remarks = taRemarks.getText();
            policy.startDay = startDate.getTime();
        	try {
    			PolicyDao.GetInstance().update(policy);	//delete policy
    		} catch (SQLException e) {
    			PurchaseController.PUP("Failure updating policy.\n for more details see log file", "Error");
    			Logger.GetInstance().log(e.getMessage());
    			pUpdate.setVisible(false);
    		}
        	PurchaseController.PUP("Successfully updated!", "Confirmation");
        	pUpdate.setVisible(false);
        	setAllPurchasesTable();
    	}
    }
    
    @FXML
    void Delete_btnClick(ActionEvent event) {
    	try {
    		List<Claim> claims = ClaimDao.GetInstance().getByID(policy.pID); //get all claims with this policy id
    		if(claims != null)
	    		for (Claim c : claims) {
	    			ClaimDao.GetInstance().delete(c);	//delete claim for this policy id
	    		}
			PolicyDao.GetInstance().delete(policy);	//delete policy
		} catch (SQLException e) {
			PurchaseController.PUP("Failure deleting policy.\n for more details see log file", "Error");
			Logger.GetInstance().log(e.getMessage());
			pUpdate.setVisible(false);
		}
    	PurchaseController.PUP("Successfully deleted!", "Confirmation");
    	pUpdate.setVisible(false);
    	setAllPurchasesTable();
    }
    
    private void initUpdateWindow(Policy p) {
    	policy = p;
    	taRemarks.setText(p.remarks);
        lblPolicyID.setText(p.pID);
        lblInsType.setText(p.type);
        lblFullName.setText(p.firstName +" "+p.lastName);
        dpDate.setValue(p.getStartDay().toLocalDate());
        pUpdate.setVisible(true);
    }
    
    boolean AreFieldsComplete()
    {
    	if(dpDate.getValue() == null || taRemarks.getText() == "" )
    		return false;
    	return true;
    }

}
