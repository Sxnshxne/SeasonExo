package sunshine.seasonexo.listeners;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import sunshine.seasonexo.chest.ChestManager;
import sunshine.seasonexo.commands.AdminsCommands;
import sunshine.seasonexo.datas.MessagesManager;

import java.util.List;

public class ChestBreakListener implements Listener {


    @EventHandler
    public void onClick(BlockBreakEvent event) {

        Player player = (Player) event.getPlayer();
        Block block = event.getBlock();


        if (block.getType() == Material.CHEST) {

            List coordsActualChest = ChestManager.getCoordsActualChest();

            if (coordsActualChest.isEmpty()) { return; }

            if (block.getState().getLocation().getX() == (Double) coordsActualChest.get(0) && block.getState().getLocation().getY() == (Double) coordsActualChest.get(1) && block.getState().getLocation().getZ() == (Double) coordsActualChest.get(2)) {
                AdminsCommands.cancelAutoDispawn();
                ChestManager.DeleteChest(player, (Double) coordsActualChest.get(0), (Double) coordsActualChest.get(1), (Double) coordsActualChest.get(2));
                Bukkit.broadcastMessage(MessagesManager.GetChestFounded());
                ChestManager.reloadCountdown();

            }


        }
    }


}
