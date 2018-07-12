/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.razniewski.countries;

import java.util.HashMap;
import java.util.HashSet;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

/**
 *
 * @author adamr
 */
public class AutographCreator implements CommandExecutor {

    private AutographStorage container;

    public AutographCreator(AutographStorage container) {
        this.container = container;
    }
    
    
    
    
    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        if (cs instanceof Player) {
            
            Player player = (Player) cs;
            if(!player.hasPermission(AutographPermission.CREATE.getPermission())) {
                player.sendMessage("No permission :(");
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
                player.sendMessage("You have to have empty map!");
                return false;
            } 
            
            Autograph autograph = null;
            if(strings.length == 0) {
                autograph = new Autograph((byte) 32, 15, 100, "Greetings,", player.getName(), "");
            } else {
                if(!player.hasPermission(AutographPermission.CUSTOM.getPermission())) {
                    player.sendMessage("You have no permissions to do custom autographs!");
                    return false;
                }
                try {
                    byte bgColor = Byte.valueOf(strings[0]);
                    int xSign = Integer.valueOf(strings[1]);
                    int ySign = Integer.valueOf(strings[2]);
                    String prefix = strings[3];
                    String additional = "";
                    for(int j = 4; j < strings.length; j++) {
                        additional = additional + " " + strings[j];
                    }
                    autograph = new Autograph(bgColor, xSign, ySign, additional, player.getName(), prefix);
                } catch(Exception e) {
                    player.sendMessage("Usage: /autograph COLOR XOFSIGN YOFSIGN PREFIXOFNICKNAME ADDITIONALMESSAGE");
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
            
            player.getInventory().remove(playerMap);
            
            ItemStack map = new ItemStack(Material.MAP);
            map.setDurability(mapView.getId());
            player.getInventory().addItem(map);
            player.sendMap(mapView);
        }

        return true;
    }
    
}
