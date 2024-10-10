package net.kevarion.corpse;

import net.minecraft.server.network.PlayerConnection;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Corpse extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getFinalDamage() <= ((Player) event.getEntity()).getHealthScale()) {
                Player player = (Player) event.getEntity();
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), getRespawnItem(player));

                player.setGameMode(GameMode.SPECTATOR);

                event.setCancelled(true);
            }
        }
    }

    private ItemStack getRespawnItem(Player player) {
        ItemStack potion = new ItemStack(Material.POTION);
        ItemMeta meta = potion.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + player.getName());

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Use this to respawn the player!");
        meta.setLore(lore);
        potion.setItemMeta(meta);
        return potion;
    }

}
