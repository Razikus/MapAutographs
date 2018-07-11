/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.razniewski.countries;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.map.MinecraftFont;

/**
 *
 * @author adamr
 */
class AutographRenderer extends MapRenderer {

    private TemporaryAutographContainer container;

    public AutographRenderer(TemporaryAutographContainer container) {
        this.container = container;
    }

    @Override
    public void render(MapView mv, MapCanvas mc, Player player) {
        Autograph autograph = container.get(mv.getId());
        if (autograph == null) {
            mc.drawText(15, 15, MinecraftFont.Font, ":(");
            return;
        }
        for (int x = 0; x < 150; x++) {
            for (int y = 0; y < 150; y++) {
                mc.setPixel(x, y, autograph.getBgColor());
            }
        }
        mc.drawText(15, 15, MinecraftFont.Font, autograph.getAdditionalText());
        mc.drawText(autograph.getxSign(), autograph.getySign(), MinecraftFont.Font, autograph.getNickname());
    }

}
