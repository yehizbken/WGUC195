/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DBAccess.DTO;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.*;

/**
 *
 * @author abenezertsegaye
 */
public class Appointment implements DTO {
    
    private int Appointment_ID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private LocalDateTime Start_Time;
    private LocalDateTime End_Time;
    private Timestamp Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private int Customer_ID;
    private int Contact_ID;
    private int User_ID;

   // private TimeStamp Last_Updated;
   //private ZonedDateTime Start, end;

    public Appointment(int Appointment_ID, String Title, String Description, String Location, String Type, LocalDateTime Start_Time, LocalDateTime End_Time, Timestamp Create_Date, String Created_By,Timestamp Last_Update) {
        this.Appointment_ID = Appointment_ID;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Type = Type;
        this.Start_Time = Start_Time;
        this.End_Time = End_Time;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        
    }

    public Appointment(int Appointment_ID, String Title, String Description, String Location, String Type, LocalDateTime Start_Time, LocalDateTime End_Time, Timestamp Create_Date, String Created_By, Timestamp Last_Update, int Customer_ID, int Contact_ID, int User_ID) {
        this.Appointment_ID = Appointment_ID;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Type = Type;
        this.Start_Time = Start_Time;
        this.End_Time = End_Time;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Customer_ID = Customer_ID;
        this.Contact_ID = Contact_ID;
        this.User_ID = User_ID;
    }
    

    public Appointment() {
    }

    public Appointment(int i, String title, String description, String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime, String custName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setAppointment_ID(int id ){
    this.Appointment_ID= id;
    
    }

    public int getAppointment_ID() {
        return Appointment_ID;
    }
    

    public LocalDateTime getStart_Time() {
        return Start_Time;
    }

    public void setStart_Time(LocalDateTime Start_Time) {
        this.Start_Time = Start_Time;
    }

    public LocalDateTime getEnd_Time() {
        return End_Time;
    }

    public void setEnd_Time(LocalDateTime End_Time) {
        this.End_Time = End_Time;
    }

    public Timestamp getCreate_Date() {
        return Create_Date;
    }

    public void setCreate_Date(Timestamp Create_Date) {
        this.Create_Date = Create_Date;
    }

    public String getCreated_By() {
        return Created_By;
    }

    public void setCreated_By(String Created_By) {
        this.Created_By = Created_By;
    }

    public Timestamp getLast_Update() {
        return Last_Update;
    }

    public void setLast_Update(Timestamp Last_Update) {
        this.Last_Update = Last_Update;
    }

   
    
    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(int Customer_ID) {
        this.Customer_ID = Customer_ID;
    }

    public int getContact_ID() {
        return Contact_ID;
    }

    public void setContact_ID(int Contact_ID) {
        this.Contact_ID = Contact_ID;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int User_ID) {
        this.User_ID = User_ID;
    }
   
    @Override
    public long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
            
}
