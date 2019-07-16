package com.github.qq44920040;

import com.github.qq44920040.NetWork.ReciveMsg;
import com.github.qq44920040.NetWork.SendMsg;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


import java.util.HashSet;
import java.util.Set;

public class sitgod extends JavaPlugin implements Listener {
    public static Set<String> sitgodinfo = new HashSet<>();
    public static Plugin plugin;
    @Override
    public void onEnable() {
        getServer().getMessenger().registerIncomingPluginChannel(this,"sitgod", new ReciveMsg());
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "sitgod");
        getServer().getPluginManager().registerEvents(this,this);
        plugin=this;
        super.onEnable();
    }

    @EventHandler
    public void PlayerJoinGame(PlayerJoinEvent event){
        Player player = event.getPlayer();
        new BukkitRunnable(){
            @Override
            public void run() {
                SendMsg.SendPlayersSitInfo(player);
            }
        }.runTaskTimer(this,20L,40L);
    }
}
