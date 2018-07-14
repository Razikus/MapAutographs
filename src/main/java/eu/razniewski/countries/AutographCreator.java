/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.razniewski.countries;

import eu.razniewski.countries.config.ConfigGate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

/**
 *
 * @author adamr
 */
public class AutographCreator implements CommandExecutor {

    private AutographStorage container;
    private ConfigGate locales;

    public AutographCreator(AutographStorage container, ConfigGate locales) {
        this.container = container;
        this.locales = locales;
    }
    
    
    
    
    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        if (cs instanceof Player) {
            
            Player player = (Player) cs;
            if(!player.hasPermission(AutographPermission.CREATE.getPermission())) {
                player.sendMessage(locales.getValueNotNull("noPermissionToCreate"));
                return false;
            }
            
            ItemStack playerMap = null;
            
            for(ItemStack stack: player.getInventory().getContents()) {
                if(stack != null && stack.getType().equals(Material.EMPTY_MAP)) {
                    playerMap = stack;
                    break;
                }
            }
            
            if(playerMap == null) {
                player.sendMessage(locales.getValueNotNull("noEmptyMap"));
                return false;
            } 
            
            Autograph autograph = null;
            if(strings.length == 0) {
                autograph = new Autograph(Byte.valueOf(locales.getValueNotNull("defaultBackground")), Integer.valueOf(locales.getValueNotNull("defaultX")), Integer.valueOf(locales.getValueNotNull("defaultY")), locales.getValueNotNull("defaultAdditionalText"), player.getName(), locales.getValueNotNull("defaultNicknamePrefix"));
            } else {
                if(!player.hasPermission(AutographPermission.CUSTOM.getPermission())) {
                    player.sendMessage(locales.getValueNotNull("noPermissionsToCustom"));
                    return false;
                }
                try {
                    byte bgColor = Byte.valueOf(strings[0]);
                    int xSign = Integer.valueOf(strings[1]);
                    int ySign = Integer.valueOf(strings[2]);
                    String prefix = strings[3];
                    String additional = "";
                    for(int j = 4; j < strings.length; j++) {
                        additional = additional + strings[j] + " ";
                    }
                    additional = additional.replaceAll("&n", "\n");
                    additional = additional.replaceAll("&", "§");
                    prefix = prefix.replaceAll("&n", "\n");
                    prefix = prefix.replaceAll("&", "§");
                    
                    autograph = new Autograph(bgColor, xSign, ySign, additional, player.getName(), prefix);
                } catch(Exception e) {
                    player.sendMessage(locales.getValueNotNull("usageInfo"));
                    return false;
                }
            }
            
            
            MapView mapView = Bukkit.createMap(player.getWorld());
            container.addMap(mapView.getId(), autograph);
            mapView.setScale(MapView.Scale.FARTHEST);
            mapView.setUnlimitedTracking(false);

            mapView.removeRenderer(mapView.getRenderers().get(0));
            MapRenderer renderer = new AutographRenderer(container);
            mapView.addRenderer(renderer);
            
            if(playerMap.getAmount() > 1) {
                playerMap.setAmount(playerMap.getAmount() -1);
            } else {
                player.getInventory().remove(playerMap);
            }
            
            ItemStack map = new ItemStack(Material.MAP);
            ItemMeta meta = map.getItemMeta();
            meta.setDisplayName(locales.getValueNotNull("defaultAutographPrefix") + player.getName() + " " + locales.getValueNotNull("defaultAutographName"));
            map.setItemMeta(meta);
            map.setDurability(mapView.getId());
            player.getInventory().addItem(map);
            player.sendMap(mapView);
        }

        return true;
    }
    
}
