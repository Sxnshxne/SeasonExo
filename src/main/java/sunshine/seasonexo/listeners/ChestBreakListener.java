package sunshine.seasonexo.listeners;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import sunshine.seasonexo.SeasonExo;

import java.util.List;

public class ChestBreakListener implements Listener {


    private SeasonExo seasonExo;

    public ChestBreakListener(SeasonExo seasonExo) {
        this.seasonExo = seasonExo;
    }


    @EventHandler
    public void onClick(BlockBreakEvent event) {

        Player player = event.getPlayer();
        Block block = event.getBlock();


        if (block.getType() == Material.CHEST) {

            List<Double> coordsActualChest = this.seasonExo.getChestManager().getCoordsActualChest();

            if (coordsActualChest.isEmpty()) { return; }

            if (block.getState().getLocation().getX() == coordsActualChest.get(0)
                    && block.getState().getLocation().getY() == coordsActualChest.get(1)
                    && block.getState().getLocation().getZ() == coordsActualChest.get(2)) {

                this.seasonExo.getRunTaskManager().cancelAutoDispawn();
                this.seasonExo.getRunTaskManager().reloadCountdown();
                this.seasonExo.getChestManager().DeleteChest(player, coordsActualChest.get(0), coordsActualChest.get(1), coordsActualChest.get(2));
                Bukkit.broadcastMessage(this.seasonExo.getConfigManager().getFormatedString("config.yml","chest-founded", true));

            }


        }
    }


}
