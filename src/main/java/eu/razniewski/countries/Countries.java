/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.razniewski.countries;

import org.bukkit.Bukkit;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author adamr
 */
public class Countries extends JavaPlugin {

    private TemporaryAutographContainer autographPermissions;
    
    @Override
    public void onEnable() {
        this.autographPermissions = new TemporaryAutographContainer();
        getCommand("autograph").setExecutor(new AutographCreator(autographPermissions));
    }

    @Override
    public void onDisable() {
        this.autographPermissions.removeAll();
        
    }
    
}
