package sunshine.seasonexo.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sunshine.seasonexo.SeasonExo;

import java.util.*;

public class AdminsCommands implements CommandExecutor {


    private final SeasonExo seasonExo;

    public AdminsCommands(SeasonExo seasonExo) {
        this.seasonExo = seasonExo;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("seasonexo") && player.hasPermission("seasonexo.*")) {


            if (args.length == 0) {

                player.sendMessage(this.seasonExo.getConfigManager().getFormatedString("config.yml","syntax_error", true));





            } else if (args[0].equals("summon")) {

                List coordsList = this.seasonExo.getChestManager().getCoordsActualChest();

                if (coordsList.isEmpty()) {

                    List<Double> coords = this.seasonExo.getChestManager().getRandomPositionsInList();

                    this.seasonExo.getChestManager().SummonChest(player, coords.get(0), coords.get(1), coords.get(2));
                    this.seasonExo.getChestManager().setCoordsActualChest(coords.get(0), coords.get(1), coords.get(2));
                    this.seasonExo.getRunTaskManager().startAutoDispawn(player, coords);

                    String message = this.seasonExo.getConfigManager().getFormatedString("config.yml","chest-admin-summoning", true);
                    message = message.replace("%seasonexo_chestCoordsX%", PlaceholderAPI.setPlaceholders(player, "%seasonexo_chestCoordsX%"));
                    message = message.replace("%seasonexo_chestCoordsY%", PlaceholderAPI.setPlaceholders(player, "%seasonexo_chestCoordsY%"));
                    message = message.replace("%seasonexo_chestCoordsZ%", PlaceholderAPI.setPlaceholders(player, "%seasonexo_chestCoordsZ%"));

                    player.sendMessage(message);





                } else {
                    player.sendMessage("§cUn coffre est déjà présent sur la map");
                }




            } else if (args[0].equals("reload")) {

                this.seasonExo.getConfigManager().reload();
                player.sendMessage(this.seasonExo.getConfigManager().getFormatedString("config.yml","plugin-reloaded", true));







            } else if (args[0].equals("delete")) {
                List<Double> coordsList = this.seasonExo.getChestManager().getCoordsActualChest();

                if (!coordsList.isEmpty()) {
                    this.seasonExo.getChestManager().DeleteChest(player, coordsList.get(0), coordsList.get(1), coordsList.get(2));
                    this.seasonExo.getRunTaskManager().cancelAutoDispawn();
                    this.seasonExo.getRunTaskManager().reloadCountdown();

                    player.sendMessage(this.seasonExo.getConfigManager().getFormatedString("config.yml","chest-admin-deleting-succesfully", true));
                } else {
                    player.sendMessage(this.seasonExo.getConfigManager().getFormatedString("config.yml","chest-admin-deleting-error", true));
                }





            } else if (args[0].equals("additem")) {

                try {

                    String material = args[1];
                    int purcentage = Integer.parseInt(args[2]);

                    this.seasonExo.getChestManager().addToItemsList(material, purcentage);
                    player.sendMessage(this.seasonExo.getConfigManager().getFormatedString("config.yml","item-added-to-config", true));

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    player.sendMessage(this.seasonExo.getConfigManager().getFormatedString("config.yml","syntax-error", true));
                }





            } else if (args[0].equals("addpos")) {

                try {

                    double posX = Double.parseDouble(args[1]);
                    double posY = Double.parseDouble(args[2]);
                    double posZ = Double.parseDouble(args[3]);

                    this.seasonExo.getChestManager().addToPositionsList(posX, posY, posZ);
                    player.sendMessage(this.seasonExo.getConfigManager().getFormatedString("config.yml","position-added-to-config", true));

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    player.sendMessage(this.seasonExo.getConfigManager().getFormatedString("config.yml","syntax-error", true));
                }





            } else if (args[0].equals("close")) {

                if (args.length == 2) {

                    try {
                        Bukkit.getPlayer(args[1]).closeInventory();
                        Bukkit.getPlayer(args[1]).sendMessage(this.seasonExo.getConfigManager().getFormatedString("config.yml","force-closed-chest-for-player", true));
                        player.sendMessage(this.seasonExo.getConfigManager().getFormatedString("config.yml","force-closed-chest-for-admin", true));
                    } catch (NullPointerException e) {
                        player.sendMessage(this.seasonExo.getConfigManager().getFormatedString("config.yml","prefix", false) + "§cPseudo inccorect");
                    }

                } else {
                    player.sendMessage(this.seasonExo.getConfigManager().getFormatedString("config.yml","syntax-error", true));
                }





            } else {

                player.sendMessage(this.seasonExo.getConfigManager().getFormatedString("config.yml","syntax-error", true));

            }

        } else {

            player.sendMessage(this.seasonExo.getConfigManager().getFormatedString("config.yml","unknow-command", true));

        }

        return false;
    }

}
