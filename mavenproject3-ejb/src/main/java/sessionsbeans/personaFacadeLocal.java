/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsbeans;

import entities.persona;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Dany
 */
@Local
public interface personaFacadeLocal {

    void create(persona persona);

    void edit(persona persona);

    void remove(persona persona);

    persona find(Object id);

    List<persona> findAll();

    List<persona> findRange(int[] range);

    int count();
    
}
