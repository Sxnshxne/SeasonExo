package sunshine.seasonexo.managers;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import sunshine.seasonexo.SeasonExo;

import java.util.*;

public class ChestManager {


    private SeasonExo seasonExo;

    public ChestManager(SeasonExo seasonExo) {
        this.seasonExo = seasonExo;
    }


    List<Double> coordsActualChest = new ArrayList<>();

    public void SummonChest(Player player, Double posX, Double posY, Double posZ) {

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
        List itemList = this.itemChooser();

        //add item on chest
        for (Object o : itemList) {
            inv.addItem((ItemStack) o);
        }


    }

    //delete chest
    public void DeleteChest(Player player, Double posX, Double posY, Double posZ) {

        //delete chest
        Location location = new Location(player.getWorld(), posX, posY, posZ);
        location.getBlock().setType(Material.AIR);

        coordsActualChest = new ArrayList<>();

    }




    //get coords of placed chest
    public List<Double> getCoordsActualChest() {
        return coordsActualChest;
    }

    //set coords of placed chest
    public void setCoordsActualChest(Double x, Double y, Double z) {
        coordsActualChest.add(x);
        coordsActualChest.add(y);
        coordsActualChest.add(z);
    }





    //create list of item who going on the chest
    private List itemChooser() {

        List itemsListToChest = new ArrayList();

        this.itemsList.forEach((n) -> {
            if (purcentageDrop((Integer) n.get(1))) {

                ItemStack item = new ItemStack(Material.valueOf((String) n.get(0)), 1);
                itemsListToChest.add(item);
            }
        });

        return itemsListToChest;

    }


    //manage purcentage of drop an item
    private boolean purcentageDrop(Integer percentage) {

        Random random = new Random();
        return random.nextInt(100) < percentage;

    }






    // --- ITEMS MANAGER ---//
    private List<List> itemsList;


    public void initItemList() {
        this.itemsList = this.seasonExo.getConfigManager().getList("message.yml", "items");
    }

    private List<List> getItemsList() {
        return itemsList;
    }

    public void addToItemsList(String material, int purcentage) {
        List newItem = new ArrayList();
        newItem.add(material);
        newItem.add(purcentage);
        this.itemsList.add(newItem);
        this.seasonExo.getConfigManager().save();
    }






    // --- POSITION MANAGER ---//
    private List<List> positionsList;

    public void initPositionsList() {
        this.positionsList = this.seasonExo.getConfigManager().getList("message.yml", "positions");
    }

    private List<List> getPositionsList() {
        return positionsList;
    }

    public List getRandomPositionsInList() {

        Random random = new Random();
        int randomValue = random.nextInt(positionsList.size());

        List coords = new ArrayList<>();

        coords.add(positionsList.get(randomValue).get(0));
        coords.add(positionsList.get(randomValue).get(1));
        coords.add(positionsList.get(randomValue).get(2));

        return coords;
    }

    public void addToPositionsList(Double X, Double Y, Double Z) {
        List newPosition = new ArrayList();
        newPosition.add(X);
        newPosition.add(Y);
        newPosition.add(Z);
        this.positionsList.add(newPosition);
        this.seasonExo.getConfigManager().save();
    }

}
