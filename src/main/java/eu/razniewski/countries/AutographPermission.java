/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.razniewski.countries;

/**
 *
 * @author edge
 */
public enum AutographPermission {
    CREATE("autograph.create"),
    CUSTOM("autograph.custom");
    private String permission;

    AutographPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
    
    
    
    
    
}
