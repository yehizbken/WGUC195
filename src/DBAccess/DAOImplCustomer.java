/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import Model.Customer;
import Model.Division;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author abenezertsegaye
 */
public class DAOImplCustomer extends DAO<Customer>  {

    private static ObservableList<Customer> allCustomer = FXCollections.observableArrayList();
    ObservableList<Customer> findCustomer = FXCollections.observableArrayList();
     private final String INSERT = "INSERT INTO customers(Customer_ID, Customer_Name, Address,Postal_Code,Phone,Division_ID) VALUES (?,?,?,?,?,?)";
     private final String DELETE = "DELETE FROM customers WHERE Customer_ID = ? " ;
     private final String ALL = "SELECT * FROM Customers";
     private final String FIND = "SELECT * FROM customers WHERE Customer_ID = ?";
     private final String UPDATE = "UPDATE customers SET Customer_ID = ?, Customer_Name=?, Address=?,Postal_Code=?,Phone=?,Division_ID=? WHERE Customer_ID=?";
             
    public static Division division; 
    public static Customer customer;

    public DAOImplCustomer() {
    }
     
  
  
    @Override
    public ObservableList<Customer> findAll() {
        DAOImplDivision di = new DAOImplDivision();
        
        try {
            Connection conn = JDBC.getConnection();
            PreparedStatement ps = conn.prepareStatement(ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int custId = rs.getInt(1);
                String CustName = rs.getString(2);
                String Address = rs.getString(3);
                String ZipCode = rs.getString(4);
                String phone = rs.getString(5);
                int division_id = rs.getInt(10);
                int country_id = di.findById(division_id).getCountry_ID();
                Customer c = new Customer(custId,CustName,Address,ZipCode,phone,division_id,country_id);
                allCustomer.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
           
        }
        return allCustomer;
    }

    @Override
    public Customer findById(int CustomerID) {

        Customer c1 = null;
        DAOImplDivision di = new DAOImplDivision();
        try {
            Connection conn = JDBC.getConnection();
            PreparedStatement ps = conn.prepareStatement(FIND);
            ps.setInt(1, CustomerID);
           ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               int custId = rs.getInt(1);
                String CustName = rs.getString(2);
                String Address = rs.getString(3);
                String ZipCode = rs.getString(4);
                String phone = rs.getString(5);
                int division_id = rs.getInt(10);
               int country_id = di.findById(division_id).getCountry_ID();
               Customer c = new Customer(custId,CustName,Address,ZipCode,phone,division_id,country_id);
                customer = c;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOImplCustomer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return c1;
    }

    @Override
    public Customer update(Customer cust) {
      try{
       Connection conn = JDBC.getConnection();
       PreparedStatement ps = conn.prepareStatement(UPDATE);
       ps.setInt(1, cust.getCust_ID());
       ps.setString(2, cust.getCustName());
       ps.setString(3, cust.getAddress());
       ps.setString(4, cust.getZipCode());
       ps.setString(5, cust.getPhone());
       ps.setInt(6,division.getDivision_ID());
       ps.setInt(7, cust.getCust_ID());
      int result = ps.executeUpdate();
      if(result == 1){
      System.out.println("WORKS");
      }
      }catch(Exception e ){
      e.printStackTrace();
      }
       return null; 
    }

    @Override
    public void delete(Customer cust) {
        try {
           JDBC.openConnection();
           Connection conn = JDBC.getConnection();
           PreparedStatement ps = conn.prepareStatement(DELETE);
           ps.setInt(1, cust.getCust_ID());
           Boolean result = ps.execute();
           if (result==true);
           System.out.print("DELETED");
         
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public Customer create(Customer cust) {
       String name = cust.getCustName();
       String address = cust.getAddress();
       String zip = cust.getZipCode();
       String phone = cust.getPhone();
       
       try{
        Connection conn = JDBC.getConnection();
        PreparedStatement ps = conn.prepareStatement(INSERT);
        ps.setInt(1, 0);
        ps.setString(2, name);
        ps.setString(3, address);
        ps.setString(4, zip);
        ps.setString(5, phone);
        ps.setInt(6,division.getDivision_ID());
        ps.executeUpdate();
       
       }catch(Exception e ){
       e.printStackTrace();
       }
       return null;
       
    }

    public static Division getDivision() {
        return division;
    }

    public static void setDivision(Division division) {
        DAOImplCustomer.division = division;
    }

    public static Customer getCustomer() {
        return customer;
    }
    
    
    }

    

