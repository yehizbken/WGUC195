/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DBAccess.DAOImplCountry;
import DBAccess.DAOImplCustomer;
import DBAccess.DAOImplDivision;
import Model.Country;
import java.net.URL;
import Model.Customer;
import Model.Division;
import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abenezertsegaye
 */
public class UpdateCustomerController implements Initializable {

    private Customer updateCustomer;
    private Stage stage;
    private Parent scene;

    @FXML
    private TextField updateIDTxt;

    @FXML
    private TextField updateNameTxt;

    @FXML
    private TextField updateAddressTxt;

    @FXML
    private TextField updatePostalTxt;

    @FXML
    private TextField updatePhoneTxt;

    @FXML
    private ComboBox<Division> updateComboState;

    @FXML
    private ComboBox<Country> updateComboCountry;

    @FXML
    void OnActionCancelUpdateCustomer(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setContentText("Warning about to revert back to the main screen. All information will be cleared. ");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
           transferScreen(event, ("/View/AllCustomers.fxml"));
        }
    }

    @FXML
    void OnActionUpdateCustomer(ActionEvent event) throws IOException {
        String updateName = updateNameTxt.getText();
        String updateAddress = updateAddressTxt.getText();
        String updatePhone = updatePhoneTxt.getText();
        String updatePostal = updatePostalTxt.getText();
        Customer updatedCustomer = new Customer(updateCustomer.getCust_ID(), updateName,
              updateAddress, updatePostal, updatePhone,updateCustomer.getDivision_ID(),updateCustomer.getCountry_ID());
        setDivision(updateComboState.getSelectionModel().getSelectedItem());
        DAOImplCustomer c = new DAOImplCustomer();

        c.update(updatedCustomer);
        updateComboState.getItems().clear();
        updateComboCountry.getItems().clear();
        transferScreen(event, "/View/AllCustomers.fxml");
    }

    @FXML
    void OnActionFilterDivision(ActionEvent event) {
        DAOImplDivision d1 = new DAOImplDivision();
        if (updateComboCountry.getSelectionModel().getSelectedItem().getCountry_ID() == 1) {
            updateComboState.getItems().clear();
            updateComboState.setItems(d1.filteredDivision(1));
        } else if (updateComboCountry.getSelectionModel().getSelectedItem().getCountry_ID() == 2) {
            updateComboState.getItems().clear();
            updateComboState.setItems(d1.filteredDivision(2));
        } else if (updateComboCountry.getSelectionModel().getSelectedItem().getCountry_ID() == 3) {
            updateComboState.getItems().clear();
            updateComboState.setItems(d1.filteredDivision(3));
        }
    }

    public UpdateCustomerController() {
        this.updateCustomer = AllCustomersController.getCustomer();
    }

    public void setDivision(Division div) {
        DAOImplCustomer.division = div;
    }

    public void transferScreen(ActionEvent event, String transferScreen) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(transferScreen));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateComboState.getItems().clear();
        updateComboCountry.getItems().clear();
        updateIDTxt.setDisable(true);
        updateIDTxt.setText(Integer.toString(updateCustomer.getCust_ID()));
        updateNameTxt.setText(updateCustomer.getCustName());
        updateAddressTxt.setText(updateCustomer.getAddress());
        updatePhoneTxt.setText(updateCustomer.getPhone());
        updatePostalTxt.setText(updateCustomer.getZipCode());
        DAOImplDivision d = new DAOImplDivision();
        DAOImplCountry c = new DAOImplCountry();
        updateComboState.setItems(d.findAll());
        updateComboCountry.setItems(c.findAll());

    }

}
