package sunshine.seasonexo;

import org.bukkit.plugin.java.JavaPlugin;
import sunshine.seasonexo.commands.AdminsCommands;
import sunshine.seasonexo.datas.ItemsManager;
import sunshine.seasonexo.datas.MessagesManager;
import sunshine.seasonexo.datas.PositionsManager;
import sunshine.seasonexo.listeners.ChestBreakListener;
import sunshine.seasonexo.listeners.ChestOpenListener;
import sunshine.seasonexo.utils.PlaceholderAPIExtention;

import java.io.FileNotFoundException;
import java.util.Objects;


public final class SeasonExo extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[SEASON-EXO] Plugin started");


        //-- Commands --//
        Objects.requireNonNull(getCommand("seasonexo")).setExecutor(new AdminsCommands());


        try {
            PositionsManager.setListOfPositions();
            ItemsManager.createDictItem();
            MessagesManager.messageSetup();
        }
        catch (FileNotFoundException e) { throw new RuntimeException(e); }

        this.getServer().getPluginManager().registerEvents(new ChestOpenListener(), this);
        this.getServer().getPluginManager().registerEvents(new ChestBreakListener(), this);

        new PlaceholderAPIExtention().register();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static SeasonExo plugin() {
        return SeasonExo.getPlugin(SeasonExo.class);
    }

}
