/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DBAccess.DAOImplCountry;
import DBAccess.DAOImplDivision;
import DBAccess.DAOImplCustomer;
import Model.Country;
import java.net.URL;
import Model.Customer;
import Model.Division;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddCustomerController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField AddCustNameTxt;

    @FXML
    private TextField AddCustAddressTxt;

    @FXML
    private TextField AddCustPostalCodeTxt;

    @FXML
    private TextField AddCustPhoneTxt;

    @FXML
    private ComboBox<Division> CustStateComboBox;

    @FXML
    private ComboBox<Country> CustCountryComboBox;

    @FXML
    void OnActionAddCustomer(ActionEvent event) throws IOException {

        String name = AddCustNameTxt.getText();
        String address = AddCustAddressTxt.getText();
        String postal = AddCustPostalCodeTxt.getText();
        String phone = AddCustPhoneTxt.getText();
        DAOImplDivision d1 = new DAOImplDivision();
        DAOImplCustomer.setDivision(CustStateComboBox.getSelectionModel().getSelectedItem());
        setDivision(CustStateComboBox.getSelectionModel().getSelectedItem());
        int division_id = CustStateComboBox.getSelectionModel().getSelectedItem().getDivision_ID();
        int country_id = CustCountryComboBox.getSelectionModel().getSelectedItem().getCountry_ID();
        Customer c = new Customer(0, name, address, postal, phone,division_id,country_id);
        DAOImplCustomer d = new DAOImplCustomer();
        d.create(c);
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AllCustomers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void OnActionCancelAndReturn(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AllCustomers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        CustStateComboBox.getItems().clear();
        CustCountryComboBox.getItems().clear();
    }

    public void setDivision(Division div) {
        DAOImplCustomer.division = div;
        DAOImplDivision d2 = new DAOImplDivision();

    }

    @FXML
    void OnActionFilterDivision(ActionEvent event) {
        DAOImplDivision d1 = new DAOImplDivision();
        if (CustCountryComboBox.getSelectionModel().getSelectedItem().getCountry_ID() == 1) {
            CustStateComboBox.getItems().clear();
            CustStateComboBox.setItems(d1.filteredDivision(1));
        } else if (CustCountryComboBox.getSelectionModel().getSelectedItem().getCountry_ID() == 2) {
            CustStateComboBox.getItems().clear();
            CustStateComboBox.setItems(d1.filteredDivision(2));
        } else if (CustCountryComboBox.getSelectionModel().getSelectedItem().getCountry_ID() == 3) {
            CustStateComboBox.getItems().clear();
            CustStateComboBox.setItems(d1.filteredDivision(3));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DAOImplDivision d = new DAOImplDivision();
        DAOImplCountry c = new DAOImplCountry();

        CustStateComboBox.setItems(d.findAll());
        CustCountryComboBox.setItems(c.findAll());

    }
}
