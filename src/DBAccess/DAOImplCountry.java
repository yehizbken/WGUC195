/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import Model.Country;
import Model.Customer;
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
public class DAOImplCountry extends DAO<Country> {
  private static ObservableList<Country> allCountry = FXCollections.observableArrayList();
  private final String ALL = "SELECT * FROM countries";
  //private final String FIND = "SELECT * FROM countries WHERE "
  
    @Override
    public ObservableList<Country> findAll() {
        try{
        Connection conn = JDBC.getConnection();
        PreparedStatement ps = conn.prepareStatement(ALL);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
         int country_ID = rs.getInt(1);
         String country = rs.getString(2);
         Country c = new Country(country_ID,country);
         allCountry.add(c);

        } 
        }catch(Exception e){e.printStackTrace();}
        return allCountry;
        
    }

    @Override
    public Country findById(int division_id) {
        try{
        Connection conn = JDBC.getConnection();
        PreparedStatement ps = conn.prepareStatement(ALL);
        ResultSet rs = ps.executeQuery(); 
        
        }catch(Exception e){
        
        
        }
      return null;
    }

    @Override
    public Country update(Country dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Country create(Country dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Country dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
