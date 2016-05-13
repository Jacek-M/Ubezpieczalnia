/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.model;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author layfl
 * @param <T> - klasa z ubezpieczalnia.entities
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

    public boolean constraintValidationsDetected(T entity) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = (Validator) factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        if (constraintViolations.size() > 0) {
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<T> cv = iterator.next();
                System.err.println(cv.getRootBeanClass().getName() + "." + cv.getPropertyPath() + " " + cv.getMessage());
            }
            return true;
        } else {
            return false;
        }
    }

    public T findById(int id) throws Exception {
        String simpleName = objectClass.getClass().getSimpleName();
        String query = simpleName + ".findBy" + simpleName + "Id";
        String paramName = objectClass.getClass().getSimpleName().toLowerCase() + "Id";
        TypedQuery<T> queryResult = em.createNamedQuery(query, (Class<T>) objectClass.getClass()).setParameter(paramName, id);
        if (queryResult.getResultList().size() > 0 && queryResult.getResultList().get(0) != null) {
            return queryResult.getResultList().get(0);
        }
        throw new Exception("Cannot find " + objectClass.getClass().getSimpleName() + "with id = " + id);
    }

    public T addNew(T object) {
        if (!constraintValidationsDetected(object)) {
            em.persist(object);
            em.flush();
            return object;
        }
        return null;
    }

    public abstract void update(T object);

    public void delete(T object) {
        em.remove(object);
    }
    


}
