package sunshine.seasonexo.listeners;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import sunshine.seasonexo.SeasonExo;
import sunshine.seasonexo.chest.ChestManager;
import sunshine.seasonexo.commands.AdminsCommands;
import sunshine.seasonexo.datas.MessagesManager;
import sunshine.seasonexo.utils.JsonManager;

import java.io.FileNotFoundException;
import java.util.List;

public class ChestOpenListener implements Listener {

    static String playerWhoOpen = "";

    public static String getPlayerWhoOpen() {
        return playerWhoOpen;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {

        Player player = (Player) event.getPlayer();
        Block block = event.getClickedBlock();


        if (!event.hasBlock() || event.getAction()!= Action.RIGHT_CLICK_BLOCK) { return; }

        if (block.getType() == Material.CHEST) {

            List coordsActualChest = ChestManager.getCoordsActualChest();

            if (coordsActualChest.isEmpty()) { return; }

            if (block.getState().getLocation().getX() == (Double) coordsActualChest.get(0) && block.getState().getLocation().getY() == (Double) coordsActualChest.get(1) && block.getState().getLocation().getZ() == (Double) coordsActualChest.get(2)) {


                try {
                    Object cooldown = new JsonManager().readSimpleValue("/SeasonExo/config.json", "cooldown-after-open");
                    String stringToConvert = String.valueOf(cooldown).replace(".0","");

                    Bukkit.broadcastMessage("§cLe coffre disparaitra dans §e"+ stringToConvert + " §csecondes");
                    Bukkit.broadcastMessage(MessagesManager.GetChestFounded());
                    AdminsCommands.cancelAutoDispawn();

                    ChestManager.reloadCountdown();

                    playerWhoOpen = player.getName();

                    Long cooldownLong = Long.parseLong(stringToConvert);

                    Bukkit.getScheduler().runTaskLater(SeasonExo.plugin(), () -> {

                        ChestManager.DeleteChest(player, (Double) coordsActualChest.get(0), (Double) coordsActualChest.get(1), (Double) coordsActualChest.get(2));
                        playerWhoOpen = "";

                    }, 20L * cooldownLong);

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }


        }
    }


}
