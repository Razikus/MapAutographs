/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.razniewski.countries.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author edge
 */
public class LocalConfig implements ConfigGate{
    private JavaPlugin plugin;
    private Properties loaded;
    private String name; 
    public LocalConfig(JavaPlugin plugin, String name) {
        this.plugin = plugin;
        this.name = name;
    }
    
    @Override
    public Properties getConfig() {
        return loaded;
    }

    @Override
    public String getValue(String key) {
        return loaded.getProperty(key);
    }

    @Override
    public void saveConfig() {
        try {
            OutputStream output = null;
            output = new FileOutputStream(plugin.getDataFolder().getAbsolutePath() + "/" + name);
            loaded.store(output, null);
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LocalConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LocalConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void loadConfig() {
        loaded = new Properties();
        try {
            InputStream input = null;
            input = new FileInputStream(plugin.getDataFolder().getAbsolutePath() + "/" + name);
            loaded.load(input);
            input.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LocalConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LocalConfig.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
