package sunshine.seasonexo.datas;


import java.io.FileNotFoundException;
import java.util.HashMap;

import static sunshine.seasonexo.utils.JsonManager.readFile;

public class MessagesManager {

    static String prefix;
    static String syntaxError;
    static String unknowCommand;
    static String missingPermission;
    static String pluginReloaded;
    static String chestSummoning;
    static String chestDeletingSuccesfully;
    static String chestDeletingError;
    static String itemAddedToConfig;
    static String positionAddedToConfig;
    static String forceClosedChestForAdmin;
    static String forceClosedChestForPlayer;



    public static String GetPrefix() {
        return prefix + " ";
    }

    public static String GetSyntaxError() {
        return syntaxError;
    }

    public static String GetUnknowCommand() {
        return unknowCommand;
    }

    public static String GetMissingPermission() {
        return missingPermission;
    }

    public static String GetPluginReloaded() {
        return pluginReloaded;
    }

    public static String GetChestDeletingSuccesfully() {
        return chestDeletingSuccesfully;
    }

    public static String GetChestDeletingError() {
        return chestDeletingError;
    }

    public static String GetForceClosedChestForAdmin() {
        return forceClosedChestForAdmin;
    }

    public static String GetForceClosedChestForPlayer() {
        return forceClosedChestForPlayer;
    }

    public static String GetPositionAddedToConfig(Double x, Double y, Double z) {

        String formattedPositionAddedMessage = positionAddedToConfig.replace("{x}", String.valueOf(x));
        formattedPositionAddedMessage = formattedPositionAddedMessage.replace("{y}", String.valueOf(y));
        formattedPositionAddedMessage = formattedPositionAddedMessage.replace("{z}", String.valueOf(z));
        return formattedPositionAddedMessage;
    }

    public static String GetChestSummoning(Double x, Double y, Double z) {
        String formattedChestSummoning = chestSummoning.replace("{x}", String.valueOf(x));
        formattedChestSummoning = formattedChestSummoning.replace("{y}", String.valueOf(y));
        formattedChestSummoning = formattedChestSummoning.replace("{z}", String.valueOf(z));
        return formattedChestSummoning;
    }

    public static String GetItemAddedToConfig(String material, double purcentage) {
        String formattedItemAddedMessage = itemAddedToConfig.replace("{material}", String.valueOf(material));
        formattedItemAddedMessage = formattedItemAddedMessage.replace("{purcentage}", String.valueOf(purcentage));
        return formattedItemAddedMessage;
    }



    public static void messageSetup() {

        HashMap hashMap;

        try { hashMap = readFile("/SeasonExo/messages.json"); }
        catch (FileNotFoundException e) { throw new RuntimeException(e); }

        prefix = configFormatter(hashMap.get("prefix"));

        syntaxError = configFormatter(hashMap.get("syntax-error"));
        unknowCommand = configFormatter(hashMap.get("unknow-command"));
        missingPermission = configFormatter(hashMap.get("missing-permission"));
        pluginReloaded = configFormatter(hashMap.get("plugin-reloaded"));

        chestSummoning = configFormatter(hashMap.get("chest-summoning"));
        chestDeletingSuccesfully = configFormatter(hashMap.get("chest-deleting-succesfully"));
        chestDeletingError = configFormatter(hashMap.get("chest-deleting-error"));

        itemAddedToConfig = configFormatter(hashMap.get("item-added-to-config"));
        positionAddedToConfig = configFormatter(hashMap.get("position-added-to-config"));

        forceClosedChestForAdmin = configFormatter(hashMap.get("force-closed-chest-for-admin"));
        forceClosedChestForPlayer = configFormatter(hashMap.get("force-closed-chest-for-player"));
    }

    public static void reloadMessages() {
        messageSetup();
    }

    private static String configFormatter(Object value) {
        String text = String.valueOf(value);
        return text.replace("&", "§");
    }

}
