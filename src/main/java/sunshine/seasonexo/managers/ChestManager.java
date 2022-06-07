package sunshine.seasonexo.chest;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import sunshine.seasonexo.SeasonExo;
import sunshine.seasonexo.datas.ItemsManager;
import sunshine.seasonexo.datas.MessagesManager;

import java.io.FileNotFoundException;
import java.util.*;

public class ChestManager {


    static List coordsActualChest = new ArrayList<>();

    public ChestManager() throws FileNotFoundException {
    }


    public static void SummonChest(Player player, Double posX, Double posY, Double posZ) {

        //place chest
        Location location = new Location(player.getWorld(), posX, posY, posZ);
        location.getBlock().setType(Material.CHEST);

        //rename chest
        Block chestBlock = location.getBlock();
        Chest chestState = (Chest) chestBlock.getState();
        chestState.setCustomName("§b§lSeason§a§lChest");
        chestState.update();

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

    //delete chest
    public static void DeleteChest(Player player, Double posX, Double posY, Double posZ) {

        //delete chest
        Location location = new Location(player.getWorld(), posX, posY, posZ);
        location.getBlock().setType(Material.AIR);

        coordsActualChest = new ArrayList<>();

    }


    //countdown
    static int countdown = 31;
    static BukkitTask task;

    public static void startCountdown() {
         task = new BukkitRunnable() {
            public void run() {

                if (countdown == 0) {
                    reloadCountdown();
                }

                countdown--;

            }

        }.runTaskTimer(SeasonExo.plugin(), 0L, 20L);
    }

    public static int getCountdown() {
        return countdown;
    }

    public static void reloadCountdown() {
        Bukkit.getScheduler().cancelTask(task.getTaskId());
        countdown = 31;
    }





    //get coords of placed chest
    public static List getCoordsActualChest() {
        return coordsActualChest;
    }

    //set coords of placed chest
    public static void setCoordsActualChest(Double x, Double y, Double z) {
        coordsActualChest.add(x);
        coordsActualChest.add(y);
        coordsActualChest.add(z);
    }






    //create list of item who going on the chest
    private static List itemChooser(Map<String, Integer> itemDict, Player player) {

        List itemsList = new ArrayList();

        for ( String key : itemDict.keySet() ) {

            ItemStack itemStack = null;

            try {
                itemStack = new ItemStack(Material.valueOf(key));
            } catch (IllegalArgumentException e) {
                player.sendMessage(MessagesManager.GetSyntaxError());
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
