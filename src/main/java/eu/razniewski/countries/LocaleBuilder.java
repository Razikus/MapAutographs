/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.razniewski.countries;

import eu.razniewski.countries.config.DefaultConfigEntry;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adamr
 */
public class LocaleBuilder {
    private List<DefaultConfigEntry> entries;

    public LocaleBuilder() {
        this.entries = new ArrayList<>();
    }
    
    public LocaleBuilder addNext(String key, String value) {
        this.entries.add(new DefaultConfigEntry(key, value));
        return this;
    }
    
    public DefaultConfigEntry[] build() {
        return entries.toArray(new DefaultConfigEntry[entries.size()]);
    }
    
    
}
