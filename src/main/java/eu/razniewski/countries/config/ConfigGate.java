/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.razniewski.countries.config;

import java.util.Properties;

/**
 *
 * @author edge
 */
public interface ConfigGate {
    public Properties getConfig();
    public String getValue(String key);
    public void saveConfig();
    public void loadConfig();
}
