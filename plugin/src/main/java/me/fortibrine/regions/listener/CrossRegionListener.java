package me.fortibrine.regions.listener;

import lombok.RequiredArgsConstructor;
import me.fortibrine.regions.api.event.RegionCrossEvent;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@RequiredArgsConstructor
public class CrossRegionListener implements Listener {

    private final Plugin plugin;

    @EventHandler
    public void onCrossRegion(RegionCrossEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() == RegionCrossEvent.Action.JOIN &&
                !player.hasPermission("colombinotest.enter-region")) {
            event.setCancelled(true);
            return;
        }

        plugin.getConfig().getStringList("apply-effects").forEach(effect -> {
            String potionEffectType = effect.split(";")[0];
            int duration = Integer.parseInt(effect.split(";")[1]);
            PotionEffectType type = Registry.EFFECT.get(NamespacedKey.minecraft(potionEffectType.toLowerCase()));

            if (type == null) return;

            if (event.getAction() == RegionCrossEvent.Action.JOIN) {
                player.addPotionEffect(new PotionEffect(type, Integer.MAX_VALUE, duration));
            } else {
                player.removePotionEffect(type);
            }
        });

    }

}
