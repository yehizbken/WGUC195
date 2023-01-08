/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import Model.Contact;
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
public class DAOImplContact extends DAO<Contact> {

    private static ObservableList<Contact> allContact = FXCollections.observableArrayList();
    private final String ALL = "SELECT * FROM contacts";
    private final String FIND = "SELECT * FROM contacts WHERE Contact_ID = ?";

    @Override
    public ObservableList<Contact> findAll() {
        try {
            Connection conn = JDBC.getConnection();
            PreparedStatement ps = conn.prepareStatement(ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int contact_id = rs.getInt(1);
                String contact_name = rs.getString(2);
                String email = rs.getString(3);
                Contact c = new Contact(contact_id, contact_name, email);
                allContact.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allContact;
    }

    @Override
    public Contact findById(int id) {
        Contact c= null;
        try {
            Connection conn = JDBC.getConnection();
            PreparedStatement ps = conn.prepareStatement(FIND);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            int contact_id = rs.getInt(1);
                String contact_name = rs.getString(2);
                String email = rs.getString(3);
                Contact con = new Contact(contact_id, contact_name, email);
                c = con;
            
            }
        } catch (Exception e) {

        }
        return c;
    }

    @Override
    public Contact update(Contact dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Contact create(Contact dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Contact dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
