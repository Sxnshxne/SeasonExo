package sunshine.seasonexo.utils;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sunshine.seasonexo.chest.ChestManager;
import sunshine.seasonexo.listeners.ChestOpenListener;

import java.util.Objects;

public class PlaceholderAPIExtention extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "seasonexo";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Sunshine";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {


        if (player == null) { return ""; }


        if (params.equalsIgnoreCase("chestIsAppeared")) {

            if (ChestManager.getCoordsActualChest().isEmpty()) { return "Présent"; }
            else { return "Absent"; }

        }


        if (params.equalsIgnoreCase("timeBeforeDeleting")) {

            if (ChestManager.getCoordsActualChest().isEmpty()) {
                return "-1";
            }
            else {
                return String.valueOf(ChestManager.getCountdown());
            }

        }


        if (params.equalsIgnoreCase("chestHasBeenOpen")) {

            if (Objects.equals(ChestOpenListener.getPlayerWhoOpen(), "")) { return "Personne"; }
            else { return ChestOpenListener.getPlayerWhoOpen(); }

        }

        return super.onPlaceholderRequest(player, params);
    }
}
