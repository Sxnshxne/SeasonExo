package sunshine.seasonexo.chest;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import sunshine.seasonexo.datas.ItemsManager;
import sunshine.seasonexo.datas.MessagesManager;

import java.util.*;

public class ChestManager {


    static List coordsActualChest = new ArrayList<>();


    public static void SummonChest(Player player, Double posX, Double posY, Double posZ) {

        //place chest
        Location location = new Location(player.getWorld(), posX, posY, posZ);
        location.getBlock().setType(Material.CHEST);

        //get chest inventory
        final Block block = location.getBlock();
        Chest chest = (Chest) block.getState();
        Inventory inv = chest.getInventory();

        //randomiser
        List itemList = itemChooser(ItemsManager.getDictItem(), player);

        //add item on chest
        for (Object o : itemList) {
            inv.addItem((ItemStack) o);
        }


    }

    public static void DeleteChest(Player player, Double posX, Double posY, Double posZ) {

        //delete chest
        Location location = new Location(player.getWorld(), posX, posY, posZ);
        location.getBlock().setType(Material.AIR);

        coordsActualChest = new ArrayList<>();

    }



    public static List getCoordsActualChest() {
        return coordsActualChest;
    }

    public static void setCoordsActualChest(Double x, Double y, Double z) {
        coordsActualChest.add(x);
        coordsActualChest.add(y);
        coordsActualChest.add(z);
    }





    //create list of item who going on the chest
    private static List itemChooser(Map<String,Integer> itemDict, Player player) {

        List itemsList = new ArrayList();

        for ( String key : itemDict.keySet() ) {

            ItemStack itemStack = null;

            try {
                itemStack = new ItemStack(Material.valueOf(key));
            } catch (IllegalArgumentException e) {
                player.sendMessage(MessagesManager.GetPrefix() + MessagesManager.GetSyntaxError());
                itemStack = new ItemStack(Material.AIR);
            }
            
            double purcentage = Double.parseDouble(String.valueOf(itemDict.get(key)));

            if (purcentageDrop(purcentage)) { itemsList.add(itemStack); }

        }

        return itemsList;

    }


    //manage purcentage of drop an item
    private static boolean purcentageDrop(double percentage)
    {
        Random random = new Random();
        int i = (int) percentage;
        return random.nextInt(100) < i;
    }

}
