/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DBAccess.JDBC;
import Model.User;
import Utility.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abenezertsegaye
 */
public class LoginController implements Initializable {

    Stage stage;
    Parent scene;
    private static User user;

    @FXML
    private Label UserNameLabel;

    @FXML
    private TextField UserNameTXT, PasswordTXT;

    @FXML
    private Label PasswordLabel;

    @FXML
    private Label zoneIdLbl;

    @FXML
    private Label DateLbl;

    @FXML
    private Label timeLbl;
    
    @FXML
    private Button LogInSignInButton;


    private ResourceBundle rb;

    private String username, password;

    private Boolean test, test1;
    private ResourceBundle resource;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @FXML
    void OnActionSignUp(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/Customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void OnActionSignInLogin(ActionEvent event) throws IOException, SQLException {
        // transferScreen(event);

        if (checkUsernameAndPassword()) {

            transferScreen(event);
        } else {
            if (resource.getLocale().getLanguage().equals("fr")) {
                showError(resource.getString("Error"), resource.getString("Match"), "");

            } else {
                showError("error", "UserName and/or Password doesnt match", "");
            }
        }

    }

    public void showError(String headerText, String contentText, String titleText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleText);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();

    }

    public void setZoneId() {
        ZonedDateTime zdt = ZonedDateTime.now();
        String ZoneId = zdt.getZone().toString();
        zoneIdLbl.setText(ZoneId);
        //DateLbl.setText(zdt.getDayOfWeek().toString());
    }

    public void setLocale() {
        Locale myLocale = Locale.getDefault();
        if (myLocale.getLanguage().equals("fr")) {
            resource = ResourceBundle.getBundle("Utility/Bundle", myLocale);
            System.out.println(resource.getLocale().getDisplayLanguage());
            UserNameLabel.setText(resource.getString("UsernameLabel"));
            PasswordLabel.setText(resource.getString("PasswordLabel"));
            LogInSignInButton.setText(resource.getString("SignIn"));
        } else {
            System.out.println("uggh" + myLocale.getDisplayLanguage());

        }
        DateLbl.setText(myLocale.getDisplayCountry());

    }

    public void transferScreen(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public Boolean checkUsernameAndPassword() throws SQLException {

        String userName = UserNameTXT.getText().trim();
        String passWord = PasswordTXT.getText().trim();
        if (resource.getLocale().getLanguage().equals("fr")) {
            if (userName.isEmpty()) {
                showError(resource.getString("Empty"), resource.getString("UserName"), resource.getString("Alert"));
                //  return false;

            } else if (passWord.isEmpty()) {
                showError(resource.getString("Empty"), resource.getString("Password"), resource.getString("Alert"));
                //  return false;
            } else {
                return validateUserAndPassword(userName, passWord);

            }

        } else {

            if (userName.isEmpty()) {
                showError("Empty", "UserName is empty", "Alert");
                //  return false;
            } else if (passWord.isEmpty()) {
                showError("Empty", "Password is empty", "Alert");
                //  return false;
            } else {
                return validateUserAndPassword(userName, passWord);

            }

        }
        return null;
    }

    public Boolean validateUserAndPassword(String username, String password) throws SQLException {
        Boolean checker = false;
        try {
            JDBC.openConnection();
            Connection conn = JDBC.getConnection();
            String statement = "SELECT * FROM users WHERE User_Name = ? AND Password =? ";
            PreparedStatement ps = conn.prepareStatement(statement);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String username1 = rs.getString("User_Name");
                String password2 = rs.getString("Password");
                if (username1.equals(username) && password2.equals(password)) {
                    checker = true;
                    if (username.equals("test")) {
                        ZonedDateTime zdt = ZonedDateTime.now();
                        Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
                        user = new User(0, username, password, zdt.toLocalDateTime(), timestamp);
                        System.out.println("1");
                    } else {
                        ZonedDateTime zdt = ZonedDateTime.now();
                        Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
                        user = new User(0, username, password, zdt.toLocalDateTime(), timestamp);
                        System.out.println("2");
                    }
                    return checker;

                } else {
                    return checker;

                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return checker;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setLocale();
        setZoneId();

    }

}
