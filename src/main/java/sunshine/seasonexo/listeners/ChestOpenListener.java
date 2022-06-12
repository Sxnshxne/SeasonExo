package sunshine.seasonexo.listeners;


import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import sunshine.seasonexo.SeasonExo;

import java.util.List;

public class ChestOpenListener implements Listener {


    private SeasonExo seasonExo;

    public ChestOpenListener(SeasonExo seasonExo) {
        this.seasonExo = seasonExo;
    }



    static String playerWhoOpen = "";

    @EventHandler
    public void onClick(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Block block = event.getClickedBlock();


        if (!event.hasBlock() || event.getAction()!= Action.RIGHT_CLICK_BLOCK) { return; }

        if (block.getType() == Material.CHEST) {

            List<Double> coordsActualChest = this.seasonExo.getChestManager().getCoordsActualChest();

            if (coordsActualChest.isEmpty()) { return; }

            if (block.getState().getLocation().getX() == coordsActualChest.get(0)
                    && block.getState().getLocation().getY() == coordsActualChest.get(1)
                    && block.getState().getLocation().getZ() == coordsActualChest.get(2)) {

                this.seasonExo.getRunTaskManager().autoDispawnAfterOpening(player, coordsActualChest);
                playerWhoOpen = player.getName();

            }


        }
    }

    public static String getPlayerWhoOpen() {
        return playerWhoOpen;
    }


}
