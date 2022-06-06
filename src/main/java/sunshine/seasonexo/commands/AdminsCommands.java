package sunshine.seasonexo.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import sunshine.seasonexo.SeasonExo;
import sunshine.seasonexo.chest.ChestManager;
import sunshine.seasonexo.datas.ItemsManager;
import sunshine.seasonexo.datas.PositionsManager;
import sunshine.seasonexo.datas.MessagesManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class AdminsCommands implements CommandExecutor {

    static BukkitTask taskId1;
    static BukkitTask taskId2;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("seasonexo") && player.hasPermission("seasonexo.*")) {


            if (args.length == 0) {

                player.sendMessage(MessagesManager.GetUnknowCommand());





            } else if (Objects.equals(args[0], "summon")) {

                List coordsList = ChestManager.getCoordsActualChest();

                if (coordsList.isEmpty()) {

                    List coords = PositionsManager.getRandomPositions();
                    coords = Arrays.asList(coords.toArray());

                    ChestManager.SummonChest(player, (Double) coords.get(0), (Double) coords.get(1), (Double) coords.get(2));
                    ChestManager.setCoordsActualChest((Double) coords.get(0), (Double) coords.get(1), (Double) coords.get(2));
                    player.sendMessage(MessagesManager.GetChestAdminSummoning((Double) coords.get(0), (Double) coords.get(1), (Double) coords.get(2)));

                    startAutoDispawn(player, coords);



                } else {
                    player.sendMessage("§cUn coffre est déjà présent sur la map");
                }




            } else if (Objects.equals(args[0], "reload")) {

                try {

                    ItemsManager.reloadDict();
                    PositionsManager.reloadListOfPositions();
                    MessagesManager.reloadMessages();
                    player.sendMessage(MessagesManager.GetPluginReloaded());

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }





            } else if (Objects.equals(args[0], "delete")) {
                List coordsList = ChestManager.getCoordsActualChest();

                if (!coordsList.isEmpty()) {
                    ChestManager.DeleteChest(player, (Double) coordsList.get(0), (Double) coordsList.get(1), (Double) coordsList.get(2));
                    player.sendMessage(MessagesManager.GetAdminChestDeletingSuccesfully());
                    cancelAutoDispawn();
                    ChestManager.reloadCountdown();
                } else {
                    player.sendMessage(MessagesManager.GetAdminChestDeletingError());
                }





            } else if (Objects.equals(args[0], "additem")) {

                try {

                    String material = args[1];
                    double purcentage = Double.parseDouble(args[2]);

                    ItemsManager.addItemToFile(material, purcentage);
                    player.sendMessage(MessagesManager.GetItemAddedToConfig(material, purcentage));

                } catch (IOException e) {
                    player.sendMessage(MessagesManager.GetSyntaxError());
                }





            } else if (Objects.equals(args[0], "addpos")) {

                try {

                    double posX = Double.parseDouble(args[1]);
                    double posY = Double.parseDouble(args[2]);
                    double posZ = Double.parseDouble(args[3]);

                    PositionsManager.addPositionToFile(posX, posY, posZ);
                    player.sendMessage(MessagesManager.GetPositionAddedToConfig(posX, posY, posZ));

                } catch (NumberFormatException | IOException | ArrayIndexOutOfBoundsException e) {
                    player.sendMessage(MessagesManager.GetSyntaxError());
                }





            } else if (Objects.equals(args[0], "close")) {

                Objects.requireNonNull(Bukkit.getPlayer(args[1])).closeInventory();
                Objects.requireNonNull(Bukkit.getPlayer(args[1])).sendMessage(MessagesManager.GetForceClosedChestForPlayer());
                player.sendMessage(MessagesManager.GetForceClosedChestForAdmin());





            } else {

                player.sendMessage(MessagesManager.GetUnknowCommand());

            }

        } else {

            player.sendMessage(MessagesManager.GetMissingPermission());

        }

        return false;
    }


    private static void startAutoDispawn(Player player, List coords) {
        Bukkit.broadcastMessage(MessagesManager.GetChestTimedSummoning(30));
        ChestManager.startCountdown();

        taskId1 = Bukkit.getScheduler().runTaskLater(SeasonExo.plugin(), () ->
                Bukkit.broadcastMessage(MessagesManager.GetChestTimedSummoning(10)), 20L * 20L);

        List finalCoords = coords;

        taskId2 = Bukkit.getScheduler().runTaskLater(SeasonExo.plugin(), () -> {

            Bukkit.broadcastMessage(MessagesManager.GetChestAutoDeleting());
            ChestManager.DeleteChest(player, (Double) finalCoords.get(0), (Double) finalCoords.get(1), (Double) finalCoords.get(2));
            ChestManager.reloadCountdown();

        }, 20L * 30L);

    }


    public static void cancelAutoDispawn() {
        Bukkit.getScheduler().cancelTask(taskId1.getTaskId());
        Bukkit.getScheduler().cancelTask(taskId2.getTaskId());
        ChestManager.reloadCountdown();
    }

}
