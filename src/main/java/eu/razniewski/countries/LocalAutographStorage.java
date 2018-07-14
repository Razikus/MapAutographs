/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.razniewski.countries;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author edge
 */
public class LocalAutographStorage implements AutographStorage{
    CachedAutographContainer container;
    JavaPlugin plugin;

    public LocalAutographStorage(JavaPlugin plugin) {
        this.container = new CachedAutographContainer();
        this.plugin = plugin;
    }
    
    @Override
    public Optional<Autograph> getMapById(Short id) {
        return Optional.ofNullable(container.get(id));
    }

    @Override
    public void addMap(Short id, Autograph autograph) {
        container.put(id, autograph);
    }

    @Override
    public void onLoad() {
        Gson gson = new Gson();
        TypeToken s;
        Type type = new TypeToken<Map<Short, Autograph>>(){}.getType();
        File storage = new File(plugin.getDataFolder() + "/storage.json");
        if(!storage.exists()) {
            storage.getParentFile().mkdirs();
            try {
                storage.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(LocalAutographStorage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            JsonReader reader = new JsonReader(new FileReader(plugin.getDataFolder().getAbsolutePath() + "/storage.json"));
            Map<Short, Autograph> map = gson.fromJson(reader, type);
            if(map == null) {
                map = new HashMap<>();
            }
            for(Short id: map.keySet()) {
                container.put(id, map.get(id));
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LocalAutographStorage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LocalAutographStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onDisable() {
        Gson gson = new Gson();
        try (Writer writer = new FileWriter(plugin.getDataFolder().getAbsolutePath() + "/storage.json")) {
            gson.toJson(container.getWholeMap(), writer);
        } catch (IOException ex) {
            Logger.getLogger(LocalAutographStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public Short[] getIds() {
        return this.container.getWholeMap().keySet().toArray(new Short[this.container.getWholeMap().keySet().size()]);
    }
    
}
