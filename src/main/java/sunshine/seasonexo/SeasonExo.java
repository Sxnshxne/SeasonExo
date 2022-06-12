package sunshine.seasonexo;

import org.bukkit.plugin.java.JavaPlugin;
import sunshine.seasonexo.commands.AdminsCommands;
import sunshine.seasonexo.managers.*;
import sunshine.seasonexo.listeners.ChestBreakListener;
import sunshine.seasonexo.listeners.ChestOpenListener;
import sunshine.seasonexo.utils.PlaceholderAPIExtention;

import java.util.Objects;


public final class SeasonExo extends JavaPlugin {


    private RunTaskManager runTaskManager;
    private ChestManager chestManager;
    private ConfigManager configManager;


    @Override
    public void onEnable() {


        this.runTaskManager = new RunTaskManager(this);
        this.chestManager = new ChestManager(this);
        this.configManager = new ConfigManager(this);


        //Config
        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();

        this.getConfigManager().setup();


        //Commands register
        Objects.requireNonNull(getCommand("seasonexo")).setExecutor(new AdminsCommands(this));



        //Listeners register
        this.getServer().getPluginManager().registerEvents(new ChestOpenListener(this), this);
        this.getServer().getPluginManager().registerEvents(new ChestBreakListener(this), this);


        //Placeholder register
        new PlaceholderAPIExtention(this).register();

    }


    public RunTaskManager getRunTaskManager() {
        return runTaskManager;
    }
    public ChestManager getChestManager() {
        return chestManager;
    }
    public ConfigManager getConfigManager() {
        return configManager;
    }

}
