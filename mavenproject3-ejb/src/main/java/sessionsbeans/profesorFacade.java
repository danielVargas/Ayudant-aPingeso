/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionsbeans;

import entities.profesor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dany
 */
@Stateless
public class profesorFacade extends AbstractFacade<profesor> implements profesorFacadeLocal {
    @PersistenceContext(unitName = "com.mycompany_mavenproject3-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public profesorFacade() {
        super(profesor.class);
    }
    
    public Object findByID(Long id){
        javax.persistence.Query query = em.createNamedQuery("profesor.findByKey");
          query.setParameter("id",id);
        //  query.setParameter("keyPays",pays.getKeyPays());
         return query.getSingleResult();
    }
    
}
