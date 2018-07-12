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
public class MapAutographs extends JavaPlugin {

    private AutographStorage autographService;
    
    @Override
    public void onEnable() {
        this.autographService = new LocalAutographStorage(this);
        autographService.onLoad();
        getCommand("autograph").setExecutor(new AutographCreator(autographService));
    }

    @Override
    public void onDisable() {
        autographService.onDisable();
        
    }
    
}
