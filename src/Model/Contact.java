/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DBAccess.DTO;

/**
 *
 * @author abenezertsegaye
 */
public class Contact implements DTO {
    private int Contact_ID;
    private String Contact_name;
    private String Email;

    public Contact(int Contact_ID, String Contact_name, String Email) {
        this.Contact_ID = Contact_ID;
        this.Contact_name = Contact_name;
        this.Email = Email;
    }

    public int getContact_ID() {
        return Contact_ID;
    }

    public void setContact_ID(int Contact_ID) {
        this.Contact_ID = Contact_ID;
    }

    public String getContact_name() {
        return Contact_name;
    }

    public void setContact_name(String Contact_name) {
        this.Contact_name = Contact_name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    @Override
    public long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override 
    public String toString(){
      return Contact_name;
    }
}
