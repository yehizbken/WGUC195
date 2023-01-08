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
public class Division implements DTO {
    private int Country_ID;
    private String Division;
    private int Division_ID;
    

    public Division(int Division_ID, String Division,int Country_ID ) {
        this.Country_ID = Country_ID;
        this.Division = Division;
        this.Division_ID = Division_ID;
        
    }

    public int getCountry_ID() {
        return Country_ID;
    }

    public void setCountry_ID(int Country_ID) {
        this.Country_ID = Country_ID;
    }

    public String getDivision() {
        return Division;
    }

    public void setDivision(String Division) {
        this.Division = Division;
    }

    public int getDivision_ID() {
        return Division_ID;
    }

    public void setDivision_ID(int Division_ID) {
        this.Division_ID = Division_ID;
    }

    @Override
    public long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public String toString(){
     return Division;
    }
    
}