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
public class Country implements DTO {
    private int Country_ID;
    private String Country;

    public Country(int Country_ID, String Country) {
        this.Country_ID = Country_ID;
        this.Country = Country;
    }

    public int getCountry_ID() {
        return Country_ID;
    }

    public void setCountry_ID(int Country_ID) {
        this.Country_ID = Country_ID;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    @Override
    public long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public String toString(){
      return Country;
    }  
}
