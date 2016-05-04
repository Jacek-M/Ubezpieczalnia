/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.model;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author layfl
 */
public abstract class AbstractModel<T> {

    @PersistenceContext(unitName = "Prodzekt-ejbPU")
    private EntityManager em;
    private T objectClass;

    public AbstractModel() {

    }

    public AbstractModel(Class<T> myClass) {
        try {
            this.objectClass = myClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<T> findAll() {

        String query = objectClass.getClass().getSimpleName() + ".findAll";
        TypedQuery<T> queryResult = em.createNamedQuery(query, (Class<T>) objectClass.getClass());
        return queryResult.getResultList();
    }

    public T findById(int id) throws Exception {
        String query = objectClass.getClass().getSimpleName() + ".findByAdresId";
        String paramName = objectClass.getClass().getSimpleName().toLowerCase() + "Id";
        TypedQuery<T> queryResult = em.createNamedQuery(query, (Class<T>) objectClass.getClass()).setParameter(paramName, id);
        if (queryResult.getResultList().get(0) != null) {
            return queryResult.getResultList().get(0);
        }
        throw new Exception("Cannot find " + objectClass.getClass().getSimpleName() + "with id = " + id);
    }

    public void addNew(T object) {
        em.persist(object);
    }

    /**
     * *
     * Update abstract bo każda klasa ma inne pola i nie wiadomo jakie chcemy
     * zmienić
     *
     * @param object
     */
    public abstract void update(T object);

    public void delete(T object) {
        em.remove(object);
    }

}
