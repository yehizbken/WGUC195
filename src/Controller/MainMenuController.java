/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abenezertsegaye
 */
public class MainMenuController implements Initializable {
    Parent scene;
    Stage stage; 
    
    @FXML
    void OnActionAppointmentScreen(ActionEvent event) throws IOException {
     transferScreen(event,"/View/DisplayAppointment.fxml");
    }

    @FXML
    void OnActionCustomerScreen(ActionEvent event) throws IOException {
      transferScreen(event,"/View/AllCustomers.fxml");
    }
    public void transferScreen(ActionEvent event, String transferScreen) throws IOException{
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(transferScreen));
        stage.setScene(new Scene(scene));
        stage.show();
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
