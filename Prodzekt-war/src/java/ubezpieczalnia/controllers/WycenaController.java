/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubezpieczalnia.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import ubezpieczalnia.entities.Wycena;
import ubezpieczalnia.model.WycenaEJB;

/**
 *
 * @author Jacek
 */
@ManagedBean
@RequestScoped
public class WycenaController implements AbstractController<Wycena>{
    
    @ManagedProperty(value = "#{pracownikController}")
    private PracownikController pracownikController;
    
//    @ManagedProperty(value = "#{pracownikController}")
//    private PracownikController pracownikController;
    
    @EJB
    private WycenaEJB wycenaEJB;

    private Wycena wycena = new Wycena();
    private List<Wycena> wycenaList = new ArrayList<>();

    @Override
    public List<Wycena> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Wycena findById() throws Exception {
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
