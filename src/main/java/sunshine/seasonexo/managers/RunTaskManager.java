package sunshine.seasonexo.managers;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import sunshine.seasonexo.SeasonExo;

import java.util.List;

public class RunTaskManager {


    private final SeasonExo seasonExo;

    public RunTaskManager(SeasonExo seasonExo) {
        this.seasonExo = seasonExo;
    }


    private BukkitTask taskId1;
    private BukkitTask taskId2;


    public void startAutoDispawn(Player player, List<Double> coords) {

        String message = this.seasonExo.getConfigManager().getFormatedString("message.yml","chest-timed-summoning",true);

        message = message.replace("%seasonexo_timeBeforeDeleting%", PlaceholderAPI.setPlaceholders(player, "%seasonexo_timeBeforeDeleting%"));
        Bukkit.broadcastMessage(message);

        this.startCountdown();


        this.taskId1 = Bukkit.getScheduler().runTaskLater(seasonExo, () -> {

            String message20S = this.seasonExo.getConfigManager().getFormatedString("message.yml","chest-timed-summoning",true);
            message20S = message20S.replace("%seasonexo_timeBeforeDeleting%", PlaceholderAPI.setPlaceholders(player, "%seasonexo_timeBeforeDeleting%"));
            Bukkit.broadcastMessage(message20S);

            }, 20L * 20L);


        this.taskId2 = Bukkit.getScheduler().runTaskLater(seasonExo, () -> {

            Bukkit.broadcastMessage(this.seasonExo.getConfigManager().getFormatedString("message.yml","chest-auto-deleting",true));
            this.seasonExo.getChestManager().DeleteChest(player, coords.get(0), coords.get(1), coords.get(2));
            this.reloadCountdown();

        }, 20L * 30L);
    }


    public void cancelAutoDispawn() {
        Bukkit.getScheduler().cancelTask(this.taskId1.getTaskId());
        Bukkit.getScheduler().cancelTask(this.taskId2.getTaskId());
        this.reloadCountdown();
    }





    private int countdownTimer = 30;
    private BukkitTask countdownTask;


    public void startCountdown() {
        countdownTask = new BukkitRunnable() {

            public void run() {
                if (countdownTimer == 0) { reloadCountdown(); }
                countdownTimer--;
            }

        }.runTaskTimer(seasonExo, 0L, 20L);
    }


    public int getCountdown() {
        return countdownTimer;
    }


    public void reloadCountdown() {
        Bukkit.getScheduler().cancelTask(countdownTask.getTaskId());
        countdownTimer = 30;
    }





    private String playerWhoOpen = "";

    public void autoDispawnAfterOpening(Player player, List<Double> coordsActualChest) {

        Object cooldown = this.seasonExo.getConfigManager().getInt("message.yml", "cooldown-autodispawn");
        String cooldownString = String.valueOf(cooldown).replace(".0", "");

        Bukkit.broadcastMessage("§cLe coffre disparaitra dans §e" + cooldownString + " §csecondes");
        Bukkit.broadcastMessage(this.seasonExo.getConfigManager().getFormatedString("message.yml","chest-founded",true));

        this.cancelAutoDispawn();
        this.reloadCountdown();

        Long cooldownLong = Long.parseLong(cooldownString);
        playerWhoOpen = player.getName();

        Bukkit.getScheduler().runTaskLater(seasonExo, () -> {

            this.seasonExo.getChestManager().DeleteChest(player, coordsActualChest.get(0), coordsActualChest.get(1), coordsActualChest.get(2));
            playerWhoOpen = "";

            }, 20L * cooldownLong);



    }



}
