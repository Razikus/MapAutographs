/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.razniewski.countries;

import eu.razniewski.countries.config.ConfigGate;
import eu.razniewski.countries.config.LocalConfig;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author adamr
 */
public class MapAutographs extends JavaPlugin {

    private AutographStorage autographService;
    private ConfigGate config;
    private ConfigGate locale;
    
    @Override
    public void onEnable() {
        checkForDataFolder();
        config = new LocalConfig(this, "config.properties");
        locale = new LocalConfig(this, "locale.properties");
        config.loadConfig();
        locale.loadConfig();
        this.autographService = new LocalAutographStorage(this);
        autographService.onLoad();
        registerMaps(autographService.getIds());
        getCommand("autograph").setExecutor(new AutographCreator(autographService));
    }

    @Override
    public void onDisable() {
        autographService.onDisable();
        config.saveConfig();
        locale.saveConfig();
        
    }

    private void registerMaps(Short[] ids) {
        for(Short id: ids) {
            MapView mapView = Bukkit.getMap(id);
            mapView.setScale(MapView.Scale.FARTHEST);
            mapView.setUnlimitedTracking(false);
            mapView.removeRenderer(mapView.getRenderers().get(0));
            MapRenderer renderer = new AutographRenderer(autographService);
            mapView.addRenderer(renderer);
        }
    }

    private void checkForDataFolder() {
        if(!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
    }
    
}
