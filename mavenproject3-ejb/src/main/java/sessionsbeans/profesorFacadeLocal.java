/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsbeans;

import entities.profesor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Dany
 */
@Local
public interface profesorFacadeLocal {

    void create(profesor profesor);

    void edit(profesor profesor);

    void remove(profesor profesor);

    profesor find(Object id);

    List<profesor> findAll();

    List<profesor> findRange(int[] range);

    int count();
    
    Object findByID(Long id);
    
}
