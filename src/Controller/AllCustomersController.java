/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DBAccess.DAOImplAppointment;
import DBAccess.DAOImplCustomer;
import Model.Appointment;
import Model.Customer;
import Utility.Utility;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abenezertsegaye
 */
public class AllCustomersController implements Initializable {

    Stage stage;
    Parent scene;
    private static Customer currentCust;
    private static Appointment currentAppointment;

    @FXML
    private TableView<Customer> AllCustomersTable;

    @FXML
    private TableColumn<Customer, Integer> ID;

    @FXML
    private TableColumn<Customer, String> phone;
    @FXML
    private TableColumn<Customer, String> Name;

    @FXML
    private TableColumn<Customer, String> Address;

    @FXML
    private TableColumn<Customer, String> ZipCode;
    
    @FXML
    private TableColumn<Customer, String> Division;

    @FXML
    private TableColumn<Customer, String> Country_ID;

    @FXML
    void OnActionBackToLogin(ActionEvent event) throws IOException {
        transferScreen(event, "/View/LoginView.fxml");
    }

    @FXML
    void OnActionAddCust(ActionEvent event) throws IOException {

        transferScreen(event, "/View/AddCustomer.fxml");
        clearScreen();
    }

    @FXML
    void OnActionDeleteCust(ActionEvent event) {
        DAOImplCustomer cust = new DAOImplCustomer();
        DAOImplAppointment app = new DAOImplAppointment();
        Appointment appointment = new Appointment();
        appointment = app.findById(AllCustomersTable.getSelectionModel().getSelectedItem().getCust_ID());
        if (appointment == null) {
            cust.delete(AllCustomersTable.getSelectionModel().getSelectedItem());
            AllCustomersTable.getItems().clear();
            AllCustomersTable.setItems(cust.findAll());
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setContentText("Please delete appointment before deleting customer");
            alert.setHeaderText("Error");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                System.out.println("Works");
            }

        }

    }

    @FXML
    void OnActionUpdateCust(ActionEvent event) throws IOException {
        currentCust = AllCustomersTable.getSelectionModel().getSelectedItem();
        setModifiedCustomer(currentCust);
        transferScreen(event, "/View/UpdateCustomer.fxml");

    }

    @FXML
    void OnActionDisplayAppointment(ActionEvent event) throws IOException {
        // DAOImplAppointment app = new DAOImplAppointment();

        // Appointment objApp = new Appointment();
        currentCust = AllCustomersTable.getSelectionModel().getSelectedItem();
        setModifiedCustomer(currentCust);
        // DisplayCustomerAppointmentController display = new DisplayCustomerAppointmentController();
        // display.setCust(currentCust); 
        // objApp = app.findById(currentCust.getCust_ID());
        // app.findAllAppointmentForOneCustomer(currentCust.getCust_ID());
        /*
        if(app.findById(currentCust.getCust_ID()) == null){
        DisplayCustomerAppointmentController.addSingleCustomerAppointment(objApp);
        }else{
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure");
            alert.setTitle("Error");
            alert.setContentText("Please select a customer to add an appointment");
            alert.setHeaderText("Waring");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                System.out.println("WOW");
        }
        }
         */
        transferScreen(event, "/View/DisplayCustomerAppointment.fxml");
    }

    @FXML
    void OnActionShowAllAppointment(ActionEvent event) throws IOException {
        transferScreen(event, "/View/DisplayAppointment.fxml");
    }

    @FXML
    void OnActionUpdateAppointment(ActionEvent event) {
        
    }

    @FXML
    void OnActionDeleteAppointment(ActionEvent event) {
        System.out.println("YES");
    }

    @FXML
    void OnActionAddAppointment(ActionEvent event) throws IOException {
        currentCust = AllCustomersTable.getSelectionModel().getSelectedItem();

        if (currentCust != null) {
            setModifiedCustomer(currentCust);
            transferScreen(event, "/View/AddAppointment.fxml");
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure");
            alert.setTitle("Error");
            alert.setContentText("Please select a customer to add an appointment");
            alert.setHeaderText("Waring");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                System.out.println("WOW");
            }

        }
    }

    public void transferScreen(ActionEvent event, String transferScreen) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(transferScreen));
        stage.setScene(new Scene(scene));
        stage.show();
        clearScreen();

    }

    public void clearScreen() {
        AllCustomersTable.getItems().clear();
    }

    public void setModifiedCustomer(Customer modifyCustomer) {
        AllCustomersController.currentCust = modifyCustomer;
    }

    public void setAppointmentObj(Appointment app) {
        DisplayCustomerAppointmentController.addSingleCustomerAppointment(app);
    }

    public static Customer getCustomer() {
        return currentCust;
    }
    
    public static Appointment getAppointment(){
    return currentAppointment;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setModifiedCustomer(null);

        Name.setCellValueFactory(new PropertyValueFactory<>("CustName"));
        ID.setCellValueFactory(new PropertyValueFactory<>("Cust_ID"));
        Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        ZipCode.setCellValueFactory(new PropertyValueFactory<>("ZipCode"));
        phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        Country_ID.setCellValueFactory(new PropertyValueFactory<>("Country_ID"));
        Division.setCellValueFactory(new PropertyValueFactory<>("Division_ID"));
        DAOImplCustomer d = new DAOImplCustomer();
        AllCustomersTable.setItems(d.findAll());
    }

}
