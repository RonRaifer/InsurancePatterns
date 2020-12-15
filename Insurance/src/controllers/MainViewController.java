package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainViewController {

    @FXML
    private AnchorPane apMainView;

    @FXML
    private Button btCarIns;

    @FXML
    private Button btApartmentIns;

    @FXML
    private Button btLifeIns;

    @FXML
    private Button btHealthIns;

    @FXML
    private Button btViewPurchases;

    @FXML
    private Button btSue;

    @FXML
    void ApartmentInsurance_btnClick(ActionEvent event) {
    	System.out.print("Apartment CLicked");
    }

    @FXML
    void CarInsurance_btnClick(ActionEvent event) {

    }

    @FXML
    void HealthInsurance_btnClick(ActionEvent event) {

    }

    @FXML
    void LifeInsurance_btnClick(ActionEvent event) {

    }

    @FXML
    void Sue_btnClick(ActionEvent event) {

    }

    @FXML
    void ViewPurchases_btnClick(ActionEvent event) {

    }

}
