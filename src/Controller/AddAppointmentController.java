/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DBAccess.DAOImplContact;
import DBAccess.DAOImplAppointment;
import Model.Appointment;
import Model.Contact;
import Model.Customer;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abenezertsegaye
 */
public class AddAppointmentController implements Initializable {

    Stage stage;
    Parent scene;
    private static Customer customer;
    private ZonedDateTime zdt;

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

    private int differenceInHour;

    private final ObservableList<LocalTime> hours = FXCollections.observableArrayList();

    @FXML
    void OnActionAddAppointment(ActionEvent event) throws IOException {

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
        Appointment appointment = new Appointment(0, title, description, location, type, startDateTime, endDateTime, timestamp, customer.getCustName(), timestamp);
        // if (appointment != null)
        app.create(appointment);
        addAppointmentContactCombo.getItems().clear();
        addAppointmentStartTime.getItems().clear();
        addAppointmentEndTime.getItems().clear();
        transferScreen(event, "/View/DisplayAppointment.fxml");

    }

    @FXML
    void OnActionCancelAppointment(ActionEvent event) throws IOException {
        addAppointmentContactCombo.getItems().clear();
        addAppointmentStartTime.getItems().clear();
        addAppointmentEndTime.getItems().clear();
        showErrorCancel("Cancel", "Do you want to cancel adding an appointment? All entry will be lost.", "", event,
                "/View/AllCustomers.fxml");

    }

    public Boolean checkIfInputsEmpty(Contact contact, LocalTime start_time, LocalTime end_time) throws IOException {
        if (addAppointmentTitleTxt.getText().isEmpty() || addAppointmentDescriptionTxa.getText().isEmpty() || addAppointmentLocation.getText().isEmpty()
                || addAppointmentType.getText().isEmpty() || contact == null || start_time == null || end_time == null) {
            showErrorTime("Warning", "Cannot leave an empty field. Please fill all the items", "Error");
            return true;
        } else {
            return false;
        }
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

    public void showErrorCancel(String headerText, String contentText, String titleText, ActionEvent event, String transferScreen) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titleText);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            addAppointmentContactCombo.getItems().clear();
            addAppointmentStartTime.getItems().clear();
            addAppointmentEndTime.getItems().clear();
            transferScreen(event, transferScreen);
        }
    }

    public Boolean checkTimeOverlap(LocalTime startTime, LocalTime endTime) throws IOException {
        if (endTime.isBefore(startTime)) {
            showErrorTime("Error", "The end time can not be before the start time", "");
            return true;
        } else {
            return false;
        }

    }

    public AddAppointmentController() {
        this.customer = AllCustomersController.getCustomer();

    }

    public Boolean checkTimeZoneConversionForOfficeHours(ZonedDateTime startTimeDate, ZonedDateTime endTimeDate) throws IOException {

        int diffInHours = displayTimeDifference(startTimeDate);
        if (startTimeDate.getHour() < 8 || startTimeDate.getHour() > 10 || endTimeDate.getHour() < 8 || endTimeDate.getHour() > 10) {
            showErrorTime("Error", "Please choice office Hours between office hours. Office hours are between 08:00 AM EST and 10:00 PM EST"
                    + "The difference in hours between your timezone and the office time is " + diffInHours, "");
            return true;
        } else {
            return false;
        }

    }

    public void showErrorTime(String headerText, String contentText, String titleText) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titleText);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println("WOW");
        }
    }

    public int displayTimeDifference(ZonedDateTime zonedStartTime) {
        TimeZone est = TimeZone.getTimeZone("America/New_York");
        ZonedDateTime est1 = ZonedDateTime.of(zonedStartTime.toLocalDateTime(), est.toZoneId());
        if (zonedStartTime.getHour() < est1.getHour()) {
            differenceInHour = est1.getHour() - zonedStartTime.getHour();
        } else {
            differenceInHour = zonedStartTime.getHour() - est1.getHour();
        }
        return differenceInHour;
    }

    /*
   public boolean checkOverLap( LocalTime startTime, LocalTime endTime) throws IOException{
       DAOImplAppointment daoApp = new DAOImplAppointment();
       
       for(int i=0;daoApp.findAll().size() < i;i++){
          if(daoApp.findAll().get(i).getStart_Time()==startTime){
          showErrorTime("Error", "Please choose another time. There is a time overlap","Warning");
          }
          return false;
       }
        return true;
     
   }
       
       
       
       
       
       
       /*
       if(daoApp.findById(customer.getCust_ID()).getStart_Time()==startTime || daoApp.findById(customer.getCust_ID()).getEnd_Time()==endTime){
           showErrorTime("Error", "Please choose another time. There is a time overlap","Warning");
           return false;
       }else
           return true;
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addAppointmentContactCombo.getItems().clear();
        addAppointmentStartTime.getItems().clear();
        addAppointmentEndTime.getItems().clear();
        DAOImplContact c = new DAOImplContact();

        addAppointmentContactCombo.setItems(c.findAll());
        addAppointmentStartTime.setItems(addHours());
        addAppointmentEndTime.setItems(addHours());
    }

}
