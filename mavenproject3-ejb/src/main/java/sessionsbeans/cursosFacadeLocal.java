/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsbeans;

import entities.cursos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Dany
 */
@Local
public interface cursosFacadeLocal {

    void create(cursos cursos);

    void edit(cursos cursos);

    void remove(cursos cursos);

    cursos find(Object id);

    List<cursos> findAll();

    List<cursos> findRange(int[] range);
    
    void algo(); 

    int count();
    
}
