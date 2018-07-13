/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.razniewski.countries.config;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author adamr
 */
public class DefaultConfigEntry implements Serializable {
    private String key;
    private String value;

    public DefaultConfigEntry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.key);
        hash = 67 * hash + Objects.hashCode(this.value);
        return hash;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultConfigEntry other = (DefaultConfigEntry) obj;
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DefaultConfigEntry{" + "key=" + key + ", value=" + value + '}';
    }
    
    
    
}
