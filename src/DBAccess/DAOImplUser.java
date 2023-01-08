/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import Model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author abenezertsegaye
 */
public class DAOImplUser extends DAO<User> {

    private static ObservableList<User> allUser = FXCollections.observableArrayList();
    private final String ALL = "SELECT * FROM users";
    private final String FIND = "SELECT * FROM users WHERE User_ID = ?";

    @Override
    public List<User> findAll() {
        try {
            Connection conn = JDBC.getConnection();
            PreparedStatement ps = conn.prepareStatement(ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int user_id = rs.getInt(1);
                String user_name = rs.getString(2);
                String password = rs.getString(3);
                LocalDateTime create_date = (LocalDateTime) rs.getObject(4);
                Timestamp last_updated = rs.getTimestamp(6);
                User u = new User(user_id, user_name, password, create_date, last_updated);
                allUser.add(u);

            }

        } catch (Exception e) {

        }
        return allUser;
    }

    @Override
    public User findById(int id) {
        User user = null;
        try {
            Connection conn = JDBC.getConnection();
            PreparedStatement ps = conn.prepareStatement(FIND);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            int user_id = rs.getInt(1);
                String user_name = rs.getString(2);
                String password = rs.getString(3);
                LocalDateTime create_date = (LocalDateTime) rs.getObject(4);
                Timestamp last_updated = rs.getTimestamp(6);
                User u = new User(user_id, user_name, password, create_date, last_updated);
                user = u;
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOImplUser.class.getName()).log(Level.SEVERE, null, ex);
        }
          return user;
    }

    @Override
    public User update(User dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User create(User dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(User dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
