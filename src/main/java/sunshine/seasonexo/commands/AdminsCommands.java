package sunshine.seasonexo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sunshine.seasonexo.chest.ChestManager;
import sunshine.seasonexo.datas.ItemsManager;
import sunshine.seasonexo.datas.PositionsManager;
import sunshine.seasonexo.datas.MessagesManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class AdminsCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("seasonexo") && player.hasPermission("seasonexo.*")) {


            if (args.length == 0) {

                player.sendMessage(MessagesManager.GetPrefix() + MessagesManager.GetUnknowCommand());





            } else if (Objects.equals(args[0], "summon")) {


                List coords = PositionsManager.getRandomPositions();
                coords = Arrays.asList(coords.toArray());

                ChestManager.SummonChest(player, (Double) coords.get(0), (Double) coords.get(1), (Double) coords.get(2));
                ChestManager.setCoordsActualChest((Double) coords.get(0), (Double) coords.get(1), (Double) coords.get(2));
                player.sendMessage(MessagesManager.GetPrefix() + MessagesManager.GetChestSummoning((Double) coords.get(0), (Double) coords.get(1), (Double) coords.get(2)));





            } else if (Objects.equals(args[0], "reload")) {

                try {

                    ItemsManager.reloadDict();
                    PositionsManager.reloadListOfPositions();
                    MessagesManager.reloadMessages();
                    player.sendMessage(MessagesManager.GetPrefix() + MessagesManager.GetPluginReloaded());

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }





            } else if (Objects.equals(args[0], "delete")) {
                List coordsList = ChestManager.getCoordsActualChest();

                if (!coordsList.isEmpty()) {
                    ChestManager.DeleteChest(player, (Double) coordsList.get(0), (Double) coordsList.get(1), (Double) coordsList.get(2));
                    player.sendMessage(MessagesManager.GetPrefix() + MessagesManager.GetChestDeletingSuccesfully());
                } else {
                    player.sendMessage(MessagesManager.GetPrefix() + MessagesManager.GetChestDeletingError());
                }





            } else if (Objects.equals(args[0], "additem")) {

                try {

                    String material = args[1];
                    double purcentage = Double.parseDouble(args[2]);

                    ItemsManager.addItemToFile(material, purcentage);
                    player.sendMessage(MessagesManager.GetPrefix() + MessagesManager.GetItemAddedToConfig(material, purcentage));

                } catch (IOException e) {
                    player.sendMessage(MessagesManager.GetPrefix() + MessagesManager.GetSyntaxError());
                }





            } else if (Objects.equals(args[0], "addpos")) {

                try {

                    double posX = Double.parseDouble(args[1]);
                    double posY = Double.parseDouble(args[2]);
                    double posZ = Double.parseDouble(args[3]);

                    PositionsManager.addPositionToFile(posX, posY, posZ);
                    player.sendMessage(MessagesManager.GetPrefix() + MessagesManager.GetPositionAddedToConfig(posX, posY, posZ));

                } catch (NumberFormatException | IOException | ArrayIndexOutOfBoundsException e) {
                    player.sendMessage(MessagesManager.GetPrefix() + MessagesManager.GetSyntaxError());
                }





            } else if (Objects.equals(args[0], "close")) {

                player.sendMessage(MessagesManager.GetPrefix() + "Commande reçu : §a" + args[0]);





            } else {

                player.sendMessage(MessagesManager.GetPrefix() + MessagesManager.GetUnknowCommand());

            }

        } else {

            player.sendMessage(MessagesManager.GetPrefix() + MessagesManager.GetMissingPermission());

        }

        return false;
    }
}
