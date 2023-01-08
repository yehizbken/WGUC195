/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import Model.Appointment;
import Model.Contact;
import Model.Customer;
import Model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author abenezertsegaye
 */
public class DAOImplAppointment extends DAO<Appointment> {

    private static Contact contact;
    private static Customer customer;
    private static User user;
    private static ObservableList<Appointment> allAppointment = FXCollections.observableArrayList();
    private static ObservableList<Appointment> findAllAppointmentForOneCustomer = FXCollections.observableArrayList();
    private final String ALL = "SELECT * FROM appointments";
    private final String INSERT = "INSERT INTO appointments(Title,Description,Location,Type,Start,End"
            + ",Created_By,Last_Updated_By,Customer_ID, User_ID, Contact_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private final String FIND = "SELECT * FROM appointments WHERE Customer_ID = ?";
    private final String DELETE = "DELETE FROM appointments WHERE Appointment_ID = ? ";
    private final String UPDATE = "UPDATE appointments SET Title = ?,Description = ?,Location = ?,Type = ?,Start = ? ,End = ?"
            + ",Created_By = ? ,Last_Updated_By = ? ,Customer_ID = ? , User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";

    public DAOImplAppointment(Customer customer, User user, Contact contact) {
        this.customer = customer;
        this.user = user;
        this.contact = contact;
    }

    public DAOImplAppointment() {
    }

    @Override
    public ObservableList<Appointment> findAll() {
        allAppointment.clear();

        try {
            Connection conn = JDBC.getConnection();
            PreparedStatement ps = conn.prepareStatement(ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointment_id = rs.getInt(1);
                String title = rs.getString(2);
                String description = rs.getString(3);
                String location = rs.getString(4);
                String type = rs.getString(5);
                LocalDateTime start = rs.getTimestamp(6).toLocalDateTime();
                LocalDateTime end = rs.getTimestamp(7).toLocalDateTime();
                Timestamp create_date = rs.getTimestamp(8);
                String created_by = rs.getString(9);
                Timestamp last_update = rs.getTimestamp(10);
                Appointment app = new Appointment(appointment_id, title, description, location, type, start, end, create_date, created_by, last_update);
                allAppointment.add(app);

            }
        } catch (Exception e) {
            e.getCause();
            e.printStackTrace();
        }
        return allAppointment;
    }

