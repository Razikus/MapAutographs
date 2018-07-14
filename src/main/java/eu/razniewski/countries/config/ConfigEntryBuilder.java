/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.razniewski.countries.config;

import eu.razniewski.countries.config.DefaultConfigEntry;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adamr
 */
public class ConfigEntryBuilder {
    private List<DefaultConfigEntry> entries;

    public ConfigEntryBuilder() {
        this.entries = new ArrayList<>();
    }
    
    public ConfigEntryBuilder addNext(String key, String value) {
        this.entries.add(new DefaultConfigEntry(key, value));
        return this;
    }
    
    public DefaultConfigEntry[] build() {
        return entries.toArray(new DefaultConfigEntry[entries.size()]);
    }
    
    
}
