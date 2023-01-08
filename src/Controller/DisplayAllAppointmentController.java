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
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
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
public class DisplayAllAppointmentController implements Initializable {

    private Stage stage;
    private Parent scene;
    private static Appointment appointment;
    private static Customer customer;
    private ObservableList<Appointment> date = FXCollections.observableArrayList();
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
    private TableColumn<Timestamp, Appointment> create_Date_Col;

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

    @FXML
    void OnActionBackToMainScreen(ActionEvent event) throws IOException {

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
        displayAppointmentTableView.setItems(daoimplApp1.findAll());
    }

    @FXML
    void OnActionViewInWeeksMonth(ActionEvent event) {
        displayInMonthAndWeek();
    }

    public void displayInMonthAndWeek() {

        displayAppointmentTableView.getItems().clear();
         Appointment app;

        if (toggleViewInMonths.isSelected()) {
            DAOImplAppointment daoApp = new DAOImplAppointment();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
           
            app = daoApp.findAll().get(1);
            int appointment_id = app.getAppointment_ID();
            String title = app.getTitle();
            String description = app.getDescription();
            String location = app.getLocation();
            String type = app.getType();
            LocalDateTime start = app.getStart_Time();
            LocalDateTime end = app.getEnd_Time();
            Timestamp create_date = app.getCreate_Date();
          //  LocalDateTime ldt = LocalDateTime.of(create_date, start);
            
          //  String format = formatter.format(ldt);
           // LocalDate u = LocalDate.parse("201806", formatter);
          //  LocalDateTime change = LocalDateTime.parse(format,formatter);
           // LocalDate ldd = u.toLocalDate();
           LocalDateTime u = LocalDateTime.now();
           Timestamp new_create_date = Timestamp.valueOf(u);

            String created_by = app.getCreated_By();
            Timestamp last_update = app.getLast_Update();
            Appointment app1 = new Appointment(appointment_id, title, description, location, type, start, end, new_create_date, created_by, last_update);
            date.add(app1);
            displayAppointmentTableView.setItems(date);
            /*
            for (int i = 0; daoApp.findAll().size() < i;i++){
            }
            /*
            daoApp.viewInWeeksOrMonths().get(1);
            LocalDate ld = daoApp.viewInWeeksOrMonths().get(1).getCreate_Date();
            String text = ld.format(formatter);
            LocalDate parsedDate = LocalDate.parse(text, formatter);
            displayAppointmentTableView.setItems(date);
            /*
            displayAppointmentTableView.getItems().clear();
            DAOImplAppointment daoApp = new DAOImplAppointment();
            LocalDate ld = daoApp.viewInWeeksOrMonths().get(1).getCreate_Date();
            int year = daoApp.viewInWeeksOrMonths().get(1).getCreate_Date().getYear();
            int date2 = daoApp.viewInWeeksOrMonths().get(1).getCreate_Date().getDayOfMonth();
            Month month = daoApp.viewInWeeksOrMonths().get(1).getCreate_Date().getMonth();
            monthdate = LocalDate.of(year, month, date2);
             */
        }
         

    }

    @FXML
    void OnActionUpdateAppointment(ActionEvent event) throws IOException {
        DAOImplCustomer cust = new DAOImplCustomer();
        Appointment app;
        app = displayAppointmentTableView.getSelectionModel().getSelectedItem();
        setAppointment(app);
        cust.findById(app.getCustomer_ID());
        System.out.println(DAOImplCustomer.getCustomer().getCustName());
        //setCustomer(cust.findById(app.getCustomer_ID()));
        //setModifiedAppointment(app);
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/UpdateAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
    }

    public static Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
    
    public void setModifiedAppointment(Appointment modifyappointment) {
        DisplayAllAppointmentController.appointment = modifyappointment;
    }

    public static Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        DisplayAllAppointmentController.customer = customer;
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

        DAOImplAppointment daoimplApp = new DAOImplAppointment();
        displayAppointmentTableView.setItems(daoimplApp.findAll());

    }

}
