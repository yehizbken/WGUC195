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
public class Customer implements DTO{

    private int Cust_ID;
    private String CustName;
    private String Country;
    private String Email;
    private String ZipCode;
    private String Address;
    private String Phone;
    private int Division_ID;
    private int Country_ID;

    ; 
    public Customer(int Cust_ID, String CustName, String Address, String ZipCode) {
        this.Cust_ID = Cust_ID;
        this.CustName = CustName;
        this.Address = Address;
        this.ZipCode = ZipCode;
      //  this.Phone = phone;
    }
    public Customer(int Cust_ID, String CustName, String Address, String ZipCode, String Phone, int Division_ID, int Country_ID) {
        this.Cust_ID = Cust_ID;
        this.CustName = CustName;
        this.Address = Address;
        this.ZipCode = ZipCode;
        this.Phone = Phone;
        this.Division_ID = Division_ID;
        this.Country_ID = Country_ID;
    }

    public Customer(int Cust_ID, String CustName, String Country, String Email, String ZipCode, String Address) {
        this.Cust_ID = Cust_ID;
        this.CustName = CustName;
        this.Country = Country;
        this.Email = Email;
        this.ZipCode = ZipCode;
        this.Address = Address;
    }

    public Customer(int cust_ID, String updateName, String updateAddress, String updatePostal, String updatePhone) {
        
    }
    

    public int getCust_ID() {
        return Cust_ID;
    }

    public void setCust_ID(int Cust_ID) {
        this.Cust_ID = Cust_ID;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public Customer(String cust) {
        this.CustName = cust;

    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String CustName) {
        this.CustName = CustName;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String ZipCode) {
        this.ZipCode = ZipCode;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public int getDivision_ID() {
        return Division_ID;
    }

    public void setDivision_ID(int Division_ID) {
        this.Division_ID = Division_ID;
    }

    public int getCountry_ID() {
        return Country_ID;
    }

    public void setCountry_ID(int Country_ID) {
        this.Country_ID = Country_ID;
    }
    

    @Override
    public long getId() {
        return Cust_ID;
    }

}
