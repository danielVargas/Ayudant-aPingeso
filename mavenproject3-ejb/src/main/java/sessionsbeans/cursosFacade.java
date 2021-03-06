/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsbeans;

import entities.cursos;
import entities.profesor;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dany
 */
@Stateless
public class cursosFacade extends AbstractFacade<cursos> implements cursosFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_mavenproject3-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public cursosFacade() {
        super(cursos.class);
    }
    public void persist(Object object) {
        em.persist(object);
    }
    
    public void crearCurso(cursos curso, List<profesor> profesores) {
        em.persist(curso);
    }
    
    public Object findByID(Long id){
        javax.persistence.Query query = em.createNamedQuery("cursos.findByKey");
          query.setParameter("id",id);
        //  query.setParameter("keyPays",pays.getKeyPays());
         return query.getSingleResult();
    }
    
         
}
