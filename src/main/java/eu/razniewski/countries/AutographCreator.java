/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.razniewski.countries;

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

    private TemporaryAutographContainer container;

    public AutographCreator(TemporaryAutographContainer container) {
        this.container = container;
    }
    
    
    
    
    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        if (cs instanceof Player) {
            Player player = (Player) cs;
            byte bgColor = Byte.valueOf(strings[0]);
            int xSign = Integer.valueOf(strings[1]);
            int ySign = Integer.valueOf(strings[2]);
            String prefix = strings[3];
            String additional = "";
            for(int j = 4; j < strings.length; j++) {
                additional = additional + strings[j];
            }
            Autograph autograph = new Autograph(bgColor, xSign, ySign, additional, player.getName(), prefix);
            
            
            MapView mapView = Bukkit.createMap(player.getWorld());
            container.put(mapView.getId(), autograph);
            mapView.setScale(MapView.Scale.FARTHEST);
            mapView.setUnlimitedTracking(false);

            mapView.removeRenderer(mapView.getRenderers().get(0));
            MapRenderer renderer = new AutographRenderer(container);
            mapView.addRenderer(renderer);
            
            ItemStack map = new ItemStack(Material.MAP);
            map.setDurability(mapView.getId());
            player.getInventory().addItem(map);
            player.sendMap(mapView);
        }

        return true;
    }
    
}
