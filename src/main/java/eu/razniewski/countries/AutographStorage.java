/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.razniewski.countries;

import java.util.Optional;

/**
 *
 * @author edge
 */
public interface AutographStorage {
    public Optional<Autograph> getMapById(Short id);
    public void addMap(Short id, Autograph autograph);
    public Short[] getIds();
    
    public void onLoad();
    public void onDisable();
    
}
