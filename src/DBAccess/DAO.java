/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import java.util.List;
import static javafx.scene.input.KeyCode.T;


/**
 *
 * @author abenezertsegaye
 */
public abstract class DAO <T extends DTO> {
    
    public abstract List<T> findAll();
    public abstract T findById(int id);
    public abstract T update(T dto);
    public abstract T create(T dto);
    public abstract void delete(T dto);
    
}
