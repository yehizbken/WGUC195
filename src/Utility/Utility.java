/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author abenezertsegaye
 */
public class Utility {
    
    private static Properties properties= null;
    
     static {
          FileInputStream fs = null;
          try{
              properties = new Properties();
              fs = new FileInputStream("Combobox.properties");
              properties.load(fs);
              
              
          }catch(Exception e){
          }
     
     
     }
    public static String getProperty(String pro){
    return properties.getProperty(pro);
    
    }
}
