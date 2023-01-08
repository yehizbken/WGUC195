/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DBAccess.DTO;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author abenezertsegaye
 */
public class User implements DTO {
    private int User_ID;
    private String User_Name;
    private String Password;
    private LocalDateTime Create_Date;
    private Timestamp Last_Update;

    public User(int User_ID, String User_Name, String Password, LocalDateTime Create_Date, Timestamp Last_Update) {
        this.User_ID = User_ID;
        this.User_Name = User_Name;
        this.Password = Password;
        this.Create_Date = Create_Date;
        this.Last_Update = Last_Update;
    }
     
    
    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int User_ID) {
        this.User_ID = User_ID;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String User_Name) {
        this.User_Name = User_Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    public void setCreate_Date(LocalDateTime Create_Date) {
        this.Create_Date = Create_Date;
    }

    public Timestamp getLast_Update() {
        return Last_Update;
    }

    public void setLast_Update(Timestamp Last_Update) {
        this.Last_Update = Last_Update;
    }

    
    
    @Override
    public long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
