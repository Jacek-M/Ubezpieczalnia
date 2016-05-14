/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.controllers;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import ubezpieczalnia.entities.Umowa;

/**
 *
 * @author Jacek
 */
@ManagedBean
@RequestScoped
public class UmowaController implements AbstractController<Umowa>{

    @ManagedProperty(value = "#{pracownikController}")
    private PracownikController pracownikController;
    
    @ManagedProperty(value = "#{registerController}")
    private RegisterController registerController;
    
    @ManagedProperty(value = "#{pojazdController}")
    private PojazdController pojazdController;
    
    @ManagedProperty(value = "#{rodzajUbezController}")
    private RodzajUbezController rodzajUbezController;
    
    @Override
    public List<Umowa> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Umowa findById() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String addNew() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
