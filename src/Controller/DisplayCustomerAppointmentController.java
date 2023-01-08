/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DBAccess.DAOImplAppointment;
import Model.Appointment;
import Model.Customer;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abenezertsegaye
 */
public class DisplayCustomerAppointmentController implements Initializable {

    private Customer cust;
    private static ObservableList<Appointment> singleCustomerAppointment = FXCollections.observableArrayList();
    private Parent scene;
    private Stage stage;
    @FXML
    private TableView<Appointment> displayAppointmentTableView;

    @FXML
    private TableColumn<Integer, Appointment> id_col;

    @FXML
    private TableColumn<String, Appointment> title_Col;

    @FXML
    private TableColumn<String, Appointment> description_Col;

    @FXML
    private TableColumn<String, Appointment> location_Col;

    @FXML
    private TableColumn<String, Appointment> type_Col;

    @FXML
    private TableColumn<LocalTime, Appointment> start_Col;

    @FXML
    private TableColumn<LocalTime, Appointment> end_Col;

    @FXML
    private TableColumn<LocalDate, Appointment> create_Date_Col;

    @FXML
    private TableColumn<String, Appointment> created_By_Col;

    @FXML
    private TableColumn<Timestamp, Appointment> last_Updated_Col;

    @FXML
    private RadioButton toggleViewInMonths;

    @FXML
    private ToggleGroup changeView;

    @FXML
    private RadioButton toggleViewInWeeks;
    private Boolean trye;
    public DisplayCustomerAppointmentController() {
        this.cust = AllCustomersController.getCustomer();
    }

    public static ObservableList<Appointment> getSingleCustomerAppointment() {
        return singleCustomerAppointment;
    }

    public static void addSingleCustomerAppointment(Appointment singleCustomerAppointment1) {
        singleCustomerAppointment.add(singleCustomerAppointment1);
    }

    @FXML
    void OnActionBackToMainScreen(ActionEvent event) throws IOException {
        displayAppointmentTableView.getItems().clear();
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AllCustomers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void OnActionDeleteAppointment(ActionEvent event) {
        DAOImplAppointment daoimplApp1 = new DAOImplAppointment();
        Appointment app = new Appointment();
        app = displayAppointmentTableView.getSelectionModel().getSelectedItem();
        daoimplApp1.delete(app);
        displayAppointmentTableView.getItems().clear();
        displayAppointmentTableView.setItems(daoimplApp1.findAllAppointmentForOneCustomer(cust.getCust_ID()));
    }

    @FXML
    void OnActionUpdateAppointment(ActionEvent event) {

    }

    public Customer getCust() {
        return cust;
    }

    public void setCust(Customer cust) {
        this.cust = cust;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DAOImplAppointment app = new DAOImplAppointment();
        displayAppointmentTableView.getItems().clear();

        id_col.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        title_Col.setCellValueFactory(new PropertyValueFactory<>("Title"));
        description_Col.setCellValueFactory(new PropertyValueFactory<>("Description"));
        location_Col.setCellValueFactory(new PropertyValueFactory<>("Location"));
        type_Col.setCellValueFactory(new PropertyValueFactory<>("Type"));
        start_Col.setCellValueFactory(new PropertyValueFactory<>("Start_Time"));
        end_Col.setCellValueFactory(new PropertyValueFactory<>("End_Time"));
        create_Date_Col.setCellValueFactory(new PropertyValueFactory<>("Create_Date"));
        created_By_Col.setCellValueFactory(new PropertyValueFactory<>("Created_By"));
        last_Updated_Col.setCellValueFactory(new PropertyValueFactory<>("Last_Update"));

        displayAppointmentTableView.setItems(app.findAllAppointmentForOneCustomer(cust.getCust_ID()));
    }

}
