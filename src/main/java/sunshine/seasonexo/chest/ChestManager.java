package sunshine.seasonexo.chest;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import sunshine.seasonexo.datas.ItemDictManager;
import sunshine.seasonexo.utils.MessagesManager;

import java.util.*;

public class ChestManager {


    public static void SummonChest(Player player, int posX, int posY, int posZ) {

        //place chest
        Location location = new Location(player.getWorld(), posX, posY, posZ);
        location.getBlock().setType(Material.CHEST);

        //get chest inventory
        final Block block = location.getBlock();
        Chest chest = (Chest) block.getState();
        Inventory inv = chest.getInventory();

        //randomiser
        List itemList = itemChooser(ItemDictManager.getDictItem(), player);

        //add item on chest
        for (Object o : itemList) {
            inv.addItem((ItemStack) o);
        }


    }






    //create list of item who going on the chest
    private static List itemChooser(Map<String,Integer> itemDict, Player player) {

        List itemsList = new ArrayList();

        for ( String key : itemDict.keySet() ) {

            ItemStack itemStack = null;

            try {
                itemStack = new ItemStack(Material.valueOf(key));
            } catch (IllegalArgumentException e) {
                player.sendMessage(MessagesManager.Prefix() + "§cUn ou plusieurs items dans le fichier de configuration §6items.yml §cest mal orthographié");
                itemStack = new ItemStack(Material.AIR);
            }
            
            int purcentage = Integer.parseInt(String.valueOf(itemDict.get(key)));

            if (purcentageDrop(purcentage)) { itemsList.add(itemStack); }

        }

        return itemsList;

    }


    //manage purcentage of drop an item
    private static boolean purcentageDrop(int percentage)
    {
        Random random = new Random();
        return random.nextInt(100) < percentage;
    }


}