    @Override
    public Appointment findById(int customer_id) {
        Appointment app_to_return = null;

        try {
            Connection conn = JDBC.getConnection();
            PreparedStatement ps = conn.prepareStatement(FIND);
            ps.setInt(1, customer_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointment_id1 = rs.getInt(1);
                String title = rs.getString(2);
                String description = rs.getString(3);
                String location = rs.getString(4);
                String type = rs.getString(5);
                LocalDateTime start = rs.getTimestamp(6).toLocalDateTime();
                LocalDateTime end = rs.getTimestamp(7).toLocalDateTime();
                Timestamp create_date = rs.getTimestamp(8);
                String created_by = rs.getString(9);
                Timestamp last_update = rs.getTimestamp(10);
                Appointment app = new Appointment(appointment_id1, title, description, location, type, start, end, create_date, created_by, last_update);
                app_to_return = app;
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return app_to_return;

    }

    @Override
    public Appointment update(Appointment appointment) {
        int id = appointment.getAppointment_ID();
        String title = appointment.getTitle();
        String description = appointment.getDescription();
        String location = appointment.getLocation();
        //Timestamp create_date1 = appointment.getCreate_Date();
        String type = appointment.getType();
        //LocalDateTime endLdt = appointment.getEnd_Time().atDate(create_date1);
        //Timestamp endTimeStamp = Timestamp.valueOf(endLdt);

        Timestamp start_time = Timestamp.valueOf(appointment.getStart_Time());
        Timestamp end_time = Timestamp.valueOf(appointment.getEnd_Time());
        // LocalDateTime end_time = appointment.getEnd_Time();

        String created_by = appointment.getCreated_By();
        Timestamp last_update = appointment.getLast_Update();
        int cust_id = customer.getCust_ID();
        int user_id = user.getUser_ID();
        int contact_id = contact.getContact_ID();

        try {
            Connection conn = JDBC.getConnection();
            PreparedStatement ps = conn.prepareStatement(UPDATE);
         
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, start_time);
            ps.setTimestamp(6, end_time);
            ps.setString(7, created_by);
            ps.setString(8, customer.getCustName());
            ps.setInt(9, cust_id);
            ps.setInt(10 , 1);
            ps.setInt(11, contact_id);
            //ps.setInt(12, id);
            int result = ps.executeUpdate();
           
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return null;
    }

    @Override
    public Appointment create(Appointment appointment) {
        int id = appointment.getAppointment_ID();
        String title = appointment.getTitle();
        String description = appointment.getDescription();
        String location = appointment.getLocation();
        Timestamp create_date1 = appointment.getCreate_Date();
        String type = appointment.getType();
        //LocalDateTime endLdt = appointment.getEnd_Time().atDate(create_date1);
        //Timestamp endTimeStamp = Timestamp.valueOf(endLdt);
        
        LocalDateTime lds = appointment.getStart_Time();
        Timestamp start = Timestamp.valueOf(lds);
        LocalDateTime eds = appointment.getStart_Time();
        Timestamp end = Timestamp.valueOf(eds);
        String created_by = appointment.getCreated_By();
        Timestamp last_update = appointment.getLast_Update();
        int cust_id = customer.getCust_ID();
        int user_id = user.getUser_ID();
        int contact_id = contact.getContact_ID();

        try {
            Connection conn = JDBC.getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERT);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, start);
            ps.setTimestamp(6, end);
            // ps.setString(7,end_time.toString());
            // ps.setTimestamp(7,last_update );
            //   ps.setDate(6, endLdt.toLocalDate().);
            // ps.setString(8, create_date.toString());
            ps.setString(7, created_by);
           // ps.setTimestamp(9, last_update);
            ps.setString(8, customer.getCustName());
            ps.setInt(9, cust_id);
            ps.setInt(10, 1);
            ps.setInt(11, contact_id);

            int rs = ps.executeUpdate();
            if (rs == 1) {
                System.out.println("Works");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Appointment appointment) {
        try {
            JDBC.openConnection();
            Connection conn = JDBC.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETE);
            ps.setInt(1, appointment.getAppointment_ID());
            Boolean result = ps.execute();
            if (result == true);
            System.out.print("DELETED");

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public ObservableList<Appointment> findAllAppointmentForOneCustomer(int cust_id) {

        try {
            Connection conn = JDBC.getConnection();
            PreparedStatement ps = conn.prepareStatement(FIND);
            ps.setInt(1, cust_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointment_id1 = rs.getInt(1);
                String title = rs.getString(2);
                String description = rs.getString(3);
                String location = rs.getString(4);
                String type = rs.getString(5);
                LocalDateTime start = rs.getTimestamp(6).toLocalDateTime();
                LocalDateTime end = rs.getTimestamp(7).toLocalDateTime();
                Timestamp create_date = rs.getTimestamp(8);
                String created_by = rs.getString(9);
                Timestamp last_update = rs.getTimestamp(10);
                Appointment app = new Appointment(appointment_id1, title, description, location, type, start, end, create_date, created_by, last_update);
                findAllAppointmentForOneCustomer.add(app);
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return findAllAppointmentForOneCustomer;
    }

    public ObservableList<Appointment> viewInWeeksOrMonths() {
        allAppointment.clear();

        try {
            Connection conn = JDBC.getConnection();
            PreparedStatement ps = conn.prepareStatement(ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointment_id1 = rs.getInt(1);
                String title = rs.getString(2);
                String description = rs.getString(3);
                String location = rs.getString(4);
                String type = rs.getString(5);
                LocalDateTime start = rs.getTimestamp(6).toLocalDateTime();
                LocalDateTime end = rs.getTimestamp(7).toLocalDateTime();
                Timestamp create_date = rs.getTimestamp(8);
                String created_by = rs.getString(9);
                Timestamp last_update = rs.getTimestamp(10);
                Appointment app = new Appointment(appointment_id1, title, description, location, type, start, end, create_date, created_by, last_update);
                allAppointment.add(app);
            }
        } catch (Exception e) {
            e.getCause();
            e.printStackTrace();
        }
        return allAppointment;
    }

    public static void setContact(Contact contact) {
        DAOImplAppointment.contact = contact;
    }

    public static void setCustomer(Customer customer) {
        DAOImplAppointment.customer = customer;
    }

    public static void setUser(User user) {
        DAOImplAppointment.user = user;
    }

    public static Contact getContact() {
        return contact;
    }

    public static Customer getCustomer() {
        return customer;
    }

    public static User getUser() {
        return user;
    }
    

}
