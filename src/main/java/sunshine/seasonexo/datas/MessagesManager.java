package sunshine.seasonexo.datas;


import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static sunshine.seasonexo.utils.JsonManager.readFile;

public class MessagesManager {

    static String prefix;
    static String syntaxError;
    static String unknowCommand;
    static String missingPermission;
    static String pluginReloaded;
    static String chestAdminSummoning;
    static String chestTimedSummoning;
    static String chestAutoDeleting;
    static String chestAdminDeletingSuccesfully;
    static String chestAdminDeletingError;
    static String itemAddedToConfig;
    static String positionAddedToConfig;
    static String forceClosedChestForAdmin;
    static String forceClosedChestForPlayer;
    static String chestFounded;



    public static String GetSyntaxError() {
        return prefix + " " + syntaxError;
    }

    public static String GetUnknowCommand() {
        return prefix + " "  + unknowCommand;
    }

    public static String GetMissingPermission() {
        return prefix + " " + missingPermission;
    }

    public static String GetPluginReloaded() {
        return prefix + " " + pluginReloaded;
    }

    public static String GetChestAutoDeleting() {
        return prefix + " " + chestAutoDeleting;
    }

    public static String GetAdminChestDeletingSuccesfully() {
        return prefix + " " + chestAdminDeletingSuccesfully;
    }

    public static String GetAdminChestDeletingError() {
        return prefix + " " + chestAdminDeletingError;
    }

    public static String GetForceClosedChestForAdmin() {
        return prefix + " " + forceClosedChestForAdmin;
    }

    public static String GetForceClosedChestForPlayer() {
        return prefix + " " + forceClosedChestForPlayer;
    }

    public static String GetChestFounded() { return prefix + " " + chestFounded; }

    public static String GetPositionAddedToConfig(Double x, Double y, Double z) {

        String formattedPositionAddedMessage = positionAddedToConfig.replace("{x}", String.valueOf(x));
        formattedPositionAddedMessage = formattedPositionAddedMessage.replace("{y}", String.valueOf(y));
        formattedPositionAddedMessage = formattedPositionAddedMessage.replace("{z}", String.valueOf(z));
        return prefix + " " + formattedPositionAddedMessage;
    }

    public static String GetChestAdminSummoning(Double x, Double y, Double z) {
        String formattedChestSummoning = chestAdminSummoning.replace("{x}", String.valueOf(x));
        formattedChestSummoning = formattedChestSummoning.replace("{y}", String.valueOf(y));
        formattedChestSummoning = formattedChestSummoning.replace("{z}", String.valueOf(z));
        return prefix + " " + formattedChestSummoning;
    }

    public static String GetChestTimedSummoning(int time) {
        String formattedChestSummoning = chestTimedSummoning.replace("{time}", String.valueOf(time));
        return prefix + " " + formattedChestSummoning;
    }

    public static String GetItemAddedToConfig(String material, double purcentage) {
        String formattedItemAddedMessage = itemAddedToConfig.replace("{material}", String.valueOf(material));
        formattedItemAddedMessage = formattedItemAddedMessage.replace("{purcentage}", String.valueOf(purcentage));
        return prefix + " " + formattedItemAddedMessage;
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

        chestAdminSummoning = configFormatter(hashMap.get("chest-admin-summoning"));
        chestTimedSummoning = configFormatter(hashMap.get("chest-timed-summoning"));
        chestAutoDeleting = configFormatter(hashMap.get("chest-auto-deleting"));

        chestAdminDeletingSuccesfully = configFormatter(hashMap.get("chest-admin-deleting-succesfully"));
        chestAdminDeletingError = configFormatter(hashMap.get("chest-admin-deleting-error"));

        itemAddedToConfig = configFormatter(hashMap.get("item-added-to-config"));
        positionAddedToConfig = configFormatter(hashMap.get("position-added-to-config"));

        forceClosedChestForAdmin = configFormatter(hashMap.get("force-closed-chest-for-admin"));
        forceClosedChestForPlayer = configFormatter(hashMap.get("force-closed-chest-for-player"));

        chestFounded = configFormatter(hashMap.get("chest-founded"));
    }

    public static void reloadMessages() {
        messageSetup();
    }

    private static String configFormatter(Object value) {

        String text = String.valueOf(value);
        text = text.replace("&", "§");

        return text;
    }

}
