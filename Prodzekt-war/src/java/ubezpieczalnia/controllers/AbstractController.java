/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.controllers;

import java.util.List;

/**
 *
 * @author layfl
 */

public interface AbstractController<T> {

    public List<T> findAll();

    public T findById() throws Exception;

    public String addNew();

    public String update();

    public String delete();

}
