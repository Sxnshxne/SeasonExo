package sunshine.seasonexo.managers;


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import sunshine.seasonexo.SeasonExo;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class ConfigManager {


    private SeasonExo seasonExo;

    public ConfigManager(SeasonExo seasonExo) {
        this.seasonExo = seasonExo;
    }




    private File configFile;

    private FileConfiguration configConfiguration;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void setup() {

        this.configFile = new File(Bukkit.getServer().getPluginManager().getPlugin("SeasonExo").getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            try { configFile.createNewFile(); }
            catch (IOException e) { throw new RuntimeException(e); }
        }

        this.configConfiguration = YamlConfiguration.loadConfiguration(configFile);

        this.seasonExo.getChestManager().initItemList();
        this.seasonExo.getChestManager().initPositionsList();

    }


    public FileConfiguration getFileConfiguration(String fileName) {
        return configConfiguration;
    }


    public void save() {
        try {
            configConfiguration.save(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void reload() {
        this.configConfiguration = YamlConfiguration.loadConfiguration(configFile);

        this.seasonExo.getChestManager().initItemList();
        this.seasonExo.getChestManager().initPositionsList();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /*public String getString(String file, String path) {
        return this.getFileConfiguration(file).getString(path);
    }*/

    public int getInt(String file, String path) {
        return this.getFileConfiguration(file).getInt(path);
    }

    /*public Double getDouble(String file, String path) {
        return this.getFileConfiguration(file).getDouble(path);
    }*/

    public List getList(String file, String path) {
        return this.getFileConfiguration(file).getList(path);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public String getFormatedString(String file, String path, boolean addPrefix) {

        String text = this.getFileConfiguration(file).getString(path);
        text = text.replace("&", "§");

        if (addPrefix) {
            String prefix = this.getFileConfiguration("config.yml").getString("prefix");
            prefix = prefix.replace("&", "§");

            return prefix + " " + text;

        } else return text;

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
