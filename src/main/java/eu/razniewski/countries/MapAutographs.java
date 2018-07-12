/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.razniewski.countries;

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
    
    @Override
    public void onEnable() {
        this.autographService = new LocalAutographStorage(this);
        autographService.onLoad();
        registerMaps(autographService.getIds());
        getCommand("autograph").setExecutor(new AutographCreator(autographService));
    }

    @Override
    public void onDisable() {
        autographService.onDisable();
        
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
    
}
