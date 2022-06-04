package sunshine.seasonexo;

import org.bukkit.plugin.java.JavaPlugin;
import sunshine.seasonexo.commands.AdminsCommands;
import sunshine.seasonexo.commands.ConsoleCommandes;
import sunshine.seasonexo.datas.ItemsManager;
import sunshine.seasonexo.datas.MessagesManager;
import sunshine.seasonexo.datas.PositionsManager;

import java.io.FileNotFoundException;
import java.util.Objects;


public final class SeasonExo extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[SEASON-EXO] Plugin started");


        //-- Commands --//
        Objects.requireNonNull(getCommand("seasonexo")).setExecutor(new AdminsCommands());
        Objects.requireNonNull(getCommand("cmdse")).setExecutor(new ConsoleCommandes());


        try {
            PositionsManager.setListOfPositions();
            ItemsManager.createDictItem();
            MessagesManager.messageSetup();
        }
        catch (FileNotFoundException e) { throw new RuntimeException(e); }


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
