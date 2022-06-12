package sunshine.seasonexo.utils;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sunshine.seasonexo.SeasonExo;
import sunshine.seasonexo.listeners.ChestOpenListener;


public class PlaceholderAPIExtention extends PlaceholderExpansion {

    private SeasonExo seasonExo;

    public PlaceholderAPIExtention(SeasonExo seasonExo) {
        this.seasonExo = seasonExo;
    }



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


        if (params.equals("chestIsAppeared")) {

            if (this.seasonExo.getChestManager().getCoordsActualChest().isEmpty()) { return "Présent"; }
            else { return "Absent"; }

        }


        if (params.equals("timeBeforeDeleting")) {

            if (this.seasonExo.getChestManager().getCoordsActualChest().isEmpty()) {
                return "-1";
            }
            else {
                return String.valueOf(this.seasonExo.getRunTaskManager().getCountdown());
            }

        }


        if (params.equals("chestHasBeenOpen")) {

            if (ChestOpenListener.getPlayerWhoOpen() == "") { return "Personne"; }
            else { ChestOpenListener.getPlayerWhoOpen(); }

        }


        if (params.equals("chestCoordsX")) {

            if (this.seasonExo.getChestManager().getCoordsActualChest().isEmpty()) { return "Null"; }
            else { return this.seasonExo.getChestManager().getCoordsActualChest().get(0).toString(); }

        }


        if (params.equals("chestCoordsY")) {

            if (this.seasonExo.getChestManager().getCoordsActualChest().isEmpty()) { return "Null"; }
            else { return this.seasonExo.getChestManager().getCoordsActualChest().get(1).toString(); }

        }


        if (params.equals("chestCoordsZ")) {

            if (this.seasonExo.getChestManager().getCoordsActualChest().isEmpty()) { return "Null"; }
            else { return this.seasonExo.getChestManager().getCoordsActualChest().get(2).toString(); }

        }


        return super.onPlaceholderRequest(player, params);
    }
}
