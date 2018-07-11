/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.razniewski.countries;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;

/**
 *
 * @author adamr
 */
public class TemporaryAutographContainer {
    private Map<Short, Autograph> map;

    public TemporaryAutographContainer() {
        this.map = new HashMap<>();
    }

    public boolean containsKey(Short key) {
        return map.containsKey(key);
    }

    public Autograph get(Short key) {
        return map.get(key);
    }

    public Autograph remove(Short key) {
        return map.remove(key);
    }
    
    public void removeAll() {
        this.map.clear();
    }

    public Autograph put(Short key, Autograph value) {
        return map.put(key, value);
    }
    
    
    
    
    
}
