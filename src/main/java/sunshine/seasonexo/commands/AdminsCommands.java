package sunshine.seasonexo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sunshine.seasonexo.chest.ChestManager;
import sunshine.seasonexo.datas.ItemDictManager;
import sunshine.seasonexo.datas.PositionsManager;
import sunshine.seasonexo.utils.MessagesManager;

import java.util.*;

public class AdminsCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("seasonexo") && player.hasPermission("seasonexo.*")) {


            if (args.length == 0) {

                player.sendMessage(MessagesManager.Prefix() + "§fCommande inconnue. Veuillez vérifier la syntaxe et réessayer");





            } else if (Objects.equals(args[0], "summon")) {

                List coords = PositionsManager.getRandomPositions();

                ChestManager.SummonChest(player, (Integer) coords.get(0), (Integer) coords.get(1), (Integer) coords.get(2));
                player.sendMessage(MessagesManager.Prefix() + "§fCoffre généré en §e" + coords.get(0) + "§f, §e" + coords.get(1) + "§f, §e" + coords.get(2) + "");





            } else if (Objects.equals(args[0], "reload")) {

                ItemDictManager.reloadDict();
                PositionsManager.reloadListOfPositions();
                player.sendMessage(MessagesManager.Prefix() + "§aPlugin rechargé avec succès");



            } else if (Objects.equals(args[0], "delete")) {

                player.sendMessage(MessagesManager.Prefix() + "Commande reçu : §a" + args[0]);





            } else if (Objects.equals(args[0], "additem")) {

                player.sendMessage(MessagesManager.Prefix() + "Commande reçu : §a" + args[0]);





            } else if (Objects.equals(args[0], "addpos")) {

                player.sendMessage(MessagesManager.Prefix() + "Commande reçu : §a" + args[0]);





            } else if (Objects.equals(args[0], "close")) {

                player.sendMessage(MessagesManager.Prefix() + "Commande reçu : §a" + args[0]);





            } else {

                player.sendMessage(MessagesManager.Prefix() + "§fCommande inconnue. Veuillez vérifier la syntaxe et réessayer");

            }

        } else {

            player.sendMessage(MessagesManager.Prefix() + "§cVous n'avez pas la permission d'executer cette commande");

        }

        return false;
    }
}
