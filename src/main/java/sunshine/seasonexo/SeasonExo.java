package sunshine.seasonexo;

import org.bukkit.plugin.java.JavaPlugin;
import sunshine.seasonexo.commands.AdminsCommands;

import java.util.Objects;


public final class SeasonExo extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[SEASON-EXO] Plugin started");


        //-- Commands --//
        Objects.requireNonNull(getCommand("seasonexo")).setExecutor(new AdminsCommands());


        //-- Listeners --//
        //this.getServer().getPluginManager().registerEvents(new MinerListener(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
