package me.lord0z.anticheat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Lord0zAntiCHEAT extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("Lord0z AntiCHEAT aktif!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Lord0z AntiCHEAT kapatildi.");
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (player.isOp()
                || player.hasPermission("lord0zanticheat.bypass")) {
            return;
        }

        if (event.getTo() == null) return;

        double dy = event.getTo().getY()
                - event.getFrom().getY();

        if (dy > 1.0
                && !player.isFlying()
                && !player.isInsideVehicle()) {

            String message = ChatColor.DARK_PURPLE
                    + "[Lord0z AntiCHEAT] "
                    + ChatColor.WHITE + player.getName()
                    + ChatColor.GRAY
                    + " supheli yukselme hareketi yapti.";

            for (Player staff : Bukkit.getOnlinePlayers()) {
                if (staff.isOp()
                        || staff.hasPermission(
                                "lord0zanticheat.alerts")) {
                    staff.sendMessage(message);
                }
            }

            getLogger().info(
                    player.getName()
                    + " icin supheli yukselme algilandi."
            );
        }
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args) {

        if (!command.getName().equalsIgnoreCase("lac")) {
            return false;
        }

        if (!sender.hasPermission("lord0zanticheat.admin")) {
            sender.sendMessage(ChatColor.RED
                    + "Bu komut icin yetkin yok.");
            return true;
        }

        sender.sendMessage(ChatColor.DARK_PURPLE
                + "Lord0z AntiCHEAT "
                + ChatColor.GRAY
                + "| Deneysel surum aktif.");
        sender.sendMessage(ChatColor.YELLOW
                + "Bu surum hile suphelerini bildirir; "
                + "otomatik ban uygulamaz.");

        return true;
    }
}
