/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.razniewski.countries;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

/**
 *
 * @author edge
 */
public class AutographInitializator implements Listener {
    
    private AutographStorage storage;

    public AutographInitializator(AutographStorage storage) {
        this.storage = storage;
    }
    
    
    
    @EventHandler
    public void onMapInitialize(MapInitializeEvent e) {
        if(storage.getMapById(e.getMap().getId()).isPresent()) {
            MapView mapView = e.getMap();
            mapView.setScale(MapView.Scale.FARTHEST);
            mapView.setUnlimitedTracking(false);

            mapView.removeRenderer(mapView.getRenderers().get(0));
            MapRenderer renderer = new AutographRenderer(storage);
            mapView.addRenderer(renderer);
        }
    }
}
