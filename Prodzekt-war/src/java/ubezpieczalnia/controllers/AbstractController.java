/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.controllers;

import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author layfl
 */
@ManagedBean
@RequestScoped
public abstract class AbstractController<T> {

    public T findAll() {
        return null;
    }

    public T findById(int id) {
        return null;
    }

    public void addNew(T object) {

    }

    public void update(T object) {

    }

    public void delete(T object) {

    }

}
