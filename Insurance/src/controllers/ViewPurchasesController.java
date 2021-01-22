package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;

import infrastructures.Dao.PolicyDao;
import infrastructures.Factories.Policy;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    	idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    	nameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    	lastCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    	typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
    	dateCol.setCellValueFactory(new PropertyValueFactory<>("startDay"));
    	remarksCol.setCellValueFactory(new PropertyValueFactory<>("remarks"));
	
    	optionsCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
    	optionsCol.setCellFactory(param -> new TableCell<Policy, Policy>() { 
    		@FXML
		    private final Hyperlink hlUpdate = new Hyperlink("Update/Delete");
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
    	list = FXCollections.observableArrayList(PolicyDao.GetInstance().getAll());
    	tblPurchases.setItems(list);
    }
    
    @FXML
    void Cancel_btnClick(ActionEvent event) {
    	pUpdate.setVisible(false);
    }

    @FXML
    void Update_btnClick(ActionEvent event) {

    }
    
    @FXML
    void Delete_btnClick(ActionEvent event) {
    	try {
			PolicyDao.GetInstance().delete(policy);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

}
