package org.omega.autoomega;

/**
 * Created with IntelliJ IDEA.
 * User: Jon
 * Date: 3/10/13
 * Time: 4:18 PM
 */

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.logging.Logger;

public class Boot extends JavaPlugin {

    private static final int DEFAULT_MIN = 0, DEFAULT_HOUR = 2;

    private static final Logger log = Logger.getLogger("Minecraft");
    private int lastRestart; //iI minutes
    private int timeLimit;

    public void onEnable() {
        loadConfig();
        lastRestart = (int) (System.currentTimeMillis() / (1000 * 60)); //Get the minutes at which the server started

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run()
            {
                //Find out if the time is up
                int minutes = (int) (System.currentTimeMillis() / (1000 * 60));
                int remaining = minutes - lastRestart;
                if (remaining <= 5) //Restarting in 5 minutes
                    getServer().broadcastMessage("Restarting in five minutes!");
                if (remaining <= 1) //Restarting in 1 minute
                    getServer().broadcastMessage("Restarting in one minute!");
                if (remaining >= timeLimit) //If the time passed since the last restart is greater than or equal to the time limit then restart
                    restartServer();
            }
        }, 0L, 20L);  //Will go through the run method every second

        log.info("[Auto Omega] Started Successfully.");
    }

    public void onDisable() {
        log.info("[Auto Omega] Shutting down.");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        return true; //Returns error to the player if return false. Otherwise if returned true, it doesnt send them any message
    }

    public void restartServer() {
        for (Player player : getServer().getOnlinePlayers()) //Kick everybody online
            player.kickPlayer("Restarting Server!");
        getServer().shutdown(); //Restart the server
    }

    public void loadConfig() {
        if (!FileManager.fileExists("plugins/Auto Omega/")) { //If the Auto Omega directory doesnt exist, create it.
            FileManager.createDirectory("plugins/Auto Omega");
        }
        if (!FileManager.fileExists("plugins/Auto Omega/config.txt")) { //If the config file doesnt exist, create it.
            FileManager.createFile("plugins/Auto Omega/config.txt");
            BufferedWriter bw = FileManager.writeFile(FileManager.loadFile("plugins/Auto Omega/config.txt")); //Load file and get bufferedwriter
            try {
                //Write default config info
                bw.write("Minutes: " + DEFAULT_MIN);
                bw.newLine();
                bw.write("Hours: " + DEFAULT_HOUR);
                bw.close(); //Always close an open stream
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Everything should be created if it wasn't so lets load it.
        String[] config = FileManager.readLines(FileManager.readFile(FileManager.loadFile("plugins/Auto Omega/config.txt"))); //Using the functions I made in FileManager, first create a file, then read from it, then get the lines

        int mins, hours;

        String minStr = config[0].trim().substring(8); //Get just the numbers at the end of the minutes
        if (isInteger(minStr)) { //If its a number
            int min = Integer.parseInt(minStr); //Get the number
            mins = min; //Set it to the desired minutes
            if (min > 60) mins = 60; //If it's greater than 60, make it 60
            if (min < 0) mins = 0;   //If it's less than 0, make it 0
        } else { //If it's not a number
            mins = DEFAULT_MIN; //Set it to the default
        }

        String hourStr = config[1].trim().substring(6); //Get just the numbers at the end of the hours
        if (isInteger(hourStr)) { //If its a number
            int hour = Integer.parseInt(hourStr); //Get the number
            hours = hour; //Set it to the desired hours
            if (hour > 24) hours = 24; //If it's greater than 24, make it 24
            if (hour < 0) hours = 0; //If it's less than 0, make it 0
        } else { //If it's not a number
            hours = DEFAULT_HOUR; //Set it to the default
        }

        timeLimit += (hours * 60); //Get the number of minutes and add it to the time limit
        timeLimit += mins; //Add the minutes
    }

    public boolean isInteger(String s) {
        try {
            int i = Integer.parseInt(s); //Create an integer from the string
            return true; //If it made it here it means that the string was a number and didnt contain letters
        } catch (Exception e) {
            return false; //The string contained characters other than numbers
        }
    }
}
