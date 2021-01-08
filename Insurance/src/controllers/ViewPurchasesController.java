package controllers;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import infrastructures.Dao.PolicyDao;
import infrastructures.Factories.Policy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    
    ObservableList<Policy> list = FXCollections.observableArrayList();
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	setAllPurchasesTable();
    }
    public void setAllPurchasesTable() {
    	idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    	nameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    	lastCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    	typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
    	dateCol.setCellValueFactory(new PropertyValueFactory<>("startDay"));
    	remarksCol.setCellValueFactory(new PropertyValueFactory<>("remarks"));
	
    	list = FXCollections.observableArrayList(PolicyDao.GetInstance().getAll());
    	tblPurchases.setItems(list);
    }

}
