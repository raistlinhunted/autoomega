package org.omega.autoomega;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

public class Boot extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");

    public void onEnable() {

    }

    public void onDisable() {

    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        return true; //Returns error to the player if return false. Otherwise if returned true, it doesnt send them any message
    }
}
