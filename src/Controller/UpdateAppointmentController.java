/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DBAccess.DAOImplAppointment;
import DBAccess.DAOImplContact;
import DBAccess.JDBC;
import Model.Appointment;
import Model.Contact;
import Model.Customer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abenezertsegaye
 */
public class UpdateAppointmentController implements Initializable {
    Parent scene;
    Stage stage;
    private Appointment appointment;
    private Customer customer;
    @FXML
    private TextField addAppointmentTitleTxt;

    @FXML
    private TextArea addAppointmentDescriptionTxa;

    @FXML
    private TextField addAppointmentLocation;

    @FXML
    private ComboBox<Contact> addAppointmentContactCombo;

    @FXML
    private TextField addAppointmentType;

    @FXML
    private ComboBox<LocalTime> addAppointmentStartTime;

    @FXML
    private ComboBox<LocalTime> addAppointmentEndTime;

    @FXML
    private DatePicker addAppointmentDate;
    private final ObservableList<LocalTime> hours = FXCollections.observableArrayList();
    
    @FXML
    void OnActionAddAppointment(ActionEvent event) throws IOException, SQLException {
       
        String title = addAppointmentTitleTxt.getText();
        String description = addAppointmentDescriptionTxa.getText();
        String location = addAppointmentLocation.getText();
        Contact contact = addAppointmentContactCombo.getValue();
        String type = addAppointmentType.getText();
        LocalTime startTime = addAppointmentStartTime.getValue();
        LocalTime endTime = addAppointmentEndTime.getValue();
        LocalDate ld = addAppointmentDate.getValue();
        LocalDateTime startDateTime = LocalDateTime.of(ld, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(ld, endTime);
        ZonedDateTime zdt = ZonedDateTime.now();
        TimeZone estTimeZone = TimeZone.getTimeZone("America/New_York");

        // ZonedDateTime zonedStartTime = ZonedDateTime.of(ld, startTime, estTimeZone.toZoneId());
        //ZonedDateTime zonedEndTime = ZonedDateTime.of(ld, endTime, estTimeZone.toZoneId());
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);

        //if (checkTimeOverlap(startTime, endTime) || checkIfInputsEmpty(contact, startTime, endTime)) {
        // }else{
        LoginController lc = new LoginController();
        DAOImplAppointment app = new DAOImplAppointment(customer, lc.getUser(), contact);
        Appointment appne = new Appointment(1, title, description, location, type, startDateTime, endDateTime, timestamp, "sgs", timestamp, 2 , 2, 1);
        // if (appointment != null)
        app.update(appne);
        addAppointmentContactCombo.getItems().clear();
        addAppointmentStartTime.getItems().clear();
        addAppointmentEndTime.getItems().clear();
        transferScreen(event, "/View/DisplayAppointment.fxml");

    }

    @FXML
    void OnActionCancelAppointment(ActionEvent event) {

    }
   public UpdateAppointmentController(){
    this.appointment = DisplayAllAppointmentController.getAppointment();
    this.customer = DisplayAllAppointmentController.getCustomer();
    System.out.println(appointment.getCreated_By());
    
    }
    
    public void transferScreen(ActionEvent event, String transferScreen) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(transferScreen));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    private ObservableList<LocalTime> addHours() {
        for (int h = 0; h < 14; h++) {

            for (int m = 0; m < 4; m++) {
                hours.add(LocalTime.of(h + 8, 15 * m));

            }

        }
        return hours;
    }

    public Customer getCustomer() {
        return customer;
    }

    public  void setCustomer(Customer customer) {
        this.customer = customer;
    }

  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        DAOImplContact c = new DAOImplContact();
        
        addAppointmentContactCombo.setItems(c.findAll());
        addAppointmentStartTime.setItems(addHours());
        addAppointmentEndTime.setItems(addHours());
        addAppointmentTitleTxt.setText(appointment.getTitle());
        addAppointmentDescriptionTxa.setText(appointment.getDescription());
        addAppointmentLocation.setText(appointment.getLocation());
        //addAppointmentContactCombo.setValue(c.findById(appointment.getContact_ID()));
        addAppointmentType.setText(appointment.getType());
       // LocalTime startTime = addAppointmentStartTime.getValue();
       // LocalTime endTime = addAppointmentEndTime.getValue();
       //addAppointmentDate.set appointment.getStart_Time().toLocalDate()
    }    
    
}
