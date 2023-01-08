/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import Model.Customer;
import Model.Division;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author abenezertsegaye
 */

public class DAOImplDivision extends DAO<Division> {
 private static ObservableList<Division> allDivision = FXCollections.observableArrayList();
 private static ObservableList<Division> filteredDivisionList = FXCollections.observableArrayList();
 
 private final String ALL = "SELECT * FROM first_level_divisions";
 private final String FIND = "SELECT * FROM first_level_divisions WHERE Division_ID = ?";
 private final String FILTER = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";
 private Division division;
 
    @Override
    public ObservableList<Division> findAll() {
        try{
          Connection conn = JDBC.getConnection();
          PreparedStatement ps = conn.prepareStatement(ALL);
          ResultSet rs = ps.executeQuery();
          while(rs.next()){
           int divison_id = rs.getInt(1);
           String country = rs.getString(2);
           int country_id = rs.getInt(7);
           Division d = new Division(divison_id,country,country_id);
           allDivision.add(d);
          }
        }catch(Exception e){
        e.printStackTrace();
        }
       return allDivision;
    }

    @Override
    public Division findById(int id) {
       try{
        Connection conn = JDBC.getConnection();
        PreparedStatement ps = conn.prepareStatement(FIND);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
          int divison_id = rs.getInt(1);
           String country = rs.getString(2);
           int country_id = rs.getInt(7);
           Division d = new Division(divison_id,country,country_id);
           setDivision(d);
        }
       }catch(Exception e){
         e.printStackTrace();
       }
     return getDivision();
    }

    @Override
    public Division update(Division dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Division create(Division dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Division dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }
    
    public ObservableList<Division> filteredDivision(int countryID){
     try{
         Connection conn = JDBC.getConnection();
         PreparedStatement ps = conn.prepareStatement(FILTER);
         ps.setInt(1, countryID);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
         int division_id = rs.getInt(1);
         String division = rs.getString(2);
         int country_id = rs.getInt(7);
         Division d = new Division(division_id,division,country_id);
         filteredDivisionList.add(d);
         }
         
     }catch(Exception e){
     e.printStackTrace();
     }
    
    return filteredDivisionList;
    }
    
    
}
