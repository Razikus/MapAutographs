/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.razniewski.countries;

import eu.razniewski.countries.config.ConfigGate;
import eu.razniewski.countries.config.DefaultConfigEntry;
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
        locale = new LocalConfig(this, "locale.properties", getDefaultLocales());
        config.loadConfig();
        locale.loadConfig();
        this.autographService = new LocalAutographStorage(this);
        autographService.onLoad();
        registerMaps(autographService.getIds());
        getCommand("autograph").setExecutor(new AutographCreator(autographService, locale));
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

    // @TODO - get rid of this in future
    private DefaultConfigEntry[] getDefaultLocales() {
        
        LocaleBuilder builder = new LocaleBuilder();
        return builder.addNext("noPermissionToCreate", "No permissions :(")
                .addNext("noEmptyMap", "No empty map")
                .addNext("defaultBackground", "32")
                .addNext("defaultX", "15")
                .addNext("defaultY", "100")
                .addNext("defaultAdditionalText", "§4;Always for\nyou,")
                .addNext("defaultNicknamePrefix", "§16;")
                .addNext("noPermissionsToCustom", "No permissions to make custom autograph")
                .addNext("usageInfo", "Usage: /autograph COLOR XOFSIGN YOFSIGN PREFIXOFNICKNAME ADDITIONALMESSAGE")
                .addNext("defaultAutographPrefix", "§3")
                .addNext("defaultAutographName", "autograph").build();
                
                
    }
    
}
